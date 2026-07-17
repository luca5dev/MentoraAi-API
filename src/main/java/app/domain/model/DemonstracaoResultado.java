package app.domain.model;

import java.util.List;

public record DemonstracaoResultado(
        String titulo,
        String mensagem,
        List<CenarioResultado> cenarios,
        List<String> logs
) {
    public record CenarioResultado(
            int numero,
            String titulo,
            String resultado,
            String exception
    ) {}
}
