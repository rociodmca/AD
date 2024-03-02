package com.example;

public class Principal {
    public static void main(String[] args) {
        Controlador cc=new Controlador();

        //Creamos persona
        Persona persona1=new Persona("Flor", "ea", 12);
        Persona persona2=new Persona("Flor2", "ea", 12);
        Persona persona3=new Persona("Flor3", "ea", 12);
        cc.metePersona(persona1);
        cc.metePersona(persona2);

        //Creamos Detalle
        Detalles detalles1=new Detalles(false,false);
        Detalles detalles2=new Detalles(true,false);
        Detalles detalles3=new Detalles(false,true);

        
        persona1.setDetalles(detalles1);
        persona2.setDetalles(detalles2);
        persona3.setDetalles(detalles3);

        cc.meteDetalle(detalles1);
        cc.meteDetalle(detalles2);

        //Creamos animales
        Animales animales1=new Animales("judas", "perro");
        Animales animales2=new Animales("judas", "gato");
        Animales animales3=new Animales("judas", "pez");



        animales1.setPersona(persona1);
        animales2.setPersona(persona1);
        animales3.setPersona(persona2);



        //añadimos los animales a las personas
        persona1.setAnimal(animales1);
        persona1.setAnimal(animales2);
        persona2.setAnimal(animales3);

                
        cc.meteAnimal(animales1);
        cc.meteAnimal(animales2);
        cc.meteAnimal(animales3);

        //Añadimos Familiares
        Familiares familiares1=new Familiares("judas", "padre");
        Familiares familiares2=new Familiares("judas1","madre");

        familiares1.getPersona().add(persona1);
        familiares1.getPersona().add(persona1);




        persona1.getFamiliares().add(familiares1);
        persona1.getFamiliares().add(familiares1);

        cc.meteFamiliares(familiares2);
        cc.meteFamiliares(familiares1);

        cc.metePersona(persona1);
       cc.metePersona(persona2);


        cc.imprimeTodos();

        
    }//main


}// Principal