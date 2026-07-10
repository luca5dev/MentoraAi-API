package app.domain.port.in;

import java.util.List;

public record CriarTrilhaDados(
        String nomeDaTrilha,
        Integer cicloEmMeses,
        Long mentorId,
        List<Long> mentoradosIds
) {
}
