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
public class ControlListaOfrenda extends MouseAdapter implements ActionListener{
    VistaListaOfrenda vistaListaofrenda=new VistaListaOfrenda();
    OfrendaDAO dAO;
    Ofrenda die=new Ofrenda();
    DefaultTableModel tablaModel=new DefaultTableModel();
    int id;
    List<Ofrenda> lista;
    ExcelExpo exp;
    
    public ControlListaOfrenda(VistaListaOfrenda vlm, OfrendaDAO aO){
        this.vistaListaofrenda=vlm;
        this.dAO=aO;
        listar();
        
        this.vistaListaofrenda.botonreporte.addActionListener(this);
        this.vistaListaofrenda.botonbuscar.addActionListener(this);
        this.vistaListaofrenda.botonlistar.addActionListener(this);
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(vistaListaofrenda.botonreporte==ae.getSource()){
            try {
                exportars();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR EN REPORTAR");
            }
        }else if(vistaListaofrenda.botonbuscar==ae.getSource()){
            try{
                buscar(vistaListaofrenda.txtbuscar.getText());
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error en la busqueda");
            }
        }else if(vistaListaofrenda.botonlistar==ae.getSource()){
            try{
                limpiartabla(vistaListaofrenda.tablaofrenda);
                listar();
                vistaListaofrenda.txtbuscar.setText("");
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error en la busqueda");
            }
        }
    }
    public void listar(){
        lista=dAO.listarOfrenda();
        tablaModel=(DefaultTableModel) vistaListaofrenda.tablaofrenda.getModel();
        Object obj[]=new Object[9];
        for(int i=0;i<lista.size();i++){
            
            obj[0]=lista.get(i).getTesorero();
            obj[1]=lista.get(i).getCarnet();
            obj[2]=lista.get(i).getMes();
            obj[3]=lista.get(i).getEntrada();
            obj[4]=lista.get(i).getSalida();
            obj[5]=lista.get(i).getSaldoanterior();
            obj[6]=lista.get(i).getSaldoactual();
            obj[7]=lista.get(i).getDescripcion();
            obj[8]=lista.get(i).getFecharegistro();
            
            tablaModel.addRow(obj);
        }
        vistaListaofrenda.tablaofrenda.setModel(tablaModel);
    }
    public void buscar(String buscando){
        if(vistaListaofrenda.txtbuscar.getText().length()==0){
            JOptionPane.showMessageDialog(null, "INGRESE UN DATO PARA BUSCAR");
        }else{
            tablaModel=dAO.buscarOfrenda(buscando);
            vistaListaofrenda.tablaofrenda.setModel(tablaModel);
        }
    }
    public void exportars(){
        try {
            exp= new ExcelExpo();
            exp.Exportar(vistaListaofrenda.tablaofrenda);
        } catch (IOException ex) {
            //Logger.getLogger(vistaListaofrenda.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
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
}
