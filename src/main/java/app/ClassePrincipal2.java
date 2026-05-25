package app;

import app.controller.MenuPrincipalController;

import java.util.InputMismatchException;

public class ClassePrincipal2 {
    public static void main(String[] args) {
        MenuPrincipalController menu = new MenuPrincipalController();

        try{
            menu.iniciaPrograma();
        } catch (InputMismatchException e){
            System.out.println("Caractere invalido");
        }


    }
}
