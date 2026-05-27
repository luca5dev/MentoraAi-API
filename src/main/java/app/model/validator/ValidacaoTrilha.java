package app.model.validator;

import app.exception.CargaHorariaExcedidaException;
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

        if (totalHoras > 20.0) {
            throw new CargaHorariaExcedidaException ("Carga horária do mentor excedida!");
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
}