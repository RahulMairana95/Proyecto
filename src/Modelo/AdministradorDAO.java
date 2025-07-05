/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.Incriptar;
import Vista.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author RAHUL
 */
public class AdministradorDAO {
    Connection con;
    Conexion conectar = new Conexion();
    PreparedStatement pres;
    ResultSet rs;
    
    VistaRegistro vistaRegistro;
    
    public AdministradorDAO(){}
    
    
    public AdministradorDAO(VistaRegistro vr){
        //System.out.println("vistaaaaaaa");
        this.vistaRegistro=vr;
    }
    public List<Administrador> listarAdministradores() {
        List<Administrador> lista = new ArrayList<>();
        String sql = "SELECT a.idadmin, a.idlider, a.usuario, a.nombreusuario, a.contraseña, " +
                     "m.nombre AS nombre_lider, m.apellidop AS apellidop_lider, m.apellidom AS apellidom_lider " +
                     "FROM administrador a " +
                     "LEFT JOIN lider l ON a.idlider = l.idlider " +
                     "LEFT JOIN membrecia m ON l.idmembrecia = m.idmembrecia";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Administrador admin = new Administrador();
                admin.setIdadmin(rs.getInt("idadmin"));
                admin.setIdlider(rs.getInt("idlider"));
                admin.setUsuario(rs.getString("usuario"));
                admin.setNombreusuario(rs.getString("nombreusuario"));
                admin.setContraseña(rs.getString("contraseña"));

                // Nombre completo del líder
                String lidernombre = rs.getString("nombre_lider") + " " +
                                     rs.getString("apellidop_lider") + " " +
                                     rs.getString("apellidom_lider");
                admin.setNombrelider(lidernombre); // Este campo debe estar en tu clase Administrador

                lista.add(admin);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    public boolean registrarAdministrador(Administrador a) {
        String sql = "INSERT INTO administrador (idlider, usuario, nombreusuario, contraseña) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Encriptar la contraseña antes de guardarla
            //String contraseñaEncriptada = Incriptar.hashSHA256(a.getContraseña());
            
            ps.setInt(1, a.getIdlider());
            ps.setString(2, a.getUsuario());
            ps.setString(3, a.getNombreusuario());
            
                    // Encriptar solo si no está en SHA-256
            String contraseñaIngresada = a.getContraseña();
            String contraseñaEncriptada = Incriptar.esSHA256(contraseñaIngresada)
                    ? contraseñaIngresada
                    : Incriptar.hashSHA256(contraseñaIngresada);
            
            ps.setString(4, contraseñaEncriptada);  // Usar la contraseña encriptada
            //ps.setString(4, a.getContraseña());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*public boolean editarAdministrador(Administrador a) {
        String sql = "UPDATE administrador SET idlider = ?, usuario = ?, nombreusuario = ?, contraseña = ? WHERE idadmin = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, a.getIdlider());
            ps.setString(2, a.getUsuario());
            ps.setString(3, a.getNombreusuario());
            ps.setString(4, a.getContraseña());
            ps.setInt(5, a.getIdadmin()); // Asegúrate de que el objeto tenga este campo

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }*/
    public boolean editarAdministrador(Administrador a) {
        String sql = "UPDATE administrador SET idlider = ?, usuario = ?, nombreusuario = ?, contraseña = ? WHERE idadmin = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, a.getIdlider());
            ps.setString(2, a.getUsuario());
            ps.setString(3, a.getNombreusuario());

            // Asegúrate de que la contraseña esté encriptada antes de guardar
            String contraseñaOriginal = a.getContraseña();
            String contraseñaEncriptada = Incriptar.esSHA256(contraseñaOriginal)
                    ? contraseñaOriginal
                    : Incriptar.hashSHA256(contraseñaOriginal);

            ps.setString(4, contraseñaEncriptada);
            ps.setInt(5, a.getIdadmin());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public boolean eliminarAdministrador(int idadmin) {
        String sql = "DELETE FROM administrador WHERE idadmin = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idadmin);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean actualizarContraseña(int idadmin, String nuevaContraseñaHash) {
        String sql = "UPDATE administrador SET contraseña = ? WHERE idadmin = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nuevaContraseñaHash);
            ps.setInt(2, idadmin);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    
}
