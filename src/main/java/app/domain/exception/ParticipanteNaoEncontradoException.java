package app.domain.exception;

public class ParticipanteNaoEncontradoException extends RuntimeException {
    public ParticipanteNaoEncontradoException(String message) {
        super(message);
    }
}
