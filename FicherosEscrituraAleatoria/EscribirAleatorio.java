import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class EscribirAleatorio {
    public static void main(String[] args) throws IOException {
        
        escribirRegistros();
        leerTodosRegistros();
        leerUnRegistro();
        
    }

    private static void escribirRegistros() throws IOException {
        File fichero = new File(".//FicherosEscrituraAleatoria//aleatorioEmpleado.dat");
        // Declaramos el objeto
        RandomAccessFile file = new RandomAccessFile(fichero, "rw");

        // Datos que quiero meter en el archivote
        String apellido[] = { "García", "Tormenta", "Flordel", "Trebol", "Flash", "Ramos", "Sevilla", "Calles",
                "Khalesy", "Sensei" };
        int departamento[] = { 10, 20, 10, 30, 42, 25, 49, 32, 45, 65 };
        Double salario[] = { 100.1, 154.54, 1324.12, 123.78, 654.78, 4568.13, 126.36, 12345.78, 4567.146, 4659.123 };

        StringBuffer buffer = null;
        int n = apellido.length;

        for (int i = 0; i < n; i++) {
            file.writeInt(i + 1);
            buffer = new StringBuffer(apellido[i]);
            buffer.setLength(10); // 10 caracteres por apellido
            file.writeChars(buffer.toString()); // insertamos el apellido
            file.writeInt(departamento[i]);
            file.writeDouble(salario[i]);
        }
        file.close();
    }

    private static void leerTodosRegistros() throws IOException {
        // Lectura
        File ficherete = new File(".//FicherosEscrituraAleatoria//aleatorioEmpleado.dat");
        RandomAccessFile file2 = new RandomAccessFile(ficherete, "r");
        // Variables para recoger datos
        int id, dep;
        double salario2;
        char apellidoAux[] = new char[10], aux;
        // Variables para la localización de los datos
        int posicion = 0;

        // Recorremos el fichero
        for (;;) {
            // Nos colocamos en la posición del fichero
            file2.seek(posicion);
            id = file2.readInt();
            // Leemos el apellido
            // Recorremos uno a uno los caracteres
            for (int i = 0; i < apellidoAux.length; i++) {
                aux = file2.readChar();
                apellidoAux[i] = aux; // los voy guardando en el array
            }
            // Convertimos el array en string
            String apellidos = new String(apellidoAux);
            // Leemos el departamento
            dep = file2.readInt();
            salario2 = file2.readDouble();
            if (id > 0) { // Hemos leído algo
                System.out.printf("ID: %s, Apellidos %s, Departamento: %d, Salario: %.2f %n", id, apellidos.trim(), dep,
                        salario2);
            }

            // Movemos el puntero al siguiente elemento del fichero.
            // Cada registro ocupa 36 bytes
            // Double 8bytes
            // Int 4bytes
            // Int 4bytes
            // 10 *2bytes = 20bytes
            posicion += 36;

            // Si he leido todo me salgo del bucle
            if (file2.getFilePointer() == file2.length()) {
                break;
            }
        }

        file2.close();
    }

    private static void leerUnRegistro() throws IOException {
        File fichero = new File(".//FicherosEscrituraAleatoria//aleatorioEmpleado.dat");
        // declara el fichero de acceso aleatorio
        RandomAccessFile file = new RandomAccessFile(fichero, "r");

        int id, dep, posicion;
        Double salario;
        char apellido[] = new char[10], aux;

        int registro = pedirNumeroAlUsuario();
        posicion = (registro - 1) * 36;
        if (posicion >= file.length())
            System.out.printf("ID: %d, NO EXISTE EMPLEADO ... ", registro);
        else {
            file.seek(posicion); // nos posicionamos
            id = file.readInt(); // obtengo id de empleado
            for (int i = 0; i < apellido.length; i++) {
                aux = file.readChar();// recorro uno a uno los caracteres del apellido
                apellido[i] = aux;// los voy guardando en el array
            }
            String apellidoS = new String(apellido);// convierto a String el array
            dep = file.readInt();// obtengo dep
            salario = file.readDouble(); // obtengo salario

            System.out.println("ID: " + registro + ", Apellido: " + apellidoS.trim() + ", Departamento: " + dep + ", Salario: " + salario);
        }
        file.close(); // cerrar fichero

    }

    public static int pedirNumeroAlUsuario() {
        Scanner sc = new Scanner(System.in);

        // Pedimos al usuario un numero
        System.out.println("Por favor, ingresa un numero: ");

        int num = sc.nextInt();

        return num;
    }
}
