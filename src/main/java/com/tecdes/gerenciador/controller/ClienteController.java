package com.tecdes.gerenciador.controller;

import com.tecdes.gerenciador.model.entity.Cliente;
import com.tecdes.gerenciador.service.ClienteService;
import java.util.List;

public class ClienteController {
    
    private final ClienteService clienteService;
    
    public ClienteController() {
        this.clienteService = new ClienteService();
    }
    
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    
    public List<Cliente> listar() {
        return clienteService.listarTodos();
    }
    
    public Cliente cadastrarCliente(String nome, String cpf, String email) {
        return clienteService.cadastrarCliente(nome, cpf, email);
    }
    
    public Cliente editarCliente(Integer id, String nome, String cpf, String email) {
        return clienteService.atualizarCliente(id, nome, cpf, email);
    }
    
    public boolean removerCliente(Integer id) {
        return clienteService.removerCliente(id);
    }
    
    public Cliente buscarPorId(Integer id) {
        return clienteService.buscarPorId(id);
    }
    
    public Cliente buscarPorCpf(String cpf) {
        return clienteService.buscarPorCpf(cpf);
    }
    
    public boolean validarCPF(String cpf) {
        return clienteService.validarCPF(cpf);
    }
}