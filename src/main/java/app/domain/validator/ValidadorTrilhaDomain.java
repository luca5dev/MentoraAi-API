package app.domain.validator;

import app.config.MensagensLogger;
import app.domain.exception.*;
import app.domain.model.Mentor;
import app.domain.model.Mentorado;
import app.domain.model.NivelSenioridade;
import app.domain.model.TrilhaMentoria;

public class ValidadorTrilhaDomain {

    public static final double PERCENTUAL_MINIMO_SKILL = 0.70;

    public void validarTudo(TrilhaMentoria trilha) {
        validarCargaHoraria(trilha);
        validarDuracao(trilha);
        validarSkills(trilha);
        validarSenioridade(trilha);
        validarQuantidadeMentorados(trilha);
    }

    public void validarCargaHoraria(TrilhaMentoria trilha) {
        Mentor mentor = trilha.getMentor();

        double totalHoras = trilha.getMentorados()
                .stream()
                .mapToDouble(Mentorado::getHorasDedicadas)
                .sum();

        if (totalHoras > mentor.getHorasDedicadas()) {
            throw new CargaHorariaExcedidaException(MensagensLogger.CARGA_HORARIA_EXCEDIDA);
        }
    }

    public void validarDuracao(TrilhaMentoria trilha) {
        Integer ciclo = trilha.getCicloEmMeses();
        if (ciclo == null || ciclo <= 0) {
            throw new NumeroForaDoIntervaloException(MensagensLogger.DURACAO_TRILHA_INVALIDA);
        }
    }

    public void validarSkills(TrilhaMentoria trilha) {
        Mentor mentor = trilha.getMentor();

        trilha.getMentorados().forEach(mentorado -> {
            if (mentorado.getSkillsDesejadas().isEmpty()) {
                throw new SkillIncompativelException(MensagensLogger.COMPATIBILIDADE_SKILLS_INVALIDA);
            }

            long skillsCompativeis = mentorado.getSkillsDesejadas()
                    .stream()
                    .filter(skill -> mentor.getSkills().contains(skill))
                    .count();

            double percentual = (double) skillsCompativeis / mentorado.getSkillsDesejadas().size();

            if (percentual < PERCENTUAL_MINIMO_SKILL) {
                throw new SkillIncompativelException(MensagensLogger.COMPATIBILIDADE_SKILLS_INVALIDA);
            }
        });
    }

    public void validarSenioridade(TrilhaMentoria trilha) {
        NivelSenioridade mentorSenioridade = trilha.getMentor().getNivelSenioridade();

        boolean invalido = trilha.getMentorados().stream()
                .anyMatch(mentorado -> mentorSenioridade.getId() <= mentorado.getNivelSenioridade().getId());

        if (invalido) {
            throw new NivelDesproporcionalException(MensagensLogger.SENIORIDADE_MENTOR_INVALIDA);
        }
    }

    public void validarQuantidadeMentorados(TrilhaMentoria trilha) {
        long quantidadeMentorados = trilha.getMentorados().stream().count();

        if (quantidadeMentorados > trilha.getMentor().getMaximoMentorados()) {
            throw new MaximoMentoradosAtingidosException(MensagensLogger.MAXIMO_MENTORADOS_ATINGIDO);
        }
    }
}
