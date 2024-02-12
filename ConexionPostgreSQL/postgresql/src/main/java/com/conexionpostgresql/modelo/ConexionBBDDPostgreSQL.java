package com.conexionpostgresql.modelo;

import java.sql.*;
import java.util.*;

public class ConexionBBDDPostgreSQL {
    private final String DRIVER = "org.postgresql.Driver";
    private final String URL = "jdbc:postgresql://localhost:5432/";
    private final String USER = "postgres";
    private final String PASSWORD = "secret";
    private Connection conexion;

    public ConexionBBDDPostgreSQL() {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public String crearBaseDatos() {
        if (conexion != null) {
            PreparedStatement sentencia = null;
            String urlBBDD = URL;
            // Sentencia SQL que crea la base de datos => String
            String creaBaseDatosSQL = "CREATE DATABASE prueba1;";

            try {
                sentencia = conexion.prepareStatement(creaBaseDatosSQL);
                sentencia.execute();
                // System.out.println("Base de datos creada correctamente.");
                return "Base de datos creada correctamente.";
            } catch (SQLException e) {
                // System.out.println("La base de datos ya existe");
                urlBBDD += "prueba1";
                liberarRecursos(sentencia);
                try {
                    conexion = DriverManager.getConnection(urlBBDD, USER, PASSWORD);
                } catch (SQLException e1) {
                    return "Error de conexión a la base de datos";
                }
                return "La base de datos ya existe";
            }
        } else {
            return "La conexión no se ha establecido";
        }
    }

    public String crearTabla() {
        if (conexion != null) {
            PreparedStatement sentencia = null;
            String crearTablaSQL = "CREATE TABLE IF NOT EXISTS productos (id SERIAL PRIMARY KEY, nombre VARCHAR(100) NOT NULL, precio FLOAT NOT NULL);";

            // Ejecuto la sentencia SQL
            try {
                sentencia = conexion.prepareStatement(crearTablaSQL);
                sentencia.executeUpdate();
                // System.out.println("Creada la tabla correctamente.");
                return "Creada la tabla correctamente.";
            } catch (SQLException e) {
                return "Error al crear la tabla";
            } finally {
                liberarRecursos(sentencia);
            }
        } else {
            return "La conexión no se ha establecido";
        }
    }

    public String meterDatos(String nombre, double precio) {
        if (conexion != null) {
            // Sentencia SQL para insertar un nuevo producto
            String sentenciaSQL = "INSERT INTO productos (nombre, precio) VALUES (?, ?);";

            PreparedStatement sentencia = null;
            try {
                sentencia = conexion.prepareStatement(sentenciaSQL);
                sentencia.setString(1, nombre);
                sentencia.setDouble(2, precio);

                sentencia.executeUpdate();
                // System.out.println("Insert correctamente hecho!");
                return "Insert correctamente hecho!";
            } catch (SQLException e) {
                // e.printStackTrace();
                return "Error, no se han podido insertar los datos.";
            } finally {
                liberarRecursos(sentencia);
            }
        } else {
            return "La conexión no se ha establecido";
        }
    }

    public List<Productos> consultaNoParametrizada() {
        if (conexion != null) {
            String sentenciaSQL = "SELECT id, nombre, precio FROM productos";
            PreparedStatement sentencia = null;
            ResultSet resultado = null;
            List<Productos> productosList = new ArrayList<Productos>();

            try {
                sentencia = conexion.prepareStatement(sentenciaSQL);
                resultado = sentencia.executeQuery();
                while (resultado.next()) {
                    productosList
                            .add(new Productos(resultado.getInt(1), resultado.getString(2), resultado.getFloat(3)));
                }
                return productosList;
            } catch (SQLException e) {
                // e.printStackTrace();
                return null;
            } finally {
                liberarRecursos(sentencia, resultado);
            }
        } else {
            return null;
        }
    }

    public List<Productos> consultaParametrizada(float filtroPrecio) {
        if (conexion != null) {
            String sentenciaSQL = "SELECT id, nombre, precio FROM productos WHERE precio = ?";
            PreparedStatement sentencia = null;
            ResultSet resultado = null;
            List<Productos> productosList = new ArrayList<Productos>();

            try {
                sentencia = conexion.prepareStatement(sentenciaSQL);
                sentencia.setFloat(1, (float) filtroPrecio);
                resultado = sentencia.executeQuery();
                while (resultado.next()) {
                    productosList
                            .add(new Productos(resultado.getInt(1), resultado.getString(2), resultado.getFloat(3)));
                }
                return productosList;
            } catch (SQLException e) {
                return null;
            } finally {
                liberarRecursos(sentencia, resultado);
            }
        } else {
            return null;
        }
    }

    public String consultaCount() {
        if (conexion != null) {
            // Consultas con funciones agregadas
            String sentenciaSQL = "SELECT COUNT(*) FROM productos;";
            PreparedStatement sentencia = null;
            ResultSet resultado = null;

            try {
                sentencia = conexion.prepareStatement(sentenciaSQL);
                resultado = sentencia.executeQuery();
                resultado.next();
                return "La cantidad de productos son: " + resultado.getString(1);
            } catch (SQLException e) {
                // e.printStackTrace();
                return "Error, no se han podido contar los productos";
            } finally {
                liberarRecursos(sentencia, resultado);
            }
        } else {
            return "La conexión no se ha establecido";
        }
    }

    public String actualizarDatos(String nuevoProducto, float nuevoPrecio, String viejoNombre) {
        if (conexion != null) {
            String sentenciaSQL = "UPDATE productos SET nombre = ?, precio = ? WHERE nombre = ?";
            PreparedStatement sentencia = null;

            try {
                sentencia = conexion.prepareStatement(sentenciaSQL);
                sentencia.setString(1, nuevoProducto);
                sentencia.setFloat(2, nuevoPrecio);
                sentencia.setString(3, viejoNombre);
                sentencia.executeUpdate();
                return "Se ha actualizado el producto";
            } catch (SQLException e) {
                return "Error en la actualización.";
            } finally {
                liberarRecursos(sentencia);
            }
        } else {
            return "La conexión no se ha establecido";
        }
    }

    public String eliminarDatos(String nombreBorrar) {
        if (conexion != null) {
            String sentenciaSQL = "DELETE FROM productos WHERE nombre = ?";
            PreparedStatement sentencia = null;

            try {
                sentencia = conexion.prepareStatement(sentenciaSQL);
                sentencia.setString(1, nombreBorrar);
                sentencia.executeUpdate();
                return "Borrado!";
            } catch (SQLException e) {
                // e.printStackTrace();
                return "Error en el borrado";
            } finally {
                liberarRecursos(sentencia);
            }
        } else {
            return "La conexión no se ha establecido";
        }
    }

    public String transaccion(String nombre, float precio) {
        if (conexion != null) {
            String sentenciaSQL = "INSERT INTO productos (nombre, precio) VALUES (?, ?);";

            try {
                conexion.setAutoCommit(false); // iniciamos la transición. Deshabilitamos el autocommit
                PreparedStatement sentenciaAltaProducto = conexion.prepareStatement(sentenciaSQL,
                        PreparedStatement.RETURN_GENERATED_KEYS);
                sentenciaAltaProducto.setString(1, nombre);
                sentenciaAltaProducto.setFloat(2, precio);
                int elementos = sentenciaAltaProducto.executeUpdate(); // --> nos devuelve
                // Validamos la ejecución de la sentencia
                conexion.commit();
                return "Hemos añadido: " + elementos;
            } catch (SQLException e) {
                // e.printStackTrace();
                return "Error al añadir elementos";
            }
        } else {
            return "La conexión no se ha establecido";
        }
    }

    private static void liberarRecursos(PreparedStatement sentencia) {
        // liberamos recursos
        if (sentencia != null) {
            try {
                sentencia.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void liberarRecursos(PreparedStatement sentencia, ResultSet resultado) {
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
