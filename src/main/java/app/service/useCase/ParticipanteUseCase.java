package app.service.useCase;

import app.dao.interfaces.ParticipanteDAO;
import app.model.dto.UsuarioCadastroDTO;
import app.model.entity.Mentor;
import app.model.entity.ParticipantePrograma;
import app.model.factory.AdicionarSkills;
import app.model.factory.EntityFactory;
import app.service.interfaces.ParticipanteService;

import java.util.List;

public class ParticipanteUseCase implements ParticipanteService {

    private final ParticipanteDAO dao;
    private final AdicionarSkills adicionarSkills = new AdicionarSkills();

    public ParticipanteUseCase(ParticipanteDAO dao) {
    this.dao = dao;
    }

    @Override
    public void cadastrar(UsuarioCadastroDTO dto) {
        adicionarSkills.skillBase(dto);

        if (dto.getOpcao() == 2) {
            adicionarSkills.skillsParaMentorados(dto);
        }

        ParticipantePrograma participantePrograma = EntityFactory.criarParticipante(dto);
        dao.persist(participantePrograma);
    }

    @Override
    public List<Mentor> listarMentores() {
        return dao.listarMentores();
    }
}
