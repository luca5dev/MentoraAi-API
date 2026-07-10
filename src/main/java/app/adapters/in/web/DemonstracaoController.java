package app.adapters.in.web;

import app.adapters.in.web.dto.DemonstracaoResponse;
import app.domain.port.in.ExecutarDemonstracaoPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/automatica")
    @ResponseStatus(HttpStatus.OK)
    public DemonstracaoResponse executarDemostracao() {
        return executarDemonstracaoPort.executar();
    }
}
