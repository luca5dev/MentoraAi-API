package app.adapters.out.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "mentor")
@PrimaryKeyJoinColumn(name = "id")
public class MentorJpaEntity extends ParticipanteJpaEntity {

    private Integer maximoMentorados;

    public Integer getMaximoMentorados() {
        return maximoMentorados;
    }

    public void setMaximoMentorados(Integer maximoMentorados) {
        this.maximoMentorados = maximoMentorados;
    }
}
