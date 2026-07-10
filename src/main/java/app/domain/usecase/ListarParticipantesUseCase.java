package app.domain.usecase;

import app.domain.model.Mentor;
import app.domain.model.Mentorado;
import app.domain.port.in.ListarParticipantesPort;
import app.domain.port.out.ParticipanteRepositoryPort;

import java.util.List;

public class ListarParticipantesUseCase implements ListarParticipantesPort {

    private final ParticipanteRepositoryPort participanteRepositoryPort;

    public ListarParticipantesUseCase(ParticipanteRepositoryPort participanteRepositoryPort) {
        this.participanteRepositoryPort = participanteRepositoryPort;
    }

    @Override
    public List<Mentor> listarMentores() {
        return participanteRepositoryPort.listarTodosParticipantes().stream()
                .filter(Mentor.class::isInstance)
                .map(Mentor.class::cast)
                .toList();
    }

    @Override
    public List<Mentorado> listarMentorados() {
        return participanteRepositoryPort.listarTodosParticipantes().stream()
                .filter(Mentorado.class::isInstance)
                .map(Mentorado.class::cast)
                .toList();
    }
}
