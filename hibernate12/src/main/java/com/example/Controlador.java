package com.example;



import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Controlador {
    
    private EntityManagerFactory EMF;

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    static {
        try {
            // Crea el EntityManagerFactory utilizando la unidad de persistencia definida en persistence.xml
            entityManagerFactory = Persistence.createEntityManagerFactory("NombreUnidadPersistencia");
        } catch (Throwable ex) {
            System.err.println("Error al inicializar el EntityManagerFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    //////////
    // Constructores
    //////////
    public Controlador(){
        this.EMF=Persistence.createEntityManagerFactory("Controlador");
    }//Constructor


    /////////////
    //Métodos
    /////////////


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



////////
// método para imprimir todos los usuarios
//////////
public void imprimeTodos(){
    EntityManager em=this.EMF.createEntityManager();
    String query="SELECT c FROM Persona c";//Ojo cuidado que no es SQL es HQL

    //TipedQuery
    TypedQuery<Persona> tq=em.createQuery(query,Persona.class);
    List<Persona> todos;

    try {
        System.out.println("*-*-*-*-*-*-*-*-*-*-");
        todos=tq.getResultList();
        for(Persona c:todos){
            System.out.println("Imprime todos "+c.toString());
        }
    } catch (Exception e) {
        System.out.println(("ha habido un error al imprimir todos. "));
        e.printStackTrace();
    }finally{
        em.close();
    }//try

}//ImprimeTodos

    // Método para meter un usuario
    public void meteUsuario(int i, String nombre, String apellido, int edad){
        EntityManager em=this.EMF.createEntityManager();//Maneja la conexión
        EntityTransaction et=null; //Entidad para administrar la transacción

        try {
            et=em.getTransaction();
            et.begin();
          //  Persona judas=new Persona(i, nombre, apellido, edad);
           // em.persist(judas);
            et.commit();
         //   System.out.println("Usuario grabado---"+ judas.toString());
        } catch (Exception e) {
            if(et!=null){
                System.out.println("Hemos tenido un problema. Hacemos un rollback!");
                et.rollback();
            }//if
            e.printStackTrace();
        }finally{
            em.close();//Liberamos
        }




    }//Mete usuario


    public void metePersona(Persona persona1) {
        EntityManager em=this.EMF.createEntityManager();//Maneja la conexión
        EntityTransaction et=null; //Entidad para administrar la transacción

        try {
            et=em.getTransaction();
            et.begin();
            
            em.persist(persona1);
            et.commit();
         //   System.out.println("Usuario grabado---"+ judas.toString());
        } catch (Exception e) {
            if(et!=null){
                System.out.println("Hemos tenido un problema. Hacemos un rollback!");
                et.rollback();
            }//if
            e.printStackTrace();
        }finally{
            em.close();//Liberamos
        }

    }


    public void meteDetalle(Detalles detalles1) {
        EntityManager em=this.EMF.createEntityManager();//Maneja la conexión
        EntityTransaction et=null; //Entidad para administrar la transacción

        try {
            et=em.getTransaction();
            et.begin();
            
            em.persist(detalles1);
            et.commit();
         //   System.out.println("Usuario grabado---"+ judas.toString());
        } catch (Exception e) {
            if(et!=null){
                System.out.println("Hemos tenido un problema. Hacemos un rollback!");
                et.rollback();
            }//if
            e.printStackTrace();
        }finally{
            em.close();//Liberamos
        }

    }


    public void meteAnimal(Animales animales1) {
        EntityManager em=this.EMF.createEntityManager();//Maneja la conexión
        EntityTransaction et=null; //Entidad para administrar la transacción

        try {
            et=em.getTransaction();
            et.begin();
            
            em.persist(animales1);
            et.commit();

        } catch (Exception e) {
            if(et!=null){
                System.out.println("Hemos tenido un problema. Hacemos un rollback!");
                et.rollback();
            }//if
            e.printStackTrace();
        }finally{
            em.close();//Liberamos
        }

    }
    
    

    public void meteFamiliares(Familiares fam1) {
        EntityManager em=this.EMF.createEntityManager();//Maneja la conexión
        EntityTransaction et=null; //Entidad para administrar la transacción

        try {
            et=em.getTransaction();
            et.begin();
            
            em.persist(fam1);
            et.commit();

        } catch (Exception e) {
            if(et!=null){
                System.out.println("Hemos tenido un problema. Hacemos un rollback!");
                et.rollback();
            }//if
            e.printStackTrace();
        }finally{
            em.close();//Liberamos
        }

    }


/*

//// Mete un usuario
   // Método para meter un usuario
   public void meteUsuario(Usuario user){
    EntityManager em=this.EMF.createEntityManager();//Maneja la conexión
    EntityTransaction et=null; //Entidad para administrar la transacción

    try {
        et=em.getTransaction();
        et.begin();
        em.persist(user);
        et.commit();
        System.out.println("Usuario grabado---"+ user.toString());
    } catch (Exception e) {
        if(et!=null){
            System.out.println("Hemos tenido un problema. Hacemos un rollback!");
            et.rollback();
        }//if
        e.printStackTrace();
    }finally{
        em.close();//Liberamos
    }




}//Mete usuario

public void meteLibro(Libro libro){
    EntityManager em=this.EMF.createEntityManager();//Maneja la conexión
    EntityTransaction et=null; //Entidad para administrar la transacción

    try {
        et=em.getTransaction();
        et.begin();
        em.persist(libro);
        et.commit();
        System.out.println("Libro grabado---"+ libro.toString());
    } catch (Exception e) {
        if(et!=null){
            System.out.println("Hemos tenido un problema. Hacemos un rollback!");
            et.rollback();
        }//if
        e.printStackTrace();
    }finally{
        em.close();//Liberamos
    }




}//Mete meteLibro

public void meteMateria(Materia materia){
    EntityManager em=this.EMF.createEntityManager();//Maneja la conexión
    EntityTransaction et=null; //Entidad para administrar la transacción

    try {
        et=em.getTransaction();
        et.begin();
        em.persist(materia);
        et.commit();
        System.out.println("Materia grabada---"+ materia.toString());
    } catch (Exception e) {
        if(et!=null){
            System.out.println("Hemos tenido un problema. Hacemos un rollback!");
            et.rollback();
        }//if
        e.printStackTrace();
    }finally{
        em.close();//Liberamos
    }




}//Mete meteLibro


*/
// Método para obtener el EntityManager actual



}//Controlador
