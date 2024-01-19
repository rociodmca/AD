import java.util.*;

public class Noticia {
    
    private String titulo;
    private String link;
    private String descripcion;
    private String contenido;
    private String fecha;
    private String imagen;
    private String video;
    private List<String> categoria = new ArrayList<>();
    
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getContenido() {
        return contenido;
    }
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public String getVideo() {
        return video;
    }
    public void setVideo(String video) {
        this.video = video;
    }
    public List<String> getCategoria() {
        return categoria;
    }
    public void setCategoria(List<String> categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "*****Noticia [titulo=" + titulo + ", link=" + link + ", descripcion=" + descripcion + ", contenido="
                + contenido + ", fecha=" + fecha + ", imagen=" + imagen + ", video=" + video + ", categoria="
                + categoria + "]";
    }
    
}
