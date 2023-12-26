import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EscribirFicheroData {
    //Escribir datos primitivos en binario
    public static void main(String[] args) throws IOException {
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
}
