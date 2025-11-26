package com.tecdes.gerenciador;

import com.tecdes.gerenciador.controller.MainController;
import com.tecdes.gerenciador.view.MainView;

public class Main {
    public static void main(String[] args) {
        MainView main = new MainView();
        new MainController(main);
        main.setVisible(true);
    }
}