package com.tecdes.gerenciador.service;

import com.tecdes.gerenciador.model.entity.Produto;
import com.tecdes.gerenciador.repository.IProdutoRepository;
import com.tecdes.gerenciador.repository.ProdutoRepository;

import java.util.List;

public class ProdutoService {
    
    private final IProdutoRepository produtoRepository;
    
    public ProdutoService() {
        this.produtoRepository = new ProdutoRepository();
    }
    
    public ProdutoService(IProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }
    
    public Produto cadastrarProduto(Integer codigo, String nome, Double valor, String status) {
        validarDados(codigo, nome, valor);
        
        if (produtoRepository.existsByCodigo(codigo)) {
            throw new IllegalArgumentException("Código já existe: " + codigo);
        }
        
        Produto produto = new Produto();
        produto.setCd_produto(codigo);
        produto.setNm_produto(nome);
        produto.setVl_produto(valor);
        produto.setSt_produto(status != null ? status : "A");
        
        return produtoRepository.save(produto);
    }
    
    
    public Produto atualizarProduto(Integer id, Integer codigo, String nome, String status, Double valor) {
        validarDados(codigo, nome, valor);
        
        Produto produto = produtoRepository.findById(id);
        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado: " + id);
        }
        
        if (!produto.getCd_produto().equals(codigo) && produtoRepository.existsByCodigo(codigo)) {
            throw new IllegalArgumentException("Código já existe: " + codigo);
        }
        
        produto.setCd_produto(codigo);
        produto.setNm_produto(nome);
        produto.setSt_produto(status != null ? status : produto.getSt_produto());
        produto.setVl_produto(valor);
        
        return produtoRepository.update(produto);
    }
    
    public boolean removerProduto(Integer id) {
        return produtoRepository.delete(id);
    }
    
    public Produto buscarPorId(Integer id) {
        return produtoRepository.findById(id);
    }
    
    public Produto buscarPorCodigo(Integer codigo) {
        return produtoRepository.findByCodigo(codigo);
    }
    
    public List<Produto> buscarPorNome(String nome) {
        return produtoRepository.findByNomeContaining(nome);
    }
    
    public List<Produto> buscarPorStatus(String status) {
        return produtoRepository.findByStatus(status);
    }
    
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }
    
    public boolean ativarProduto(Integer id) {
        Produto produto = produtoRepository.findById(id);
        if (produto != null) {
            produto.setSt_produto("A");
            produtoRepository.update(produto);
            return true;
        }
        return false;
    }
    
    public boolean desativarProduto(Integer id) {
        Produto produto = produtoRepository.findById(id);
        if (produto != null) {
            produto.setSt_produto("I");
            produtoRepository.update(produto);
            return true;
        }
        return false;
    }
    
    public boolean alternarStatus(Integer id) {
        Produto produto = produtoRepository.findById(id);
        if (produto != null) {
            if ("A".equals(produto.getSt_produto())) {
                produto.setSt_produto("I");
            } else {
                produto.setSt_produto("A");
            }
            produtoRepository.update(produto);
            return true;
        }
        return false;
    }
    
    private void validarDados(Integer codigo, String nome, Double valor) {
        if (codigo == null || codigo <= 0) {
            throw new IllegalArgumentException("Código inválido");
        }
        
        if (nome == null || nome.trim().isEmpty() || nome.length() > 80) {
            throw new IllegalArgumentException("Nome deve ter entre 1 e 80 caracteres");
        }
        
        if (valor == null || valor < 0) {
            throw new IllegalArgumentException("Valor inválido");
        }
    }
}