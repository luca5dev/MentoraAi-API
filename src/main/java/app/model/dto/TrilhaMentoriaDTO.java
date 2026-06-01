package app.model.dto;

import app.model.entity.Mentor;
import app.model.entity.Mentorado;
import app.model.enums.Skill;

import java.util.ArrayList;
import java.util.List;

public class TrilhaMentoriaDTO {

    private Long idMentor;

    private String nome;

    private Integer duracaoHoras;

    private List<Skill> skills = new ArrayList<>();

    private Mentor mentor;

    private List<Long> idsMentorados = new ArrayList<>();

    public TrilhaMentoriaDTO() {
    }


    public TrilhaMentoriaDTO(String nome, Integer duracaoMeses, List<Skill> skills, Mentor mentor, List<Long> idsMentorados) {
        this.nome = nome;
        this.duracaoHoras = duracaoMeses;
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

    public Integer getDuracaoHoras() {
        return duracaoHoras;
    }

    public void setDuracaoHoras(Integer duracaoHoras) {
        this.duracaoHoras = duracaoHoras;
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

    public void addIdMentorado(Long idMentorado){
        this.idsMentorados.add(idMentorado);
   }


}
