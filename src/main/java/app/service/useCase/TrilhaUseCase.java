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

        ValidacaoTrilha validator = new ValidacaoTrilha();
        validator.validarCargaHoraria(trilhaMentoria);
        validator.validarSenioridade(trilhaMentoria);
        validator.validarSkills(trilhaMentoria);

        mentor.setId(null); // Limpa o ID temporário antes de persistir no banco para não dar conflito
        mentorados.forEach(mentorado -> mentorado.setId(null));

        boolean ok = trilhaDAO.persist(trilhaMentoria);
        if (ok) {
            participanteService.limparMemoriaAposPersistencia();
            System.out.println("Trilha persistida com sucesso!");
        } else {
            System.out.println("A Trilha NÃO foi salva no banco de dados. Os dados foram mantidos em memória para você tentar novamente.");
        }
    }

    @Override
    public List<TrilhaMentoria> listarTrilhasPersistidas() {
        return trilhaDAO.listarTrilhas();
    }

    public void persistirJaValidada(TrilhaMentoria trilhaMentoria) {
        boolean ok = trilhaDAO.persist(trilhaMentoria);
        if (ok) System.out.println("Trilha persistida com sucesso!");
        else System.out.println("A persistência falhou.");
    }
}
