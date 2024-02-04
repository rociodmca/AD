package com.ejercicio1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio6 {
    public static void main(String[] args) {
        String cadena = LeerFicheroEjer5();

        int vocales = 0, consonantes = 0, numeros = 0;
        for (char caracter : cadena.toLowerCase().toCharArray()) {
            if (!Character.isDigit(caracter)) {
                if (caracter == 'a' || caracter == 'e' || caracter == 'i' || caracter == 'o' || caracter == 'u') {
                    vocales++;
                } else {
                    consonantes++;
                }
            } else{
                numeros++;
            }
        }
        System.out.println("Cantidad de vocales: " + vocales + ", de consonantes " + consonantes + " y de numeros " + numeros + ".");
        List<Integer> datos = new ArrayList<>();
        List<Integer> datosAux;
        datos.add(vocales);
        datos.add(consonantes);
        datos.add(numeros);

        Path ficherete = Paths.get("ejercicio1\\src\\main\\java\\com\\ejercicio1\\rocio\\escribirObjeto.sz");
        // Serializacion de objetos
        ObjectOutputStream serializarO;
        ObjectInputStream serializarI;

        try {
            if (Files.notExists(ficherete)) {
                // Si no existe
                serializarO = new ObjectOutputStream(new FileOutputStream(ficherete.toString()));
                serializarO.writeObject(datos);
                serializarO.close(); // cerramos
            } else {
                datosAux = new ArrayList<>();
                serializarI = new ObjectInputStream(new FileInputStream(ficherete.toString()));
                datosAux = (List<Integer>) serializarI.readObject();
                // Lo imprimimos por pantalla
                for (int dato : datosAux) {
                    System.out.println(dato);
                }
                serializarI.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static String LeerFicheroEjer5() {
        String cadena = "";
        
        try {
            // Lectura del ejercicio anterior
            // Lectura utilizando un canal
            ByteBuffer buffer = ByteBuffer.allocate(92 * 5);

            FileInputStream fis = new FileInputStream(
                    "ejercicio1\\src\\main\\java\\com\\ejercicio1\\rocio\\escribirObjeto.bin");
            FileChannel canal = fis.getChannel();
            int pos = 0;

            // Leemos del archivo en el buffer
            canal.read(buffer);
            // Preparar el búffer para la lectura
            buffer.flip(); // Reinicia el puntero que está leyendo

            // buffer
            // bytes 1-4(int=0): 00000000 00000000 00000000 00000000
            // bytes 5-8(int=1): 00000000 00000000 00000000 00000001
            // bytes 9-12(int=2): 00000000 00000000 00000000 00000010
            // bytes 13-16(int=3): 00000000 00000000 00000000 00000011

            byte[] bArr = new byte[92 * 5];
            // Leemos los enteros del búffer
            while (buffer.hasRemaining()) {
                System.out.print(buffer.getInt(pos) + " ");
                cadena += buffer.getInt(pos);
                pos += 4;
                buffer.position(pos);
                bArr = new byte[20];
                buffer.get(bArr, 0, 20);
                cadena += new String(bArr);
                System.out.print(new String(bArr) + " ");
                pos += 20;
                cadena += buffer.getInt(pos);
                System.out.print(buffer.getInt(pos) + " ");
                pos += 4;
                cadena += buffer.getInt(pos);
                System.out.print(buffer.getInt(pos) + " ");
                pos += 4;
                buffer.position(pos);
                bArr = new byte[20];
                buffer.get(bArr, 0, 20);
                cadena += new String(bArr);
                System.out.print(new String(bArr) + " ");
                pos += 20;
                buffer.position(pos);
                bArr = new byte[20];
                buffer.get(bArr, 0, 20);
                cadena += new String(bArr);
                System.out.print(new String(bArr) + " ");
                pos += 20;
                buffer.position(pos);
                bArr = new byte[20];
                buffer.get(bArr, 0, 20);
                cadena += new String(bArr);
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
        } finally{
            return cadena;
        }
    }
}
