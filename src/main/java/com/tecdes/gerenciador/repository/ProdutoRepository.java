package com.tecdes.gerenciador.repository;

import com.tecdes.gerenciador.config.ConnectionFactory;
import com.tecdes.gerenciador.model.entity.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository implements IProdutoRepository {
    
    @Override
    public Produto save(Produto produto) {
        String sql = "INSERT INTO T_GRP_PRODUTO (cd_produto, nm_produto, st_produto, vl_produto, dt_validade, dt_fabricacao) VALUES (?, ?, ?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            stmt.setInt(1, produto.getCd_produto());
            stmt.setString(2, produto.getNm_produto());
            stmt.setString(3, produto.getSt_produto());
            stmt.setDouble(4, produto.getVl_produto());
            
            if (produto.getDt_validade() != null) {
                stmt.setDate(5, new java.sql.Date(produto.getDt_validade().getTime()));
            } else {
                stmt.setDate(5, null);
            }
            
            if (produto.getDt_fabricacao() != null) {
                stmt.setDate(6, new java.sql.Date(produto.getDt_fabricacao().getTime()));
            } else {
                stmt.setDate(6, null);
            }
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    produto.setId_produto(rs.getInt(1));
                }
            }
            
            return produto;
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar produto: " + e.getMessage(), e);
        } finally {
            closeResources(rs, stmt);
        }
    }
    
    @Override
    public Produto findById(Integer id) {
        String sql = "SELECT * FROM T_GRP_PRODUTO WHERE id_produto = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToProduto(rs);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto por ID: " + e.getMessage(), e);
        } finally {
            closeResources(rs, stmt);
        }
        
        return null;
    }
    
    @Override
    public Produto findByCodigo(Integer codigo) {
        String sql = "SELECT * FROM T_GRP_PRODUTO WHERE cd_produto = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToProduto(rs);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto por código: " + e.getMessage(), e);
        } finally {
            closeResources(rs, stmt);
        }
        
        return null;
    }
    
    @Override
    public List<Produto> findAll() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM T_GRP_PRODUTO ORDER BY nm_produto";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                produtos.add(mapResultSetToProduto(rs));
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos: " + e.getMessage(), e);
        } finally {
            closeResources(rs, stmt);
        }
        
        return produtos;
    }
    
    @Override
    public List<Produto> findByStatus(String status) {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM T_GRP_PRODUTO WHERE st_produto = ? ORDER BY nm_produto";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                produtos.add(mapResultSetToProduto(rs));
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produtos por status: " + e.getMessage(), e);
        } finally {
            closeResources(rs, stmt);
        }
        
        return produtos;
    }
    
    @Override
    public List<Produto> findByNomeContaining(String nome) {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM T_GRP_PRODUTO WHERE nm_produto LIKE ? ORDER BY nm_produto";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                produtos.add(mapResultSetToProduto(rs));
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produtos por nome: " + e.getMessage(), e);
        } finally {
            closeResources(rs, stmt);
        }
        
        return produtos;
    }
    
    @Override
    public Produto update(Produto produto) {
        String sql = "UPDATE T_GRP_PRODUTO SET cd_produto = ?, nm_produto = ?, st_produto = ?, vl_produto = ?, dt_validade = ?, dt_fabricacao = ? WHERE id_produto = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, produto.getCd_produto());
            stmt.setString(2, produto.getNm_produto());
            stmt.setString(3, produto.getSt_produto());
            stmt.setDouble(4, produto.getVl_produto());
            
            if (produto.getDt_validade() != null) {
                stmt.setDate(5, new java.sql.Date(produto.getDt_validade().getTime()));
            } else {
                stmt.setDate(5, null);
            }
            
            if (produto.getDt_fabricacao() != null) {
                stmt.setDate(6, new java.sql.Date(produto.getDt_fabricacao().getTime()));
            } else {
                stmt.setDate(6, null);
            }
            
            stmt.setInt(7, produto.getId_produto());
            
            stmt.executeUpdate();
            return produto;
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto: " + e.getMessage(), e);
        } finally {
            closeResources(null, stmt);
        }
    }
    
    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM T_GRP_PRODUTO WHERE id_produto = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar produto: " + e.getMessage(), e);
        } finally {
            closeResources(null, stmt);
        }
    }
    
    @Override
    public boolean existsById(Integer id) {
        String sql = "SELECT 1 FROM T_GRP_PRODUTO WHERE id_produto = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            return rs.next();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existência do produto: " + e.getMessage(), e);
        } finally {
            closeResources(rs, stmt);
        }
    }
    
    @Override
    public boolean existsByCodigo(Integer codigo) {
        String sql = "SELECT 1 FROM T_GRP_PRODUTO WHERE cd_produto = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery();
            return rs.next();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existência do código: " + e.getMessage(), e);
        } finally {
            closeResources(rs, stmt);
        }
    }
    
    private Produto mapResultSetToProduto(ResultSet rs) throws SQLException {
        Produto produto = new Produto();
        produto.setId_produto(rs.getInt("id_produto"));
        produto.setCd_produto(rs.getInt("cd_produto"));
        produto.setNm_produto(rs.getString("nm_produto"));
        produto.setSt_produto(rs.getString("st_produto"));
        produto.setVl_produto(rs.getDouble("vl_produto"));
        produto.setDt_validade(rs.getDate("dt_validade"));
        produto.setDt_fabricacao(rs.getDate("dt_fabricacao"));
        return produto;
    }
    
    private void closeResources(ResultSet rs, Statement stmt) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            System.err.println("Erro ao fechar recursos: " + e.getMessage());
        }
    }
}