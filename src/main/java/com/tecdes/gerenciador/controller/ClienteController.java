package com.tecdes.gerenciador.controller;

import java.util.ArrayList;
import java.util.List;

import com.tecdes.gerenciador.model.entity.Cliente;

public class ClienteController {

    private final List<Cliente> bancoClientes = new ArrayList<>();

    public List<Cliente> listar() {
        return bancoClientes;
    }

    public Cliente cadastrarCliente(
            Integer id,
            String nome,
            String cpf,
            String email
    ) {
        Cliente c = new Cliente();
        c.setId_cliente(id);
        c.setNm_cliente(nome);
        c.setNr_cpf(cpf);     
        c.setDs_email(email);

        bancoClientes.add(c);
        return c;
    }

    public Cliente editarCliente(
            Integer id,
            String nome,
            String cpf,
            String email
    ) {
        Cliente c = buscarPorId(id);

        if (c == null) {
            throw new IllegalArgumentException("Cliente não encontrado para edição.");
        }

        c.setNm_cliente(nome);
        c.setNr_cpf(cpf);
        c.setDs_email(email);

        return c;
    }

    public boolean removerCliente(Integer id) {
        Cliente c = buscarPorId(id);

        if (c != null) {
            bancoClientes.remove(c);
            return true;
        }

        return false;
    }

    public Cliente buscarPorId(Integer id) {
        return bancoClientes.stream()
                .filter(c -> c.getId_cliente().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Cliente buscarPorCpf(String cpf) {
        return bancoClientes.stream()
                .filter(c -> c.getNr_cpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }
}
