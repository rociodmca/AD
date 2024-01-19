package com.example;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.io.Serializable;

@XStreamAlias("Personas")
public class PersonaAnotaciones implements Serializable{
    @XStreamAlias("nombre")
    private String nombre;
    @XStreamAlias("edad")
    private int edad;
    private static final long serialVersionUID = 1234567;

    public PersonaAnotaciones(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Persona [nombre=" + nombre + ", edad=" + edad + "]";
    }
}
