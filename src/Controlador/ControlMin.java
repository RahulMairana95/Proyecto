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
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class ControlMin extends MouseAdapter implements ActionListener{
    VistaLiderMin vistaLiderMin=new VistaLiderMin();
    MinDAO mdao;
    MembreciaDAO memDAO;
    Minis minis=new Minis();
    DefaultTableModel tablamodel=new DefaultTableModel();
    int id;
    List<Minis> lista;
    Membrecia mem=new Membrecia();
    
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    List<Membrecia> lislider=new ArrayList<>();
    
    ExcelExpo exp;
    
    public ControlMin(VistaLiderMin vl, MinDAO md){
        this.vistaLiderMin=vl;
        this.mdao=md;
        mostrarlista();
        inhabilitar();
        mostrarNombre();
        
        this.vistaLiderMin.botonagregar.addActionListener(this);
        this.vistaLiderMin.botonnuevo.addActionListener(this);
        this.vistaLiderMin.botoneditar.addActionListener(this);
        this.vistaLiderMin.botoncancelar.addActionListener(this);
        this.vistaLiderMin.botoneliminar.addActionListener(this);
        
        this.vistaLiderMin.botonreporte.addActionListener(this);
        //BOTONES BUSCAR Y LISTAR
        this.vistaLiderMin.botonbuscar.addActionListener(this);
        this.vistaLiderMin.botonlistar.addActionListener(this);
        
        this.vistaLiderMin.tablamin.addMouseListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(vistaLiderMin.botonagregar==ae.getSource()){
            try {
                agregarNuevo();
                limpiartabla(vistaLiderMin.tablamin);
                mostrarlista();
                limpiarfield();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo agregar");
            }
        }else if(vistaLiderMin.botoneditar==ae.getSource()){
            try {
                modificar();
                limpiartabla(vistaLiderMin.tablamin);
                mostrarlista();
                limpiarfield();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo modificar");
            }
        }else if(vistaLiderMin.botoneliminar==ae.getSource()){
            try {
                eliminar();
                limpiartabla(vistaLiderMin.tablamin);
                mostrarlista();
                limpiarfield();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar");
            }
        }else if(vistaLiderMin.botoncancelar==ae.getSource()){
            try {
                limpiarfield();
                limpiartabla(vistaLiderMin.tablamin);
                mostrarlista();
                inhabilitar();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo cancelar");
            }
        }else if(vistaLiderMin.botonnuevo==ae.getSource()){
            try {
                limpiarfield();
                habilitar();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo limpiar");
            }
        }else if(vistaLiderMin.botonbuscar==ae.getSource()){
            try {
                buscar(vistaLiderMin.txtbuscar.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR AL BUSCAR LIDER");
            }
        }else if(vistaLiderMin.botonlistar==ae.getSource()){
            try{
                vistaLiderMin.txtbuscar.setText("");
                limpiartabla(vistaLiderMin.tablamin);
                mostrarlista();
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "ERROR AL LISTAR");
            }
        }else if(vistaLiderMin.botonreporte==ae.getSource()){
            try{
                exportars();
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "ERROR AL EXPORTAR");
            }
        }
    }
    @Override
    public void mouseClicked(MouseEvent e){
        int fila=vistaLiderMin.tablamin.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA");
        }else{
            id=lista.get(fila).getIdmin();
            
            String nom=vistaLiderMin.tablamin.getValueAt(fila, 0).toString();
            String ape=vistaLiderMin.tablamin.getValueAt(fila, 1).toString();
            String ci=vistaLiderMin.tablamin.getValueAt(fila, 2).toString();
            String min=vistaLiderMin.tablamin.getValueAt(fila, 3).toString();
            String cargo=vistaLiderMin.tablamin.getValueAt(fila, 4).toString();
            String ini=vistaLiderMin.tablamin.getValueAt(fila, 5).toString();
            String fin=vistaLiderMin.tablamin.getValueAt(fila, 6).toString();
            
            try {
                vistaLiderMin.txtnombre.setText(nom);
                vistaLiderMin.txtapellidos.setText(ape);
                vistaLiderMin.txtdocumento.setText(ci);
                
                vistaLiderMin.boxministerio.setSelectedItem(min);
                vistaLiderMin.boxcargo.setSelectedItem(cargo);
                
                vistaLiderMin.fechainicio.setDate(sdf.parse(ini));
                vistaLiderMin.fechafin.setDate(sdf.parse(fin));
            } catch (Exception errr) {
            }
        }
    }
    public void mostrarNombre(){
        MembreciaDAO lldao=new MembreciaDAO();
        //List<Lideriglesia> lislider=new ArrayList<>();
        lislider=lldao.listarMembrecia();
        vistaLiderMin.boxnombre.addItem(" "+" "+"Seleccione un nombre");
        for(int i=0;i<lislider.size();i++){
            vistaLiderMin.boxnombre.addItem(lislider.get(i).getIdmembrecia()+" "+lislider.get(i).getNombre()+" "+lislider.get(i).getApellidop()+" "+lislider.get(i).getApellidom());
            
        }    
          
    }
    public void mostrarlista(){
        lista=mdao.mostrar();
        tablamodel=(DefaultTableModel) vistaLiderMin.tablamin.getModel();
        Object obj[]=new Object[7];
        //System.out.println("lista Lider");
        for(int i=0;i<lista.size();i++){
            
            obj[0]=lista.get(i).getNombre();
            obj[1]=lista.get(i).getApellidos();
            obj[2]=lista.get(i).getCi();
            obj[3]=lista.get(i).getMinisterio();
            obj[4]=lista.get(i).getCargo();
            obj[5]=sdf.format(lista.get(i).getIniciogestion());
            obj[6]=sdf.format(lista.get(i).getFingestion());
            
            tablamodel.addRow(obj);
        }
        vistaLiderMin.tablamin.setModel(tablamodel);
    }
    public void agregarNuevo(){
        if(vistaLiderMin.txtnombre.getText().trim().isEmpty()||
                vistaLiderMin.txtapellidos.getText().trim().isEmpty()||
                vistaLiderMin.txtdocumento.getText().trim().isEmpty()||
                vistaLiderMin.boxministerio.getSelectedItem().toString().trim().isEmpty()||
                vistaLiderMin.boxcargo.getSelectedItem().toString().trim().isEmpty()||
                vistaLiderMin.fechainicio.getCalendar().toString().trim().isEmpty()||
                vistaLiderMin.fechafin.getCalendar().toString().trim().isEmpty()){
            
          JOptionPane.showMessageDialog(null,"DEBE LLENAR TODOS LOS CAMPOS");  
        }else{
            
            String tmp=(String) vistaLiderMin.boxnombre.getSelectedItem();
            String [] aux=tmp.split(" ");
            String idmen=aux[0];
            
            minis.setIdmembrecia(Integer.parseInt(idmen));
            //System.err.println(idmen+"miembro num");
            ///////DATOS LLAMADOS DE LA MEMBRECIA
            minis.setNombre(vistaLiderMin.txtnombre.getText());
            minis.setApellidos(vistaLiderMin.txtapellidos.getText());
            minis.setCi(vistaLiderMin.txtdocumento.getText());
            
            minis.setMinisterio((String)vistaLiderMin.boxministerio.getSelectedItem());
            minis.setCargo((String)vistaLiderMin.boxcargo.getSelectedItem());
            
            Calendar calenn, calenc;
            int diai,mesi,yeari,diaf,mesf,yearf;
            calenn=vistaLiderMin.fechainicio.getCalendar();
            diai=calenn.get(Calendar.DAY_OF_MONTH);
            mesi=calenn.get(Calendar.MONTH);
            yeari=calenn.get(Calendar.YEAR)-1900;
            minis.setIniciogestion(new Date(yeari, mesi, diai));
            
            calenc=vistaLiderMin.fechafin.getCalendar();
            diaf=calenc.get(Calendar.DAY_OF_MONTH);
            mesf=calenc.get(Calendar.MONTH);
            yearf=calenc.get(Calendar.YEAR)-1900;
            minis.setFingestion(new Date(yearf, mesf, diaf));
            
            mdao.agregar(minis);
            
        }
    }
    public void modificar(){
        int fila=vistaLiderMin.tablamin.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA");
        }else{
            if(vistaLiderMin.txtnombre.getText().trim().isEmpty()||
                vistaLiderMin.txtapellidos.getText().trim().isEmpty()||
                vistaLiderMin.txtdocumento.getText().trim().isEmpty()||
                vistaLiderMin.boxministerio.getSelectedItem().toString().trim().isEmpty()||
                vistaLiderMin.boxcargo.getSelectedItem().toString().trim().isEmpty()||
                vistaLiderMin.fechainicio.getCalendar().toString().trim().isEmpty()||
                vistaLiderMin.fechafin.getCalendar().toString().trim().isEmpty()){
            
          JOptionPane.showMessageDialog(null,"DEBE LLENAR TODOS LOS CAMPOS");  
        }else{
            id=lista.get(fila).getIdmin();
            String nombre=vistaLiderMin.txtnombre.getText();
            String paterno=vistaLiderMin.txtapellidos.getText();
            String num=vistaLiderMin.txtdocumento.getText();
            
            String min=(String)vistaLiderMin.boxministerio.getSelectedItem();
            String cargo=(String)vistaLiderMin.boxcargo.getSelectedItem();
            
            Calendar calenn, calenc;
            int diai,mesi,yeari,diaf,mesf,yearf;
            calenn=vistaLiderMin.fechainicio.getCalendar();
            diai=calenn.get(Calendar.DAY_OF_MONTH);
            mesi=calenn.get(Calendar.MONTH);
            yeari=calenn.get(Calendar.YEAR)-1900;
            Date fini=(new Date(yeari, mesi, diai));
            
            calenc=vistaLiderMin.fechafin.getCalendar();
            diaf=calenc.get(Calendar.DAY_OF_MONTH);
            mesf=calenc.get(Calendar.MONTH);
            yearf=calenc.get(Calendar.YEAR)-1900;
            Date fin=(new Date(yearf, mesf, diaf));
            
            minis.setIdmin(id);
            minis.setNombre(nombre);
            minis.setApellidos(paterno);
            minis.setCi(num);
            minis.setMinisterio(min);
            minis.setCargo(cargo);
            minis.setIniciogestion(fini);
            minis.setFingestion(fin);
            
            mdao.modificar(minis);
            }
        }
    }
    public void eliminar(){
        int fila=vistaLiderMin.tablamin.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA PARA ELIMINAR");
        }else{
            mdao.eliminarlider(id);
            System.out.println("eliminando");
        }
    }
    public void buscar(String buscando){
        if(vistaLiderMin.txtbuscar.getText().length()==0){
            JOptionPane.showMessageDialog(null, "INGRESE UN DATO PARA BUSCAR");
        }else{
            tablamodel=mdao.buscarMinis(buscando);
            vistaLiderMin.tablamin.setModel(tablamodel);
        }
    }
    public void limpiartabla(JTable tabla){
        try {
            int filas=tabla.getRowCount();
            for(int i=0;filas>i;i++){
                tablamodel.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar tabla");
        }
    }
    public void exportars(){
        try {
            exp= new ExcelExpo();
            exp.Exportar(vistaLiderMin.tablamin);
        } catch (IOException ex) {
            Logger.getLogger(VistaListaMembrecia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void limpiarfield(){
        vistaLiderMin.txtnombre.setText("");
        vistaLiderMin.txtapellidos.setText("");
        vistaLiderMin.txtdocumento.setText("");
        vistaLiderMin.boxministerio.setSelectedItem("");
        vistaLiderMin.boxcargo.setSelectedItem("");
        
        Date fechaactual=new Date(Calendar.getInstance().getTime().getTime());
        vistaLiderMin.fechainicio.setDate(fechaactual);
        vistaLiderMin.fechafin.setDate(fechaactual);
    }
    public void inhabilitar(){
        vistaLiderMin.botonagregar.setEnabled(false);
        vistaLiderMin.botoncancelar.setEnabled(false);
        vistaLiderMin.botoneliminar.setEnabled(false);
        vistaLiderMin.botoneditar.setEnabled(false);
        vistaLiderMin.botonreporte.setEnabled(false);
        vistaLiderMin.tablamin.setEnabled(false);
        
        vistaLiderMin.boxnombre.setEnabled(false);
        vistaLiderMin.txtnombre.setEnabled(false);
        vistaLiderMin.txtapellidos.setEnabled(false);
        vistaLiderMin.txtdocumento.setEnabled(false);
        vistaLiderMin.boxministerio.setEnabled(false);
        vistaLiderMin.boxcargo.setEnabled(false);
        vistaLiderMin.fechafin.setEnabled(false);
        vistaLiderMin.fechainicio.setEnabled(false);
    }
    public void habilitar(){
        vistaLiderMin.botonagregar.setEnabled(true);
        vistaLiderMin.botoncancelar.setEnabled(true);
        vistaLiderMin.botoneliminar.setEnabled(true);
        vistaLiderMin.botoneditar.setEnabled(true);
        vistaLiderMin.botonreporte.setEnabled(true);
        vistaLiderMin.tablamin.setEnabled(true);
        
        vistaLiderMin.boxnombre.setEnabled(true);
        vistaLiderMin.txtnombre.setEnabled(true);
        vistaLiderMin.txtapellidos.setEnabled(true);
        vistaLiderMin.txtdocumento.setEnabled(true);
        vistaLiderMin.boxministerio.setEnabled(true);
        vistaLiderMin.boxcargo.setEnabled(true);
        vistaLiderMin.fechafin.setEnabled(true);
        vistaLiderMin.fechainicio.setEnabled(true);
    }
}
