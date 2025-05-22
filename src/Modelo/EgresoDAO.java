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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class EgresoDAO {
    
    VistaEgreso vistaEgreso;
    public EgresoDAO(){}
    
    public EgresoDAO(VistaEgreso ve){
        this.vistaEgreso=ve;
    }
    ////////// LISTAR
    public List<Egreso> listarEgresos() {
        List<Egreso> lista = new ArrayList<>();
        String sql = "SELECT e.idegreso, e.fecha, e.tipo_egreso, e.monto, e.descripcion, " +
                     "e.motivo, e.metodo_de_pago, e.idlider, " +  // 👈 AGREGADO
                     "m.nombre AS nombre_lider, m.apellidop AS apellidop_lider, m.apellidom AS apellidom_lider " +
                     "FROM egresos e " +
                     "LEFT JOIN lider l ON e.idlider = l.idlider " +
                     "LEFT JOIN membrecia m ON l.idmembrecia = m.idmembrecia";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Egreso i = new Egreso();
                i.setIdegreso(rs.getInt("idegreso"));
                i.setFecha(rs.getDate("fecha"));
                i.setTipo_egreso(rs.getString("tipo_egreso"));
                i.setMonto(rs.getDouble("monto"));
                i.setDescripcion(rs.getString("descripcion"));
                i.setMotivo(rs.getString("motivo"));
                i.setMetodo_de_pago(rs.getString("metodo_de_pago"));

                // ✅ Setea los IDs correctamente
                //i.setIdmembrecia(rs.getInt("idmembrecia"));
                i.setIdlider(rs.getInt("idlider"));


                // Nombre del líder
                String liderNombre = rs.getString("nombre_lider") + " " +
                                     rs.getString("apellidop_lider") + " " +
                                     rs.getString("apellidom_lider");
                i.setNombreLider(liderNombre);

                lista.add(i);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    public List<Egreso> listarEgresoActual() {
        List<Egreso> lista = new ArrayList<>();

        String sql = "SELECT e.idegreso, e.fecha, e.tipo_egreso, e.monto, e.descripcion, " +
                     "e.motivo, e.metodo_de_pago, e.idlider, " +
                     "m.nombre AS nombre_lider, m.apellidop AS apellidop_lider, m.apellidom AS apellidom_lider " +
                     "FROM egresos e " +
                     "LEFT JOIN lider l ON e.idlider = l.idlider " +
                     "LEFT JOIN membrecia m ON l.idmembrecia = m.idmembrecia " +
                     "WHERE e.fecha BETWEEN ? AND ?";

        // Calcular fechas
        LocalDate fechaFin = LocalDate.now();
        LocalDate fechaInicio = fechaFin.minusMonths(1);

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Setear fechas
            ps.setDate(1, Date.valueOf(fechaInicio));
            ps.setDate(2, Date.valueOf(fechaFin));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Egreso i = new Egreso();
                    i.setIdegreso(rs.getInt("idegreso"));
                    i.setFecha(rs.getDate("fecha"));
                    i.setTipo_egreso(rs.getString("tipo_egreso"));
                    i.setMonto(rs.getDouble("monto"));
                    i.setDescripcion(rs.getString("descripcion"));
                    i.setMotivo(rs.getString("motivo"));
                    i.setMetodo_de_pago(rs.getString("metodo_de_pago"));
                    i.setIdlider(rs.getInt("idlider"));

                    // Nombre del líder
                    String liderNombre = rs.getString("nombre_lider") + " " +
                                         rs.getString("apellidop_lider") + " " +
                                         rs.getString("apellidom_lider");
                    i.setNombreLider(liderNombre);

                    lista.add(i);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    //////REGISTRAR
    public boolean registrarEgreso(Egreso e) {
        if (e == null || e.getFecha() == null || e.getMonto() <= 0) {
            System.err.println("Datos inválidos para registrar egreso.");
            return false;
        }

        String sql = "INSERT INTO egresos (fecha, tipo_egreso, monto, descripcion, motivo, metodo_de_pago, idlider) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(e.getFecha().getTime()));
            ps.setString(2, e.getTipo_egreso());
            ps.setDouble(3, e.getMonto());
            ps.setString(4, e.getDescripcion());
            ps.setString(5, e.getMotivo());
            ps.setString(6, e.getMetodo_de_pago());
            ps.setInt(7, e.getIdlider());

            return ps.executeUpdate() > 0;

        } catch (Exception ex) {
            ex.printStackTrace(); // o usa un logger
            return false;
        }
    }

    
    public List<Lideriglesia> listarLideresConNombres() {
        List<Lideriglesia> lista = new ArrayList<>();
        String sql = "SELECT l.idlider, m.nombre, m.apellidop, m.apellidom " +
                     "FROM lider l " +
                     "INNER JOIN membrecia m ON l.idmembrecia = m.idmembrecia";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Lideriglesia l = new Lideriglesia();
                l.setIdlider(rs.getInt("idlider"));
                l.setNombre(rs.getString("nombre"));
                l.setApellidop(rs.getString("apellidop"));
                l.setApellidom(rs.getString("apellidom"));
                lista.add(l);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public boolean modificarlista(Egreso e){
        String sql = "UPDATE egresos SET fecha = ?, tipo_egreso = ?, monto = ?, descripcion = ?, motivo = ?, metodo_de_pago =?, idlider = ? " +
                     "WHERE idegreso = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(e.getFecha().getTime()));
            ps.setString(2, e.getTipo_egreso());
            ps.setDouble(3, e.getMonto());
            ps.setString(4, e.getDescripcion());
            ps.setString(5, e.getMotivo());
            ps.setString(6, e.getMetodo_de_pago());
            ps.setInt(7, e.getIdlider());
            ps.setInt(8, e.getIdegreso()); // este es el ID del egreso a modificar

            return ps.executeUpdate() > 0;

        } catch (SQLException es) {
            es.printStackTrace();
            return false;
        }
    }
    
    public boolean eliminarEgreso(int id) {
        String sql = "DELETE FROM egresos WHERE idegreso = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Egreso> buscarEgresos(String texto) {
        List<Egreso> lista = new ArrayList<>();
        String sql = "SELECT e.idegreso, e.fecha, e.tipo_egreso, e.monto, e.descripcion, " +
                     "e.motivo, e.metodo_de_pago, e.idlider, " +
                     "m.nombre AS nombre_lider, m.apellidop AS apellidop_lider, m.apellidom AS apellidom_lider " +
                     "FROM egresos e " +
                     "LEFT JOIN lider l ON e.idlider = l.idlider " +
                     "LEFT JOIN membrecia m ON l.idmembrecia = m.idmembrecia " +
                     "WHERE LOWER(CONCAT_WS(' ', " +
                     "DATE_FORMAT(e.fecha, '%Y-%m-%d'), " + // 👈 se incluye fecha como texto
                     "e.tipo_egreso, e.descripcion, e.motivo, e.metodo_de_pago, " +
                     "m.nombre, m.apellidop, m.apellidom)) LIKE ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + texto.toLowerCase() + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Egreso e = new Egreso();
                    e.setIdegreso(rs.getInt("idegreso"));
                    e.setFecha(rs.getDate("fecha"));
                    e.setTipo_egreso(rs.getString("tipo_egreso"));
                    e.setMonto(rs.getDouble("monto"));
                    e.setDescripcion(rs.getString("descripcion"));
                    e.setMotivo(rs.getString("motivo"));
                    e.setMetodo_de_pago(rs.getString("metodo_de_pago"));
                    e.setIdlider(rs.getInt("idlider"));

                    String liderNombre = rs.getString("nombre_lider") + " " +
                                         rs.getString("apellidop_lider") + " " +
                                         rs.getString("apellidom_lider");
                    e.setNombreLider(liderNombre);

                    lista.add(e);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    ///////FILTRAR FECHAS
    public List<Egreso> buscarEgresosPorFechaYTipo(Date fechaDesde, Date fechaHasta, String tipo) {
        List<Egreso> lista = new ArrayList<>();
        String sql = "SELECT e.idegreso, e.fecha, e.tipo_egreso, e.monto, e.descripcion, " +
                     "e.motivo, e.metodo_de_pago, e.idlider, " +
                     "m.nombre AS nombre_lider, m.apellidop AS apellidop_lider, m.apellidom AS apellidom_lider " +
                     "FROM egresos e " +
                     "LEFT JOIN lider l ON e.idlider = l.idlider " +
                     "LEFT JOIN membrecia m ON l.idmembrecia = m.idmembrecia " +
                     "WHERE e.fecha BETWEEN ? AND ? AND e.tipo_egreso = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(fechaDesde.getTime()));
            ps.setDate(2, new java.sql.Date(fechaHasta.getTime()));
            ps.setString(3, tipo);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Egreso i = new Egreso();
                    i.setIdegreso(rs.getInt("idegreso"));
                    i.setFecha(rs.getDate("fecha"));
                    i.setTipo_egreso(rs.getString("tipo_egreso"));
                    i.setMonto(rs.getDouble("monto"));
                    i.setDescripcion(rs.getString("descripcion"));
                    i.setMotivo(rs.getString("motivo"));
                    i.setMetodo_de_pago(rs.getString("metodo_de_pago"));
                    i.setIdlider(rs.getInt("idlider"));

                    // Concatenar nombre completo del líder
                    String nombre = rs.getString("nombre_lider");
                    String apellidop = rs.getString("apellidop_lider");
                    String apellidom = rs.getString("apellidom_lider");

                    String liderNombre = ((nombre != null ? nombre : "") + " " +
                                          (apellidop != null ? apellidop : "") + " " +
                                          (apellidom != null ? apellidom : "")).trim();
                    i.setNombreLider(liderNombre.isEmpty() ? "Sin líder asignado" : liderNombre);

                    lista.add(i);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }


}
