package com.tecdes.gerenciador.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainView extends JFrame {

    private JButton btnCliente = new JButton("Cliente");
    private JButton btnProduto = new JButton("Produto");
    private JButton btnPedido = new JButton("Pedido");

    public MainView() {

        initComponents();
                
            }
        
            private void initComponents() {
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setTitle("Gerenciador de Pedidos");
                setLayout(null);
                setSize(600, 400);
                setLocationRelativeTo(null);

                JLabel lblBemVindo = new JLabel("Seja bem-vindo!");
                lblBemVindo.setBounds(250, 25, 200, 35);
                add(lblBemVindo);

                JLabel lblOpcao = new JLabel("Selecione uma das opções:");
                lblOpcao.setBounds(220, 45, 200, 35);
                add(lblOpcao);

                btnCliente.setBounds(225, 100, 150, 35);
                add(btnCliente);

                btnProduto.setBounds(225, 140, 150, 35);
                add(btnProduto);

                btnPedido.setBounds(225, 180, 150, 35);
                add(btnPedido);

                configurarEventos();
                                }
                
                private void configurarEventos() {
                    btnCliente.addActionListener(e -> { 
                        ClienteForm clienteForm = new ClienteForm();
                        clienteForm.setVisible(true);
                    });

                    btnProduto.addActionListener(e -> { 
                        ProdutoForm produtoForm = new ProdutoForm();
                        produtoForm.setVisible(true);
                    });

                    btnPedido.addActionListener(e -> { 
                        PedidoForm pedidoForm = new PedidoForm();
                        pedidoForm.setVisible(true);
                    });
                }
}
