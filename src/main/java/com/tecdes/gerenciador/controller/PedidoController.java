package com.tecdes.gerenciador.controller;

import com.tecdes.gerenciador.model.StatusPedido;
import com.tecdes.gerenciador.model.entity.Pedido;
import com.tecdes.gerenciador.service.PedidoService;

import java.util.List;

public class PedidoController {
    
    private final PedidoService pedidoService;
    
    public PedidoController() {
        this.pedidoService = new PedidoService();
    }
    
    public Pedido criarPedido(Integer codigo, String descricao, Integer clienteId) {
        try {
            return pedidoService.criarPedido(codigo, descricao, clienteId);
        } catch (Exception e) {
            System.err.println("Erro ao criar pedido: " + e.getMessage());
            return null;
        }
    }
    
    public boolean atualizarPedido(Integer id, Integer codigo, String descricao, String status, Double total, Integer clienteId) {
        try {
            pedidoService.atualizarPedido(id, codigo, descricao, status, total, clienteId);
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao atualizar pedido: " + e.getMessage());
            return false;
        }
    }
    
    public boolean removerPedido(Integer id) {
        return pedidoService.removerPedido(id);
    }
    
    public Pedido buscarPedidoPorId(Integer id) {
        return pedidoService.buscarPorId(id);
    }
    
    public Pedido buscarPedidoPorCodigo(Integer codigo) {
        return pedidoService.buscarPorCodigo(codigo);
    }
    
    public List<Pedido> buscarPedidosPorCliente(Integer clienteId) {
        return pedidoService.buscarPorCliente(clienteId);
    }
    
    public List<Pedido> buscarPedidosPorStatus(String status) {
        return pedidoService.buscarPorStatus(status);
    }
    
    public List<Pedido> listarPedidos() {
        return pedidoService.listarTodos();
    }
    
    public List<Pedido> listarPedidosPendentes() {
        return pedidoService.buscarPorStatus("P");
    }
    
    public List<Pedido> listarPedidosConcluidos() {
        return pedidoService.buscarPorStatus("D");
    }
    
    public List<Pedido> listarPedidosCancelados() {
        return pedidoService.buscarPorStatus("C");
    }
    
    public boolean alterarStatusPedido(Integer id, String status) {
        return pedidoService.alterarStatus(id, status);
    }
    
    public boolean cancelarPedido(Integer id) {
        return pedidoService.cancelarPedido(id);
    }
    
    public boolean finalizarPedido(Integer id) {
        return pedidoService.finalizarPedido(id);
    }
    
    // Métodos para estatísticas
    public int contarPedidos() {
        return pedidoService.contarPedidos();
    }
    
    public int contarPedidosPendentes() {
        return pedidoService.contarPedidosPendentes();
    }
    
    public int contarPedidosConcluidos() {
        return pedidoService.contarPedidosConcluidos();
    }
    
    public int contarPedidosCancelados() {
        return pedidoService.contarPedidosCancelados();
    }
    
    // Métodos para converter status
    public String getDescricaoStatus(String statusCodigo) {
        return StatusPedido.getDescricaoByCodigo(statusCodigo);
    }
    
    public List<String> getTodosStatus() {
        return List.of("P", "E", "R", "D", "C");
    }
}