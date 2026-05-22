package app.model.dto;

import app.model.entity.Mentor;
import app.model.entity.Mentorado;
import app.model.enums.Skill;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class TrilhaMentoriaDTO {

    private int idMentor;

    private String nome;

    private Integer duracaoMeses;

    private List<Skill> skills = new ArrayList<>();

    private Mentor mentor;

    private List<Mentorado> mentorados = new ArrayList<>();

    public TrilhaMentoriaDTO() {
    }


    public TrilhaMentoriaDTO(String nome, Integer duracaoMeses, List<Skill> skills, Mentor mentor, List<Mentorado> mentorados) {
        this.nome = nome;
        this.duracaoMeses = duracaoMeses;
        this.skills = skills;
        this.mentor = mentor;
        this.mentorados = mentorados;
    }

    public int getIdMentor() {
        return idMentor;
    }

    public void setIdMentor(int idMentor) {
        this.idMentor = idMentor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public List<Skill> getSkills() {
        return skills;
    }

    public void addMentorado(Mentorado mentorado){
        mentorados.add(mentorado);
   }

   public void addSkill(Skill skill){
        this.skills.add(skill);
   }


}
