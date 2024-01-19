import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
        ArrayList<Noticia> noticias = new ArrayList<>();
        try {
            // Filtramos por los elementos del RSS
            // Creamos el DOM del documento
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(urinoticias);

            // Obteneoms la lista de nodos
            NodeList item = document.getElementsByTagName("item");
            
            //Recorremos todos los nodos hojas de este nodo
            for (int i = 0; i < item.getLength(); i++) {
                Node nodo = item.item(i);
                int contadorImagenes = 0; //para cada noticia

                Noticia noticia = new Noticia();
                //Tendremos que recorrer los hijos de este nodo
                for(Node n = nodo.getFirstChild(); n != null; n = n.getNextSibling()) {
                    //Miramos el titulo
                    if (n.getNodeName().equals("title")) {
                        String titulo = n.getTextContent();
                        noticia.setTitulo(titulo);
                        System.out.println("Titulo " + titulo);
                    }
                    //Si es el del enlace
                    if (n.getNodeName().equals("link")) {
                        String link = n.getTextContent();
                        noticia.setLink(link);
                        System.out.println("Link " + link);
                    }
                    //Descripcion
                    if (n.getNodeName().equals("description")) {
                        String descripcion = n.getTextContent();
                        noticia.setDescripcion(descripcion);
                        System.out.println("Descripcion " + descripcion);
                    }
                    //Fecha de publicacion
                    if (n.getNodeName().equals("pubDate")) {
                        String fecha = n.getTextContent();
                        noticia.setFecha(fecha);
                        System.out.println("Fecha " + fecha);
                    }
                    //Contenido
                    if (n.getNodeName().equals("content:encoded")) {
                        String contenido = n.getTextContent();
                        noticia.setContenido(contenido);
                        System.out.println("Contenido " + contenido);
                    }
                    //Imagen
                    if (n.getNodeName().equals("media:content")) {
                        //Obtenemos el elemento
                        Element e = (Element)n;
                        String atributo = e.getAttribute("url");
                        //Controlamos que coja solo una imagen
                        if (contadorImagenes == 0) {
                            noticia.setImagen(atributo);
                            contadorImagenes++;
                        }
                    }
                    //Categorias
                    if (n.getNodeName().equals("category")) {
                        noticia.setCategoria(n.getTextContent());
                    }
                    //Metemos cada noticia que hemos leido en la lista
                    noticias.add(noticia);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noticias;
    }

}
