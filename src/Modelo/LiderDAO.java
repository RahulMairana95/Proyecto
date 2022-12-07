/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

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
    
    public List mostrar(){
        List<Lideriglesia> lid=new ArrayList<>();
        String listarsql="select * from lider";
        
        try {
            con=consql.conectando();
            pres=con.prepareStatement(listarsql);
            rs=pres.executeQuery();
            
            while(rs.next()){
                Lideriglesia li=new Lideriglesia();
                li.setIdlider(rs.getInt(1));
                li.setIdmembrecia(rs.getInt(2));
                li.setNombre(rs.getString(3));
                li.setApaterno(rs.getString(4));
                li.setAmaterno(rs.getString(5));
                li.setCi(rs.getString(6));
                li.setCargo(rs.getString(7));
                li.setIniciogestion(rs.getDate(8));
                li.setFingestion(rs.getDate(9));
                
                
                
                lid.add(li);
            }
        } catch (Exception e) {
        }
        return lid;
    }
    public boolean agregar(Lideriglesia lid){
        int resp=0;
        String agregarsql="insert into lider(nombre,apaterno,amaterno,ci,cargo,iniciogestion,fingestion)"
                + "values(?,?,?,?,?,?,?)";
        
        try {
            pres=con.prepareStatement(agregarsql);
            
            pres.setString(1, lid.getNombre());
            pres.setString(2, lid.getApaterno());
            pres.setString(3, lid.getAmaterno());
            pres.setString(4, lid.getCi());
            pres.setString(5, lid.getCargo());
            pres.setDate(6, lid.getIniciogestion());
            pres.setDate(7, lid.getFingestion());
            
            int n=pres.executeUpdate();
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
    public boolean  modificar(Lideriglesia lid){
        int res=0;
        String modificarsql="update lider set nombre=?,apaterno=?,amaterno=?,ci=?,cargo=?,iniciogestion=?,fingestion=?"
                +"where idlider=?";
        try {
            con=consql.conectando();
            pres=con.prepareStatement(modificarsql);
            
            pres.setString(1, lid.getNombre());
            pres.setString(2, lid.getApaterno());
            pres.setString(3, lid.getAmaterno());
            pres.setString(4, lid.getCi());
            pres.setString(5, lid.getCargo());
            pres.setDate(6, lid.getIniciogestion());
            pres.setDate(7, lid.getFingestion());
            
            pres.setInt(8, lid.getIdlider());
            
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
    public void eliminarlider(int idLid){
        int res=0;
        String eliminarsql="delete from lider where idlider=?";
        try {
            con=consql.conectando();
            pres=con.prepareStatement(eliminarsql);
            pres.setInt(1, idLid);
            
            res=pres.executeUpdate();
        } catch (Exception e) {
        }
    }
}

