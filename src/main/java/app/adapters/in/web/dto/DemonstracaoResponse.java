package app.adapters.in.web.dto;

import java.util.List;

public record DemonstracaoResponse(
        String titulo,
        String mensagem,
        List<CenarioResponse> cenarios,
        List<String> logs
) {
    public record CenarioResponse(
            int numero,
            String titulo,
            String resultado,
            String exception
    ){}
}
