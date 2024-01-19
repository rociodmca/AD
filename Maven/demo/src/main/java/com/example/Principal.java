package com.example;

import java.io.*;

import com.thoughtworks.xstream.XStream;

public class Principal {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Escribir un fichero binario
        escribirArchivoBinario();
        ListaPersonas listP = leerArchivoBinario();
        Imprimir(listP);
        //crearXML(listP);
        crearXMLAnotaciones(listP);
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
        } catch (EOFException eof) { 
        }
        dataIS.close();
        return listaPer;
    }

    private static void Imprimir(ListaPersonas listaP) {
        System.out.println("");
        for (int i = 0; i < listaP.tam(); i++) {
            System.out.println("");
            Persona per = listaP.getPersona(i);
            System.out.println(per.getNombre() + " " + per.getEdad());
        }
    }

    private static void crearXML(ListaPersonas listaImpresion) {
        try {
            XStream xstream = new XStream();
            xstream.alias("ListaPersonasMunicipio", ListaPersonas.class);
            xstream.alias("DatosPersona", Persona.class);

            xstream.toXML(listaImpresion, new FileOutputStream("./Maven/Personas.xml"));
            System.out.println("Creando fichero XML...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void crearXMLAnotaciones(ListaPersonas listaImpresion) {
        ListaPersonasAnotaciones listaPersonasAnotaciones = new ListaPersonasAnotaciones();
        for (int i = 0; i < listaImpresion.tam(); i++) {
            listaPersonasAnotaciones.add(listaImpresion.getPersona(i));
        }
        XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);

        xStream.processAnnotations(ListaPersonasAnotaciones.class);
        xStream.processAnnotations(PersonaAnotaciones.class);

        String xml = xStream.toXML(listaPersonasAnotaciones);

        System.out.println(xml);
    }
}
