package app.adapters.out.persistence.repository;

import app.domain.model.Mentor;
import app.domain.model.Mentorado;
import app.domain.model.ParticipantePrograma;
import app.domain.port.out.ParticipanteRepositoryPort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class ParticipantePersistenceAdapter implements ParticipanteRepositoryPort {

    private final ParticipanteJpaRepository participanteJpaRepository;
    private final ParticipanteJpaMapper mapper;

    public ParticipantePersistenceAdapter(ParticipanteJpaRepository participanteJpaRepository,
                                          ParticipanteJpaMapper mapper) {
        this.participanteJpaRepository = participanteJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public void persist(ParticipantePrograma participantePrograma) {
        var entity = mapper.toJpa(participantePrograma);
        var savedEntity = participanteJpaRepository.save(entity);
        participantePrograma.setId(savedEntity.getId());
    }

    @Override
    public void update(ParticipantePrograma participantePrograma) {
        var entity = mapper.toJpa(participantePrograma);
        var savedEntity = participanteJpaRepository.save(entity);
        participantePrograma.setId(savedEntity.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ParticipantePrograma> listarTodosParticipantes() {
        return participanteJpaRepository.findAll().stream()
                .map(mapper::toDomainParticipante)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ParticipantePrograma> buscarParticipantePorId(Long id) {
        return participanteJpaRepository.findById(id)
                .map(mapper::toDomainParticipante);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Mentor> buscarMentorPorId(Long id) {
        return participanteJpaRepository.findMentorById(id).map(mapper::toDomainMentor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Mentorado> buscarMentoradosPorIds(List<Long> ids) {
        return participanteJpaRepository.findMentoradosByIds(ids).stream()
                .map(mapper::toDomainMentorado)
                .toList();
    }
}
