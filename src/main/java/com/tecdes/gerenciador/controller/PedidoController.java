package com.tecdes.gerenciador.controller;

import java.util.ArrayList;
import java.util.List;

import com.tecdes.gerenciador.model.entity.Cliente;
import com.tecdes.gerenciador.model.entity.ItemPedido;
import com.tecdes.gerenciador.model.entity.Pedido;
import com.tecdes.gerenciador.model.StatusPedido;
import com.tecdes.gerenciador.service.PedidoService;

public class PedidoController {

    private final PedidoService pedidoService;
    private final List<Pedido> bancoPedidos = new ArrayList<>();

    public PedidoController() {
        this.pedidoService = new PedidoService();
    }

    public List<Pedido> listar() {
        return bancoPedidos;
    }

    public Pedido criarPedido(Integer cd, String descricao, Cliente cliente) {

        Pedido p = new Pedido();
        p.setCd_pedido(cd);
        p.setDs_pedido(descricao);
        p.setSt_pedido(StatusPedido.PENDENTE.name());
        p.setItens(new ArrayList<>());
        p.setTotal(0.0);

        bancoPedidos.add(p);

        return p;
    }

    public void adicionarItem(Pedido pedido, ItemPedido item) {
        pedido.getItens().add(item);
        pedidoService.calcularTotal(pedido);
    }

    public void mudarStatus(Pedido pedido, StatusPedido novoStatus) {
        pedido.setSt_pedido(novoStatus.name());
    }

    public void recalcular(Pedido pedido) {
        pedidoService.atualizarTotaisDosItens(pedido);
        pedidoService.calcularTotal(pedido);
    }

    public Pedido buscarPorCodigo(Integer codigo) {
        return bancoPedidos.stream()
                .filter(p -> p.getCd_pedido().equals(codigo))
                .findFirst()
                .orElse(null);
    }
}
