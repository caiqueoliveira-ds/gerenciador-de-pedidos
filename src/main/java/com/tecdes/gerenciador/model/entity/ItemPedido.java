package com.tecdes.gerenciador.model.entity;

public class ItemPedido {
    private Produto produto;
    private Integer quantidade;
    private Double subtotal;
    
    // Construtor padrão
    public ItemPedido() {}
    
    // Construtor com parâmetros
    public ItemPedido(Produto produto, Integer quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        calcularSubtotal();
    }
    
    // Getters e Setters
    public Produto getProduto() {
        return produto;
    }
    
    public void setProduto(Produto produto) {
        this.produto = produto;
        calcularSubtotal();
    }
    
    public Integer getQuantidade() {
        return quantidade;
    }
    
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
        calcularSubtotal();
    }
    
    public Double getSubtotal() {
        return subtotal;
    }
    
    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
    
    // Métodos de negócio
    public Double calcularSubtotal() {
        if (produto != null && produto.getVl_produto() != null && quantidade != null) {
            this.subtotal = produto.getVl_produto() * quantidade;
        } else {
            this.subtotal = 0.0;
        }
        return this.subtotal;
    }
    
    public Double calcularTotal() {
        return calcularSubtotal();
    }
    
    @Override
    public String toString() {
        return "ItemPedido [produto=" + (produto != null ? produto.getNm_produto() : "null") 
                + ", quantidade=" + quantidade + ", subtotal=" + subtotal + "]";
    }
}