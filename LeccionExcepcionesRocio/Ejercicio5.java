/* Rocío De Marco Carrasco 2ºDAM AD */

package LeccionExcepcionesRocio;

import java.util.Scanner;

class PrecioDemasiadoAltoException extends Exception {
    public PrecioDemasiadoAltoException(String msg) {
        super(msg);
    }
}

public class Ejercicio5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el precio sin iva");
        double numero = sc.nextDouble();
        try {
            System.out.println("El precio con IVA es: " + getPrecioConIva(numero));
        } catch (PrecioDemasiadoAltoException e) {
            System.out.println(e);
        } finally {
            sc.close();
        }
    }

    static double getPrecioConIva(double num) throws PrecioDemasiadoAltoException {
        double precioConIVA = num * 1.16;
        if (precioConIVA > 100) {
            throw new PrecioDemasiadoAltoException(
                    "El precio excede de 100 euros, el total sería " + precioConIVA + ".");
        } else
            return num * 1.16;
    }
}
