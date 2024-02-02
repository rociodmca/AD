import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class PrincipalAlumnos {
    public static void main(String[] args) {
        
        try {
            //1º conseguir SAXFactory
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //Conseguir el objeto SAX
            SAXParser parser = factory.newSAXParser();
            AlumnoHandler handler = new AlumnoHandler();
            parser.parse("./XML_SAX/alumnos.xml", handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            
            e.printStackTrace();
        }
    }
}
