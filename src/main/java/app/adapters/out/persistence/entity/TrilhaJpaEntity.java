package app.adapters.out.persistence.entity;

import app.domain.model.Skill;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trilha_mentoria")
public class TrilhaJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeDaTrilha;
    private Integer cicloEmMeses;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private MentorJpaEntity mentor;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "trilha_id")
    private List<MentoradoJpaEntity> mentorados = new ArrayList<>();

    @ElementCollection(targetClass = Skill.class)
    @Enumerated(EnumType.STRING)
    private List<Skill> skillsDaTrilha = new ArrayList<>();

    public TrilhaJpaEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeDaTrilha() {
        return nomeDaTrilha;
    }

    public void setNomeDaTrilha(String nomeDaTrilha) {
        this.nomeDaTrilha = nomeDaTrilha;
    }

    public Integer getCicloEmMeses() {
        return cicloEmMeses;
    }

    public void setCicloEmMeses(Integer cicloEmMeses) {
        this.cicloEmMeses = cicloEmMeses;
    }

    public MentorJpaEntity getMentor() {
        return mentor;
    }

    public void setMentor(MentorJpaEntity mentor) {
        this.mentor = mentor;
    }

    public List<MentoradoJpaEntity> getMentorados() {
        return mentorados;
    }

    public void setMentorados(List<MentoradoJpaEntity> mentorados) {
        this.mentorados = mentorados;
    }

    public List<Skill> getSkillsDaTrilha() {
        return skillsDaTrilha;
    }

    public void setSkillsDaTrilha(List<Skill> skillsDaTrilha) {
        this.skillsDaTrilha = skillsDaTrilha;
    }
}
