import java.io.*;

public class VerInf {
    public static void main(String[] args) {
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
}
