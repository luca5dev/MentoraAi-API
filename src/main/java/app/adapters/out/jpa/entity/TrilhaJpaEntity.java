package app.adapters.out.jpa.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TrilhaJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private MentorJpaEntity mentor;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "trilha_id")
    private List<MentoradoJpaEntity> mentorados = new ArrayList<>();

    public TrilhaJpaEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MentorJpaEntity getMentor() {
        return mentor;
    }

    public void setMentor(MentorJpaEntity mentor) {
        this.mentor = mentor;
    }

    public List<MentoradoJpaEntity> getMentorados() {
        return mentorados;
    }

    public void setMentorados(List<MentoradoJpaEntity> mentorados) {
        this.mentorados = mentorados;
    }
}