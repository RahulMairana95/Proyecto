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
 * @author LENOVO
 */
public class MinDAO {
    Connection con;
    Conexion consql=new Conexion();
    PreparedStatement pres;
    ResultSet rs;
    
    VistaLiderMin liderMin;
    public MinDAO(){}
    
    public MinDAO(VistaLiderMin min){
        this.liderMin=min;
    }

    
    
    public List mostrar(){
        List<Minis> min=new ArrayList<>();
        String listarsql="select * from ministerio";
        
        try {
            con=consql.conectando();
            pres=con.prepareStatement(listarsql);
            rs=pres.executeQuery();
            
            //System.out.println(rs.next()+"condiciones");
            while(rs.next()){
                Minis li=new Minis();
               // System.out.println(rs.getInt(1)+"id ");
                
                li.setIdmin(rs.getInt(1));
                //System.out.println(rs.getInt(2)+"idmem ");
                li.setIdmembrecia(rs.getInt(2));
                li.setNombre(rs.getString(3));
                li.setApellidos(rs.getString(4));
                li.setCi(rs.getString(5));
                li.setMinisterio(rs.getString(6));
                li.setCargo(rs.getString(7));
                li.setIniciogestion(rs.getDate(8));
                li.setFingestion(rs.getDate(9));
                
                //System.out.println(li + "intentando ver");
                
                min.add(li);
            }
            
        } catch (Exception e) {
        }
        //System.out.println(lid + "sacando lider");
        return min;
    }
    public boolean agregar(Minis lid){
        int resp=0;
        String agregarsql="insert into ministerio(idmembrecia,nombre,apellidos,ci,ministerio,cargo,iniciogestion,fingestion)"
                + "values(?,?,?,?,?,?,?,?)";
        
        try {
            pres=con.prepareStatement(agregarsql);
            
            //System.out.println(lid.getIdmembrecia()+"id meme");
            pres.setInt(1, lid.getIdmembrecia());
            pres.setString(2, lid.getNombre());
            pres.setString(3, lid.getApellidos());
            pres.setString(4, lid.getCi());
            pres.setString(5, lid.getMinisterio());
            pres.setString(6, lid.getCargo());
            pres.setDate(7, lid.getIniciogestion());
            pres.setDate(8, lid.getFingestion());
            
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
    public boolean  modificar(Minis lid){
        int res=0;
        String modificarsql="update ministerio set nombre=?,apellidos=?,ci=?,ministerio=?,cargo=?,iniciogestion=?,fingestion=?"
                +"where idmin=?";
        try {
            con=consql.conectando();
            pres=con.prepareStatement(modificarsql);
            
            pres.setString(1, lid.getNombre());
            pres.setString(2, lid.getApellidos());
            pres.setString(3, lid.getCi());
            pres.setString(4, lid.getMinisterio());
            pres.setString(5, lid.getCargo());
            pres.setDate(6, lid.getIniciogestion());
            pres.setDate(7, lid.getFingestion());
            
            pres.setInt(8, lid.getIdmin());
            
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
        String eliminarsql="delete from ministerio where idmin=?";
        try {
            con=consql.conectando();
            pres=con.prepareStatement(eliminarsql);
            pres.setInt(1, idLid);
            
            res=pres.executeUpdate();
        } catch (Exception e) {
        }
    }
    public DefaultTableModel buscarlider(String buscar){
        String [] nombreColum={"NOMBRE","APELLIDOS","C.I.","MINISTERIO","CARGO","INICIO GESTION","FIN GESTION"};
        String [] registros=new String[7];
        DefaultTableModel tablasearh=new DefaultTableModel(null, nombreColum);
        
        String buscarsql="select * from ministerio where ministerio like'%"+buscar+"%'";
        
        try {
            con=consql.conectando();
            pres=con.prepareStatement(buscarsql);
            rs=pres.executeQuery();
            
            while(rs.next()){
                registros[0]=rs.getString("nombre");
                registros[1]=rs.getString("apellidos");
                registros[2]=rs.getString("ci");
                registros[3]=rs.getString("ministerio");
                registros[4]=rs.getString("cargo");
                registros[5]=rs.getString("iniciogestion");
                registros[6]=rs.getString("fingestion");
                
                tablasearh.addRow(registros);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return tablasearh;
    }
    public DefaultTableModel buscarMinis(String buscar){
        String [] nombreColum={"NOMBRE","APELLIDOS","C.I.","MINISTERIO","CARGO","INICIO GESTION","FIN GESTION"};
        String [] registros=new String[7];
        DefaultTableModel tablasearh=new DefaultTableModel(null, nombreColum);
        
        String buscarsql="select * from ministerio where ministerio like'%"+buscar+"%' or nombre like'%"+buscar+"%'"
                + "or apellidos like'%"+buscar+"%' or ci like'%"+buscar+"%' or cargo like'%"+buscar+"%' "
                + "or iniciogestion like'%"+buscar+"%' or fingestion like'%"+buscar+"%'";
        
        try {
            con=consql.conectando();
            pres=con.prepareStatement(buscarsql);
            rs=pres.executeQuery();
            
            while(rs.next()){
                registros[0]=rs.getString("nombre");
                registros[1]=rs.getString("apellidos");
                registros[2]=rs.getString("ci");
                registros[3]=rs.getString("ministerio");
                registros[4]=rs.getString("cargo");
                registros[5]=rs.getString("iniciogestion");
                registros[6]=rs.getString("fingestion");
                
                tablasearh.addRow(registros);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return tablasearh;
    }
    
}
