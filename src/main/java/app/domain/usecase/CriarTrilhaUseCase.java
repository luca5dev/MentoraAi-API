package app.domain.usecase;

import app.domain.model.Mentor;
import app.domain.model.Mentorado;
import app.domain.model.Skill;
import app.domain.model.TrilhaMentoria;
import app.domain.port.in.CriarTrilhaDados;
import app.domain.port.in.CriarTrilhaPort;
import app.domain.port.out.ParticipanteRepositoryPort;
import app.domain.port.out.TrilhaRepositoryPort;
import app.domain.validator.ValidadorTrilhaDomain;
import app.domain.exception.ListaVaziaException;
import app.domain.exception.ParticipanteNaoEncontradoException;

import java.util.List;

public class CriarTrilhaUseCase implements CriarTrilhaPort {

    private final ParticipanteRepositoryPort participanteRepositoryPort;
    private final TrilhaRepositoryPort trilhaRepositoryPort;
    private final ValidadorTrilhaDomain validadorTrilhaDomain;

    public CriarTrilhaUseCase(ParticipanteRepositoryPort participanteRepositoryPort,
                              TrilhaRepositoryPort trilhaRepositoryPort,
                              ValidadorTrilhaDomain validadorTrilhaDomain) {
        this.participanteRepositoryPort =  participanteRepositoryPort;
        this.trilhaRepositoryPort = trilhaRepositoryPort;
        this.validadorTrilhaDomain = validadorTrilhaDomain;
    }

    @Override
    public TrilhaMentoria executar(CriarTrilhaDados dados) {
        Mentor mentor = participanteRepositoryPort.buscarMentorPorId(dados.mentorId())
                .orElseThrow(() -> new ParticipanteNaoEncontradoException("Mentor não encontrado: " + dados.mentorId()));

        List<Mentorado> mentorados = participanteRepositoryPort.buscarMentoradosPorIds(dados.mentoradosIds());

        if (mentorados == null || mentorados.isEmpty()) {
            throw new ListaVaziaException("É necessário informar ao menos um mentorado para a trilha.");
        }

        TrilhaMentoria trilha = new TrilhaMentoria();
        trilha.setNomeDaTrilha(dados.nomeDaTrilha());
        trilha.setCicloEmMeses(dados.cicloEmMeses());
        trilha.setMentor(mentor);
        trilha.setMentorados(mentorados);

        List<Skill> skillsEnsinadas = mentorados.stream()
                .flatMap(m -> m.getSkillsDesejadas().stream())
                .distinct()
                .filter(skill -> mentor.getSkills().contains(skill))
                .toList();
        trilha.setSkillsDaTrilha(skillsEnsinadas);

        validadorTrilhaDomain.validarTudo(trilha);

        trilhaRepositoryPort.persist(trilha);
        return trilha;
    }
}
