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
    private Double valorHora;
    private Integer cargaHorariaMaxima; //Vamos usar esse limite para validar a regra de negócio de trava de carga horária
    private Integer maximoMentorados; //Vamos usar esse limite para fixar um número realista de mentorados por mentor

    public UsuarioCadastroDTO() {
    }

    public UsuarioCadastroDTO(String nome, NivelSenioridade nivelSenioridade, Double valorHora, Integer cargaHorariaMaxima, Integer maximoMentorados) {
        this.nome = nome;
        this.nivelSenioridade = nivelSenioridade;
        this.valorHora = valorHora;
        this.cargaHorariaMaxima = cargaHorariaMaxima;
        this.maximoMentorados = maximoMentorados;
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

    public Double getValorHora() {
        return valorHora;
    }

    public void setValorHora(Double valorHora) {
        this.valorHora = valorHora;
    }

    public Integer getCargaHorariaMaxima() {
        return cargaHorariaMaxima;
    }

    public void setCargaHorariaMaxima(Integer cargaHorariaMaxima) {
        this.cargaHorariaMaxima = cargaHorariaMaxima;
    }

    public Integer getMaximoMentorados() {
        return maximoMentorados;
    }

    public void setMaximoMentorados(Integer maximoMentorados) {
        this.maximoMentorados = maximoMentorados;
    }
}
