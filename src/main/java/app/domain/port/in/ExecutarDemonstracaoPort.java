package app.domain.port.in;

import app.adapters.in.web.dto.DemonstracaoResponse;

public interface ExecutarDemonstracaoPort {
    DemonstracaoResponse executar();
}
