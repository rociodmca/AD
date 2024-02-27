package com.hibernate27;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class Principal {
    public static void main(String[] args) {

        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            // 1ª obtener el EntityManager utilizando la case controlador
            entityManager = Controlador.getEntityManager();

            // 2ª Iniciamos el objeto que va a llevar la transacción.
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Creamos los actores
            for (int i = 0; i < 4; i++) {
                Actor actor = new Actor("Actorcete " + i, new Date(), null);
                entityManager.persist(actor);
            }

            // Crear los directores
            for (int i = 0; i < 4; i++) {
                Director director = new Director("Director " + i, new Date(), null);
                entityManager.persist(director);
            }

            // Obtenemos el primer director y actores
            Director director = entityManager.find(Director.class, 1); // Suponemos que el director tiene el id=1
            Set<Actor> actores = new HashSet<Actor>();

            for (int i = 0; i < 4; i++) {
                Actor actor = entityManager.find(Actor.class, i);
                actores.add(actor);
            }

            // Insertamos las peliculas con el director y actores
            for (int i = 0; i < 4; i++) {
                Pelicula pelicula = new Pelicula("Pelicula " + i, director, new Date(), actores);
                // Lo persistimos, lo metemos
                entityManager.persist(pelicula);
            }
            transaction.commit(); // Aunque tenemos configurado por defecto el auto-comit
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("Ojo, queda algo pendiente.");
                transaction.rollback();
            }
        } finally {
            //Cerramos el EntityManager
            if (entityManager != null) {
                Controlador.closeEntityManager();
            }
            Controlador.closeEntityManagerFactory();
        }

    }
}
