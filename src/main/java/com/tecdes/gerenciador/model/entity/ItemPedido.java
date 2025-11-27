package com.tecdes.gerenciador.model.entity;

import java.util.List;

public class ItemPedido extends Pedido {

    private Produto produto;
    private Integer quantidade;
    private Double total;

    public ItemPedido(){}

    public ItemPedido(Produto produto, Integer quantidade){
        this.produto = produto;
        this.quantidade = quantidade;
        this.total = calcularTotal();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        this.total = calcularTotal();
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
        this.total = calcularTotal();
    }

    public Double calcularTotal(){
        if (produto == null || produto.getVl_produto() == null || quantidade == null){
                return 0.0;
        }
        return produto.getVl_produto() * quantidade;
    }

    public Double getTotal(){
        return total;
    }

    public List getItens() {
            return getItens();

    };
 
}
