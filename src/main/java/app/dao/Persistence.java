package app.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.HashMap;
import java.util.Map;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class Persistence {
    private EntityManagerFactory emf = createEntityManagerFactory("entityManager");
    private EntityManager em = emf.createEntityManager();

    public Persistence() {

    }

    public void persist(Object entidade){
        em.getTransaction().begin();
        em.persist(entidade);
        em.getTransaction().commit();
    }

    public void update(Object entidade){
        em.getTransaction().begin();
        em.merge(entidade);
        em.getTransaction().commit();
    }

    public void close(){
        em.close();
        emf.close();
    }

}
