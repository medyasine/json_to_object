package com.example.firstproject.demo2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerProvider {
    private static EntityManagerFactory entityManagerFactory;

    static {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("myJpaUnit");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}