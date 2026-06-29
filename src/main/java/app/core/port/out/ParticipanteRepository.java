package app.core.port.out;

import app.domain.model.Participante;

import java.util.List;
import java.util.Optional;

public interface ParticipanteRepository {

    void persist(Participante participante);

    void update(Participante participante);

    List<Participante> listarTodosParticipantes();

    Optional<Participante> buscarParticipantePorId(Long id);
}
