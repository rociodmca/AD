import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class AlumnoHandler extends DefaultHandler {
    private StringBuilder value;
    boolean esNombre = false;
    boolean esApellidos = false;
    boolean esNota = false;

    public AlumnoHandler() {
        this.value = new StringBuilder();
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.value.setLength(0);

        if (qName.equals("estudiante")) {
            String numatricula = attributes.getValue("numatricula");
            System.out.println("Numero matricula: " + numatricula);
        } else if (qName.equals("nombre")) {
            esNombre = true;
        } else if (qName.equals("apellidos")) {
            esApellidos = true;
        } else if (qName.equals("nota")) {
            esNota = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        /*switch (qName) {
            case "estudiante":
                System.out.println("");
                break;
            case "nombre":
                System.out.println("Nombre: " + this.value.toString());
                break;
            case "apellidos":
                System.out.println("Apellidos: " + this.value.toString());
                break;
            case "nota":
                System.out.println("Nota: " + this.value.toString());
                break;
            default:
                break;
        }*/

        if (qName.equals("estudiante")) {
            System.out.println("Termina el estudiante y chimpun.");
            this.value.setLength(0);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException{
        //this.value.append(ch, start, length);
        if (esNombre) {
            System.out.println("Nombre " + new String(ch, start, length));
            esNombre = false;
        } else if (esApellidos) {
            System.out.println("Apellidos " + new String(ch, start, length));
            esApellidos = false;
        } else if (esNota) {
            System.out.println("Nota " + new String(ch, start, length));
            esNota = false;
        }
    }
}
