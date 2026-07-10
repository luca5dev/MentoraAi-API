package app.adapters.in.web.dto;

import java.util.List;

public record TrilhaResponse(
      Long id,
      String nome,
      Integer cicloEmMeses,
      Long idMentor,
      List<Long> idsMentorados,
      List<String> skills,
      Double custoMensalTotal,
      Double custoTotalDoCiclo
) {
}
