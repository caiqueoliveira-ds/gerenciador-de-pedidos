package com.tecdes.gerenciador.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.DecimalFormat;

public class ProdutoForm extends JFrame {

    private JTable tabelaProdutos;
    private JButton btnAddProduto;
    private JButton btnEditarProduto;
    private JButton btnRemoverProduto;
    private JButton btnBuscar;
    private JButton btnAtivarDesativar;
    private JTextField txtBusca;
    private JComboBox<String> cmbCategoria;
    private JComboBox<String> cmbStatus;
    private JLabel lblTotalProdutos;
    private JLabel lblProdutosAtivos;

    // Cores para lanchonete
    private final Color COR_PRIMARIA = new Color(41, 128, 185);    // Laranja - cor de lanchonete
    private final Color COR_SECUNDARIA = new Color(243, 156, 18);  // Laranja claro
    private final Color COR_VERMELHO = new Color(231, 76, 60);     // Vermelho para carnes/combos
    private final Color COR_VERDE = new Color(46, 204, 113);       // Verde para saladas/vegetarianos
    private final Color COR_TEXTO = new Color(44, 62, 80);         // Cinza escuro
    private final Color COR_BORDA = new Color(236, 240, 241);      // Cinza claro

    public ProdutoForm() {
        initComponents();
        layoutComponents();
        aplicarEstilos();
        carregarDadosExemplo(); // Dados de exemplo para lanchonete
    }

