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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    List<Membrecia> listamem = new ArrayList<>();
    
    Workbook wb;
    public MembreciaDAO(){
    };
    
    public MembreciaDAO(VistaMembrecia vm){
        this.vistaMembrecia=vm;
    }
    
    public List listarMembrecia(){
        //List<Membrecia> listamem = new ArrayList<>();
        String consultas="select * from membrecia";
        
        try {
            con=conectarMySQL.conectando();
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
                
               // mem.setIdmembrecia(rs.getInt(12));
                
                listamem.add(mem);
                
            }
            //System.out.println("mostrar"+ listamem);
            
        } catch (Exception e) {
        }
        return listamem;
    }
    public boolean agregar(Membrecia men){
        int resp=0;
        String agregarmsql="insert into membrecia(nombre,apellidop,apellidom,numdocumento,fechanacimiento,estadocivil,fechaconversion,fechabautizo,talentos,dones,activo)"
                            +"values(?,?,?,?,?,?,?,?,?,?,?)";
        System.out.println("agregardb");
            
        try{
            pres=con.prepareStatement(agregarmsql);
            
            //pres.setInt(1, men.getIdmembrecia());
            pres.setString(1, men.getNombre());
            pres.setString(2,   men.getApellidop());
            pres.setString(3, men.getApellidom());
            pres.setString(4, men.getNumdocumento());
            pres.setDate(5, men.getFechanacimiento());
            pres.setString(6, men.getEstadocivil());
            pres.setDate(7, men.getFechaconversion());
            pres.setDate(8, men.getFechabautizo());
            pres.setString(9, men.getTalentos());
            pres.setString(10, men.getDones());
            pres.setString(11, men.getActivo());
            
            int n = pres.executeUpdate();

            if (n != 0) {
                
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    public boolean modificar(Membrecia mem){
        int res=0;
        String modificarsql="update membrecia set nombre=?,apellidop=?,apellidom=?,numdocumento=?,fechanacimiento=?,estadocivil=?,fechaconversion=?,fechabautizo=?,talentos=?,dones=?,activo=?"
                            +"where idmembrecia=?";
        System.out.println("modificando");

        try {
            con=conectarMySQL.conectando();
            pres=con.prepareStatement(modificarsql);
            
            pres.setString(1, mem.getNombre());
            pres.setString(2,   mem.getApellidop());
            pres.setString(3, mem.getApellidom());
            pres.setString(4, mem.getNumdocumento());
            pres.setDate(5, mem.getFechanacimiento());
            pres.setString(6, mem.getEstadocivil());
            pres.setDate(7, mem.getFechaconversion());
            pres.setDate(8, mem.getFechabautizo());
            pres.setString(9, mem.getTalentos());
            pres.setString(10, mem.getDones());
            pres.setString(11, mem.getActivo());
            
            pres.setInt(12, mem.getIdmembrecia());
            
            int n = pres.executeUpdate();

            if (n != 0) {
                
                return true;
            }else{
                return false;
            }
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
        
    }
    public void eliminarmiembros(int idMem){
        int res=0;
        String elimiarsql="delete from membrecia where idmembrecia=?";
        try {
            con=conectarMySQL.conectando();
            pres=con.prepareStatement(elimiarsql);
            pres.setInt(1, idMem);
            
            res =pres.executeUpdate();
        } catch (Exception e) {
            
        }
    }
    
    public DefaultTableModel buscarMiembros(String buscar){
        String [] nombbreColum={"nombre","apellidop","apellidom","numdocumento","fechanacimiento","estadocivil","fechaconversion","fechabautizo","talentos","dones","activo"};
        String [] registros=new String[11];
        DefaultTableModel tablabuscar=new DefaultTableModel(null, nombbreColum);
        
        String buscarsql="select * from membrecia where numdocumento like'%"+buscar+"%' or nombre like'%"+buscar+"%' or apellidop like'%"+buscar+"%'";
        
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
               
               tablabuscar.addRow(registros);
           }
        } catch (Exception e) {
            System.out.println(e);
        }
        return tablabuscar;
    }
   
}
