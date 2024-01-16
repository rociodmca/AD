import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LibrosHandler extends DefaultHandler{
    
    private StringBuilder value;

    public LibrosHandler() {
        this.value = new StringBuilder();
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.value.setLength(0);

        if (qName.equals("libro")) {
            String anio = attributes.getValue("annio");
            System.out.println("Atributo año: " + anio);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "libro":
                System.out.println("");
                break;
            case "titulo":
                System.out.println("Titulo: " + this.value.toString());
                break;
            case "apellido":
                System.out.println("Apellido: " + this.value.toString());
                break;
            case "nombre":
                System.out.println("Nombre del autor: " + this.value.toString());
                break;
            case "editorial":
                System.out.println("Editorial: " + this.value.toString());
                break;
            case "precio":
                System.out.println("Precio: " + this.value.toString());
                break;
            default:
                break;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException{
        this.value.append(ch, start, length);
    }

}
