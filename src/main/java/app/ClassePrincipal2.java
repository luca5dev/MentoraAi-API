package main.java.app;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ClassePrincipal2 {
    public static void main(String[] args) {
        System.out.println("Hello world");
        System.out.println("Tentativa de conexão...");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("entityManager");
        EntityManager em = emf.createEntityManager();

        System.out.println("EntityManager Inicializado com sucesso");

        em.close();
        emf.close();
    }
}
