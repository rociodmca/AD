package com.example;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//Clase para el manejador de eventos
public class ManejadorHandler extends DefaultHandler{
    private ArrayList<Libro> libros = new ArrayList<>();
    //creamos un búfer para leer los elementos que tienen texto
    private StringBuilder buffer = new StringBuilder();

    //Método para reconocer los caracteres
    @Override
    public void characters(char ch[], int start, int length) throws SAXException{
        this.buffer.append(ch, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.buffer.setLength(0);

        if (qName.equals("libro")) {
            String anio = attributes.getValue("isbn");
            System.out.println("Atributo isbn: " + anio);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "titulo":
                System.out.println("Titulo: " + this.buffer.toString());
                break;
            case "autor":
                System.out.println("Nombre del autor: " + this.buffer.toString());
                break;
            default:
                break;
        }
    }

}
