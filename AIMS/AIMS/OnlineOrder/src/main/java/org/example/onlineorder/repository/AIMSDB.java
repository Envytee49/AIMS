package org.example.onlineorder.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AIMSDB {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("aims");
    private static EntityManager entityManager = emf.createEntityManager();

    public static EntityManager getEntityManager() {
        return entityManager;
    }

    public static void shutdown() {
        emf.close();
        entityManager.close();
    }
}
