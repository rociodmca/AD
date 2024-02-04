package com.ejercicio1;

import java.util.*;

public class Noticia {
    
    private String titulo;
    private String descripcion;
    private String creador;
    private List<String> categoria = new ArrayList<>();
    
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getCreador() {
        return creador;
    }
    public void setCreador(String creador) {
        this.creador = creador;
    }
    public List<String> getCategoria() {
        return categoria;
    }
    public void setCategoria(List<String> categorias) {
        this.categoria = categorias;
    }
    @Override
    public String toString() {
        return "Noticia [titulo=" + titulo + ", descripcion=" + descripcion + ", creador=" + creador + ", categorias="
                + categoria + "]";
    }

    public void setCategoria(String textContext) {
        categoria.add(textContext);
    }
}
