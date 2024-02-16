package com.example;

public class Noticia {
    
    private String titulo;
    private String descripcion;
    
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

    @Override
    public String toString() {
        return "*****Noticia [titulo=" + titulo + ", descripcion=" + descripcion + "]";
    }
    
}
