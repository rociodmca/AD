package com.ejercicio1;

import java.util.*;

public class Ejercicio7 {
    public static void main(String[] args) {
        
        System.out.println("Vamos a leer las noticias.");
        List<Noticia> noticias = Rsscontrolador.getInstance().getNoticias("https://e00-elmundo.uecdn.es/elmundo/rss/espana.xml");
        if (noticias.size()>0) {
            System.out.println("***Ultimas noticias***");
            //noticias.forEach(System.out::println);
            for (Noticia noticia : noticias) {
                System.out.println(noticia);
            }
        } else {
            System.out.println("No hay noticias, alma de cantaro.");
        }

    }
}
