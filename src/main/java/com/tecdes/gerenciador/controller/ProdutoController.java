package com.tecdes.gerenciador.controller;

import com.tecdes.gerenciador.model.entity.Produto;
import com.tecdes.gerenciador.service.ProdutoService;

import java.util.Date;
import java.util.List;

public class ProdutoController {
    
    private final ProdutoService produtoService;
    
    public ProdutoController() {
        this.produtoService = new ProdutoService();
    }
    
    // Métodos CRUD para a view
    
    public boolean cadastrarProduto(Integer codigo, String nome, Double preco, String status) {
        try {
            produtoService.cadastrarProduto(codigo, nome, preco, status);
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar: " + e.getMessage());
            return false;
        }
    }
    
    public boolean atualizarProduto(Integer id, Integer codigo, String nome, String status, 
                                   Double preco, Date validade, Date fabricacao) {
        try {
            produtoService.atualizarProduto(id, codigo, nome, status, preco, validade, fabricacao);
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao atualizar: " + e.getMessage());
            return false;
        }
    }
    
    public boolean removerProduto(Integer id) {
        return produtoService.removerProduto(id);
    }
    
    public Produto buscarProdutoPorId(Integer id) {
        return produtoService.buscarPorId(id);
    }
    
    public Produto buscarProdutoPorCodigo(Integer codigo) {
        return produtoService.buscarPorCodigo(codigo);
    }
    
    public List<Produto> buscarProdutosPorNome(String nome) {
        return produtoService.buscarPorNome(nome);
    }
    
    public List<Produto> listarProdutos() {
        return produtoService.listarTodos();
    }
    
    public List<Produto> listarProdutosAtivos() {
        return produtoService.buscarPorStatus("A");
    }
    
    public List<Produto> listarProdutosInativos() {
        return produtoService.buscarPorStatus("I");
    }
    
    public boolean ativarProduto(Integer id) {
        return produtoService.ativarProduto(id);
    }
    
    public boolean desativarProduto(Integer id) {
        return produtoService.desativarProduto(id);
    }
    
    public boolean alternarStatusProduto(Integer id) {
        return produtoService.alternarStatus(id);
    }
    
    public long contarProdutosAtivos() {
        return produtoService.listarTodos().stream()
                .filter(p -> "A".equals(p.getSt_produto()))
                .count();
    }
    
    public long contarTotalProdutos() {
        return produtoService.listarTodos().size();
    }
}