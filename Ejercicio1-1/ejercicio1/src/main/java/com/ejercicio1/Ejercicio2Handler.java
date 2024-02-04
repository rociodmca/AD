package com.ejercicio1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Ejercicio2Handler extends DefaultHandler {
    private StringBuilder value;

    public Ejercicio2Handler() {
        this.value = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.value.setLength(0);

        if (qName.equals("ciudad")) {
            String id = attributes.getValue("id");
            System.out.println("Atributo id: " + id);
        }
        if (qName.equals("nombreApe")) {
            String apellido = attributes.getValue("apellido");
            System.out.println("Apellido: " + apellido);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "ciudad":
                System.out.println("");
                break;
            case "nombre":
                System.out.println("Ciudad: " + this.value.toString());
                break;
            case "temperatura":
                System.out.println("Temperatura: " + this.value.toString());
                break;
            case "presion":
                System.out.println("Presion: " + this.value.toString());
                break;
            case "nombreApe":
                System.out.println("Nombre: " + this.value.toString());
                break;
            default:
                break;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        this.value.append(ch, start, length);
    }
}
