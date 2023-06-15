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
import javax.swing.table.DefaultTableModel;

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
        /*String ofrendaSQL="select d.idofrenda, d.idlider, \n" +
                    "(SELECT nombre FROM lider l INNER JOIN ofrenda z ON l.idlider=z.idlider WHERE d.idlider=z.idlider)as tesorn, \n" +
                    "(SELECT apaterno FROM lider l INNER JOIN ofrenda z on l.idlider=z.idlider WHERE d.idlider=z.idlider)as tesorap, \n" +
                    "d.carnet,d.mes,d.entrada,d.salida,d.saldoanterior, d.saldoactual,d.descripcion,d.fecharegistro from ofrenda d";
        System.out.println("ofrendando");*/
        String ofrendaSQL="select * from ofrenda";
        try {
            con=conectarMySQL.conectando();
            pres=con.prepareStatement(ofrendaSQL);
            rs=pres.executeQuery();
            
            
            
            while(rs.next()){
                
                Ofrenda diez=new Ofrenda();
                
                diez.setIdofrenda(rs.getInt(1));
                diez.setIdlider(rs.getInt(2));
                diez.setTesorero(rs.getString(3));
                diez.setCarnet(rs.getString(4));
                diez.setMes(rs.getString(5));
                diez.setEntrada(rs.getDouble(6));
                diez.setSalida(rs.getDouble(7));
                diez.setSaldoanterior(rs.getDouble(8));
                diez.setSaldoactual(rs.getDouble(9));
                diez.setDescripcion(rs.getString(10));
                diez.setFecharegistro(rs.getDate(11));
                
                listaofrenda.add(diez);
                
            }
            System.out.println("ofrendado");
        } catch (Exception e) {
        }
        return listaofrenda;
    }
    public boolean añadir(Ofrenda of){
        int res=0;
        String añadirsql="insert into ofrenda(idlider,tesorero,carnet,mes,entrada,salida,saldoanterior,saldoactual,descripcion,fecharegistro)"
                         + "values(?,?,?,?,?,?,?,?,?,?)";
        try {
            pres=con.prepareStatement(añadirsql);
            
            pres.setInt(1, of.getIdlider());
            pres.setString(2, of.getTesorero());
            pres.setString(3, of.getCarnet());
            pres.setString(4, of.getMes());
            pres.setDouble(5, of.getEntrada());
            pres.setDouble(6, of.getSalida());
            pres.setDouble(7, of.getSaldoanterior());
            pres.setDouble(8, of.getSaldoactual());
            pres.setString(9, of.getDescripcion());
            pres.setDate(10, of.getFecharegistro());
            
            int n=pres.executeUpdate();
            if (n != 0) {
                
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
    public boolean modificar(Ofrenda of){
        int res=0;
        String modificarsql="update ofrenda set idlider=?,tesorero=?,carnet=?,mes=?,entrada=?,salida=?,saldoanterior=?,saldoactual=?,descripcion=?,fecharegistro=?"
                            +"where idofrenda=?";
        try {
            con=conectarMySQL.conectando();
            pres=con.prepareStatement(modificarsql);
            
            pres.setInt(1, of.getIdlider());
            pres.setString(2, of.getTesorero());
            pres.setString(3, of.getCarnet());
            pres.setString(4, of.getMes());
            pres.setDouble(5, of.getEntrada());
            pres.setDouble(6, of.getSalida());
            pres.setDouble(7, of.getSaldoanterior());
            pres.setDouble(8, of.getSaldoactual());
            pres.setString(9, of.getDescripcion());
            pres.setDate(10, of.getFecharegistro());
            
            pres.setInt(11, of.getIdofrenda());
            
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
    public DefaultTableModel buscarOfrenda(String buscar){
        String [] nombreColum={"TESORERO","CARNET","MES","ENTRADA","SALIDA","SALDO ANTERIOR","SALDO ACTUAL","DESCRIPCION","FECHA DE REGISTRO"};
        String [] registros=new String[9];
        DefaultTableModel tablesearch= new DefaultTableModel(null, nombreColum);
        
        String buscarsql="select * from ofrenda where carnet like'%"+buscar+"%' or mes like'%"+buscar+"%' or fecharegistro like'%"+buscar+"%' or idlider like'%"+buscar+"%'";
        
        try {
            con=conectarMySQL.conectando();
            pres=con.prepareStatement(buscarsql);
            rs=pres.executeQuery();
            
            while(rs.next()){
                registros[0]=rs.getString("tesorero");
                registros[1]=rs.getString("carnet");
                registros[2]=rs.getString("mes");
                registros[3]=rs.getString("entrada");
                registros[4]=rs.getString("salida");
                registros[5]=rs.getString("saldoanterior");
                registros[6]=rs.getString("saldoactual");
                registros[7]=rs.getString("descripcion");
                registros[8]=rs.getString("fecharegistro");
                
                tablesearch.addRow(registros);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    
        return tablesearch;
    }
}
