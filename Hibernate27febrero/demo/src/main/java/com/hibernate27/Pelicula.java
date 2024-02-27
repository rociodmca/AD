package com.hibernate27;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "pelicula", catalog = "db_peliculas")
public class Pelicula implements Serializable{

    private int id;
    private String titulo;
    private Director director;
    private Date fechaEstreno;
    private Set<Actor> actores = new HashSet<Actor>();
    
    public Pelicula() {
    }

    public Pelicula(String titulo, Director director, Date fechaEstreno, Set<Actor> actores) {
        this.titulo = titulo;
        this.director = director;
        this.fechaEstreno = fechaEstreno;
        this.actores = actores;
    }

    public Pelicula(int id, String titulo, Director director, Date fechaEstreno, Set<Actor> actores) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.fechaEstreno = fechaEstreno;
        this.actores = actores;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "titulo", nullable = false, length = 300)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    //Aquí va a ir una relación de muchos a uno
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_director")
    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_estreno", length = 10)
    public Date getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(Date fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    //Relacion N:M con Actor
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "actores_peliculas", catalog = "db_peliculas", joinColumns = {@JoinColumn(name = "id_pelicula", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "id_actor", nullable = false, updatable = false)})
    public Set<Actor> getActores() {
        return actores;
    }

    public void setActores(Set<Actor> actores) {
        this.actores = actores;
    }

    @Override
    public String toString() {
        return "Pelicula [id=" + id + ", titulo=" + titulo + ", director=" + director + ", fechaEstreno=" + fechaEstreno
                + ", actores=" + actores + "]";
    }

    

}
