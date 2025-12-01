package com.tecdes.gerenciador.util;

import com.tecdes.gerenciador.model.entity.Pedido;
import com.tecdes.gerenciador.model.entity.ItemPedido;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RelatorioUtil {
    
    public static void gerarRelatorioPedidos(List<Pedido> pedidos, String relatoriotxt) {
        String caminho = "relatorio.txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho, true))) {
            
            writer.write("=== RELATÓRIO DE PEDIDOS ===");
            writer.newLine();
            
            for (Pedido p : pedidos) {
                writer.write(formatarPedido(p));
                writer.newLine();
            }
            
            writer.write("Total de pedidos: " + pedidos.size());
            writer.newLine();
            writer.write("=============================");
            writer.newLine();
            
        } catch (IOException e) {
            System.err.println("Erro ao salvar relatório: " + e.getMessage());
        }
    }
    
    private static String formatarPedido(Pedido pedido) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Pedido #").append(pedido.getCd_pedido())
          .append(" - ").append(pedido.getSt_pedido())
          .append(" | R$ ").append(String.format("%.2f", pedido.getTotal() != null ? pedido.getTotal() : 0.0));
        
        if (pedido.getItens() != null) {
            sb.append(" (").append(pedido.getItens().size()).append(" itens)");
        }
        
        return sb.toString();
    }
    
    public static void gerarRelatorioResumido(List<Pedido> pedidos) {
        String caminho = "relatorio.txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho, true))) {
            
            writer.write("=== RESUMO PEDIDOS ===");
            writer.newLine();
            
            long pendentes = contarPorStatus(pedidos, "PENDENTE");
            long processando = contarPorStatus(pedidos, "PROCESSANDO");
            long prontos = contarPorStatus(pedidos, "PRONTO");
            long entregues = contarPorStatus(pedidos, "ENTREGUE");
            long cancelados = contarPorStatus(pedidos, "CANCELADO");
            
            writer.write("Pendentes: " + pendentes);
            writer.newLine();
            writer.write("Processando: " + processando);
            writer.newLine();
            writer.write("Prontos: " + prontos);
            writer.newLine();
            writer.write("Entregues: " + entregues);
            writer.newLine();
            writer.write("Cancelados: " + cancelados);
            writer.newLine();
            writer.write("Total: " + pedidos.size());
            writer.newLine();
            
        } catch (IOException e) {
            System.err.println("Erro ao salvar relatório: " + e.getMessage());
        }
    }
    
    private static long contarPorStatus(List<Pedido> pedidos, String status) {
        return pedidos.stream()
                .filter(p -> status.equals(p.getSt_pedido()))
                .count();
    }
}