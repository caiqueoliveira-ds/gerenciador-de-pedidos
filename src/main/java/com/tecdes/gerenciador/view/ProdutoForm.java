package com.tecdes.gerenciador.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import com.tecdes.gerenciador.controller.ProdutoController;
import com.tecdes.gerenciador.model.entity.Produto;

public class ProdutoForm extends JFrame {
    
    private ProdutoController produtoController;
    private DefaultTableModel tableModel;
    private JTable tabelaProdutos;
    private JButton btnAddProduto, btnEditarProduto, btnRemoverProduto, btnBuscar, btnAtivarDesativar;
    private JTextField txtBusca;
    private JComboBox<String> cmbCategoria, cmbStatus;
    private JLabel lblTotalProdutos, lblProdutosAtivos;
    
    private final Color COR_PRIMARIA = new Color(41, 128, 185);
    private final Color COR_VERDE = new Color(46, 204, 113);
    private final Color COR_TEXTO = new Color(44, 62, 80);
    private final Color COR_BORDA = new Color(236, 240, 241);
    
    public ProdutoForm() {
        this.produtoController = new ProdutoController();
        initComponents();
        layoutComponents();
        aplicarEstilos();
        configurarEventos();
        carregarDados();
    }
    
    private void initComponents() {
        setTitle("Gerenciador de Produtos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        
        btnAddProduto = new JButton("Novo Produto");
        btnEditarProduto = new JButton("Editar");
        btnRemoverProduto = new JButton("Remover");
        btnAtivarDesativar = new JButton("Ativar/Desativar");
        btnBuscar = new JButton("Buscar");
        txtBusca = new JTextField(20);
        
        cmbCategoria = new JComboBox<>(new String[]{"Todos", "Lanches", "Bebidas", "Acompanhamentos"});
        cmbStatus = new JComboBox<>(new String[]{"Todos", "Ativos", "Inativos"});
        
        lblTotalProdutos = new JLabel("Total: 0");
        lblProdutosAtivos = new JLabel("Ativos: 0");
        
        String[] colunas = {"ID", "Código", "Nome", "Preço", "Status"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaProdutos = new JTable(tableModel);
        tabelaProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));
        
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        // Cabeçalho
        JPanel panelCabecalho = new JPanel(new BorderLayout());
        JLabel lblTitulo = new JLabel("Gerenciamento de Produtos");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(COR_TEXTO);
        
        JPanel panelBotoesCabecalho = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotoesCabecalho.add(btnAddProduto);
        
        panelCabecalho.add(lblTitulo, BorderLayout.WEST);
        panelCabecalho.add(panelBotoesCabecalho, BorderLayout.EAST);
        
        // Ferramentas
        JPanel panelFerramentas = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFerramentas.setBorder(BorderFactory.createTitledBorder("Filtros e Ações"));
        panelFerramentas.add(new JLabel("Buscar:"));
        panelFerramentas.add(txtBusca);
        panelFerramentas.add(btnBuscar);
        panelFerramentas.add(Box.createHorizontalStrut(20));
        panelFerramentas.add(new JLabel("Status:"));
        panelFerramentas.add(cmbStatus);
        panelFerramentas.add(Box.createHorizontalStrut(20));
        panelFerramentas.add(btnEditarProduto);
        panelFerramentas.add(btnAtivarDesativar);
        panelFerramentas.add(btnRemoverProduto);
        
        // Estatísticas
        JPanel panelEstatisticas = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        panelEstatisticas.setBorder(BorderFactory.createTitledBorder("Estatísticas"));
        panelEstatisticas.add(lblTotalProdutos);
        panelEstatisticas.add(lblProdutosAtivos);
        
        // Tabela
        JPanel panelTabela = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);
        panelTabela.add(scrollPane, BorderLayout.CENTER);
        
        // Montagem
        panelPrincipal.add(panelCabecalho, BorderLayout.NORTH);
        panelPrincipal.add(panelFerramentas, BorderLayout.CENTER);
        panelPrincipal.add(panelEstatisticas, BorderLayout.SOUTH);
        
