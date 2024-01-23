package com.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GeneradorDOM {
    //documento donde guardar el XML
    
    private Document document;

    //constructor
    public GeneradorDOM() throws ParserConfigurationException {
        DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factoria.newDocumentBuilder();
        //Aqui es donde se crea el documento gracias al builder
        document = builder.newDocument();
    }

    //Metodo para generar el documento
    public void generarDocument() {
        //Elemento raiz
        Element productos = document.createElement("productos");
        document.appendChild(productos);

        //Añadimos un elemento
        //Producto 1
        Element producto = document.createElement("producto");
        productos.appendChild(producto);
        producto.setAttribute("Nombre", "Manzana");

        Element peso = document.createElement("peso");
        peso.appendChild(document.createTextNode("200"));
        //Lo añadimos
        producto.appendChild(peso);
        
        //Producto 2
        Element producto2 = document.createElement("producto");
        productos.appendChild(producto2);
        producto2.setAttribute("Nombre", "Pavo");

        Element peso2 = document.createElement("peso");
        peso2.appendChild(document.createTextNode("150"));
        //Lo añadimos
        producto2.appendChild(peso2);

        //Producto 3
        Element producto3 = document.createElement("producto");
        productos.appendChild(producto3);
        producto3.setAttribute("Nombre", "Aceite");

        Element peso3 = document.createElement("peso");
        peso3.appendChild(document.createTextNode("150"));
        //Lo añadimos
        producto3.appendChild(peso3);
    }

    //Método para generar el XML
    public void generarXML() throws IOException, TransformerException {
        //Para generar el XML hay que hacer una transformación
        TransformerFactory factoria = TransformerFactory.newInstance();
        Transformer transformer = factoria.newTransformer();

        //Tenemos que crear un source y result para el método de transform
        Source source = new DOMSource(document);
        //Result
        File file = new File(".//JAXB//jaxb//src//productos.xml");
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);
        Result result = new StreamResult(pw);
        //Llamamos al transformador
        transformer.transform(source, result);
    }
}
