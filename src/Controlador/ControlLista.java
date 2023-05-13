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
public class ControlLista extends MouseAdapter implements ActionListener{
    VistaListaMembrecia vistaListaMembrecia=new VistaListaMembrecia();
    MembreciaDAO mdao;
    Membrecia men=new Membrecia();
    DefaultTableModel tablaModel=new DefaultTableModel();
    int id;
    List<Membrecia> lista;
    
    ExcelExpo exp;
    
    public ControlLista(VistaListaMembrecia vlm, MembreciaDAO aO){
        this.vistaListaMembrecia=vlm;
        this.mdao=aO;
        listar();
        
        //EVENTO DE BOTON REPORTAR
        this.vistaListaMembrecia.btnexportar.addActionListener(this);
        this.vistaListaMembrecia.botonbuscar.addActionListener(this);
        this.vistaListaMembrecia.botonlistar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(vistaListaMembrecia.btnexportar==ae.getSource()){
            try {
                exportars();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR EN REPORTAR");
            }
        }else if(vistaListaMembrecia.botonbuscar==ae.getSource()){
            try{
                buscar(vistaListaMembrecia.txtbusqueda.getText());
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error en la busqueda");
            }
        }else if(vistaListaMembrecia.botonlistar==ae.getSource()){
            try{
                limpiartabla(vistaListaMembrecia.tablalistar);
                listar();
                vistaListaMembrecia.txtbusqueda.setText("");
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error en la busqueda");
            }
        }
    }
    
    public void listar(){
        lista=mdao.listarMembrecia();
        tablaModel=(DefaultTableModel) vistaListaMembrecia.tablalistar.getModel();
        Object obj[]=new Object[11];
        for(int i=0;i<lista.size();i++){
            
            obj[0]=lista.get(i).getNombre();
            obj[1]=lista.get(i).getApellidop();
            obj[2]=lista.get(i).getApellidom();
            obj[3]=lista.get(i).getNumdocumento();
            obj[4]=lista.get(i).getFechanacimiento();
            obj[5]=lista.get(i).getEstadocivil();
            obj[6]=lista.get(i).getFechaconversion();
            obj[7]=lista.get(i).getFechabautizo();
            obj[8]=lista.get(i).getTalentos();
            obj[9]=lista.get(i).getDones();
            obj[10]=lista.get(i).getActivo();
            
            tablaModel.addRow(obj);
        }
        vistaListaMembrecia.tablalistar.setModel(tablaModel);
    }
    
    public void exportars(){
        try {
            exp= new ExcelExpo();
            exp.Exportar(vistaListaMembrecia.tablalistar);
        } catch (IOException ex) {
            Logger.getLogger(VistaListaMembrecia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void buscar(String buscr){
        tablaModel=mdao.buscarMiembros(buscr);
        vistaListaMembrecia.tablalistar.setModel(tablaModel);
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
}
