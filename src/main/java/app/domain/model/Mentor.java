package app.domain.model;

import java.util.List;

public class Mentor extends ParticipantePrograma {

    private static final double HORAS_MAXIMAS_MENTOR = 20.0;
    private static final int MAXIMO_MENTORADOS = 4;

    private Integer maximoMentorados;

    public Mentor() {}

    public Mentor(String nome,
                  NivelSenioridade nivelSenioridade,
                  List<Skill> skills,
                  Double valorHora) {

        super(nome, nivelSenioridade, skills, valorHora, HORAS_MAXIMAS_MENTOR);
        this.maximoMentorados = MAXIMO_MENTORADOS;
    }

    @Override
    public Double calcularCustoOportunidadeMensal() {
        return getValorHora() * getHorasDedicadas() * getNivelSenioridade().getFatorCusto();
    }

    public Integer getMaximoMentorados() {
        return maximoMentorados;
    }

    public void setMaximoMentorados(Integer maximoMentorados) {
        this.maximoMentorados = maximoMentorados;
    }
}
