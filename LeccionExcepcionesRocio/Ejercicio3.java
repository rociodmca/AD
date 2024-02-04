/* Rocío De Marco Carrasco 2ºDAM AD */

package LeccionExcepcionesRocio;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ejercicio3 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        double n = 0;
        int posicion = 0;
        String cadena = "";
        double[] valores = { 9.83, 4.5, -3.06, 0.06, 2.52, -11.3, 7.60, 3.00, -30.4, 105.2 };

        System.out.println("Contenido del array antes de modificar:");
        for (int i = 0; i < valores.length; i++) {
            System.out.printf("%.2f ", valores[i]);
        }
        System.out.print("\n\nIntroduce la posicion del array a modificar: ");
        try {
            cadena = sc.nextLine();
        } catch (NoSuchElementException | IllegalStateException e) {
            e.printStackTrace();
        }
        try {
            posicion = Integer.parseInt(cadena);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

        System.out.print("\nIntroduce el nuevo valor de la posicion " + posicion + ": ");
        try {
            n = sc.nextDouble();
        } catch (NumberFormatException | InputMismatchException e) {
            e.printStackTrace();
        }

        try {
            valores[posicion] = n;
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            aiobe.printStackTrace();
        }

        System.out.println("\nPosicion a modificar " + posicion);
        System.out.println("Nuevo valor: " + n);
        System.out.println("Contenido del array modificado:");
        for (int i = 0; i < valores.length; i++) {
            System.out.printf("%.2f ", valores[i]);
        }
    }
}
