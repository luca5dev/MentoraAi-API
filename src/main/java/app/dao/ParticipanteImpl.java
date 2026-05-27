package app.dao;

import app.config.JPAUtil;
import app.model.entity.Mentor;
import app.model.entity.Mentorado;
import app.model.entity.ParticipantePrograma;
import app.dao.interfaces.ParticipanteDAO;
import jakarta.persistence.EntityManager;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;
import java.util.Optional;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class ParticipanteImpl implements ParticipanteDAO {


    @Override
    public void persist(ParticipantePrograma participantePrograma) {
        EntityManager em = JPAUtil.factory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(participantePrograma);
            em.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Erro na persistência" + e.getMessage() + ". Nenhuma alteração foi feita!");
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void update(ParticipantePrograma participantePrograma) {
        EntityManager em = JPAUtil.factory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(participantePrograma);
            em.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Erro na atualização: " + e.getMessage() + ". Nenhuma alteração foi feita!");
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<ParticipantePrograma> listarParticipantes() {
        EntityManager em = JPAUtil.factory().createEntityManager();
        try {
            return em.createQuery("SELECT s FROM ParticipantePrograma s ORDER BY s.id ASC", ParticipantePrograma.class)
                    .getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Mentor> listarMentores() {
        EntityManager em = JPAUtil.factory().createEntityManager();
        try {
            return em.createQuery("SELECT m FROM Mentor m ORDER BY m.id ASC ", Mentor.class).getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Mentorado> listarMentorados() {
        EntityManager em = JPAUtil.factory().createEntityManager();
        try {
            return em.createQuery("SELECT m FROM Mentorado m ORDER BY m.id ASC ", Mentorado.class).getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public Optional<Mentor> buscarMentorId(long id) {
        EntityManager em = JPAUtil.factory().createEntityManager();
        try {
            Optional<Mentor> mentor = Optional.ofNullable(em.find(Mentor.class, id));
            return mentor;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public Optional<Mentorado> buscarMentoradoId(long id) {
        EntityManager em = JPAUtil.factory().createEntityManager();
        try {
            Optional<Mentorado> mentorado = Optional.ofNullable(em.find(Mentorado.class, id));
            return mentorado;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

}
