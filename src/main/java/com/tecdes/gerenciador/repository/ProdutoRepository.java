package com.tecdes.gerenciador.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.tecdes.gerenciador.model.entity.Produto;

public class ProdutoRepository implements IProdutoRepository {

    private final Map<Integer, Produto> banco = new HashMap<>();
    private final Map<Integer, Integer> codigoIndex = new HashMap<>(); // Índice para busca por código
    private int idGenerator = 1;

    @Override
    public Produto save(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }
        
        // Validar código único
        if (produto.getCd_produto() != null && existsByCodigo(produto.getCd_produto())) {
            throw new IllegalArgumentException("Código de produto já existe: " + produto.getCd_produto());
        }
        
        // Se já tem ID, é uma atualização
        if (produto.getId_produto() != null && banco.containsKey(produto.getId_produto())) {
            return update(produto);
        }
        
        // Se não tem ID, é um novo produto
        produto.setId_produto(idGenerator++);
        banco.put(produto.getId_produto(), produto);
        if (produto.getCd_produto() != null) {
            codigoIndex.put(produto.getCd_produto(), produto.getId_produto());
        }
        return produto;
    }

    @Override
    public Produto findById(Integer id_produto) {
        return banco.get(id_produto);
    }

    @Override
    public Produto findByCodigo(Integer cd_produto) {
        Integer id = codigoIndex.get(cd_produto);
        return id != null ? banco.get(id) : null;
    }

    @Override
    public List<Produto> findAll() {
        return new ArrayList<>(banco.values());
    }

    @Override
    public List<Produto> findByStatus(String status) {
        return banco.values().stream()
                .filter(produto -> status.equals(produto.getSt_produto()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Produto> findByNomeContaining(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        String nomeLower = nome.toLowerCase();
        return banco.values().stream()
                .filter(produto -> produto.getNm_produto() != null &&
                                  produto.getNm_produto().toLowerCase().contains(nomeLower))
                .collect(Collectors.toList());
    }

    @Override
    public Produto update(Produto produto) {
        if (produto == null || produto.getId_produto() == null) {
            throw new IllegalArgumentException("Produto ou ID não pode ser nulo");
        }
        
        if (!banco.containsKey(produto.getId_produto())) {
            throw new IllegalArgumentException("Produto não encontrado para atualização");
        }
        
        // Atualizar índice de código se necessário
        Produto produtoAntigo = banco.get(produto.getId_produto());
        if (produtoAntigo.getCd_produto() != null && produto.getCd_produto() != null &&
            !produtoAntigo.getCd_produto().equals(produto.getCd_produto())) {
            codigoIndex.remove(produtoAntigo.getCd_produto());
            codigoIndex.put(produto.getCd_produto(), produto.getId_produto());
        }
        
        banco.put(produto.getId_produto(), produto);
        return produto;
    }

    @Override
    public boolean delete(Integer id_produto) {
        if (banco.containsKey(id_produto)) {
            Produto produto = banco.get(id_produto);
            if (produto.getCd_produto() != null) {
                codigoIndex.remove(produto.getCd_produto());
            }
            banco.remove(id_produto);
            return true;
        }
        return false;
    }

    @Override
    public boolean existsById(Integer id_produto) {
        return banco.containsKey(id_produto);
    }

    @Override
    public boolean existsByCodigo(Integer cd_produto) {
        return codigoIndex.containsKey(cd_produto);
    }
}