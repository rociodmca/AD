package com.example;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "animales") 
public class Animales {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_animal")
    private int id;
    @Column(name="nombre")
    private String nombre;
    @Column(name="animal")
    private String animal;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_persona")
    private Persona persona;

    public Persona getPersona() {
        return persona;
    }
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    public Animales() {
    }
    public Animales(String nombre, String animal) {
        this.nombre = nombre;
        this.animal = animal;
    }
    public Animales(String nombre, String animal,Persona person) {
        this.nombre = nombre;
        this.animal = animal;
        this.persona=person;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getAnimal() {
        return animal;
    }
    public void setAnimal(String animal) {
        this.animal = animal;
    }
    @Override
    public String toString() {
        return "Animales [id=" + id + ", nombre=" + nombre + ", animal=" + animal + "]";
    }

    



}
