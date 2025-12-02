package com.tecdes.gerenciador.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/gerenciador_pedidos";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    private static ConnectionFactory instance;
    private Connection connection;
    
    private ConnectionFactory() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver MySQL não encontrado", e);
        }
    }
    
    public static synchronized ConnectionFactory getInstance() {
        if (instance == null) {
            instance = new ConnectionFactory();
        }
        return instance;
    }
    
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexão com MySQL estabelecida!");
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados", e);
        }
    }
    
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Conexão com MySQL fechada!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static Connection getNewConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar nova conexão", e);
        }
    }
    
    public static boolean testConnection() {
        try (Connection testConn = getNewConnection()) {
            return testConn != null;
        } catch (SQLException e) {
            System.err.println("Erro de conexão: " + e.getMessage());
            return false;
        }
    }
}