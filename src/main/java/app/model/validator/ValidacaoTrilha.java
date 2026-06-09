package app.model.validator;

import app.exception.CargaHorariaExcedidaException;
import app.exception.MaximoMentoradosAtingidosException;
import app.exception.NivelDesproporcionalException;
import app.exception.SkillIncompativelException;
import app.model.entity.Mentor;
import app.model.entity.Mentorado;
import app.model.entity.TrilhaMentoria;
import app.model.enums.NivelSenioridade;

public class ValidacaoTrilha {

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

    public void validarSkills(TrilhaMentoria trilha) {
        Mentor mentor = trilha.getMentor();

        trilha.getMentorados().forEach(mentorado -> {
            long skillsCompativeis = mentorado.getSkillsDesejadas()
                    .stream()
                    .filter(skill -> mentor.getSkills().contains(skill))
                    .count();

            double percentual = (double) skillsCompativeis / mentorado.getSkillsDesejadas().size();

            if (percentual < 0.7) {
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