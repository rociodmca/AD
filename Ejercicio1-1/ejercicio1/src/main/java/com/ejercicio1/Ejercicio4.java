package com.ejercicio1;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class Ejercicio4 {
    public static void main(String[] args) throws IOException, CsvValidationException {
        List<Objeto> listaObjetos = new ArrayList<>();
        List<Objeto> lectura = new ArrayList<>();
        
        escribirRegistros();
        listaObjetos = leerTodosRegistros();

        exportarCSV(listaObjetos);
        lectura = importarCSV();
        for (Objeto objeto : lectura) {
            System.out.println(objeto);
        }
    }

    private static List<Objeto> importarCSV() throws CsvValidationException, IOException {
        String entradaArchivo = "ejercicio1\\src\\main\\java\\com\\ejercicio1\\ciudades.csv";
        // Vamos a verificar si existe el archivo o no
        boolean existe = new File(entradaArchivo).exists();
        List<Objeto> objetos = new ArrayList<>();

        if (existe) { // Si existe lo borramos
            File archivo = new File(entradaArchivo);
            CSVReader entradaCSV = new CSVReader(new FileReader(archivo));
            String datos[], linea[];
            while ((datos = entradaCSV.readNext()) != null) {
                linea = datos[0].toString().split(";");
                //System.out.println(datos[0]);
                if (StringUtils.isNumeric(linea[0])) {
                    Objeto obj = new Objeto(linea[1].toString(), Integer.parseInt(linea[2]), Integer.parseInt(linea[3]), linea[4].toString(), linea[5].toString(), linea[6].toString());
                    objetos.add(obj);
                }
                //for (String string : datos) {
                //    System.out.println(string + "\t");
                //}
                //System.out.println();
            }
            entradaCSV.close();
        }
        return objetos;
    }

    private static void exportarCSV(List<Objeto> objetos) {
        String salidaArchivo = "ejercicio1\\src\\main\\java\\com\\ejercicio1\\ciudades.csv";
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
            String cabecera[] = { "id", "Ciudad", "Temperatura", "Presion", "DNI", "Nombre Alumno", "Apellidos" };
            int i = 1;
            salidaCSV.writeNext(cabecera);

            // Metemos todos los datos
            // Recorremos toda la lista
            List<String[]> datos = new ArrayList<String[]>();
            for (Objeto obj : objetos) {
                // Forma 1 de hacerlo
                // String dato[] = {Integer.toString(emp.getId()), emp.getApellido(),
                // Integer.toString(emp.getDep()), Double.toString(emp.getSalario())};
                // salidaCSV.writeNext(dato, false);
                // Forma 2 de hacerlo
                datos.add(new String[] { Integer.toString(i), obj.getCiudad(), Integer.toString(obj.getTemperatura()), 
                            Integer.toString(obj.getPresion()), obj.getDni(), obj.getNombre(), obj.getApellido() });
                i++;
            }
            salidaCSV.writeAll(datos, false);
            salidaCSV.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void escribirRegistros() throws IOException {
        File fichero = new File("ejercicio1\\src\\main\\java\\com\\ejercicio1\\ciudades.dat");
        // Declaramos el objeto
        RandomAccessFile file = new RandomAccessFile(fichero, "rw");

        // Datos que quiero meter en el archivote
        Objeto objetosEscribir[] = {new Objeto("Cuenca", 12, 1000, "04635262A", "Rocio", "De Marco"),
                                    new Objeto("Madrid", 23, 2000, "04635262A", "Rocio", "De Marco"),
                                    new Objeto("Barcelona", 12, 2001, "04635262A", "Rocio", "De Marco"),
                                    new Objeto("Alicante", 5, 1200, "04635262A", "Rocio", "De Marco"),
                                    new Objeto("New York", 1, 850, "04635262A", "Rocio", "De Marco")};

        StringBuffer buffer = null;
        int n = objetosEscribir.length;

        for (int i = 0; i < n; i++) {
            file.writeInt(i + 1);
            buffer = new StringBuffer(objetosEscribir[i].getCiudad());
            buffer.setLength(10); // 10 caracteres por apellido
            file.writeChars(buffer.toString()); // insertamos el apellido
            file.writeInt(objetosEscribir[i].getTemperatura());
            file.writeInt(objetosEscribir[i].getPresion());
            buffer = new StringBuffer(objetosEscribir[i].getDni());
            buffer.setLength(10); // 10 caracteres por apellido
            file.writeChars(buffer.toString()); // insertamos el apellido
            buffer = new StringBuffer(objetosEscribir[i].getNombre());
            buffer.setLength(10); // 10 caracteres por apellido
            file.writeChars(buffer.toString()); // insertamos el apellido
            buffer = new StringBuffer(objetosEscribir[i].getApellido());
            buffer.setLength(10); // 10 caracteres por apellido
            file.writeChars(buffer.toString()); // insertamos el apellido
        }
        file.close();
    }

    private static List<Objeto> leerTodosRegistros() throws IOException {
        List<Objeto> objetos = new ArrayList<Objeto>();
        // Lectura
        File ficherete = new File("ejercicio1\\src\\main\\java\\com\\ejercicio1\\ciudades.dat");
        RandomAccessFile file2 = new RandomAccessFile(ficherete, "r");
        // Variables para recoger datos
        int id, temperatura, presion;
        char Aux[] = new char[10], aux;
        // Variables para la localización de los datos
        int posicion = 0;

        // Recorremos el fichero
        for (;;) {
            // Nos colocamos en la posición del fichero
            file2.seek(posicion);
            id = file2.readInt();
            // Leemos el apellido
            // Recorremos uno a uno los caracteres
            for (int i = 0; i < 10; i++) {
                aux = file2.readChar();
                Aux[i] = aux; // los voy guardando en el array
            }
            // Convertimos el array en string
            String ciudad = new String(Aux);
            // Leemos el departamento
            temperatura = file2.readInt();
            presion = file2.readInt();
            Aux = new char[10];
            // Leemos el apellido
            // Recorremos uno a uno los caracteres
            for (int i = 0; i < 10; i++) {
                aux = file2.readChar();
                Aux[i] = aux; // los voy guardando en el array
            }
            // Convertimos el array en string
            String dni = new String(Aux);
            Aux = new char[10];
            // Leemos el apellido
            // Recorremos uno a uno los caracteres
            for (int i = 0; i < 10; i++) {
                aux = file2.readChar();
                Aux[i] = aux; // los voy guardando en el array
            }
            // Convertimos el array en string
            String nombre = new String(Aux);
            Aux = new char[10];
            // Leemos el apellido
            // Recorremos uno a uno los caracteres
            for (int i = 0; i < 10; i++) {
                aux = file2.readChar();
                Aux[i] = aux; // los voy guardando en el array
            }
            // Convertimos el array en string
            String apellidos = new String(Aux);
            if (id > 0) { // Hemos leído algo
                System.out.printf("ID: %s, Ciudad: %s, Temperatura: %d, Presión: %d, DNI: %s, Nombre alumno: %s, Apellido: %s %n", 
                                    id, ciudad.trim(), temperatura, presion, dni.trim(), nombre.trim(), apellidos.trim());
                objetos.add(new Objeto(ciudad.trim(), temperatura, presion, dni.trim(), nombre.trim(), apellidos.trim()));
            }

            // Movemos el puntero al siguiente elemento del fichero.
            // Cada registro ocupa 92 bytes
            // Int 4bytes
            // 10 *2bytes = 20bytes
            // Int 4bytes
            // Int 4bytes
            // 10 *2bytes = 20bytes
            // 10 *2bytes = 20bytes
            // 10 *2bytes = 20bytes
            posicion += 92;

            // Si he leido todo me salgo del bucle
            if (file2.getFilePointer() == file2.length()) {
                file2.close();
                break;
            }
        }
        return objetos;
    }
}
