package com.tecdes.gerenciador.service;

import com.tecdes.gerenciador.model.entity.Pedido;
import com.tecdes.gerenciador.model.entity.ItemPedido;
import com.tecdes.gerenciador.model.entity.Produto;
import com.tecdes.gerenciador.model.StatusPedido;
import com.tecdes.gerenciador.repository.IPedidoRepository;

import java.util.HashSet;
import java.util.Set;

public class PedidoService {
    
    private IPedidoRepository pedidoRepository = null;
    
    public PedidoService() {
        this.pedidoRepository = pedidoRepository;
    }
    
    // ========== OPERAÇÕES BÁSICAS DE PEDIDO ==========
    
    public Pedido criarPedido(Integer codigo, String descricao) {
        validarCodigoPedido(codigo);
        
        Pedido pedido = new Pedido();
        pedido.setCd_pedido(codigo);
        pedido.setDs_pedido(descricao);
        pedido.setStatus(StatusPedido.PENDENTE);
        
        return pedidoRepository.save(pedido);
    }
    
    private void validarCodigoPedido(Integer codigo) {
        if (codigo == null || codigo <= 0) {
            throw new IllegalArgumentException("Código do pedido inválido.");
        }
        
        if (pedidoRepository.existsByCodigo(codigo)) {
            throw new IllegalArgumentException("Código já existe.");
        }
    }
    
    public Double calcularTotal(Pedido pedido) {
        validarPedidoParaCalculo(pedido);
        
        double total = 0.0;
        for (ItemPedido item : pedido.getItens()) {
            validarItemParaCalculo(item);
            total += item.calcularTotal();
        }
        
        pedido.setTotal(total);
        return total;
    }
    
    private void validarPedidoParaCalculo(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo.");
        }
        
        if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
            throw new IllegalArgumentException("Pedido sem itens.");
        }
        
        // Verificar produtos duplicados
        Set<Integer> produtosIds = new HashSet<>();
        for (ItemPedido item : pedido.getItens()) {
            if (item.getProduto() != null) {
                Integer produtoId = item.getProduto().getId_produto();
                if (produtoId != null && !produtosIds.add(produtoId)) {
                    throw new IllegalArgumentException("Produto duplicado: " + item.getProduto().getNm_produto());
                }
            }
        }
    }
    
    private void validarItemParaCalculo(ItemPedido item) {
        if (item.getProduto() == null) {
            throw new IllegalArgumentException("Item sem produto.");
        }
        
        if (item.getProduto().getVl_produto() == null) {
            throw new IllegalArgumentException("Produto sem preço: " + item.getProduto().getNm_produto());
        }
        
        if (item.getQuantidade() == null || item.getQuantidade() <= 0) {
            throw new IllegalArgumentException("Quantidade inválida para: " + item.getProduto().getNm_produto());
        }
    }
    
    // ========== GERENCIAMENTO DE ITENS ==========
    
    public void adicionarItem(Pedido pedido, Produto produto, Integer quantidade) {
        if (pedido.isFinalizado()) {
            throw new IllegalStateException("Pedido finalizado não aceita alterações.");
        }
        
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }
        
        if (quantidade == null || quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade inválida.");
        }
        
        ItemPedido item = new ItemPedido(produto, quantidade);
        pedido.adicionarItem(item);
        calcularTotal(pedido);
    }
    
    public void removerItem(Pedido pedido, int index) {
        if (pedido.isFinalizado()) {
            throw new IllegalStateException("Pedido finalizado não aceita alterações.");
        }
        
        if (index < 0 || index >= pedido.getItens().size()) {
            throw new IllegalArgumentException("Índice inválido.");
        }
        
        pedido.removerItem(index);
        
        if (!pedido.getItens().isEmpty()) {
            calcularTotal(pedido);
        } else {
            pedido.setTotal(0.0);
        }
    }
    
    // ========== CONTROLE DE STATUS ==========
    
    public void alterarStatus(Pedido pedido, StatusPedido novoStatus) {
        if (pedido == null || novoStatus == null) {
            throw new IllegalArgumentException("Parâmetros inválidos.");
        }
        
        StatusPedido atual = pedido.getStatus();
        validarTransicaoStatus(atual, novoStatus);
        
        pedido.setStatus(novoStatus);
        
        // Recalcular ao finalizar
        if (novoStatus == StatusPedido.ENTREGUE) {
            calcularTotal(pedido);
        }
    }
    
    private void validarTransicaoStatus(StatusPedido atual, StatusPedido novo) {
        // Não pode alterar status finalizados
        if (atual == StatusPedido.ENTREGUE || atual == StatusPedido.CANCELADO) {
            throw new IllegalStateException("Pedido finalizado não pode ter status alterado.");
        }
        
        // Fluxo permitido:
        // PENDENTE -> PROCESSANDO -> PRONTO -> ENTREGUE
        // Qualquer status pode ir para CANCELADO
        
        switch (atual) {
            case PENDENTE:
                if (!(novo == StatusPedido.PROCESSANDO || novo == StatusPedido.CANCELADO)) {
                    throw new IllegalStateException("De PENDENTE só pode ir para PROCESSANDO ou CANCELADO.");
                }
                break;
                
            case PROCESSANDO:
                if (!(novo == StatusPedido.PRONTO || novo == StatusPedido.CANCELADO)) {
                    throw new IllegalStateException("De PROCESSANDO só pode ir para PRONTO ou CANCELADO.");
                }
                break;
                
            case PRONTO:
                if (!(novo == StatusPedido.ENTREGUE || novo == StatusPedido.CANCELADO)) {
                    throw new IllegalStateException("De PRONTO só pode ir para ENTREGUE ou CANCELADO.");
                }
                break;
        }
    }
    
    public void cancelarPedido(Pedido pedido) {
        alterarStatus(pedido, StatusPedido.CANCELADO);
    }
    
    public void finalizarPedido(Pedido pedido) {
        alterarStatus(pedido, StatusPedido.ENTREGUE);
    }
    
    // ========== CONSULTAS SIMPLES ==========
    
    public Pedido buscarPorCodigo(Integer codigo) {
        return pedidoRepository.findByCodigo(codigo);
    }
    
    public boolean pedidoPodeSerCancelado(Pedido pedido) {
        return pedido != null && 
               !pedido.isFinalizado() && 
               pedido.getStatus() != StatusPedido.CANCELADO;
    }

    public void atualizarTotaisDosItens(Pedido pedido) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}