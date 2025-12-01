package com.tecdes.gerenciador.model.entity;

public class Cliente {
    private Integer id_cliente;
    private String nm_cliente;
    private String nr_cpf;
    private String ds_email;
    
    // Construtor padrão
    public Cliente() {}
    
    // Construtor com parâmetros
    public Cliente(String nome, String cpf, String email) {
        this.nm_cliente = nome;
        this.nr_cpf = cpf;
        this.ds_email = email;
    }
    
    // Getters e Setters
    public Integer getId_cliente() {
        return id_cliente;
    }
    
    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }
    
    public String getNm_cliente() {
        return nm_cliente;
    }
    
    public void setNm_cliente(String nm_cliente) {
        this.nm_cliente = nm_cliente;
    }
    
    public String getNr_cpf() {
        return nr_cpf;
    }
    
    public void setNr_cpf(String nr_cpf) {
        this.nr_cpf = nr_cpf;
    }
    
    public String getDs_email() {
        return ds_email;
    }
    
    public void setDs_email(String ds_email) {
        this.ds_email = ds_email;
    }
    
    @Override
    public String toString() {
        return "Cliente [id=" + id_cliente + ", nome=" + nm_cliente + ", cpf=" + nr_cpf + "]";
    }
}