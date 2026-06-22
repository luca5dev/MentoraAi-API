package app.model.dto;

import app.model.entity.Mentor;
import app.model.enums.Skill;

import java.util.ArrayList;
import java.util.List;

public class TrilhaMentoriaDTO {

    private Long idMentor;
    private String nome;
    private Integer cicloEmMeses;
    private List<Skill> skills = new ArrayList<>();
    private Mentor mentor;
    private List<Long> idsMentorados = new ArrayList<>();

    public TrilhaMentoriaDTO() {}

    public TrilhaMentoriaDTO(String nome, Integer cicloEmMeses, List<Skill> skills, Mentor mentor, List<Long> idsMentorados) {
        this.nome = nome;
        this.cicloEmMeses = cicloEmMeses;
        this.skills = skills;
        this.mentor = mentor;
        this.idsMentorados = idsMentorados;
    }

    public Long getIdMentor() {
        return idMentor;
    }

    public void setIdMentor(Long idMentor) {
        this.idMentor = idMentor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCicloEmMeses() {
        return cicloEmMeses;
    }

    public void setCicloEmMeses(Integer cicloEmMeses) {
        this.cicloEmMeses = cicloEmMeses;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    public List<Long> getIdsMentorados() {
        return idsMentorados;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = new ArrayList<>(skills);
    }

    public void addIdMentorado(Long idMentorado){
        this.idsMentorados.add(idMentorado);
   }
}
