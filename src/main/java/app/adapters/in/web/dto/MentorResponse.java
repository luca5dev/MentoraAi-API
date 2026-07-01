package app.adapters.in.web.dto;

import java.util.List;

public record MentorResponse(
        Long id,
        String nome,
        String nivelSenioridade,
        List<String> skills,
        Double valorHora,
        Double horasDedicadas,
        Integer maximoMentorados
) {
}