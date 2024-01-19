import java.util.List;

public class Main {

    public static void main(String[] args) {
        
        System.out.println("Vamos a leer las noticias.");
        List<Noticia> noticias = Rsscontrolador.getInstance().getNoticias("https://feeds.elpais.com/mrss-s/pages/ep/site/elpais.com/section/ultimas-noticias/portada");
        if (noticias.size()>0) {
            System.out.println("***Ultimas noticias***");
            noticias.forEach(System.out::println);
        } else {
            System.out.println("No hay noticias, alma de cantaro.");
        }

    }

}
