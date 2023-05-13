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
public class OfrendaDAO {
    Connection con;
    Conexion conectarMySQL=new Conexion();
    PreparedStatement pres;
    ResultSet rs;
    
    VistaOfrenda vistaOfrenda;
    
    public OfrendaDAO(){
    };
    
    public OfrendaDAO(VistaOfrenda vo){
        this.vistaOfrenda=vo;
    }
    
    public List listarOfrenda(){
        List<Ofrenda> listaofrenda=new ArrayList<>();
        String ofrendaSQL="select d.idofrenda, d.idlider, \n" +
                    "(SELECT nombre FROM lider l INNER JOIN ofrenda z ON l.idlider=z.idlider WHERE d.idlider=z.idlider)as tesorn, \n" +
                    "(SELECT apaterno FROM lider l INNER JOIN ofrenda z on l.idlider=z.idlider WHERE d.idlider=z.idlider)as tesorap, \n" +
                    "d.carnet,d.mes,d.entrada,d.salida,d.saldoanterior, d.saldoactual,d.descripcion,d.fecharegistro from ofrenda d";
        System.out.println("ofrendando");
        try {
            con=conectarMySQL.conectando();
            pres=con.prepareStatement(ofrendaSQL);
            rs=pres.executeQuery();
            
            
            
            while(rs.next()){
                
                Ofrenda diez=new Ofrenda();
                
                diez.setIdofrenda(rs.getInt(1));
                diez.setIdlider(rs.getInt(2));
                diez.setTesorn(rs.getString(3));
                diez.setTesorap(rs.getString(4));
                diez.setCarnet(rs.getString(5));
                diez.setMes(rs.getString(6));
                diez.setEntrada(rs.getDouble(7));
                diez.setSalida(rs.getDouble(8));
                diez.setSaldoanterior(rs.getDouble(9));
                diez.setSaldoactual(rs.getDouble(10));
                diez.setDescripcion(rs.getString(11));
                diez.setFecharegistro(rs.getDate(12));
                
                listaofrenda.add(diez);
                
            }
            System.out.println("ofrendado");
        } catch (Exception e) {
        }
        return listaofrenda;
    }
    public boolean agregar(Ofrenda di){
        int res=0;
        String agregarsql="insert into ofrenda(idlider,carnet,mes,entrada,salida,saldoanterior,saldoactual,descripcion,fecharegistro)"
                +"values(?,?,?,?,?,?,?,?,?)";
        
        try {
            pres=con.prepareStatement(agregarsql);
            
            pres.setInt(1, di.getIdlider());
            pres.setString(2, di.getCarnet());
            pres.setString(3, di.getMes());
            pres.setDouble(4, di.getEntrada());
            pres.setDouble(5, di.getSalida());
            pres.setDouble(6, di.getSaldoanterior());
            pres.setDouble(7, di.getSaldoactual());
            pres.setString(8, di.getDescripcion());
            pres.setDate(9, di.getFecharegistro());
            
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
    public boolean modificar(Ofrenda dm){
        int res=0;
        String modificarsql="update ofrenda set idlider=?,carnet=?,mes=?,entrada=?,salida=?,saldoanterior=?,saldoactual=?,descripcion=?,fecharegistro=?"
                            +"where idofrenda=?";
        try {
            con=conectarMySQL.conectando();
            pres=con.prepareStatement(modificarsql);
            
            pres.setInt(1, dm.getIdlider());
            pres.setString(2, dm.getCarnet());
            pres.setString(3, dm.getMes());
            pres.setDouble(4, dm.getEntrada());
            pres.setDouble(5, dm.getSalida());
            pres.setDouble(6, dm.getSaldoanterior());
            pres.setDouble(7, dm.getSaldoactual());
            pres.setString(8, dm.getDescripcion());
            pres.setDate(9, dm.getFecharegistro());
            
            pres.setInt(10, dm.getIdofrenda());
            
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
    public void eliminarOfrenda(int idLid){
        int res=0;
        String eliminarsql="delete from ofrenda where idofrenda=?";
        try {
            con=conectarMySQL.conectando();
            pres=con.prepareStatement(eliminarsql);
            pres.setInt(1, idLid);
            
            res=pres.executeUpdate();
        } catch (Exception e) {
        }
    }
}
