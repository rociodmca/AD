package com.examen;

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
import jakarta.persistence.Table;

@Entity
@Table(name = "patrocinador", catalog = "nba")
public class patrocinador implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "marca")
    private String marca;
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "jugadores_patrocinador", joinColumns = {@JoinColumn(name = "id")}, inverseJoinColumns = {@JoinColumn(name = "codigojug")})
    private List<jugadores> jugadores = new ArrayList<>();
    
    public patrocinador(String marca) {
        this.marca = marca;
    }

    public patrocinador(String marca, List<jugadores> jugadores) {
        this.marca = marca;
        this.jugadores = jugadores;
    }

    public patrocinador(int id, String marca, List<jugadores> jugadores) {
        this.id = id;
        this.marca = marca;
        this.jugadores = jugadores;
    }

    public patrocinador() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public List<jugadores> getJugadores() {
        return jugadores;
    }
    public void setJugadores(List<jugadores> jugadores) {
        this.jugadores = jugadores;
    }

    @Override
    public String toString() {
        return "patrocinador [id=" + id + ", marca=" + marca + ", jugadores=" + jugadores + "]";
    }
}
