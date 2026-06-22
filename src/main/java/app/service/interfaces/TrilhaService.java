package app.service.interfaces;

import app.model.dto.TrilhaMentoriaDTO;
import app.model.entity.TrilhaMentoria;

import java.util.List;
import java.util.Optional;

public interface TrilhaService {

    void cadastrar(TrilhaMentoriaDTO dto);

    List<TrilhaMentoria> listarTrilhasPersistidas();

    Optional<TrilhaMentoria> buscarTrilhaId(long id);

    void editarTrilha(TrilhaMentoria trilhaMentoria);

    boolean excluirTrilha(long id);

    void persistirJaValidada(TrilhaMentoria trilhaMentoria); //Apenas para a demonstração do AC6
}
