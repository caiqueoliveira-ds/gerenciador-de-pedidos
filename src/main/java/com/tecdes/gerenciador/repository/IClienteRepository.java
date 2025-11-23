package com.tecdes.gerenciador.repository;

public interface IClienteRepository {
    Cliente save(Cliente cliente);
    Cliente findById(Integer id_cliente);
    List<Cliente> findAll();
    Cliente update(Cliente cliente);
    boolean delete(Integer id_cliente);
}
