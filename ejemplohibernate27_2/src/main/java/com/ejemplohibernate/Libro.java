package com.ejemplohibernate;

import java.io.Serializable;

import org.hibernate.annotations.Cascade;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "libro") 
public class Libro implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Column(name="titulo")   
    private String titulo;
    @Column(name="isbn")
    private String isbn;

    @ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    
///////// Constructores
    public Libro() {
    }
    
    public Libro(String titulo, String isbn) {
        this.titulo = titulo;
        this.isbn = isbn;
    }


    public Libro(String titulo, String isbn, Usuario usuario) {
            this.titulo = titulo;
            this.isbn = isbn;
            this.usuario = usuario;
        }
    
//////////////////////


    ///////// getter & setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Libro [id=" + id + ", titulo=" + titulo + ", isbn=" + isbn + "]";
    }
    


    
}//Libro
