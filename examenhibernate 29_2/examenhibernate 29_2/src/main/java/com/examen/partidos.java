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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "partidos", catalog = "nba") 
public class partidos implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codigopar")
    private int codigo;
    //relacion muchos a 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eq_local", referencedColumnName = "nombreeq")
    private equipos equipo_local;
    //relacion muchos a 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eq_visi", referencedColumnName = "nombreeq")
    private equipos equipo_visitante;
    @Column(name = "puntos_local")
    private int Puntos_local;
    @Column(name = "puntos_visitante")
    private int Puntos_visitante;
    @Column(name = "temporada")
    private String temporada;
    //relacion muchos a muchos
    //@ManyToMany(fetch = FetchType.LAZY)
    //@JoinTable(name = "jugadores_partidos", catalog = "nba", joinColumns = {@JoinColumn(name = "codigopar", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "codigojug", nullable = false, updatable = false)})
    //private List<jugadores> jugadores = new ArrayList<jugadores>();

    public partidos(equipos equipo_local, equipos equipo_visitante, int puntos_local, int puntos_visitante,
            String temporada) {
        this.equipo_local = equipo_local;
        this.equipo_visitante = equipo_visitante;
        Puntos_local = puntos_local;
        Puntos_visitante = puntos_visitante;
        this.temporada = temporada;
    }

    /*public List<jugadores> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<jugadores> jugadores) {
        this.jugadores = jugadores;
    }*/

    /*public partidos(equipos equipo_local, equipos equipo_visitante, int puntos_local, int puntos_visitante,
            String temporada, List<jugadores> jugadores) {
        this.equipo_local = equipo_local;
        this.equipo_visitante = equipo_visitante;
        Puntos_local = puntos_local;
        Puntos_visitante = puntos_visitante;
        this.temporada = temporada;
        //this.jugadores = jugadores;
    }*/

    public partidos(int codigo, equipos equipo_local, equipos equipo_visitante, int puntos_local, int puntos_visitante,
            String temporada) {
        this.codigo = codigo;
        this.equipo_local = equipo_local;
        this.equipo_visitante = equipo_visitante;
        Puntos_local = puntos_local;
        Puntos_visitante = puntos_visitante;
        this.temporada = temporada;
        //this.jugadores = jugadores;
    }

    public partidos() {
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public equipos getEquipo_local() {
        return equipo_local;
    }

    public void setEquipo_local(equipos equipo_local) {
        this.equipo_local = equipo_local;
    }

    public equipos getEquipo_visitante() {
        return equipo_visitante;
    }

    public void setEquipo_visitante(equipos equipo_visitante) {
        this.equipo_visitante = equipo_visitante;
    }

    public int getPuntos_local() {
        return Puntos_local;
    }

    public void setPuntos_local(int puntos_local) {
        Puntos_local = puntos_local;
    }

    public int getPuntos_visitante() {
        return Puntos_visitante;
    }

    public void setPuntos_visitante(int puntos_visitante) {
        Puntos_visitante = puntos_visitante;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    @Override
    public String toString() {
        return "partidos [codigo=" + codigo + ", equipo_local=" + equipo_local + ", equipo_visitante="
                + equipo_visitante + ", Puntos_local=" + Puntos_local + ", Puntos_visitante=" + Puntos_visitante
                + ", temporada=" + temporada + "]";
    }

    

}
    
