package com.hibernate;

import java.util.*;

public class Principal {
    public static void main( String[] args )
    {
        Controlador cc = new Controlador(); //Me creo el controlador
        //Creo libros
        Libro libro1 = new Libro("Ser feo y no morir", "123465");
        Libro libro2 = new Libro("Ser feo y no ligar", "123465");
        Libro libro3 = new Libro("Ser feo y ligar", "123465");
        Libro libro4 = new Libro("Ser feo y que no te miren", "123465");
        Libro libro5 = new Libro("Ser feo y sebrevivir", "123465");
        List<Libro> libretes = new ArrayList<Libro>();
        libretes.add(libro4);
        libretes.add(libro5);
        
        //Creo direcciones
        DireccionPrincipal direccion1 = new DireccionPrincipal("Calle flor", "13009");
        DireccionPrincipal direccion2 = new DireccionPrincipal("Calle 1", "22222");
        DireccionPrincipal direccion3 = new DireccionPrincipal("Calle 2", "33333");
        DireccionPrincipal direccion4 = new DireccionPrincipal("Calle 3", "44444");
        DireccionPrincipal direccion5 = new DireccionPrincipal("Calle 4", "55555");

        //Meto el primer usuario
        Usuario user1 = new Usuario("nombre1", "apellido1", 23, direccion1, libretes);
        
        Usuario user2 = new Usuario("nombre2", "apellido2", 23, direccion2, libretes);
        
        Usuario user3 = new Usuario("nombre3", "apellido3", 23, direccion3, libretes);
        //libretes.remove(libro4);
        Usuario user4 = new Usuario("nombre4", "apellido4", 23, direccion4, libretes);

        cc.meterUsuario(user1);
        libretes.clear();
        libretes.add(libro1);
        cc.meterUsuario(user2);
        libretes.clear();
        libretes.add(libro2);
        cc.meterUsuario(user3);
        cc.meterUsuario(user4);
        cc.imprimeTodos();
        cc.imprimeUno(3);
        cc.eliminaUno(4);
        cc.imprimeTodos();
        //Editamos un usuario
        cc.editaUsuario(2, "nombre", "apellido", 45);
        cc.imprimeTodos();
    }
}
