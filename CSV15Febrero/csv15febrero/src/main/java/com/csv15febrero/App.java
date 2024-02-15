package com.csv15febrero;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class App 
{
    public static void main( String[] args )
    {
        GeneraCSV();
        LeeCSV();
    }

    private static void LeeCSV() {
        //Nombre del fichero
        String nombreArchivo = ".\\csv15febrero\\src\\main\\java\\com\\csv15febrero\\datos.csv";

        //Variable para recoger datos.
        int nivelFealdad, nivelTontuna;
        String nombre, apodo, apodoBarman;

        boolean existe = new File(nombreArchivo).exists();
        
        if (!existe) {
            System.out.println("El archivo no existe!!!!!!");
        } else {
            try {
                CSVReader entrada = new CSVReader(new FileReader(nombreArchivo));
                String[] siguiente;
                while ((siguiente = entrada.readNext()) != null) {
                    for (String celda : siguiente) {
                        System.out.print(celda + "\t");
                    }
                    System.out.println();
                }
                entrada.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (CsvValidationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private static void GeneraCSV() {
        //Nombre del archivo
        String nombreArchivo = ".\\csv15febrero\\src\\main\\java\\com\\csv15febrero\\datos.csv";
        //Abrimos una instancia de File para saber si existe o no
        boolean existe = new File(nombreArchivo).exists();

        if (existe) {
            File borra = new File(nombreArchivo);
            borra.delete();
        }

        //Creamos el archivo
        try {
            CSVWriter salidaCsvwriter = new CSVWriter(new FileWriter(nombreArchivo), ',', CSVWriter.DEFAULT_QUOTE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            
            //Cabecera
            String[] cabecera = {"Nombre", "NivelFealdad", "NivelTontuna", "Apodo", "ApodoBarman"};
            salidaCsvwriter.writeNext(cabecera, false);

            //Creo una lista para meter los datos
            List<String[]> datos = new ArrayList<String[]>();

            for (int i = 0; i < 6; i++) {
                datos.add(new String[]{"judasA".concat(String.valueOf(i*100)), "3", "5", "florin", "Tontuco"});
            }

            salidaCsvwriter.writeAll(datos, false); //Escribimos los datos creados
            salidaCsvwriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }
}