        add(panelPrincipal, BorderLayout.NORTH);
        add(panelTabela, BorderLayout.CENTER);
    }
    
    private void aplicarEstilos() {
        btnAddProduto.setBackground(COR_PRIMARIA);
        btnAddProduto.setForeground(Color.WHITE);
        btnAddProduto.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        // Estilizar tabela
        tabelaProdutos.getTableHeader().setBackground(COR_PRIMARIA);
        tabelaProdutos.getTableHeader().setForeground(Color.WHITE);
        tabelaProdutos.setRowHeight(30);
        tabelaProdutos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        // Estatísticas
        lblTotalProdutos.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblProdutosAtivos.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblProdutosAtivos.setForeground(COR_VERDE);
    }
    
    private void configurarEventos() {
        btnAddProduto.addActionListener(e -> abrirFormularioCadastro());
        btnEditarProduto.addActionListener(e -> editarProduto());
        btnRemoverProduto.addActionListener(e -> removerProduto());
        btnAtivarDesativar.addActionListener(e -> alternarStatusProduto());
        btnBuscar.addActionListener(e -> buscarProdutos());
        cmbStatus.addActionListener(e -> filtrarPorStatus());
        
        tabelaProdutos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    editarProduto();
                }
            }
        });
        
        txtBusca.addActionListener(e -> buscarProdutos());
    }
    
    private void carregarDados() {
        tableModel.setRowCount(0);
        List<Produto> produtos = produtoController.listarProdutos();
        
        for (Produto p : produtos) {
            Object[] row = {
                p.getId_produto(),
                p.getCd_produto(),
                p.getNm_produto(),
                String.format("R$ %.2f", p.getVl_produto()),
                p.getSt_produto().equals("A") ? "Ativo" : "Inativo"
            };
            tableModel.addRow(row);
        }
        
        atualizarEstatisticas();
    }
    
    private void atualizarEstatisticas() {
        long total = produtoController.contarTotalProdutos();
        long ativos = produtoController.contarProdutosAtivos();
        
        lblTotalProdutos.setText("Total: " + total);
        lblProdutosAtivos.setText("Ativos: " + ativos);
    }
    
    private void abrirFormularioCadastro() {
        JDialog dialog = new JDialog(this, "Cadastrar Produto", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Código
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Código:*"), gbc);
        gbc.gridx = 1;
        JTextField txtCodigo = new JTextField(15);
        panel.add(txtCodigo, gbc);
        
        // Nome
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Nome:*"), gbc);
        gbc.gridx = 1;
        JTextField txtNome = new JTextField(20);
        panel.add(txtNome, gbc);
        
        // Preço
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Preço:*"), gbc);
        gbc.gridx = 1;
        JTextField txtPreco = new JTextField(10);
        panel.add(txtPreco, gbc);
        
        // Status
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Status:*"), gbc);
        gbc.gridx = 1;
        JComboBox<String> cmbStatus = new JComboBox<>(new String[]{"A - Ativo", "I - Inativo"});
        panel.add(cmbStatus, gbc);
        
        // Botões
        JPanel panelBotoes = new JPanel();
        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");
        
        btnSalvar.addActionListener(e -> {
            try {
                Integer codigo = Integer.parseInt(txtCodigo.getText());
                String nome = txtNome.getText();
                Double preco = Double.parseDouble(txtPreco.getText());
                String status = ((String) cmbStatus.getSelectedItem()).substring(0, 1);
                
                boolean sucesso = produtoController.cadastrarProduto(codigo, nome, preco, status);
                
                if (sucesso) {
                    JOptionPane.showMessageDialog(dialog, "Produto cadastrado com sucesso!");
                    carregarDados();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Erro ao cadastrar produto!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        btnCancelar.addActionListener(e -> dialog.dispose());
        
        panelBotoes.add(btnSalvar);
        panelBotoes.add(btnCancelar);
        
        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(panelBotoes, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
    
    private void editarProduto() {
        int selectedRow = tabelaProdutos.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto para editar!");
            return;
        }
        
        Integer produtoId = (Integer) tableModel.getValueAt(selectedRow, 0);
        Produto produto = produtoController.buscarProdutoPorId(produtoId);
        
        if (produto != null) {
            abrirFormularioEdicao(produto);
        }
    }
    
    private void abrirFormularioEdicao(Produto produto) {
        JDialog dialog = new JDialog(this, "Editar Produto", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // ID (somente leitura)
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        JTextField txtId = new JTextField(String.valueOf(produto.getId_produto()));
        txtId.setEditable(false);
        panel.add(txtId, gbc);
        
        // Código
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Código:*"), gbc);
        gbc.gridx = 1;
        JTextField txtCodigo = new JTextField(String.valueOf(produto.getCd_produto()));
        panel.add(txtCodigo, gbc);
        
        // Nome
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Nome:*"), gbc);
        gbc.gridx = 1;
        JTextField txtNome = new JTextField(produto.getNm_produto());
        panel.add(txtNome, gbc);
        
        // Preço
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Preço:*"), gbc);
        gbc.gridx = 1;
        JTextField txtPreco = new JTextField(String.valueOf(produto.getVl_produto()));
        panel.add(txtPreco, gbc);
        
        // Status
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Status:*"), gbc);
        gbc.gridx = 1;
        JComboBox<String> cmbStatus = new JComboBox<>(new String[]{"A - Ativo", "I - Inativo"});
        if ("A".equals(produto.getSt_produto())) {
            cmbStatus.setSelectedIndex(0);
        } else {
            cmbStatus.setSelectedIndex(1);
        }
        panel.add(cmbStatus, gbc);
        
        // Botões
        JPanel panelBotoes = new JPanel();
        JButton btnSalvar = new JButton("Salvar Alterações");
        JButton btnCancelar = new JButton("Cancelar");
        
        btnSalvar.addActionListener(e -> {
            try {
                Integer id = produto.getId_produto();
                Integer codigo = Integer.parseInt(txtCodigo.getText());
                String nome = txtNome.getText();
                Double preco = Double.parseDouble(txtPreco.getText());
                String status = ((String) cmbStatus.getSelectedItem()).substring(0, 1);
                
                boolean sucesso = produtoController.atualizarProduto(id, codigo, nome, status, preco);
                
                if (sucesso) {
                    JOptionPane.showMessageDialog(dialog, "Produto atualizado com sucesso!");
                    carregarDados();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Erro ao atualizar produto!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Preço e código devem ser números válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        btnCancelar.addActionListener(e -> dialog.dispose());
        
        panelBotoes.add(btnSalvar);
        panelBotoes.add(btnCancelar);
        
        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(panelBotoes, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
    
    private void removerProduto() {
        int selectedRow = tabelaProdutos.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto para remover!");
            return;
        }
        
        Integer produtoId = (Integer) tableModel.getValueAt(selectedRow, 0);
        String produtoNome = (String) tableModel.getValueAt(selectedRow, 2);
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Remover produto: " + produtoNome + "?", 
            "Confirmar", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            boolean sucesso = produtoController.removerProduto(produtoId);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Produto removido com sucesso!");
                carregarDados();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao remover produto!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void alternarStatusProduto() {
        int selectedRow = tabelaProdutos.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto!");
            return;
        }
        
        Integer produtoId = (Integer) tableModel.getValueAt(selectedRow, 0);
        boolean sucesso = produtoController.alternarStatusProduto(produtoId);
        
        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Status alterado com sucesso!");
            carregarDados();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao alterar status!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void buscarProdutos() {
        String termo = txtBusca.getText().trim();
        if (termo.isEmpty()) {
            carregarDados();
            return;
        }
        
        tableModel.setRowCount(0);
        List<Produto> resultados;
        
        try {
            Integer codigo = Integer.parseInt(termo);
            Produto produto = produtoController.buscarProdutoPorCodigo(codigo);
            if (produto != null) {
                adicionarProdutoNaTabela(produto);
            }
        } catch (NumberFormatException e) {
            resultados = produtoController.buscarProdutosPorNome(termo);
            for (Produto p : resultados) {
                adicionarProdutoNaTabela(p);
            }
        }
        
        atualizarEstatisticas();
    }
    
    private void filtrarPorStatus() {
        String status = (String) cmbStatus.getSelectedItem();
        
        tableModel.setRowCount(0);
        List<Produto> produtos;
        
        if ("Todos".equals(status)) {
            produtos = produtoController.listarProdutos();
        } else if ("Ativos".equals(status)) {
            produtos = produtoController.listarProdutosAtivos();
        } else {
            produtos = produtoController.listarProdutosInativos();
        }
        
        for (Produto p : produtos) {
            Object[] row = {
                p.getId_produto(),
                p.getCd_produto(),
                p.getNm_produto(),
                String.format("R$ %.2f", p.getVl_produto()),
                p.getSt_produto().equals("A") ? "Ativo" : "Inativo"
            };
            tableModel.addRow(row);
        }
        
        atualizarEstatisticas();
    }
    
    private void adicionarProdutoNaTabela(Produto produto) {
        Object[] row = {
            produto.getId_produto(),
            produto.getCd_produto(),
            produto.getNm_produto(),
            String.format("R$ %.2f", produto.getVl_produto()),
            produto.getSt_produto().equals("A") ? "Ativo" : "Inativo"
        };
        tableModel.addRow(row);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            ProdutoForm form = new ProdutoForm();
            form.setVisible(true);
        });
    }
}