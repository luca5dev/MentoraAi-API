package app.model.entity;

import app.model.NivelSenioridade;
import app.model.Skill;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.List;

@Entity
public class Mentorado extends ParticipantePrograma {

    private Integer horasMentoriaMensal; //Será usada para calcular o custo de oportunidade do mentorado, multiplicando pelo valor hora, e também para validar a regra de negócio de trava de carga horária

    @ElementCollection(targetClass = Skill.class)
    @Enumerated(EnumType.STRING)
    private List<Skill> skillsDesejadas; //Vamos usar na validação de compatibilidade técnica (o que um mentor ensina e o que o mentorado quer aprender)

    public Mentorado() {}

    public Mentorado(String nome,
                     NivelSenioridade nivelSenioridade,
                     List<Skill> skills,
                     Double valorHora,
                     Integer horasMentoriaMensal,
                     List<Skill> skillsDesejadas) {
        super(nome, nivelSenioridade, skills, valorHora);

        this.horasMentoriaMensal = horasMentoriaMensal;
        this.skillsDesejadas = skillsDesejadas;
    }

    @Override
    public Double calcularCustoOportunidadeMensal() { //Aqui representa o custo de oportunidade para a empresa. Valor da hora do funcionário * horas que ele passará em treinamento
        return getValorHora() * horasMentoriaMensal;
    }

    public List<Skill> getSkillsDesejadas() {
        return skillsDesejadas;
    }

    public void setSkillsDesejadas(List<Skill> skillsDesejadas) {
        this.skillsDesejadas = skillsDesejadas;
    }

    public Integer getHorasMentoriaMensal() {
        return horasMentoriaMensal;
    }

    public void setHorasMentoriaMensal(Integer horasMentoriaMensal) {
        this.horasMentoriaMensal = horasMentoriaMensal;
    }
}
