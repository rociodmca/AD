package com.example;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "familiares") //ojete en minúscula o mayúscula. Case-sensitive.
public class Familiares {

    //Propiedades
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_familiares")
    private int id_familiares;
    @Column(name="nombre")
    private String nombre;
    @Column(name="parentesco")
    private String parentesco;
    
    @ManyToMany(cascade = {CascadeType.DETACH})
    @JoinTable(name="personaFamiliar", joinColumns={@JoinColumn(name="id_familiares")}, inverseJoinColumns={@JoinColumn(name="id_persona")})
    private List<Persona> persona=new ArrayList<Persona>();

    public Familiares() {
    }
    public Familiares(String nombre, String parentesco) {
        this.nombre = nombre;
        this.parentesco = parentesco;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getParentesco() {
        return parentesco;
    }
    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }
    public List<Persona> getPersona() {
        return persona;
    }
    public void setPersona(List<Persona> persona) {
        this.persona = persona;
    }




}
