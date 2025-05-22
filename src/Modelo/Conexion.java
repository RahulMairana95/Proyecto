/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author RAHUL OMERO MAIRANAs
 */
public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/membrecia?useTimezone=true&serverTimezone=America/La_Paz";
    private static final String USER = "root";  
    private static final String PASSWORD = "";
    
    public static Connection conectar = null; // Variable de conexión global

    // Método para obtener la conexión
    public static Connection getConnection() {
        try {
            // Si la conexión está cerrada o no existe, crear una nueva
            if (conectar == null || conectar.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conectar = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Conectado exitosamente a la base de datos.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Error: No se encontró el driver de MySQL.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar a la base de datos.");
            e.printStackTrace();
        }
        return conectar;
    }

    // Método para cerrar la conexión
    public static void closeConnection() {
        try {
            if (conectar != null && !conectar.isClosed()) {
                conectar.close();
                System.out.println("🔌 Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al cerrar la conexión.");
            e.printStackTrace();
        }
    }
}
