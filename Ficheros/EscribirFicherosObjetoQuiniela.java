import java.io.*;
import java.util.Random;

public class EscribirFicherosObjetoQuiniela {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Quiniela quiniela;
        File fichero = new File(".//Ficheros//FicheroQuiniela.dat");
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
            System.out.println(quiniela.toString());
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
}
