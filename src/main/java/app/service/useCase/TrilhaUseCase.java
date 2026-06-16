package app.service.useCase;

import app.dao.interfaces.TrilhaDAO;
import app.model.dto.TrilhaMentoriaDTO;
import app.model.entity.Mentor;
import app.model.entity.Mentorado;
import app.model.entity.TrilhaMentoria;
import app.model.enums.Skill;
import app.model.factory.EntityFactory;
import app.model.validator.ValidacaoTrilha;
import app.service.interfaces.ParticipanteService;
import app.service.interfaces.TrilhaService;

import java.util.List;
import java.util.Optional;

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

        List<Skill> skillsDaTrilha = montarSkillsDaTrilha(mentor, mentorados);
        dto.setSkills(skillsDaTrilha);

        TrilhaMentoria trilhaMentoria = EntityFactory.criarTrilha(dto, mentor, mentorados);
        System.out.println("Skills que serão ensinadas nesta trilha:");
        trilhaMentoria.getSkillsDaTrilha().forEach(skill -> System.out.println("- " + skill));

        ValidacaoTrilha validator = new ValidacaoTrilha();
        validator.validarCargaHoraria(trilhaMentoria);
        validator.validarQuantidadeMentorados(trilhaMentoria);
        validator.validarSenioridade(trilhaMentoria);
        validator.validarSkills(trilhaMentoria);

        mentor.setId(null); // Limpa o ID temporário antes de persistir no banco para não dar conflito
        mentorados.forEach(mentorado -> mentorado.setId(null));

        boolean ok = trilhaDAO.persist(trilhaMentoria);
        if (ok) {
            participanteService.limparMemoriaAposPersistencia();
            imprimirResumoTrilha(trilhaMentoria);
        } else {
            System.out.println("A Trilha NÃO foi salva no banco de dados. Os dados foram mantidos em memória para você tentar novamente.");
        }
    }

    private List<Skill> montarSkillsDaTrilha(Mentor mentor, List<Mentorado> mentorados) {
        return mentorados.stream()
                .flatMap(mentorado -> mentorado.getSkillsDesejadas().stream())
                .distinct()
                .filter(skill -> mentor.getSkills().contains(skill))
                .toList();
    }

    private void imprimirResumoTrilha(TrilhaMentoria trilhaMentoria) {
        System.out.println("\nTrilha persistida com sucesso!");
        System.out.println("--------------------------------------------------");
        System.out.println("Nome da trilha: " + trilhaMentoria.getNomeDaTrilha());
        System.out.println("Duração: " + trilhaMentoria.getCicloEmMeses() + " meses");

        System.out.println("\nMentor:");
        System.out.println("- " +trilhaMentoria.getMentor().getNome()
        + " | Senioridade: " + trilhaMentoria.getMentor().getNivelSenioridade()
        + " | Skills: " + trilhaMentoria.getMentor().getSkills());

        System.out.println("\nMentorados:");
        trilhaMentoria.getMentorados().forEach(mentorado -> System.out.println(
                "- " + mentorado.getNome()
        +" | Senioridade: " + mentorado.getNivelSenioridade()
        +" | Skills desejadas: " + mentorado.getSkillsDesejadas()
        ));

        System.out.println("\nHabilidades que serão ensinadas:");
        trilhaMentoria.getSkillsDaTrilha().forEach(skill -> System.out.println("- " + skill));

        System.out.printf("%nCusto de oportunidade mensal: R$ %.2f%n", trilhaMentoria.calcularCustoMensalTotal());
        System.out.printf("Custo de oportunidade total do ciclo: R$ %.2f%n", trilhaMentoria.calcularCustoTotalDoCiclo());

        System.out.println("--------------------------------------------------\n");
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

    @Override
    public Optional<TrilhaMentoria> buscarTrilhaId(long id) {
        return trilhaDAO.buscarTrilhaId(id);
    }

    @Override
    public void editarTrilha(TrilhaMentoria trilhaMentoria) {
        trilhaDAO.update(trilhaMentoria);
    }

    @Override
    public boolean excluirTrilha(long id) {
        return trilhaDAO.remover(id);
    }
}
