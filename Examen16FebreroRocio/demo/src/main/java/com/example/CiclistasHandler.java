package com.example;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CiclistasHandler extends DefaultHandler{
    private StringBuilder value;

    public CiclistasHandler() {
        this.value = new StringBuilder();
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.value.setLength(0);

        if (qName.equals("CICLISTA")) {
            String dorsal = attributes.getValue("dorsal");
            System.out.println("Atributo dorsal: " + dorsal);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "CICLISTA":
                System.out.println("");
                break;
            case "NOMBRE":
                System.out.println("Nombre: " + this.value.toString());
                break;
            case "EDAD":
                System.out.println("Edad: " + this.value.toString());
                break;
            case "NOMEQ":
                System.out.println("Nomeq: " + this.value.toString());
                break;
            default:
                break;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException{
        this.value.append(ch, start, length);
    }
}
