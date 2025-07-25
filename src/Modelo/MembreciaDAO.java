/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.*;
import java.io.*;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

/**
 *
 * @author RAHUL
 */
public class MembreciaDAO {
    Connection con;
    Conexion conectarMySQL=new Conexion();
    PreparedStatement pres;
    ResultSet rs;
    
    VistaMembrecia vistaMembrecia;
    
    
    Workbook wb;
    public MembreciaDAO(){
    };
    
    public MembreciaDAO(VistaMembrecia vm){
        this.vistaMembrecia=vm;
    }
    
    public List listarMembrecia(){
        List<Membrecia> listamem = new ArrayList<>();
        String consultas="select * from membrecia";
        
        try {
            con=conectarMySQL.getConnection();
            pres=con.prepareStatement(consultas);
            rs=pres.executeQuery();
            
            System.out.println("listando db");
            
            while(rs.next()){
                Membrecia mem=new Membrecia();
                mem.setIdmembrecia(rs.getInt(1));
                mem.setNombre(rs.getString(2));
                mem.setApellidop(rs.getString(3));
                mem.setApellidom(rs.getString(4));
                mem.setNumdocumento(rs.getString(5));
                mem.setFechanacimiento(rs.getDate(6));
                mem.setEstadocivil(rs.getString(7));
                mem.setFechaconversion(rs.getDate(8));
                mem.setFechabautizo(rs.getDate(9));
                mem.setTalentos(rs.getString(10));
                mem.setDones(rs.getString(11));
                mem.setActivo(rs.getString(12));
                mem.setDireccion(rs.getString(13));
                mem.setTelefono(rs.getInt(14));
                mem.setNomreferencia(rs.getString(15));
                mem.setNumreferencia(rs.getInt(16));
                
               // mem.setIdmembrecia(rs.getInt(12));
                
                listamem.add(mem);
                
            }
            //System.out.println("mostrar"+ listamem);
            
        } catch (Exception e) {
        }
        return listamem;
    }
    public boolean agregar(Membrecia men) {
        String agregarmsql = "INSERT INTO membrecia(nombre, apellidop, apellidom, numdocumento, fechanacimiento, estadocivil, fechaconversion, fechabautizo, talentos, dones, activo, direccion, telefono, nomreferencia, numreferencia) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            con = Conexion.getConnection();

            pres = con.prepareStatement(agregarmsql);
            
            pres.setString(1, men.getNombre());
            pres.setString(2, men.getApellidop());
            pres.setString(3, men.getApellidom());
            pres.setString(4, men.getNumdocumento());
            pres.setDate(5, men.getFechanacimiento());
            pres.setString(6, men.getEstadocivil());
            pres.setDate(7, men.getFechaconversion());
            pres.setDate(8, men.getFechabautizo());
            pres.setString(9, men.getTalentos());
            pres.setString(10, men.getDones());
            pres.setString(11, men.getActivo());
            pres.setString(12, men.getDireccion());
            pres.setInt(13, men.getTelefono());
            pres.setString(14, men.getNomreferencia());
            pres.setInt(15, men.getNumreferencia());

            int n = pres.executeUpdate();
            return n > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar: " + e.getMessage());
            return false;

        } finally {
            try {
                if (pres != null) pres.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean modificar(Membrecia mem) {
    String sql = "UPDATE membrecia SET nombre=?, apellidop=?, apellidom=?, numdocumento=?, fechanacimiento=?, " +
                 "estadocivil=?, fechaconversion=?, fechabautizo=?, talentos=?, dones=?, activo=?, direccion=?, " +
                 "telefono=?, nomreferencia=?, numreferencia=? WHERE idmembrecia=?";
    
    System.out.println("Modificando...");

    try (Connection con = conectarMySQL.getConnection();
         PreparedStatement pres = con.prepareStatement(sql)) {
        
        pres.setString(1, mem.getNombre());
        pres.setString(2, mem.getApellidop());
        pres.setString(3, mem.getApellidom());
        pres.setString(4, mem.getNumdocumento());
        pres.setDate(5, mem.getFechanacimiento());
        pres.setString(6, mem.getEstadocivil());
        pres.setDate(7, mem.getFechaconversion());
        pres.setDate(8, mem.getFechabautizo());
        pres.setString(9, mem.getTalentos());
        pres.setString(10, mem.getDones());
        pres.setString(11, mem.getActivo());
        pres.setString(12, mem.getDireccion());
        pres.setInt(13, mem.getTelefono());
        pres.setString(14, mem.getNomreferencia());
        pres.setInt(15, mem.getNumreferencia());
        pres.setInt(16, mem.getIdmembrecia());

        return pres.executeUpdate() > 0; // Retorna true si se actualizó al menos 1 fila

    } catch (Exception e) {
        e.printStackTrace(); // Imprime el error en consola
        return false;
    }
}
    public boolean eliminar(int idMem) {
    String elimiarsql = "DELETE FROM membrecia WHERE idmembrecia=?";
    
    try (Connection con = conectarMySQL.getConnection();
         PreparedStatement pres = con.prepareStatement(elimiarsql)) {
        
        pres.setInt(1, idMem);
        return pres.executeUpdate() > 0; // Retorna true si se eliminó al menos 1 fila
        
    } catch (Exception e) {
        e.printStackTrace(); // Proporciona detalles sobre la excepción
        return false; // Retorna false si ocurre un error
    }
}
    
    public DefaultTableModel buscarMiembros(String buscar){
        String [] nombbreColum={"NOMBRE","APELLIDO P.","APELLIDO M.","C.I.","FECHA NACIMIENTO","ESTADO CIVIL","FECHA CONVERSION","FECHA BAUTIZO","TALENTOS","DONES","ACTIVO","DIRECCION","TELEFONO","NOMBRE REFERENCIA","NUMERO REFERENCIA"};
        String [] registros=new String[14];
        DefaultTableModel tablabuscar=new DefaultTableModel(null, nombbreColum);
        
        String buscarsql="select * from membrecia where numdocumento like'%"+buscar+"%' or nombre like'%"+buscar+"%' or apellidop like'%"+buscar+"%' or apellidom like'%"+buscar+"%' or estadocivil like'%"+buscar+"%'";
        
        try {
           con=conectarMySQL.conectar;
           pres=con.prepareStatement(buscarsql);
           rs=pres.executeQuery();
           
           while(rs.next()){
               registros[0]=rs.getString("nombre");
               registros[1]=rs.getString("apellidop");
               registros[2]=rs.getString("apellidom");
               registros[3]=rs.getString("numdocumento");
               registros[4]=rs.getString("fechanacimiento");
               registros[5]=rs.getString("estadocivil");
               registros[6]=rs.getString("fechaconversion");
               registros[7]=rs.getString("fechabautizo");
               registros[8]=rs.getString("talentos");
               registros[9]=rs.getString("dones");
               registros[10]=rs.getString("activo");
               registros[11]=rs.getString("direccion");
               registros[12]=rs.getString("telefono");
               registros[13]=rs.getString("nomreferencia");
               registros[14]=rs.getString("numreferencia");
               
               tablabuscar.addRow(registros);
           }
        } catch (Exception e) {
            System.out.println(e);
        }
        return tablabuscar;
    }
    
    //////BUSCAR MEMBRECIA
    public List<Membrecia> buscarMembrecia(String texto) {
        List<Membrecia> lista = new ArrayList<>();

        // Consulta que busca por número de documento o por nombre completo
        String sql = "SELECT * FROM membrecia WHERE " +
                     "numdocumento LIKE ? OR " +
                     "CONCAT(nombre, ' ', apellidop, ' ', apellidom) LIKE ?";

        try (Connection con = conectarMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + texto + "%");
            ps.setString(2, "%" + texto + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Membrecia mem = new Membrecia();
                mem.setIdmembrecia(rs.getInt(1));
                mem.setNombre(rs.getString(2));
                mem.setApellidop(rs.getString(3));
                mem.setApellidom(rs.getString(4));
                mem.setNumdocumento(rs.getString(5));
                mem.setFechanacimiento(rs.getDate(6));
                mem.setEstadocivil(rs.getString(7));
                mem.setFechaconversion(rs.getDate(8));
                mem.setFechabautizo(rs.getDate(9));
                mem.setTalentos(rs.getString(10));
                mem.setDones(rs.getString(11));
                mem.setActivo(rs.getString(12));
                mem.setDireccion(rs.getString(13));
                mem.setTelefono(rs.getInt(14));
                mem.setNomreferencia(rs.getString(15));
                mem.setNumreferencia(rs.getInt(16));

                lista.add(mem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    public List<Membrecia> buscarmiembros(String texto) {
        List<Membrecia> lista = new ArrayList<>();

        String sql = "SELECT * FROM membrecia WHERE " +
                     "LOWER(numdocumento) LIKE ? OR " +
                     "LOWER(nombre) LIKE ? OR " +
                     "LOWER(apellidop) LIKE ? OR " +
                     "LOWER(apellidom) LIKE ? OR " +
                     "LOWER(CONCAT(nombre, ' ', apellidop, ' ', apellidom)) LIKE ?";

        try (Connection con = conectarMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String filtro = "%" + texto.toLowerCase().trim() + "%";
            for (int i = 1; i <= 5; i++) {
                ps.setString(i, filtro);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Membrecia mem = new Membrecia();
                mem.setIdmembrecia(rs.getInt(1));
                mem.setNombre(rs.getString(2));
                mem.setApellidop(rs.getString(3));
                mem.setApellidom(rs.getString(4));
                mem.setNumdocumento(rs.getString(5));
                mem.setFechanacimiento(rs.getDate(6));
                mem.setEstadocivil(rs.getString(7));
                mem.setFechaconversion(rs.getDate(8));
                mem.setFechabautizo(rs.getDate(9));
                mem.setTalentos(rs.getString(10));
                mem.setDones(rs.getString(11));
                mem.setActivo(rs.getString(12));
                mem.setDireccion(rs.getString(13));
                mem.setTelefono(rs.getInt(14));
                mem.setNomreferencia(rs.getString(15));
                mem.setNumreferencia(rs.getInt(16));

                lista.add(mem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    
    public List<Membrecia> buscarCumpleanierosPorRango(Date fechaDesde, Date fechaHasta) {
        List<Membrecia> lista = new ArrayList<>();

        // Convertimos a LocalDate correctamente
        LocalDate inicio = fechaDesde.toLocalDate();
        LocalDate fin = fechaHasta.toLocalDate();

        // Consulta SQL que compara día y mes
        String sql = "SELECT * FROM membrecia WHERE " +
                     "DATE_FORMAT(fechanacimiento, '%m-%d') BETWEEN ? AND ?";

        try (Connection con = conectarMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Formato MM-dd para comparar solo día y mes
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
            ps.setString(1, formatter.format(inicio));
            ps.setString(2, formatter.format(fin));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Membrecia mem = new Membrecia();
                mem.setIdmembrecia(rs.getInt(1));
                mem.setNombre(rs.getString(2));
                mem.setApellidop(rs.getString(3));
                mem.setApellidom(rs.getString(4));
                mem.setNumdocumento(rs.getString(5));
                mem.setFechanacimiento(rs.getDate(6));
                mem.setEstadocivil(rs.getString(7));
                mem.setFechaconversion(rs.getDate(8));
                mem.setFechabautizo(rs.getDate(9));
                mem.setTalentos(rs.getString(10));
                mem.setDones(rs.getString(11));
                mem.setActivo(rs.getString(12));
                mem.setDireccion(rs.getString(13));
                mem.setTelefono(rs.getInt(14));
                mem.setNomreferencia(rs.getString(15));
                mem.setNumreferencia(rs.getInt(16));

                lista.add(mem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public boolean existeDocumento(String numDocumento) {
        String sql = "SELECT COUNT(*) FROM membrecia WHERE numdocumento = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, numDocumento);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Membrecia> buscarPorEstadoCivil(String estadoCivil) {
        List<Membrecia> lista = new ArrayList<>();
        String sql = "SELECT * FROM membrecia WHERE estadocivil = ?";

        try (Connection con = conectarMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estadoCivil);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Membrecia mem = new Membrecia();
                mem.setIdmembrecia(rs.getInt(1));
                mem.setNombre(rs.getString(2));
                mem.setApellidop(rs.getString(3));
                mem.setApellidom(rs.getString(4));
                mem.setNumdocumento(rs.getString(5));
                mem.setFechanacimiento(rs.getDate(6));
                mem.setEstadocivil(rs.getString(7));
                mem.setFechaconversion(rs.getDate(8));
                mem.setFechabautizo(rs.getDate(9));
                mem.setTalentos(rs.getString(10));
                mem.setDones(rs.getString(11));
                mem.setActivo(rs.getString(12));
                mem.setDireccion(rs.getString(13));
                mem.setTelefono(rs.getInt(14));
                mem.setNomreferencia(rs.getString(15));
                mem.setNumreferencia(rs.getInt(16));

                lista.add(mem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    public List<Membrecia> buscarPorDones(String dones) {
        List<Membrecia> lista = new ArrayList<>();
        String sql = "SELECT * FROM membrecia WHERE dones = ?";

        try (Connection con = conectarMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dones);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Membrecia mem = new Membrecia();
                mem.setIdmembrecia(rs.getInt(1));
                mem.setNombre(rs.getString(2));
                mem.setApellidop(rs.getString(3));
                mem.setApellidom(rs.getString(4));
                mem.setNumdocumento(rs.getString(5));
                mem.setFechanacimiento(rs.getDate(6));
                mem.setEstadocivil(rs.getString(7));
                mem.setFechaconversion(rs.getDate(8));
                mem.setFechabautizo(rs.getDate(9));
                mem.setTalentos(rs.getString(10));
                mem.setDones(rs.getString(11));
                mem.setActivo(rs.getString(12));
                mem.setDireccion(rs.getString(13));
                mem.setTelefono(rs.getInt(14));
                mem.setNomreferencia(rs.getString(15));
                mem.setNumreferencia(rs.getInt(16));
                lista.add(mem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    public List<Membrecia> buscarPorTalentos(String talentos) {
        List<Membrecia> lista = new ArrayList<>();
        String sql = "SELECT * FROM membrecia WHERE talentos = ?";

        try (Connection con = conectarMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, talentos);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Membrecia mem = new Membrecia();
                mem.setIdmembrecia(rs.getInt(1));
                mem.setNombre(rs.getString(2));
                mem.setApellidop(rs.getString(3));
                mem.setApellidom(rs.getString(4));
                mem.setNumdocumento(rs.getString(5));
                mem.setFechanacimiento(rs.getDate(6));
                mem.setEstadocivil(rs.getString(7));
                mem.setFechaconversion(rs.getDate(8));
                mem.setFechabautizo(rs.getDate(9));
                mem.setTalentos(rs.getString(10));
                mem.setDones(rs.getString(11));
                mem.setActivo(rs.getString(12));
                mem.setDireccion(rs.getString(13));
                mem.setTelefono(rs.getInt(14));
                mem.setNomreferencia(rs.getString(15));
                mem.setNumreferencia(rs.getInt(16));
                lista.add(mem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    public List<Membrecia> buscarPorActivo(String activo) {
        List<Membrecia> lista = new ArrayList<>();
        String sql = "SELECT * FROM membrecia WHERE activo = ?";

        try (Connection con = conectarMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, activo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Membrecia mem = new Membrecia();
                mem.setIdmembrecia(rs.getInt(1));
                mem.setNombre(rs.getString(2));
                mem.setApellidop(rs.getString(3));
                mem.setApellidom(rs.getString(4));
                mem.setNumdocumento(rs.getString(5));
                mem.setFechanacimiento(rs.getDate(6));
                mem.setEstadocivil(rs.getString(7));
                mem.setFechaconversion(rs.getDate(8));
                mem.setFechabautizo(rs.getDate(9));
                mem.setTalentos(rs.getString(10));
                mem.setDones(rs.getString(11));
                mem.setActivo(rs.getString(12));
                mem.setDireccion(rs.getString(13));
                mem.setTelefono(rs.getInt(14));
                mem.setNomreferencia(rs.getString(15));
                mem.setNumreferencia(rs.getInt(16));
                lista.add(mem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    public List<Membrecia> buscarPorFechaNacimiento(Date desde, Date hasta) {
        List<Membrecia> lista = new ArrayList<>();
        String sql = "SELECT * FROM membrecia WHERE fechanacimiento BETWEEN ? AND ?";

        try (Connection con = conectarMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(desde.getTime()));
            ps.setDate(2, new java.sql.Date(hasta.getTime()));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Membrecia mem = new Membrecia();
                mem.setIdmembrecia(rs.getInt(1));
                mem.setNombre(rs.getString(2));
                mem.setApellidop(rs.getString(3));
                mem.setApellidom(rs.getString(4));
                mem.setNumdocumento(rs.getString(5));
                mem.setFechanacimiento(rs.getDate(6));
                mem.setEstadocivil(rs.getString(7));
                mem.setFechaconversion(rs.getDate(8));
                mem.setFechabautizo(rs.getDate(9));
                mem.setTalentos(rs.getString(10));
                mem.setDones(rs.getString(11));
                mem.setActivo(rs.getString(12));
                mem.setDireccion(rs.getString(13));
                mem.setTelefono(rs.getInt(14));
                mem.setNomreferencia(rs.getString(15));
                mem.setNumreferencia(rs.getInt(16));

                lista.add(mem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    public List<Membrecia> buscarPorFechaBautizo(Date desde, Date hasta) {
        List<Membrecia> lista = new ArrayList<>();
        String sql = "SELECT * FROM membrecia WHERE fechabautizo BETWEEN ? AND ?";

        try (Connection con = conectarMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(desde.getTime()));
            ps.setDate(2, new java.sql.Date(hasta.getTime()));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Membrecia mem = new Membrecia();
                mem.setIdmembrecia(rs.getInt(1));
                mem.setNombre(rs.getString(2));
                mem.setApellidop(rs.getString(3));
                mem.setApellidom(rs.getString(4));
                mem.setNumdocumento(rs.getString(5));
                mem.setFechanacimiento(rs.getDate(6));
                mem.setEstadocivil(rs.getString(7));
                mem.setFechaconversion(rs.getDate(8));
                mem.setFechabautizo(rs.getDate(9));
                mem.setTalentos(rs.getString(10));
                mem.setDones(rs.getString(11));
                mem.setActivo(rs.getString(12));
                mem.setDireccion(rs.getString(13));
                mem.setTelefono(rs.getInt(14));
                mem.setNomreferencia(rs.getString(15));
                mem.setNumreferencia(rs.getInt(16));

                lista.add(mem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    
   
}
