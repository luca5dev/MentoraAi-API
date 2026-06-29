package app.core.port.out;

import app.core.entity.TrilhaMentoria;

import java.util.Optional;

public interface TrilhaRepository {

    TrilhaMentoria salvar(TrilhaMentoria trilha);

    Optional<TrilhaMentoria> buscarPorId(Long id);
}
