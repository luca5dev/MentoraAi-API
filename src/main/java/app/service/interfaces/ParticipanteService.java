package app.service.interfaces;

import app.model.dto.UsuarioCadastroDTO;
import app.model.entity.Mentor;
import app.model.entity.Mentorado;

import java.util.List;

public interface ParticipanteService {

    void cadastrar(UsuarioCadastroDTO dto);

    List<Mentor> listarMentoresEmMemoria();

    List<Mentorado> listarMentoradosEmMemoria();

    Mentor buscarMentorPorIdTemporario(Long id);

    List<Mentorado> buscarMentoradosPorIdsTemporarios(List<Long> ids);

    void limparMemoriaAposPersistencia();

    void editarNomeMentorEmMemoria(Long id, String novoNome);

    void editarNomeMentoradoEmMemoria(Long id, String novoNome);

    boolean removerMentorEmMemoria(Long id);

    boolean removerMentoradoEmMemoria(Long id);

}
