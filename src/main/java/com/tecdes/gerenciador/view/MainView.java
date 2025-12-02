package com.tecdes.gerenciador.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    private JButton btnCliente;
    private JButton btnProduto;
    private JButton btnPedido;
    private JButton btnSair;
    private JLabel lblUsuarioLogado;

    // Cores modernas
    private final Color COR_PRIMARIA = new Color(41, 128, 185);
    private final Color COR_SECUNDARIA = new Color(52, 152, 219);
    private final Color COR_TEXTO = new Color(44, 62, 80);
    private final Color COR_FUNDO = new Color(248, 249, 250);

    public MainView() {
        initComponents();
        aplicarEstilos();
        configurarEventos();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sistema de Gerenciamento - Principal");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        // Componentes
        btnCliente = new JButton("Gerenciar Clientes");
        btnProduto = new JButton("Gerenciar Produtos");
        btnPedido = new JButton("Gerenciar Pedidos");
        btnSair = new JButton("Sair do Sistema");
        
        lblUsuarioLogado = new JLabel("Usuário: Administrador");
        
        layoutComponents();
    }

    private void layoutComponents() {
        // Layout principal com BorderLayout
        setLayout(new BorderLayout(0, 0));
        
        // === CABEÇALHO ===
        JPanel panelCabecalho = criarPanelCabecalho();
        
        // === CONTEÚDO CENTRAL ===
        JPanel panelConteudo = criarPanelConteudo();
        
        // === RODAPÉ ===
        JPanel panelRodape = criarPanelRodape();

        // Adicionar ao frame
        add(panelCabecalho, BorderLayout.NORTH);
        add(panelConteudo, BorderLayout.CENTER);
        add(panelRodape, BorderLayout.SOUTH);
    }

    private JPanel criarPanelCabecalho() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COR_PRIMARIA);
        panel.setBorder(new EmptyBorder(15, 20, 15, 20));
        panel.setPreferredSize(new Dimension(getWidth(), 80));

        // Título principal
        JLabel lblTitulo = new JLabel("Sistema de Gerenciamento");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(Color.WHITE);

        // Informações do usuário
        JPanel panelUsuario = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelUsuario.setBackground(COR_PRIMARIA);
        lblUsuarioLogado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblUsuarioLogado.setForeground(Color.WHITE);
        panelUsuario.add(lblUsuarioLogado);

        panel.add(lblTitulo, BorderLayout.WEST);
        panel.add(panelUsuario, BorderLayout.EAST);

        return panel;
    }

    private JPanel criarPanelConteudo() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COR_FUNDO);
        panel.setBorder(new EmptyBorder(40, 50, 40, 50));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        // Mensagem de boas-vindas
        JLabel lblBemVindo = new JLabel("Bem-vindo ao Sistema!", JLabel.CENTER);
        lblBemVindo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblBemVindo.setForeground(COR_TEXTO);
        
        JLabel lblInstrucao = new JLabel("Selecione uma das opções abaixo:", JLabel.CENTER);
        lblInstrucao.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblInstrucao.setForeground(COR_TEXTO);

        // Painel de botões
        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new BoxLayout(panelBotoes, BoxLayout.Y_AXIS));
        panelBotoes.setBackground(COR_FUNDO);
        panelBotoes.setBorder(new EmptyBorder(20, 100, 20, 100));

        // Adicionar botões com espaçamento
        panelBotoes.add(btnCliente);
        panelBotoes.add(Box.createRigidArea(new Dimension(0, 15)));
        panelBotoes.add(btnProduto);
        panelBotoes.add(Box.createRigidArea(new Dimension(0, 15)));
        panelBotoes.add(btnPedido);

        // Configurar layout
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(lblBemVindo, gbc);
        
        gbc.insets = new Insets(0, 0, 40, 0);
        panel.add(lblInstrucao, gbc);
        
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(panelBotoes, gbc);

        return panel;
    }

    private JPanel criarPanelRodape() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, COR_TEXTO),
            new EmptyBorder(10, 20, 10, 20)
        ));

        // Informações da empresa
        JLabel lblInfo = new JLabel("TecDes Solutions © 2024 - Todos os direitos reservados");
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblInfo.setForeground(Color.GRAY);

        // Botão sair
        JPanel panelSair = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelSair.setBackground(Color.WHITE);
        panelSair.add(btnSair);

        panel.add(lblInfo, BorderLayout.WEST);
        panel.add(panelSair, BorderLayout.EAST);

        return panel;
    }

    private void aplicarEstilos() {
        // Estilo para botões de menu
        estiloBotaoMenu(btnCliente, COR_PRIMARIA);
        estiloBotaoMenu(btnProduto, COR_PRIMARIA);
        estiloBotaoMenu(btnPedido, COR_PRIMARIA);
        
        // Estilo para botão sair
        btnSair.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnSair.setBackground(new Color(231, 76, 60));         // Vermelho
        btnSair.setForeground(Color.WHITE);
        btnSair.setFocusPainted(false);
        btnSair.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(192, 57, 43)),
            BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
    }

    private void estiloBotaoMenu(JButton botao, Color cor) {
        botao.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(cor.darker()),
            BorderFactory.createEmptyBorder(12, 20, 12, 20)
        ));
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setMaximumSize(new Dimension(300, 50));

        // Efeito hover
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(cor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(cor);
            }
        });
    }

    private void configurarEventos() {
        btnCliente.addActionListener(e -> { 
            abrirTelaClientes();
        });

        btnProduto.addActionListener(e -> { 
            abrirTelaProdutos();
        });

        btnPedido.addActionListener(e -> { 
            abrirTelaPedidos();
        });

        btnSair.addActionListener(e -> {
            sairSistema();
        });
    }

    private void abrirTelaClientes() {
        SwingUtilities.invokeLater(() -> {
            ClienteForm clienteForm = new ClienteForm();
            clienteForm.setVisible(true);
        });
    }

    private void abrirTelaProdutos() {
        SwingUtilities.invokeLater(() -> {
            ProdutoForm produtoForm = new ProdutoForm();
            produtoForm.setVisible(true);
        });
    }

    private void abrirTelaPedidos() {
        SwingUtilities.invokeLater(() -> {
            PedidoForm pedidoForm = new PedidoForm();
            pedidoForm.setVisible(true);
        });
    }

    private void sairSistema() {
        int confirmacao = JOptionPane.showConfirmDialog(
            this,
            "Deseja realmente sair do sistema?",
            "Confirmação de Saída",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (confirmacao == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // Métodos para o controller
    public JButton getBtnCliente() { return btnCliente; }
    public JButton getBtnProduto() { return btnProduto; }
    public JButton getBtnPedido() { return btnPedido; }
    public JButton getBtnSair() { return btnSair; }
    public JLabel getLblUsuarioLogado() { return lblUsuarioLogado; }

    public void setUsuarioLogado(String usuario) {
        lblUsuarioLogado.setText("Usuário: " + usuario);
    }

    // Método main para teste
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            MainView mainView = new MainView();
            mainView.setUsuarioLogado("Administrador");
            mainView.setVisible(true);
        });
    }
}