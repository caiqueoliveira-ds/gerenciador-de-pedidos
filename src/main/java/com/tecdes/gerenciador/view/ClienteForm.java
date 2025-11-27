package com.tecdes.gerenciador.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.tecdes.gerenciador.repository.ClienteRepository;

public class ClienteForm extends JFrame{

    public ClienteForm(){
        // ClienteRepository clienteRepository = new ClienteRepository();
        initComponents();
        // carregarDados();
            }
        
            private void initComponents() {
                setTitle("Cadastro de Clientes");
                setLayout(null);
                setSize(900, 600);
                setLocationRelativeTo(null);

                JLabel lblListaCliente = new JLabel("Controle de Clientes");
                lblListaCliente.setBounds(370, 15, 300, 20);
                add(lblListaCliente);

                JButton btnAddCliente = new JButton("Cadastrar Cliente");
                btnAddCliente.setBounds(600, 10, 150, 30);
                add(btnAddCliente);
                };

    
}
