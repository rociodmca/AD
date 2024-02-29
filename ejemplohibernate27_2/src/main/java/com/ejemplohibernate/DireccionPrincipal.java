package com.ejemplohibernate;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "direccionppl") //ojete en minúscula o mayúscula. Case-sensitive.
public class DireccionPrincipal implements Serializable{

    private static long serialVersionUID=1L;
    @Id 
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    private int id;
    @Column(name="calle")
    private String calle;
    @Column(name="codigopostal")
    private String codigoPostal;



    /////// Constructores
    public DireccionPrincipal() {
    }

    public DireccionPrincipal(String calle, String codigoPostal) {
        this.calle = calle;
        this.codigoPostal = codigoPostal;
    }
    public DireccionPrincipal(int id, String calle, String codigoPostal) {
        this.id = id;
        this.calle = calle;
        this.codigoPostal = codigoPostal;
    }

    /////// Getter & stters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCalle() {
        return calle;
    }
    public void setCalle(String calle) {
        this.calle = calle;
    }
    public String getCodigoPostal() {
        return codigoPostal;
    }
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

  

    //////// toString
      @Override
    public String toString() {
        return "DireccionPrincipal [id=" + id + ", calle=" + calle + ", codigoPostal=" + codigoPostal + "]";
    }



}//Direccion
