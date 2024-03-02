package com.example;



import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Controlador2 {
    
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    static {
        try {
            // Crea el EntityManagerFactory utilizando la unidad de persistencia definida en persistence.xml
            entityManagerFactory = Persistence.createEntityManagerFactory("SanPitoPato");
        } catch (Throwable ex) {
            System.err.println("Error al inicializar el EntityManagerFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Método para obtener el EntityManager actual
    public static EntityManager getEntityManager() {
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }

    // Método para cerrar el EntityManager actual
    public static void closeEntityManager() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }

    public void metePersona(Persona persona) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Persistir la persona
            entityManager.persist(persona);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    
    }

    public void meteAnimal(Animales animal1) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Persistir la persona
            entityManager.persist(animal1);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    
    }

    public void meteFamiliares(Familiares familiar1) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Persistir la persona
            entityManager.persist(familiar1);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void meteDetalles(Detalles detalles) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Persistir la persona
            entityManager.persist(detalles);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    
    
}