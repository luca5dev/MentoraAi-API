package app.util;

import app.exception.CampoVazioException;

import java.util.Scanner;

//Classe de leitura de texto
public class ConsoleInput {

    public static final Scanner sc = new Scanner(System.in);

    public static int lerNumero(){
        int numero = sc.nextInt();
        sc.nextLine();
        return numero;
    }

    public static Double lerValor(){
        Double valor = sc.nextDouble();
        sc.nextLine();
        return valor;
    }

    public static char lerOpcao(){
        char opc = sc.next().charAt(0);
        return opc;
    }

    public static String lerTexto(){
        String entrada = sc.nextLine().trim();

        if (entrada.isEmpty()){
            throw new CampoVazioException("Este campo não pode estar vazio");
        } else {
            return entrada;
        }
    }

    public static String limpaBuffer(){
        return sc.nextLine();
    }

    public static void fecharScanner(){
        sc.close();
    }


}
