package app.domain.port.in;

import app.domain.model.TrilhaMentoria;

import java.util.List;

public interface ListarTrilhasPort {
   List<TrilhaMentoria> listar();
}
