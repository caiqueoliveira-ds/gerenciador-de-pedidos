package com.tecdes.gerenciador.model.entity;

import java.sql.Date;

public class Produto {
    /*id_produto INT PRIMARY KEY AUTO_INCREMENT,
    cd_produto INT NOT NULL UNIQUE,
    nm_produto VARCHAR(80) NOT NULL,
    st_produto CHAR(1) NOT NULL,
    vl_produto DECIMAL(5,2) NOT NULL,
    dt_validade DATE NOT NULL,
    dt_fabricacao DATE NULL*/

    private Integer id_produto;
    private Integer cd_produto;
    private String nm_produto;
    private Integer st_produto;
    private Double vl_produto;
    private Date dt_validade;
    private Date dt_fabricacao;
    
    public Produto(){}

    public Produto(String nm_produto, Integer cd_produto, Double vl_produto, Integer id_produto, Integer st_produto, Date dt_validade, Date dt_fabricacao){
        this.id_produto = id_produto;
        this.cd_produto = cd_produto;
        this.nm_produto = nm_produto;
        this.st_produto = st_produto;
        this.vl_produto = vl_produto;
        this.dt_validade = dt_validade;
        this.dt_fabricacao = dt_fabricacao;
    }

    public Integer getId_produto() {
        return id_produto;}
    public void setId_produto(Integer id_produto) {
        this.id_produto = id_produto;}

    public Integer getCd_produto() {
        return cd_produto;}
    public void setCd_produto(Integer cd_produto) {
        this.cd_produto = cd_produto;}

    public String getNm_produto() {
        return nm_produto;}
    public void setNm_produto(String nm_produto) {
        this.nm_produto = nm_produto;}

    public Integer getSt_produto() {
        return st_produto;}
    public void setSt_produto(Integer st_produto) {
        this.st_produto = st_produto;}

    public Double getVl_produto() {
        return vl_produto;}
    public void setVl_produto(Double vl_produto) {
        this.vl_produto = vl_produto;}

    public Date getDt_validade() {
        return dt_validade;}
    public void setDt_validade(Date dt_validade) {
        this.dt_validade = dt_validade;}

    public Date getDt_fabricacao() {
        return dt_fabricacao;}
    public void setDt_fabricacao(Date dt_fabricacao) {
        this.dt_fabricacao = dt_fabricacao;}

    @Override    
    public String toString(){
        return String.format("Produto [id_produto=%d, cd_produto=%d, nm_produto=%s, st_produto=%d, vl_produto=R$ %.2f, dt_validade=%s, dt_fabricacao=%s");
    }
    
}
