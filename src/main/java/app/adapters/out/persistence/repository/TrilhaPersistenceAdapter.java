package app.adapters.out.persistence.repository;

import app.adapters.out.persistence.entity.TrilhaJpaEntity;
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
    private final ParticipanteJpaRepository participanteJpaRepository;

    public TrilhaPersistenceAdapter(TrilhaJpaRepository trilhaJpaRepository,
                                    TrilhaJpaMapper trilhaJpaMapper,
                                    ParticipanteJpaRepository participanteJpaRepository) {
        this.trilhaJpaRepository = trilhaJpaRepository;
        this.trilhaJpaMapper = trilhaJpaMapper;
        this.participanteJpaRepository = participanteJpaRepository;
    }

    @Override
    @Transactional
    public void persist(TrilhaMentoria trilha) {
        var entity = trilhaJpaMapper.toJpa(trilha);
        vincularEntidadesGerenciadas(entity, trilha);
        var savedEntity = trilhaJpaRepository.save(entity);
        trilha.setId(savedEntity.getId());
    }

    @Override
    @Transactional
    public void update(TrilhaMentoria trilha) {
        var entity = trilhaJpaMapper.toJpa(trilha);
        vincularEntidadesGerenciadas(entity, trilha);
        var savedEntity = trilhaJpaRepository.save(entity);
        trilha.setId(savedEntity.getId());
    }

    @Override
    public void delete(Long id) {
        trilhaJpaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrilhaMentoria> listarTodasTrilhas() {
        return trilhaJpaRepository.findAll().stream()
                .map(trilhaJpaMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TrilhaMentoria> buscarPorId(Long id) {
        return trilhaJpaRepository.findById(id)
                .map(trilhaJpaMapper::toDomain);
    }

    private void vincularEntidadesGerenciadas(TrilhaJpaEntity entity, TrilhaMentoria trilha) {
        if (trilha.getMentor() != null && trilha.getMentor().getId() != null) {
            var mentorGerenciado = participanteJpaRepository
                    .findMentorById(trilha.getMentor().getId())
                    .orElseThrow(() -> new IllegalStateException(
                            "Mentor não encontrado: " + trilha.getMentor().getId()));
            entity.setMentor(mentorGerenciado);
        }

        if (trilha.getMentorados() != null && !trilha.getMentorados().isEmpty()) {
            var ids = trilha.getMentorados().stream()
                    .map(app.domain.model.Mentorado::getId)
                    .toList();
            var mentoradosGerenciados = participanteJpaRepository.findMentoradosByIds(ids);
            entity.setMentorados(new java.util.ArrayList<>(mentoradosGerenciados));
        }
    }

}


