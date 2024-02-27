package com.hibernate27;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Controlador {
    
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    static {
        try {
            //Creamos el entityManagerFactory utilizando la prsistencia de xml
            entityManagerFactory = Persistence.createEntityManagerFactory("Controlador");
        } catch (Exception e) {
            System.err.println("Error al inicializar EntityManagerFactory" + e);
        }
        
    }

    //Método para devolver el EntityManager actual
    public static EntityManager getEntityManager() {
        if (entityManager == null || !entityManager.isOpen()) {  //Está cerrado
            entityManager = entityManagerFactory.createEntityManager();  //Lo creo
        }
        return entityManager;
    }

    //Método para cerrar el EntityManager
    public static void closeEntityManager() {
        if (entityManager != null && entityManager.isOpen()) {  //Está abierto
            entityManager.close();
        }
    }

    //Método para cerrar el EntityManagerFactory
    public static void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) { //Esta abierto
            entityManagerFactory.close();
        }
    }

}
