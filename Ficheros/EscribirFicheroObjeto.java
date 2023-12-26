import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class EscribirFicheroObjeto {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Persona persona;
        File fichero = new File("FicheroPersona.dat");
        FileOutputStream fileOut = new FileOutputStream(fichero);
        ObjectOutputStream datasalida = new ObjectOutputStream(fileOut);

        String nombres[] = { "Aba", "Vicioso", "Hoy", "Llueve" };
        int edades[] = { 20, 25, 30, 25 };

        // Escritura de objetos
        for (int i = 0; i < edades.length; i++) {
            persona = new Persona(nombres[i], edades[i]);
            datasalida.writeObject(persona);
        }

        datasalida.close();

        //Lectura de objetos
        ObjectInputStream dataentrada = new ObjectInputStream(new FileInputStream(fichero));
        try {
            while (true) {
                persona = (Persona)dataentrada.readObject();
                System.out.println(persona.toString());
            }
        } catch (EOFException eof) {
            dataentrada.close();
        }

    }
}
