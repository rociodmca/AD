package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Principal {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Escribir un fichero binario
        escribirArchivoBinario();
        ListaPersonas lista = leerArchivoBinario();
    }

    private static void escribirArchivoBinario() throws IOException {
        Persona persona;
        File fichero = new File("./Maven/ficheretepersona.dat");
        FileOutputStream fileout = new FileOutputStream(fichero, true);
        ObjectOutputStream dataOS = new ObjectOutputStream(fileout);

        String nombre[] = { "Alberto", "Perico", "Yomismamente", "otro" };
        int edades[] = { 18, 28, 36, 45 };

        System.out.println("Empezamos a grabar los datos!!!");
        for (int i = 0; i < edades.length; i++) {
            persona = new Persona(nombre[i], edades[i]);
            dataOS.writeObject(persona);
            // System.out.println("Grabando la persona: " + persona.toString());
        }
        dataOS.close();
    }

    private static ListaPersonas leerArchivoBinario() throws IOException, ClassNotFoundException {
        File fichero2 = new File("./Maven/ficheretepersona.dat");
        FileInputStream filein = new FileInputStream(fichero2);
        ObjectInputStream dataIS = new ObjectInputStream(filein);

        // Ahora leemos el archivo binario
        ListaPersonas listaPer = new ListaPersonas();
        try {
            while (true) {
                Persona persona = (Persona) dataIS.readObject();
                System.out.println(persona.toString());
                listaPer.add(persona);
            }
        } catch (Exception e) {
            dataIS.close();
            return listaPer;
        }
    }
}
