package app.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Persistence {
    EntityManagerFactory emf = jakarta.persistence.Persistence.createEntityManagerFactory("entityManager");
    EntityManager em = emf.createEntityManager();

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
