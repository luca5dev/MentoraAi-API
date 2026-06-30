package app.domain.port.in;

import app.domain.model.Skill;

import java.util.List;

public record CriarTrilhaDados(
        String nomeDaTrilha,
        Integer cicloEmMeses,
        Long mentorId,
        List<Long> mentoradosIds,
        List<Skill> skillsDaTrilha
) {
}
