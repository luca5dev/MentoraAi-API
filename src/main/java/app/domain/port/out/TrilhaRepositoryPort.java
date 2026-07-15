package app.domain.port.out;

import app.domain.model.TrilhaMentoria;

import java.util.List;
import java.util.Optional;

public interface TrilhaRepositoryPort {

    void persist(TrilhaMentoria trilhaMentoria);

    void update(TrilhaMentoria trilhaMentoria);

    void delete(Long id);

    List<TrilhaMentoria> listarTodasTrilhas();

    Optional<TrilhaMentoria> buscarPorId(Long id);

    boolean existeTrilhaComMentor(Long mentorId);
}
