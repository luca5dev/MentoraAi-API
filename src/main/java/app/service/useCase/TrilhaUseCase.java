package app.service.useCase;

import app.dao.interfaces.ParticipanteDAO;
import app.dao.TrilhaImpl;
import app.exception.MaximoMentoradosAtingidosException;
import app.model.dto.TrilhaMentoriaDTO;
import app.model.entity.Mentor;
import app.model.entity.TrilhaMentoria;
import app.model.factory.EntityFactory;
import app.model.validator.ValidacaoTrilha;
import app.service.interfaces.TrilhaService;

public class TrilhaUseCase implements TrilhaService {

    private final ParticipanteDAO participanteDAO;
    private final TrilhaImpl trilhaDao;

    public TrilhaUseCase(ParticipanteDAO participanteDAO, TrilhaImpl trilhaDao) {
        this.participanteDAO = participanteDAO;
        this.trilhaDao = trilhaDao;
    }

    @Override
    public void cadastrar(TrilhaMentoriaDTO dto) {

        Mentor mentor = participanteDAO.buscarMentorId(dto.getIdMentor()).orElseThrow(() -> new RuntimeException("Mentor não encontrado"));

        if (dto.getMentorados().size() > 4) {
            throw new MaximoMentoradosAtingidosException("Máximo de 4 mentorados atingido");
        }

        TrilhaMentoria trilhaMentoria = EntityFactory.criarTrilha(dto, mentor);

        ValidacaoTrilha validator = new ValidacaoTrilha();
        validator.validarCargaHoraria(trilhaMentoria);
        validator.validarSenioridade(trilhaMentoria);
        validator.validarSkills(trilhaMentoria);

        trilhaDao.persist(trilhaMentoria);
    }
}
