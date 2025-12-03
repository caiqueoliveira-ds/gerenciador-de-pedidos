package com.tecdes.gerenciador.model.entity;

import java.util.Date;

public class Pedido {
    private Integer id_pedido;
    private Integer cd_pedido;
    private String ds_pedido;
    private String st_pedido;
    private Double total;
    private Integer id_cliente; // Referência ao cliente
    private Date dt_pedido;
    
    public Pedido() {
        this.dt_pedido = new Date();
        this.total = 0.0;
        this.st_pedido = "P"; // P = Pendente
    }
    
    public Pedido(Integer cd_pedido, String descricao, Integer clienteId) {
        this();
        this.cd_pedido = cd_pedido;
        this.ds_pedido = descricao;
        this.id_cliente = clienteId;
    }
    
    // Getters e Setters
    public Integer getId_pedido() { return id_pedido; }
    public void setId_pedido(Integer id_pedido) { this.id_pedido = id_pedido; }
    
    public Integer getCd_pedido() { return cd_pedido; }
    public void setCd_pedido(Integer cd_pedido) { this.cd_pedido = cd_pedido; }
    
    public String getDs_pedido() { return ds_pedido; }
    public void setDs_pedido(String ds_pedido) { this.ds_pedido = ds_pedido; }
    
    public String getSt_pedido() { return st_pedido; }
    public void setSt_pedido(String st_pedido) { this.st_pedido = st_pedido; }
    
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
    
    public Integer getId_cliente() { return id_cliente; }
    public void setId_cliente(Integer id_cliente) { this.id_cliente = id_cliente; }
    
    public Date getDt_pedido() { return dt_pedido; }
    public void setDt_pedido(Date dt_pedido) { this.dt_pedido = dt_pedido; }
    
    @Override
    public String toString() {
        return "Pedido #" + cd_pedido + " - " + ds_pedido + " (R$ " + total + ")";
    }
}