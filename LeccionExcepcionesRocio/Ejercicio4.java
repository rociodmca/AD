/* Rocío De Marco Carrasco 2ºDAM AD */

package LeccionExcepcionesRocio;

import java.util.Scanner;

class NumeroNegativoException extends Exception {
    public NumeroNegativoException(String msg) {
        super(msg);
    }
}

class NumeroFactorizadoDemasiadoGrandeException extends Exception {
    public NumeroFactorizadoDemasiadoGrandeException(String msg) {
        super(msg);
    }
}

class Factorial {
    private int num;

    public Factorial(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int FactorialNumero() throws NumeroExcedenteException, NumeroFactorizadoDemasiadoGrandeException {
        int factorizado = num;
        if (num <= 0)
            throw new NumeroExcedenteException("El numero es negativo, no se puede factorizar.");
        for (int i = (num - 1); i > 0; i--) {
            factorizado = factorizado * i;
            if (factorizado <= 0)
                throw new NumeroFactorizadoDemasiadoGrandeException(
                        "El numero factorizado excede los límites de un número entero.");
        }
        return factorizado;
    }
}

public class Ejercicio4 {
    public static void main(String[] args) {
        int num = PedirNumero();
        Factorial numFactorial = new Factorial(num);
        System.out.println(numFactorial.getNum());
        try {
            System.out.println("El factorial de " + num + " es " + numFactorial.FactorialNumero());
        } catch (NumeroExcedenteException e) {
            System.out.println(e);
        } catch (NumeroFactorizadoDemasiadoGrandeException e) {
            System.out.println(e);
        }
    }

    static int PedirNumero() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce un numero entero:");
        try {
            int num = sc.nextInt();
            sc.close();
            return num;
        } catch (NumberFormatException nfe) {
            System.out.println(nfe);
            return 0;
        } finally {
            sc.close();
        }
    }
}
