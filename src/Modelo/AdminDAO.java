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
public class AdminDAO {
    Connection con;
    Conexion conectar = new Conexion();
    PreparedStatement pres;
    ResultSet rs;
    
    VistaRegistro vistaRegistro;
    
    public AdminDAO(){}
    
    
    public AdminDAO(VistaRegistro vr){
        //System.out.println("vistaaaaaaa");
        this.vistaRegistro=vr;
    }
    public List listaradmin(){
        List<Administrador> listaAD=new ArrayList<>();
        String consulta="select * from administrador";
        
        try {
            
            con=conectar.conectando();
            pres=con.prepareStatement(consulta);
            rs=pres.executeQuery();
            
            while(rs.next()){
                Administrador ad=new Administrador();
                ad.setIdadmin(rs.getInt(1));

                ad.setNombre(rs.getString(2));
                ad.setApaterno(rs.getString(3));
                ad.setAmaterno(rs.getString(4));
                ad.setNumdocumento(rs.getString(5));
                ad.setTelefono(rs.getInt(6));
                ad.setEmail(rs.getString(7));
                ad.setCargo(rs.getString(8));
                ad.setUsuario(rs.getString(9));
                ad.setNombreusuario(rs.getString(10));
                ad.setContraseña(rs.getString(11));

                
                
                listaAD.add(ad);
            }
        } catch (Exception e) {
        }
        return listaAD;
    }
    public boolean agregar(Administrador min){
        //int resp=0;
        String agregarmsql="insert into administrador(nombre,apaterno,apmaterno,numdocumento,telefono,email,cargo,usuario,nombreusuario,contraseña)"
               +"values(?,?,?,?,?,?,?,?,?,?)";
        
        System.out.println("agregardb");
            
        try{
            pres=con.prepareStatement(agregarmsql);        
            //pres.setInt(1, men.getIdmembrecia());
            pres.setString(1, min.getNombre());
            pres.setString(2,   min.getApaterno());
            pres.setString(3, min.getAmaterno());
            pres.setString(4, min.getNumdocumento());
            pres.setInt(5, min.getTelefono());
            pres.setString(6, min.getEmail());
            pres.setString(7, min.getCargo());
            pres.setString(8, min.getUsuario());
            pres.setString(9, min.getNombreusuario());
            pres.setString(10, min.getContraseña());
            
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
            con=conectar.conectando();
            pres=con.prepareStatement(elimiarsql);
            pres.setInt(1, idMem);
            
            res =pres.executeUpdate();
        } catch (Exception e) {
            
        }
    }
}
