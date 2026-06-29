package app.adapters.out.jpa;

import app.adapters.out.jpa.entity.TrilhaJpaEntity;
import app.adapters.out.jpa.repository.TrilhaJpaRepository;
import app.core.entity.TrilhaMentoria;
import app.core.port.out.TrilhaRepository;

import java.util.Optional;

public class TrilhaRepositoryAdapter implements TrilhaRepository {

    private final TrilhaJpaRepository jpaRepository;

    public TrilhaRepositoryAdapter(TrilhaJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public TrilhaMentoria salvar( TrilhaMentoria trilha) {
        TrilhaJpaEntity entity = toEntity(trilha);

        TrilhaJpaEntity saved = jpaRepository.save(entity);

        return toDomain(saved);
    }

    @Override
    public Optional<TrilhaMentoria> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    private TrilhaJpaEntity toEntity(TrilhaMentoria domain) {
        TrilhaJpaEntity entity = new TrilhaJpaEntity();
        entity.setId(domain.getId());

        //falta mapear mentor e mentorados

        return domain;
    }
}
