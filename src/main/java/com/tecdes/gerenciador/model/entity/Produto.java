package com.tecdes.gerenciador.model.entity;

import java.util.Date;

public class Produto {
    private Integer id_produto;
    private Integer cd_produto;
    private String nm_produto;
    private String st_produto;
    private Double vl_produto;
    private Date dt_validade;
    private Date dt_fabricacao;
    
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
    public Integer getId_produto() {
        return id_produto;
    }
    
    public void setId_produto(Integer id_produto) {
        this.id_produto = id_produto;
    }
    
    public Integer getCd_produto() {
        return cd_produto;
    }
    
    public void setCd_produto(Integer cd_produto) {
        this.cd_produto = cd_produto;
    }
    
    public String getNm_produto() {
        return nm_produto;
    }
    
    public void setNm_produto(String nm_produto) {
        this.nm_produto = nm_produto;
    }
    
    public String getSt_produto() {
        return st_produto;
    }
    
    public void setSt_produto(String st_produto) {
        this.st_produto = st_produto;
    }
    
    public Double getVl_produto() {
        return vl_produto;
    }
    
    public void setVl_produto(Double vl_produto) {
        this.vl_produto = vl_produto;
    }
    
    public Date getDt_validade() {
        return dt_validade;
    }
    
    public void setDt_validade(Date dt_validade) {
        this.dt_validade = dt_validade;
    }
    
    public Date getDt_fabricacao() {
        return dt_fabricacao;
    }
    
    public void setDt_fabricacao(Date dt_fabricacao) {
        this.dt_fabricacao = dt_fabricacao;
    }
    
    @Override
    public String toString() {
        return "Produto [codigo=" + cd_produto + ", nome=" + nm_produto 
                + ", valor=" + vl_produto + ", status=" + st_produto + "]";
    }
}