package app.adapters.in.web.dto;

import java.util.List;

public record MentoradoResponse(
        Long id,
        String name,
        String nivelSenioridade,
        List<String> skills,
        Double valorHora,
        Double horasDedicadas,
        List<String> skillsDesejadas
) {
}
