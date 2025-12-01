package com.tecdes.gerenciador.repository;

import java.util.List;
import com.tecdes.gerenciador.model.entity.Produto;

public interface IProdutoRepository {
    Produto save(Produto produto);
    Produto findById(Integer id_produto);
    Produto findByCodigo(Integer cd_produto);
    List<Produto> findAll();
    List<Produto> findByStatus(String status);
    List<Produto> findByNomeContaining(String nome);
    Produto update(Produto produto);
    boolean delete(Integer id_produto);
    boolean existsById(Integer id_produto);
    boolean existsByCodigo(Integer cd_produto);
}