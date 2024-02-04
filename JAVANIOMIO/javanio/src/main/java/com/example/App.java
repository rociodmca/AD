package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // Clase FileSystem
        // ejemplo1();
        // ejemplo2();
        // ejemplo3();
        // ejemplo4();
        // ejemplo5();
        // ejemplo6();
         ejemplo7();
        //ejemplo8();
    }

    private static void ejemplo8() {
        Path ficherete = Paths.get(
                "C:\\Users\\rocio\\OneDrive\\DAM2\\AD\\AD\\AD\\JAVANIOMIO\\javanio\\src\\main\\java\\com\\example\\lista.sz");
        // Serializacion de objetos
        ObjectOutputStream serializarO;
        ObjectInputStream serializarI;
        List<String> nombres, nombresAux;
        nombres = new ArrayList<>();
        nombres.add("Pepito");
        nombres.add("Baldomero");
        nombres.add("Juanito");
        nombres.add("Apretao");
        nombres.add("SoyDemasiadoFeo");

        try {
            if (Files.notExists(ficherete)) {
                // Si no existe
                serializarO = new ObjectOutputStream(new FileOutputStream(ficherete.toString()));
                serializarO.writeObject(nombres);
                serializarO.close(); //cerramos
            } else {
                nombresAux = new ArrayList<>();
                serializarI = new ObjectInputStream(new FileInputStream(ficherete.toString()));
                nombresAux = (List<String>) serializarI.readObject();
                //Lo imprimimos por pantalla
                for (String nombre : nombresAux) {
                    System.out.println(nombre);
                }
                serializarI.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void ejemplo7() {

        // Escritura utilizando un canal
        // Creamos un archivo y obtenemos el canal para esribir en él
        try (FileOutputStream fos = new FileOutputStream("JAVANIOMIO\\javanio\\src\\main\\java\\com\\example\\numeritos.bin");){
            
            FileChannel canal = fos.getChannel();

            // Creamos un buffer y ponemos algún numerico
            ByteBuffer buffer = ByteBuffer.allocate(4 * Integer.BYTES);
            for (int i = 0; i < 4; i++) {
                buffer.putInt(i);
            }

            // Preparamos el buffer para escribir
            buffer.flip();
            // Escribimos en el canal
            canal.write(buffer);

            fos.close();

            // Lectura utilizando un canal
            FileInputStream fis = new FileInputStream(
                    "JAVANIOMIO\\javanio\\src\\main\\java\\com\\example\\numeritos.bin");
            canal = fis.getChannel();

            // Creamos un búffer para la lectura con el tamaño adecuado
            buffer = ByteBuffer.allocate(Integer.BYTES * 4);

            // Leemos del archivo en el buffer
            canal.read(buffer);
            // Preparar el búffer para la lectura
            // buffer.flip(); //Reinicia el puntero que está leyendo
            buffer.position(0);
            buffer.limit(16);

            // buffer
            // bytes 1-4(int=0): 00000000 00000000 00000000 00000000
            // bytes 5-8(int=1): 00000000 00000000 00000000 00000001
            // bytes 9-12(int=2): 00000000 00000000 00000000 00000010
            // bytes 13-16(int=3): 00000000 00000000 00000000 00000011

            // Leemos los enteros del búffer
            while (buffer.hasRemaining()) {
                System.out.println(buffer.getInt());
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void ejemplo6() throws FileNotFoundException, IOException {
        String file = "C:\\Users\\rocio\\OneDrive\\DAM2\\AD\\AD\\AD\\JAVANIOMIO\\javanio\\src\\main\\java\\com\\example\\filechannel.txt";
        try (RandomAccessFile writer = new RandomAccessFile(file, "rw");
                FileChannel channel = writer.getChannel()) {
            ByteBuffer buff = ByteBuffer.wrap("Hola Mundo".getBytes(StandardCharsets.UTF_8));

            channel.write(buff);
        }
    }

    private static void ejemplo5() {
        Path origen = Paths.get(
                "C:\\Users\\rocio\\OneDrive\\DAM2\\AD\\AD\\AD\\JAVANIOMIO\\javanio\\src\\main\\java\\com\\example\\origen\\origen.txt");
        Path destino = Paths.get(
                "C:\\Users\\rocio\\OneDrive\\DAM2\\AD\\AD\\AD\\JAVANIOMIO\\javanio\\src\\main\\java\\com\\example\\destino\\destino.txt");
        try {
            BufferedReader inputReader = Files.newBufferedReader(origen, Charset.defaultCharset());
            BufferedWriter outputWriter = Files.newBufferedWriter(destino, Charset.defaultCharset(),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            String linea;
            while ((linea = inputReader.readLine()) != null) {
                outputWriter.write(linea, 0, linea.length());
                outputWriter.newLine();
            }
            inputReader.close();
            outputWriter.close();
        } catch (IOException e) {
            System.err.println("Errrrrrrrrror " + e);
            System.exit(-1);
        }
    }

    private static void ejemplo4() {
        /*
         * Modos de acceso, el parámetro OpenOptions
         * WRITE: habilita la escritura en el fichero
         * APPEND: todo lo escrito al fichero se hará al final del mismo
         * CREATE_NEW: crea un fichero nuevo y lanza una excepción si ya existía
         * CREATE: crea el fichero si no existe y simplemente lo abre si ya existía
         * TRUNCATE_EXISTING: si el fichero existe, y tiene contenido, se ignora su
         * contenido para sobreescribirlo desde el principio.
         */
        // Escritura de ficheros mediante arrays
        Path origen = Paths.get(
                "C:\\Users\\rocio\\OneDrive\\DAM2\\AD\\AD\\AD\\JAVANIOMIO\\javanio\\src\\main\\java\\com\\example\\origen\\origen.txt");
        Path destino = Paths.get(
                "C:\\Users\\rocio\\OneDrive\\DAM2\\AD\\AD\\AD\\JAVANIOMIO\\javanio\\src\\main\\java\\com\\example\\destino\\destino.txt");
        try {
            byte[] contenido = Files.readAllBytes(origen);
            Files.write(destino, contenido, StandardOpenOption.WRITE, StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println("Errrrrrrrrrrrrrrrrrrror " + e);
            System.exit(-1);
        }
    }

    private static void ejemplo3() {
        Path origen = Paths.get(
                "C:\\Users\\rocio\\OneDrive\\DAM2\\AD\\AD\\AD\\JAVANIOMIO\\javanio\\src\\main\\java\\com\\example\\origen\\origen.txt");
        Path destino = Paths.get(
                "C:\\Users\\rocio\\OneDrive\\DAM2\\AD\\AD\\AD\\JAVANIOMIO\\javanio\\src\\main\\java\\com\\example\\destino\\destino.txt");
        try {
            Files.copy(origen, destino);
        } catch (FileAlreadyExistsException e) {
            System.out.println("Ostras tu, el archivo ya existe. Deja de fumar.");
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private static void ejemplo2() {
        // Ejemplo2: Creación y borrado de directorios
        Path path = Paths.get(
                "C:\\Users\\rocio\\OneDrive\\DAM2\\AD\\AD\\AD\\JAVANIOMIO\\javanio\\src\\main\\java\\com\\example\\prueba");
        try {
            if (Files.exists(path)) {
                System.out.println("Existe el directorio y procedemos a eliminarlos");
                Files.delete(path);
            } else {
                System.out.println("No existe y lo creamos.");
                Files.createDirectory(path);
            }
        } catch (Exception e) {

        }
    }

    private static void ejemplo1() {
        Path path = Paths.get(
                "C:\\Users\\rocio\\OneDrive\\DAM2\\AD\\AD\\AD\\JAVANIOMIO\\javanio\\src\\main\\java\\com\\example\\hola.txt");
        System.out.println(" path = " + path);
        System.out.println(" existencia = " + Files.exists(path));
        System.out.println(" lectura = " + Files.isReadable(path));
        System.out.println(" escritura = " + Files.isWritable(path));
        System.out.println(" ejecutable = " + Files.isExecutable(path));
    }
}
