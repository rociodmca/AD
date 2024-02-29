package com.ejemplohibernate;

import java.util.ArrayList;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        Controlador cc=new Controlador();//Me creo el controlador.

        //Creo usuario
        Usuario user1=new Usuario("nombre1","apellido1",23);
        Usuario user2=new Usuario("nombre2","apellido1",23);
        
        //Creo libros
        Libro libro1=new Libro("Ser feo y no morir","123465");
        Libro libro2=new Libro("Ser feo y no ligar","123465");
        Libro libro3=new Libro("Ser feo y ligar","123465");
        Libro libro4=new Libro("Ser feo y que no te miren","123465");
        Libro libro5=new Libro("Ser feo y sobrevivir","123465");

        List<Libro> libretes=new ArrayList<Libro>();
        List<Libro> libretes2=new ArrayList<Libro>();

        //Añadimos los libros a la lista
        libretes.add(libro1);
        libretes.add(libro2);

        //Añadimos los libros a la lista
        libretes2.add(libro3);
        libretes2.add(libro4);
        libretes2.add(libro5);
        

        //Le añadimos los libros al usuario
        user1.setLibros(libretes);
        user2.setLibros(libretes2);


        //Creo Direcciones
        DireccionPrincipal direccion1=new DireccionPrincipal("Calle flor", "13009");
        DireccionPrincipal direccion2=new DireccionPrincipal("Calle 1", "22222");
        DireccionPrincipal direccion3=new DireccionPrincipal("Calle 2", "3333");
        DireccionPrincipal direccion4=new DireccionPrincipal("Calle 3", "44444");
        DireccionPrincipal direccion5=new DireccionPrincipal("Calle 4", "5555");

        //metemos la dirección
        user1.setDireccion(direccion1);
        user2.setDireccion(direccion2);

        //Creamos la materia
        Materia materia1=new Materia("Matemáticas");
        Materia materia2=new Materia("Flores");
        user1.addMateria(materia1);
        user1.addMateria(materia2);
        
        //Metemos las materias
        cc.meteMateria(materia1);
        cc.meteMateria(materia2);

        materia1.getUsuarios().add(user1);
        materia1.getUsuarios().add(user2);

        user1.getMaterias().add(materia1);
        user1.getMaterias().add(materia2);



        cc.meteUsuario(user1);
        cc.meteUsuario(user2);

        

        //Creamos usuarios
 
        
        // Usuario user2=new Usuario("nombre2","apellido1",23,direccion2,libretes);
        // libretes.add(libro2);
        // Usuario user3=new Usuario("nombre3","apellido1",23,direccion3,libretes);
        // libretes.remove(libro4);
        // Usuario user4=new Usuario("nombre4","apellido1",23,direccion4,libretes);

        // //Metemos usuarios
        // cc.meteUsuario(user1);cc.meteUsuario(user2);cc.meteUsuario(user3);cc.meteUsuario(user4);
        // cc.imprimeTodos();
        // cc.imprimeUno(3);
        // cc.eliminaUno(4);
        // cc.imprimeTodos();
        // //Editamos un usuario
        // cc.editaUsuario(2,"nombre","flordeloto",45);
        // cc.imprimeUno(2);
        
        cc.imprimeTodos();

        
    }//main




}//Principal