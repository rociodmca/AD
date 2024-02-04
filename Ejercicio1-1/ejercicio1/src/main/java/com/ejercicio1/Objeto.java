package com.ejercicio1;

public class Objeto {
    private String ciudad;
    private int temperatura;
    private int presion;
    private String dni;
    private String nombre;
    private String apellido;
    
    public Objeto(String ciudad, int temperatura, int presion, String dni, String nombre, String apellido) {
        this.ciudad = ciudad;
        this.temperatura = temperatura;
        this.presion = presion;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public int getPresion() {
        return presion;
    }

    public void setPresion(int presion) {
        this.presion = presion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    @Override
    public String toString() {
        return "Objeto [ciudad=" + ciudad + ", temperatura=" + temperatura + ", presion=" + presion + ", dni=" + dni
                + ", nombre=" + nombre + ", apellido=" + apellido + "]";
    }
    
}
