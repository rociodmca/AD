package com.example;

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

public class LecturaDOM {
    //Objeto con el que lo voy a recibir
    Document document;

    public LecturaDOM(String ruta) throws ParserConfigurationException, SAXException, IOException {
        File aXML = new File(ruta);

        //Creamos los objetos para poder leer el fichero
        DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factoria.newDocumentBuilder();
        
        //Transferimos el XML al objeto Document
        document = builder.parse(aXML);
        //Lo normalizamos
        document.getDocumentElement().normalize();
    }

    //Método para leer el xml que le hemos pasado al constructor
    public void lecturaXML() {
        //Obtener el nombre del nodo raiz
        System.out.println("Nombre *: " + document.getDocumentElement().getNodeName());
        //Lista de nodos
        NodeList nodeList = document.getElementsByTagName("producto");
        Node node;
        //Creamos un bucle para recorrer los elementos de nodeList
        for (int i = 0; i < nodeList.getLength(); i++) {
            node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                System.out.println("Nombre " + element.getAttribute("Nombre"));
                System.out.println("peso " + element.getElementsByTagName("peso").item(0).getTextContent());
            }
        }
    }
}
