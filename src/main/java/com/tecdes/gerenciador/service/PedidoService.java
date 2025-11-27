package com.tecdes.gerenciador.service;

import com.tecdes.gerenciador.model.entity.Pedido;
import com.tecdes.gerenciador.model.entity.ItemPedido;

public class PedidoService {

    public void validarPedido(Pedido pedido) {

        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo.");
        }

        if (pedido.getCd_pedido() == null) {
            throw new IllegalArgumentException("Código do pedido é obrigatório.");
        }

        if (pedido.getSt_pedido() == null || pedido.getSt_pedido().isBlank()) {
            throw new IllegalArgumentException("Status do pedido é obrigatório.");
        }

        if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
            throw new IllegalArgumentException("O pedido deve conter ao menos 1 item.");
        }
    }


    public Double calcularTotal(Pedido pedido) {

        validarPedido(pedido);

        double totalPedido = 0.0;

        for (ItemPedido item : pedido.getItens()) {

            if (item.getProduto() == null) {
                throw new IllegalArgumentException("Item sem produto.");
            }

            if (item.getProduto().getVl_produto() == null) {
                throw new IllegalArgumentException("Produto sem preço cadastrado.");
            }

            if (item.getQuantidade() == null || item.getQuantidade() <= 0) {
                throw new IllegalArgumentException("Quantidade do item inválida.");
            }

            totalPedido += item.calcularTotal();
        }

        pedido.setTotal(totalPedido);
        return totalPedido;
    }


    public void alterarStatus(Pedido pedido, String novoStatus) {

        if (novoStatus == null || novoStatus.isBlank()) {
            throw new IllegalArgumentException("Novo status inválido.");
        }

        pedido.setSt_pedido(novoStatus);
    }


    public void atualizarTotaisDosItens(Pedido pedido) {

        if (pedido.getItens() == null) return;

        for (ItemPedido item : pedido.getItens()) {
            item.calcularTotal();
        }
    }
}
