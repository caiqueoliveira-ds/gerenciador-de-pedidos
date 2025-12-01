package com.tecdes.gerenciador.service;
import com.tecdes.gerenciador.model.entity.Produto;
import com.tecdes.gerenciador.repository.IProdutoRepository;

import java.util.Date;
import java.util.List;

public class ProdutoService {
    
    private final IProdutoRepository produtoRepository;
    
    public ProdutoService(IProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }
    
    public Produto cadastrarProduto(Integer codigo, String nome, Double valor, String status) {
        validarDadosProduto(codigo, nome, valor);
        
        Produto produto = new Produto();
        produto.setCd_produto(codigo);
        produto.setNm_produto(nome);
        produto.setVl_produto(valor);
        produto.setSt_produto(status != null ? status : "ATIVO");
        
        return produtoRepository.save(produto);
    }
    
    public Produto cadastrarProdutoCompleto(Integer codigo, String nome, String status, 
                                           Double valor, Date validade, Date fabricacao) {
        validarDadosProduto(codigo, nome, valor);
        
        Produto produto = new Produto();
        produto.setCd_produto(codigo);
        produto.setNm_produto(nome);
        produto.setSt_produto(status != null ? status : "ATIVO");
        produto.setVl_produto(valor);
        produto.setDt_validade(validade);
        produto.setDt_fabricacao(fabricacao);
        
        return produtoRepository.save(produto);
    }
    
    public Produto atualizarProduto(Integer id, Integer codigo, String nome, String status, 
                                   Double valor, Date validade, Date fabricacao) {
        validarDadosProduto(codigo, nome, valor);
        
        Produto produto = produtoRepository.findById(id);
        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado com ID: " + id);
        }
        
        // Verificar se código está sendo alterado e se já existe
        if (!produto.getCd_produto().equals(codigo) && produtoRepository.existsByCodigo(codigo)) {
            throw new IllegalArgumentException("Código de produto já existe: " + codigo);
        }
        
        produto.setCd_produto(codigo);
        produto.setNm_produto(nome);
        produto.setSt_produto(status != null ? status : produto.getSt_produto());
        produto.setVl_produto(valor);
        produto.setDt_validade(validade);
        produto.setDt_fabricacao(fabricacao);
        
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
            produto.setSt_produto("ATIVO");
            produtoRepository.update(produto);
            return true;
        }
        return false;
    }
    
    public boolean desativarProduto(Integer id) {
        Produto produto = produtoRepository.findById(id);
        if (produto != null) {
            produto.setSt_produto("INATIVO");
            produtoRepository.update(produto);
            return true;
        }
        return false;
    }
    
    private void validarDadosProduto(Integer codigo, String nome, Double valor) {
        if (codigo == null || codigo <= 0) {
            throw new IllegalArgumentException("Código do produto é obrigatório e deve ser maior que zero.");
        }
        
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório.");
        }
        
        if (valor == null || valor < 0) {
            throw new IllegalArgumentException("Valor do produto é obrigatório e não pode ser negativo.");
        }
    }
    
    public boolean verificarValidade(Produto produto) {
        if (produto == null || produto.getDt_validade() == null) {
            return true; // Produto sem validade definida
        }
        
        Date hoje = new Date();
        return !produto.getDt_validade().before(hoje);
    }
}