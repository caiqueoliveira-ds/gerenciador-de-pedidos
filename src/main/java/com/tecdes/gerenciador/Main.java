package com.tecdes.gerenciador;

import com.tecdes.gerenciador.config.ConnectionFactory;
import com.tecdes.gerenciador.view.MainView;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Define look and feel do sistema
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
            // Inicia a aplicação
            SwingUtilities.invokeLater(() -> {
                MainView mainView = new MainView();
                mainView.setVisible(true);
            });
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                "Erro ao iniciar o sistema: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}