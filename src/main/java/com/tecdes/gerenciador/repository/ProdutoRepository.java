package com.tecdes.gerenciador.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tecdes.gerenciador.model.entity.Produto;

public class ProdutoRepository implements IProdutoRepository {

    private Map<Integer, Produto> banco = new HashMap<>();
    private int idGenerator = 1;

    @Override
    public Produto save(Produto produto) {
        produto.setId_produto(idGenerator++);
        banco.put(produto.getId_produto(), produto);
        return produto;
    }

    @Override
    public Produto findById(Integer id_produto) {
        return banco.get(id_produto);
    }

    @Override
    public List<Produto> findAll() {
        return new ArrayList<>(banco.values());
    }

    @Override
    public Produto update(Produto produto) {
        if (produto.getId_produto() == null || !banco.containsKey(produto.getId_produto())) {
            return null;
        }
        banco.put(produto.getId_produto(), produto);
        return produto;
    }

    @Override
    public boolean delete(Integer id_produto) {
        if (banco.containsKey(id_produto)) {
            banco.remove(id_produto);
            return true;
        }
        return false;
    }
}