package app.adapters.out.persistence.repository;

import app.domain.model.TrilhaMentoria;
import app.domain.port.out.TrilhaRepositoryPort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class TrilhaPersistenceAdapter implements TrilhaRepositoryPort {

    private final TrilhaJpaRepository trilhaJpaRepository;
    private final TrilhaJpaMapper trilhaJpaMapper;

    public TrilhaPersistenceAdapter(TrilhaJpaRepository trilhaJpaRepository,
                                    TrilhaJpaMapper trilhaJpaMapper) {
        this.trilhaJpaRepository = trilhaJpaRepository;
        this.trilhaJpaMapper = trilhaJpaMapper;
    }

    @Override
    public void persist(TrilhaMentoria trilha) {
        var entity = trilhaJpaMapper.toJpa(trilha);
        trilhaJpaRepository.save(entity);
    }

    @Override
    public void update(TrilhaMentoria trilha) {
        var entity = trilhaJpaMapper.toJpa(trilha);
        trilhaJpaRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        trilhaJpaRepository.deleteById(id);
    }

    @Override
    public List<TrilhaMentoria> listarTodasTrilhas() {
        return trilhaJpaRepository.findAll().stream()
                .map(trilhaJpaMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<TrilhaMentoria> buscarPorId(Long id) {
        return trilhaJpaRepository.findById(id)
                .map(trilhaJpaMapper::toDomain);
    }

     @Override
     @Transactional
     public void limparMentoradosOrfaos() {
         trilhaJpaRepository.limparMentoradosOrfaos();
     }
}


