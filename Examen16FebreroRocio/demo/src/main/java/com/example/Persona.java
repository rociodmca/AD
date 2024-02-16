package com.example;

public class Persona {
    private String sexo;
    private String edad;
    private String periodo;
    private String total;
    
    public Persona(String sexo, String edad, String periodo, String total) {
        this.sexo = sexo;
        this.edad = edad;
        this.periodo = periodo;
        this.total = total;
    }

    public Persona() {
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Persona [sexo=" + sexo + ", edad=" + edad + ", periodo=" + periodo + ", total=" + total + "]";
    }

}
