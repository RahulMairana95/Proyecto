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
 * @author RAHUL
 */
public class ControlLider extends MouseAdapter implements ActionListener{
    VistaLider vistaLider=new VistaLider();
    LiderDAO ldao;
    MembreciaDAO menAO;
    Lideriglesia lideriglesia=new Lideriglesia();
    DefaultTableModel tablamodel=new DefaultTableModel();
    int id;
    List<Lideriglesia> lista;
    Membrecia mem=new Membrecia();
    
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    List<Membrecia> lislider=new ArrayList<>();
    
    ExcelExpo exp;
    
    public ControlLider(VistaLider vl, LiderDAO ld){
        this.vistaLider=vl;
        this.ldao=ld;
        //System.out.println("liderando");
        mostrarlista();
        inhabilitar();
        mostrarNombre();
        //listarnombres();
        
        this.vistaLider.botonagregar.addActionListener(this);
        this.vistaLider.botoneditar.addActionListener(this);
        this.vistaLider.botoncancelar.addActionListener(this);
        this.vistaLider.botoneliminar.addActionListener(this);
        this.vistaLider.botonnuevo.addActionListener(this);
        
        this.vistaLider.botonreporte.addActionListener(this);
        //BOTONES BUSCAR Y LISTAR
        this.vistaLider.botonbuscar.addActionListener(this);
        this.vistaLider.botonlistar.addActionListener(this);
        
        this.vistaLider.tablalider.addMouseListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(vistaLider.botonagregar==ae.getSource()){
            try {
                agregarNuevo();
                limpiartabla(vistaLider.tablalider);
                mostrarlista();
                limpiarfield();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo agregar");
            }
        }else if(vistaLider.botoneditar==ae.getSource()){
            try {
                modificar();
                limpiartabla(vistaLider.tablalider);
                mostrarlista();
                limpiarfield();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo modificar");
            }
        }else if(vistaLider.botoneliminar==ae.getSource()){
            try {
                eliminar();
                limpiartabla(vistaLider.tablalider);
                mostrarlista();
                limpiarfield();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar");
            }
        }else if(vistaLider.botoncancelar==ae.getSource()){
            try {
                limpiarfield();
                limpiartabla(vistaLider.tablalider);
                mostrarlista();
                inhabilitar();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo cancelar");
            }
        }else if(vistaLider.botonnuevo==ae.getSource()){
            try {
                limpiarfield();
                habilitar();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo limpiar");
            }
        }else if(vistaLider.botonbuscar==ae.getSource()){
            try {
                buscar(vistaLider.txtbuscar.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR AL BUSCAR LIDER");
            }
        }else if(vistaLider.botonlistar==ae.getSource()){
            try{
                vistaLider.txtbuscar.setText("");
                limpiartabla(vistaLider.tablalider);
                mostrarlista();
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "ERROR AL LISTAR");
            }
        }else if(vistaLider.botonreporte==ae.getSource()){
            try{
                exportars();
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "ERROR AL EXPORTAR");
            }
        }
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e){
        int fila=vistaLider.tablalider.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA");
        }else{
            id=lista.get(fila).getIdlider();
            
            String nom=vistaLider.tablalider.getValueAt(fila, 0).toString();
            String ape=vistaLider.tablalider.getValueAt(fila, 1).toString();
            String ci=vistaLider.tablalider.getValueAt(fila, 2).toString();
            String cargo=vistaLider.tablalider.getValueAt(fila, 3).toString();
            String ini=vistaLider.tablalider.getValueAt(fila, 4).toString();
            String fin=vistaLider.tablalider.getValueAt(fila, 5).toString();
            
            try {
                vistaLider.txtnombre.setText(nom);
                vistaLider.txtapellidos.setText(ape);
                vistaLider.txtdocumento.setText(ci);
                
                vistaLider.boxcargo.setSelectedItem(cargo);
                
                vistaLider.fechainicio.setDate(sdf.parse(ini));
                vistaLider.fechafin.setDate(sdf.parse(fin));
            } catch (Exception errr) {
            }
        }
    }
    public void mostrarNombre(){
        MembreciaDAO lldao=new MembreciaDAO();
        //List<Lideriglesia> lislider=new ArrayList<>();
        lislider=lldao.listarMembrecia();
        vistaLider.boxnombre.addItem(" "+" "+"Seleccione un nombre");
        for(int i=0;i<lislider.size();i++){
            vistaLider.boxnombre.addItem(lislider.get(i).getIdmembrecia()+" "+lislider.get(i).getNombre()+" "+lislider.get(i).getApellidop()+" "+lislider.get(i).getApellidom());
            
        }    
          
    }/*
    public void listarnombres(){
       String dato= (String)vistaLider.boxnombre.getSelectedItem();
       String [] aux=dato.split("-");
       String idmem=aux[0];
       
       MembreciaDAO llldao=new MembreciaDAO();
       lislider=llldao.listarMembrecia();
       
       
        if(){
            
            vistaLider.txtnombre.setText(mem.getNombre());
            vistaLider.txtpaterno.setText(mem.getApellidop());
            vistaLider.txtmaterno.setText(mem.getApellidom());
            vistaLider.txtdocumento.setText(mem.getNumdocumento());
            
            System.out.println("datossssssss" +dato);
        } 
        
    }*/
    
    
    public void mostrarlista(){
        lista=ldao.mostrar();
        tablamodel=(DefaultTableModel) vistaLider.tablalider.getModel();
        Object obj[]=new Object[6];
        //System.out.println("lista Lider");
        for(int i=0;i<lista.size();i++){
            
            obj[0]=lista.get(i).getNombre();
            obj[1]=lista.get(i).getApellidos();
            obj[2]=lista.get(i).getCi();
            obj[3]=lista.get(i).getCargo();
            obj[4]=sdf.format(lista.get(i).getIniciogestion());
            obj[5]=sdf.format(lista.get(i).getFingestion());
            
            tablamodel.addRow(obj);
        }
        vistaLider.tablalider.setModel(tablamodel);
    }

    public void agregarNuevo(){
        if(vistaLider.txtnombre.getText().trim().isEmpty()||
                vistaLider.txtapellidos.getText().trim().isEmpty()||
                vistaLider.txtdocumento.getText().trim().isEmpty()||
                vistaLider.boxcargo.getSelectedItem().toString().trim().isEmpty()||
                vistaLider.fechainicio.getCalendar().toString().trim().isEmpty()||
                vistaLider.fechafin.getCalendar().toString().trim().isEmpty()){
            
          JOptionPane.showMessageDialog(null,"DEBE LLENAR TODOS LOS CAMPOS");  
        }else{
            
            String tmp=(String) vistaLider.boxnombre.getSelectedItem();
            String [] aux=tmp.split(" ");
            String idmen=aux[0];
            
            lideriglesia.setIdmembrecia(Integer.parseInt(idmen));
            //System.err.println(idmen+"miembro num");
            ///////DATOS LLAMADOS DE LA MEMBRECIA
            lideriglesia.setNombre(vistaLider.txtnombre.getText());
            lideriglesia.setApellidos(vistaLider.txtapellidos.getText());
            lideriglesia.setCi(vistaLider.txtdocumento.getText());
            
            lideriglesia.setCargo((String)vistaLider.boxcargo.getSelectedItem());
            
            Calendar calenn, calenc;
            int diai,mesi,yeari,diaf,mesf,yearf;
            calenn=vistaLider.fechainicio.getCalendar();
            diai=calenn.get(Calendar.DAY_OF_MONTH);
            mesi=calenn.get(Calendar.MONTH);
            yeari=calenn.get(Calendar.YEAR)-1900;
            lideriglesia.setIniciogestion(new Date(yeari, mesi, diai));
            
            calenc=vistaLider.fechafin.getCalendar();
            diaf=calenc.get(Calendar.DAY_OF_MONTH);
            mesf=calenc.get(Calendar.MONTH);
            yearf=calenc.get(Calendar.YEAR)-1900;
            lideriglesia.setFingestion(new Date(yearf, mesf, diaf));
            
            ldao.agregar(lideriglesia);
            
        }
    }
    public void modificar(){
        int fila=vistaLider.tablalider.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA");
        }else{
            if(vistaLider.txtnombre.getText().trim().isEmpty()||
                vistaLider.txtapellidos.getText().trim().isEmpty()||
                vistaLider.txtdocumento.getText().trim().isEmpty()||
                vistaLider.boxcargo.getSelectedItem().toString().trim().isEmpty()||
                vistaLider.fechainicio.getCalendar().toString().trim().isEmpty()||
                vistaLider.fechafin.getCalendar().toString().trim().isEmpty()){
            
          JOptionPane.showMessageDialog(null,"DEBE LLENAR TODOS LOS CAMPOS");  
        }else{
            id=lista.get(fila).getIdlider();
            String nombre=vistaLider.txtnombre.getText();
            String paterno=vistaLider.txtapellidos.getText();
            String num=vistaLider.txtdocumento.getText();
            
            String cargo=(String)vistaLider.boxcargo.getSelectedItem();
            
            Calendar calenn, calenc;
            int diai,mesi,yeari,diaf,mesf,yearf;
            calenn=vistaLider.fechainicio.getCalendar();
            diai=calenn.get(Calendar.DAY_OF_MONTH);
            mesi=calenn.get(Calendar.MONTH);
            yeari=calenn.get(Calendar.YEAR)-1900;
            Date fini=(new Date(yeari, mesi, diai));
            
            calenc=vistaLider.fechafin.getCalendar();
            diaf=calenc.get(Calendar.DAY_OF_MONTH);
            mesf=calenc.get(Calendar.MONTH);
            yearf=calenc.get(Calendar.YEAR)-1900;
            Date fin=(new Date(yearf, mesf, diaf));
            
            lideriglesia.setIdlider(id);
            lideriglesia.setNombre(nombre);
            lideriglesia.setApellidos(paterno);
            lideriglesia.setCi(num);
            lideriglesia.setCargo(cargo);
            lideriglesia.setIniciogestion(fini);
            lideriglesia.setFingestion(fin);
            
            ldao.modificar(lideriglesia);
            }
        }
    }
    public void eliminar(){
        int fila=vistaLider.tablalider.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA PARA ELIMINAR");
        }else{
            ldao.eliminarlider(id);
            System.out.println("eliminando");
        }
    }
    public void buscar(String buscando){
        if(vistaLider.txtbuscar.getText().length()==0){
            JOptionPane.showMessageDialog(null, "INGRESE UN DATO PARA BUSCAR");
        }else{
            tablamodel=ldao.buscarlider(buscando);
            vistaLider.tablalider.setModel(tablamodel);
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
    public void limpiarfield(){
        vistaLider.txtnombre.setText("");
        vistaLider.txtapellidos.setText("");
        vistaLider.txtdocumento.setText("");
        vistaLider.boxcargo.setSelectedItem("");
        
        Date fechaactual=new Date(Calendar.getInstance().getTime().getTime());
        vistaLider.fechainicio.setDate(fechaactual);
        vistaLider.fechafin.setDate(fechaactual);
    }
    public void exportars(){
        try {
            exp= new ExcelExpo();
            exp.Exportar(vistaLider.tablalider);
        } catch (IOException ex) {
            Logger.getLogger(VistaListaMembrecia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void inhabilitar(){
        vistaLider.botonagregar.setEnabled(false);
        vistaLider.botoncancelar.setEnabled(false);
        vistaLider.botoneliminar.setEnabled(false);
        vistaLider.botoneditar.setEnabled(false);
        vistaLider.botonreporte.setEnabled(false);
        vistaLider.tablalider.setEnabled(false);
        
        vistaLider.boxnombre.setEnabled(false);
        vistaLider.txtnombre.setEnabled(false);
        vistaLider.txtapellidos.setEnabled(false);
        vistaLider.txtdocumento.setEnabled(false);
        vistaLider.boxcargo.setEnabled(false);
        vistaLider.fechafin.setEnabled(false);
        vistaLider.fechainicio.setEnabled(false);
    }
    public void habilitar(){
        vistaLider.botonagregar.setEnabled(true);
        vistaLider.botoncancelar.setEnabled(true);
        vistaLider.botoneliminar.setEnabled(true);
        vistaLider.botoneditar.setEnabled(true);
        vistaLider.botonreporte.setEnabled(true);
        vistaLider.tablalider.setEnabled(true);
        
        vistaLider.boxnombre.setEnabled(true);
        vistaLider.txtnombre.setEnabled(true);
        vistaLider.txtapellidos.setEnabled(true);
        vistaLider.txtdocumento.setEnabled(true);
        vistaLider.boxcargo.setEnabled(true);
        vistaLider.fechafin.setEnabled(true);
        vistaLider.fechainicio.setEnabled(true);
    }
}
