import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class CrearyLeerDOMconcesionario {

    public static void main(String[] args) {
        String nomArchivo = "concesionario";
        List<Concesionario> listaCoches = new ArrayList<Concesionario>();

        listaCoches.add(new Concesionario("Opel", "Astra", "negro", "1234ABC"));
        listaCoches.add(new Concesionario("Nissan", "Patrol", "blanco", "5678CDE"));
        listaCoches.add(new Concesionario("Seat", "Ibiza", "gris", "4321FGH"));
        listaCoches.add(new Concesionario("Audi", "A3", "rojo", "8765IJK"));

        try {
            crearXML(nomArchivo, listaCoches);
            leerXML();
        } catch (ParserConfigurationException | TransformerFactoryConfigurationError | TransformerException
                | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    // Método para crear XML
    private static void crearXML(String nomArchivo, List<Concesionario> listaCoches)
            throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
        // 1º Creamos el constructor de la fabrica de archivos
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 2º Creamos el parser
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();
        Document document = implementation.createDocument(null, nomArchivo, null);
        document.setXmlVersion("1.0");
        Element raiz = document.getDocumentElement();
        for (int i = 0; i < listaCoches.size(); i++) {
            Element itemNode = document.createElement("COCHE");
            // Marca
            Attr attr = document.createAttribute("id");
            attr.setValue(Integer.toString(i+1));
            itemNode.setAttributeNodeNS(attr);
            Element marcaNode = document.createElement("MARCA");
            Text nodeMarcaValue = document.createTextNode("" + listaCoches.get(i).getMarca());
            marcaNode.appendChild(nodeMarcaValue); // le pegamos el valor

            // Modelo
            Element modeloNode = document.createElement("MODELO");
            Text nodeModeloValue = document.createTextNode("" + listaCoches.get(i).getModelo());
            modeloNode.appendChild(nodeModeloValue); // le pegamos el valor

            // Color
            Element colorNode = document.createElement("COLOR");
            Text nodeColorValue = document.createTextNode("" + listaCoches.get(i).getColor());
            colorNode.appendChild(nodeColorValue); // le pegamos el valor

            // Matrícula
            Element matriculaNode = document.createElement("MATRICULA");
            Text nodeMatriculaValue = document.createTextNode("" + listaCoches.get(i).getColor());
            matriculaNode.appendChild(nodeMatriculaValue); // le pegamos el valor

            // Hemos creado los nodos y ahora pegamos los hijos.
            itemNode.appendChild(marcaNode);
            itemNode.appendChild(modeloNode);
            itemNode.appendChild(colorNode);
            itemNode.appendChild(matriculaNode);

            // al raiz se lo endosamos
            raiz.appendChild(itemNode);
        }

        // Generar el XML
        DOMSource source = new DOMSource(document);

        // Donde lo guardamos ??
        StreamResult result = new StreamResult(new java.io.File(nomArchivo + ".xml"));
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, result);
    }

    // Metodo para leer XML
    private static void leerXML() throws ParserConfigurationException, SAXException, IOException {
        // Creamos descriptor de fichero
        File archivo = new File("concesionario.xml");
        // 1º Creamos el constructor de la fabrica de archivos
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 2º Creamos el parser
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(archivo);
        // Tenemos que normalizar
        document.getDocumentElement().normalize();
        // Imprimimos el nodo raiz
        System.out.println("Elemento raiz: " + document.getDocumentElement().getLocalName());

        NodeList listaUsuario = document.getElementsByTagName("COCHE");
        for (int i = 0; i < listaUsuario.getLength(); i++) {
            // Por cada nodo de la lista
            Node nodo = listaUsuario.item(i);
            System.out.println("Elemento: " + nodo.getNodeName());
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nodo;
                System.out.println("\tCoche Id: " + element.getAttribute("id"));
                System.out.println("\tMarca: " + element.getElementsByTagName("MARCA").item(0).getTextContent());
                System.out.println("\tModelo: " + element.getElementsByTagName("MODELO").item(0).getTextContent());
                System.out.println("\tColor: " + element.getElementsByTagName("COLOR").item(0).getTextContent());
                System.out.println("\tMatricula: " + element.getElementsByTagName("MATRICULA").item(0).getTextContent());

                System.out.println(" ");
            }
        }
    }
}
