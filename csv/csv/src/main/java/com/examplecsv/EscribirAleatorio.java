package com.examplecsv;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class EscribirAleatorio {
    public static void main(String[] args) throws IOException, CsvValidationException {

        List<Empleado> empleados = new ArrayList<Empleado>();
        List<Empleado> lectura = new ArrayList<Empleado>();

        escribirRegistros();
        empleados = leerTodosRegistros();
        System.out.println(empleados.get(1).toString());
        leerUnRegistro();

        exportarCSV(empleados);
        lectura = importarCSV();
        for (Empleado empleado : lectura) {
            System.out.println(empleado);
        }

    }

    private static List<Empleado> importarCSV() throws CsvValidationException, IOException {
        String entradaArchivo = ".//csv//csv//src//main//java//com//examplecsv//empleado.csv";
        // Vamos a verificar si existe el archivo o no
        boolean existe = new File(entradaArchivo).exists();
        List<Empleado> empleados = new ArrayList<>();

        if (existe) { // Si existe lo borramos
            File archivo = new File(entradaArchivo);
            CSVReader entradaCSV = new CSVReader(new FileReader(archivo));
            String datos[], linea[];
            while ((datos = entradaCSV.readNext()) != null) {
                linea = datos[0].toString().split(";");
                //System.out.println(datos[0]);
                if (StringUtils.isNumeric(linea[0])) {
                    Empleado emp = new Empleado(Integer.parseInt(linea[0]), linea[1].toString(), Integer.parseInt(linea[2]), Double.parseDouble(linea[3]));
                    empleados.add(emp);
                }
                /*for (String string : datos) {
                    System.out.println(string + "\t");
                }
                System.out.println();*/
            }
            entradaCSV.close();
        }
        return empleados;
    }

    private static void exportarCSV(List<Empleado> empleados) {
        String salidaArchivo = ".//csv//csv//src//main//java//com//examplecsv//empleado.csv";
        // Vamos a verificar si existe el archivo o no
        boolean existe = new File(salidaArchivo).exists();

        if (existe) { // Si existe lo borramos
            File archivote = new File(salidaArchivo);
            archivote.delete();
        }
        // Creamos el archivo
        try {
            CSVWriter salidaCSV = new CSVWriter(new FileWriter(salidaArchivo, true), ';', CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.NO_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

            // Dato para identificar las cabeceras
            String cabecera[] = { "id", "Apellidos", "Departamento", "Salario" };
            salidaCSV.writeNext(cabecera);

            // Metemos todos los datos
            // Recorremos toda la lista
            List<String[]> datos = new ArrayList<String[]>();
            for (Empleado emp : empleados) {
                // Forma 1 de hacerlo
                // String dato[] = {Integer.toString(emp.getId()), emp.getApellido(),
                // Integer.toString(emp.getDep()), Double.toString(emp.getSalario())};
                // salidaCSV.writeNext(dato, false);
                // Forma 2 de hacerlo
                datos.add(new String[] { Integer.toString(emp.getId()), emp.getApellido(),
                        Integer.toString(emp.getDep()), Double.toString(emp.getSalario()) });
            }
            salidaCSV.writeAll(datos, false);
            salidaCSV.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void escribirRegistros() throws IOException {
        File fichero = new File(".//csv//csv//src//main//java//com//examplecsv//aleatorioEmpleado.dat");
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

    private static List<Empleado> leerTodosRegistros() throws IOException {
        List<Empleado> empleados = new ArrayList<Empleado>();
        // Lectura
        File ficherete = new File(".//csv//csv//src//main//java//com//examplecsv//aleatorioEmpleado.dat");
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
                empleados.add(new Empleado(id, apellidos.trim(), dep, salario2));
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
        return empleados;
    }

    private static void leerUnRegistro() throws IOException {
        File fichero = new File(".//csv//csv//src//main//java//com//examplecsv//aleatorioEmpleado.dat");
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

            System.out.println("ID: " + registro + ", Apellido: " + apellidoS.trim() + ", Departamento: " + dep
                    + ", Salario: " + salario);
        }
        file.close(); // cerrar fichero

    }

    private static int pedirNumeroAlUsuario() {
        Scanner sc = new Scanner(System.in);

        // Pedimos al usuario un numero
        System.out.println("Por favor, ingresa un numero: ");

        int num = sc.nextInt();
        sc.close();

        return num;
    }
}
