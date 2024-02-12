package com.conexionpostgresql;

import java.util.*;

import com.conexionpostgresql.controlador.Controlador;
import com.conexionpostgresql.modelo.ConexionBBDDPostgreSQL;
import com.conexionpostgresql.modelo.Productos;
import com.conexionpostgresql.vista.Vista;

public class Main {
    public static void main(String[] args) {
        
        ConexionBBDDPostgreSQL modelo = new ConexionBBDDPostgreSQL();
        List<Productos> Listproductos = new ArrayList<Productos>();
        Vista vista = new Vista();
        Controlador controlador = new Controlador(modelo, Listproductos, vista);

        controlador.Acciones();
    }

}