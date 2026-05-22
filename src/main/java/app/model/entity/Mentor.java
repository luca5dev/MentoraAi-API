package app.model.entity;

import app.model.enums.NivelSenioridade;
import app.model.enums.Skill;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Mentor extends ParticipantePrograma {

    private Integer maximoMentorados;

    public Mentor() {}

    public Mentor(String nome,
                  NivelSenioridade nivelSenioridade,
                  List<Skill> skills,
                  Double valorHora,
                  Double horasDedicadas) {

        super(nome, nivelSenioridade, skills, valorHora, horasDedicadas);

        this.horasDedicadas = 20.0;
        this.maximoMentorados = 4;
    }

    @Override
    public Double calcularCustoOportunidadeMensal() { //Calcula quanto vai custar o mentor por mês, considerando o valor hora e a carga horária máxima, mesmo que ele não atinja esse limite, o custo de oportunidade é calculado com base na carga horária máxima, porque é o tempo que ele está se dedicando ao programa
        return getValorHora() * horasDedicadas;
    }

    public Integer getMaximoMentorados() {
        return maximoMentorados;
    }

    public void setMaximoMentorados(Integer maximoMentorados) {
        this.maximoMentorados = maximoMentorados;
    }
}

