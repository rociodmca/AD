package com.conexionpostgresql.vista;

public class Vista {
    
    public void ListarOpciones() {
        System.out.println("------------------MENÚ-BASE-DE-DATOS-POSTGRESQL-------------------");
        System.out.println("1.- Insertar datos en la tabla");
        System.out.println("2.- Consultar los datos de la tabla.");
        System.out.println("3.- Consultar los datos de la tabla de manera parametrizada.");
        System.out.println("4.- Consultar la cantidad de datos que tiene la tabla.");
        System.out.println("5.- Actualizar datos de la tabla.");
        System.out.println("6.- Eliminar datos.");
        System.out.println("7.- Hacer una transacción.");
        System.out.println("------------------------------------------------------------------");
        System.out.println("Introduce el número correspondiente a la opción elegida:");
    }

    public void MandarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
