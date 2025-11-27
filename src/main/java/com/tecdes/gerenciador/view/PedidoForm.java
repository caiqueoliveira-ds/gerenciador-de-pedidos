package com.tecdes.gerenciador.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PedidoForm extends JFrame{

    public PedidoForm(){
        // ClienteRepository clienteRepository = new ClienteRepository();
        initComponents();
        // carregarDados();
            }
        
            private void initComponents() {
                setTitle("Gerenciador de pedidos");
                setLayout(null);
                setSize(900, 600);
                setLocationRelativeTo(null);

                JLabel lblListaPedido = new JLabel("Lista de Pedidos");
                lblListaPedido.setBounds(370, 15, 300, 20);
                add(lblListaPedido);

                JButton btnAddPedido = new JButton("Novo Pedido");
                btnAddPedido.setBounds(600, 10, 150, 30);
                add(btnAddPedido);
                };
}
