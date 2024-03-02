package com.examen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "equipos", catalog = "nba")
public class equipos implements Serializable {
    
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nombreeq", nullable = false)
    private String Nombre;
    @Column(name = "ciudad")
    private String Ciudad;
    @Column(name = "conferencia")
    private String Conferencia;
    @Column(name = "division")
    private String Division;
    //relacion 1 a muchos
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "equipos")
    private List<jugadores> jugadores = new ArrayList<jugadores>();

    public equipos(String nombre, String ciudad, String conferencia, String division) {
        Nombre = nombre;
        Ciudad = ciudad;
        Conferencia = conferencia;
        Division = division;
    }

    public equipos() {
    }

    public equipos(String nombre, String ciudad, String conferencia, String division,
            List<com.examen.jugadores> jugadores) {
        Nombre = nombre;
        Ciudad = ciudad;
        Conferencia = conferencia;
        Division = division;
        this.jugadores = jugadores;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String ciudad) {
        Ciudad = ciudad;
    }

    public String getConferencia() {
        return Conferencia;
    }

    public void setConferencia(String conferencia) {
        Conferencia = conferencia;
    }

    public String getDivision() {
        return Division;
    }

    public void setDivision(String division) {
        Division = division;
    }

    public List<jugadores> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<jugadores> jugadores) {
        this.jugadores = jugadores;
    }

    @Override
    public String toString() {
        return "equipos [Nombre=" + Nombre + ", Ciudad=" + Ciudad + ", Conferencia=" + Conferencia + ", Division="
                + Division + ", jugadores=" + jugadores + "]";
    }

    
}
    

   