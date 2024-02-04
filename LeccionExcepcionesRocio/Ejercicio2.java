/* Rocío De Marco Carrasco 2ºDAM AD */

package LeccionExcepcionesRocio;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 2; i++) {
            System.out.println(LeerEntero(sc));
        }
        for (int i = 0; i < 2; i++) {
            System.out.println(LeerDouble(sc));
        }
        sc.nextLine();
        System.out.println("Introduce una cadena");
        String cad = sc.nextLine();
        System.out.println(cad);
        sc.close();
    }

    static int LeerEntero(Scanner sc) throws InputMismatchException, NumberFormatException {
        int numero;
        System.out.println("Introduce un entero: ");
        numero = sc.nextInt();
        return numero;
    }

    static double LeerDouble(Scanner sc) throws InputMismatchException, NumberFormatException {
        double numero;
        System.out.println("Introduce un double: ");
        numero = sc.nextDouble();
        return numero;
    }
}
