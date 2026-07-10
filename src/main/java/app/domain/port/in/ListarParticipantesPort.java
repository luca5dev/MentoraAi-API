package app.domain.port.in;

import app.domain.model.Mentor;
import app.domain.model.Mentorado;

import java.util.List;

public interface ListarParticipantesPort {

    List<Mentor> listarMentores();

    List<Mentorado> listarMentorados();
}
