package com.hibernatepostgresql;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Controlador {

    private EntityManagerFactory EMF;

    public Controlador() {
        this.EMF = Persistence.createEntityManagerFactory("Controlador");
    }

    public void meterUsuario(int id, String nombre, String apellido, int edad) {
        EntityManager em = this.EMF.createEntityManager(); // Maneja la conexión
        EntityTransaction et = null; // Entidad para administrar la transacción

        try {
            et = em.getTransaction();
            et.begin();
            Usuario judas = new Usuario(id, nombre, apellido, edad);
            em.persist(judas);
            et.commit();
            System.out.println("Usuario grabado---" + judas.toString());
        } catch (Exception e) {
            if (et != null) {
                System.out.println("Hemos tenido un problema. Hacemos un rollback");
                et.rollback();
            }
        } finally {
            em.close(); //Liberamos
        }

    }

    public void meterUsuario(Usuario user) {
        EntityManager em = this.EMF.createEntityManager(); // Maneja la conexión
        EntityTransaction et = null; // Entidad para administrar la transacción

        try {
            et = em.getTransaction();
            et.begin();
            em.persist(user);
            et.commit();
            System.out.println("Usuario grabado---" + user.toString());
        } catch (Exception e) {
            if (et != null) {
                System.out.println("Hemos tenido un problema. Hacemos un rollback");
                et.rollback();
            }
        } finally {
            em.close(); //Liberamos
        }

    }

    //Método para imprimir por pantalla todos los usuarios
    public void imprimeTodos() {
        EntityManager em = this.EMF.createEntityManager();
        String query = "SELECT c FROM Usuario c"; //Ojo cuidado que no es SQL es HQL

        //TipedQuery
        TypedQuery<Usuario> tq = em.createQuery(query, Usuario.class);
        List<Usuario> todos;
        
        try {
            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*");
            todos = tq.getResultList();
            for (Usuario c : todos) {
                System.out.println("Imprime todos " + c.toString());
            }
        } catch (Exception e) {
            System.out.println("Ha habido un error al imprimir todos.");
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    //Método para imprimir uno
    public void imprimeUno(int i) {
        EntityManager em = EMF.createEntityManager();
        String query = "SELECT c FROM Usuario c WHERE c.id=:param"; //con ':' le indicamos que es un parámetro

        TypedQuery<Usuario> tq = em.createQuery(query, Usuario.class);
        tq.setParameter("param", i);
        Usuario resulta = null;
        try {
            resulta = tq.getSingleResult(); //Devuelve un solo resultado, estamos comparando claves primarias
            System.out.println("Imprimimos valor " + i + " " + resulta.toString());
        } catch (NoResultException e) {
            System.out.println("No devuelve nada! Usuario no existe, alma de pollo!");
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    //Método para eliminar uno
    public void eliminaUno(int id) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction et = null;

        Usuario borra = null;

        try {
            et = em.getTransaction();
            et.begin();
            borra = em.find(Usuario.class, id);
            if (borra == null) {
                System.out.println("el usuario no está ni se le espera");
                return;
            }
            em.remove(borra); //Lo eliminamos
            em.flush(); //Sincronizar la persistencia
            et.commit();
            System.out.println("El usuario con id " + id + " ha pasado a mejor vida.");
        } catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    //Método para editar
    public void editaUsuario(int id, String nombre, String apellido, int edad) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction et = null;

        Usuario customizado = null;
        try {
            et = em.getTransaction();
            et.begin();
            customizado = em.find(Usuario.class, id);
            if (customizado == null) {
                System.out.println("Chaval! ese usuario no existe, flipado.");
                return;
            }
            customizado.setNombre(nombre);
            customizado.setApellido(apellido);
            customizado.setEdad(edad);
            em.persist(customizado);
            System.out.println("Elemento editado: " + customizado.toString());
            et.commit();
        } catch (Exception e) {
            if (et != null) {
                System.out.println("problema al editar un usuario");
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
