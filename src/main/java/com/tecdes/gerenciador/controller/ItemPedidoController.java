package com.tecdes.gerenciador.controller;

import com.tecdes.gerenciador.model.entity.ItemPedido;
import com.tecdes.gerenciador.model.entity.Pedido;
import com.tecdes.gerenciador.model.entity.Produto;
import com.tecdes.gerenciador.service.PedidoService;

public class ItemPedidoController {

    private final PedidoService pedidoService;

    public ItemPedidoController() {
        this.pedidoService = new PedidoService();
    }

    public void adicionarItem(Pedido pedido, Produto produto, Integer quantidade) {

        ItemPedido item = new ItemPedido(produto, quantidade);
        pedido.getItens().add(item);

        pedidoService.calcularTotal(pedido);
    }

    public void editarItem(Pedido pedido, int index, Produto produto, Integer quantidade) {

        if (index < 0 || index >= pedido.getItens().size()) {
            throw new IllegalArgumentException("Índice de item inválido.");
        }

        ItemPedido item = pedido.getItens().get(index);
        item.setProduto(produto);
        item.setQuantidade(quantidade);

        pedidoService.calcularTotal(pedido);
    }

    public void removerItem(Pedido pedido, int index) {

        if (index < 0 || index >= pedido.getItens().size()) {
            throw new IllegalArgumentException("Índice inválido.");
        }

        pedido.getItens().remove(index);
        pedidoService.calcularTotal(pedido);
    }
}
