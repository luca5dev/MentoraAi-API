package app.service.useCase;

import app.dao.interfaces.TrilhaDAO;
import app.exception.MaximoMentoradosAtingidosException;
import app.model.dto.TrilhaMentoriaDTO;
import app.model.entity.Mentor;
import app.model.entity.Mentorado;
import app.model.entity.TrilhaMentoria;
import app.model.factory.EntityFactory;
import app.model.validator.ValidacaoTrilha;
import app.service.interfaces.ParticipanteService;
import app.service.interfaces.TrilhaService;

import java.util.List;

public class TrilhaUseCase implements TrilhaService {

    private final ParticipanteService participanteService;
    private final TrilhaDAO trilhaDAO;

    public TrilhaUseCase(ParticipanteService participanteService, TrilhaDAO trilhaDAO) {
        this.participanteService = participanteService;
        this.trilhaDAO = trilhaDAO;
    }

    @Override
    public void cadastrar(TrilhaMentoriaDTO dto) {
        Mentor mentor = participanteService.buscarMentorPorIdTemporario(dto.getIdMentor());
        List<Mentorado> mentorados = participanteService.buscarMentoradosPorIdsTemporarios(dto.getIdsMentorados());

        if (mentorados.size() > 4) {
            throw new MaximoMentoradosAtingidosException("Máximo de 4 mentorados atingido");
        }

        TrilhaMentoria trilhaMentoria = EntityFactory.criarTrilha(dto, mentor, mentorados);

        mentor.setId(null); // Limpa o ID temporário antes de persistir no banco para não dar conflito
        mentorados.forEach(mentorado -> mentorado.setId(null));

        ValidacaoTrilha validator = new ValidacaoTrilha();
        validator.validarCargaHoraria(trilhaMentoria);
        validator.validarSenioridade(trilhaMentoria);
        validator.validarSkills(trilhaMentoria);

        trilhaDAO.persist(trilhaMentoria);
        participanteService.limparMemoriaAposPersistencia();
    }
}
