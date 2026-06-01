package app.util;

import app.exception.CampoVazioException;
import app.exception.EntradaInvalidaException;
import app.exception.NumeroForaDoIntervaloException;
import app.exception.ValorDaHoraInvalidoException;

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
            throw new EntradaInvalidaException("Entrada inválida. Por favor, insira um número.");
        }
    }

    public static int lerNumeroPositivo() { //Criei para evitar que seja atribuido zero ou horas negativas para a duração da trilha
        int numero = lerNumero();

        if (numero <= 0) {
            throw new NumeroForaDoIntervaloException("O número deve ser maior que zero.");
        }

        return numero;
    }

    public static Double lerValor() {

        try {
            Double valor = sc.nextDouble();
            limpaBuffer();

            if (valor <= 0) {
                throw new ValorDaHoraInvalidoException("O valor do pagamento em horas não pode ser menor ou igual a zero.");
            }
            return valor;

        } catch (InputMismatchException e) {
            limpaBuffer();
            throw new EntradaInvalidaException("Entrada inválida. Por favor, insira um valor numérico.");
        }
    }

    public static char lerOpcao() {

        try {
            String entrada = sc.next();
            limpaBuffer();

            if (entrada.length() != 1) {
                throw new CampoVazioException("Digite apenas um caractere válido.");
            }

            char opcao = entrada.charAt(0);

            if (!Character.isLetter(opcao)) {
                throw new CampoVazioException("Digite apenas letras.");
            }

            return opcao;

        } catch (NoSuchElementException | IllegalStateException e) {
            limpaBuffer();
            throw new CampoVazioException("Entrada inválida. Por favor, insira um caractere válido.");
        }
    }

    public static String lerTexto() {

        try {
            String entrada = sc.nextLine().trim();

            if (entrada.isEmpty()) {
                throw new CampoVazioException("Este campo não pode estar vazio");
            }

            return entrada;

        } catch (NoSuchElementException | IllegalStateException e) {
            throw new CampoVazioException("Erro ao ler a entrada de texto.");
        }
    }

    public static void limpaBuffer() {
        sc.nextLine();
    }

    public static void fecharScanner(){
        sc.close();
    }
}