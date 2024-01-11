import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class CrearyLeerDOM {

    public static void main(String[] args) {
        String nomArchivo = "usuarios";
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();

        listaUsuarios.add(new Usuario(1, "Judas", "12341345"));
        listaUsuarios.add(new Usuario(2, "Flor de loto", "54345636"));
        listaUsuarios.add(new Usuario(3, "Armando Gresca", "75837962348"));
        listaUsuarios.add(new Usuario(4, "El feo", "367356735"));

        try {
            crearXML(nomArchivo, listaUsuarios);
            leerXML();
        } catch (ParserConfigurationException | TransformerFactoryConfigurationError | TransformerException
                | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    // Método para crear XML
    private static void crearXML(String nomArchivo, List<Usuario> listaUsuarios)
            throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
        // 1º Creamos el constructor de la fabrica de archivos
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 2º Creamos el parser
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();
        Document document = implementation.createDocument(null, nomArchivo, null);
        document.setXmlVersion("1.0");
        Element raiz = document.getDocumentElement();
        for (int i = 0; i < listaUsuarios.size(); i++) {
            Element itemNode = document.createElement("USUARIO");
            // ID
            Element idNode = document.createElement("ID");
            Text nodeIdValue = document.createTextNode("" + listaUsuarios.get(i).getIdUsuario());
            idNode.appendChild(nodeIdValue); // le pegamos el valor

            // Nombre
            Element nombreNode = document.createElement("NOMBRE");
            Text nodeNombreValue = document.createTextNode("" + listaUsuarios.get(i).getNombre());
            nombreNode.appendChild(nodeNombreValue); // le pegamos el valor

            // Telefono
            Element telefonoNode = document.createElement("TELEFONO");
            Text nodeTelefonoValue = document.createTextNode("" + listaUsuarios.get(i).getTelefono());
            telefonoNode.appendChild(nodeTelefonoValue); // le pegamos el valor

            // Hemos creado los nodos y ahora pegamos los hijos.
            itemNode.appendChild(idNode);
            itemNode.appendChild(nombreNode);
            itemNode.appendChild(telefonoNode);

            // al raiz se lo endosamos
            raiz.appendChild(itemNode);
        }

        // Generar el XML
        Source source = new DOMSource(document);

        // Donde lo guardamos ??
        Result result = new StreamResult(new java.io.File(nomArchivo + ".xml"));
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, result);
    }

    // Metodo para leer XML
    private static void leerXML() throws ParserConfigurationException, SAXException, IOException {
        // Creamos descriptor de fichero
        File archivo = new File("usuarios.xml");
        // 1º Creamos el constructor de la fabrica de archivos
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 2º Creamos el parser
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(archivo);
        // Tenemos que normalizar
        document.getDocumentElement().normalize();
        // Imprimimos el nodo raiz
        System.out.println("Elemento raiz: " + document.getDocumentElement().getLocalName());

        NodeList listaUsuario = document.getElementsByTagName("USUARIO");
        for (int i = 0; i < listaUsuario.getLength(); i++) {
            // Por cada nodo de la lista
            Node nodo = listaUsuario.item(i);
            System.out.println("Elemento: " + nodo.getNodeName());
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nodo;
                System.out.println("Id: " + element.getElementsByTagName("ID").item(0).getTextContent());
                System.out.println("Nombre: " + element.getElementsByTagName("NOMBRE").item(0).getTextContent());
                System.out.println("Telefono: " + element.getElementsByTagName("TELEFONO").item(0).getTextContent());

                System.out.println(" ");
            }
        }
    }
}
