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

public class Principal {
    //Ejercicio con DOM introduciendo atributos

    public static void main(String[] args) throws TransformerException, SAXException, IOException {
       
       
        try {
            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
            DocumentBuilder db=dbf.newDocumentBuilder();
            Document doc=db.newDocument();
           
            //Definimos el elemento raíz
            Element eRaiz=doc.createElement("concesionario");
            doc.appendChild(eRaiz);

            //Dedifimos el nodo que contendrá los elementos
            Element eCoche=doc.createElement("coche");
            eRaiz.appendChild(eCoche);

   // Creamos un atributo
            Attr attr=doc.createAttribute("id");
            attr.setValue("1");
            eCoche.setAttributeNode(attr);

            //Definimos otro elemento
            Element eMarca=doc.createElement("marca");
            eMarca.appendChild(doc.createTextNode("Mercedes"));
            eCoche.appendChild(eMarca);

            //Definimos otro elemento
            Element eModelo=doc.createElement("modelo");
            eModelo.appendChild(doc.createTextNode("Clase A"));
            eCoche.appendChild(eModelo);

            //Definimos ahora cilindrada
            Element eCilindrada=doc.createElement("cilindrada");
            eCilindrada.appendChild(doc.createTextNode("1.6"));
            eCoche.appendChild(eCilindrada);

            //Una vez creada la estructura.
            // Tenemos que formalizarlo en el archivo
            TransformerFactory transformerFactory=TransformerFactory.newInstance();
            Transformer transformer=transformerFactory.newTransformer();
            DOMSource source=new DOMSource(doc);
            StreamResult result=new StreamResult(new File("cochecitos.xml"));

            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {
            
            e.printStackTrace();
        }

        //// ///////////
        // Lectura
        ///////////////
        File file=new File("cochecitos.xml");
        try {
            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
            DocumentBuilder db=dbf.newDocumentBuilder();
            Document doc=db.parse(file);
           
            //Cogemos los nodos
            NodeList nList=doc.getElementsByTagName("coche");
            System.out.println(" ¡Tenemos los nodos!");
            System.out.println("Y... son: "+nList.getLength());

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode=nList.item(i);
                if(nNode.getNodeType()==Node.ELEMENT_NODE){
                    Element eElement= (Element) nNode;
                    System.out.println("\n Coche id: "+ eElement.getAttribute("id"));
                    System.out.println("\n Marca: "+eElement.getElementsByTagName("marca").item(0).getTextContent());
                    System.out.println("\n Modelo: "+eElement.getElementsByTagName("modelo").item(0).getTextContent());
                    System.out.println("\n Cilindrada: "+eElement.getElementsByTagName("cilindrada").item(0).getTextContent());
                }//if

            }//for


        } catch (ParserConfigurationException e) {
            
            e.printStackTrace();
        }
       



    }//main
}//Principal