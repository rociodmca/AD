package com.ejercicio1;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class Ejercicio2 {
    public static void main(String[] args) {
        
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            Ejercicio2Handler handler = new Ejercicio2Handler();
            parser.parse("ejercicio1\\src\\main\\java\\com\\ejercicio1\\ciudades.xml", handler);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
