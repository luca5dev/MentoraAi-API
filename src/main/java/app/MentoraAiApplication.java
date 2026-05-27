package app;

import app.config.JPAUtil;
import app.controller.MenuPrincipalController;

import java.util.InputMismatchException;

public class MentoraAiApplication {
    public static void main(String[] args) {
        MenuPrincipalController menu = new MenuPrincipalController();


        try{
            menu.iniciaPrograma();
        } catch (InputMismatchException e){
            System.out.println("Caractere invalido");
        }

        JPAUtil.fecharFactory();


    }
}
