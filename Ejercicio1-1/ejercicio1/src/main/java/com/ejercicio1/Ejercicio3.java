package com.ejercicio1;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Ejercicio3 {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        File archivo = new File("ejercicio1\\src\\main\\java\\com\\ejercicio1\\ciudades.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(archivo);
        document.getDocumentElement().normalize();
        System.out.println("Elemento raiz: " + document.getDocumentElement().getLocalName());

        NodeList listaCiudades = document.getElementsByTagName("ciudad");
        NodeList listaApellido = document.getElementsByTagName("nombreApe");
        for (int i = 0; i < listaCiudades.getLength(); i++) {
            Node nodo = listaCiudades.item(i);
            Node nodo2 = listaApellido.item(i);
            System.out.println("Elemento: " + nodo.getNodeName());
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nodo;
                Element element2 = (Element) nodo2;
                System.out.println("Ciudad: " + element.getElementsByTagName("nombre").item(0).getTextContent());
                System.out.println("Temperatura: " + element.getElementsByTagName("temperatura").item(0).getTextContent());
                System.out.println("Presion: " + element.getElementsByTagName("presion").item(0).getTextContent());
                //System.out.println("Nombre: " + element.getElementsByTagName("nombreApe").item(0).getTextContent());
                System.out.println("soy yo en el examen");
                System.out.println("Apellido: " + element2.getAttribute("apellido"));
                System.out.println("");
            }
        }
    }
}
