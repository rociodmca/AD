import java.io.*;

public class EscribirBytes {
    public static void main(String[] args) throws IOException {
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
}
