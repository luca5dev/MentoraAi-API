package app.domain.port.in;

import app.domain.model.NivelSenioridade;
import app.domain.model.Skill;

import java.util.List;

public record CadastrarMentoradoDados(
        String nome,
        NivelSenioridade nivelSenioridade,
        List<Skill> skills,
        Double valorHora,
        Double horasDedicadas,
        List<Skill> skillsDesejadas
) {
}
