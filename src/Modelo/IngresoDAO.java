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
public class IngresoDAO {
    VistaIngreso vistaIngreso;
    public IngresoDAO(){}
    
    public IngresoDAO(VistaIngreso ingreso){
        this.vistaIngreso=ingreso;
    }
    
    public List<Ingreso> listarIngresos() {
        List<Ingreso> lista = new ArrayList<>();
        String sql = "SELECT i.idingreso, i.fecha, i.tipo_ingreso, i.monto, i.descripcion, " +
                     "i.idmembrecia, i.idlider, " +  // 👈 AGREGADO
                     "m1.nombre AS nombre_miembro, m1.apellidop AS apellidop_miembro, m1.apellidom AS apellidom_miembro, " +
                     "m2.nombre AS nombre_lider, m2.apellidop AS apellidop_lider, m2.apellidom AS apellidom_lider " +
                     "FROM ingresos i " +
                     "LEFT JOIN membrecia m1 ON i.idmembrecia = m1.idmembrecia " +
                     "LEFT JOIN lider l ON i.idlider = l.idlider " +
                     "LEFT JOIN membrecia m2 ON l.idmembrecia = m2.idmembrecia";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Ingreso i = new Ingreso();
                i.setIdingreso(rs.getInt("idingreso"));
                i.setFecha(rs.getDate("fecha"));
                i.setTipo_ingreso(rs.getString("tipo_ingreso"));
                i.setMonto(rs.getDouble("monto"));
                i.setDescripcion(rs.getString("descripcion"));

                // ✅ Setea los IDs correctamente
                i.setIdmembrecia(rs.getInt("idmembrecia"));
                i.setIdlider(rs.getInt("idlider"));

                // Nombre del miembro
                String miembroNombre = rs.getString("nombre_miembro") + " " +
                                       rs.getString("apellidop_miembro") + " " +
                                       rs.getString("apellidom_miembro");
                i.setNombreMiembro(miembroNombre);

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
    
    public List<Ingreso> listarIngresosActual() {
        List<Ingreso> lista = new ArrayList<>();

        String sql = "SELECT i.idingreso, i.fecha, i.tipo_ingreso, i.monto, i.descripcion, " +
                     "i.idmembrecia, i.idlider, " +
                     "m1.nombre AS nombre_miembro, m1.apellidop AS apellidop_miembro, m1.apellidom AS apellidom_miembro, " +
                     "m2.nombre AS nombre_lider, m2.apellidop AS apellidop_lider, m2.apellidom AS apellidom_lider " +
                     "FROM ingresos i " +
                     "LEFT JOIN membrecia m1 ON i.idmembrecia = m1.idmembrecia " +
                     "LEFT JOIN lider l ON i.idlider = l.idlider " +
                     "LEFT JOIN membrecia m2 ON l.idmembrecia = m2.idmembrecia " +
                     "WHERE i.fecha BETWEEN ? AND ?";

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
                    Ingreso i = new Ingreso();
                    i.setIdingreso(rs.getInt("idingreso"));
                    i.setFecha(rs.getDate("fecha"));
                    i.setTipo_ingreso(rs.getString("tipo_ingreso"));
                    
                    i.setMonto(rs.getDouble("monto"));
                    
                    
                    i.setDescripcion(rs.getString("descripcion"));
                    i.setIdmembrecia(rs.getInt("idmembrecia"));
                    i.setIdlider(rs.getInt("idlider"));

                    // Nombre del miembro
                    String miembroNombre = rs.getString("nombre_miembro") + " " +
                                           rs.getString("apellidop_miembro") + " " +
                                           rs.getString("apellidom_miembro");
                    i.setNombreMiembro(miembroNombre);

                    // Nombre del líder
                    String liderNombre = rs.getString("nombre_lider") + " " +
                                         rs.getString("apellidop_lider") + " " +
                                         rs.getString("apellidom_lider");
                    i.setNombreLider(liderNombre);
                    System.err.println("Double33333333");
                    lista.add(i);
                    System.err.println("Double4444");
                }
            }

        } catch (SQLException e) {
            System.err.println("Aqui es error");
            e.printStackTrace();
        }
        System.err.println("Double55555");
        return lista;
    }



    public boolean registrarIngreso(Ingreso i) {
        String sql = "INSERT INTO ingresos (fecha, tipo_ingreso, monto, descripcion, idmembrecia, idlider) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(i.getFecha().getTime()));
            ps.setString(2, i.getTipo_ingreso());
            ps.setDouble(3, i.getMonto());
            ps.setString(4, i.getDescripcion());
            ps.setInt(5, i.getIdmembrecia());
            ps.setInt(6, i.getIdlider());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Membrecia> listarMembresia() {
    List<Membrecia> lista = new ArrayList<>();
    String sql = "SELECT idmembrecia, nombre, apellidop, apellidom FROM membrecia";

    try (Connection con = Conexion.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Membrecia m = new Membrecia();
            m.setIdmembrecia(rs.getInt("idmembrecia"));
            m.setNombre(rs.getString("nombre") + " " +
                        rs.getString("apellidop") + " " +
                        rs.getString("apellidom"));
            lista.add(m);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return lista;
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

    public boolean modificarlista(Ingreso i) {
        String sql = "UPDATE ingresos SET fecha = ?, tipo_ingreso = ?, monto = ?, descripcion = ?, idmembrecia = ?, idlider = ? " +
                     "WHERE idingreso = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(i.getFecha().getTime()));
            ps.setString(2, i.getTipo_ingreso());
            ps.setDouble(3, i.getMonto());
            ps.setString(4, i.getDescripcion());
            ps.setInt(5, i.getIdmembrecia());
            ps.setInt(6, i.getIdlider());
            ps.setInt(7, i.getIdingreso()); // este es el ID del ingreso a modificar

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean eliminarIngreso(int id) {
        String sql = "DELETE FROM ingresos WHERE idingreso = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Ingreso> buscarIngresos(String texto) {
        List<Ingreso> lista = new ArrayList<>();

        String sql = "SELECT i.idingreso, i.fecha, i.tipo_ingreso, i.monto, i.descripcion, " +
                     "i.idmembrecia, i.idlider, " +
                     "m1.nombre AS nombre_miembro, m1.apellidop AS apellidop_miembro, m1.apellidom AS apellidom_miembro, " +
                     "m2.nombre AS nombre_lider, m2.apellidop AS apellidop_lider, m2.apellidom AS apellidom_lider " +
                     "FROM ingresos i " +
                     "LEFT JOIN membrecia m1 ON i.idmembrecia = m1.idmembrecia " +
                     "LEFT JOIN lider l ON i.idlider = l.idlider " +
                     "LEFT JOIN membrecia m2 ON l.idmembrecia = m2.idmembrecia " +
                     "WHERE LOWER(i.tipo_ingreso) LIKE ? " +
                     "OR LOWER(i.descripcion) LIKE ? " +
                     "OR LOWER(CONCAT(m1.nombre, ' ', m1.apellidop, ' ', m1.apellidom)) LIKE ? " +
                     "OR LOWER(CONCAT(m2.nombre, ' ', m2.apellidop, ' ', m2.apellidom)) LIKE ? " +
                     "OR DATE_FORMAT(i.fecha, '%Y-%m-%d') LIKE ?";  // 👉 búsqueda por fecha

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String filtro = "%" + texto.toLowerCase() + "%";
            for (int i = 1; i <= 5; i++) {
                ps.setString(i, filtro);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Ingreso i = new Ingreso();
                    i.setIdingreso(rs.getInt("idingreso"));
                    i.setFecha(rs.getDate("fecha"));
                    i.setTipo_ingreso(rs.getString("tipo_ingreso"));
                    i.setMonto(rs.getDouble("monto"));
                    i.setDescripcion(rs.getString("descripcion"));
                    i.setIdmembrecia(rs.getInt("idmembrecia"));
                    i.setIdlider(rs.getInt("idlider"));

                    // Nombre del miembro
                    String miembroNombre = rs.getString("nombre_miembro") + " " +
                                           rs.getString("apellidop_miembro") + " " +
                                           rs.getString("apellidom_miembro");
                    i.setNombreMiembro(miembroNombre);

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


    
    public List<Ingreso> buscarIngresosPorFechaYTipo(Date desde, Date hasta, String tipo) {
        List<Ingreso> lista = new ArrayList<>();
        String sql = "SELECT i.idingreso, i.fecha, i.tipo_ingreso, i.monto, i.descripcion, " +
                     "i.idmembrecia, i.idlider, " +
                     "m1.nombre AS nombre_miembro, m1.apellidop AS apellidop_miembro, m1.apellidom AS apellidom_miembro, " +
                     "m2.nombre AS nombre_lider, m2.apellidop AS apellidop_lider, m2.apellidom AS apellidom_lider " +
                     "FROM ingresos i " +
                     "LEFT JOIN membrecia m1 ON i.idmembrecia = m1.idmembrecia " +
                     "LEFT JOIN lider l ON i.idlider = l.idlider " +
                     "LEFT JOIN membrecia m2 ON l.idmembrecia = m2.idmembrecia " +
                     "WHERE i.fecha BETWEEN ? AND ? AND i.tipo_ingreso = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, desde);
            ps.setDate(2, hasta);
            ps.setString(3, tipo);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Ingreso i = new Ingreso();
                    i.setIdingreso(rs.getInt("idingreso"));
                    i.setFecha(rs.getDate("fecha"));
                    i.setTipo_ingreso(rs.getString("tipo_ingreso"));
                    //i.setMonto(800);
                    System.err.println("Double");
                    i.setMonto(rs.getDouble("monto"));
                    System.err.println("Double2");
                    /*double montoDb = rs.getDouble("monto");
                    if (rs.wasNull()) {
                        i.setMonto(null);
                    } else {
                        i.setMonto(montoDb);
                    }*/
                    
                    i.setDescripcion(rs.getString("descripcion"));
                    i.setIdmembrecia(rs.getInt("idmembrecia"));
                    i.setIdlider(rs.getInt("idlider"));

                    String miembroNombre = rs.getString("nombre_miembro") + " " +
                                           rs.getString("apellidop_miembro") + " " +
                                           rs.getString("apellidom_miembro");
                    i.setNombreMiembro(miembroNombre);

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


}
