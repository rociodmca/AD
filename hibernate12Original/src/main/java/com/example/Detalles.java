package com.example;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalles") 
public class Detalles implements Serializable {
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_telefono")
    private int id;
    @Column(name="trabajo")
    private boolean trabajo;
    @Column(name="propio")
    private boolean propio;




    
    public Detalles() {
    }


    public Detalles(boolean trabajo) {
        this.trabajo = trabajo;
    }


    public Detalles(boolean b, boolean c) {
        this.trabajo = b;
        this.propio=c;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public boolean isTrabajo() {
        return trabajo;
    }


    public void setTrabajo(boolean trabajo) {
        this.trabajo = trabajo;
    }


    public boolean isPropio() {
        return propio;
    }


    public void setPropio(boolean propio) {
        this.propio = propio;
    }


    @Override
    public String toString() {
        return "Detalles [id=" + id + ", trabajo=" + trabajo + ", propio=" + propio + "]";
    }


    

}
