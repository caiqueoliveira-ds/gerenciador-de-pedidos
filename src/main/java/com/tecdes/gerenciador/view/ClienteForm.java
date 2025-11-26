package com.tecdes.gerenciador.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ClienteForm extends JFrame{

    public ClienteForm(){
        initComponents();
            }
        
            private void initComponents() {
                setTitle("Cadastro de Clientes");
                setLayout(null);
                setSize(450, 300);
                setLocationRelativeTo(null);

                JLabel lblNome = new JLabel("Nome:");
                lblNome.setBounds(5, 10, 50, 20);
                add(lblNome);

                JTextField txtNome = new JTextField();
                txtNome.setBounds(55, 10, 200, 20);
                add(txtNome);
                };

    
}
