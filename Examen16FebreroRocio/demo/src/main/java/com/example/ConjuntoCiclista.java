package com.example;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//Definir el elemento Root del xml
@XmlRootElement(name = "conjuntoCiclistas")
@XmlType(propOrder = {"nombre", "ciclistas"})
public class ConjuntoCiclista {
    private String nombre;
    private ArrayList<Ciclista> ciclistas = new ArrayList<>();

    @XmlElement(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElementWrapper(name = "ciclistas")
    @XmlElement(name = "ciclista")
    public ArrayList<Ciclista> getCiclistas() {
        return ciclistas;
    }

    public void setCiclistas(ArrayList<Ciclista> ciclistas) {
        this.ciclistas = ciclistas;
    }

    @Override
    public String toString() {
        return "ConjuntoCiclista [nombre=" + nombre + ", ciclistas=" + ciclistas + "]";
    }
}
