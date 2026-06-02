package app.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/*
 * Mudei a EntityManagerFactory porque antes era criada várias vezes
 * causando lentidão, agora é criada só na primeira chamada
 */

public final class JPAUtil {

    public static final String PERSISTENCE_UNIT = "entityManager";

    private static class Holder {
        private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    }
    private JPAUtil() {
    }

    public static EntityManagerFactory factory() {
        return Holder.FACTORY;
    }

    public static EntityManager getEntityManager() {
        return Holder.FACTORY.createEntityManager();
    }

    public static void fecharFactory() {
        if (Holder.FACTORY.isOpen()) {
            Holder.FACTORY.close();
        }
    }
}
