package com.hibernate27;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "director", catalog = "db_peliculas")
public class Director implements Serializable{

    private int id;
    private String nombre;
    private Date fechaNacimiento;
    private Set<Pelicula> peliculas = new HashSet<Pelicula>();
    
    public Director(String nombre) {
        this.nombre = nombre;
    }

    public Director() {
    }

    public Director(String nombre, Date fechaNacimiento, Set<Pelicula> peliculas) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.peliculas = peliculas;
    }

    public Director(int id, String nombre, Date fechaNacimiento, Set<Pelicula> peliculas) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.peliculas = peliculas;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "nombre", nullable = false, length = 200)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_nacimiento", length = 10)
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    //Ojo! Relación 1:N 1 director tiene muchas peliculas
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "director")  //obligatorio mapearlo por ser 1 a n
    public Set<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(Set<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    @Override
    public String toString() {
        return "Director [id=" + id + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + ", peliculas="
                + peliculas + "]";
    }

}
