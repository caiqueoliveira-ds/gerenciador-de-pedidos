package com.tecdes.gerenciador.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;
import com.tecdes.gerenciador.controller.PedidoController;
import com.tecdes.gerenciador.model.entity.Pedido;
import com.tecdes.gerenciador.util.RelatorioUtil;

public class PedidoForm extends JFrame {
    private JButton btnAddPedido;
    private JTable tabelaPedidos;
    private JButton btnRelatorio;
    private PedidoController pedidoController;
    private DefaultTableModel tableModel;

    public PedidoForm() {
        this.pedidoController = new PedidoController();
        initComponents();
        carregarDados();
    }

    private void initComponents() {
        setTitle("Pedidos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));
        
        // Painel superior
        JPanel panelTop = new JPanel(new BorderLayout(10, 0));
        panelTop.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JLabel lblTitulo = new JLabel("Pedidos");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        
        btnAddPedido = new JButton("Novo Pedido");
        btnRelatorio = new JButton("Gerar Relatório");
        
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelBotoes.add(btnRelatorio);
        panelBotoes.add(btnAddPedido);
        
        panelTop.add(lblTitulo, BorderLayout.WEST);
        panelTop.add(panelBotoes, BorderLayout.EAST);

        // Tabela
        String[] colunas = {"ID", "Código", "Descrição", "Cliente", "Valor", "Status"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tabelaPedidos = new JTable(tableModel);
        tabelaPedidos.setFont(new Font("Arial", Font.PLAIN, 12));
        tabelaPedidos.setRowHeight(25);
        
        JTableHeader header = tabelaPedidos.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 12));
        header.setBackground(new Color(220, 220, 220));
        
        JScrollPane scrollPane = new JScrollPane(tabelaPedidos);
        
        // Adicionar ao frame
        add(panelTop, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Eventos
        configurarEventos();
    }

    private void configurarEventos() {
        btnAddPedido.addActionListener(e -> criarPedido());
        btnRelatorio.addActionListener(e -> gerarRelatorioSimples());
    }
    
    private void carregarDados() {
        tableModel.setRowCount(0);
        List<Pedido> pedidos = pedidoController.listarPedidos();
        
        for (Pedido pedido : pedidos) {
            String statusDescricao = pedidoController.getDescricaoStatus(pedido.getSt_pedido());
            
            Object[] row = {
                pedido.getId_pedido(),
                pedido.getCd_pedido(),
                pedido.getDs_pedido(),
                pedido.getId_cliente() != null ? pedido.getId_cliente() : "-",
                String.format("R$ %.2f", pedido.getTotal() != null ? pedido.getTotal() : 0.0),
                statusDescricao
            };
            tableModel.addRow(row);
        }
    }
    
    private void criarPedido() {
        String codigoStr = JOptionPane.showInputDialog(this, "Código do pedido:");
        if (codigoStr == null || codigoStr.trim().isEmpty()) return;
        
        String descricao = JOptionPane.showInputDialog(this, "Descrição:");
        if (descricao == null) return;
        
        try {
            Integer codigo = Integer.parseInt(codigoStr);
            Pedido pedido = pedidoController.criarPedido(codigo, descricao, null);
            if (pedido != null) {
                carregarDados();
                JOptionPane.showMessageDialog(this, "Pedido criado!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void gerarRelatorioSimples() {
        List<Pedido> pedidos = pedidoController.listarPedidos();
        
        if (pedidos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum pedido para gerar relatório!");
            return;
        }
        
        // Gera relatório simples
        RelatorioUtil.gerarRelatorioPedidos(pedidos, "relatorio_pedidos.txt");
        
        JOptionPane.showMessageDialog(this, 
            "Relatório gerado!\nArquivo: relatorio_pedidos.txt", 
            "Relatório", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    // Getters simples
    public JButton getBtnAddPedido() { return btnAddPedido; }
    public JTable getTabelaPedidos() { return tabelaPedidos; }
    public JButton getBtnRelatorio() { return btnRelatorio; }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PedidoForm().setVisible(true);
        });
    }
}