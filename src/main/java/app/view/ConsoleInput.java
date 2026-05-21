package app.view;

import java.util.Scanner;

public class ConsoleInput {
    public static final Scanner sc = new Scanner(System.in);


    public static int lerNumero(){
        int opcao = sc.nextInt();
        sc.nextLine();
        return opcao;
    }

    public static String lerTexto(){
        return sc.nextLine();
    }

    public static String limpaBuffer(){
        return sc.nextLine();
    }
}
