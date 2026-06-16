package app.dao.interfaces;

import app.model.entity.TrilhaMentoria;

import java.util.List;
import java.util.Optional;

public interface TrilhaDAO {

    boolean persist(TrilhaMentoria trilhaMentoria);

    void update(TrilhaMentoria trilhaMentoria);

    boolean remover(Long id);

    List<TrilhaMentoria> listarTrilhas();

    Optional<TrilhaMentoria> buscarTrilhaId(long id);
}
