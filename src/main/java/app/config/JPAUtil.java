package app.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private JPAUtil(){

    }

    public static EntityManagerFactory factory(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("entityManager");
        return emf;
    }

    public static void fecharFactory(){
        factory().close();
    }
}
