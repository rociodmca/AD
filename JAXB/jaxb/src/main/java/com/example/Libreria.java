package com.example;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//Definir el elemento Root del xml
@XmlRootElement(name = "libreria")
@XmlType(propOrder = {"nombre", "libros"})
public class Libreria {
    //Crear la estructura
    private String nombre;
    private ArrayList<Libro> libros = new ArrayList<>();
    
    public Libreria() {
    }

    @XmlElement(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Libros es un wrapper, es decir, un envoltorio.
    @XmlElementWrapper(name = "libros")
    //Dentro de cada envoltorio irá libro
    @XmlElement(name = "libro")
    public ArrayList<Libro> getLibros() {
        return libros;
    }

    public void setLibros(ArrayList<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Libreria [nombre=" + nombre + ", libros=" + libros + "]";
    }
}
