package app.domain.model;

import app.config.MensagensLogger;
import app.domain.exception.CampoVazioException;
import app.domain.exception.CargaHorariaExcedidaException;

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
        this(nome, nivelSenioridade, skills, valorHora, HORAS_MAXIMAS_MENTOR);
    }

    public Mentor(String nome,
                  NivelSenioridade nivelSenioridade,
                  List<Skill> skills,
                  Double valorHora,
                  Double horasDedicadas) {

        super(nome, nivelSenioridade, skills, valorHora, validarHorasDedicadas(horasDedicadas));
        this.maximoMentorados = MAXIMO_MENTORADOS;
    }

    private static Double validarHorasDedicadas(Double horasDedicadas) {
        if (horasDedicadas == null) {
            throw new CampoVazioException(MensagensLogger.HORAS_MENTOR_OBRIGATORIAS);
        }

        if (horasDedicadas <= 0) {
            throw new CampoVazioException(MensagensLogger.HORAS_MENTOR_INVALIDAS);
        }

        if (horasDedicadas > HORAS_MAXIMAS_MENTOR) {
            throw new CargaHorariaExcedidaException(MensagensLogger.HORAS_MENTOR_EXCEDIDAS);
        }

        return horasDedicadas;
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
