package com.tecdes.gerenciador.model.entity;

public class ItemPedido {
    private Integer id_item_pedido;
    private Integer id_pedido;
    private Integer id_produto;
    private Integer qtd_item;
    private String ds_observacao;
    private Double subtotal;
    
    // Produto (para facilitar)
    private Produto produto;
    
    public ItemPedido() {}
    
    public ItemPedido(Integer id_pedido, Integer id_produto, Integer quantidade) {
        this.id_pedido = id_pedido;
        this.id_produto = id_produto;
        this.qtd_item = quantidade;
        this.ds_observacao = "";
    }
    
    // Getters e Setters
    public Integer getId_item_pedido() { return id_item_pedido; }
    public void setId_item_pedido(Integer id_item_pedido) { this.id_item_pedido = id_item_pedido; }
    
    public Integer getId_pedido() { return id_pedido; }
    public void setId_pedido(Integer id_pedido) { this.id_pedido = id_pedido; }
    
    public Integer getId_produto() { return id_produto; }
    public void setId_produto(Integer id_produto) { this.id_produto = id_produto; }
    
    public Integer getQtd_item() { return qtd_item; }
    public void setQtd_item(Integer qtd_item) { this.qtd_item = qtd_item; }
    
    public String getDs_observacao() { return ds_observacao; }
    public void setDs_observacao(String ds_observacao) { this.ds_observacao = ds_observacao; }
    
    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
    
    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }
    
    public Double calcularSubtotal() {
        if (produto != null && produto.getVl_produto() != null && qtd_item != null) {
            this.subtotal = produto.getVl_produto() * qtd_item;
        } else {
            this.subtotal = 0.0;
        }
        return this.subtotal;
    }
    
    @Override
    public String toString() {
        return qtd_item + "x " + (produto != null ? produto.getNm_produto() : "Produto " + id_produto);
    }
}