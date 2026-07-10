package app.domain.exception;

import java.util.InputMismatchException;

public class EntradaInvalidaException extends InputMismatchException {
    public EntradaInvalidaException(String message) {
        super(message);
    }
}
