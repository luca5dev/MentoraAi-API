package app.dao;

import app.model.entity.Mentor;
import app.model.entity.Mentorado;
import app.model.entity.ParticipantePrograma;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class ParticipanteDAO {
    private EntityManagerFactory emf = createEntityManagerFactory("entityManager");
    private EntityManager em = emf.createEntityManager();

    public ParticipanteDAO() {

    }

    public void persist(Object entidade) {
        em.getTransaction().begin();
        em.persist(entidade);
        em.getTransaction().commit();
    }

    public void update(Object entidade) {
        em.getTransaction().begin();
        em.merge(entidade);
        em.getTransaction().commit();
    }

    public void close() {
        em.close();
        emf.close();
    }

    public void listarParticipantes() {

        List<ParticipantePrograma> participantes = em.createQuery("SELECT s FROM ParticipantePrograma s ORDER BY s.id ASC", ParticipantePrograma.class)
                .getResultList();

        if (!participantes.isEmpty()) {
            System.out.println("Lista de Participantes");
            for (ParticipantePrograma p : participantes) {
                System.out.println(p.getId());
                System.out.println(p.getNome());
                System.out.println(p.getSkills());
            }
        } else {
            System.out.println("Não há participantes cadastrados");
        }
    }

    public void listarMentores() {
        List<Mentor> mentores = em.createQuery("SELECT m FROM Mentor m ORDER BY m.id ASC ", Mentor.class).getResultList();

        if (!mentores.isEmpty()) {
            System.out.println("Mentores: ");
            for (Mentor m : mentores) {
                System.out.println(m.getId());
                System.out.println(m.getNome());
                System.out.println(m.getSkills());
                System.out.println(m.getNivelSenioridade());
            }
        } else {
            System.out.println("Não há mentores cadastrados");
        }
    }

    public void listarMentorados() {
        List<Mentorado> mentorados = em.createQuery("SELECT m FROM Mentorado m ORDER BY m.id ASC ", Mentorado.class).getResultList();

        if (mentorados != null) {
            if (mentorados.isEmpty()) {
                System.out.println("Não há mentorados cadastrados");
            } else {
                System.out.println("Mentorados: ");
                for (Mentorado m : mentorados) {
                    System.out.println("=========================");
                    System.out.println(m.getId());
                    System.out.println(m.getNome());
                    System.out.println(m.getSkills());
                    System.out.println(m.getNivelSenioridade());
                    System.out.println(m.getSkillsDesejadas());
                    System.out.println("=========================");
                }
            }

        }
    }

}
