package com.example;

import java.util.*;

public class ListaPersonas {
    private List<Persona> lista = new ArrayList<Persona>();

    public List<Persona> getLista() {
        return lista;
    }

    public void setLista(List<Persona> lista) {
        this.lista = lista;
    }

    public void add(Persona per) {
        lista.add(per);
    }

    @Override
    public String toString() {
        return "ListaPersonas [lista=" + lista + "]";
    }

    public List<Persona> getListaPersonas() {
        return lista;
    }

    public int tam() {
        return lista.size();
    }

    public Persona getPersona(int i) {
        return lista.get(i);
    }

}
