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
            
            //System.out.println(rs.next()+"condiciones");
            while(rs.next()){
                Lideriglesia li=new Lideriglesia();
                System.out.println(rs.getInt(1)+"id ");
                
                li.setIdlider(rs.getInt(1));
                //System.out.println(rs.getInt(2)+"idmem ");
                li.setIdmembrecia(rs.getInt(2));
                li.setNombre(rs.getString(3));
                li.setApellidos(rs.getString(4));
                li.setCi(rs.getString(5));
                li.setCargo(rs.getString(6));
                li.setIniciogestion(rs.getDate(7));
                li.setFingestion(rs.getDate(8));
                
                //System.out.println(li + "intentando ver");
                
                lid.add(li);
            }
            
        } catch (Exception e) {
        }
        System.out.println(lid + "sacando lider");
        return lid;
    }
    public boolean agregar(Lideriglesia lid){
        int resp=0;
        String agregarsql="insert into lider(idmembrecia,nombre,apellidos,ci,cargo,iniciogestion,fingestion)"
                + "values(?,?,?,?,?,?,?)";
        
        try {
            pres=con.prepareStatement(agregarsql);
            
            System.out.println(lid.getIdmembrecia()+"id meme");
            pres.setInt(1, lid.getIdmembrecia());
            pres.setString(2, lid.getNombre());
            pres.setString(3, lid.getApellidos());
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
        String modificarsql="update lider set nombre=?,apellidos=?,ci=?,cargo=?,iniciogestion=?,fingestion=?"
                +"where idlider=?";
        try {
            con=consql.conectando();
            pres=con.prepareStatement(modificarsql);
            
            pres.setString(1, lid.getNombre());
            pres.setString(2, lid.getApellidos());
            pres.setString(3, lid.getCi());
            pres.setString(4, lid.getCargo());
            pres.setDate(5, lid.getIniciogestion());
            pres.setDate(6, lid.getFingestion());
            
            pres.setInt(7, lid.getIdlider());
            
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
    public DefaultTableModel buscarlider(String buscar){
        String [] nombreColum={"NOMBRE","APELLIDOS","C.I.","CARGO","INICIO GESTION","FIN GESTION"};
        String [] registros=new String[6];
        DefaultTableModel tablasearh=new DefaultTableModel(null, nombreColum);
        
        String buscarsql="select * from lider where nombre like'%"+buscar+"%' or apellidos like'%"+buscar+"%' or ci like'%"+buscar+"%' or cargo like'%"+buscar+"%' or iniciogestion like'%"+buscar+"%' or fingestion like'%"+buscar+"%'";
        
        try {
            con=consql.conectando();
            pres=con.prepareStatement(buscarsql);
            rs=pres.executeQuery();
            
            while(rs.next()){
                registros[0]=rs.getString("nombre");
                registros[1]=rs.getString("apellidos");
                registros[2]=rs.getString("ci");
                registros[3]=rs.getString("cargo");
                registros[4]=rs.getString("iniciogestion");
                registros[5]=rs.getString("fingestion");
                
                tablasearh.addRow(registros);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return tablasearh;
    }
}

