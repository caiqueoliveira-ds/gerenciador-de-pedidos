package com.tecdes.gerenciador;

import java.util.List;

import com.tecdes.gerenciador.controller.MainController;
import com.tecdes.gerenciador.model.entity.Pedido;
import com.tecdes.gerenciador.repository.PedidoRepository;
import com.tecdes.gerenciador.util.RelatorioUtil;
import com.tecdes.gerenciador.view.MainView;

public class Main {
    public static void main(String[] args) {
        MainView main = new MainView();
        new MainController(main);
        main.setVisible(true);
    
        PedidoRepository pedidoRepository = new PedidoRepository();
        List<Pedido> pedidos = pedidoRepository.findAll();
        RelatorioUtil.gerarRelatorio(pedidos, "relatorio.txt");
    }
}