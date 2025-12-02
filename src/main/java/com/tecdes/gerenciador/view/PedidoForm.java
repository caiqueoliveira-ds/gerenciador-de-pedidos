package com.tecdes.gerenciador.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;

import java.awt.*;

public class PedidoForm extends JFrame {
    private JButton btnAddPedido;
    private JTable tabelaPedidos;
    private JScrollPane scrollPane;
    private JPanel panelTop;
    private JPanel panelCenter;
    private JPanel panelBottom;
    private JTextField campoBusca;
    private JButton btnBuscar;
    private JButton btnLimpar;
    private JButton btnEditar;
    private JButton btnExcluir;

    public PedidoForm() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Gerenciador de Pedidos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setResizable(true);

        // Usar BorderLayout como layout principal
        setLayout(new BorderLayout(10, 10));
        
        // Painel superior com título e botão
        panelTop = new JPanel(new BorderLayout(10, 0));
        panelTop.setBorder(new EmptyBorder(15, 20, 15, 20));
        panelTop.setBackground(new Color(248, 249, 250));

        // Título com estilo
        JLabel lblTitulo = new JLabel("Gerenciador de Pedidos");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(33, 37, 41));
        
        // Botão Novo Pedido com estilo local
        btnAddPedido = new JButton("+ Novo Pedido");
        estilizarBotao(btnAddPedido, new Color(41, 128, 185), Color.WHITE);

        panelTop.add(lblTitulo, BorderLayout.WEST);
        panelTop.add(btnAddPedido, BorderLayout.EAST);

        // Painel de busca
        JPanel panelBusca = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelBusca.setBorder(new EmptyBorder(10, 20, 10, 20));
        panelBusca.setBackground(Color.WHITE);

        JLabel lblBusca = new JLabel("Buscar:");
        lblBusca.setFont(new Font("Arial", Font.PLAIN, 14));
        
        campoBusca = new JTextField(20);
        campoBusca.setFont(new Font("Arial", Font.PLAIN, 14));
        campoBusca.setPreferredSize(new Dimension(250, 35));
        campoBusca.setBorder(new LineBorder(new Color(206, 212, 218), 1));
        
        // Botões de busca estilizados localmente
        btnBuscar = new JButton("Buscar");
        estilizarBotao(btnBuscar, new Color(41, 128, 185), Color.WHITE);
        
        btnLimpar = new JButton("Limpar");
        estilizarBotao(btnLimpar, new Color(41, 128, 185), Color.WHITE);

        panelBusca.add(lblBusca);
        panelBusca.add(campoBusca);
        panelBusca.add(btnBuscar);
        panelBusca.add(btnLimpar);

        // Tabela de pedidos
        String[] colunas = {"ID", "Cliente", "Data", "Valor Total", "Status", "Ações"};
        Object[][] dados = {};

        tabelaPedidos = new JTable(dados, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabela não editável
            }
        };
        
        tabelaPedidos.setFont(new Font("Arial", Font.PLAIN, 13));
        tabelaPedidos.setRowHeight(40);
        tabelaPedidos.setSelectionBackground(new Color(220, 237, 255));
        tabelaPedidos.setSelectionForeground(Color.BLACK);
        
        // Configurar cabeçalho da tabela
        JTableHeader header = tabelaPedidos.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 13));
        header.setBackground(new Color(233, 236, 239));
        header.setForeground(new Color(33, 37, 41));
        header.setReorderingAllowed(false);
        
        tabelaPedidos.setGridColor(new Color(222, 226, 230));
        
        // Configurar largura das colunas
        tabelaPedidos.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabelaPedidos.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabelaPedidos.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabelaPedidos.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabelaPedidos.getColumnModel().getColumn(4).setPreferredWidth(100);
        tabelaPedidos.getColumnModel().getColumn(5).setPreferredWidth(120);

        scrollPane = new JScrollPane(tabelaPedidos);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(222, 226, 230)),
            "Lista de Pedidos",
            0, 0,
            new Font("Arial", Font.BOLD, 14),
            new Color(73, 80, 87)
        ));

        // Painel central com busca e tabela
        panelCenter = new JPanel(new BorderLayout(0, 10));
        panelCenter.setBorder(new EmptyBorder(0, 20, 0, 20));
        panelCenter.add(panelBusca, BorderLayout.NORTH);
        panelCenter.add(scrollPane, BorderLayout.CENTER);

        // Painel inferior com botões de ação
        panelBottom = new JPanel();
        panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panelBottom.setBackground(new Color(248, 249, 250));
        panelBottom.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(222, 226, 230)));

        // Botões estilizados localmente
        btnEditar = new JButton("Editar Pedido");
        estilizarBotao(btnEditar, new Color(41, 128, 185), Color.WHITE);
        
        btnExcluir = new JButton("Excluir Pedido");
        estilizarBotao(btnExcluir, new Color(220, 53, 69), Color.WHITE);

        panelBottom.add(btnEditar);
        panelBottom.add(btnExcluir);

        // Adicionar painéis ao frame
        add(panelTop, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);

        // Adicionar painel de status
        adicionarPainelStatus();
    }

    private void estilizarBotao(JButton botao, Color corFundo, Color corTexto) {
        botao.setFont(new Font("Arial", Font.BOLD, 13));
        botao.setBackground(corFundo);
        botao.setForeground(corTexto);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(corFundo.darker(), 1),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Efeito hover apenas para este botão
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(corFundo.darker());
                botao.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(corFundo.darker().darker(), 1),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20)
                ));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(corFundo);
                botao.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(corFundo.darker(), 1),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20)
                ));
            }
        });
        
        // Efeito ao pressionar
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botao.setBackground(corFundo.darker().darker());
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                botao.setBackground(corFundo.darker());
            }
        });
    }

    private void adicionarPainelStatus() {
        JPanel panelStatus = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        panelStatus.setBorder(new EmptyBorder(10, 20, 10, 20));
        panelStatus.setBackground(Color.WHITE);

        // Cartões de status
        panelStatus.add(criarCartaoStatus("Total Pedidos", "0", new Color(0, 123, 255)));
        panelStatus.add(criarCartaoStatus("Pendentes", "0", new Color(255, 193, 7)));
        panelStatus.add(criarCartaoStatus("Concluídos", "0", new Color(40, 167, 69)));
        panelStatus.add(criarCartaoStatus("Cancelados", "0", new Color(220, 53, 69)));

        // Adicionar acima da tabela
        panelCenter.add(panelStatus, BorderLayout.NORTH);
    }

    private JPanel criarCartaoStatus(String titulo, String valor, Color cor) {
        JPanel cartao = new JPanel();
        cartao.setLayout(new BoxLayout(cartao, BoxLayout.Y_AXIS));
        cartao.setBackground(Color.WHITE);
        cartao.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(222, 226, 230), 1),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        cartao.setPreferredSize(new Dimension(150, 80));

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Arial", Font.BOLD, 24));
        lblValor.setForeground(cor);
        lblValor.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Arial", Font.PLAIN, 14));
        lblTitulo.setForeground(new Color(73, 80, 87));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        cartao.add(lblValor);
        cartao.add(Box.createRigidArea(new Dimension(0, 5)));
        cartao.add(lblTitulo);

        return cartao;
    }

    // Getters para os componentes (para uso no controller)
    public JButton getBtnAddPedido() {
        return btnAddPedido;
    }

    public JTable getTabelaPedidos() {
        return tabelaPedidos;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnLimpar() {
        return btnLimpar;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public JButton getBtnExcluir() {
        return btnExcluir;
    }

    public JTextField getCampoBusca() {
        return campoBusca;
    }

    // Método para obter o ID do pedido selecionado
    public Integer getPedidoSelecionadoId() {
        int linha = tabelaPedidos.getSelectedRow();
        if (linha != -1) {
            return (Integer) tabelaPedidos.getValueAt(linha, 0);
        }
        return null;
    }

    // Método para testar a interface
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Apenas para este frame, não afeta outros
                JFrame.setDefaultLookAndFeelDecorated(false);
                new PedidoForm().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}