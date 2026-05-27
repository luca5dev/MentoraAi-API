package app.dao;

import app.config.JPAUtil;
import app.dao.interfaces.TrilhaDAO;
import app.model.entity.TrilhaMentoria;
import jakarta.persistence.EntityManager;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;
import java.util.Optional;

public class TrilhaImpl implements TrilhaDAO {

    @Override
    public void persist(TrilhaMentoria trilhaMentoria) {
        EntityManager em = JPAUtil.factory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(trilhaMentoria);
            em.getTransaction().commit();
        } catch (ConstraintViolationException e){
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Erro na persistência: " + e.getMessage() + ". Nenhuma alteração foi feita!");
        } finally {
            if (em!=null&& em.isOpen()){
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
        } catch (ConstraintViolationException e){
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Erro na persistência: " + e.getMessage() + ". Nenhuma alteração foi feita!");
        } finally {
            if (em!=null&& em.isOpen()){
                em.close();
            }
        }
    }

    @Override
    public List<TrilhaMentoria> listarTrilhas() {
        EntityManager em = JPAUtil.factory().createEntityManager();
        try {
            return em.createQuery("SELECT s FROM TrilhaMentoria s ORDER BY s.id ASC", TrilhaMentoria.class)
                    .getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public Optional<TrilhaMentoria> buscarTrilhaId(long id) {
       EntityManager em = JPAUtil.factory().createEntityManager();
       Optional<TrilhaMentoria> trilhaMentoria = Optional.ofNullable(em.find(TrilhaMentoria.class, id));
       return trilhaMentoria;
    }
}
