package app.domain.validator;

import app.domain.model.Mentor;
import app.domain.model.Mentorado;
import app.domain.model.NivelSenioridade;
import app.domain.model.TrilhaMentoria;
import app.exception.*;

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
            throw new CargaHorariaExcedidaException ("A soma das horas dos mentorados ultrapassa a capacidade mensal do mentor.");
        }
    }

    public void validarDuracao(TrilhaMentoria trilha) {
        Integer ciclo = trilha.getCicloEmMeses();
        if (ciclo == null || ciclo <= 0) {
            throw new NumeroForaDoIntervaloException("A duração da trilha deve ser maior que zero.");
        }
    }

    public void validarSkills(TrilhaMentoria trilha) {
        Mentor mentor = trilha.getMentor();

        trilha.getMentorados().forEach(mentorado -> {
            long skillsCompativeis = mentorado.getSkillsDesejadas()
                    .stream()
                    .filter(skill -> mentor.getSkills().contains(skill))
                    .count();

            double percentual = (double) skillsCompativeis / mentorado.getSkillsDesejadas().size();

            if (percentual < PERCENTUAL_MINIMO_SKILL) {
                throw new SkillIncompativelException("Compatibilidade de skills menor que 70%");
            }
        });
    }

    public void validarSenioridade(TrilhaMentoria trilha) {
        NivelSenioridade mentorSenioridade = trilha.getMentor().getNivelSenioridade();

        boolean invalido = trilha.getMentorados().stream()
                .anyMatch(mentorado -> mentorSenioridade.getId() <= mentorado.getNivelSenioridade().getId());

        if (invalido) {
            throw new NivelDesproporcionalException("O mentor deve ter senioridade superior a todos os mentorados.");
        }
    }

    public void validarQuantidadeMentorados(TrilhaMentoria trilha) {
        long quantidadeMentorados = trilha.getMentorados().stream().count();

        if (quantidadeMentorados > trilha.getMentor().getMaximoMentorados()) {
            throw new MaximoMentoradosAtingidosException("Máximo de mentorados atingido para este mentor.");
        }
    }
}