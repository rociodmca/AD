package com.examen;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Controlador {

    private static EntityManagerFactory EMF;
    private static EntityManager entityManager;

    static {
        try {
            // Crea el EntityManagerFactory utilizando la unidad de persistencia definida en persistence.xml
            EMF = Persistence.createEntityManagerFactory("Persistencia");
        } catch (Throwable ex) {
            System.err.println("Error al inicializar el EntityManagerFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Método para obtener el EntityManager actual
    public static EntityManager getEntityManager() {
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = EMF.createEntityManager();
        }
        return entityManager;
    }

    // Método para cerrar el EntityManager actual
    public static void closeEntityManager() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
        if (EMF != null && EMF.isOpen()) {
            EMF.close();
        }
    }

    /*public void meterJugador(String nombre_Jugador, String procedencia, String altura, int peso, String posicion, equipos equipo, List<partidos> partidos, valoracionRevistas valoracionRevistas) {
        EntityManager em = this.EMF.createEntityManager(); // Maneja la conexión
        EntityTransaction et = null; // Entidad para administrar la transacción

        try {
            et = em.getTransaction();
            et.begin();
            jugadores jugador = new jugadores(nombre_Jugador, procedencia, altura, peso, posicion, equipo, partidos, valoracionRevistas);
            em.persist(jugador);
            et.commit();
            System.out.println("Jugador grabado---" + jugador.toString());
        } catch (Exception e) {
            if (et != null) {
                System.out.println("Hemos tenido un problema. Hacemos un rollback");
                et.rollback();
            }
        } finally {
            em.close(); //Liberamos
        }

    }*/

    public void meterJugador(jugadores jugador) {
        EntityManager em = getEntityManager(); // Maneja la conexión
        EntityTransaction et = null; // Entidad para administrar la transacción

        try {
            et = em.getTransaction();
            et.begin();
            em.persist(jugador);
            et.commit();
            System.out.println("Jugador grabado---" + jugador.toString());
        } catch (Exception e) {
            if (et != null) {
                System.out.println("Hemos tenido un problema. Hacemos un rollback");
                et.rollback();
            }
        } finally {
            //em.close(); //Liberamos
        }

    }

    /*public void meterEquipo(String nombre, String ciudad, String conferencia, String division,List<com.examen.jugadores> jugadores) {
        EntityManager em = this.EMF.createEntityManager(); // Maneja la conexión
        EntityTransaction et = null; // Entidad para administrar la transacción

        try {
            et = em.getTransaction();
            et.begin();
            equipos equipo = new equipos(nombre, ciudad, conferencia, division, jugadores);
            em.persist(equipo);
            et.commit();
            System.out.println("Equipo grabado---" + equipo.toString());
        } catch (Exception e) {
            if (et != null) {
                System.out.println("Hemos tenido un problema. Hacemos un rollback");
                et.rollback();
            }
        } finally {
            em.close(); //Liberamos
        }

    }*/

    public void meterEquipo(equipos equipo) {
        EntityManager em = getEntityManager(); // Maneja la conexión
        EntityTransaction et = null; // Entidad para administrar la transacción

        try {
            et = em.getTransaction();
            et.begin();
            em.persist(equipo);
            et.commit();
            System.out.println("Equipo grabado---" + equipo.toString());
        } catch (Exception e) {
            if (et != null) {
                System.out.println("Hemos tenido un problema. Hacemos un rollback");
                et.rollback();
            }
        } finally {
            //em.close(); //Liberamos
        }

    }

    /*public void meterPartido(equipos equipo_local, equipos equipo_visitante, int puntos_local, int puntos_visitante, String temporada, List<jugadores> jugadores) {
        EntityManager em = this.EMF.createEntityManager(); // Maneja la conexión
        EntityTransaction et = null; // Entidad para administrar la transacción

        try {
            et = em.getTransaction();
            et.begin();
            partidos partido = new partidos(equipo_local, equipo_visitante, puntos_local, puntos_visitante, temporada, jugadores);
            em.persist(partido);
            et.commit();
            System.out.println("Partido grabado---" + partido.toString());
        } catch (Exception e) {
            if (et != null) {
                System.out.println("Hemos tenido un problema. Hacemos un rollback");
                et.rollback();
            }
        } finally {
            em.close(); //Liberamos
        }

    }*/

    public void meterPartido(partidos partido) {
        EntityManager em = getEntityManager(); // Maneja la conexión
        EntityTransaction et = null; // Entidad para administrar la transacción

        try {
            et = em.getTransaction();
            et.begin();
            em.persist(partido);
            et.commit();
            System.out.println("Partido grabado---" + partido.toString());
        } catch (Exception e) {
            if (et != null) {
                System.out.println("Hemos tenido un problema. Hacemos un rollback");
                et.rollback();
            }
        } finally {
            //em.close(); //Liberamos
        }

    }

    /*public void meterValoracionRevista(String nombreRevista, int valoracion) {
        EntityManager em = this.EMF.createEntityManager(); // Maneja la conexión
        EntityTransaction et = null; // Entidad para administrar la transacción

        try {
            et = em.getTransaction();
            et.begin();
            valoracionRevistas valoracionRevista = new valoracionRevistas(nombreRevista, valoracion);
            em.persist(valoracionRevista);
            et.commit();
            System.out.println("ValoracionRevista grabado---" + valoracionRevista.toString());
        } catch (Exception e) {
            if (et != null) {
                System.out.println("Hemos tenido un problema. Hacemos un rollback");
                et.rollback();
            }
        } finally {
            em.close(); //Liberamos
        }

    }*/

    public void meterValoracionRevista(valoracionRevistas valoracionRevista) {
        EntityManager em = getEntityManager(); // Maneja la conexión
        EntityTransaction et = null; // Entidad para administrar la transacción

        try {
            et = em.getTransaction();
            et.begin();
            em.persist(valoracionRevista);
            et.commit();
            System.out.println("ValoracionRevista grabado---" + valoracionRevista.toString());
        } catch (Exception e) {
            if (et != null) {
                System.out.println("Hemos tenido un problema. Hacemos un rollback");
                et.rollback();
            }
        } finally {
            //em.close(); //Liberamos
        }

    }

    public void meterPatrocinador(patrocinador patrocinador) {
        EntityManager em = getEntityManager(); // Maneja la conexión
        EntityTransaction et = null; // Entidad para administrar la transacción

        try {
            et = em.getTransaction();
            et.begin();
            em.persist(patrocinador);
            et.commit();
            System.out.println("Patrocinador grabado---" + patrocinador.toString());
        } catch (Exception e) {
            if (et != null) {
                System.out.println("Hemos tenido un problema. Hacemos un rollback");
                et.rollback();
            }
        } finally {
            //em.close(); //Liberamos
        }

    }

    //Método para imprimir por pantalla todos los jugadores
    public void imprimeTodosJugadores() {
        EntityManager em = getEntityManager();
        String query = "SELECT c FROM jugadores c"; //Ojo cuidado que no es SQL es HQL

        //TipedQuery
        TypedQuery<jugadores> tq = em.createQuery(query, jugadores.class);
        List<jugadores> todos;
        
        try {
            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*");
            todos = tq.getResultList();
            for (jugadores c : todos) {
                System.out.println("Imprime todos " + c.toString());
            }
        } catch (Exception e) {
            System.out.println("Ha habido un error al imprimir todos.");
            e.printStackTrace();
        } finally {
            //em.close();
        }
    }

    //Método para imprimir por pantalla todos los equipos
    public void imprimeTodosEquipos() {
        EntityManager em = getEntityManager();
        String query = "SELECT c FROM equipos c"; //Ojo cuidado que no es SQL es HQL

        //TipedQuery
        TypedQuery<equipos> tq = em.createQuery(query, equipos.class);
        List<equipos> todos;
        
        try {
            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*");
            todos = tq.getResultList();
            for (equipos c : todos) {
                System.out.println("Imprime todos " + c.toString());
            }
        } catch (Exception e) {
            System.out.println("Ha habido un error al imprimir todos.");
            e.printStackTrace();
        } finally {
            //em.close();
        }
    }

    //Método para imprimir un jugador
    public void imprimeUno(int i) {
        EntityManager em = EMF.createEntityManager();
        String query = "SELECT c FROM jugadores c WHERE c.codigo=:param"; //con ':' le indicamos que es un parámetro

        TypedQuery<jugadores> tq = em.createQuery(query, jugadores.class);
        tq.setParameter("param", i);
        jugadores resulta = null;
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

    //Método para eliminar un jugador
    public void eliminaUno(int codigo) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction et = null;

        jugadores borra = null;

        try {
            et = em.getTransaction();
            et.begin();
            borra = em.find(jugadores.class, codigo);
            if (borra == null) {
                System.out.println("el usuario no está ni se le espera");
                return;
            }
            em.remove(borra); //Lo eliminamos
            em.flush(); //Sincronizar la persistencia
            et.commit();
            System.out.println("El usuario con id " + codigo + " ha pasado a mejor vida.");
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
    public void editaJugador(int codigo, String nombre_Jugador, String procedencia, String altura, int peso, String posicion) {
        EntityManager em = EMF.createEntityManager();
        EntityTransaction et = null;

        jugadores customizado = null;
        try {
            et = em.getTransaction();
            et.begin();
            customizado = em.find(jugadores.class, codigo);
            if (customizado == null) {
                System.out.println("Chaval! ese usuario no existe, flipado.");
                return;
            }
            customizado.setNombre_Jugador(nombre_Jugador);
            customizado.setProcedencia(procedencia);
            customizado.setAltura(altura);
            customizado.setPeso(peso);
            customizado.setPosicion(posicion);
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

    public void jugadoresAltos(String altura) {
        EntityManager em = EMF.createEntityManager();
        String query = "SELECT c FROM jugadores c WHERE c.Altura > :param"; //con ':' le indicamos que es un parámetro

        TypedQuery<jugadores> tq = em.createQuery(query, jugadores.class);
        tq.setParameter("param", altura);
        List<jugadores> altos;
        try {
            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*");
            altos = tq.getResultList();
            for (jugadores c : altos) {
                System.out.println("Imprime jugadores altos " + c.toString());
            }
        } catch (NoResultException e) {
            System.out.println("Ha habido un error");
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
