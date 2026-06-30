package app.adapters.out.persistence.repository;

import app.domain.model.Mentor;
import app.domain.model.Mentorado;
import app.domain.model.ParticipantePrograma;
import app.domain.port.out.ParticipanteRepositoryPort;
import org.springframework.stereotype.Component;

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
        participanteJpaRepository.save(entity);
    }

    @Override
    public void update(ParticipantePrograma participantePrograma) {
        var entity = mapper.toJpa(participantePrograma);
        participanteJpaRepository.save(entity);
    }

    @Override
    public List<ParticipantePrograma> listarTodosParticipantes() {
        return participanteJpaRepository.findAll().stream()
                .map(mapper::toDomainParticipante)
                .toList();
    }

    @Override
    public Optional<ParticipantePrograma> buscarParticipantePorId(Long id) {
        return participanteJpaRepository.findById(id)
                .map(mapper::toDomainParticipante);
    }

    @Override
    public Optional<Mentor> buscarMentorPorId(Long id) {
        return participanteJpaRepository.findMentorById(id).map(mapper::toDomainMentor);
    }

    @Override
    public List<Mentorado> buscarMentoradosPorIds(List<Long> ids) {
        return participanteJpaRepository.findMentoradosByIds(ids).stream()
                .map(mapper::toDomainMentorado)
                .toList();
    }
}
