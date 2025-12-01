package com.tecdes.gerenciador.model.entity;

import java.util.List;

public class Pedido {
/*
 * id_pedido INT PRIMARY KEY AUTO_INCREMENT,
    cd_pedido INT NOT NULL UNIQUE,
    st_pedido CHAR(1) NOT NULL,
    ds_pedido VARCHAR(80) NOT NULL
 */

    private Integer id_pedido;
    private Integer cd_pedido;
    private String st_pedido;
    private String ds_pedido;
    private List<ItemPedido> itens;
    protected Double total;

    public Pedido(){};

    public Pedido(Integer id_pedido, Integer cd_pedido, String st_pedido, String ds_pedido){
        this.id_pedido = id_pedido;
        this.cd_pedido = cd_pedido;
        this.st_pedido = st_pedido;
        this.ds_pedido = ds_pedido;
    }

    public Integer getId_pedido(){
        return id_pedido;}
    public void setId_pedido(Integer id_pedido){
        this.id_pedido = id_pedido;}

    public Integer getCd_pedido(){
        return cd_pedido;}
    public void setCd_pedido(Integer cd_pedido){
        this.cd_pedido = cd_pedido;}

    public String getSt_pedido (){
        return st_pedido;}
    public void setSt_pedido (String st_pedido){
        this.st_pedido = st_pedido;}

    public String getDs_pedido (){
        return ds_pedido;}
    public void setDs_pedido (String ds_pedido){
        this.ds_pedido = ds_pedido;}

        @Override
        public String toString(){
            return String.format
            ("Pedido [id_pedido=%d, cd_pedido=%d, st_pedido=%s, ds_pedido=%s]",
            id_pedido, cd_pedido, st_pedido, ds_pedido);
        }
        

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public Double getTotal() {
        return total;
    }
    
    public void setTotal(Double total) {
        this.total = total;
    }
}
