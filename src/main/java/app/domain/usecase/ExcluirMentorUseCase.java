package app.domain.usecase;

import app.config.MensagensLogger;
import app.domain.exception.EntradaInvalidaException;
import app.domain.exception.ParticipanteNaoEncontradoException;
import app.domain.port.in.ExcluirMentorPort;
import app.domain.port.out.ParticipanteRepositoryPort;
import app.domain.port.out.TrilhaRepositoryPort;

public class ExcluirMentorUseCase implements ExcluirMentorPort {

    private final ParticipanteRepositoryPort participanteRepositoryPort;
    private final TrilhaRepositoryPort trilhaRepositoryPort;

    public ExcluirMentorUseCase(ParticipanteRepositoryPort participanteRepositoryPort,
                                TrilhaRepositoryPort trilhaRepositoryPort) {
        this.participanteRepositoryPort = participanteRepositoryPort;
        this.trilhaRepositoryPort = trilhaRepositoryPort;
    }

    @Override
    public void excluir(Long id) {
        participanteRepositoryPort.buscarMentorPorId(id)
                .orElseThrow(() -> new ParticipanteNaoEncontradoException(MensagensLogger.MENTOR_NAO_ENCONTRADO + id));

        if (trilhaRepositoryPort.existeTrilhaComMentor(id)) {
            throw new EntradaInvalidaException(MensagensLogger.MENTOR_VINCULADO_TRILHA);
        }

        participanteRepositoryPort.delete(id);
    }
}
