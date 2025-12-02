package com.tecdes.gerenciador.view;

import com.tecdes.gerenciador.model.entity.Cliente;
import com.tecdes.gerenciador.repository.ClienteRepository;
import com.tecdes.gerenciador.service.ClienteService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClienteForm extends JFrame {

    private JTable tabelaClientes;
    private JButton btnAddCliente;
    private JButton btnEditarCliente;
    private JButton btnRemoverCliente;
    private JButton btnBuscar;
    private JTextField txtBusca;
    private JLabel lblStatus;

    private final ClienteService clienteService =
            new ClienteService(new ClienteRepository());

    public ClienteForm() {
        initComponents();
        layoutComponents();
        aplicarEstilos();
        carregarClientes();
    }

    private void initComponents() {
        setTitle("Sistema de Cadastro - Clientes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        btnAddCliente = new JButton("Cadastrar Cliente");
        btnEditarCliente = new JButton("Editar");
        btnRemoverCliente = new JButton("Remover");
        btnBuscar = new JButton("Buscar");

        txtBusca = new JTextField(15);
        lblStatus = new JLabel("Total de clientes: 0");

        tabelaClientes = new JTable();
        tabelaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // EVENTO DO BOTÃO CADASTRAR
        btnAddCliente.addActionListener(e -> abrirPopupCadastro());
    }

    private void layoutComponents() {

        setLayout(new BorderLayout(10, 10));
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Cabeçalho
        JPanel panelCab = new JPanel(new BorderLayout());
        JLabel titulo = new JLabel("Controle de Clientes");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        panelCab.add(titulo, BorderLayout.WEST);
        panelCab.add(btnAddCliente, BorderLayout.EAST);

        // Ferramentas
        JPanel ferramentas = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ferramentas.setBorder(BorderFactory.createTitledBorder("Ferramentas"));
        ferramentas.add(new JLabel("Buscar:"));
        ferramentas.add(txtBusca);
        ferramentas.add(btnBuscar);
        ferramentas.add(btnEditarCliente);
        ferramentas.add(btnRemoverCliente);

        // Tabela
        JPanel painelTabela = new JPanel(new BorderLayout());
        painelTabela.setBorder(BorderFactory.createTitledBorder("Lista de Clientes"));
        JScrollPane scroll = new JScrollPane(tabelaClientes);
        painelTabela.add(scroll, BorderLayout.CENTER);

        // Status
        JPanel panelStatus = new JPanel(new BorderLayout());
        panelStatus.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
        panelStatus.add(lblStatus, BorderLayout.EAST);

        // Montagem
        panelPrincipal.add(panelCab, BorderLayout.NORTH);
        panelPrincipal.add(ferramentas, BorderLayout.CENTER);
        panelPrincipal.add(painelTabela, BorderLayout.SOUTH);

        add(panelPrincipal, BorderLayout.CENTER);
        add(panelStatus, BorderLayout.SOUTH);
    }

    private void aplicarEstilos() {
        btnAddCliente.setBackground(new Color(41, 128, 185));
        btnAddCliente.setForeground(Color.WHITE);
        btnAddCliente.setFont(new Font("Segoe UI", Font.BOLD, 14));
    }

    private void abrirPopupCadastro() {
        JTextField txtNome = new JTextField();
        JTextField txtCpf = new JTextField();
        JTextField txtEmail = new JTextField();

        Object[] campos = {
                "Nome:", txtNome,
                "CPF (somente números):", txtCpf,
                "Email:", txtEmail
        };

        int opcao = JOptionPane.showConfirmDialog(
                this,
                campos,
                "Cadastrar Novo Cliente",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (opcao == JOptionPane.OK_OPTION) {
            try {
                Cliente novo = clienteService.cadastrarCliente(
                        txtNome.getText(),
                        txtCpf.getText(),
                        txtEmail.getText()
                );

                JOptionPane.showMessageDialog(this,
                        "Cliente cadastrado com sucesso!\nID: " + novo.getId_cliente());

                carregarClientes();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Erro: " + ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void carregarClientes() {
        List<Cliente> lista = clienteService.buscarTodos();

        String[] colunas = {"ID", "Nome", "CPF", "Email"};

        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

        for (Cliente c : lista) {
            modelo.addRow(new Object[]{
                    c.getId_cliente(),
                    c.getNm_cliente(),
                    c.getNr_cpf(),
                    c.getDs_email()
            });
        }

        tabelaClientes.setModel(modelo);
        lblStatus.setText("Total de clientes: " + lista.size());
    }
}
