package com.tecdes.gerenciador.service;

import com.tecdes.gerenciador.model.StatusPedido;
import com.tecdes.gerenciador.model.entity.Pedido;
import com.tecdes.gerenciador.repository.PedidoRepository;

import java.util.List;

public class PedidoService {
    
    private final PedidoRepository pedidoRepository;
    
    public PedidoService() {
        this.pedidoRepository = new PedidoRepository();
    }
    
    public Pedido criarPedido(Integer codigo, String descricao, Integer clienteId) {
        validarCodigoPedido(codigo);
        
        Pedido pedido = new Pedido();
        pedido.setCd_pedido(codigo);
        pedido.setDs_pedido(descricao);
        pedido.setId_cliente(clienteId);
        pedido.setSt_pedido("P"); // Pendente
        
        return pedidoRepository.save(pedido);
    }
    
    public Pedido atualizarPedido(Integer id, Integer codigo, String descricao, String status, Double total, Integer clienteId) {
        Pedido pedido = pedidoRepository.findById(id);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado: " + id);
        }
        
        // Verificar se código foi alterado
        if (!pedido.getCd_pedido().equals(codigo) && pedidoRepository.existsByCodigo(codigo)) {
            throw new IllegalArgumentException("Código já existe: " + codigo);
        }
        
        pedido.setCd_pedido(codigo);
        pedido.setDs_pedido(descricao);
        pedido.setSt_pedido(status != null ? status : pedido.getSt_pedido());
        pedido.setTotal(total);
        pedido.setId_cliente(clienteId);
        
        return pedidoRepository.update(pedido);
    }
    
    public boolean removerPedido(Integer id) {
        return pedidoRepository.delete(id);
    }
    
    public Pedido buscarPorId(Integer id) {
        return pedidoRepository.findById(id);
    }
    
    public Pedido buscarPorCodigo(Integer codigo) {
        return pedidoRepository.findByCodigo(codigo);
    }
    
    public List<Pedido> buscarPorCliente(Integer clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }
    
    public List<Pedido> buscarPorStatus(String status) {
        return pedidoRepository.findByStatus(status);
    }
    
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }
    
    public boolean alterarStatus(Integer id, String novoStatus) {
        Pedido pedido = pedidoRepository.findById(id);
        if (pedido != null) {
            pedido.setSt_pedido(novoStatus);
            pedidoRepository.update(pedido);
            return true;
        }
        return false;
    }
    
    public boolean cancelarPedido(Integer id) {
        return alterarStatus(id, "C"); // C = Cancelado
    }
    
    public boolean finalizarPedido(Integer id) {
        return alterarStatus(id, "D"); // D = Entregue
    }
    
    private void validarCodigoPedido(Integer codigo) {
        if (codigo == null || codigo <= 0) {
            throw new IllegalArgumentException("Código do pedido inválido");
        }
        
        if (pedidoRepository.existsByCodigo(codigo)) {
            throw new IllegalArgumentException("Código já existe: " + codigo);
        }
    }
    
    // Métodos para estatísticas
    public int contarPedidos() {
        return pedidoRepository.findAll().size();
    }
    
    public int contarPedidosPendentes() {
        return pedidoRepository.countByStatus("P");
    }
    
    public int contarPedidosConcluidos() {
        return pedidoRepository.countByStatus("D");
    }
    
    public int contarPedidosCancelados() {
        return pedidoRepository.countByStatus("C");
    }
}