    private void initComponents() {
        setTitle("Sistema de Lanchonete - Cardápio");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setResizable(true);

        // Componentes
        btnAddProduto = new JButton("Novo Item");
        btnEditarProduto = new JButton("Editar");
        btnRemoverProduto = new JButton("Remover");
        btnAtivarDesativar = new JButton("Ativar/Desativar");
        btnBuscar = new JButton("Buscar");
        txtBusca = new JTextField(15);
        
        // Filtros para lanchonete
        cmbCategoria = new JComboBox<>(new String[]{
            "Todos", "Lanches", "Bebidas", "Acompanhamentos", 
            "Sobremesas", "Promoções", "Vegetariano"
        });
        
        cmbStatus = new JComboBox<>(new String[]{
            "Todos", "Ativos", "Inativos", "Mais Vendidos", "Em Promoção"
        });
        
        // Labels de estatísticas
        lblTotalProdutos = new JLabel("Itens: 0");
        lblProdutosAtivos = new JLabel("Ativos: 0");

        // Configurar tabela
        tabelaProdutos = new JTable();
        tabelaProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));
        
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));

        // === CABEÇALHO ===
        JPanel panelCabecalho = criarPanelCabecalho();
        
        // === BARRA DE FERRAMENTAS ===
        JPanel panelFerramentas = criarPanelFerramentas();
        
        // === PAINEL DE ESTATÍSTICAS ===
        JPanel panelEstatisticas = criarPanelEstatisticas();
        
        // === TABELA ===
        JPanel panelTabela = criarPanelTabela();
        
        // === STATUS BAR ===
        JPanel panelStatus = criarPanelStatus();

        // Montar layout principal
        panelPrincipal.add(panelCabecalho, BorderLayout.NORTH);
        panelPrincipal.add(panelFerramentas, BorderLayout.CENTER);
        panelPrincipal.add(panelEstatisticas, BorderLayout.SOUTH);
        
        add(panelPrincipal, BorderLayout.NORTH);
        add(panelTabela, BorderLayout.CENTER);
        add(panelStatus, BorderLayout.SOUTH);
    }

    private JPanel criarPanelCabecalho() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(0, 0, 15, 0));

        // Título com ícone de lanchonete
        JLabel lblTitulo = new JLabel("Cardápio da Lanchonete");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(COR_TEXTO);

        // Botões de ação
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelBotoes.add(btnAddProduto);

        panel.add(lblTitulo, BorderLayout.WEST);
        panel.add(panelBotoes, BorderLayout.EAST);

        return panel;
    }

    private JPanel criarPanelFerramentas() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COR_BORDA),
            "Gerenciar Cardápio",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 12),
            COR_TEXTO
        ));

        panel.add(new JLabel("Buscar:"));
        panel.add(txtBusca);
        panel.add(btnBuscar);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Categoria:"));
        panel.add(cmbCategoria);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Status:"));
        panel.add(cmbStatus);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(btnEditarProduto);
        panel.add(btnAtivarDesativar);
        panel.add(btnRemoverProduto);

        return panel;
    }

    private JPanel criarPanelEstatisticas() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COR_BORDA),
            "Resumo do Cardápio",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 12),
            COR_TEXTO
        ));

        // Estilizar labels de estatísticas
        estiloLabelEstatistica(lblTotalProdutos, COR_TEXTO, "Total de itens no cardápio");
        estiloLabelEstatistica(lblProdutosAtivos, COR_VERDE, "Itens disponíveis para venda");

        panel.add(lblTotalProdutos);
        panel.add(lblProdutosAtivos);

        return panel;
    }

    private JPanel criarPanelTabela() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COR_BORDA),
            "Lista de Itens do Cardápio",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 12),
            COR_TEXTO
        ));

        // Personalizar a tabela
        tabelaProdutos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabelaProdutos.setRowHeight(35);
        tabelaProdutos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabelaProdutos.getTableHeader().setBackground(COR_PRIMARIA);
        tabelaProdutos.getTableHeader().setForeground(Color.WHITE);
        tabelaProdutos.setGridColor(COR_BORDA);
        tabelaProdutos.setShowGrid(true);

        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);
        scrollPane.setPreferredSize(new Dimension(900, 400));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel criarPanelStatus() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, COR_BORDA));
        panel.setBackground(new Color(41, 128, 185));
        
        JLabel lblInfo = new JLabel("Dica: Use Ativar/Desativar para itens temporariamente indisponíveis");
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblInfo.setForeground(Color.GRAY);
        
        JLabel lblContador = new JLabel("0 itens selecionados");
        lblContador.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblContador.setForeground(Color.GRAY);
        
        panel.add(lblInfo, BorderLayout.WEST);
        panel.add(lblContador, BorderLayout.EAST);
        
        return panel;
    }

    private void aplicarEstilos() {
        // Estilo para botão Novo Item
        btnAddProduto.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAddProduto.setBackground(COR_PRIMARIA);
        btnAddProduto.setForeground(Color.WHITE);
        btnAddProduto.setFocusPainted(false);
        btnAddProduto.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COR_PRIMARIA.darker()),
            BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));

        // Estilo para botões secundários
        estiloBotaoSecundario(btnEditarProduto);
        estiloBotaoSecundario(btnRemoverProduto);
        estiloBotaoSecundario(btnBuscar);
        
        // Estilo especial para Ativar/Desativar
        btnAtivarDesativar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAtivarDesativar.setBackground(new Color(52, 152, 219));
        btnAtivarDesativar.setForeground(Color.WHITE);
        btnAtivarDesativar.setFocusPainted(false);
        btnAtivarDesativar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(41, 128, 185)),
            BorderFactory.createEmptyBorder(6, 12, 6, 12)
        ));

        // Estilo para campos de busca e combobox
        txtBusca.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtBusca.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COR_BORDA),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));

        cmbCategoria.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cmbStatus.setFont(new Font("Segoe UI", Font.PLAIN, 13));
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
        
        // Efeito hover
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(new Color(245, 245, 245));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(Color.WHITE);
            }
        });
    }

    private void estiloLabelEstatistica(JLabel label, Color corTexto, String tooltip) {
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(corTexto);
        label.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COR_BORDA),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        label.setBackground(new Color(250, 250, 250));
        label.setOpaque(true);
        label.setToolTipText(tooltip);
    }

    private void carregarDadosExemplo() {
        // Dados de exemplo para lanchonete
        String[] colunas = {"Código", "Item", "Categoria", "Preço", "Estoque", "Status", "Vendas"};
        Object[][] dados = {};
        
        tabelaProdutos.setModel(new javax.swing.table.DefaultTableModel(dados, colunas));
        atualizarEstatisticas(0, 0);
    }

    public void atualizarEstatisticas(int totalItens, int itensAtivos) {
        lblTotalProdutos.setText("Itens: " + totalItens);
        lblProdutosAtivos.setText("Ativos: " + itensAtivos);
    }
}