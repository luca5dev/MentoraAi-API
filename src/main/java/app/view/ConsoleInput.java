package app.view;

import java.util.Scanner;

//Classe de leitura de texto
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


}
