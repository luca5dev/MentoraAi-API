package app.domain.exception;

public class ListaVaziaException extends RuntimeException {
    public ListaVaziaException(String message) {
        super(message);
    }
}
