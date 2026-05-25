package app.model.entity;

import app.model.enums.Skill;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class TrilhaMentoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeDaTrilha;
    private Integer duracaoMeses; //Duração do ciclo de mentoria, mencionado nas Premissas da US.

    @ManyToOne
    private Mentor mentor; //Muitas trilhas podem ter o mesmo mentor

    @OneToMany
    private List<Mentorado> mentorados; //Uma trilha possui vários mentorados

    @ElementCollection(targetClass = Skill.class)
    @Enumerated(EnumType.STRING)
    private List<Skill> skillsDaTrilha;

    private TrilhaMentoria() {}

    private TrilhaMentoria(String nomeDaTrilha, Integer duracaoMeses, Mentor mentor, List<Mentorado> mentorados, List<Skill> skillsDaTrilha) {
        this.nomeDaTrilha = nomeDaTrilha;
        this.duracaoMeses = duracaoMeses;
        this.mentor = mentor;
        this.mentorados = mentorados;
        this.skillsDaTrilha = skillsDaTrilha;
    }

    public Long getId() {
        return id;
    }

    public String getNomeDaTrilha() {
        return nomeDaTrilha;
    }

    public void setNomeDaTrilha(String nomeDaTrilha) {
        this.nomeDaTrilha = nomeDaTrilha;
    }

    public Integer getDuracaoMeses() {
        return duracaoMeses;
    }

    public void setDuracaoMeses(Integer duracaoMeses) {
        this.duracaoMeses = duracaoMeses;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    public List<Mentorado> getMentorados() {
        return mentorados;
    }

    public void setMentorados(List<Mentorado> mentorados) {
        this.mentorados = mentorados;
    }

    public List<Skill> getSkillsDaTrilha() {
        return skillsDaTrilha;
    }

    public void setSkillsDaTrilha(List<Skill> skillsDaTrilha) {
        this.skillsDaTrilha = skillsDaTrilha;
    }

    public void adicionarSkills(Skill skill){
        this.skillsDaTrilha.add(skill);
    }

    public void adicionarMentorado(Mentorado mentorado){
        this.mentorados.add(mentorado);
    }

    @Override
    public String toString() {
        return "TrilhaMentoria{" +
                "id=" + id +
                ", nomeDaTrilha='" + nomeDaTrilha + '\'' +
                ", duracaoMeses=" + duracaoMeses +
                ", mentor=" + mentor +
                ", skillsDaTrilha=" + skillsDaTrilha +
                ", mentorados=" + mentorados +
                '}';
    }
}
