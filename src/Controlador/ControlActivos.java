/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author LENOVO
 */
public class ControlActivos extends MouseAdapter implements ActionListener{
    VistaMiembrosActivos vistaMiembrosActivos=new VistaMiembrosActivos();
    ActivosDAO adao;
    MiembrosActivos miembrosActivos=new MiembrosActivos();
    
    //JDesktopPane panel;
    
    VentanaPrincipalIglesia vp;
    //VistaDiezmo vd= new VistaDiezmo();
    
    DefaultTableModel tableModel=new DefaultTableModel();
    int id;
    List<MiembrosActivos> lista;
    Membrecia mem=new Membrecia();
    
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    List<Membrecia> lismem=new ArrayList<>();
    
    ExcelExpo exp;

    
    public ControlActivos(VistaMiembrosActivos ma, ActivosDAO ad){
        this.vistaMiembrosActivos=ma;
        this.adao=ad;
        
        //LLAMANDO OBJETOS
        mostrarlista();
        relacion();
        inhabilitar();
        
        this.vistaMiembrosActivos.btnregistrar.addActionListener(this);
        this.vistaMiembrosActivos.btneditar.addActionListener(this);
        this.vistaMiembrosActivos.btndelete.addActionListener(this);
        this.vistaMiembrosActivos.btnexportar.addActionListener(this);
        this.vistaMiembrosActivos.btnbuscar.addActionListener(this);
        this.vistaMiembrosActivos.btncancelar.addActionListener(this);
        this.vistaMiembrosActivos.btnnuevo.addActionListener(this);
        this.vistaMiembrosActivos.btnlistar.addActionListener(this);
        //this.vistaMiembrosActivos.botonegreso.addActionListener(this);
        
        this.vistaMiembrosActivos.tablaactivos.addMouseListener(this);
    }
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        //AÑADIR METODOS DE EVENTOS
        if(vistaMiembrosActivos.btnregistrar==ae.getSource()){
            try {
                addNuevo();
                limpiartabla(vistaMiembrosActivos.tablaactivos);
                mostrarlista();
                limpiarfield();
                //JOptionPane.showMessageDialog(null, "SE AGREGARON CORRECTAMENTE");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo agregar");
            }
        }else if(vistaMiembrosActivos.btneditar==ae.getSource()){
            try {
                editar();
                limpiartabla(vistaMiembrosActivos.tablaactivos);
                mostrarlista();
                limpiarfield();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo modificar");
            }
        }else if(vistaMiembrosActivos.btndelete==ae.getSource()){
            try {
                delete();
                limpiartabla(vistaMiembrosActivos.tablaactivos);
                mostrarlista();
                limpiarfield();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar");
            }
        }else if(vistaMiembrosActivos.btnexportar==ae.getSource()){
            try {
                exportars();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar");
            }
        }else if(vistaMiembrosActivos.btnbuscar==ae.getSource()){
            try {
                buscar(vistaMiembrosActivos.txtbuscar.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR AL BUSCAR PERSONA");
            }
        }else if(vistaMiembrosActivos.btncancelar==ae.getSource()){
            try {
                limpiartabla(vistaMiembrosActivos.tablaactivos);
                mostrarlista();
                limpiarfield();
                inhabilitar();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR AL BUSCAR PERSONA");
            }
        }else if(vistaMiembrosActivos.btnnuevo==ae.getSource()){
            try {
                limpiarfield();
                habilitar();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR AL BUSCAR PERSONA");
            }
        }else if(vistaMiembrosActivos.btnlistar==ae.getSource()){
            try {
                vistaMiembrosActivos.txtbuscar.setText("");
                limpiartabla(vistaMiembrosActivos.tablaactivos);
                mostrarlista();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR AL BUSCAR PERSONA");
            }
        
        }
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        int fila=vistaMiembrosActivos.tablaactivos.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UNA FILA");
        }else{
            id=lista.get(fila).getIdactivos();
            
            String nd=vistaMiembrosActivos.tablaactivos.getValueAt(fila, 0).toString();
            String nr=vistaMiembrosActivos.tablaactivos.getValueAt(fila, 1).toString();
            String nom=vistaMiembrosActivos.tablaactivos.getValueAt(fila, 2).toString();
            String ap=vistaMiembrosActivos.tablaactivos.getValueAt(fila, 3).toString();
            String can=vistaMiembrosActivos.tablaactivos.getValueAt(fila, 4).toString();
            String fc=vistaMiembrosActivos.tablaactivos.getValueAt(fila, 5).toString();
            
            try {
                vistaMiembrosActivos.textnumdiezmo.setText(nd);
                vistaMiembrosActivos.txtnumrecibo.setText(nr);
                vistaMiembrosActivos.txtnombre.setText(nom);
                vistaMiembrosActivos.txtapellidos.setText(ap);
                vistaMiembrosActivos.txtcantidad.setText(can);
                
                //System.err.println(fc+" cagatido");
                vistaMiembrosActivos.datedeposito.setDate(sdf.parse(fc));
                
            } catch (Exception er) {
                JOptionPane.showMessageDialog(null, er);
            }
        }
    }
    
    public void relacion(){
        MembreciaDAO aO=new MembreciaDAO();
        lismem=aO.listarMembrecia();
        vistaMiembrosActivos.boxmiembroactivos.addItem(" "+" "+"Seleccione un nombre");
        for(int i=0;i<lismem.size();i++){
            vistaMiembrosActivos.boxmiembroactivos.addItem(lismem.get(i).getIdmembrecia()+" "+lismem.get(i).getNombre()+" "+lismem.get(i).getApellidop()+" "+lismem.get(i).getApellidom());
        }
    }
    
    public void mostrarlista(){
        lista=adao.mostrar();
        tableModel=(DefaultTableModel) vistaMiembrosActivos.tablaactivos.getModel();
        Object obj[]=new Object[6];
        for(int i=0;i<lista.size();i++){
            obj[0]=lista.get(i).getNdiezmo();
            obj[1]=lista.get(i).getNumrecibo();
            obj[2]=lista.get(i).getNombre();
            obj[3]=lista.get(i).getApellidos();
            obj[4]=lista.get(i).getCantidad();
            obj[5]=sdf.format(lista.get(i).getFecha());
            
            tableModel.addRow(obj);
        }
        vistaMiembrosActivos.tablaactivos.setModel(tableModel);
    }
    public void addNuevo(){
        if(vistaMiembrosActivos.textnumdiezmo.getText().trim().isEmpty()||
                vistaMiembrosActivos.txtnumrecibo.getText().trim().isEmpty()||
                vistaMiembrosActivos.txtnombre.getText().trim().isEmpty()||
                vistaMiembrosActivos.txtapellidos.getText().trim().isEmpty()||
                vistaMiembrosActivos.txtcantidad.getText().trim().isEmpty()||
                vistaMiembrosActivos.datedeposito.getCalendar().toString().trim().isEmpty()){
            
            JOptionPane.showMessageDialog(null,"DEBE LLENAR TODOS LOS CAMPOS"); 
        }else{
            String tmp=(String) vistaMiembrosActivos.boxmiembroactivos.getSelectedItem();
            String [] aux=tmp.split(" ");
            String idmen=aux[0];
            
            miembrosActivos.setIdmembrecia(Integer.parseInt(idmen));
            //System.err.println(idmen+"miembro num");
            miembrosActivos.setNdiezmo(Integer.parseInt(vistaMiembrosActivos.textnumdiezmo.getText()));
            miembrosActivos.setNumrecibo(vistaMiembrosActivos.txtnumrecibo.getText());
            miembrosActivos.setNombre(vistaMiembrosActivos.txtnombre.getText());
            miembrosActivos.setApellidos(vistaMiembrosActivos.txtapellidos.getText());
            miembrosActivos.setCantidad(Double.parseDouble(vistaMiembrosActivos.txtcantidad.getText()));
            
            Calendar calenn;
            int diai,mesi,yeari;
            calenn=vistaMiembrosActivos.datedeposito.getCalendar();
            diai=calenn.get(Calendar.DAY_OF_MONTH);
            mesi=calenn.get(Calendar.MONTH);
            yeari=calenn.get(Calendar.YEAR)-1900;
            miembrosActivos.setFecha(new Date(yeari, mesi, diai));
            
            adao.agregar(miembrosActivos);
        }
    }
    public void editar(){
        int fila=vistaMiembrosActivos.tablaactivos.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA PARA MODIFICAR DATOS");
        }else{
            if(vistaMiembrosActivos.textnumdiezmo.getText().trim().isEmpty()||
                    vistaMiembrosActivos.txtnumrecibo.getText().trim().isEmpty()||
                    vistaMiembrosActivos.txtnombre.getText().trim().isEmpty()||
                    vistaMiembrosActivos.txtapellidos.getText().trim().isEmpty()||
                    vistaMiembrosActivos.txtcantidad.getText().trim().isEmpty()||
                    vistaMiembrosActivos.datedeposito.getCalendar().toString().trim().isEmpty()){
                
                JOptionPane.showMessageDialog(null,"DEBE LLENAR TODOS LOS CAMPOS");
            }else{
                id=lista.get(fila).getIdactivos();
                String nd=vistaMiembrosActivos.textnumdiezmo.getText();
                String nr=vistaMiembrosActivos.txtnumrecibo.getText();
                String nom=vistaMiembrosActivos.txtnombre.getText();
                String ap=vistaMiembrosActivos.txtapellidos.getText();
                String ca=vistaMiembrosActivos.txtcantidad.getText();
                
                Calendar calenn;
                int diai,mesi,yeari;
                calenn=vistaMiembrosActivos.datedeposito.getCalendar();
                diai=calenn.get(Calendar.DAY_OF_MONTH);
                mesi=calenn.get(Calendar.MONTH);
                yeari=calenn.get(Calendar.YEAR)-1900;
                Date fe=(new Date(yeari, mesi, diai));
                
                miembrosActivos.setIdactivos(id);
                miembrosActivos.setNdiezmo(Integer.parseInt(nd));
                miembrosActivos.setNumrecibo(nr);
                miembrosActivos.setNombre(nom);
                miembrosActivos.setApellidos(ap);
                miembrosActivos.setCantidad(Double.parseDouble(ca));
                miembrosActivos.setFecha(fe);
                
                adao.editar(miembrosActivos);
            }
        }
    }
    public void delete(){
        int fila=vistaMiembrosActivos.tablaactivos.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA PARA ELIMINAR");
        }else{
            adao.eliminar(id);
        }
    }
    public void exportars(){
        try {
            exp= new ExcelExpo();
            exp.Exportar(vistaMiembrosActivos.tablaactivos);
        } catch (IOException ex) {
            Logger.getLogger(VistaListaMembrecia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void buscar(String buscando){
        tableModel=adao.buscarDiezmadores(buscando);
        vistaMiembrosActivos.tablaactivos.setModel(tableModel);
    }
    
    
    
    public void limpiartabla(JTable tabla){
        try {
            int filas=tabla.getRowCount();
            for(int i=0;filas>i;i++){
                tableModel.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar tabla");
        }
    }
    public void limpiarfield(){
        vistaMiembrosActivos.textnumdiezmo.setText("");
        vistaMiembrosActivos.txtnumrecibo.setText("");
        vistaMiembrosActivos.txtnombre.setText("");
        vistaMiembrosActivos.txtapellidos.setText("");
        vistaMiembrosActivos.txtcantidad.setText("");
        
        Date fechaactual=new Date(Calendar.getInstance().getTime().getTime());
        vistaMiembrosActivos.datedeposito.setDate(fechaactual);
        
    }
    public void inhabilitar(){
        vistaMiembrosActivos.btnregistrar.setEnabled(false);
        vistaMiembrosActivos.btneditar.setEnabled(false);
        vistaMiembrosActivos.btncancelar.setEnabled(false);
        vistaMiembrosActivos.btndelete.setEnabled(false);
        vistaMiembrosActivos.btnexportar.setEnabled(false);
        vistaMiembrosActivos.btnbuscar.setEnabled(false);
        vistaMiembrosActivos.btnlistar.setEnabled(false);
        
        vistaMiembrosActivos.boxmiembroactivos.setEnabled(false);
        vistaMiembrosActivos.tablaactivos.setEnabled(false);
        vistaMiembrosActivos.txtnombre.setEnabled(false);
        vistaMiembrosActivos.txtapellidos.setEnabled(false);
        vistaMiembrosActivos.txtbuscar.setEnabled(false);
        vistaMiembrosActivos.txtcantidad.setEnabled(false);
        vistaMiembrosActivos.txtnumrecibo.setEnabled(false);
        vistaMiembrosActivos.datedeposito.setEnabled(false);
        vistaMiembrosActivos.textnumdiezmo.setEnabled(false);
        
    }
    public void habilitar(){
        vistaMiembrosActivos.btnregistrar.setEnabled(true);
        vistaMiembrosActivos.btneditar.setEnabled(true);
        vistaMiembrosActivos.btncancelar.setEnabled(true);
        vistaMiembrosActivos.btndelete.setEnabled(true);
        vistaMiembrosActivos.btnexportar.setEnabled(true);
        vistaMiembrosActivos.btnbuscar.setEnabled(true);
        vistaMiembrosActivos.btnlistar.setEnabled(true);
        
        vistaMiembrosActivos.boxmiembroactivos.setEnabled(true);
        vistaMiembrosActivos.tablaactivos.setEnabled(true);
        vistaMiembrosActivos.txtnombre.setEnabled(true);
        vistaMiembrosActivos.txtapellidos.setEnabled(true);
        vistaMiembrosActivos.txtbuscar.setEnabled(true);
        vistaMiembrosActivos.txtcantidad.setEnabled(true);
        vistaMiembrosActivos.txtnumrecibo.setEnabled(true);
        vistaMiembrosActivos.datedeposito.setEnabled(true);
        vistaMiembrosActivos.textnumdiezmo.setEnabled(true);
    }
    
    
}
