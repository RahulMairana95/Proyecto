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
 * @author LENOVO
 */
public class MinDAO {
    Connection con;
    Conexion consql=new Conexion();
    PreparedStatement pres;
    ResultSet rs;
    
    VistaLiderMin liderMin;
    public MinDAO(){}
    
    public MinDAO(VistaLiderMin min){
        this.liderMin=min;
    }

    
    public List<Ministerio> mostrarlidermin() {
        List<Ministerio> lid = new ArrayList<>();

        // Consulta SQL corregida: Solo seleccionamos las columnas necesarias
        String listarsql = "SELECT l.idmin, m.nombre, m.apellidop, m.apellidom, m.numdocumento, m.telefono, " +
                           "l.ministerio, l.cargo, l.iniciogestion, l.fingestion " +
                           "FROM ministerio l " +
                           "INNER JOIN membrecia m ON l.idmembrecia = m.idmembrecia";

        try {
            con = consql.getConnection();
            pres = con.prepareStatement(listarsql);
            rs = pres.executeQuery();

            while (rs.next()) {
                Ministerio min = new Ministerio();

                // Asignar valores con los índices correctos
                min.setIdmin(rs.getInt(1));
                min.setNombre(rs.getString(2));        // m.nombre
                min.setApellidop(rs.getString(3));     // m.apellidop
                min.setApellidom(rs.getString(4));     // m.apellidom
                min.setNumdocumento(rs.getString(5));            // m.numdocumento
                min.setTelefono(rs.getInt(6));
                min.setMinisterio(rs.getString(7));    // l.ministerio
                min.setCargo(rs.getString(8));         // l.cargo
                min.setIniciogestion(rs.getDate(9));   // l.iniciogestion

                min.setFingestion(rs.getDate(10));      // l.fingestion

                lid.add(min);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Muestra errores en la consola
        } finally {
            // Cerrar conexión para evitar fugas de memoria
            try {
                if (rs != null) rs.close();
                if (pres != null) pres.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Tamaño de la lista: " + lid.size()); // Verificar si hay datos
        return lid;
    }
    
    public boolean agregar(Ministerio lid){
        String agregarsql="insert into ministerio(idmembrecia,ministerio,cargo,iniciogestion,fingestion)"
                + "values(?,?,?,?,?)";
        
        try (Connection con = Conexion.getConnection(); 
             PreparedStatement pres = con.prepareStatement(agregarsql)) {
            
            if (lid.getIdmembrecia() <= 0) {
                JOptionPane.showMessageDialog(null, "ERROR: ID DE MEMBRECIA NO VÁLIDO");
                return false;
            }
            
            //System.out.println(lid.getIdmembrecia()+"id meme");
            pres.setInt(1, lid.getIdmembrecia());
            pres.setString(2, lid.getMinisterio());
            pres.setString(3, lid.getCargo());
            pres.setDate(4, lid.getIniciogestion());
            pres.setDate(5, lid.getFingestion());
            
            int n = pres.executeUpdate();
            return n > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERROR AL INSERTAR: " + e.getMessage());
            return false;
        }
        
    }
    public boolean  modificar(Ministerio lid){
        
        String modificarsql="UPDATE ministerio SET ministerio=?,cargo=?,iniciogestion=?,fingestion=?"
                +"WHERE idmin=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement pres = con.prepareStatement(modificarsql)) {
            
            
            pres.setString(1, lid.getMinisterio());
            pres.setString(2, lid.getCargo());
            pres.setDate(3, lid.getIniciogestion());
            pres.setDate(4, lid.getFingestion());
            
            pres.setInt(5, lid.getIdmin());
            
            int filasAfectadas = pres.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean eliminarlider(int idLid){
        String eliminarsql="DELETE FROM ministerio WHERE idmin=?";
        try (Connection con = Conexion.getConnection(); 
             PreparedStatement ps = con.prepareStatement(eliminarsql)) {
            
            // Establecer el ID del líder que se va a eliminar
            ps.setInt(1, idLid);

            // Ejecutar la actualización y verificar si se eliminó al menos una fila
            int filasAfectadas = ps.executeUpdate();

            // Retornar verdadero si se eliminó al menos una fila, de lo contrario falso
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Ministerio> buscarMinisterio(String texto) {
        List<Ministerio> lista = new ArrayList<>();

        String sql = "SELECT l.idmin, m.nombre, m.apellidop, m.apellidom, m.numdocumento, m.telefono," +
                     "l.ministerio, l.cargo, l.iniciogestion, l.fingestion " +
                     "FROM ministerio l " +
                     "INNER JOIN membrecia m ON l.idmembrecia = m.idmembrecia " +
                     "WHERE LOWER(m.nombre) LIKE ? " +
                     "OR LOWER(m.apellidop) LIKE ? " +
                     "OR LOWER(m.apellidom) LIKE ? " +
                     "OR LOWER(m.numdocumento) LIKE ? " +
                     "OR LOWER(m.telefono) LIKE ? " +
                     "OR LOWER(l.ministerio) LIKE ? " +
                     "OR LOWER(l.cargo) LIKE ? " +
                     "OR DATE_FORMAT(l.iniciogestion, '%Y-%m-%d') LIKE ? " +
                     "OR DATE_FORMAT(l.fingestion, '%Y-%m-%d') LIKE ?";

        try (Connection con = consql.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String filtro = "%" + texto.toLowerCase() + "%";
            for (int i = 1; i <= 8; i++) {
                ps.setString(i, filtro);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ministerio min = new Ministerio();

                min.setIdmin(rs.getInt("idmin"));
                min.setNombre(rs.getString("nombre"));
                min.setApellidop(rs.getString("apellidop"));
                min.setApellidom(rs.getString("apellidom"));
                min.setNumdocumento(rs.getString("numdocumento"));
                min.setTelefono(rs.getInt("telefono"));
                min.setMinisterio(rs.getString("ministerio"));
                min.setCargo(rs.getString("cargo"));
                min.setIniciogestion(rs.getDate("iniciogestion"));
                min.setFingestion(rs.getDate("fingestion"));

                lista.add(min);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Ministerio> buscarPorMinisterio(String ministerio) {
        List<Ministerio> lista = new ArrayList<>();

        String sql = "SELECT l.idmin, m.nombre, m.apellidop, m.apellidom, m.numdocumento, m.telefono," +
                     "l.ministerio, l.cargo, l.iniciogestion, l.fingestion " +
                     "FROM ministerio l " +
                     "INNER JOIN membrecia m ON l.idmembrecia = m.idmembrecia " +
                     "WHERE l.ministerio = ?";

        try (Connection con = consql.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, ministerio);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Ministerio min = new Ministerio();
                min.setIdmin(rs.getInt(1));
                min.setNombre(rs.getString(2));
                min.setApellidop(rs.getString(3));
                min.setApellidom(rs.getString(4));
                min.setNumdocumento(rs.getString(5));
                min.setTelefono(rs.getInt(6));
                min.setMinisterio(rs.getString(7));
                min.setCargo(rs.getString(8));
                min.setIniciogestion(rs.getDate(9));
                min.setFingestion(rs.getDate(10));
                lista.add(min);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    public List<Ministerio> buscarPorNombreOApellido(String textoBusqueda) {
        List<Ministerio> lista = new ArrayList<>();

        String sql = "SELECT l.idmin, m.nombre, m.apellidop, m.apellidom, m.numdocumento, m.telefono, " +
                 "l.ministerio, l.cargo, l.iniciogestion, l.fingestion " +
                 "FROM ministerio l " +
                 "INNER JOIN membrecia m ON l.idmembrecia = m.idmembrecia " +
                 "WHERE LOWER(m.nombre) LIKE ? OR LOWER(m.apellidop) LIKE ? " +
                 "OR LOWER(m.apellidom) LIKE ? OR LOWER(m.numdocumento) LIKE ?";

        try (Connection con = consql.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String filtro = "%" + textoBusqueda.toLowerCase().trim() + "%";
            for (int i = 1; i <= 4; i++) {
                ps.setString(i, filtro);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Ministerio min = new Ministerio();
                min.setIdmin(rs.getInt(1));
                min.setNombre(rs.getString(2));
                min.setApellidop(rs.getString(3));
                min.setApellidom(rs.getString(4));
                min.setNumdocumento(rs.getString(5));
                min.setTelefono(rs.getInt(6));
                min.setMinisterio(rs.getString(7));
                min.setCargo(rs.getString(8));
                min.setIniciogestion(rs.getDate(9));
                min.setFingestion(rs.getDate(10));
                lista.add(min);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    public List<Ministerio> buscarPorCargo(String cargo) {
        List<Ministerio> resultados = new ArrayList<>();

        String sql = "SELECT l.idmin, m.nombre, m.apellidop, m.apellidom, m.numdocumento, m.telefono," +
                     "l.ministerio, l.cargo, l.iniciogestion, l.fingestion " +
                     "FROM ministerio l " +
                     "INNER JOIN membrecia m ON l.idmembrecia = m.idmembrecia " +
                     "WHERE l.cargo = ?";

        try (Connection con = consql.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cargo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Ministerio min = new Ministerio();
                min.setIdmin(rs.getInt(1));
                min.setNombre(rs.getString(2));
                min.setApellidop(rs.getString(3));
                min.setApellidom(rs.getString(4));
                min.setNumdocumento(rs.getString(5));
                min.setTelefono(rs.getInt(6));
                min.setMinisterio(rs.getString(7));
                min.setCargo(rs.getString(8));
                min.setIniciogestion(rs.getDate(9));
                min.setFingestion(rs.getDate(10));
                resultados.add(min);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultados;
    }
    public List<Ministerio> buscarPorInicioGestion(Date desde, Date hasta) {
        List<Ministerio> resultados = new ArrayList<>();

        String sql = "SELECT l.idmin, m.nombre, m.apellidop, m.apellidom, m.numdocumento, m.telefono, " +
                     "l.ministerio, l.cargo, l.iniciogestion, l.fingestion " +
                     "FROM ministerio l " +
                     "INNER JOIN membrecia m ON l.idmembrecia = m.idmembrecia " +
                     "WHERE l.iniciogestion BETWEEN ? AND ?";

        try (Connection con = consql.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(desde.getTime()));
            ps.setDate(2, new java.sql.Date(hasta.getTime()));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Ministerio min = new Ministerio();
                min.setIdmin(rs.getInt(1));
                min.setNombre(rs.getString(2));
                min.setApellidop(rs.getString(3));
                min.setApellidom(rs.getString(4));
                min.setNumdocumento(rs.getString(5));
                min.setTelefono(rs.getInt(6));
                min.setMinisterio(rs.getString(7));
                min.setCargo(rs.getString(8));
                min.setIniciogestion(rs.getDate(9));
                min.setFingestion(rs.getDate(10));
                resultados.add(min);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultados;
    }
    public List<Ministerio> buscarPorFinGestion(Date desde, Date hasta) {
        List<Ministerio> resultados = new ArrayList<>();

        String sql = "SELECT l.idmin, m.nombre, m.apellidop, m.apellidom, m.numdocumento, m.telefono, " +
                     "l.ministerio, l.cargo, l.iniciogestion, l.fingestion " +
                     "FROM ministerio l " +
                     "INNER JOIN membrecia m ON l.idmembrecia = m.idmembrecia " +
                     "WHERE l.fingestion BETWEEN ? AND ?";

        try (Connection con = consql.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(desde.getTime()));
            ps.setDate(2, new java.sql.Date(hasta.getTime()));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Ministerio min = new Ministerio();
                min.setIdmin(rs.getInt(1));
                min.setNombre(rs.getString(2));
                min.setApellidop(rs.getString(3));
                min.setApellidom(rs.getString(4));
                min.setNumdocumento(rs.getString(5));
                min.setTelefono(rs.getInt(6));
                min.setMinisterio(rs.getString(7));
                min.setCargo(rs.getString(8));
                min.setIniciogestion(rs.getDate(9));
                min.setFingestion(rs.getDate(10));
                resultados.add(min);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultados;
    }
    

    
    
}
