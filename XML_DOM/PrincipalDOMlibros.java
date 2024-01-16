import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PrincipalDOMlibros {
    // Ejercicio con DOM introduciendo atributos

    public static void main(String[] args) throws TransformerException, SAXException, IOException {

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            // Definimos el elemento raíz
            Element eRaiz = doc.createElement("libros");
            doc.appendChild(eRaiz);

            // PRIMER LIBRO
            // Dedifimos el nodo que contendrá los elementos
            Element eLibro = doc.createElement("libro");
            eRaiz.appendChild(eLibro);

            // Creamos un atributo
            Attr attr = doc.createAttribute("annio");
            attr.setValue("2024");
            eLibro.setAttributeNode(attr);

            // Definimos otro elemento
            Element eTitulo = doc.createElement("titulo");
            eTitulo.appendChild(doc.createTextNode("Pasar un invierno calentito"));
            eLibro.appendChild(eTitulo);

            // Definimos otro elemento
            Element eAutor = doc.createElement("autor");
            eLibro.appendChild(eAutor);

            // Definimos otro elemento
            Element eApellido = doc.createElement("apellido");
            eApellido.appendChild(doc.createTextNode("San Borombón"));
            eAutor.appendChild(eApellido);

            // Definimos otro elemento
            Element eNombre = doc.createElement("nombre");
            eNombre.appendChild(doc.createTextNode("Pito Pato"));
            eAutor.appendChild(eNombre);

            // Definimos otro elemento
            Element eEditorial = doc.createElement("editorial");
            eEditorial.appendChild(doc.createTextNode("Ande yo caliente..."));
            eLibro.appendChild(eEditorial);

            // Definimos otro elemento
            Element ePrecio = doc.createElement("precio");
            ePrecio.appendChild(doc.createTextNode("90"));
            eLibro.appendChild(ePrecio);

            // SEGUNDO LIBRO
            // Dedifimos el nodo que contendrá los elementos
            Element eLibro2 = doc.createElement("libro");
            eRaiz.appendChild(eLibro2);

            // Creamos un atributo
            Attr attr2 = doc.createAttribute("annio");
            attr2.setValue("2015");
            eLibro2.setAttributeNode(attr2);

            // Definimos otro elemento
            Element eTitulo2 = doc.createElement("titulo");
            eTitulo2.appendChild(doc.createTextNode("Pasar el verano fresco"));
            eLibro2.appendChild(eTitulo2);

            // Definimos otro elemento
            Element eAutor2 = doc.createElement("autor");
            eLibro2.appendChild(eAutor2);

            // Definimos otro elemento
            Element eApellido2 = doc.createElement("apellido");
            eApellido2.appendChild(doc.createTextNode("Florindote"));
            eAutor2.appendChild(eApellido2);

            // Definimos otro elemento
            Element eNombre2 = doc.createElement("nombre");
            eNombre2.appendChild(doc.createTextNode("Marca paquete"));
            eAutor2.appendChild(eNombre2);

            // Definimos otro elemento
            Element eEditorial2 = doc.createElement("editorial");
            eEditorial2.appendChild(doc.createTextNode("Ande yo fresco..."));
            eLibro2.appendChild(eEditorial2);

            // Definimos otro elemento
            Element ePrecio2 = doc.createElement("precio");
            ePrecio2.appendChild(doc.createTextNode("150"));
            eLibro2.appendChild(ePrecio2);

            // Una vez creada la estructura.
            // Tenemos que formalizarlo en el archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("./XML_DOM/libros.xml"));

            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //// ///////////
        // Lectura
        ///////////////
        File file = new File("./XML_DOM/libros.xml");
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);

            // Cogemos los nodos
            NodeList nList = doc.getElementsByTagName("libro");
            System.out.println(" ¡Tenemos los nodos!");
            System.out.println("Y... son: " + nList.getLength());

            for (int i = 0; i < nList.getLength(); i++) {
                System.out.println("LIBRO " + (i+1));
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("\t Annio: " + eElement.getAttribute("annio"));
                    System.out.println("\t Titulo: " + eElement.getElementsByTagName("titulo").item(0).getTextContent());
                    NodeList autor = eElement.getElementsByTagName("autor");
                    Node nAutor = autor.item(0);
                    if (nAutor.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement2 = (Element) nAutor;
                        System.out.println(
                                "\t Apellidos: " + eElement2.getElementsByTagName("apellido").item(0).getTextContent());
                        System.out.println(
                                "\t Nombre: " + eElement2.getElementsByTagName("nombre").item(0).getTextContent());
                    }
                    System.out.println("\t Editorial: "
                                    + eElement.getElementsByTagName("editorial").item(0).getTextContent());
                    System.out.println("\t Precio: " + eElement.getElementsByTagName("precio").item(0).getTextContent());
                } // if

            } // for

        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }// main
}// Principal