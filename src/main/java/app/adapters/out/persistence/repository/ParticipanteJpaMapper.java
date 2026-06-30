package app.adapters.out.persistence.repository;

import app.adapters.out.persistence.entity.MentorJpaEntity;
import app.adapters.out.persistence.entity.MentoradoJpaEntity;
import app.adapters.out.persistence.entity.ParticipanteJpaEntity;
import app.domain.model.Mentor;
import app.domain.model.Mentorado;
import app.domain.model.ParticipantePrograma;
import app.domain.model.Skill;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ParticipanteJpaMapper {

    public ParticipanteJpaEntity toJpa(ParticipantePrograma domain) {
        if (domain instanceof Mentor mentor) {
            MentorJpaEntity entity = new MentorJpaEntity();
            copyBaseToJpa(domain, entity);
            entity.setMaximoMentorados(mentor.getMaximoMentorados());
            return entity;
        }

        if (domain instanceof Mentorado mentorado) {
            MentoradoJpaEntity entity = new MentoradoJpaEntity();
            copyBaseToJpa(domain, entity);
            entity.setSkillsDesejadas(new ArrayList<>(mentorado.getSkillsDesejadas()));
            return entity;
        }

        throw new IllegalArgumentException("Tipo de participante não suportado: " + domain.getClass().getName());
    }

    public ParticipantePrograma toDomainParticipante(ParticipanteJpaEntity entity) {
        if (entity instanceof MentorJpaEntity mentorEntity) {
            return toDomainMentor(mentorEntity);
        }

        if (entity instanceof MentoradoJpaEntity mentoradoEntity) {
            return toDomainMentorado(mentoradoEntity);
        }

        throw new IllegalArgumentException("Tipo de entidade não suportado: " + entity.getClass().getName());
    }

    public Mentor toDomainMentor(MentorJpaEntity entity) {
        Mentor mentor = new Mentor();
        copyBaseToDomain(entity, mentor);
        mentor.setMaximoMentorados(entity.getMaximoMentorados());
        return mentor;
    }

    public Mentorado toDomainMentorado(MentoradoJpaEntity entity) {
        Mentorado mentorado = new Mentorado();
        copyBaseToDomain(entity, mentorado);
        List<Skill> skillsDesejadas = entity.getSkillsDesejadas() == null ? List.of() : entity.getSkillsDesejadas();
        for (Skill skill : skillsDesejadas) {
            if (!mentorado.getSkillsDesejadas().contains(skill)) {
                mentorado.adicionarSkillDesejada(skill);
            }
        }
        return mentorado;
    }

    private void copyBaseToJpa(ParticipantePrograma domain, ParticipanteJpaEntity entity) {
        entity.setId(domain.getId());
        entity.setNome(domain.getNome());
        entity.setNivelSenioridade(domain.getNivelSenioridade());
        entity.setSkills(new ArrayList<>(domain.getSkills()));
        entity.setValorHora(domain.getValorHora());
        entity.setHorasDedicadas(domain.getHorasDedicadas());
    }

    private void copyBaseToDomain(ParticipanteJpaEntity entity, ParticipantePrograma domain) {
        domain.setId(entity.getId());
        domain.setNome(entity.getNome());
        domain.setNivelSenioridade(entity.getNivelSenioridade());
        List<Skill> skills = entity.getSkills() == null ? List.of() : entity.getSkills();
        for (Skill skill : skills) {
            if (!domain.getSkills().contains(skill)) {
                domain.adicionarSkill(skill);
            }
        }
        domain.setValorHora(entity.getValorHora());
        domain.setHorasDedicadas(entity.getHorasDedicadas());
    }

    public MentorJpaEntity toJpaMentor(Mentor mentor) {
        return (MentorJpaEntity) toJpa(mentor);
    }

    public MentoradoJpaEntity toJpaMentorado(Mentorado mentorado) {
        return (MentoradoJpaEntity) toJpa(mentorado);
    }
}

