package com.tecdes.gerenciador.model;

public enum StatusPedido {
    PENDENTE("P", "Pendente"),
    PROCESSANDO("E", "Em Processamento"),
    PRONTO("R", "Pronto"),
    ENTREGUE("D", "Entregue"),
    CANCELADO("C", "Cancelado");
    
    private final String codigo;
    private final String descricao;
    
    StatusPedido(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
    
    public String getCodigo() { return codigo; }
    public String getDescricao() { return descricao; }
    
    public static StatusPedido fromCodigo(String codigo) {
        for (StatusPedido status : values()) {
            if (status.getCodigo().equals(codigo)) {
                return status;
            }
        }
        return PENDENTE;
    }
    
    public static String getDescricaoByCodigo(String codigo) {
        return fromCodigo(codigo).getDescricao();
    }
}