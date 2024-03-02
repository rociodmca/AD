package com.examen;

import java.io.Serializable;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "valoracionrevistas", catalog = "nba")
public class valoracionRevistas implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "nombrerevista", nullable = false)
    private String nombreRevista;
    @Column(name = "valoracion")
    private int valoracion;
    //relacion 1 a 1
    /*@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private jugadores jugador;*/

    public valoracionRevistas(String nombreRevista, int valoracion) {
        this.nombreRevista = nombreRevista;
        this.valoracion = valoracion;
    }

    /*public valoracionRevistas(String nombreRevista, int valoracion, jugadores jugador) {
        this.nombreRevista = nombreRevista;
        this.valoracion = valoracion;
        this.jugador = jugador;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public valoracionRevistas() {
    }

    public String getNombreRevista() {
        return nombreRevista;
    }

    public void setNombreRevista(String nombreRevista) {
        this.nombreRevista = nombreRevista;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    /*public jugadores getJugador() {
        return jugador;
    }

    public void setJugador(jugadores jugador) {
        this.jugador = jugador;
    }*/

    @Override
    public String toString() {
        return "valoracionRevistas [nombreRevista=" + nombreRevista + ", valoracion=" + valoracion + /*", jugador="
                + jugador +*/ "]";
    }

    
}
