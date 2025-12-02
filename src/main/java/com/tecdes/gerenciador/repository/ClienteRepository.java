package com.tecdes.gerenciador.repository;

import com.tecdes.gerenciador.config.Conexao;
import com.tecdes.gerenciador.model.entity.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository implements IClienteRepository {

    @Override
    public Cliente save(Cliente cliente) {
        String sql = "INSERT INTO T_GRP_CLIENTE (nm_cliente, nr_cpf, ds_email) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cliente.getNm_cliente());
            stmt.setString(2, cliente.getNr_cpf());
            stmt.setString(3, cliente.getDs_email());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                cliente.setId_cliente(rs.getInt(1));
            }

            return cliente;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar cliente: " + e.getMessage());
        }
    }

    @Override
    public Cliente findById(Integer id) {
        String sql = "SELECT * FROM T_GRP_CLIENTE WHERE id_cliente = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapCliente(rs);
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar cliente: " + e.getMessage());
        }
    }

    @Override
    public Cliente findByCpf(String cpf) {
        String sql = "SELECT * FROM T_GRP_CLIENTE WHERE nr_cpf = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapCliente(rs);
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar cliente por CPF: " + e.getMessage());
        }
    }

    @Override
    public List<Cliente> findAll() {
        String sql = "SELECT * FROM T_GRP_CLIENTE";

        List<Cliente> clientes = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                clientes.add(mapCliente(rs));
            }

            return clientes;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar clientes: " + e.getMessage());
        }
    }

    @Override
    public Cliente update(Cliente cliente) {
        String sql = "UPDATE T_GRP_CLIENTE SET nm_cliente=?, nr_cpf=?, ds_email=? WHERE id_cliente=?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNm_cliente());
            stmt.setString(2, cliente.getNr_cpf());
            stmt.setString(3, cliente.getDs_email());
            stmt.setInt(4, cliente.getId_cliente());

            stmt.executeUpdate();
            return cliente;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM T_GRP_CLIENTE WHERE id_cliente=?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir cliente: " + e.getMessage());
        }
    }

    @Override
    public boolean existsById(Integer id) {
        return findById(id) != null;
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return findByCpf(cpf) != null;
    }

    private Cliente mapCliente(ResultSet rs) throws SQLException {
        Cliente c = new Cliente();
        c.setId_cliente(rs.getInt("id_cliente"));
        c.setNm_cliente(rs.getString("nm_cliente"));
        c.setNr_cpf(rs.getString("nr_cpf"));
        c.setDs_email(rs.getString("ds_email"));
        return c;
    }
}
