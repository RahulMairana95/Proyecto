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
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author LENOVO
 */
public class ControlListaActivos extends MouseAdapter implements ActionListener{
    VistaListaActivos vistaListaActivos=new VistaListaActivos();
    ActivosDAO dao;
    MiembrosActivos activos=new MiembrosActivos();
    DefaultTableModel tablaModel=new DefaultTableModel();
    int id;
    List<MiembrosActivos> lista; 
    ExcelExpo exp;
    
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    public ControlListaActivos(VistaListaActivos vla, ActivosDAO ad){
        this.vistaListaActivos=vla;
        this.dao=ad;
        mostrarlista();
        
        
        this.vistaListaActivos.botonbuscar.addActionListener(this);
        this.vistaListaActivos.botonlistar.addActionListener(this);
        this.vistaListaActivos.botonexcel.addActionListener(this);
        
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(vistaListaActivos.botonbuscar==ae.getSource()){
            try {
                buscar(vistaListaActivos.txtbuscar.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR AL BUSCAR");
            }
        }else if(vistaListaActivos.botonlistar==ae.getSource()){
            try{
                limpiartabla(vistaListaActivos.tablaactivos);
                mostrarlista();
                vistaListaActivos.txtbuscar.setText("");
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "ERROR EN LISTAR");
            }
        }else if(vistaListaActivos.botonexcel==ae.getSource()){
            try{
                exportars();
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "ERROR EN EXPORTAR");
            }
        }
    }
    
    public void mostrarlista(){
        lista=dao.mostrar();
        tablaModel=(DefaultTableModel) vistaListaActivos.tablaactivos.getModel();
        Object obj[]=new Object[6];
        for(int i=0;i<lista.size();i++){
            obj[0]=lista.get(i).getNdiezmo();
            obj[1]=lista.get(i).getNumrecibo();
            obj[2]=lista.get(i).getNombre();
            obj[3]=lista.get(i).getApellidos();
            obj[4]=lista.get(i).getCantidad();
            obj[5]=sdf.format(lista.get(i).getFecha());
            
            tablaModel.addRow(obj);
        }
        vistaListaActivos.tablaactivos.setModel(tablaModel);
    }
    public void buscar(String buscando){
        if(vistaListaActivos.txtbuscar.getText().length()==0){
            JOptionPane.showMessageDialog(null, "INGRESE UN DATO PARA BUSCAR");
        }else{
            tablaModel=dao.buscarDiezmadores(buscando);
            vistaListaActivos.tablaactivos.setModel(tablaModel);
        }
    }
    public void limpiartabla(JTable tabla){
        try {
            int filas=tabla.getRowCount();
            for(int i=0;filas>i;i++){
                tablaModel.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL LIMPIAR");
        }
    }
    public void exportars(){
        try {
            exp= new ExcelExpo();
            exp.Exportar(vistaListaActivos.tablaactivos);
        } catch (IOException ex) {
            //Logger.getLogger(vistaListaofrenda.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
}
