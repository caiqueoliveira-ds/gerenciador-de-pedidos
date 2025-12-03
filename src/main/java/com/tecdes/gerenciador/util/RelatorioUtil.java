package com.tecdes.gerenciador.util;

import com.tecdes.gerenciador.model.entity.Pedido;
import com.tecdes.gerenciador.model.StatusPedido;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RelatorioUtil {
    
    public static void gerarRelatorioPedidos(List<Pedido> pedidos, String nomeArquivo) {
        String caminho = nomeArquivo != null ? nomeArquivo : "relatorio_pedidos.txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho, false))) {
            
            writer.write("RELATORIO DE PEDIDOS");
            writer.newLine();
            writer.write("=====================");
            writer.newLine();
            writer.newLine();
            
            if (pedidos.isEmpty()) {
                writer.write("Nenhum pedido encontrado.");
                writer.newLine();
            } else {
                double valorTotal = 0.0;
                
                for (Pedido p : pedidos) {
                    writer.write(formatarPedido(p));
                    writer.newLine();
                    
                    if (p.getTotal() != null) {
                        valorTotal += p.getTotal();
                    }
                }
                
                writer.newLine();
                writer.write("RESUMO:");
                writer.newLine();
                writer.write("Total de pedidos: " + pedidos.size());
                writer.newLine();
                writer.write(String.format("Valor total: R$ %.2f", valorTotal));
                writer.newLine();
                writer.write("Pendentes: " + contarPorStatus(pedidos, "P"));
                writer.newLine();
                writer.write("Em processamento: " + contarPorStatus(pedidos, "E"));
                writer.newLine();
                writer.write("Prontos: " + contarPorStatus(pedidos, "R"));
                writer.newLine();
                writer.write("Entregues: " + contarPorStatus(pedidos, "D"));
                writer.newLine();
                writer.write("Cancelados: " + contarPorStatus(pedidos, "C"));
                writer.newLine();
            }
            
            writer.write("=====================");
            writer.newLine();
            
            System.out.println("Relatorio salvo: " + caminho);
            
        } catch (IOException e) {
            System.err.println("Erro ao salvar relatorio: " + e.getMessage());
        }
    }
    
    private static String formatarPedido(Pedido pedido) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Pedido #").append(pedido.getCd_pedido());
        sb.append(" - ID: ").append(pedido.getId_pedido());
        sb.append(" | Cliente: ").append(pedido.getId_cliente() != null ? pedido.getId_cliente() : "N/A");
        sb.append(" | Status: ").append(StatusPedido.getDescricaoByCodigo(pedido.getSt_pedido()));
        sb.append(" | Valor: R$ ").append(String.format("%.2f", pedido.getTotal() != null ? pedido.getTotal() : 0.0));
        
        return sb.toString();
    }
    
    public static void gerarRelatorioResumido(List<Pedido> pedidos, String nomeArquivo) {
        String caminho = nomeArquivo != null ? nomeArquivo : "relatorio_resumido.txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho, false))) {
            
            writer.write("RESUMO DE PEDIDOS");
            writer.newLine();
            writer.write("==================");
            writer.newLine();
            writer.newLine();
            
            long pendentes = contarPorStatus(pedidos, "P");
            long processando = contarPorStatus(pedidos, "E");
            long prontos = contarPorStatus(pedidos, "R");
            long entregues = contarPorStatus(pedidos, "D");
            long cancelados = contarPorStatus(pedidos, "C");
            
            double valorTotal = pedidos.stream()
                .filter(p -> p.getTotal() != null)
                .mapToDouble(Pedido::getTotal)
                .sum();
            
            writer.write(String.format("Total de pedidos: %d", pedidos.size()));
            writer.newLine();
            writer.write(String.format("Valor total: R$ %.2f", valorTotal));
            writer.newLine();
            writer.newLine();
            writer.write("Pendentes: " + pendentes);
            writer.newLine();
            writer.write("Em processamento: " + processando);
            writer.newLine();
            writer.write("Prontos: " + prontos);
            writer.newLine();
            writer.write("Entregues: " + entregues);
            writer.newLine();
            writer.write("Cancelados: " + cancelados);
            writer.newLine();
            writer.write("==================");
            writer.newLine();
            
            System.out.println("Relatorio resumido salvo: " + caminho);
            
        } catch (IOException e) {
            System.err.println("Erro ao salvar relatorio: " + e.getMessage());
        }
    }
    
    private static long contarPorStatus(List<Pedido> pedidos, String status) {
        return pedidos.stream()
                .filter(p -> status.equals(p.getSt_pedido()))
                .count();
    }
    
    public static void gerarRelatorioPedido(Pedido pedido, String nomeArquivo) {
        String caminho = nomeArquivo != null ? nomeArquivo : "pedido_" + pedido.getCd_pedido() + ".txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho, false))) {
            
            writer.write("COMPROVANTE DE PEDIDO");
            writer.newLine();
            writer.write("======================");
            writer.newLine();
            writer.newLine();
            
            writer.write("CODIGO: " + pedido.getCd_pedido());
            writer.newLine();
            writer.write("ID: " + pedido.getId_pedido());
            writer.newLine();
            writer.write("CLIENTE ID: " + (pedido.getId_cliente() != null ? pedido.getId_cliente() : "N/A"));
            writer.newLine();
            writer.write("DESCRICAO: " + pedido.getDs_pedido());
            writer.newLine();
            writer.write("STATUS: " + StatusPedido.getDescricaoByCodigo(pedido.getSt_pedido()));
            writer.newLine();
            writer.write(String.format("VALOR: R$ %.2f", pedido.getTotal() != null ? pedido.getTotal() : 0.0));
            writer.newLine();
            writer.write("======================");
            writer.newLine();
            
            System.out.println("Comprovante salvo: " + caminho);
            
        } catch (IOException e) {
            System.err.println("Erro ao salvar comprovante: " + e.getMessage());
        }
    }
}