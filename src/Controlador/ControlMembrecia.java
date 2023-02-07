/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import Vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAHUL
 */
public class ControlMembrecia extends MouseAdapter implements ActionListener{
    VistaMembrecia vistaMembrecia=new VistaMembrecia();
    MembreciaDAO membreciaDAO;
    Membrecia membrecia=new Membrecia();
    DefaultTableModel tablaModel=new DefaultTableModel();
    int id;
    List<Membrecia> lista;
    
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    
    ConverMayus con;
    
    public ControlMembrecia(VistaMembrecia vistaMembrecia, MembreciaDAO membreciaDAO){
        System.out.println("listando");
        this.vistaMembrecia=vistaMembrecia;
        this.membreciaDAO=membreciaDAO;
        
        listar();
        //mayus();
        inhabilitar();
        
        
        //EVENTO DE BOTONES
        this.vistaMembrecia.botonagregar.addActionListener(this);
        this.vistaMembrecia.botonmodificar.addActionListener(this);
        this.vistaMembrecia.botoneliminar.addActionListener(this);
        this.vistaMembrecia.botoncancelar.addActionListener(this);
        this.vistaMembrecia.botonnuevo.addActionListener(this);
        this.vistaMembrecia.botonreporte.addActionListener(this);
        this.vistaMembrecia.btnbuscar.addActionListener(this);
        
        this.vistaMembrecia.txtdocumento.addActionListener(this);
        
        //MODIFICAR Y ELIMINAR
        this.vistaMembrecia.tablademiembros.addMouseListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(vistaMembrecia.botonagregar==ae.getSource()){
            try {
                agreagarNuevo();
                limpiartabla(vistaMembrecia.tablademiembros);
                listar();
                limpiarventanas();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo agregar");
            }
        }else if(vistaMembrecia.botonmodificar==ae.getSource()){
            try {
                modificar();
                limpiartabla(vistaMembrecia.tablademiembros);
                listar();
                limpiarventanas();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo modificar");
            }
        }else if(vistaMembrecia.botoneliminar==ae.getSource()){
            try {
                eliminar();
                limpiartabla(vistaMembrecia.tablademiembros);
                listar();
                limpiarventanas();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar");
            }
        }else if(vistaMembrecia.botoncancelar==ae.getSource()){
            try {
                limpiarventanas();
                limpiartabla(vistaMembrecia.tablademiembros);
                listar();
                inhabilitar();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo cancelar");
            }
        }else if(vistaMembrecia.botonnuevo==ae.getSource()){
            try {
                limpiarventanas();
                habilitar();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo limpiar");
            }
        }else if(vistaMembrecia.btnbuscar==ae.getSource()){
            try{
                buscar(vistaMembrecia.txtbuscar.getText());
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error en la busqueda");
            }
        }
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        int fila=vistaMembrecia.tablademiembros.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA");
        }else{
            id=lista.get(fila).getIdmembrecia();
            
            String nom=vistaMembrecia.tablademiembros.getValueAt(fila, 0).toString();
            String pat=vistaMembrecia.tablademiembros.getValueAt(fila, 1).toString();
            String mat=vistaMembrecia.tablademiembros.getValueAt(fila, 2).toString();
            String num=vistaMembrecia.tablademiembros.getValueAt(fila, 3).toString();
            String fnac=vistaMembrecia.tablademiembros.getValueAt(fila, 4).toString();
            String civ=vistaMembrecia.tablademiembros.getValueAt(fila, 5).toString();
            String fcon=vistaMembrecia.tablademiembros.getValueAt(fila, 6).toString();
            String fbau=vistaMembrecia.tablademiembros.getValueAt(fila, 7).toString();
            String tal=vistaMembrecia.tablademiembros.getValueAt(fila, 8).toString();
            String don=vistaMembrecia.tablademiembros.getValueAt(fila, 9).toString();
            String act=vistaMembrecia.tablademiembros.getValueAt(fila, 10).toString();
            
            
            try {
                vistaMembrecia.txtnombre.setText(nom);
                vistaMembrecia.txtpaterno.setText(pat);
                vistaMembrecia.txtmaterno.setText(mat);
                vistaMembrecia.txtdocumento.setText(num);
                //System.out.println("parseado aqui");

                vistaMembrecia.datenacimiento.setDate(sdf.parse(fnac));
                
                vistaMembrecia.boxestado.setSelectedItem(civ);
                //System.out.println("parseado");
                vistaMembrecia.datevonversion.setDate(sdf.parse(fcon));
                
                vistaMembrecia.datebautizo.setDate(sdf.parse(fbau));
                
                vistaMembrecia.boxtalentos.setSelectedItem(tal);
                vistaMembrecia.boxdones.setSelectedItem(don);
                vistaMembrecia.boxactivo.setSelectedItem(act);
            } catch (ParseException err) {
                //System.out.println("errooooo"+ err);
            }
        }
    }




    
    public void listar(){
        lista=membreciaDAO.listarMembrecia();
        tablaModel=(DefaultTableModel) vistaMembrecia.tablademiembros.getModel();
        Object obj[]=new Object[11];
        for(int i=0;i<lista.size();i++){
            
            obj[0]=lista.get(i).getNombre();
            obj[1]=lista.get(i).getApellidop();
            obj[2]=lista.get(i).getApellidom();
            obj[3]=lista.get(i).getNumdocumento();
            obj[4]=sdf.format(lista.get(i).getFechanacimiento());
            obj[5]=lista.get(i).getEstadocivil();
            obj[6]=sdf.format(lista.get(i).getFechaconversion());
            obj[7]=sdf.format(lista.get(i).getFechabautizo());
            obj[8]=lista.get(i).getTalentos();
            obj[9]=lista.get(i).getDones();
            obj[10]=lista.get(i).getActivo();
            
            tablaModel.addRow(obj);
        }
        vistaMembrecia.tablademiembros.setModel(tablaModel);
    }
    public void agreagarNuevo(){
        if(vistaMembrecia.txtnombre.getText().trim().isEmpty()||
                vistaMembrecia.txtpaterno.getText().trim().isEmpty()||
                vistaMembrecia.txtmaterno.getText().trim().isEmpty()||
                vistaMembrecia.txtdocumento.getText().trim().isEmpty()||
                vistaMembrecia.datenacimiento.getCalendar().toString().trim().isEmpty()||
                vistaMembrecia.boxestado.getSelectedItem().toString().trim().isEmpty()||
                vistaMembrecia.datevonversion.getCalendar().toString().trim().isEmpty()||
                vistaMembrecia.datebautizo.getCalendar().toString().trim().isEmpty()||
                vistaMembrecia.boxtalentos.getSelectedItem().toString().trim().isEmpty()||
                vistaMembrecia.boxdones.getSelectedItem().toString().trim().isEmpty()||
                vistaMembrecia.boxactivo.getSelectedItem().toString().trim().isEmpty()){
            
            JOptionPane.showMessageDialog(null,"DEBE LLENAR TODOS LOS CAMPOS");
        }else{
            System.out.println("probando insert");
            
            //membrecia.setIdmembrecia(Integer.parseInt(vistaMembrecia.txtnombre.getText()));
            membrecia.setNombre(vistaMembrecia.txtnombre.getText());
            membrecia.setApellidop(vistaMembrecia.txtpaterno.getText());
            membrecia.setApellidom(vistaMembrecia.txtmaterno.getText());
            membrecia.setNumdocumento(vistaMembrecia.txtdocumento.getText());
            
            Calendar calenn, calenc,calenb;
            int dian,mesn,yearn,diac,mesc,yearc,diab,mesb,yearb;
            calenn=vistaMembrecia.datenacimiento.getCalendar();
            dian=calenn.get(Calendar.DAY_OF_MONTH);
            mesn=calenn.get(Calendar.MONTH);
            yearn=calenn.get(Calendar.YEAR)-1900;
            membrecia.setFechanacimiento(new Date(yearn, mesn, dian));
            
            membrecia.setEstadocivil((String) vistaMembrecia.boxestado.getSelectedItem());
            
            calenc=vistaMembrecia.datevonversion.getCalendar();
            diac=calenc.get(Calendar.DAY_OF_MONTH);
            mesc=calenc.get(Calendar.MONTH);
            yearc=calenc.get(Calendar.YEAR)-1900;
            membrecia.setFechaconversion(new Date(yearc, mesc, diac));
            
            calenb=vistaMembrecia.datebautizo.getCalendar();
            diab=calenb.get(Calendar.DAY_OF_MONTH);
            mesb=calenb.get(Calendar.MONTH);
            yearb=calenb.get(Calendar.YEAR)-1900;
            membrecia.setFechabautizo(new Date(yearb, mesb, diab));
            
            membrecia.setTalentos((String)vistaMembrecia.boxtalentos.getSelectedItem());
            membrecia.setDones((String)vistaMembrecia.boxdones.getSelectedItem());
            membrecia.setActivo((String) vistaMembrecia.boxactivo.getSelectedItem());
            
            membreciaDAO.agregar(membrecia);
        }
    }
    public void modificar(){
        int fila=vistaMembrecia.tablademiembros.getSelectedRow();
        System.out.println("modificar miembros");
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA");
        }else{
            if(vistaMembrecia.txtnombre.getText().trim().isEmpty()||
                vistaMembrecia.txtpaterno.getText().trim().isEmpty()||
                vistaMembrecia.txtmaterno.getText().trim().isEmpty()||
                vistaMembrecia.txtdocumento.getText().trim().isEmpty()||
                vistaMembrecia.datenacimiento.getCalendar().toString().trim().isEmpty()||
                vistaMembrecia.boxestado.getSelectedItem().toString().trim().isEmpty()||
                vistaMembrecia. datevonversion.getCalendar().toString().trim().isEmpty()||
                vistaMembrecia.datebautizo.getCalendar().toString().trim().isEmpty()||
                vistaMembrecia.boxtalentos.getSelectedItem().toString().trim().isEmpty()||
                vistaMembrecia.boxdones.getSelectedItem().toString().trim().isEmpty()||
                vistaMembrecia.boxactivo.getSelectedItem().toString().trim().isEmpty()){
            
            JOptionPane.showMessageDialog(null,"DEBE LLENAR TODOS LOS CAMPOS");
        }else{
            id=lista.get(fila).getIdmembrecia();
            
            String nombre=vistaMembrecia.txtnombre.getText();
            String paterno=vistaMembrecia.txtpaterno.getText();
            String materno=vistaMembrecia.txtmaterno.getText();
            String num=vistaMembrecia.txtdocumento.getText();
            
            Calendar calenn, calenc,calenb;
            int dian,mesn,yearn,diac,mesc,yearc,diab,mesb,yearb;
            calenn=vistaMembrecia.datenacimiento.getCalendar();
            dian=calenn.get(Calendar.DAY_OF_MONTH);
            mesn=calenn.get(Calendar.MONTH);
            yearn=calenn.get(Calendar.YEAR)-1900;
            Date fnacim=(new Date(yearn, mesn, dian));
            
            String civil=(String) vistaMembrecia.boxestado.getSelectedItem();
            
            calenc=vistaMembrecia.datevonversion.getCalendar();
            diac=calenc.get(Calendar.DAY_OF_MONTH);
            mesc=calenc.get(Calendar.MONTH);
            yearc=calenc.get(Calendar.YEAR)-1900;
            Date fconver=(new Date(yearc, mesc, diac));
            
            calenb=vistaMembrecia.datebautizo.getCalendar();
            diab=calenb.get(Calendar.DAY_OF_MONTH);
            mesb=calenb.get(Calendar.MONTH);
            yearb=calenb.get(Calendar.YEAR)-1900;
            Date fbaut=(new Date(yearb, mesb, diab));
            
            String tale=(String) vistaMembrecia.boxtalentos.getSelectedItem();
            String dones=(String) vistaMembrecia.boxdones.getSelectedItem();
            String acti=(String) vistaMembrecia.boxactivo.getSelectedItem();
            
                System.out.println("id " +id );
            
            membrecia.setIdmembrecia(id);
            membrecia.setNombre(nombre);
            membrecia.setApellidop(paterno);
            membrecia.setApellidom(materno);
            membrecia.setNumdocumento(num);
            
            membrecia.setFechanacimiento(fnacim);
            membrecia.setEstadocivil(civil);
            membrecia.setFechaconversion(fconver);
            membrecia.setFechabautizo(fbaut);
            
            membrecia.setTalentos(tale);
            membrecia.setDones(dones);
            membrecia.setActivo(acti);
            
                System.out.println("probando modificar");
            
            membreciaDAO.modificar(membrecia);
            }
        }
    }
    public void limpiartabla(JTable tabla){
        try {
            int filas=tabla.getRowCount();
            for(int i=0;filas>i;i++){
                tablaModel.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar tabla");
        }
    }
    public void eliminar(){
        int fila=vistaMembrecia.tablademiembros.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA PARA ELIMINAR");
        }else{
            membreciaDAO.eliminarmiembros(id);
            System.out.println("eliminando");
        }
    }
    public void limpiarventanas(){
        
        vistaMembrecia.txtnombre.setText("");
        vistaMembrecia.txtpaterno.setText("");
        vistaMembrecia.txtmaterno.setText("");
        vistaMembrecia.txtdocumento.setText("");
        
        Date fechaactual=new Date(Calendar.getInstance().getTime().getTime());
        vistaMembrecia.datenacimiento.setDate(fechaactual);
        
        vistaMembrecia.boxestado.setSelectedItem("");
       
        vistaMembrecia.datevonversion.setDate(fechaactual);
        
        vistaMembrecia.datebautizo.setDate(fechaactual);
        
        vistaMembrecia.boxtalentos.setSelectedItem("");
        vistaMembrecia.boxdones.setSelectedItem("");
        vistaMembrecia.boxactivo.setSelectedItem("");
    }
    public void inhabilitar(){
        vistaMembrecia.botonagregar.setEnabled(false);
        vistaMembrecia.botoncancelar.setEnabled(false);
        vistaMembrecia.botoneliminar.setEnabled(false);
        vistaMembrecia.botonmodificar.setEnabled(false);
        vistaMembrecia.botonreporte.setEnabled(false);
        
        vistaMembrecia.txtnombre.setEnabled(false);
        vistaMembrecia.txtpaterno.setEnabled(false);
        vistaMembrecia.txtmaterno.setEnabled(false);
        vistaMembrecia.txtdocumento.setEnabled(false);
        vistaMembrecia.boxtalentos.setEnabled(false);
        vistaMembrecia.boxdones.setEnabled(false);
        vistaMembrecia.boxactivo.setEnabled(false);
        vistaMembrecia.boxestado.setEnabled(false);
        vistaMembrecia.datebautizo.setEnabled(false);
        vistaMembrecia.datenacimiento.setEnabled(false);
        vistaMembrecia.datevonversion.setEnabled(false);
    }
    public void habilitar(){
        vistaMembrecia.botonagregar.setEnabled(true);
        vistaMembrecia.botoncancelar.setEnabled(true);
        vistaMembrecia.botoneliminar.setEnabled(true);
        vistaMembrecia.botonmodificar.setEnabled(true);
        vistaMembrecia.botonreporte.setEnabled(true);
        
        vistaMembrecia.txtnombre.setEnabled(true);
        vistaMembrecia.txtpaterno.setEnabled(true);
        vistaMembrecia.txtmaterno.setEnabled(true);
        vistaMembrecia.txtdocumento.setEnabled(true);
        vistaMembrecia.boxtalentos.setEnabled(true);
        vistaMembrecia.boxdones.setEnabled(true);
        vistaMembrecia.boxactivo.setEnabled(true);
        vistaMembrecia.boxestado.setEnabled(true);
        vistaMembrecia.datebautizo.setEnabled(true);
        vistaMembrecia.datenacimiento.setEnabled(true);
        vistaMembrecia.datevonversion.setEnabled(true);
    }
    
    public void buscar(String buscando){
        tablaModel=membreciaDAO.buscarMiembros(buscando);
        vistaMembrecia.tablademiembros.setModel(tablaModel);
    }
    
    
    
    /*public void mayus(){
        
            con=new ConverMayus();
            String cad=(String)vistaMembrecia.txtnombre.getText();
            
            con.convertirMayus(cad);
            
    }*/
}
