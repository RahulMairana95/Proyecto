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
    
    
    public Administrador validarAdmin(String nombreUsuario, String contraseña) {
        // Validación especial para el superadmin (no almacenado en la BD)
        if (nombreUsuario.equals("superadmin")) {
            String hashIngresado = Incriptar.hashSHA256(contraseña).trim();
            String hashSuperAdmin = "207c71aacaafa5ca29b0e88414e52bbf56b93a131e9b915c5519938d40ed223d"; // Calcula esto previamente

            if (hashIngresado.equals(hashSuperAdmin)) {
                 /*System.out.println("✅ Contraseña correcta para superadmin.");
                 System.out.println("Contraseña ingresada: " + contraseña);
                 System.out.println("Hash generado: " + hashIngresado);*/
                 
                Administrador administrador = new Administrador();
                administrador.setIdadmin(0); // puedes usar 0 o algún valor fijo
                administrador.setIdlider(0);
                administrador.setUsuario("SUPERADMIN");
                administrador.setNombreusuario("superadmin");
                administrador.setContraseña(hashSuperAdmin);
                administrador.setNombrelider("SUPERADMIN");
                return administrador;

            } else {
                /*System.out.println("Contraseña incorrecta para superadmin.");
                System.out.println("Contraseña ingresada: " + contraseña);
                System.out.println("Hash generado: " + hashIngresado);*/
                return null;
            }
        }

        // Validación regular desde la base de datos
        String sql = "SELECT a.idadmin, a.idlider, a.usuario, a.nombreusuario, a.contraseña, " +
                 "CONCAT(m.nombre, ' ', m.apellidop, ' ', m.apellidom) AS nombre_lider " +
                 "FROM administrador a " +
                 "INNER JOIN lider l ON a.idlider = l.idlider " +
                 "INNER JOIN membrecia m ON l.idmembrecia = m.idmembrecia " +
                 "WHERE a.nombreusuario = ?";
         Administrador administrador = null;

        try (Connection con = conexion.getConnection();
             PreparedStatement pres = con.prepareStatement(sql)) {

            pres.setString(1, nombreUsuario);
            try (ResultSet result = pres.executeQuery()) {
                if (result.next()) {
                    String contraseñaBD = result.getString("contraseña").trim();
                    String contraseñaIngresadaHash = Incriptar.hashSHA256(contraseña).trim();

                    if (contraseñaBD.equals(contraseñaIngresadaHash)) {
                        administrador = new Administrador();
                        administrador.setIdadmin(result.getInt("idadmin"));
                        administrador.setIdlider(result.getInt("idlider"));
                        administrador.setUsuario(result.getString("usuario"));
                        administrador.setNombreusuario(result.getString("nombreusuario"));
                        administrador.setContraseña(contraseñaBD);
                        administrador.setNombrelider(result.getString("nombre_lider"));
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

