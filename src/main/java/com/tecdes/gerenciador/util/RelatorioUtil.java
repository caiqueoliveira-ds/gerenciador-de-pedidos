package com.tecdes.gerenciador.util;

import com.tecdes.gerenciador.model.entity.Pedido;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RelatorioUtil {

    public static void gerarRelatorio(List<Pedido> pedidos, String caminhoArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {

            writer.write("===== RELATÓRIO DE PEDIDOS =====");
            writer.newLine();

            for (Pedido p : pedidos) {
                writer.write(String.format("ID: %d | Código: %d | Status: %s | Descrição: %s | Total: R$ %.2f",
                        p.getId_pedido(),
                        p.getCd_pedido(),
                        p.getSt_pedido(),
                        p.getDs_pedido(),
                        p.getTotal() != null ? p.getTotal() : 0.0));
                writer.newLine();
            }

            writer.write("===== FIM DO RELATÓRIO =====");
            writer.newLine();
            writer.newLine();

            System.out.println("Relatório atualizado em: " + caminhoArquivo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
