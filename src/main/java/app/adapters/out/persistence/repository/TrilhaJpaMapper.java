package app.adapters.out.persistence.repository;

import app.adapters.out.persistence.entity.MentoradoJpaEntity;
import app.adapters.out.persistence.entity.TrilhaJpaEntity;
import app.domain.model.Mentorado;
import app.domain.model.TrilhaMentoria;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TrilhaJpaMapper {

    private final ParticipanteJpaMapper participanteJpaMapper;

    public TrilhaJpaMapper(ParticipanteJpaMapper participanteJpaMapper) {
        this.participanteJpaMapper = participanteJpaMapper;
    }

    public TrilhaJpaEntity toJpa(TrilhaMentoria domain) {
        TrilhaJpaEntity entity = new TrilhaJpaEntity();
        entity.setId(domain.getId());
        entity.setNomeDaTrilha(domain.getNomeDaTrilha());
        entity.setCicloEmMeses(domain.getCicloEmMeses());

        if (domain.getMentor() != null) {
            entity.setMentor(participanteJpaMapper.toJpaMentor(domain.getMentor()));
        }

        List<MentoradoJpaEntity> mentorados = domain.getMentorados() == null
                ? new ArrayList<>()
                : domain.getMentorados().stream()
                .map(participanteJpaMapper::toJpaMentorado)
                .toList();
        entity.setMentorados(mentorados);

        if (domain.getSkillsDaTrilha() != null) {
            entity.setSkillsDaTrilha(new ArrayList<>(domain.getSkillsDaTrilha()));
        }

        return entity;
    }

    public TrilhaMentoria toDomain(TrilhaJpaEntity entity) {
        TrilhaMentoria domain = new TrilhaMentoria();
        domain.setId(entity.getId());
        domain.setNomeDaTrilha(entity.getNomeDaTrilha());
        domain.setCicloEmMeses(entity.getCicloEmMeses());

        if (entity.getMentor() != null) {
            domain.setMentor(participanteJpaMapper.toDomainMentor(entity.getMentor()));
        }

        List<Mentorado> mentorados = entity.getMentorados() == null
                ? new ArrayList<>()
                : entity.getMentorados().stream()
                .map(participanteJpaMapper::toDomainMentorado)
                .toList();
        domain.setMentorados(mentorados);

        if (entity.getSkillsDaTrilha() != null) {
            domain.setSkillsDaTrilha(new ArrayList<>(entity.getSkillsDaTrilha()));
        }

        return domain;
    }
}

