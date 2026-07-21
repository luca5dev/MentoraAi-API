package app.adapters.in.web;

import app.adapters.in.web.dto.DemonstracaoResponse;
import app.domain.model.DemonstracaoResultado;
import app.domain.port.in.ExecutarDemonstracaoPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demonstracao")
public class DemonstracaoController {

    private final ExecutarDemonstracaoPort executarDemonstracaoPort;

    public DemonstracaoController(ExecutarDemonstracaoPort executarDemonstracaoPort) {
        this.executarDemonstracaoPort = executarDemonstracaoPort;
    }

    @PostMapping("/automatica")
    @ResponseStatus(HttpStatus.OK)
    public DemonstracaoResponse executarDemostracao() {
        DemonstracaoResultado resultado = executarDemonstracaoPort.executar();

        return new DemonstracaoResponse(
                resultado.titulo(),
                resultado.mensagem(),
                resultado.cenarios().stream()
                        .map(cenario -> new DemonstracaoResponse.CenarioResponse(
                                cenario.numero(),
                                cenario.titulo(),
                                cenario.resultado(),
                                cenario.exception()
                        ))
                        .toList(),
                resultado.logs()
        );
    }
}
