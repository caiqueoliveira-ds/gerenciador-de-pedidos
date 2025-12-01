package com.tecdes.gerenciador.repository;

import java.util.List;
import com.tecdes.gerenciador.model.entity.Pedido;

public interface IPedidoRepository {
    Pedido save(Pedido pedido);
    Pedido findById(Integer id_pedido);
    Pedido findByCodigo(Integer cd_pedido);
    List<Pedido> findAll();
    List<Pedido> findByClienteId(Integer clienteId);
    List<Pedido> findByStatus(String status);
    Pedido update(Pedido pedido);
    boolean delete(Integer id_pedido);
    boolean existsById(Integer id_pedido);
    boolean existsByCodigo(Integer cd_pedido);
}