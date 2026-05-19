package app.model;

import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Mentor extends ParticipantePrograma {
    private  Integer cargaHorariaMaxima;
    private Integer maximoMentorados;

    public Mentor() {}

    public Mentor(String nome,
                  NivelSenioridade nivelSenioridade,
                  List<Skill> skills,
                  Double valorHora,
                  Integer cargaHorariaMaxima,
                  Integer maximoMentorados) {

        super(nome, nivelSenioridade, skills, valorHora);

        this.cargaHorariaMaxima = cargaHorariaMaxima;
        this.maximoMentorados = maximoMentorados;
    }

    @Override
    public Double calcularCustoOportunidadeMensal() {
        return getValorHora() * cargaHorariaMaxima;
    }

    public Integer getCargaHorariaMaxima() {
        return cargaHorariaMaxima;
    }

    public Integer getMaximoMentorados() {
        return maximoMentorados;
    }

    public void setMaximoMentorados(Integer maximoMentorados) {
        this.maximoMentorados = maximoMentorados;
    }
}

