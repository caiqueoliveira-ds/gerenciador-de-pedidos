package com.tecdes.gerenciador.service;
import com.tecdes.gerenciador.model.entity.Cliente;
import com.tecdes.gerenciador.repository.IClienteRepository;

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
    
    public Cliente atualizarCliente(Integer id, String nome, String cpf, String email) {
        validarDadosCliente(nome, cpf, email);
        
        Cliente cliente = clienteRepository.findById(id);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado com ID: " + id);
        }
        
        // Verificar se CPF está sendo alterado e se já existe
        if (!cliente.getNr_cpf().equals(cpf) && clienteRepository.existsByCpf(cpf)) {
            throw new IllegalArgumentException("CPF já cadastrado: " + cpf);
        }
        
        cliente.setNm_cliente(nome);
        cliente.setNr_cpf(cpf);
        cliente.setDs_email(email);
        
        return clienteRepository.update(cliente);
    }
    
    public boolean removerCliente(Integer id) {
        return clienteRepository.delete(id);
    }
    
    public Cliente buscarPorId(Integer id) {
        return clienteRepository.findById(id);
    }
    
    public Cliente buscarPorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }
    
    private void validarDadosCliente(String nome, String cpf, String email) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente é obrigatório.");
        }
        
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF do cliente é obrigatório.");
        }
        
        // Validação básica de CPF (11 dígitos)
        if (!cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido. Deve conter 11 dígitos.");
        }
        
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email do cliente é obrigatório.");
        }
        
        // Validação básica de email
        if (!email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Email inválido.");
        }
    }
    
    public boolean validarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            return false;
        }
        
        // Algoritmo de validação de CPF
        try {
            int[] numbers = new int[11];
            for (int i = 0; i < 11; i++) {
                numbers[i] = Character.getNumericValue(cpf.charAt(i));
            }
            
            // Primeiro dígito verificador
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum += numbers[i] * (10 - i);
            }
            int firstDigit = 11 - (sum % 11);
            if (firstDigit >= 10) firstDigit = 0;
            
            if (firstDigit != numbers[9]) {
                return false;
            }
            
            // Segundo dígito verificador
            sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += numbers[i] * (11 - i);
            }
            int secondDigit = 11 - (sum % 11);
            if (secondDigit >= 10) secondDigit = 0;
            
            return secondDigit == numbers[10];
        } catch (Exception e) {
            return false;
        }
    }
}