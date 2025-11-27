package com.tecdes.gerenciador.repository;

import java.util.List;
import com.tecdes.gerenciador.model.entity.Pedido;

public interface IPedidoRepository {

    Pedido save(Pedido pedido);
    Pedido findById(Integer id_pedido);
    List<Pedido> findAll();
    Pedido update(Pedido pedido);
    boolean delete(Integer id_pedido);
}
