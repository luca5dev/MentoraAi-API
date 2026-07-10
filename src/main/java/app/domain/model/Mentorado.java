package app.domain.model;

import app.domain.exception.CampoVazioException;
import app.domain.exception.SkillDuplicadaException;

import java.util.ArrayList;
import java.util.List;

public class Mentorado extends ParticipantePrograma {

    private List<Skill> skillsDesejadas = new ArrayList<>();
    private static final double HORAS_MINIMAS_MENTORADO = 1.0; //horasDedicadas

    public Mentorado() {}

    public Mentorado(String nome,
                     NivelSenioridade nivelSenioridade,
                     List<Skill> skills,
                     Double valorHora,
                     Double horasDedicadas,
                     List<Skill> skillsDesejadas) {
        super(nome, nivelSenioridade, skills, valorHora, validarHorasDedicadas(horasDedicadas));
        this.skillsDesejadas = skillsDesejadas != null ? new ArrayList<>(skillsDesejadas) : new ArrayList<>();
    }

    private static Double validarHorasDedicadas(Double horasDedicadas) {
        if (horasDedicadas == null) {
            throw new CampoVazioException("Horas dedicadas do mentorado não podem ser nulas.");
        }

        if (horasDedicadas < HORAS_MINIMAS_MENTORADO) {
            throw new CampoVazioException("As horas dedicadas do mentorado devem ser maiores que zero.");
        }
        return horasDedicadas;
    }
    /*
     *Aqui representa o custo de oportunidade para a empresa.
     * Valor da hora do funcionário * horasDedicadas
     */
    @Override
    public Double calcularCustoOportunidadeMensal() {
        return getValorHora() * getHorasDedicadas() * getNivelSenioridade().getFatorCusto();
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
