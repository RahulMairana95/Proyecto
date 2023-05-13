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
public class ActivosDAO {
    Connection con;
    Conexion consql=new Conexion();
    PreparedStatement pres;
    ResultSet rs;
    
    VistaMiembrosActivos miembrosActivos;
    
    public ActivosDAO(){}
    
    
    public ActivosDAO(VistaMiembrosActivos actis){
        this.miembrosActivos=actis;
    }
    
    public List mostrar(){
        List<MiembrosActivos> mac=new ArrayList<>();
        String listarsql="select * from diezmomiembro";
        
        try {
            con=consql.conectando();
            pres=con.prepareStatement(listarsql);
            rs=pres.executeQuery();
            
            while(rs.next()){
                MiembrosActivos mi=new MiembrosActivos();
                
                mi.setIdactivos(rs.getInt(1));
                mi.setIdmembrecia((rs.getInt(2)));
                mi.setNdiezmo(rs.getInt(3));
                mi.setNumrecibo(rs.getString(4));
                mi.setNombre(rs.getString(5));
                mi.setApellidos(rs.getString(6));
                mi.setCantidad(rs.getDouble(7));
                mi.setFecha(rs.getDate(8));
                
                mac.add(mi);
            }
            
        } catch (Exception e) {
        }
        return mac;
    }
    public boolean agregar(MiembrosActivos ma){
        String agregarsql="insert into diezmomiembro(idmembrecia,ndiezmo,numrecibo,nombre,apellidos,cantidad,fecha)values(?,?,?,?,?,?,?)";
        //System.err.println(agregarsql+"consulta");
        try {
            pres=con.prepareStatement(agregarsql);
            
            //System.out.println(ma.getIdmembrecia()+"id membres");
            pres.setInt(1, ma.getIdmembrecia());
            pres.setInt(2, ma.getNdiezmo());
            pres.setString(3, ma.getNumrecibo());
            pres.setString(4, ma.getNombre());
            pres.setString(5, ma.getApellidos());
            pres.setDouble(6, ma.getCantidad());
            pres.setDate(7, ma.getFecha());
            
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
    public boolean editar(MiembrosActivos ma){
        String editarsql="update diezmomiembro set ndiezmo=?,numrecibo=?,nombre=?,apellidos=?,cantidad=?,fecha=? where idactivos=?";
        
        try {
            con=consql.conectando();
            pres=con.prepareStatement(editarsql);
            
            pres.setInt(1, ma.getNdiezmo());
            pres.setString(2, ma.getNumrecibo());
            pres.setString(3, ma.getNombre());
            pres.setString(4, ma.getApellidos());
            pres.setDouble(5, ma.getCantidad());
            pres.setDate(6, ma.getFecha());
            
            pres.setInt(7, ma.getIdactivos());
            
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
    public void eliminar(int idac){
        int res=0;
        String deletsql="delete from diezmomiembro where idactivos=?";
        try {
            con=consql.conectando();
            pres=con.prepareStatement(deletsql);
            pres.setInt(1, idac);
            
            res=pres.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public DefaultTableModel buscarDiezmadores(String buscar){
        String [] nombreColum={"ndiezmo","numrecibo","nombre","apellidos","cantidad","fecha"};
        String [] registros=new String[6];
        DefaultTableModel tablasearh=new DefaultTableModel(null, nombreColum);
        
        String buscarsql="select * from diezmomiembro where ndiezmo like'%"+buscar+"%' or nombre like'%"+buscar+"%' or apellidos like'%"+buscar+"%'";
        
        try {
            con=consql.conectando();
            pres=con.prepareStatement(buscarsql);
            rs=pres.executeQuery();
            
            while(rs.next()){
                registros[0]=rs.getString("ndiezmo");
                registros[1]=rs.getString("numrecibo");
                registros[2]=rs.getString("nombre");
                registros[3]=rs.getString("apellidos");
                registros[4]=rs.getString("cantidad");
                registros[5]=rs.getString("fecha");
                
                tablasearh.addRow(registros);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return tablasearh;
    }
}
