/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.Incriptar;
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
    
    public ValidarAdmin(){
    
    }
    // Constructor corregido
    public ValidarAdmin(Usuario usuario) {
        this.usuario = usuario;
        System.out.println("✅ Constructor ValidarAdmin ejecutado correctamente.");
    }
    
    // Método para validar credenciales
    /*public Administrador validarAdmin(String nombreUsuario, String contraseña) {
        String consultaSQL = "SELECT * FROM administrador WHERE nombreusuario = ? AND contraseña = ?";
        Administrador administrador = null;
        
        // Encriptar la contraseña que ingresa el usuario antes de enviarla al SQL
        String contraseñaEncriptada = Incriptar.hashSHA256(contraseña);
        
        try (Connection con = conexion.getConnection();
             PreparedStatement pres = con.prepareStatement(consultaSQL)) {

            pres.setString(1, nombreUsuario);
            pres.setString(2, contraseñaEncriptada);
            try (ResultSet result = pres.executeQuery()) {
                if (result.next()) {
                    administrador = new Administrador();
                    administrador.setIdadmin(result.getInt("idadmin"));
                    administrador.setIdlider(result.getInt("idlider"));
                    administrador.setUsuario(result.getString("usuario"));
                    administrador.setNombreusuario(result.getString("nombreusuario"));
                    administrador.setContraseña(result.getString("contraseña"));

                    //System.out.println("✅ Usuario autenticado: " + administrador.getNombre());
                } else {
                    System.out.println("❌ Usuario o contraseña incorrectos.");
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al validar el administrador: " + e.getMessage());
            e.printStackTrace();
        }
        return administrador; // Retorna null si no encuentra el usuario
    }*/
    public Administrador validarAdmin(String nombreUsuario, String contraseña) {
        String sql = "SELECT * FROM administrador WHERE nombreusuario = ?";
        Administrador administrador = null;

        try (Connection con = conexion.getConnection();
             PreparedStatement pres = con.prepareStatement(sql)) {

            pres.setString(1, nombreUsuario);
            try (ResultSet result = pres.executeQuery()) {
                if (result.next()) {
                    // Se obtiene la contraseña guardada (encriptada)
                    String contraseñaBD = result.getString("contraseña").trim();

                    // Se encripta la ingresada
                    String contraseñaIngresadaHash = Incriptar.hashSHA256(contraseña).trim();
                    
                    //System.out.println("Hash ingresado: " + contraseñaIngresadaHash);
                    //System.out.println("Hash en DB: " + contraseñaBD);
                    
                    // Comparar hashes
                    if (contraseñaBD.equals(contraseñaIngresadaHash)) {
                        administrador = new Administrador();
                        administrador.setIdadmin(result.getInt("idadmin"));
                        administrador.setIdlider(result.getInt("idlider"));
                        administrador.setUsuario(result.getString("usuario"));
                        administrador.setNombreusuario(result.getString("nombreusuario"));
                        administrador.setContraseña(contraseñaBD); // puedes omitir si no necesitas mostrarla
                         //System.out.println("✅ Usuario y contraseña correctos. Inicio de sesión válido.");
                    } else {
                        System.out.println("❌ Contraseña incorrecta.");
                    }
                } else {
                    System.out.println("❌ Usuario no encontrado.");
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al validar el administrador: " + e.getMessage());
        }

        return administrador;
    }
    public void actualizarTodasLasContraseñas() {
        String selectSQL = "SELECT idadmin, contraseña FROM administrador";
        String updateSQL = "UPDATE administrador SET contraseña = ? WHERE idadmin = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement psSelect = con.prepareStatement(selectSQL);
             PreparedStatement psUpdate = con.prepareStatement(updateSQL)) {

            ResultSet rs = psSelect.executeQuery();

            while (rs.next()) {
                int idAdmin = rs.getInt("idadmin");
                String contraseñaActual = rs.getString("contraseña");

                if (!Incriptar.esSHA256(contraseñaActual)) {
                    String contraseñaHasheada = Incriptar.hashSHA256(contraseñaActual);

                    psUpdate.setString(1, contraseñaHasheada);
                    psUpdate.setInt(2, idAdmin);
                    psUpdate.executeUpdate();

                    System.out.println("Contraseña actualizada para idadmin = " + idAdmin);
                } else {
                    System.out.println("Contraseña ya está hasheada para idadmin = " + idAdmin);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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

