package com.tecdes.gerenciador.repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tecdes.gerenciador.model.entity.Cliente;

public class ClienteRepository implements IClienteRepository{

    private Map <Integer, Cliente> banco = new HashMap<>();
    private int idGenerator = 1;

    @Override
    public Cliente save(Cliente cliente) {
        cliente.setId_cliente(idGenerator++);
        banco.put(cliente.getId_cliente(), cliente);
        return cliente;
    }

    @Override
    public Cliente findById(Integer id_cliente) {
        return banco.get(id_cliente);
    }

    @Override
    public List<Cliente> findAll() {
            return new ArrayList<>(banco.values());
    }

    @Override
    public Cliente update(Cliente cliente) {
        if (cliente.getId_cliente() == null || !banco.containsKey(cliente.getId_cliente())) {
            return null;
        }
        banco.put(cliente.getId_cliente(), cliente);
        return cliente;
    }

    @Override
    public boolean delete(Integer id_cliente) {
        if (banco.containsKey(id_cliente)) {
            banco.remove(id_cliente);
            return true;
        }
        return false;
    }
}
