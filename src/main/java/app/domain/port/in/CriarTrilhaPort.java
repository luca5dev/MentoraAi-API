package app.domain.port.in;

import app.domain.model.TrilhaMentoria;

public interface CriarTrilhaPort {

    TrilhaMentoria executar(CriarTrilhaDados dados);
}
