package app.dao.interfaces;

import app.model.entity.Mentor;
import app.model.entity.Mentorado;
import app.model.entity.ParticipantePrograma;

import java.util.List;
import java.util.Optional;

public interface ParticipanteDAO {

    void persist(ParticipantePrograma participantePrograma);

    void update(ParticipantePrograma participantePrograma);

    List<ParticipantePrograma> listarParticipantes();

    List<Mentor> listarMentores();

    List<Mentorado> listarMentorados();

    Optional<Mentor> buscarMentorId(long id);

    Optional<Mentorado> buscarMentoradoId(long id);
}
