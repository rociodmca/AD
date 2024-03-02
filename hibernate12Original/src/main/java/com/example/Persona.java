package com.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "persona") //ojete en minúscula o mayúscula. Case-sensitive.
public class Persona implements Serializable{
   // private static long serialVersionUID=1L;

    //Propiedades
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_usuario")
    private int id_usuario;
    @Column(name="nombre")
    private String nombre;
    @Column(name="apellido")
    private String apellido;
    @Column(name="edad")
    private int edad;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="detalles",referencedColumnName = "id_telefono") //Este es el que marca la existencia o no de Detalles
    private Detalles detalles;

   @OneToMany(mappedBy = "persona",cascade =  CascadeType.MERGE  ,orphanRemoval = true)
   private List<Animales> animales=new ArrayList<Animales>();

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name="personaFamiliar", joinColumns={@JoinColumn(name="id_persona")}, inverseJoinColumns={@JoinColumn(name="id_familiares")})
    private List<Familiares> familiares = new ArrayList<Familiares>();


    //////////
    // Constructores
    /////////

    public Persona() {
    }
    public Persona(String nombre, String apellido, int edad, Detalles detalles) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.detalles = detalles;
    }
    public Persona(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.detalles = detalles;
    }

///////////
// Getter & Setter
///////////




public String getNombre() {
    return nombre;
}




public void setNombre(String nombre) {
    this.nombre = nombre;
}


public String getApellido() {
    return apellido;
}


public void setApellido(String apellido) {
    this.apellido = apellido;
}


public int getEdad() {
    return edad;
}


public void setEdad(int edad) {
    this.edad = edad;
}


public Detalles getDetalles() {
    return detalles;
}


public void setDetalles(Detalles detalles) {
    this.detalles = detalles;
}

public void setAnimal(Animales an)
{
    this.animales.add(an);
}


public int getId_usuario() {
    return id_usuario;
}
public void setId_usuario(int id_usuario) {
    this.id_usuario = id_usuario;
}
public List<Animales> getAnimales() {
    return animales;
}
public void setAnimales(List<Animales> animales) {
    this.animales = animales;
}
public List<Familiares> getFamiliares() {
    return familiares;
}
public void setFamiliares(List<Familiares> familiares) {
    this.familiares = familiares;
}
@Override
public String toString() {
    return "Persona [id_usuario=" + id_usuario + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad
            + ", detalles=" + detalles + ", animales=" + animales + ", familiares=" + familiares + "]";
}






}//persona
