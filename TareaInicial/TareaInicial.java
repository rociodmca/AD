package TareaInicial;
//Rocio De Marco Carrasco 
//Acceso a Datos
//2ºDAM

import java.util.*;

public class TareaInicial {
    public static void main(String[] args) {
        // Pilas
        Stack<Persona> pilaPersonas = inicio();
        Persona pers;
        
        System.out.println("Pila -> Stack -> estructura LIFO (Last In First Out)");
        System.out.println("La pila esta vacia? " + esVacia(pilaPersonas));
        mostrar(pilaPersonas);
        System.out.println("");
        System.out.println("Busco a Maria");
        pers = busqueda(pilaPersonas, "Maria");
        System.out.println(pers.ToString());
        System.out.println("");
        System.out.println("Elimino a Juan");
        eliminarPersona(pilaPersonas, "Juan");
        mostrar(pilaPersonas);
        System.out.println("");
        System.out.println("Elimino al último que se introdujo");
        eliminarUltimoIntroducido(pilaPersonas);
        mostrar(pilaPersonas);
        System.out.println("");
        System.out.println("Vacio la pila");
        vaciado(pilaPersonas);
        System.out.println("La pila esta vacia? " + esVacia(pilaPersonas));

        // Colas
        Queue<Persona> colaPersonas = inicioColas();

        System.out.println("Cola -> Queue -> estructura FIFO (First In First Out)");
        System.out.println("La cola esta vacia? " + esVacia(colaPersonas));
        mostrar(colaPersonas);
        System.out.println("");
        System.out.println("Busco a Maria");
        pers = busqueda(colaPersonas, "Maria");
        System.out.println(pers.ToString());
        System.out.println("");
        System.out.println("Elimino a Juan");
        eliminarPersona(colaPersonas, "Juan");
        mostrar(colaPersonas);
        System.out.println("");
        System.out.println("Elimino al primero que se introdujo");
        eliminarPrimeroIntroducido(colaPersonas);
        mostrar(colaPersonas);
        System.out.println("");
        System.out.println("Primera persona de la cola");
        pers = primeraPersona(colaPersonas);
        System.out.println(pers.ToString());
        System.out.println("");
        System.out.println("Vacio la pila");
        vaciado(colaPersonas);
        System.out.println("La pila esta vacia? " + esVacia(pilaPersonas));
    }

    public static int numAleatorio(int num) {
        Random rnd = new Random();
        return rnd.nextInt(num) + 1;
    }

    // Metodos pilas
    public static Stack<Persona> inicio() {
        Stack<Persona> pila = new Stack<Persona>();
        pila.push(new Persona("Juan", numAleatorio(100)));
        pila.push(new Persona());
        pila.push(new Persona("Maria", numAleatorio(100)));
        pila.push(new Persona("Rosa", numAleatorio(100)));
        pila.push(new Persona("Emilio", numAleatorio(100)));
        pila.push(new Persona("Carlos", numAleatorio(100)));
        return pila;
    }

    public static void vaciado(Stack<Persona> pila) {
        pila.clear();
    }

    public static boolean esVacia(Stack<Persona> pila) {
        return pila.isEmpty();
    }

    public static void mostrar(Stack<Persona> pila) {
        for (Persona persona : pila) {
            System.out.println(persona.ToString());
        }
    }

    public static Persona busqueda(Stack<Persona> pila, String nombre) {
        for (Persona persona : pila) {
            if (persona.getNombre() == nombre) {
                return persona;
            }
        }
        return null;
    }

    public static void eliminarPersona(Stack<Persona> pila, String nombre) {
        Persona buscado = busqueda(pila, nombre);
        if (buscado != null) {
            pila.remove(buscado);
        }
    }

    public static void eliminarUltimoIntroducido(Stack<Persona> pila) {
        pila.pop();
    }

    // Metodos colas
    public static Queue<Persona> inicioColas() {
        Queue<Persona> cola = new LinkedList<>();
        cola.add(new Persona("Juan", numAleatorio(100)));
        cola.add(new Persona());
        cola.add(new Persona("Maria", numAleatorio(100)));
        cola.add(new Persona("Rosa", numAleatorio(100)));
        cola.add(new Persona("Emilio", numAleatorio(100)));
        cola.add(new Persona("Carlos", numAleatorio(100)));
        return cola;
    }

    public static void vaciado(Queue<Persona> cola) {
        cola.clear();
    }

    public static boolean esVacia(Queue<Persona> cola) {
        return cola.isEmpty();
    }

    public static void mostrar(Queue<Persona> cola) {
        for (Persona persona : cola) {
            System.out.println(persona.ToString());
        }
    }

    public static Persona busqueda(Queue<Persona> cola, String nombre) {
        for (Persona persona : cola) {
            if (persona.getNombre() == nombre) {
                return persona;
            }
        }
        return null;
    }

    public static void eliminarPersona(Queue<Persona> cola, String nombre) {
        Persona buscado = busqueda(cola, nombre);
        if (buscado != null) {
            cola.remove(buscado);
        }
    }

    public static void eliminarPrimeroIntroducido(Queue<Persona> cola) {
        cola.remove();
    }

    public static Persona primeraPersona(Queue<Persona> cola) {
        return cola.peek();
    }


}