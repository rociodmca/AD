import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class PrincipalSAX {
    public static void main(String[] args) {
        try {
            // Conseguimos una instancia de SAXFactory
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            LibrosHandler handler = new LibrosHandler();
            parser.parse("./XML_SAX/libros.xml", handler);
        } catch (ParserConfigurationException e) {
           
            e.printStackTrace();
        } catch (SAXException e) {
            
            e.printStackTrace();
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }
}
