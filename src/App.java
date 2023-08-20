import java.util.Scanner;

import infrasctructure.console.GerenciadorDeNotas;

public class App {
    public static void main(String[] args) throws Exception {

        try {
            GerenciadorDeNotas.runSystem();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
