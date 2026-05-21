package app;

import app.controller.menuController.MenuPrincipalController;

import java.util.Scanner;

public class ClassePrincipal2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MenuPrincipalController menu = new MenuPrincipalController();

        menu.iniciaPrograma();

    }
}
