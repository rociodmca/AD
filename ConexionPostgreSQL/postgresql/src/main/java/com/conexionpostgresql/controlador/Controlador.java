package com.conexionpostgresql.controlador;

import java.util.*;

import com.conexionpostgresql.modelo.ConexionBBDDPostgreSQL;
import com.conexionpostgresql.modelo.Productos;
import com.conexionpostgresql.vista.Vista;

public class Controlador {
    private ConexionBBDDPostgreSQL modelo;
    private List<Productos> productos;
    private Vista vista;
    private int opcion;
    private Scanner sc = new Scanner(System.in);

    public Controlador(ConexionBBDDPostgreSQL modelo, List<Productos> productos, Vista vista) {
        this.modelo = modelo;
        this.productos = productos;
        this.vista = vista;
    }

    public void Acciones() {
        vista.MandarMensaje("Creando BBDD en PostgreSQL......");
        vista.MandarMensaje(modelo.crearBaseDatos());

        do {
            vista.ListarOpciones();
            try {
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        try {
                            sc.nextLine();
                            vista.MandarMensaje("Introduce el nombre del producto:");
                            String nombreProducto = sc.nextLine();
                            vista.MandarMensaje("Introduce el precio del producto:");
                            float precioProducto = sc.nextFloat();
                            vista.MandarMensaje(modelo.meterDatos(nombreProducto, precioProducto));
                        } catch (InputMismatchException e) {
                            e.printStackTrace();
                        }
                        sc.nextLine();
                        break;
                    case 2:
                        productos = modelo.consultaNoParametrizada();
                        for (Productos productos2 : productos) {
                            vista.MandarMensaje(productos2.toString());
                        }
                        break;
                    case 3:
                        try {
                            sc.nextLine();
                            vista.MandarMensaje("Introduce el precio del producto a buscar:");
                            float precioProducto = sc.nextFloat();
                            productos = modelo.consultaParametrizada(precioProducto);
                            for (Productos productos2 : productos) {
                                vista.MandarMensaje(productos2.toString());
                            }
                        } catch (InputMismatchException e) {
                            e.printStackTrace();
                        }
                        sc.nextLine();
                        break;
                    case 4:
                        vista.MandarMensaje(modelo.consultaCount());
                        break;
                    case 5:
                        try {
                            sc.nextLine();
                            vista.MandarMensaje("Introduce el nuevo nombre del producto:");
                            String nuevoNombreProducto = sc.nextLine();
                            vista.MandarMensaje("Introduce el nuevo precio del producto:");
                            float precioProducto = sc.nextFloat();
                            sc.nextLine();
                            vista.MandarMensaje("Introduce el viejo nombre del producto:");
                            String viejoNombreProducto = sc.nextLine();
                            vista.MandarMensaje(
                                    modelo.actualizarDatos(nuevoNombreProducto, precioProducto, viejoNombreProducto));
                        } catch (InputMismatchException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 6:
                        try {
                            sc.nextLine();
                            vista.MandarMensaje("Introduce el nombre del producto a eliminar:");
                            String nombreProducto = sc.nextLine();
                            vista.MandarMensaje(modelo.eliminarDatos(nombreProducto));
                        } catch (InputMismatchException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 7:
                        try {
                            sc.nextLine();
                            vista.MandarMensaje("Introduce el nombre del producto:");
                            String nombreProducto = sc.nextLine();
                            vista.MandarMensaje("Introduce el precio del producto:");
                            float precioProducto = sc.nextFloat();
                            vista.MandarMensaje(modelo.transaccion(nombreProducto, precioProducto));
                        } catch (InputMismatchException e) {
                            e.printStackTrace();
                        }
                        sc.nextLine();
                        break;
                    default:
                        break;
                }
            } catch (InputMismatchException e) {
                e.printStackTrace();
            }
        } while (opcion > 0 && opcion <= 7);
        sc.close();
        vista.MandarMensaje("Saliendo ......");
    }
}
