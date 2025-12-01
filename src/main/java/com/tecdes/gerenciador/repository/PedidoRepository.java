package com.tecdes.gerenciador.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.tecdes.gerenciador.model.entity.Pedido;

public class PedidoRepository implements IPedidoRepository {

    private final Map<Integer, Pedido> banco = new HashMap<>();
    private final Map<Integer, Integer> codigoIndex = new HashMap<>(); // Índice para busca por código
    private int idGenerator = 1;

    @Override
    public Pedido save(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo");
        }
        
        // Validar código único
        if (pedido.getCd_pedido() != null && existsByCodigo(pedido.getCd_pedido())) {
            throw new IllegalArgumentException("Código de pedido já existe: " + pedido.getCd_pedido());
        }
        
        // Se já tem ID, é uma atualização
        if (pedido.getId_pedido() != null && banco.containsKey(pedido.getId_pedido())) {
            return update(pedido);
        }
        
        // Se não tem ID, é um novo pedido
        pedido.setId_pedido(idGenerator++);
        banco.put(pedido.getId_pedido(), pedido);
        if (pedido.getCd_pedido() != null) {
            codigoIndex.put(pedido.getCd_pedido(), pedido.getId_pedido());
        }
        return pedido;
    }

    @Override
    public Pedido findById(Integer id_pedido) {
        return banco.get(id_pedido);
    }

    @Override
    public Pedido findByCodigo(Integer cd_pedido) {
        Integer id = codigoIndex.get(cd_pedido);
        return id != null ? banco.get(id) : null;
    }

    @Override
    public List<Pedido> findAll() {
        return new ArrayList<>(banco.values());
    }

    @Override
    public List<Pedido> findByClienteId(Integer clienteId) {
        return banco.values().stream()
                .filter(pedido -> pedido.getCliente() != null && 
                                 clienteId.equals(pedido.getCliente().getId_cliente()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Pedido> findByStatus(String status) {
        return banco.values().stream()
                .filter(pedido -> status.equals(pedido.getSt_pedido()))
                .collect(Collectors.toList());
    }

    @Override
    public Pedido update(Pedido pedido) {
        if (pedido == null || pedido.getId_pedido() == null) {
            throw new IllegalArgumentException("Pedido ou ID não pode ser nulo");
        }
        
        if (!banco.containsKey(pedido.getId_pedido())) {
            throw new IllegalArgumentException("Pedido não encontrado para atualização");
        }
        
        // Atualizar índice de código se necessário
        Pedido pedidoAntigo = banco.get(pedido.getId_pedido());
        if (pedidoAntigo.getCd_pedido() != null && pedido.getCd_pedido() != null &&
            !pedidoAntigo.getCd_pedido().equals(pedido.getCd_pedido())) {
            codigoIndex.remove(pedidoAntigo.getCd_pedido());
            codigoIndex.put(pedido.getCd_pedido(), pedido.getId_pedido());
        }
        
        banco.put(pedido.getId_pedido(), pedido);
        return pedido;
    }

    @Override
    public boolean delete(Integer id_pedido) {
        if (banco.containsKey(id_pedido)) {
            Pedido pedido = banco.get(id_pedido);
            if (pedido.getCd_pedido() != null) {
                codigoIndex.remove(pedido.getCd_pedido());
            }
            banco.remove(id_pedido);
            return true;
        }
        return false;
    }

    @Override
    public boolean existsById(Integer id_pedido) {
        return banco.containsKey(id_pedido);
    }

    @Override
    public boolean existsByCodigo(Integer cd_pedido) {
        return codigoIndex.containsKey(cd_pedido);
    }
}