package com.tecdes.gerenciador.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ProdutoForm extends JFrame{

     public ProdutoForm(){
        // ClienteRepository clienteRepository = new ClienteRepository();
        initComponents();
        // carregarDados();
            }
        
            private void initComponents() {
                setTitle("Gerenciador de Produtos");
                setLayout(null);
                setSize(900, 600);
                setLocationRelativeTo(null);

                JLabel lblListaPedido = new JLabel("Lista de Produtos");
                lblListaPedido.setBounds(370, 15, 300, 20);
                add(lblListaPedido);

                JButton btnAddPedido = new JButton("Novo Produto");
                btnAddPedido.setBounds(600, 10, 150, 30);
                add(btnAddPedido);

                JButton btnAddCombo = new JButton("Novo Combo");
                btnAddCombo.setBounds(600, 50, 150, 30);
                add(btnAddCombo);
                };
}
