package com.tecdes.gerenciador.repository;

import java.util.List;
import com.tecdes.gerenciador.model.entity.Produto;

public interface IProdutoRepository {
    Produto save(Produto produto);
    Produto findById(Integer id_produto);
    List<Produto> findAll();
    Produto update(Produto produto);
    boolean delete(Integer id_produto);
}
