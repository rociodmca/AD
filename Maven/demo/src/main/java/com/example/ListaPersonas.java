package com.example;

import java.util.*;

public class ListaPersonas {
    private List<Persona> lista = new ArrayList<Persona>();

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

}
