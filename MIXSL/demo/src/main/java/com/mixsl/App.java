package com.mixsl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        //"Hoja de estilo"
        //String hojaEstilo = ".\\MIXSL\\demo\\src\\main\\java\\com\\mixsl\\alumnosPlantilla.xsl";
        //String datosAlumno = ".\\MIXSL\\demo\\src\\main\\java\\com\\mixsl\\alumnos.xml";
        //File pagHTML = new File(".\\MIXSL\\demo\\src\\main\\java\\com\\mixsl\\mipagina.html");
        String hojaEstilo = ".\\MIXSL\\demo\\src\\main\\java\\com\\mixsl\\judasPlantilla.xsl";
        String datosAlumno = ".\\MIXSL\\demo\\src\\main\\java\\com\\mixsl\\judas.xml";
        File pagHTML = new File(".\\MIXSL\\demo\\src\\main\\java\\com\\mixsl\\mipaginaJudas.html");
        FileOutputStream os = new FileOutputStream(pagHTML);

        //Indicamos los origenes y el final
        Source estilos = new StreamSource(hojaEstilo);//xsl
        Source datos = new StreamSource(datosAlumno);//xml
        //Resultado de tipo Result
        Result result = new StreamResult(os);//dato de resultado

        //Para la transformación vamos a utilizar un objeto denominado Transformer
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer(estilos);
            //Le decimos que nos haga la transformación transformer
            transformer.transform(datos, result);
        } catch (TransformerFactoryConfigurationError | TransformerException e) {
            e.printStackTrace();
        }

        //Cerramos el descriptor de salida
        os.close();
    }
}
