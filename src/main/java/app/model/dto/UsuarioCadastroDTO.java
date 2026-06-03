package app.model.dto;

import app.model.enums.NivelSenioridade;
import app.model.enums.Skill;

import java.util.ArrayList;
import java.util.List;

public class UsuarioCadastroDTO {

    private int opcao;
    private String nome;
    private NivelSenioridade nivelSenioridade;
    private List<Skill> skills = new ArrayList<>();
    private List<Skill> skillsDesejadas = new ArrayList<>();
    private Double valorHora;
    private Double horasDedicadas;

    public UsuarioCadastroDTO() {
    }

    public int getOpcao() {
        return opcao;
    }

    public void setOpcao(int opcao) {
        this.opcao = opcao;
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

    public void adicionarSkill(Skill skill){
        this.skills.add(skill);
    }

    public void adicionarSkillDesejada(Skill skill){
        this.skillsDesejadas.add(skill);
    }

    public List<Skill> getSkillsDesejadas() {
        return skillsDesejadas;
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
