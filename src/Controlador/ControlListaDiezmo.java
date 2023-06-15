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
public class ControlListaDiezmo extends MouseAdapter implements ActionListener{
    VistaListaDiezmo vistaListaDiezmo=new VistaListaDiezmo();
    DiezmoDAO dAO;
    Diezmo die=new Diezmo();
    DefaultTableModel tablaModel=new DefaultTableModel();
    int id;
    List<Diezmo> lista;
    
    ExcelExpo exp;
    
    public ControlListaDiezmo(VistaListaDiezmo vlm, DiezmoDAO aO){
        this.vistaListaDiezmo=vlm;
        this.dAO=aO;
        listar();
        
        this.vistaListaDiezmo.botonreporte.addActionListener(this);
        this.vistaListaDiezmo.botonbuscar.addActionListener(this);
        this.vistaListaDiezmo.botonlistar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(vistaListaDiezmo.botonreporte==ae.getSource()){
            try {
                excel();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR EN REPORTAR");
            }
        }else if(vistaListaDiezmo.botonbuscar==ae.getSource()){
            try{
                buscar(vistaListaDiezmo.txtbuscar.getText());
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error en la busqueda");
            }
        }else if(vistaListaDiezmo.botonlistar==ae.getSource()){
            try{
                limpiartabla(vistaListaDiezmo.tabladiezmo);
                listar();
                vistaListaDiezmo.txtbuscar.setText("");
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error en la busqueda");
            }
        }
    }
    public void listar(){
        lista=dAO.listarDiezmo();
        tablaModel=(DefaultTableModel) vistaListaDiezmo.tabladiezmo.getModel();
        Object obj[]=new Object[11];
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
        vistaListaDiezmo.tabladiezmo.setModel(tablaModel);
    }
    public void buscar(String buscando){
        if(vistaListaDiezmo.txtbuscar.getText().length()==0){
            JOptionPane.showMessageDialog(null, "INGRESE UN DATO PARA BUSCAR");
        }else{
            tablaModel=dAO.buscarDiezmo(buscando);
            vistaListaDiezmo.tabladiezmo.setModel(tablaModel);
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
    public void excel(){
        try {
            exp= new ExcelExpo();
            exp.Exportar(vistaListaDiezmo.tabladiezmo);
        } catch (IOException ex) {
            Logger.getLogger(VistaListaMembrecia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
