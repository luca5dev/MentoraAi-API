package app.adapters.out.persistence.entity;

import app.domain.model.Skill;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mentorado")
@PrimaryKeyJoinColumn(name = "id")
public class MentoradoJpaEntity extends ParticipanteJpaEntity {

    @ElementCollection(targetClass = Skill.class)
    @Enumerated(EnumType.STRING)
    private List<Skill> skillsDesejadas = new ArrayList<>();

    public List<Skill> getSkillsDesejadas() {
        return skillsDesejadas;
    }

    public void setSkillsDesejadas(List<Skill> skillsDesejadas) {
        this.skillsDesejadas = skillsDesejadas;
    }
}
