package com.ejemplohibernate;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios") //ojete en minúscula o mayúscula. Case-sensitive.
public class Usuario implements Serializable{
    private static long serialVersionUID=1L;

    //Propiedades
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_usuario")
    private int id_usuario;
    @Column(name="nombre")
    private String nombre;
    @Column(name="apellido")
    private String apellido;
    @Column(name="edad")
    private int edad;

    @OneToOne(cascade = CascadeType.ALL)
    private DireccionPrincipal direccion;

    @OneToMany(cascade= CascadeType.ALL) 
    private List<Libro> libros=new ArrayList<Libro>();

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name="UsuarioMateria", joinColumns={@JoinColumn(name="id_usuario")}, inverseJoinColumns={@JoinColumn(name="id_materia")})
    private List<Materia> materias = new ArrayList<Materia>();

    ///////////////////
// Constructores   
///////////////////
    public Usuario() {
    }


    
    public Usuario(int id, String nombre, String apellido, int edad) {
        this.id_usuario = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }



    public Usuario(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public Usuario(String nombre, String apellido, int edad, DireccionPrincipal direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.direccion = direccion;
    }

        public Usuario(String nombre, String apellido, int edad, DireccionPrincipal direccion, List<Libro> libros) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.direccion = direccion;
        this.libros = libros;
    }

///////////////////
// Fin Constructor
///////////////////

    //Setter & Getter
    public int getId() {
        return id_usuario;
    }
    public void setId(int id) {
        this.id_usuario = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
public DireccionPrincipal getDireccion() {
    return direccion;
}



public void setDireccion(DireccionPrincipal direccion) {
    this.direccion = direccion;
}



public List<Libro> getLibros() {
    return libros;
}



public void setLibros(List<Libro> libros) {
    this.libros = libros;
}



public List<Materia> getMaterias()
{
    return materias;
}

public void setMaterias(List<Materia> materias)
{
    this.materias = materias;
}

public void addMateria(Materia materia)
{
    this.materias.add(materia);
}

public String obtenerListaMaterias() {
    StringBuilder materiasStr = new StringBuilder();
    materiasStr.append(nombre).append(" ").append(apellido).append(":\n");
    
    for (Materia materia : materias) {
        materiasStr.append("- ").append(materia.getNombre()).append("\n");
    }
    
    return materiasStr.toString();
}


@Override
public String toString() {
    return "Usuario [id_usuario=" + id_usuario + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad
            + ", direccion=" + direccion + ", libros=" + libros + ", materias=" + obtenerListaMaterias() + "]";
}



}//Usuario
