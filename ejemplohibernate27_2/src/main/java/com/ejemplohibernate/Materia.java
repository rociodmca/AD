package com.ejemplohibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


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
@Table(name="materia")
public class Materia implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_materia")  
    private long id;
    @Column(name="nombre")
    private String nombre;

 
     @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="UsuarioMateria", joinColumns={@JoinColumn(name="id_materia")}, inverseJoinColumns={@JoinColumn(name="id_usuario")})
    private List<Usuario> usuarios=new ArrayList<Usuario>();
    


    public Materia()
    {
    }
    

    public Materia(String nombre) {
        this.nombre = nombre;
    }


    public long getId()
    {
        return id;
    }

    protected void setId(long id)
    {
        this.id = id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }


    public List<Usuario> getUsuarios() {
        return usuarios;
    }


    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }


    
    @Override
    public String toString() {
        return "Materia [id=" + id + ", nombre=" + nombre + "]";
    }


    
}//Materia