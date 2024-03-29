package com.example;

import com.example.Animales;
import com.example.Persona;
import com.example.Familiares;
import com.example.Controlador;

public class Principal2 {

    public static void main(String[] args) {
        // Creamos un objeto Controlador para gestionar las operaciones con la base de datos
        Controlador2 controlador = new Controlador2();

        // Creamos instancias de Familiares
        Familiares familiar1 = new Familiares("Padre", "ApellidoPadre");
        Familiares familiar2 = new Familiares("Madre", "ApellidoMadre");

        // Creamos instancias de Animales
        Animales animal1 = new Animales("Perro", "Canino");
        Animales animal2 = new Animales("Gato", "Felino");

        // Creamos una instancia de Detalles
        Detalles detalles = new Detalles(true,true);
        
        // Creamos una instancia de Persona y asignamos los detalles, animales y familiares
        Persona persona = new Persona("Nombre", "Apellido", 30, detalles);
        
        persona.getFamiliares().add(familiar1);
        persona.getFamiliares().add(familiar2);

        controlador.meteDetalles(detalles);

        // Persistimos los familiares
        controlador.meteFamiliares(familiar1);
        controlador.meteFamiliares(familiar2);
        
        // Persistimos la persona
        controlador.metePersona(persona);

        persona.setAnimal(animal1);
        persona.setAnimal(animal2);

        animal1.setPersona(persona);
        animal2.setPersona(persona);

        // Persistimos los animales
        controlador.meteAnimal(animal1);
        controlador.meteAnimal(animal2);

        // Cerramos el controlador
        Controlador2.closeEntityManager();
    }
}

 