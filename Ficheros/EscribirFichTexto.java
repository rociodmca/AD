import java.io.*;

public class EscribirFichTexto {
    public static void main(String[] args) throws IOException {
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
    
}