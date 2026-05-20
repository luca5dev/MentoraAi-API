package app.model.entity;

import app.model.NivelSenioridade;
import app.model.Skill;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Mentor extends ParticipantePrograma {

    private Integer cargaHorariaMaxima; //Vamos usar esse limite para validar a regra de negócio de trava de carga horária
    private Integer maximoMentorados; //Vamos usar esse limite para fixar um número realista de mentorados por mentor

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
    public Double calcularCustoOportunidadeMensal() { //Calcula quanto vai custar o mentor por mês, considerando o valor hora e a carga horária máxima, mesmo que ele não atinja esse limite, o custo de oportunidade é calculado com base na carga horária máxima, porque é o tempo que ele está se dedicando ao programa
        return getValorHora() * cargaHorariaMaxima;
    }

    public Integer getCargaHorariaMaxima() {
        return cargaHorariaMaxima;
    }

    public void setCargaHorariaMaxima() {
        this.cargaHorariaMaxima = cargaHorariaMaxima;
    }

    public Integer getMaximoMentorados() {
        return maximoMentorados;
    }

    public void setMaximoMentorados(Integer maximoMentorados) {
        this.maximoMentorados = maximoMentorados;
    }
}

