/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Vista.*;
import java.sql.Connection;

/**
 *
 * @author RAHUL
 */
public class ValidarAdmin {
    PreparedStatement pres;
    ResultSet result;
    
    Conexion conexion=new Conexion();
    Connection con;
    
    Administrador admin= new Administrador();
    Usuario usuario;
    
    public ValidarAdmin(Usuario usuario){
        this.usuario=this.usuario;
        
        System.out.println("llega");
    }
    
    public Administrador ValidarAdmin(String nombreusuario, String contraseña){
        Administrador administrador= new Administrador();
        String consultasql="SELECT * FROM administrador WHERE nombreusuario=? AND contraseña=?";
        
        System.out.println("consulta");
        
        //System.out.println(administrador.getCargo());
        try {
            con=conexion.conectando();
            pres=con.prepareStatement(consultasql);
            pres.setString(1, nombreusuario);
            pres.setString(2, contraseña);
            result=pres.executeQuery();
            
            while(result.next()){
                administrador.setIdadmin(result.getInt(1));
                administrador.setNombre(result.getString(2));
                administrador.setApaterno(result.getString(3));
                administrador.setAmaterno(result.getString(4));
                administrador.setNumdocumento(result.getString(5));
                administrador.setTelefono(result.getInt(6));
                administrador.setEmail(result.getString(7));
                administrador.setCargo(result.getString(8));
                administrador.setUsuario(result.getString(9));
                administrador.setNombreusuario(result.getString(10));
                administrador.setContraseña(result.getString(11));
            }
            System.out.println("allaaa");
        } catch (Exception e) {
            System.out.println("error"+e);
        }
        return administrador;
        
    }
}
