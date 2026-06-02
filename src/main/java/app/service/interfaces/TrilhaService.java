package app.service.interfaces;

import app.model.dto.TrilhaMentoriaDTO;
import app.model.entity.TrilhaMentoria;

import java.util.List;

public interface TrilhaService {
    void cadastrar(TrilhaMentoriaDTO dto);
    List<TrilhaMentoria> listarTrilhasPersistidas();

    void persistirJaValidada(TrilhaMentoria trilhaMentoria); //Apenas para a demonstração do AC6
}
