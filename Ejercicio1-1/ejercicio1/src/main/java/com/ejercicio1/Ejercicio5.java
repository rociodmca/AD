package com.ejercicio1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Ejercicio5 {
    public static void main(String[] args) throws CsvValidationException, IOException {

        Path path = Paths.get("ejercicio1\\src\\main\\java\\com\\ejercicio1\\rocio");
        try {
            if (Files.exists(path)) {
                System.out.println("Existe el directorio y procedemos a eliminarlos");
                // Files.delete(path);
            } else {
                System.out.println("No existe y lo creamos.");
                // Files.createDirectory(path);
            }
        } catch (Exception e) {
        }

        List<Objeto> objetos = new ArrayList<Objeto>();
        objetos = importarCSV();

        try (FileOutputStream fos = new FileOutputStream("ejercicio1\\src\\main\\java\\com\\ejercicio1\\rocio\\escribirObjeto.bin");) {

            FileChannel canal = fos.getChannel();
            int pos = 0;

            // Creamos un buffer y ponemos algún numerico
            ByteBuffer buffer = ByteBuffer.allocate(92 * objetos.size());
            for (int i = 0; i < objetos.size(); i++) {
                buffer.position(pos);
                buffer.putInt(i + 1);
                pos += 4;
                buffer.position(pos);
                buffer.put(objetos.get(i).getCiudad().getBytes());
                pos += 20;
                buffer.position(pos);
                buffer.putInt(objetos.get(i).getTemperatura());
                pos += 4;
                buffer.position(pos);
                if (objetos.get(i).getPresion() > 2000) {
                    System.out.println("Tiene pinta de hacer calor. Rocio");
                }
                buffer.putInt(objetos.get(i).getPresion());
                pos += 4;
                buffer.position(pos);
                buffer.put(objetos.get(i).getDni().getBytes());
                pos += 20;
                buffer.position(pos);
                buffer.put(objetos.get(i).getNombre().getBytes());
                pos += 20;
                buffer.position(pos);
                buffer.put(objetos.get(i).getApellido().getBytes());
                pos += 20;
            }
            buffer.position(pos);

            // Preparamos el buffer para escribir
            buffer.flip();
            // Escribimos en el canal
            canal.write(buffer);

            fos.close();

            // Lectura utilizando un canal
            FileInputStream fis = new FileInputStream("ejercicio1\\src\\main\\java\\com\\ejercicio1\\rocio\\escribirObjeto.bin");
            canal = fis.getChannel();

            // Creamos un búffer para la lectura con el tamaño adecuado
            buffer = ByteBuffer.allocate(92 * objetos.size());

            // Leemos del archivo en el buffer
            canal.read(buffer);
            // Preparar el búffer para la lectura
            buffer.flip(); // Reinicia el puntero que está leyendo

            // buffer
            // bytes 1-4(int=0): 00000000 00000000 00000000 00000000
            // bytes 5-8(int=1): 00000000 00000000 00000000 00000001
            // bytes 9-12(int=2): 00000000 00000000 00000000 00000010
            // bytes 13-16(int=3): 00000000 00000000 00000000 00000011

            pos = 0;
            byte[] bArr = new byte[92 * objetos.size()];
            // Leemos los enteros del búffer
            while (buffer.hasRemaining()) {
                System.out.print(buffer.getInt(pos) + " ");
                pos += 4;
                buffer.position(pos);
                bArr = new byte[20];
                buffer.get(bArr, 0, 20);
                System.out.print(new String(bArr) + " ");
                pos += 20;
                System.out.print(buffer.getInt(pos) + " ");
                pos += 4;
                System.out.print(buffer.getInt(pos) + " ");
                pos += 4;
                buffer.position(pos);
                bArr = new byte[20];
                buffer.get(bArr, 0, 20);
                System.out.print(new String(bArr) + " ");
                pos += 20;
                buffer.position(pos);
                bArr = new byte[20];
                buffer.get(bArr, 0, 20);
                System.out.print(new String(bArr) + " ");
                pos += 20;
                buffer.position(pos);
                bArr = new byte[20];
                buffer.get(bArr, 0, 20);
                System.out.print(new String(bArr));
                pos += 20;
                buffer.position(pos);
                System.out.println();
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
                // System.out.println(datos[0]);
                if (StringUtils.isNumeric(linea[0])) {
                    Objeto obj = new Objeto(linea[1].toString(), Integer.parseInt(linea[2]), Integer.parseInt(linea[3]),
                            linea[4].toString(), linea[5].toString(), linea[6].toString());
                    objetos.add(obj);
                }
                // for (String string : datos) {
                // System.out.println(string + "\t");
                // }
                // System.out.println();
            }
            entradaCSV.close();
        }
        return objetos;
    }
}
