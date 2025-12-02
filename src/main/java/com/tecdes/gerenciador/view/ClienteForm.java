package com.tecdes.gerenciador.view;

import com.tecdes.gerenciador.controller.ClienteController;
import com.tecdes.gerenciador.model.entity.Cliente;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
        configurarEventos();
        carregarClientes();
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
        
        // Configurar modelo da tabela
        String[] colunas = {"ID", "Nome", "CPF", "Email"};
        Object[][] dados = {}; // Vazio inicialmente
        tabelaClientes.setModel(new javax.swing.table.DefaultTableModel(dados, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
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

        // Montar layout principal
        panelPrincipal.add(panelCabecalho, BorderLayout.NORTH);
        panelPrincipal.add(panelFerramentas, BorderLayout.CENTER);
        panelPrincipal.add(panelTabela, BorderLayout.SOUTH);
        
        add(panelPrincipal, BorderLayout.CENTER);
        add(panelStatus, BorderLayout.SOUTH);
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
    
    private void configurarEventos() {
        btnAddCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarCliente();
            }
        });
        
        btnEditarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarCliente();
            }
        });
        
        btnRemoverCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerCliente();
            }
        });
        
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCliente();
            }
        });
    }
    
    private void carregarClientes() {
        try {
            ClienteController controller = new ClienteController();
            List<Cliente> clientes = controller.listar();
            
            // Limpar tabela
            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tabelaClientes.getModel();
            model.setRowCount(0);
            
            // Adicionar clientes na tabela
            for (Cliente cliente : clientes) {
                model.addRow(new Object[]{
                    cliente.getId_cliente(),
                    cliente.getNm_cliente(),
                    formatarCPF(cliente.getNr_cpf()),
                    cliente.getDs_email()
                });
            }
            
            lblStatus.setText("Total de clientes: " + clientes.size());
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Erro ao carregar clientes: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cadastrarCliente() {
        // Painel para os campos
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JTextField txtNome = new JTextField();
        JTextField txtCpf = new JTextField();
        JTextField txtEmail = new JTextField();
        
        panel.add(new JLabel("Nome:"));
        panel.add(txtNome);
        panel.add(new JLabel("CPF:"));
        panel.add(txtCpf);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        
        int result = JOptionPane.showConfirmDialog(this, panel, 
            "Cadastrar Novo Cliente", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                ClienteController controller = new ClienteController();
                Cliente cliente = controller.cadastrarCliente(
                    txtNome.getText().trim(),
                    txtCpf.getText().trim(),
                    txtEmail.getText().trim()
                );
                
                JOptionPane.showMessageDialog(this,
                    "Cliente cadastrado com sucesso!\nID: " + cliente.getId_cliente(),
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
                
                carregarClientes();
                
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Erro de Validação",
                    JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                    "Erro ao cadastrar cliente: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void editarCliente() {
        int selectedRow = tabelaClientes.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Selecione um cliente para editar",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Obter dados do cliente selecionado
        Integer id = (Integer) tabelaClientes.getValueAt(selectedRow, 0);
        String nome = (String) tabelaClientes.getValueAt(selectedRow, 1);
        String cpf = (String) tabelaClientes.getValueAt(selectedRow, 2);
        String email = (String) tabelaClientes.getValueAt(selectedRow, 3);
        
        // Remover formatação do CPF
        cpf = cpf.replaceAll("\\D", "");
        
        // Painel para edição
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JTextField txtNome = new JTextField(nome);
        JTextField txtCpf = new JTextField(cpf);
        JTextField txtEmail = new JTextField(email);
        
        panel.add(new JLabel("Nome:"));
        panel.add(txtNome);
        panel.add(new JLabel("CPF:"));
        panel.add(txtCpf);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        
        int result = JOptionPane.showConfirmDialog(this, panel, 
            "Editar Cliente", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                ClienteController controller = new ClienteController();
                Cliente cliente = controller.editarCliente(
                    id,
                    txtNome.getText().trim(),
                    txtCpf.getText().trim(),
                    txtEmail.getText().trim()
                );
                
                JOptionPane.showMessageDialog(this,
                    "Cliente atualizado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
                
                carregarClientes();
                
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Erro de Validação",
                    JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                    "Erro ao atualizar cliente: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void removerCliente() {
        int selectedRow = tabelaClientes.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Selecione um cliente para remover",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Obter dados do cliente selecionado
        Integer id = (Integer) tabelaClientes.getValueAt(selectedRow, 0);
        String nome = (String) tabelaClientes.getValueAt(selectedRow, 1);
        
        int confirm = JOptionPane.showConfirmDialog(this,
            "Deseja realmente remover o cliente:\n" + nome + " (ID: " + id + ")?",
            "Confirmar Exclusão",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                ClienteController controller = new ClienteController();
                boolean sucesso = controller.removerCliente(id);
                
                if (sucesso) {
                    JOptionPane.showMessageDialog(this,
                        "Cliente removido com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    carregarClientes();
                }
                
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                    "Erro ao remover cliente: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void buscarCliente() {
        String busca = txtBusca.getText().trim();
        if (busca.isEmpty()) {
            carregarClientes();
            return;
        }
        
        try {
            ClienteController controller = new ClienteController();
            List<Cliente> todosClientes = controller.listar();
            
            // Filtrar localmente por nome (case insensitive)
            List<Cliente> filtrados = todosClientes.stream()
                .filter(c -> c.getNm_cliente().toLowerCase().contains(busca.toLowerCase()))
                .toList();
            
            // Limpar tabela
            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tabelaClientes.getModel();
            model.setRowCount(0);
            
            // Adicionar clientes filtrados
            for (Cliente cliente : filtrados) {
                model.addRow(new Object[]{
                    cliente.getId_cliente(),
                    cliente.getNm_cliente(),
                    formatarCPF(cliente.getNr_cpf()),
                    cliente.getDs_email()
                });
            }
            
            lblStatus.setText("Encontrados: " + filtrados.size() + " cliente(s)");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Erro na busca: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private String formatarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            return cpf;
        }
        return cpf.substring(0, 3) + "." + 
               cpf.substring(3, 6) + "." + 
               cpf.substring(6, 9) + "-" + 
               cpf.substring(9);
    }
    
    // Método para testar o form
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
            SwingUtilities.invokeLater(() -> {
                ClienteForm form = new ClienteForm();
                form.setVisible(true);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}