package com.tecdes.gerenciador.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tecdes.gerenciador.model.entity.Cliente;

public class ClienteRepository implements IClienteRepository {

    private final Map<Integer, Cliente> banco = new HashMap<>();
    private final Map<String, Integer> cpfIndex = new HashMap<>(); // Índice para busca por CPF
    private int idGenerator = 1;

    @Override
    public Cliente save(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        }
        
        // Validar CPF único
        if (cliente.getNr_cpf() != null && existsByCpf(cliente.getNr_cpf())) {
            throw new IllegalArgumentException("CPF já cadastrado: " + cliente.getNr_cpf());
        }
        
        // Se já tem ID, é uma atualização
        if (cliente.getId_cliente() != null && banco.containsKey(cliente.getId_cliente())) {
            return update(cliente);
        }
        
        // Se não tem ID, é um novo cliente
        cliente.setId_cliente(idGenerator++);
        banco.put(cliente.getId_cliente(), cliente);
        cpfIndex.put(cliente.getNr_cpf(), cliente.getId_cliente());
        return cliente;
    }

    @Override
    public Cliente findById(Integer id_cliente) {
        return banco.get(id_cliente);
    }

    @Override
    public Cliente findByCpf(String cpf) {
        Integer id = cpfIndex.get(cpf);
        return id != null ? banco.get(id) : null;
    }

    @Override
    public List<Cliente> findAll() {
        return new ArrayList<>(banco.values());
    }

    @Override
    public Cliente update(Cliente cliente) {
        if (cliente == null || cliente.getId_cliente() == null) {
            throw new IllegalArgumentException("Cliente ou ID não pode ser nulo");
        }
        
        if (!banco.containsKey(cliente.getId_cliente())) {
            throw new IllegalArgumentException("Cliente não encontrado para atualização");
        }
        
        // Atualizar índice de CPF se necessário
        Cliente clienteAntigo = banco.get(cliente.getId_cliente());
        if (!clienteAntigo.getNr_cpf().equals(cliente.getNr_cpf())) {
            cpfIndex.remove(clienteAntigo.getNr_cpf());
            cpfIndex.put(cliente.getNr_cpf(), cliente.getId_cliente());
        }
        
        banco.put(cliente.getId_cliente(), cliente);
        return cliente;
    }

    @Override
    public boolean delete(Integer id_cliente) {
        if (banco.containsKey(id_cliente)) {
            Cliente cliente = banco.get(id_cliente);
            cpfIndex.remove(cliente.getNr_cpf());
            banco.remove(id_cliente);
            return true;
        }
        return false;
    }

    @Override
    public boolean existsById(Integer id_cliente) {
        return banco.containsKey(id_cliente);
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return cpfIndex.containsKey(cpf);
    }
}