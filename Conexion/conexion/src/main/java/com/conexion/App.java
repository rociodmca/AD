package com.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Programa principal
public class App {
    public static void main(String[] args) {
        // Configuración de la conexión a la base de datos
        String URL = "jdbc:mysql://localhost:33060";
        String USUARIO = "root";
        String CONTRASENA = "secret";

        Connection conexion;
        conexion = establecerConexion(URL, USUARIO, CONTRASENA);

        if (conexion != null) {
            crearBaseDatos(conexion);
            seleccionarBase(conexion, "prueba1");
            crearTabla(conexion);
            meterDatos(conexion);
            consultaNoParametrizada(conexion);
            consultaParametrizada(conexion, 10.99f);
            consultaCount(conexion);
            actualizarDatos(conexion, "satisfyer", 100.3f, "Producto1");
            eliminarDatos(conexion, "satisfyer");
            transaccion(conexion);
        }

    }

    private static void transaccion(Connection conexion) {
        String sentenciaSQL = "INSERT INTO productos (nombre, precio) VALUES (?, ?);";
        
        String nombreProducto = "MiProducto";
        float precioProducto = 12f;
        //PreparedStatement sentencia = null;
        System.out.println("Damos de alta MiProducto");

        try {
            conexion.setAutoCommit(false); //iniciamos la transición. Deshabilitamos el autocommit
            PreparedStatement sentenciaAltaProducto = conexion.prepareStatement(sentenciaSQL, PreparedStatement.RETURN_GENERATED_KEYS);
            sentenciaAltaProducto.setString(1, nombreProducto);
            sentenciaAltaProducto.setFloat(2, precioProducto);
            int elementos = sentenciaAltaProducto.executeUpdate(); //--> nos devuelve
            //Validamos la ejecución de la sentencia
            conexion.commit();
            System.out.println("Hemos añadido: " + elementos);
            consultaNoParametrizada(conexion);
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    private static void eliminarDatos(Connection conexion, String nombreBorrar) {
        String sentenciaSQL = "DELETE FROM productos WHERE nombre = ?";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSQL);
            sentencia.setString(1, nombreBorrar);
            sentencia.executeUpdate();
            System.out.println("Borrado!");
            consultaNoParametrizada(conexion);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            liberarRecursos(sentencia);
        }
    }

    private static void actualizarDatos(Connection conexion, String nuevoProducto, float nuevoPrecio, String viejoNombre) {
        String sentenciaSQL = "UPDATE productos SET nombre = ?, precio = ? WHERE nombre = ?";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSQL);
            sentencia.setString(1, nuevoProducto);
            sentencia.setFloat(2, nuevoPrecio);
            sentencia.setString(3, viejoNombre);
            sentencia.executeUpdate();
            System.out.println("Se ha actualizado el producto");
            consultaNoParametrizada(conexion);
        } catch (SQLException e) {
        }
        liberarRecursos(sentencia);
    }

    private static void consultaCount(Connection conexion) {
        //Consultas con funciones agregadas
        String sentenciaSQL = "SELECT COUNT(*) FROM productos;";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSQL);
            resultado = sentencia.executeQuery();
            resultado.next();
            System.out.println("La cantidad de productos son: " + resultado.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            liberarRecursos(sentencia, resultado);
        }
    }

    private static void consultaParametrizada(Connection conexion, float filtroPrecio) {
        String sentenciaSQL = "SELECT nombre, precio FROM productos WHERE precio = ?";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSQL);
            sentencia.setFloat(1, (float)filtroPrecio);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                System.out.println("Dentro");
                System.out.println("nombre: " + resultado.getString(1));
                System.out.println("precio: " + resultado.getFloat(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            //liberarRecursos(sentencia, resultado);
        }
    }

    private static void consultaNoParametrizada(Connection conexion) {
        String sentenciaSQL = "SELECT nombre, precio FROM productos";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSQL);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                System.out.println("nombre: " + resultado.getString(1));
                System.out.println("precio: " + resultado.getFloat(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            liberarRecursos(sentencia, resultado);
        }
            
    }

    private static void meterDatos(Connection conexion) {
        //Sentencia SQL para insertar un nuevo producto
        String sentenciaSQL = "INSERT INTO productos (nombre, precio) VALUES (?, ?);";

        String nombreProducto = "Producto1";
        float precioProducto = 10.99f;
        PreparedStatement sentencia = null;
        try {
            sentencia = conexion.prepareStatement(sentenciaSQL);
            sentencia.setString(1, nombreProducto);
            sentencia.setDouble(2, precioProducto);

            sentencia.executeUpdate();
            System.out.println("Insert correctamente hecho!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        liberarRecursos(sentencia);
    }

    private static void crearTabla(Connection conexion) {
        PreparedStatement sentencia = null;
        String crearTablaSQL = "CREATE TABLE IF NOT EXISTS productos(id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(100) NOT NULL, precio FLOAT NOT NULL);";

        // Ejecuto la sentencia SQL
        try {
            sentencia = conexion.prepareStatement(crearTablaSQL);
            sentencia.executeUpdate();
            System.out.println("Creada la tabla correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        liberarRecursos(sentencia);

    }

    private static void seleccionarBase(Connection conexion, String string) {
        // Seleccionamos la base de datos
        try {
            conexion.setCatalog(string);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error");
        }
        ;
    }

    private static void crearBaseDatos(Connection conexion) {
        PreparedStatement sentencia = null;
        // SEntencia SQL que crea la base de datos => String
        String creaBaseDatosSQL = "CREATE DATABASE IF NOT EXISTS prueba1;";

        try {
            sentencia = conexion.prepareStatement(creaBaseDatosSQL);
            sentencia.executeUpdate();
            System.out.println("Base de datos creada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        liberarRecursos(sentencia);

    }

    public static Connection establecerConexion(String url, String usuario, String contrasena) {
        Connection conexion = null;

        try {
            // Cargar el controlador de mysql
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Establecemos la conexión
            conexion = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("Funciona correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conexion;
    }

    static void liberarRecursos(PreparedStatement sentencia) {
        // liberamos recursos
        if (sentencia != null) {
            try {
                sentencia.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static void liberarRecursos(PreparedStatement sentencia, ResultSet resultado) {
        // liberamos recursos
        if (sentencia != null) {
            try {
                sentencia.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            resultado.close();
        } catch (SQLException e) {
            System.out.println("Fallo al liberar el ResultSet");
            e.printStackTrace();
        }
    }
}
