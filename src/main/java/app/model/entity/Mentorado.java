package app.model.entity;

import app.exception.CampoVazioException;
import app.exception.SkillDuplicadaException;
import app.model.enums.NivelSenioridade;
import app.model.enums.Skill;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.List;

@Entity
public class Mentorado extends ParticipantePrograma {

    @ElementCollection(targetClass = Skill.class)
    @Enumerated(EnumType.STRING)
    private List<Skill> skillsDesejadas;

    public Mentorado() {}

    public Mentorado(String nome,
                     NivelSenioridade nivelSenioridade,
                     List<Skill> skills,
                     Double valorHora,
                     Double horasDedicadas,
                     List<Skill> skillsDesejadas) {
        super(nome, nivelSenioridade, skills, valorHora, horasDedicadas);
        this.skillsDesejadas = skillsDesejadas;
    }

    @Override
    public Double calcularCustoOportunidadeMensal() { //Aqui representa o custo de oportunidade para a empresa. Valor da hora do funcionário * horas que ele passará em treinamento
        return getValorHora() * getHorasDedicadas();
    }

    public List<Skill> getSkillsDesejadas() {
        return skillsDesejadas;
    }

    public void adicionarSkillDesejada(Skill skill) {
        if (this.skillsDesejadas.contains(skill)) {
            throw new SkillDuplicadaException("Skill já adicionada.");
        }
        this.skillsDesejadas.add(skill);
    }

    @Override
    public String toString() {
        return "Mentorado{" +
                "skillsDesejadas=" + skillsDesejadas +
                '}';
    }
}
