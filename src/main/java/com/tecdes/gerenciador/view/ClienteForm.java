package com.tecdes.gerenciador.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClienteForm extends JFrame {
    private JTable tabelaClientes;
    private JButton btnAddCliente;
    private JButton btnEditarCliente;
    private JButton btnRemoverCliente;
    private JButton btnBuscar;
    private JTextField txtBusca;
    private JLabel lblStatus;
    
    // Cores modernas
    private final Color COR_PRIMARIA = new Color(41, 128, 185);    // Azul profissional
    private final Color COR_SECUNDARIA = new Color(52, 152, 219);  // Azul mais claro
    private final Color COR_TEXTO = new Color(44, 62, 80);         // Cinza escuro
    private final Color COR_BORDA = new Color(236, 240, 241);      // Cinza claro

    public ClienteForm() {
        initComponents();
        layoutComponents();
        aplicarEstilos();
    }

    private void initComponents() {
        setTitle("Sistema de Cadastro - Clientes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(true);

        // Componentes
        btnAddCliente = new JButton("Cadastrar Cliente");
        btnEditarCliente = new JButton("Editar");
        btnRemoverCliente = new JButton("Remover");
        btnBuscar = new JButton("Buscar");
        txtBusca = new JTextField(15);
        
        // Label de status
        lblStatus = new JLabel("Total de clientes: 0");
        lblStatus.setHorizontalAlignment(SwingConstants.RIGHT);

        // Configurar tabela
        tabelaClientes = new JTable();
        tabelaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void layoutComponents() {
        // Usar BorderLayout para organização mais limpa
        setLayout(new BorderLayout(10, 10));
        
        // Adicionar margens ao conteúdo principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));

        // === CABEÇALHO ===
        JPanel panelCabecalho = criarPanelCabecalho();
        
        // === BARRA DE FERRAMENTAS ===
        JPanel panelFerramentas = criarPanelFerramentas();
        
        // === TABELA ===
        JPanel panelTabela = criarPanelTabela();
        
        // === STATUS BAR ===
        JPanel panelStatus = criarPanelStatus();

        // Montar layout principal CORRIGIDO
        panelPrincipal.add(panelCabecalho, BorderLayout.NORTH);
        panelPrincipal.add(panelFerramentas, BorderLayout.CENTER); // Mudado para CENTER
        panelPrincipal.add(panelTabela, BorderLayout.SOUTH); // Mudado para SOUTH
        
        add(panelPrincipal, BorderLayout.CENTER);
        add(panelStatus, BorderLayout.SOUTH); // Status bar separada
    }

    private JPanel criarPanelCabecalho() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(0, 0, 15, 0));

        // Título
        JLabel lblTitulo = new JLabel("Controle de Clientes");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(COR_TEXTO);

        // Botão de cadastro
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotoes.add(btnAddCliente);

        panel.add(lblTitulo, BorderLayout.WEST);
        panel.add(panelBotoes, BorderLayout.EAST);

        return panel;
    }

    private JPanel criarPanelFerramentas() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COR_BORDA),
            "Ferramentas",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 12),
            COR_TEXTO
        ));

        // Adicionar componentes na ordem correta
        panel.add(new JLabel("Buscar:"));
        panel.add(txtBusca);
        panel.add(btnBuscar);
        panel.add(Box.createHorizontalStrut(20)); // Espaçamento
        panel.add(btnEditarCliente);
        panel.add(btnRemoverCliente);

        return panel;
    }

    private JPanel criarPanelTabela() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COR_BORDA),
            "Lista de Clientes",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 12),
            COR_TEXTO
        ));

        // Personalizar a tabela
        tabelaClientes.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabelaClientes.setRowHeight(30);
        tabelaClientes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabelaClientes.getTableHeader().setBackground(COR_PRIMARIA);
        tabelaClientes.getTableHeader().setForeground(Color.WHITE);
        tabelaClientes.setGridColor(COR_BORDA);
        tabelaClientes.setShowGrid(true);

        JScrollPane scrollPane = new JScrollPane(tabelaClientes);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel criarPanelStatus() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, COR_BORDA));
        panel.setBackground(new Color(250, 250, 250));
        panel.setPreferredSize(new Dimension(getWidth(), 25));
        
        JLabel lblInfo = new JLabel("Sistema de Cadastro v1.0");
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblInfo.setForeground(Color.GRAY);
        
        panel.add(lblInfo, BorderLayout.WEST);
        panel.add(lblStatus, BorderLayout.EAST);
        
        return panel;
    }

    private void aplicarEstilos() {
        // Estilo para botões primários (Cadastrar)
        btnAddCliente.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAddCliente.setBackground(COR_PRIMARIA);
        btnAddCliente.setForeground(Color.WHITE);
        btnAddCliente.setFocusPainted(false);
        btnAddCliente.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COR_PRIMARIA.darker()),
            BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));

        // Estilo para botões secundários
        estiloBotaoSecundario(btnEditarCliente);
        estiloBotaoSecundario(btnRemoverCliente);
        estiloBotaoSecundario(btnBuscar);

        // Estilo para campo de busca
        txtBusca.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtBusca.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COR_BORDA),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));

        // Estilo para label de status
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblStatus.setForeground(COR_TEXTO);
    }

    private void estiloBotaoSecundario(JButton botao) {
        botao.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        botao.setBackground(Color.WHITE);
        botao.setForeground(COR_TEXTO);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COR_BORDA),
            BorderFactory.createEmptyBorder(6, 12, 6, 12)
        ));
        
        // Efeito hover básico
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(new Color(245, 245, 245));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(Color.WHITE);
            }
        });
    }
}