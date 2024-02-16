package com.example;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//Definir el elemento Root del xml
@XmlRootElement(name = "ciclista")
@XmlType(propOrder = {"dorsal", "nombre", "edad", "nomeq"})
public class Ciclista implements Serializable{
    private int dorsal;
    private String nombre;
    private int edad;
    private String nomeq;

    @XmlElement(name = "dorsal")
    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    @XmlElement(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement(name = "edad")
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @XmlElement(name = "nomeq")
    public String getNomeq() {
        return nomeq;
    }

    public void setNomeq(String nomeq) {
        this.nomeq = nomeq;
    }

    @Override
    public String toString() {
        return "Ciclista [dorsal=" + dorsal + ", nombre=" + nombre + ", edad=" + edad + ", nomeq=" + nomeq + "]";
    }
    
}
