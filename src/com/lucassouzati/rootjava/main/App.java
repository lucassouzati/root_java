package com.lucassouzati.rootjava.main;

import java.util.logging.Logger;

import com.lucassouzati.rootjava.main.infrasctructure.console.GerenciadorDeNotas;

public class App {
    private static final Logger logger = Logger.getLogger(App.class.getName());
    public static void main(String[] args) {

        try {
            GerenciadorDeNotas.runSystem();
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

}
