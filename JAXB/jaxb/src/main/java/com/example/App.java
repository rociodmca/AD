package com.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ParserConfigurationException, IOException, TransformerException, SAXException, JAXBException
    {
        //1º Escritura y lectura con DOM
        GeneradorDOM generadorDOM = new GeneradorDOM();
        generadorDOM.generarDocument();
        generadorDOM.generarXML();

        //Lectura
        LecturaDOM lecturaDOM = new LecturaDOM(".//JAXB//jaxb//src//productos.xml");
        lecturaDOM.lecturaXML();

        System.out.println("");

        //2º lectura con JAXB
        //Debemos hacer un contexto
        JAXBContext context = JAXBContext.newInstance(Libreria.class);
        //Vamos a pasar del XML a java para poderlo leer Unmarshaller
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Libreria libreria = (Libreria)unmarshaller.unmarshal(new File(".//JAXB//jaxb//src//libreria.xml"));
        //Imprimimos los datos
        System.out.println("Nombre de la libreria: " + libreria.getNombre());
        ArrayList<Libro> libros = libreria.getLibros();
        for (Libro libro : libros) {
            System.out.println("ISBN " + libro.getIsbn() + "\nTitulo " + libro.getTitulo() + "\nAutor " + libro.getAutor() + "\n-------------------------------");
        }

        //3º escritura con JAXB
        //Creamos contexto. Se le asigna la clase que engloba el XML
        JAXBContext contextEscritura = JAXBContext.newInstance(Libreria.class);
        Marshaller marshaller = contextEscritura.createMarshaller();
        //Creamos la información 
        Libreria libreriaJAXB = new Libreria();
        libreriaJAXB.setNombre("Libreria de superheroes");

        //Añado los libros
        ArrayList<Libro> librosJAXB = new ArrayList<>();

        //Librotes
        Libro libro1 = new Libro();
        libro1.setIsbn("258976");
        libro1.setAutor("Judas");
        libro1.setTitulo("Soy feo porque el mundo me hizo así");
        librosJAXB.add(libro1);

        Libro libro2 = new Libro();
        libro2.setIsbn("741258");
        libro2.setAutor("Flor del bosque");
        libro2.setTitulo("Me gusta bañarme");
        librosJAXB.add(libro2);

        Libro libro3 = new Libro();
        libro3.setIsbn("789654");
        libro3.setAutor("Agua Pasada");
        libro3.setTitulo("Déjala correr");
        librosJAXB.add(libro3);

        //ya tenemos todos los libros. Los cargo en la librería
        libreriaJAXB.setLibros(librosJAXB);

        //Para que salga bonito, o en un formato mejor setProperty
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(libreriaJAXB, new FileWriter(".//JAXB//jaxb//src//libreriaJAXB.xml"));

        //Leer con SAX
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();

        //Se le pasa un File y se le indica el manejador de eventos
        File file = new File(".//JAXB//jaxb//src//libreriaJAXB.xml");
        ManejadorHandler handler = new ManejadorHandler();
        //Este método dejará un arrayList
        saxParser.parse(file, handler);

        //Recogemos la información 
        ArrayList<Libro> libreriaSAX = handler.getLibrerias();

        //Imprimiríamos la información
        for (Libro l : libreriaSAX) {
            System.out.println(l);
        }
    }
}
