package app.domain.usecase;

import app.domain.model.TrilhaMentoria;
import app.domain.port.in.ListarTrilhasPort;
import app.domain.port.out.TrilhaRepositoryPort;

import java.util.List;

public class ListarTrilhasUseCase implements ListarTrilhasPort {

    private final TrilhaRepositoryPort trilhaRepositoryPort;

    public ListarTrilhasUseCase(TrilhaRepositoryPort trilhaRepositoryPort) {
        this.trilhaRepositoryPort = trilhaRepositoryPort;
    }

    @Override
    public List<TrilhaMentoria> listar() {
        return trilhaRepositoryPort.listarTodasTrilhas();
    }
}
