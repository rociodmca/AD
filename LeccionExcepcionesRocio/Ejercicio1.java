/* Rocío De Marco Carrasco 2ºDAM AD */

package LeccionExcepcionesRocio;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) throws InputMismatchException {
        Scanner sc = new Scanner(System.in);
        int numero;

        for (int i = 0; i < 4; i++) {
            System.out.println("Introduce un número: ");
            try {
                numero = sc.nextInt();
                Rango(numero);
            } catch (NumberFormatException nfe) {
                System.out.println(nfe.getStackTrace());
            } catch (NumeroExcedenteException nee) {
                nee.printStackTrace();
            }
        }
        sc.close();
    }

    static void Rango(int num) throws NumeroExcedenteException {
        if (num > 100 || num < 0) {
            throw new NumeroExcedenteException("Numero fuera de rango");
        }
    }
}

class NumeroExcedenteException extends Exception {
    public NumeroExcedenteException(String msg) {
        super(msg);
    }
}
