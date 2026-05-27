package app.exception;

import java.util.InputMismatchException;

public class EntradaInvalidaException extends InputMismatchException {
    public EntradaInvalidaException(){
        super("Entrada inválida fornecida pelo usuário");
    }
    public EntradaInvalidaException(String message) {
        super(message);
    }
}
