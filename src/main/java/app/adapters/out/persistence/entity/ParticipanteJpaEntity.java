package app.adapters.out.persistence.entity;

import app.domain.model.NivelSenioridade;
import app.domain.model.Skill;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "participanteprograma")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ParticipanteJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Enumerated(EnumType.STRING)
    private NivelSenioridade nivelSenioridade;

    @ElementCollection(targetClass = Skill.class)
    @Enumerated(EnumType.STRING)
    private List<Skill> skills = new ArrayList<>();

    private Double valorHora;
    private Double horasDedicadas;

    public ParticipanteJpaEntity() {}

    public ParticipanteJpaEntity(String nome, NivelSenioridade nivelSenioridade,
                                 Double valorHora, Double horasDedicadas) {
        this.nome = nome;
        this.nivelSenioridade = nivelSenioridade;
        this.valorHora = valorHora;
        this.horasDedicadas = horasDedicadas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public NivelSenioridade getNivelSenioridade() {
        return nivelSenioridade;
    }

    public void setNivelSenioridade(NivelSenioridade nivelSenioridade) {
        this.nivelSenioridade = nivelSenioridade;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public Double getValorHora() {
        return valorHora;
    }

    public void setValorHora(Double valorHora) {
        this.valorHora = valorHora;
    }

    public Double getHorasDedicadas() {
        return horasDedicadas;
    }

    public void setHorasDedicadas(Double horasDedicadas) {
        this.horasDedicadas = horasDedicadas;
    }
}
