package com.example;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//Clase para el manejador de eventos
public class ManejadorHandler extends DefaultHandler{
    private ArrayList<Libro> libreriaSAX = new ArrayList<>();
    //creamos un búfer para leer los elementos que tienen texto
    private StringBuilder buffer = new StringBuilder();
    
    private String nombreLibreria;
    private String isbnTemp;
    private String tituloTemp;
    private String autorTemp;

    public ManejadorHandler() {
        libreriaSAX = new ArrayList<>();
        buffer = new StringBuilder();
    }

    public ArrayList<Libro> getLibrerias() {
        return libreriaSAX;
    }

    //Método para reconocer los caracteres
    @Override
    public void characters(char ch[], int start, int length) throws SAXException{
        this.buffer.append(ch, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.buffer.setLength(0);

        if ("libreria".equals(qName)) {
            nombreLibreria = "";
        } else if ("libro".equals(qName)) {
            isbnTemp = attributes.getValue("isbn");
            
            tituloTemp = "";
            autorTemp = "";
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String value = buffer.toString().trim();

        if ("nombre".equals(qName)) {
            nombreLibreria = value;
            System.out.println("Nombre libreria "+nombreLibreria);
        } else if ("titulo".equals(qName)) {
            tituloTemp = value;
            System.out.println("Titulo "+tituloTemp);
        } else if ("autor".equals(qName)) {
            autorTemp = value;
            System.out.println("Autor "+autorTemp);
        } else if ("libro".equals(qName)) {
            Libro libro = new Libro(isbnTemp, tituloTemp, autorTemp);
            libreriaSAX.add(libro);
        } else if ("libreria".equals(qName)) {
            //System.out.println("Nombre de la librería: " + nombreLibreria);
        }
    }

}
