import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LeerFicheBytes {
    public static void main(String[] args) throws IOException {
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
}
