package com.tecdes.gerenciador.service;

import com.tecdes.gerenciador.model.entity.Cliente;
import com.tecdes.gerenciador.repository.IClienteRepository;

import java.util.List;

public class ClienteService {

    private final IClienteRepository clienteRepository;

    public ClienteService(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente cadastrarCliente(String nome, String cpf, String email) {
        validarDadosCliente(nome, cpf, email);

        Cliente cliente = new Cliente();
        cliente.setNm_cliente(nome);
        cliente.setNr_cpf(cpf);
        cliente.setDs_email(email);

        return clienteRepository.save(cliente);
    }

    public List<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    private void validarDadosCliente(String nome, String cpf, String email) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome é obrigatório");

        if (cpf == null || !cpf.matches("\\d{11}"))
            throw new IllegalArgumentException("CPF deve ter 11 dígitos");

        if (email == null || !email.contains("@"))
            throw new IllegalArgumentException("Email inválido");
    }
}
