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
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "actor", catalog = "db_peliculas")
public class Actor implements Serializable {
    
    private int id;
    private String nombre;
    private Date fechaNacimiento;
    private Set<Pelicula> peliculas = new HashSet<Pelicula>();

    
    public Actor(String nombre) {
        this.nombre = nombre;
    }

    public Actor(String nombre, Date fechaNacimiento, Set<Pelicula> peliculas) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.peliculas = peliculas;
    }

    public Actor(int id, String nombre, Date fechaNacimiento, Set<Pelicula> peliculas) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.peliculas = peliculas;
    }

    public Actor() {
    }

    //Definimos las tablas desde aquí.
    //¡OJETE! Las anotaciones el otro día las hicimos arriba, en la propia declaración
    //Las vamos a hacer ahora en el getter & setter

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false) //esto sobraría menos el nombre claro
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "nombre", nullable = false, length = 300)
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

    //Aquí hay una relación de muchos a muchos!!!
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "actores_peliculas", catalog = "db_peliculas", joinColumns = {@JoinColumn(name = "id_actor", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "id_pelicula", nullable = false, updatable = false)})
    public Set<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(Set<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    @Override
    public String toString() {
        return "Actor [id=" + id + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + ", peliculas="
                + peliculas + "]";
    }

}
