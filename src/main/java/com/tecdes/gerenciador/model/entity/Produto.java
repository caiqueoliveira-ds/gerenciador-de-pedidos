package com.tecdes.gerenciador.model.entity;

public class Produto {
    private Integer id_produto;
    private Integer cd_produto;
    private String nm_produto;
    private String st_produto;
    private Double vl_produto;
    
    // Construtor padrão
    public Produto() {}
    
    // Construtor com parâmetros
    public Produto(Integer codigo, String nome, Double valor, String status) {
        this.cd_produto = codigo;
        this.nm_produto = nome;
        this.vl_produto = valor;
        this.st_produto = status;
    }
    
    // Getters e Setters
    public Integer getId_produto() { return id_produto; }
    public void setId_produto(Integer id_produto) { this.id_produto = id_produto; }
    
    public Integer getCd_produto() { return cd_produto; }
    public void setCd_produto(Integer cd_produto) { this.cd_produto = cd_produto; }
    
    public String getNm_produto() { return nm_produto; }
    public void setNm_produto(String nm_produto) { this.nm_produto = nm_produto; }
    
    public String getSt_produto() { return st_produto; }
    public void setSt_produto(String st_produto) { this.st_produto = st_produto; }
    
    public Double getVl_produto() { return vl_produto; }
    public void setVl_produto(Double vl_produto) { this.vl_produto = vl_produto; }
    
    @Override
    public String toString() {
        return nm_produto + " (R$ " + vl_produto + ")";
    }
}