package com.tecdes.gerenciador.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/gerenciador_pedidos";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // XAMPP normalmente tem senha vazia

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao conectar no banco: " + e.getMessage());
        }
    }
}
