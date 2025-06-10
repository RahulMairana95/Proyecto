/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAHUL
 */
public class LiderDAO {
    Connection con;
    Conexion consql=new Conexion();
    PreparedStatement pres;
    ResultSet rs;
    
    VistaLider vistaLider;
    public LiderDAO(){}
    
    public LiderDAO(VistaLider lider){
        this.vistaLider=lider;
    }
    
    
    /////CONSULTA USANDO JOIN
    public List<Lideriglesia> mostrarlider() {
        List<Lideriglesia> lid = new ArrayList<>();

        String listarsql = "SELECT l.idlider, m.nombre, m.apellidop, m.apellidom, m.numdocumento, " +
                           "l.cargo, l.iniciogestion, l.fingestion " +
                           "FROM lider l " +
                           "INNER JOIN membrecia m ON l.idmembrecia = m.idmembrecia";

        try {
            con = consql.getConnection();
            pres = con.prepareStatement(listarsql);
            rs = pres.executeQuery();

            while (rs.next()) {
                Lideriglesia li = new Lideriglesia();

                li.setIdlider(rs.getInt(1));           // ✅ ID del líder
                li.setNombre(rs.getString(2));         // m.nombre
                li.setApellidop(rs.getString(3));      // m.apellidop
                li.setApellidom(rs.getString(4));      // m.apellidom
                li.setNumdocumento(rs.getString(5));   // m.numdocumento
                li.setCargo(rs.getString(6));          // l.cargo
                li.setIniciogestion(rs.getDate(7));    // l.iniciogestion
                li.setFingestion(rs.getDate(8));       // l.fingestion

                lid.add(li);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pres != null) pres.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Tamaño de la lista: " + lid.size());
        return lid;
    }

    
    public boolean agregarl(Lideriglesia lid) {
        String agregarsql = "INSERT INTO lider(idmembrecia, cargo, iniciogestion, fingestion) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection(); 
             PreparedStatement pres = con.prepareStatement(agregarsql)) {

            // Depuración: Verificar valores antes de la inserción
            System.out.println("ID Membrecia: " + lid.getIdmembrecia());
            System.out.println("Cargo: " + lid.getCargo());
            System.out.println("Fecha Inicio: " + lid.getIniciogestion());
            System.out.println("Fecha Fin: " + lid.getFingestion());

            if (lid.getIdmembrecia() <= 0) {
                JOptionPane.showMessageDialog(null, "ERROR: ID DE MEMBRECIA NO VÁLIDO");
                return false;
            }

            pres.setInt(1, lid.getIdmembrecia());
            pres.setString(2, lid.getCargo());
            pres.setDate(3, new java.sql.Date(lid.getIniciogestion().getTime()));
            pres.setDate(4, new java.sql.Date(lid.getFingestion().getTime()));

            int n = pres.executeUpdate();
            return n > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERROR AL INSERTAR: " + e.getMessage());
            return false;
        }
    }   

    
    public boolean modificar(Lideriglesia lider) {
        String sql = "UPDATE lider SET cargo = ?, iniciogestion = ?, fingestion = ? WHERE idlider = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, lider.getCargo());
            ps.setDate(2, lider.getIniciogestion());
            ps.setDate(3, lider.getFingestion());
            ps.setInt(4, lider.getIdlider());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    

    public boolean eliminar(int idlider) {
        // Consulta SQL para eliminar un líder por su idlider
        String sql = "DELETE FROM lider WHERE idlider = ?";

        // Conexión y declaración preparadas
        try (Connection con = Conexion.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Establecer el ID del líder que se va a eliminar
            ps.setInt(1, idlider);

            // Ejecutar la actualización y verificar si se eliminó al menos una fila
            int filasAfectadas = ps.executeUpdate();

            // Retornar verdadero si se eliminó al menos una fila, de lo contrario falso
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Lideriglesia> buscarLider(String texto) {
        List<Lideriglesia> lista = new ArrayList<>();

        String sql = "SELECT l.idlider, m.nombre, m.apellidop, m.apellidom, m.numdocumento, " +
                     "l.cargo, l.iniciogestion, l.fingestion " +
                     "FROM lider l " +
                     "INNER JOIN membrecia m ON l.idmembrecia = m.idmembrecia " +
                     "WHERE LOWER(m.nombre) LIKE ? " +
                     "OR LOWER(m.apellidop) LIKE ? " +
                     "OR LOWER(m.apellidom) LIKE ? " +
                     "OR LOWER(m.numdocumento) LIKE ? " +
                     "OR LOWER(l.cargo) LIKE ? " +
                     "OR DATE_FORMAT(l.iniciogestion, '%Y-%m-%d') LIKE ? " +
                     "OR DATE_FORMAT(l.fingestion, '%Y-%m-%d') LIKE ?";

        try (Connection con = consql.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String filtro = "%" + texto.toLowerCase() + "%";
            for (int i = 1; i <= 7; i++) {
                ps.setString(i, filtro);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Lideriglesia li = new Lideriglesia();
                li.setIdlider(rs.getInt("idlider"));
                li.setNombre(rs.getString("nombre"));
                li.setApellidop(rs.getString("apellidop"));
                li.setApellidom(rs.getString("apellidom"));
                li.setNumdocumento(rs.getString("numdocumento"));
                li.setCargo(rs.getString("cargo"));
                li.setIniciogestion(rs.getDate("iniciogestion"));
                li.setFingestion(rs.getDate("fingestion"));
                lista.add(li);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    public List<Lideriglesia> buscarLideres(String texto) {
        List<Lideriglesia> resultados = new ArrayList<>();

        String sql = "SELECT l.idlider, m.nombre, m.apellidop, m.apellidom, m.numdocumento, " +
                     "l.cargo, l.iniciogestion, l.fingestion " +
                     "FROM lider l " +
                     "INNER JOIN membrecia m ON l.idmembrecia = m.idmembrecia " +
                     "WHERE m.numdocumento LIKE ? OR " +
                     "CONCAT(m.nombre, ' ', m.apellidop, ' ', m.apellidom) LIKE ?";

        try (Connection con = consql.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + texto + "%");
            ps.setString(2, "%" + texto + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Lideriglesia li = new Lideriglesia();
                li.setIdlider(rs.getInt(1));
                li.setNombre(rs.getString(2));
                li.setApellidop(rs.getString(3));
                li.setApellidom(rs.getString(4));
                li.setNumdocumento(rs.getString(5));
                li.setCargo(rs.getString(6));
                li.setIniciogestion(rs.getDate(7));
                li.setFingestion(rs.getDate(8));
                resultados.add(li);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultados;
    }




}

