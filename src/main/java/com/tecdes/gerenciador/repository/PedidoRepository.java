package com.tecdes.gerenciador.repository;

import com.tecdes.gerenciador.config.ConnectionFactory;
import com.tecdes.gerenciador.model.entity.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PedidoRepository implements IPedidoRepository {
    
    @Override
    public Pedido save(Pedido pedido) {
        String sql;
        boolean isUpdate = pedido.getId_pedido() != null && existsById(pedido.getId_pedido());
        
        if (isUpdate) {
            sql = "UPDATE T_GRP_PEDIDO SET cd_pedido = ?, ds_pedido = ?, st_pedido = ?, id_cliente = ?, total = ? WHERE id_pedido = ?";
        } else {
            sql = "INSERT INTO T_GRP_PEDIDO (cd_pedido, ds_pedido, st_pedido, id_cliente, total, dt_pedido) VALUES (?, ?, ?, ?, ?, ?)";
        }
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            
            if (isUpdate) {
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, pedido.getCd_pedido());
                stmt.setString(2, pedido.getDs_pedido());
                stmt.setString(3, pedido.getSt_pedido());
                stmt.setObject(4, pedido.getId_cliente());
                stmt.setDouble(5, pedido.getTotal() != null ? pedido.getTotal() : 0.0);
                stmt.setInt(6, pedido.getId_pedido());
                stmt.executeUpdate();
                return pedido;
            } else {
                stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, pedido.getCd_pedido());
                stmt.setString(2, pedido.getDs_pedido());
                stmt.setString(3, pedido.getSt_pedido() != null ? pedido.getSt_pedido() : "P");
                stmt.setObject(4, pedido.getId_cliente());
                stmt.setDouble(5, pedido.getTotal() != null ? pedido.getTotal() : 0.0);
                stmt.setDate(6, new java.sql.Date(new Date().getTime()));
                stmt.executeUpdate();
                
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    pedido.setId_pedido(rs.getInt(1));
                }
                return pedido;
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new IllegalArgumentException("Código de pedido já existe: " + pedido.getCd_pedido());
            }
            throw new RuntimeException("Erro ao salvar pedido: " + e.getMessage(), e);
        } finally {
            closeResources(rs, stmt);
        }
    }
    
    @Override
    public Pedido findById(Integer id_pedido) {
        String sql = "SELECT p.*, c.nm_cliente FROM T_GRP_PEDIDO p " +
                    "LEFT JOIN T_GRP_CLIENTE c ON p.id_cliente = c.id_cliente " +
                    "WHERE p.id_pedido = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_pedido);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToPedido(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pedido por ID: " + e.getMessage(), e);
        } finally {
            closeResources(rs, stmt);
        }
    }
    
    @Override
    public Pedido findByCodigo(Integer cd_pedido) {
        String sql = "SELECT p.*, c.nm_cliente FROM T_GRP_PEDIDO p " +
                    "LEFT JOIN T_GRP_CLIENTE c ON p.id_cliente = c.id_cliente " +
                    "WHERE p.cd_pedido = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cd_pedido);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToPedido(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pedido por código: " + e.getMessage(), e);
        } finally {
            closeResources(rs, stmt);
        }
    }
    
    @Override
    public List<Pedido> findAll() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT p.*, c.nm_cliente FROM T_GRP_PEDIDO p " +
                    "LEFT JOIN T_GRP_CLIENTE c ON p.id_cliente = c.id_cliente " +
                    "ORDER BY p.id_pedido DESC";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                pedidos.add(mapResultSetToPedido(rs));
            }
            return pedidos;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar pedidos: " + e.getMessage(), e);
        } finally {
            closeResources(rs, stmt);
        }
    }
    
    @Override
    public List<Pedido> findByClienteId(Integer clienteId) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT p.*, c.nm_cliente FROM T_GRP_PEDIDO p " +
                    "LEFT JOIN T_GRP_CLIENTE c ON p.id_cliente = c.id_cliente " +
                    "WHERE p.id_cliente = ? ORDER BY p.id_pedido DESC";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, clienteId);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                pedidos.add(mapResultSetToPedido(rs));
            }
            return pedidos;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pedidos por cliente: " + e.getMessage(), e);
        } finally {
            closeResources(rs, stmt);
        }
    }
    
    @Override
    public List<Pedido> findByStatus(String status) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT p.*, c.nm_cliente FROM T_GRP_PEDIDO p " +
                    "LEFT JOIN T_GRP_CLIENTE c ON p.id_cliente = c.id_cliente " +
                    "WHERE p.st_pedido = ? ORDER BY p.id_pedido DESC";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                pedidos.add(mapResultSetToPedido(rs));
            }
            return pedidos;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pedidos por status: " + e.getMessage(), e);
        } finally {
            closeResources(rs, stmt);
        }
    }
    
    @Override
    public Pedido update(Pedido pedido) {
        return save(pedido);
    }
    
    @Override
    public boolean delete(Integer id_pedido) {
        // Primeiro deletar itens do pedido
        String sqlItens = "DELETE FROM T_GRP_ITEM_PEDIDO WHERE id_pedido = ?";
        String sqlPedido = "DELETE FROM T_GRP_PEDIDO WHERE id_pedido = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            
            // Deletar itens primeiro
            stmt = conn.prepareStatement(sqlItens);
            stmt.setInt(1, id_pedido);
            stmt.executeUpdate();
            stmt.close();
            
            // Deletar pedido
            stmt = conn.prepareStatement(sqlPedido);
            stmt.setInt(1, id_pedido);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir pedido: " + e.getMessage(), e);
        } finally {
            closeResources(null, stmt);
        }
    }
    
    @Override
    public boolean existsById(Integer id_pedido) {
        String sql = "SELECT 1 FROM T_GRP_PEDIDO WHERE id_pedido = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_pedido);
            rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existência do pedido: " + e.getMessage(), e);
        } finally {
            closeResources(rs, stmt);
        }
    }
    
    @Override
    public boolean existsByCodigo(Integer cd_pedido) {
        String sql = "SELECT 1 FROM T_GRP_PEDIDO WHERE cd_pedido = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cd_pedido);
            rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existência do código: " + e.getMessage(), e);
        } finally {
            closeResources(rs, stmt);
        }
    }
    
    private Pedido mapResultSetToPedido(ResultSet rs) throws SQLException {
        Pedido pedido = new Pedido();
        pedido.setId_pedido(rs.getInt("id_pedido"));
        pedido.setCd_pedido(rs.getInt("cd_pedido"));
        pedido.setDs_pedido(rs.getString("ds_pedido"));
        pedido.setSt_pedido(rs.getString("st_pedido"));
        pedido.setTotal(rs.getDouble("total"));
        pedido.setId_cliente(rs.getInt("id_cliente"));
        pedido.setDt_pedido(rs.getDate("dt_pedido"));
        return pedido;
    }
    
    private void closeResources(ResultSet rs, Statement stmt) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            System.err.println("Erro ao fechar recursos: " + e.getMessage());
        }
    }
    
    // Método adicional para contar pedidos por status
    public int countByStatus(String status) {
        String sql = "SELECT COUNT(*) FROM T_GRP_PEDIDO WHERE st_pedido = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao contar pedidos: " + e.getMessage(), e);
        } finally {
            closeResources(rs, stmt);
        }
    }
}