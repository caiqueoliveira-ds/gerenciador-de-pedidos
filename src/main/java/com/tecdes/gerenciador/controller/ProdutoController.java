package com.tecdes.gerenciador.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tecdes.gerenciador.model.entity.Produto;

public class ProdutoController {

    private final List<Produto> bancoProdutos = new ArrayList<>();

    public List<Produto> listar() {
        return bancoProdutos;
    }

    public Produto cadastrarProduto(
            Integer cd_produto,
            String nome,
            String status,
            Double valor,
            Date validade,
            Date fabricacao
    ) {
        Produto p = new Produto();
        p.setCd_produto(cd_produto);
        p.setNm_produto(nome);
        p.setSt_produto(status);
        p.setVl_produto(valor);
        p.setDt_validade(validade);
        p.setDt_fabricacao(fabricacao);

        bancoProdutos.add(p);
        return p;
    }

    public Produto editarProduto(
            Integer cd_produto,
            String nome,
            String status,
            Double valor,
            Date validade,
            Date fabricacao
    ) {
        Produto p = buscarPorCodigo(cd_produto);

        if (p == null) {
            throw new IllegalArgumentException("Produto não encontrado para edição.");
        }

        p.setNm_produto(nome);
        p.setSt_produto(status);
        p.setVl_produto(valor);
        p.setDt_validade(validade);
        p.setDt_fabricacao(fabricacao);

        return p;
    }

    public boolean removerProduto(Integer codigo) {
        Produto p = buscarPorCodigo(codigo);
        if (p != null) {
            bancoProdutos.remove(p);
            return true;
        }
        return false;
    }

    public Produto buscarPorCodigo(Integer codigo) {
        return bancoProdutos.stream()
                .filter(p -> p.getCd_produto().equals(codigo))
                .findFirst()
                .orElse(null);
    }
}
