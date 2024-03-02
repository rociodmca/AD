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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "jugadores", catalog = "nba")
public class jugadores implements Serializable{
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codigojug", unique = true, nullable = false)
    private int codigo;
    @Column(name = "nombre_jugador")
    private String Nombre_Jugador;
    @Column(name = "procedencia")
    private String Procedencia;
    @Column(name = "altura")
    private String Altura;
    @Column(name = "peso")
    private int Peso;
    @Column(name = "posicion")
    private String Posicion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nombreeq")
    private equipos equipos;
    //relacion muchos a muchos
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "jugadores_patrocinador", joinColumns = {@JoinColumn(name = "codigojug")}, inverseJoinColumns = {@JoinColumn(name = "id")})
    private List<patrocinador> patrocinadores = new ArrayList<>();
    //@ManyToMany(fetch = FetchType.LAZY)
    //@JoinTable(name = "jugadores_partidos", catalog = "nba", joinColumns = {@JoinColumn(name = "codigojug", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "codigopar", nullable = false, updatable = false)})
    //private List<partidos> partidos = new ArrayList<partidos>(); 
    //relacion 1 a 1
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name="valoracionrevistas",referencedColumnName = "nombrerevista")
    private valoracionRevistas valoracionRevistas;


    public jugadores(String nombre_Jugador, String procedencia, String altura, int peso, String posicion,
            equipos equipo) {
        Nombre_Jugador = nombre_Jugador;
        Procedencia = procedencia;
        Altura = altura;
        Peso = peso;
        Posicion = posicion;
        this.equipos = equipo;
    }

    public jugadores(String nombre_Jugador, String procedencia, String altura, int peso, String posicion) {
        Nombre_Jugador = nombre_Jugador;
        Procedencia = procedencia;
        Altura = altura;
        Peso = peso;
        Posicion = posicion;
    }

    public valoracionRevistas getValoracionRevistas() {
        return valoracionRevistas;
    }

    public void setValoracionRevistas(valoracionRevistas valoracionRevistas) {
        this.valoracionRevistas = valoracionRevistas;
    }

    /*public List<partidos> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<partidos> partidos) {
        this.partidos = partidos;
    }*/

    public jugadores(String nombre_Jugador, String procedencia, String altura, int peso, String posicion,
            equipos equipo, valoracionRevistas valoracionRevistas) {
        Nombre_Jugador = nombre_Jugador;
        Procedencia = procedencia;
        Altura = altura;
        Peso = peso;
        Posicion = posicion;
        this.equipos = equipo;
        //this.partidos = partidos;
        this.valoracionRevistas = valoracionRevistas;
    }

    public jugadores(int codigo, String nombre_Jugador, String procedencia, String altura, int peso, String posicion,
            equipos equipo, valoracionRevistas valoracionRevistas) {
        this.codigo = codigo;
        Nombre_Jugador = nombre_Jugador;
        Procedencia = procedencia;
        Altura = altura;
        Peso = peso;
        Posicion = posicion;
        this.equipos = equipo;
        //this.partidos = partidos;
        this.valoracionRevistas = valoracionRevistas;
    }

    public jugadores() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre_Jugador() {
        return Nombre_Jugador;
    }

    public void setNombre_Jugador(String nombre_Jugador) {
        Nombre_Jugador = nombre_Jugador;
    }

    public String getProcedencia() {
        return Procedencia;
    }

    public void setProcedencia(String procedencia) {
        Procedencia = procedencia;
    }

    public String getAltura() {
        return Altura;
    }

    public void setAltura(String altura) {
        Altura = altura;
    }

    public int getPeso() {
        return Peso;
    }

    public void setPeso(int peso) {
        Peso = peso;
    }

    public String getPosicion() {
        return Posicion;
    }

    public void setPosicion(String posicion) {
        Posicion = posicion;
    }

    public equipos getEquipo() {
        return equipos;
    }

    public void setEquipo(equipos equipo) {
        this.equipos = equipo;
    }

    public List<patrocinador> getPatrocinadores() {
        return patrocinadores;
    }

    public void setPatrocinadores(List<patrocinador> patrocinadores) {
        this.patrocinadores = patrocinadores;
    }

    @Override
    public String toString() {
        return "jugadores [codigo=" + codigo + ", Nombre_Jugador=" + Nombre_Jugador + ", Procedencia=" + Procedencia
                + ", Altura=" + Altura + ", Peso=" + Peso + ", Posicion=" + Posicion + /* ", equipos=" + equipos
                + ", partidos=" + partidos + ", valoracionRevistas=" + valoracionRevistas + */"]";
    }

    

}