import org.xml.sax.helpers.DefaultHandler;

public class LibrosHandler extends DefaultHandler{
    
    private StringBuilder value;

    public LibrosHandler(StringBuilder value) {
        this.value = value;
    }
    
    @Override
    public void StartElement(String uri, String localName)

}
