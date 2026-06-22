package app.dao;

import app.config.JPAUtil;
import app.dao.interfaces.TrilhaDAO;
import app.model.entity.TrilhaMentoria;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class TrilhaImpl implements TrilhaDAO {

    @Override
    public boolean persist(TrilhaMentoria trilhaMentoria) {
        EntityManager em = JPAUtil.factory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(trilhaMentoria);
            em.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            Throwable causa = e.getCause() != null ? e.getCause() : e;
            System.out.println("Erro ao persistir a trilha: " + causa.getMessage());
            return false;
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void update(TrilhaMentoria trilhaMentoria) {
        EntityManager em = JPAUtil.factory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(trilhaMentoria);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Erro ao persistir a trilha: "
                    + (e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<TrilhaMentoria> listarTrilhas() {
        EntityManager em = JPAUtil.factory().createEntityManager();
        try {
            List<TrilhaMentoria> trilhas = em.createQuery(
                    "SELECT DISTINCT t FROM TrilhaMentoria t " +
                            "LEFT JOIN FETCH t.mentor " +
                            "LEFT JOIN FETCH t.mentorados " +
                            "ORDER BY t.id ASC",
                    TrilhaMentoria.class).getResultList();

            trilhas.forEach(trilha -> {
                trilha.getSkillsDaTrilha().size();
                if (trilha.getMentor() != null) {
                    trilha.getMentor().getSkills().size();
                }
                trilha.getMentorados().forEach(mentorado -> mentorado.getSkillsDesejadas().size());
            });
            return trilhas;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public Optional<TrilhaMentoria> buscarTrilhaId(long id) {
       EntityManager em = JPAUtil.factory().createEntityManager();
       try {
           TrilhaMentoria trilhaMentoria = em.createQuery(
                   "SELECT t FROM TrilhaMentoria t " +
                           "LEFT JOIN FETCH t.mentor " +
                           "LEFT JOIN FETCH t.mentorados " +
                           "WHERE t.id = :id",
                   TrilhaMentoria.class)
                   .setParameter("id", id)
                   .getResultStream()
                   .findFirst()
                   .orElse(null);

           if (trilhaMentoria != null) {
               trilhaMentoria.getSkillsDaTrilha().size();
           }

           if (trilhaMentoria.getMentor() != null) {
               trilhaMentoria.getMentor().getSkills().size();
           }
           trilhaMentoria.getMentorados().forEach(mentorado -> {
               mentorado.getSkills().size();
               mentorado.getSkillsDesejadas().size();
           });
           return Optional.ofNullable(trilhaMentoria);
       } finally {
           if (em.isOpen()) {
               em.close();
           }
       }
    }

    @Override
    public boolean remover(long id) {
        EntityManager em = JPAUtil.factory().createEntityManager();
        try {
            em.getTransaction().begin();

            TrilhaMentoria trilhaMentoria = em.find(TrilhaMentoria.class, id);
            if (trilhaMentoria == null) {
                em.getTransaction().rollback();
                return false;
            }
            em.remove(trilhaMentoria);

            em.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            Throwable causa = e.getCause() != null ? e.getCause() : e;
            System.out.println("Erro ao remover a trilha: " + causa.getMessage());
            return false;
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}
