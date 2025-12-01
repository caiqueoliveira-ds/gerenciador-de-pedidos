package com.tecdes.gerenciador.model.entity;

public class Cliente {
/*
 * id_cliente INT PRIMARY KEY AUTO_INCREMENT,
    nm_cliente VARCHAR(150) NOT NULL,
    nr_cpf CHAR(14) NOT NULL UNIQUE,
    ds_email VARCHAR(150) NOT NULL UNIQUE,
 */

    private Integer id_cliente;
    private String nm_cliente;
    private String nr_cpf;
    private String ds_email;

    public Cliente(){}

    public Cliente(Integer id_cliente, String nm_cliente, String nr_cpf, String ds_email){

        this.id_cliente = id_cliente;
        this.nm_cliente = nm_cliente;
        this.nr_cpf = nr_cpf;
        this.ds_email = ds_email;   

    }

    // Getters e Setters

    public Integer getId_cliente(){
        return id_cliente;}
    public void setId_cliente(Integer id_cliente){
        this.id_cliente = id_cliente;}

    public String getNm_cliente(){
        return nm_cliente;}
    public void setNm_cliente(String nm_cliente){
        this.nm_cliente = nm_cliente;}

    public String getNr_cpf(){
        return nr_cpf;}
    public void setNr_cpf(String nr_cpf){
        this.nr_cpf = nr_cpf;}

    public String getDs_email(){
        return ds_email;}
    public void setDs_email(String ds_email){
        this.ds_email = ds_email;}
        
    @Override
    public String toString(){
        return String.format("Cliente [id_cliente=%d, nm_cliente=%s, nr_cpf=%d, ds_email=%s");
    }
}
