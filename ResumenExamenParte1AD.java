import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

public class ResumenExamenParte1AD {
    //No funciona
    public static void main(String[] args) {
        //Ficheros
        CrearDir();

        try {
            EscribirBytes();
            LeerFicheBytes();
            EscribirFicheroData();
            EscribirFichTexto();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //Serialización de objetos
            EscribirFicheroObjeto();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        VerInf();
        ListarFicheros();

        //DOM y SAX
        //Carpetas XML_DOM y XML_SAX

        //Serialización de objetos con JavaNIO
        //Ejercicio 6 examen 29/11/2022

        //Conversion XML
        //Ejercicio 1 examen 29/11/2024
        //MIXSL (maven)

        //Excepciones
        //Lección excepciones

        //JAXB
        //JAXB (maven)

        //JSON
        //JSONGSON (maven)

        //JAVANIO (maven)

        //RSS

        //CONEXION A BBDD (sin mvc)
    }

    private static void ListarFicheros() {
        String dir = ".";
        File f = new File(dir);
        String[] archivos = f.list();
        System.out.printf("Ficheros en el directorio actual: %d %n", archivos.length);
        for (int i = 0; i < archivos.length; i++) {
            File fichero2 = new File(f, archivos[i]);
            System.out.printf("Nombre: %s, es fichero?: %b, es directorio? %b %n", archivos[i], fichero2.isFile(), fichero2.isDirectory());
        }
    }

    private static void VerInf() {
        String dir = "uno.java";
        File f = new File(dir);
        System.out.println("Información sobre el fichero.\n");
        if (f.exists()) {
            if (!f.isFile()) {
                System.out.println("Ficheros y directorios asociados.\n");
                for (String string : f.list()) {
                    System.out.println(string);
                }
                System.out.println("Ficheros del directorio\n");
                for (File fichero : f.listFiles()) {
                    System.out.println(fichero);
                }
            }
            System.out.println("Nombre: " + f.getName() + "\n");
            System.out.println("Ruta relativa: " + f.getPath() + "\n");
            System.out.println("Ruta absoluta: " + f.getAbsolutePath() + "\n");
            System.out.println("Se puede escribir? " + f.canWrite() + " y leer? " + f.canRead() + "\n");
            System.out.printf("Es fichero?: %b, es directorio? %b %n", f.isFile(), f.isDirectory() + "\n");
            System.out.println("Tamano: " + f.length() + "\n");
            System.out.println("El padre es: " + f.getParent() + "\n");
        }
    }

    private static void EscribirFichTexto() throws IOException {
        File fichero = new File("FicheroTexto.txt");
        FileWriter fic = new FileWriter(fichero);
        String cadena = "Esto es una prueba con FileWriter";
        char[] cad = cadena.toCharArray();

        for (int i = 0; i < cad.length; i++) {
            fic.write(cad[i]);
        }

        fic.append("*");
        fic.write(cad);
        String c = "\n Esto es todo amigos!!!";
        fic.write(c);

        String prov[] = {"Albacete", "Cuenca", "Guadalajara", "Ciudad Real", "Toledo"};
        fic.write("\n\n\n");
        for (int i = 0; i < prov.length; i++) {
            fic.write(prov[i]);
            fic.write("\n");
        }

        fic.close();

        try{
            BufferedWriter fichero2 = new BufferedWriter(new FileWriter("FicheroTexto1.txt"));
            for (int i = 0; i < 10; i++) {
                fichero2.write("Mi numero de fila: " + i);
                fichero2.newLine();
            }
            fichero2.close();
        } catch (Exception e) {

        }

        try {
            BufferedReader ficherete = new BufferedReader(new FileReader("FicheroTexto2.txt"));
            String linea;
            System.out.println("Leyendo el archivete.\n");
            while ((linea = ficherete.readLine())!=null) {
                System.out.println(linea);
            }
            ficherete.close();
        } catch (Exception e) {
        }
    }

    private static void LeerFicheBytes() throws IOException {
        File fichero = new File("ficheroBytes.dat");
        FileInputStream fic = new FileInputStream(fichero); // Descriptor de fichero
        int i;
        char letra;

        while ((i = fic.read()) != -1) {
            if ((char) i != ' ') {
                letra = Character.toLowerCase((char) i);
                System.out.print(cambiarLetras(letra));
            }
        }
        fic.close();
    }

    //Codigo cesar
    public static char cambiarLetras(char letra) {
        char[] abec = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
        int posicion = 0;
        for (int j = 0; j < abec.length - 1; j++) {
            if (abec[j] == letra) {
                posicion = j;
            }
        }
        if ((posicion + 3) > abec.length - 1) {
            return abec[(posicion + 3) - abec.length];
        } else {
            return abec[posicion + 3];
        }
    }

    private static void EscribirFicheroObjeto() throws IOException, ClassNotFoundException {
        Quiniela quiniela;
        File fichero = new File(".\\Ficheros\\FicheroQuiniela.dat");
        FileOutputStream fileOut = new FileOutputStream(fichero);
        ObjectOutputStream datasalida = new ObjectOutputStream(fileOut);

        String equipos[] = { "Real Madrid", "Barcelona", "Almeria", "Rayo Vayecano", "Granada" };
        char resultados[] = { 'x', '1', '2' };
        int indice;
        boolean flag;

        Random rnd = new Random();

        // Escritura de objetos
        for (int i = 0; i < equipos.length; i++) {
            do {
                indice = rnd.nextInt(equipos.length);
                if (indice != i) flag = false;
                else flag = true;
            } while (flag);
            quiniela = new Quiniela(equipos[i], equipos[indice], resultados[rnd.nextInt(resultados.length)]);
            datasalida.writeObject(quiniela);
        }

        datasalida.close();

        //Lectura de objetos
        ObjectInputStream dataentrada = new ObjectInputStream(new FileInputStream(fichero));
        try {
            while (true) {
                quiniela = (Quiniela)dataentrada.readObject();
                System.out.println(quiniela.toString());
            }
        } catch (EOFException eof) {
            dataentrada.close();
        }
    }

    private static void EscribirFicheroData() throws IOException {
        File fichero = new File("FicheroData.dat");
        DataOutputStream datasalida = new DataOutputStream(new FileOutputStream(fichero));
        
        String nombres[] = {"Judas", "Hay", "Tormenta", "Sopa", "Castellana"};
        int edades[] = {20, 30, 45, 46, 23};

        for (int i = 0; i < edades.length; i++) {
            datasalida.writeUTF(nombres[i]);
            datasalida.writeInt(edades[i]);
        }

        datasalida.close();

        //Lectura
        DataInputStream dataentrada = new DataInputStream(new FileInputStream(fichero));
        String nom;
        int edad;
        try {
            while (true) {
            nom = dataentrada.readUTF();
            edad = dataentrada.readInt();
            System.out.println("Nombre= " + nom + " edad= " + edad);
        }
        } catch (EOFException eof) {
            dataentrada.close();
            //eof.printStackTrace();
        }
    }

    private static void EscribirBytes() throws IOException {
        File fichero = new File("FicheroBytes2.dat");
        FileOutputStream fileout = new FileOutputStream(fichero);
        FileInputStream filein = new FileInputStream(fichero);
        int j;
        
        for (int i = 0; i < 100; i++) {
            fileout.write(i);
        }
        fileout.close();

        while ((j = filein.read())!=-1) {
            System.out.println(j);
        }
        filein.close();
    }

    private static void CrearDir() {
        File d = new File("NUEVODIR"); // directorio que creo
        File f1 = new File(d, "FICHERO1.TXT");
        File f2 = new File(d, "FICHERO2.TXT");

        d.mkdir();// CREAR DIRECTORIO

        try {
            if (f1.createNewFile())
                System.out.println("FICHERO1 creado correctamente ... ");
            else
                System.out.println("No se ha podido crear FICHERO1 ... ");

            if (f2.createNewFile())
                System.out.println("FICHERO2 creado correctamente ... ");
            else
                System.out.println("No se ha podido crear FICHERO2 ... ");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        f1.renameTo(new File(d, "FICHEROINUEVO.TXT"));// renombro FICHER01

        try {
            File f3 = new File("NUEVODIR/FICHERO3.TXT");
            f3.createNewFile();// crea FICHERO3 en NUEVODIR
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
