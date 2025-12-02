package com.tecdes.gerenciador.repository;

import java.util.List;
import com.tecdes.gerenciador.model.entity.Cliente;

public interface IClienteRepository {
    Cliente save(Cliente cliente);
    Cliente findById(Integer id_cliente);
    Cliente findByCpf(String cpf);
    List<Cliente> findAll();
    Cliente update(Cliente cliente);
    boolean delete(Integer id_cliente);
    boolean existsById(Integer id_cliente);
    boolean existsByCpf(String cpf);
}
