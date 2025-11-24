package com.tecdes.gerenciador.model;

public enum StatusPedido {

    PENDENTE("Pendente"),
    PROCESSANDO("Processando"),
    PRONTO("Pronto"),
    ENTREGUE("Entregue"),
    CANCELADO("Cancelado");

    private final String descricao;

    StatusPedido(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}
