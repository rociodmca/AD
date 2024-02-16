package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class App {
    public static void main(String[] args) {
        // Configuración de la conexión a la base de datos
        String URL = "jdbc:mysql://localhost:33060";
        String USUARIO = "root";
        String CONTRASENA = "secret";

        Connection conexion;

        conexion = Ejercicio1(URL, USUARIO, CONTRASENA);
        if (conexion != null) {
            Ejercicio1CreacionBBDD(conexion);
            seleccionarBase(conexion);
            Ejercicio2apartadoA(conexion, 0);
            Ejercicio2apartadoB(conexion);
            try {
                Ejercicio3(conexion);
            } catch (JAXBException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Ejercicio4(conexion);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                Ejercicio5();
            } catch (TransformerFactoryConfigurationError | ParserConfigurationException | TransformerException
                    | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Ejercicio6();
            try {
                Ejercicio7();
            } catch (NumberFormatException | PasaDe1MillonException e) {
                e.printStackTrace();
            }
            Ejercicio8();
        }
        // Ejercicio9();
    }

    private static void Ejercicio8() {
        // Cogiendo el ejercicio anterior (solamente las 6 primeras líneas quitando las
        // dos primeras) y creando un objeto base. Crea un archivo JSON utilizando la
        // librería gson de google.

        // Nombre del fichero
        String nombreArchivo = "fichero.csv";

        // Variable para recoger datos.
        List<Persona> personas = new ArrayList<>();

        boolean existe = new File(nombreArchivo).exists();

        if (!existe) {
            System.out.println("El archivo no existe!!!!!!");
        } else {
            try {
                CSVReader entrada = new CSVReader(new FileReader(nombreArchivo));
                String[] siguiente;
                entrada.readNext();
                entrada.readNext();
                int i = 0;

                while (((siguiente = entrada.readNext()) != null) && (i <= 6)) {
                    for (int j = 0; j < siguiente.length; j++) {
                        personas.add(new Persona(siguiente[0], siguiente[1], siguiente[2], siguiente[3]));
                    }
                    i++;
                }

                entrada.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (CsvValidationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String json = new Gson().toJson(personas);
        System.out.println(json);
    }

    private static void Ejercicio7() throws NumberFormatException, PasaDe1MillonException {
        // Cogiendo el siguiente archivo csv, programando con la librería que hemos
        // utilizado en clase, imprimir por pantalla todos los datos. Suma todos los
        // datos de la cuarta columna a partir de 0 años, en cuanto la suma pase de
        // 1millón saltará una excepción creada por ti indicando hasta que edad se ha
        // sumado.
        // Nombre del fichero
        String nombreArchivo = "fichero.csv";

        // Variable para recoger datos.
        int edad = 0;

        boolean existe = new File(nombreArchivo).exists();

        if (!existe) {
            System.out.println("El archivo no existe!!!!!!");
        } else {
            try {
                CSVReader entrada = new CSVReader(new FileReader(nombreArchivo));
                String[] siguiente;
                entrada.readNext();
                entrada.readNext();
                while ((siguiente = entrada.readNext()) != null) {
                    for (int i = 0; i < siguiente.length; i++) {
                        System.out.println(siguiente[i]);
                        try {
                            if (Integer.parseInt(siguiente[3]) > 0) {
                                edad += Integer.parseInt(siguiente[3]);
                                PasaDe1Millon(edad);
                                System.out.println(edad);
                            }
                        } catch (NumberFormatException e) {
                        }
                    }
                    // System.out.println();
                }
                entrada.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (CsvValidationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void Ejercicio6() {
        // Visualizar todas las etiquetas del fichero “ciclistas.xml” utilizando las
        // técnicas SAX.
        try {
            // Conseguimos una instancia de SAXFactory
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            CiclistasHandler handler = new CiclistasHandler();
            parser.parse("ciclistas2.xml", handler);
        } catch (ParserConfigurationException e) {

            e.printStackTrace();
        } catch (SAXException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private static void Ejercicio5() throws TransformerFactoryConfigurationError, ParserConfigurationException,
            TransformerException, FileNotFoundException, IOException, ClassNotFoundException {
        // Leyendo el fichero anterior (“ciclismo.dat”) crear un fichero llamado
        // “ciclistas.xml” usando DOM creando al menos un atributo.
        File fichero = new File("ciclismo.dat");
        ArrayList<Ciclista> ciclistas = new ArrayList<>();
        Ciclista cicl;
        ObjectInputStream dataentrada = new ObjectInputStream(new FileInputStream(fichero));
        try {
            while (true) {
                cicl = (Ciclista) dataentrada.readObject();
                ciclistas.add(cicl);
            }
        } catch (EOFException eof) {
            dataentrada.close();
        }
        // 1º Creamos el constructor de la fabrica de archivos
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 2º Creamos el parser
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();
        Document document = implementation.createDocument(null, "ciclistas2", null);
        document.setXmlVersion("1.0");
        Element raiz = document.getDocumentElement();
        for (int i = 0; i < ciclistas.size(); i++) {
            Element itemNode = document.createElement("CICLISTA");
            // Nombre
            Attr attr = document.createAttribute("dorsal");
            attr.setValue(Integer.toString(ciclistas.get(i).getDorsal()));
            itemNode.setAttributeNodeNS(attr);
            Element nombreNode = document.createElement("NOMBRE");
            Text nodeNombreValue = document.createTextNode("" + ciclistas.get(i).getNombre());
            nombreNode.appendChild(nodeNombreValue); // le pegamos el valor

            // Edad
            Element edadNode = document.createElement("EDAD");
            Text nodeEdadValue = document.createTextNode("" + Integer.toString(ciclistas.get(i).getEdad()));
            edadNode.appendChild(nodeEdadValue); // le pegamos el valor

            // Nomeq
            Element nomeqNode = document.createElement("NOMEQ");
            Text nodeNomeqValue = document.createTextNode("" + ciclistas.get(i).getNomeq());
            nomeqNode.appendChild(nodeNomeqValue); // le pegamos el valor

            // Hemos creado los nodos y ahora pegamos los hijos.
            itemNode.appendChild(nombreNode);
            itemNode.appendChild(edadNode);
            itemNode.appendChild(nomeqNode);

            // al raiz se lo endosamos
            raiz.appendChild(itemNode);
        }

        // Generar el XML
        DOMSource source = new DOMSource(document);

        // Donde lo guardamos ??
        StreamResult result = new StreamResult(new java.io.File("ciclistas2.xml"));
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, result);
    }

    private static void Ejercicio9() {
        System.out.println("Vamos a leer las noticias.");
        List<Noticia> noticias = Rsscontrolador.getInstance()
                .getNoticias("https://api2.rtve.es/rss/temas_castilla-mancha.xml");
        if (noticias.size() > 0) {
            System.out.println("***Ultimas noticias***");
            for (Noticia noticia : noticias) {
                System.out.println(noticia);
            }
        } else {
            System.out.println("No hay noticias, alma de cantaro.");
        }
    }

    private static void Ejercicio4(Connection conexion) throws IOException {
        // Cogiendo como base la tabla ciclista, crea un fichero binario de acceso
        // aleatorio denominado “ciclismo.dat”. Sabiendo que se utilizará un objeto
        // serializado, se deberá guardar objetos como tal y leerlos como objetos
        // imprimíéndolos por pantalla. ¿Qué hay que poner y en qué elemento y dónde si
        // utilizamos distintas versiones de Java?
        ArrayList<Ciclista> ciclistas = new ArrayList<>();

        String sentenciaSQL = "SELECT * FROM ciclista";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSQL);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                Ciclista cicl = new Ciclista();
                cicl.setDorsal(resultado.getInt(1));
                cicl.setNombre(resultado.getString(2));
                cicl.setEdad(resultado.getInt(3));
                cicl.setNomeq(resultado.getString(4));
                ciclistas.add(cicl);
            }

            File fichero = new File("ciclismo.dat");
            // Declaramos el objeto
            // RandomAccessFile file = new RandomAccessFile(fichero, "rw");
            FileOutputStream fileOut = new FileOutputStream(fichero);
            ObjectOutputStream datasalida = new ObjectOutputStream(fileOut);

            for (int i = 0; i < ciclistas.size(); i++) {
                datasalida.writeObject(ciclistas.get(i));
            }
            datasalida.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            liberarRecursos(sentencia, resultado);

        }
    }

    private static void Ejercicio3(Connection conexion) throws JAXBException, IOException {
        // Con la tecnología JAXB, crea un fichero XML de la base de datos, de la tabla
        // ciclista.
        // Creamos contexto. Se le asigna la clase que engloba el XML
        JAXBContext contextEscritura = JAXBContext.newInstance(ConjuntoCiclista.class);
        Marshaller marshaller = contextEscritura.createMarshaller();
        // Creamos la información
        ConjuntoCiclista ciclistasJAXB = new ConjuntoCiclista();
        ciclistasJAXB.setNombre("Ciclistas");

        ArrayList<Ciclista> ciclistas = new ArrayList<>();
        Ciclista cicl = new Ciclista();

        String sentenciaSQL = "SELECT * FROM ciclista";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSQL);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                cicl.setDorsal(resultado.getInt(1));
                cicl.setNombre(resultado.getString(2));
                cicl.setEdad(resultado.getInt(3));
                cicl.setNomeq(resultado.getString(4));
                ciclistas.add(cicl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            liberarRecursos(sentencia, resultado);

            ciclistasJAXB.setCiclistas(ciclistas);

            // Para que salga bonito, o en un formato mejor setProperty
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(ciclistasJAXB, new FileWriter(".//ciclistas.xml"));

        }

    }

    private static void Ejercicio2apartadoB(Connection conexion) {
        // Obtener el nombre del equipo y el director del ciclista que ha ganado la
        // etapa más corta.

        String sentenciaSQL = "SELECT e.nomeq, director FROM equipo e, ciclista c WHERE e.nomeq=c.nomeq AND dorsal = ((SELECT dorsal FROM etapa WHERE km = (SELECT MIN(km) FROM etapa)))";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSQL);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                System.out.println("nomeq: " + resultado.getString(1));
                System.out.println("director: " + resultado.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            liberarRecursos(sentencia, resultado);
        }

    }

    private static void Ejercicio2apartadoA(Connection conexion, int altura) {
        // Obtener el dorsal y el nombre de los ciclistas que han ganado los puertos una
        // determinada altura (que se pase por parámetro a un método específico)
        String sentenciaSQL = "SELECT c.dorsal, nombre FROM ciclista c, puerto p WHERE c.dorsal=p.dorsal AND altura > ?";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSQL);
            sentencia.setInt(1, altura);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                System.out.println("dorsal: " + resultado.getInt(1));
                System.out.println("nombre: " + resultado.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // liberarRecursos(sentencia, resultado);
        }
    }

    private static void seleccionarBase(Connection conexion) {
        // Seleccionamos la base de datos
        try {
            conexion.setCatalog("ciclismo");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error");
        }
        ;
    }

    private static void Ejercicio1CreacionBBDD(Connection conexion) {
        PreparedStatement sentencia = null;
        // Sentencia SQL que crea la base de datos => String
        String creaBaseDatosSQL = "CREATE DATABASE IF NOT EXISTS ciclismo;";

        try {
            sentencia = conexion.prepareStatement(creaBaseDatosSQL);
            sentencia.executeUpdate();
            System.out.println("Base de datos creada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        liberarRecursos(sentencia);
    }

    private static Connection Ejercicio1(String url, String usuario, String contrasena) {
        // 1. Crea una conexión a la base de datos MySQL. Este servidor NO debe estar
        // corriendo de manera local. Se puede utilizar cualquier plataforma/servidor
        // (Docker, MV, Xampp, etc). No se puede utilizar un servidor local, deberás
        // utilizar cualquier otro método. Explica brevemente cómo lo haces, y crea la
        // base de datos "ciclismo"

        Connection conexion = null;

        try {
            // Cargar el controlador de mysql
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Establecemos la conexión
            conexion = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("Funciona correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conexion;
    }

    static void liberarRecursos(PreparedStatement sentencia) {
        // liberamos recursos
        if (sentencia != null) {
            try {
                sentencia.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static void liberarRecursos(PreparedStatement sentencia, ResultSet resultado) {
        // liberamos recursos
        if (sentencia != null) {
            try {
                sentencia.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            resultado.close();
        } catch (SQLException e) {
            System.out.println("Fallo al liberar el ResultSet");
            e.printStackTrace();
        }
    }

    static void PasaDe1Millon(int num) throws PasaDe1MillonException {
        if (num > 1000000) {
            throw new PasaDe1MillonException("El numero supera 1 millon");
        }
    }
}

class PasaDe1MillonException extends Exception {
    public PasaDe1MillonException(String msg) {
        super(msg);
    }
}
