package com.tecdes.gerenciador.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe utilitária para gerenciar conexões com o banco de dados
 * Implementa o padrão Singleton
 */
public class ConnectionFactory {
    private static ConnectionFactory instance;
    private Connection connection;
    
    // Configurações do banco de dados
    private final String URL = "jdbc:mysql://localhost:3306/gerenciador_pedidos";
    private final String USER = "root";
    private final String PASSWORD = "senai123";
    
    private ConnectionFactory() {
        // Construtor privado para prevenir instanciação externa
    }
    
    public static ConnectionFactory getInstance() {
        if (instance == null) {
            instance = new ConnectionFactory();
        }
        return instance;
    }
    
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
    
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}