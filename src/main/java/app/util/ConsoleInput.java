package app.util;

import app.exception.CampoVazioException;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

//Classe de leitura de texto
public class ConsoleInput {

    public static final Scanner sc = new Scanner(System.in);

    private ConsoleInput() {
        throw new UnsupportedOperationException("Classe utilitária não pode ser instanciada");
    }

    public static int lerNumero() {
        try {
            int numero = sc.nextInt();
            limpaBuffer();
            return numero;

        } catch (InputMismatchException e) {
            limpaBuffer();
            throw new CampoVazioException("Entrada inválida. Por favor, insira um número.");
        }
    }

    public static Double lerValor() {
        try {
            Double valor = sc.nextDouble();
            limpaBuffer();
            return valor;
        } catch (InputMismatchException e) {
            limpaBuffer();
            throw new CampoVazioException("Entrada inválida. Por favor, insira um valor numérico.");
        }
    }

    public static char lerOpcao() {
        try {
            char opcao = sc.next().charAt(0);
            limpaBuffer();
            return opcao;
        } catch (NoSuchElementException | IllegalStateException e) {
            limpaBuffer();
            throw new CampoVazioException("Entrada inválida. Por favor, insira um caractere válido.");
        }
    }

    public static String lerTexto() {
            String entrada = sc.nextLine().trim();

            if (entrada.isEmpty()) {
                throw new CampoVazioException("Este campo não pode estar vazio");
            }
            return entrada;
    }

    public static void limpaBuffer() {
        sc.nextLine();
    }

    public static void fecharScanner(){
        sc.close();
    }
}
