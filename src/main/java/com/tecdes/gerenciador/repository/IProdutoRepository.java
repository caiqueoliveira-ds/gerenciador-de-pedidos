package com.tecdes.gerenciador.repository;

import java.util.List;
import com.tecdes.gerenciador.model.entity.Produto;

public interface IProdutoRepository {
    Produto save(Produto produto);
    Produto findById(Integer id);
    Produto findByCodigo(Integer codigo);
    List<Produto> findAll();
    List<Produto> findByStatus(String status);
    List<Produto> findByNomeContaining(String nome);
    Produto update(Produto produto);
    boolean delete(Integer id);
    boolean existsById(Integer id);
    boolean existsByCodigo(Integer codigo);
}