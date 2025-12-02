package com.tecdes.gerenciador.repository;

import com.tecdes.gerenciador.config.ConnectionFactory;
import com.tecdes.gerenciador.model.entity.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository implements IClienteRepository {
    
    @Override
    public Cliente save(Cliente cliente) {
        String sql;
        boolean isUpdate = cliente.getId_cliente() != null && existsById(cliente.getId_cliente());
        
        if (isUpdate) {
            sql = "UPDATE T_GRP_CLIENTE SET nm_cliente = ?, nr_cpf = ?, ds_email = ? WHERE id_cliente = ?";
        } else {
            sql = "INSERT INTO T_GRP_CLIENTE (nm_cliente, nr_cpf, ds_email) VALUES (?, ?, ?)";
        }
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            
            if (isUpdate) {
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, cliente.getNm_cliente());
                stmt.setString(2, cliente.getNr_cpf());
                stmt.setString(3, cliente.getDs_email());
                stmt.setInt(4, cliente.getId_cliente());
                stmt.executeUpdate();
                return cliente;
            } else {
                stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, cliente.getNm_cliente());
                stmt.setString(2, cliente.getNr_cpf());
                stmt.setString(3, cliente.getDs_email());
                stmt.executeUpdate();
                
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    cliente.setId_cliente(rs.getInt(1));
                }
                return cliente;
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // Duplicate entry
                if (e.getMessage().contains("nr_cpf")) {
                    throw new IllegalArgumentException("CPF já cadastrado: " + cliente.getNr_cpf());
                } else if (e.getMessage().contains("ds_email")) {
                    throw new IllegalArgumentException("Email já cadastrado: " + cliente.getDs_email());
                }
            }
            throw new RuntimeException("Erro ao salvar cliente no banco: " + e.getMessage(), e);
        } finally {
            closeResources(rs, stmt); // NÃO fecha a conexão!
        }
    }

    @Override
    public Cliente findById(Integer id_cliente) {
        String sql = "SELECT * FROM T_GRP_CLIENTE WHERE id_cliente = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_cliente);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToCliente(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente por ID: " + e.getMessage(), e);
        } finally {
            closeResources(rs, stmt); // NÃO fecha a conexão!
        }
    }

    @Override
    public Cliente findByCpf(String cpf) {
        String sql = "SELECT * FROM T_GRP_CLIENTE WHERE nr_cpf = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToCliente(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente por CPF: " + e.getMessage(), e);
        } finally {
            closeResources(rs, stmt); // NÃO fecha a conexão!
        }
    }

    @Override
    public List<Cliente> findAll() {
        String sql = "SELECT * FROM T_GRP_CLIENTE ORDER BY nm_cliente";
        List<Cliente> clientes = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                clientes.add(mapResultSetToCliente(rs));
            }
            return clientes;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes: " + e.getMessage(), e);
        } finally {
            closeResources(rs, stmt); // NÃO fecha a conexão!
        }
    }

    @Override
    public Cliente update(Cliente cliente) {
        return save(cliente); // Usa o mesmo método save
    }

    @Override
    public boolean delete(Integer id_cliente) {
        String sql = "DELETE FROM T_GRP_CLIENTE WHERE id_cliente = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_cliente);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            if (e.getErrorCode() == 1451) { // Foreign key constraint
                throw new IllegalArgumentException("Não é possível excluir o cliente pois existem pedidos associados.");
            }
            throw new RuntimeException("Erro ao excluir cliente: " + e.getMessage(), e);
        } finally {
            closeResources(null, stmt); // NÃO fecha a conexão!
        }
    }

    @Override
    public boolean existsById(Integer id_cliente) {
        String sql = "SELECT 1 FROM T_GRP_CLIENTE WHERE id_cliente = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_cliente);
            rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existência do cliente: " + e.getMessage(), e);
        } finally {
            closeResources(rs, stmt); // NÃO fecha a conexão!
        }
    }

    @Override
    public boolean existsByCpf(String cpf) {
        String sql = "SELECT 1 FROM T_GRP_CLIENTE WHERE nr_cpf = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existência do CPF: " + e.getMessage(), e);
        } finally {
            closeResources(rs, stmt); // NÃO fecha a conexão!
        }
    }
    
    // Método auxiliar para mapear ResultSet para Cliente
    private Cliente mapResultSetToCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId_cliente(rs.getInt("id_cliente"));
        cliente.setNm_cliente(rs.getString("nm_cliente"));
        cliente.setNr_cpf(rs.getString("nr_cpf"));
        cliente.setDs_email(rs.getString("ds_email"));
        return cliente;
    }
    
    // Método auxiliar para fechar recursos (NÃO fecha a conexão!)
    private void closeResources(ResultSet rs, Statement stmt) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            // NÃO fecha a conexão aqui!
        } catch (SQLException e) {
            System.err.println("Erro ao fechar recursos: " + e.getMessage());
        }
    }
    
    // Método adicional para busca por nome
    public List<Cliente> findByNomeContaining(String nome) {
        String sql = "SELECT * FROM T_GRP_CLIENTE WHERE nm_cliente LIKE ? ORDER BY nm_cliente";
        List<Cliente> clientes = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                clientes.add(mapResultSetToCliente(rs));
            }
            return clientes;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar clientes por nome: " + e.getMessage(), e);
        } finally {
            closeResources(rs, stmt); // NÃO fecha a conexão!
        }
    }
}