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
public class DiezmoDAO {
    Connection con;
    Conexion conectarMySQL=new Conexion();
    PreparedStatement pres;
    ResultSet rs;
    
    VistaDiezmo vistaDiezmo;
    
    public DiezmoDAO(){
    };
    
    public DiezmoDAO(VistaDiezmo ve){
        this.vistaDiezmo=ve;
    }
    
    public List listarDiezmo(){
        List<Diezmo> listadiezmo=new ArrayList<>();
        /*String diezmoSQL="select d.iddiezmo, d.idlider, \n" +
                    "(SELECT nombre FROM lider l INNER JOIN diezmo z ON l.idlider=z.idlider WHERE d.idlider=z.idlider)as tesorn, \n" +
                    "(SELECT apaterno FROM lider l INNER JOIN diezmo z on l.idlider=z.idlider WHERE d.idlider=z.idlider)as tesorap, \n" +
                    "d.carnet,d.mes,d.entrada,d.salida,d.saldoanterior, d.saldoactual,d.descripcion,d.fecharegistro from diezmo d";
        System.out.println("diezmando");  */
        String diezmoSQL="select * from diezmo";
        try {
            con=conectarMySQL.conectando();
            pres=con.prepareStatement(diezmoSQL);
            rs=pres.executeQuery();
            
            
            
            while(rs.next()){
                
                Diezmo diez=new Diezmo();
                
                diez.setIddiezmo(rs.getInt(1));
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
                
                listadiezmo.add(diez);
                
            }
            System.out.println("diezmandooooooooooooooooooo");
        } catch (Exception e) {
        }
        return listadiezmo;
    }
    public boolean agregar(Diezmo di){
        int res=0;
        String agregarsql="insert into diezmo(idlider,tesorero,carnet,mes,entrada,salida,saldoanterior,saldoactual,descripcion,fecharegistro)"
                +"values(?,?,?,?,?,?,?,?,?,?)";
        
        try {
            pres=con.prepareStatement(agregarsql);
            
            pres.setInt(1, di.getIdlider());
            pres.setString(2, di.getTesorero());
            pres.setString(3, di.getCarnet());
            pres.setString(4, di.getMes());
            pres.setDouble(5, di.getEntrada());
            pres.setDouble(6, di.getSalida());
            pres.setDouble(7, di.getSaldoanterior());
            pres.setDouble(8, di.getSaldoactual());
            pres.setString(9, di.getDescripcion());
            pres.setDate(10, di.getFecharegistro());
            
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
    public boolean modificar(Diezmo dm){
        String modificarsql="update diezmo set tesorero=?,carnet=?,mes=?,entrada=?,salida=?,saldoanterior=?,saldoactual=?,descripcion=?,fecharegistro=? where iddiezmo=?";
        try {
            con=conectarMySQL.conectando();
            pres=con.prepareStatement(modificarsql);
            
            //pres.setInt(1, dm.getIdlider());
            pres.setString(1, dm.getTesorero());
            pres.setString(2, dm.getCarnet());
            pres.setString(3, dm.getMes());
            pres.setDouble(4, dm.getEntrada());
            pres.setDouble(5, dm.getSalida());
            pres.setDouble(6, dm.getSaldoanterior());
            pres.setDouble(7, dm.getSaldoactual());
            pres.setString(8, dm.getDescripcion());
            pres.setDate(9, dm.getFecharegistro());
            
            pres.setInt(10, dm.getIddiezmo());
            
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
    public void eliminardiezmo(int idLid){
        int res=0;
        String eliminarsql="delete from diezmo where iddiezmo=?";
        try {
            con=conectarMySQL.conectando();
            pres=con.prepareStatement(eliminarsql);
            pres.setInt(1, idLid);
            
            res=pres.executeUpdate();
        } catch (Exception e) {
        }
    }
    public DefaultTableModel buscarDiezmo(String buscar){
        String [] nombreColum={"TESORERO","CARNET","MES","ENTRADA","SALIDA","SALDO ANTERIOR","SALDO ACTUAL","DESCRIPCION","FECHA DE REGISTRO"};
        String [] registros=new String[9];
        DefaultTableModel tablesearch= new DefaultTableModel(null, nombreColum);
        
        String buscarsql="select * from diezmo where carnet like'%"+buscar+"%' or mes like'%"+buscar+"%' or fecharegistro like'%"+buscar+"%' or idlider like'%"+buscar+"%'";
        
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
