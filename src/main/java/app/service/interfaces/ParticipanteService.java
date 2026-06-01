package app.service.interfaces;

import app.model.dto.UsuarioCadastroDTO;
import app.model.entity.Mentor;

import java.util.List;

public interface ParticipanteService {
    void cadastrar(UsuarioCadastroDTO dto);

    List<Mentor> listarMentores();
}
