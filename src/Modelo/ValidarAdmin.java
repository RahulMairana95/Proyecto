/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Vista.*;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author RAHUL
 */
public class ValidarAdmin {
    private Connection con;
    private final Conexion conexion = new Conexion();
    private Usuario usuario;

    // Constructor corregido
    public ValidarAdmin(Usuario usuario) {
        this.usuario = usuario;
        System.out.println("✅ Constructor ValidarAdmin ejecutado correctamente.");
    }
    
    // Método para validar credenciales
    public Administrador validarAdmin(String nombreUsuario, String contraseña) {
        String consultaSQL = "SELECT * FROM administrador WHERE nombreusuario = ? AND contraseña = ?";
        Administrador administrador = null;

        try (Connection con = conexion.getConnection();
             PreparedStatement pres = con.prepareStatement(consultaSQL)) {

            pres.setString(1, nombreUsuario);
            pres.setString(2, contraseña);
            try (ResultSet result = pres.executeQuery()) {
                if (result.next()) {
                    administrador = new Administrador();
                    administrador.setIdadmin(result.getInt("idadmin"));
                    administrador.setIdlider(result.getInt("idlider"));
                    administrador.setNombre(result.getString("nombre"));
                    administrador.setApellidos(result.getString("apellidos"));
                    administrador.setNumdocumento(result.getString("numdocumento"));
                    administrador.setTelefono(result.getInt("telefono"));
                    administrador.setEmail(result.getString("email"));
                    administrador.setUsuario(result.getString("usuario"));
                    administrador.setNombreusuario(result.getString("nombreusuario"));
                    administrador.setContraseña(result.getString("contraseña"));

                    System.out.println("✅ Usuario autenticado: " + administrador.getNombre());
                } else {
                    System.out.println("❌ Usuario o contraseña incorrectos.");
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al validar el administrador: " + e.getMessage());
            e.printStackTrace();
        }
        return administrador; // Retorna null si no encuentra el usuario
    }
    public boolean existeUsuario(String nombreUsuario) {
    String sql = "SELECT 1 FROM administrador WHERE nombreusuario = ?";
    try (Connection con = conexion.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, nombreUsuario);
        try (ResultSet rs = ps.executeQuery()) {
            return rs.next();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

}

