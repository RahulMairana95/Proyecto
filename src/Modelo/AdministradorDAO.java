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
    public List listaradmin(){
        List<Administrador> listaAD=new ArrayList<>();
        String consulta="select * from administrador";
        try {
            con=conectar.getConnection();
            pres=con.prepareStatement(consulta);
            rs=pres.executeQuery();
            while(rs.next()){
                Administrador ad=new Administrador();
                ad.setIdadmin(rs.getInt(1));
                
                ad.setIdlider(rs.getInt(2));
                ad.setNombre(rs.getString(3));
                ad.setApellidos(rs.getString(4));
                ad.setNumdocumento(rs.getString(5));
                ad.setTelefono(rs.getInt(6));
                ad.setEmail(rs.getString(7));
                ad.setUsuario(rs.getString(8));
                ad.setNombreusuario(rs.getString(9));
                ad.setContraseña(rs.getString(10));
                
                listaAD.add(ad);
            }
        } catch (Exception e) {
        }
        return listaAD;
    }
    public boolean agregar(Administrador min){
        //int resp=0;
        String agregarmsql="insert into administrador(idlider,nombre,apellidos,numdocumento,telefono,email,usuario,nombreusuario,contraseña)"
               +"values(?,?,?,?,?,?,?,?,?)";
        
        System.out.println("agregardb");
            
        try{
            pres=con.prepareStatement(agregarmsql);        
            //pres.setInt(1, men.getIdmembrecia());
            pres.setInt(1, min.getIdlider());
           pres.setString(2, min.getNombre());
            pres.setString(3,   min.getApellidos());
            pres.setString(4, min.getNumdocumento());
            pres.setInt(5, min.getTelefono());
            pres.setString(6, min.getEmail());
            pres.setString(7, min.getUsuario());
            pres.setString(8, min.getNombreusuario());
            pres.setString(9, min.getContraseña());
            
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
    public void insertarDatos(Object[] obj){
        int resp=0;
        String agregarmsql="insert into administrador(idlider,nombre,apellidos,numdocumento,telefono,email,usuario,nombreusuario,contraseña)"
               +"values(?,?,?,?,?,?,?,?,?)";
        try {
            con=conectar.getConnection();
            pres=con.prepareStatement(agregarmsql);
            pres.setObject(1, obj[0]);
            pres.setObject(2, obj[1]);
            pres.setObject(3, obj[2]);
            pres.setObject(4, obj[3]);
            pres.setObject(5, obj[4]);
            pres.setObject(6, obj[5]);
            pres.setObject(7, obj[6]);
            pres.setObject(8, obj[7]);
            pres.setObject(9, obj[8]);
            resp=pres.executeUpdate();
        } catch (Exception e) {
        }
        
    }
    public boolean editar(Administrador admini){
        int res=0;
        String editarsql="update administrador set nombre=?,apellidos=?,numdocumento=?,telefono=?,email=?,usuario=?,nombreusuario=?,contraseña=?"
                         +"where idadmin=?";
        try {
            con=conectar.getConnection();
            pres=con.prepareStatement(editarsql);
            
            pres.setString(1, admini.getNombre());
            pres.setString(2, admini.getApellidos());
            pres.setString(3, admini.getNumdocumento());
            pres.setInt(4, admini.getTelefono());
            pres.setString(5, admini.getEmail());
            pres.setString(6, admini.getUsuario());
            pres.setString(7, admini.getNombreusuario());
            pres.setString(8, admini.getContraseña());
            
            pres.setInt(9, admini.getIdadmin());
            
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
    public void eliminarcuenta(int idMem){
        int res=0;
        String elimiarsql="delete from administrador where idadmin=?";
        try {
            con=conectar.getConnection();
            pres=con.prepareStatement(elimiarsql);
            pres.setInt(1, idMem);
            
            res =pres.executeUpdate();
        } catch (Exception e) {
            
        }
    }
}
