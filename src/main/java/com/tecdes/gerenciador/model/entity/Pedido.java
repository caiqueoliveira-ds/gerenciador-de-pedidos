package com.tecdes.gerenciador.model.entity;

import java.util.ArrayList;
import java.util.List;
import com.tecdes.gerenciador.model.StatusPedido;

public class Pedido {
    private Integer id_pedido;
    private Integer cd_pedido;
    private String ds_pedido;
    private String st_pedido;
    private Double total;
    private Cliente cliente;
    private List<ItemPedido> itens = new ArrayList<>();
    
    // Construtor padrão
    public Pedido() {
        this.st_pedido = StatusPedido.PENDENTE.name(); // Status padrão
        this.total = 0.0;
    }
    
    // Construtor com parâmetros
    public Pedido(Integer cd_pedido, String descricao, Cliente cliente) {
        this();
        this.cd_pedido = cd_pedido;
        this.ds_pedido = descricao;
        this.cliente = cliente;
    }
    
    // Getters e Setters
    public Integer getId_pedido() {
        return id_pedido;
    }
    
    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }
    
    public Integer getCd_pedido() {
        return cd_pedido;
    }
    
    public void setCd_pedido(Integer cd_pedido) {
        this.cd_pedido = cd_pedido;
    }
    
    public String getDs_pedido() {
        return ds_pedido;
    }
    
    public void setDs_pedido(String ds_pedido) {
        this.ds_pedido = ds_pedido;
    }
    
    public String getSt_pedido() {
        return st_pedido;
    }
    
    public void setSt_pedido(String st_pedido) {
        this.st_pedido = st_pedido;
    }
    
    // Getter para Status como Enum
    public StatusPedido getStatus() {
        if (st_pedido == null) {
            return StatusPedido.PENDENTE;
        }
        try {
            return StatusPedido.valueOf(st_pedido.toUpperCase());
        } catch (IllegalArgumentException e) {
            return StatusPedido.PENDENTE;
        }
    }
    
    // Setter para Status como Enum
    public void setStatus(StatusPedido status) {
        this.st_pedido = status.name();
    }
    
    public Double getTotal() {
        return total;
    }
    
    public void setTotal(Double total) {
        this.total = total;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public List<ItemPedido> getItens() {
        return itens;
    }
    
    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }
    
    // Métodos utilitários
    public void adicionarItem(ItemPedido item) {
        if (itens == null) {
            itens = new ArrayList<>();
        }
        itens.add(item);
    }
    
    public void removerItem(int index) {
        if (itens != null && index >= 0 && index < itens.size()) {
            itens.remove(index);
        }
    }
    
    // Método para verificar se pedido está em determinado status
    public boolean isStatus(StatusPedido status) {
        return getStatus() == status;
    }
    
    // Método para verificar se pedido está finalizado (ENTREGUE ou CANCELADO)
    public boolean isFinalizado() {
        return getStatus() == StatusPedido.ENTREGUE || getStatus() == StatusPedido.CANCELADO;
    }
    
    @Override
    public String toString() {
        return "Pedido [id=" + id_pedido + ", codigo=" + cd_pedido + 
               ", status=" + st_pedido + ", total=" + total + 
               ", cliente=" + (cliente != null ? cliente.getNm_cliente() : "null") +
               ", itens=" + (itens != null ? itens.size() : 0) + "]";
    }
}