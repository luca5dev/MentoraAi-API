package app.domain.port.out;

import app.domain.model.Mentor;
import app.domain.model.Mentorado;
import app.domain.model.ParticipantePrograma;

import java.util.List;
import java.util.Optional;

public interface ParticipanteRepositoryPort {

    void persist(ParticipantePrograma participantePrograma);

    void update(ParticipantePrograma participantePrograma);

    List<ParticipantePrograma> listarTodosParticipantes();

    Optional<ParticipantePrograma> buscarParticipantePorId(Long id);

    Optional<Mentor> buscarMentorPorId(Long id);

    List<Mentorado> buscarMentoradosPorIds(List<Long> ids);

}
