package com.ejercicio1;

import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Rsscontrolador {

    private String uri;
    public static Rsscontrolador controlador;

    public static Rsscontrolador getInstance() {
        if (controlador == null) {
            controlador = new Rsscontrolador();
        }
        return controlador;
    }

    public List<Noticia> getNoticias(String urinoticias) {
        this.uri = urinoticias;

        // Empieza la lectura DOM
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        List<Noticia> noticias = new ArrayList<>();
        try {
            // Filtramos por los elementos del RSS
            // Creamos el DOM del documento
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(urinoticias);

            // Obteneoms la lista de nodos
            NodeList item = document.getElementsByTagName("item");

            // Recorremos todos los nodos hojas de este nodo
            for (int i = 0; i < item.getLength(); i++) {
                Node nodo = item.item(i);

                Noticia noticia = new Noticia();
                // Tendremos que recorrer los hijos de este nodo
                for (Node n = nodo.getFirstChild(); n != null; n = n.getNextSibling()) {
                    // Miramos el titulo
                    if (n.getNodeName().equals("title")) {
                        String titulo = n.getTextContent();
                        noticia.setTitulo(titulo);
                        System.out.println("Titulo " + titulo);
                    }
                    // Descripcion
                    if (n.getNodeName().equals("description")) {
                        String descripcion = n.getTextContent();
                        noticia.setDescripcion(descripcion);
                        System.out.println("Descripcion " + descripcion);
                    }
                    // Creador
                    if (n.getNodeName().equals("dc:creator")) {
                        String creador = n.getTextContent();
                        noticia.setCreador(creador);
                        System.out.println("Creador " + creador);
                    }
                    // Categorias
                    if (n.getNodeName().equals("category")) {
                        noticia.setCategoria(n.getTextContent());
                    }
                    // Metemos cada noticia que hemos leido en la lista
                    noticias.add(noticia);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noticias;
    }
}
