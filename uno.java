import java.io.*;

public class uno {
    public static void main(String[] args) {
        String dir = ".";
        File f = new File(dir);
        String[] archivos = f.list();
        System.out.printf("Ficheros en el directorio actual: %d %n", archivos.length);
        for (int i = 0; i < archivos.length; i++) {
            File fichero2 = new File(f, archivos[i]);
            System.out.printf("Nombre: %s, es fichero?: %b, es directorio? %b %n", archivos[i], fichero2.isFile(), fichero2.isDirectory());
        }
    }
}
