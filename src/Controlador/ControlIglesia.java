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
public class ControlIglesia extends MouseAdapter implements ActionListener{
    VistaListaLider vistaLiderIglesia=new VistaListaLider();
    LiderDAO ldao;
    Lideriglesia lideriglesia=new Lideriglesia();
    DefaultTableModel tablamodel=new DefaultTableModel();
    int id;
    List<Lideriglesia> lista;
    
    ExcelExpo exp;
    
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    public ControlIglesia(VistaListaLider vlm, LiderDAO aO){
        this.vistaLiderIglesia=vlm;
        this.ldao=aO;
        mostrarlista();
        
        this.vistaLiderIglesia.botonbuscar1.addActionListener(this);
        this.vistaLiderIglesia.botonlistar.addActionListener(this);
        this.vistaLiderIglesia.botonreporte.addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(vistaLiderIglesia.botonreporte==ae.getSource()){
            try {
                exportars();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR EN REPORTAR");
            }
        }else if(vistaLiderIglesia.botonbuscar1==ae.getSource()){
            try{
                buscar(vistaLiderIglesia.txtbuscar.getText());
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error en la busqueda");
            }
        }else if(vistaLiderIglesia.botonlistar==ae.getSource()){
            try{
                limpiartabla(vistaLiderIglesia.tablaiglesia);
                mostrarlista();
                vistaLiderIglesia.txtbuscar.setText("");
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error en la busqueda");
            }
        }
    }
    public void mostrarlista(){
        lista=ldao.mostrar();
        tablamodel=(DefaultTableModel) vistaLiderIglesia.tablaiglesia.getModel();
        Object obj[]=new Object[6];
        System.out.println("lista Lider");
        for(int i=0;i<lista.size();i++){
            
            obj[0]=lista.get(i).getNombre();
            obj[1]=lista.get(i).getApellidos();
            obj[2]=lista.get(i).getCi();
            obj[3]=lista.get(i).getCargo();
            obj[4]=sdf.format(lista.get(i).getIniciogestion());
            obj[5]=sdf.format(lista.get(i).getFingestion());
            
            tablamodel.addRow(obj);
        }
        vistaLiderIglesia.tablaiglesia.setModel(tablamodel);
    }
    public void exportars(){
        try {
            exp= new ExcelExpo();
            exp.Exportar(vistaLiderIglesia.tablaiglesia);
        } catch (IOException ex) {
            Logger.getLogger(VistaListaMembrecia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void buscar(String buscando){
        if(vistaLiderIglesia.txtbuscar.getText().length()==0){
            JOptionPane.showMessageDialog(null, "INGRESE UN DATO PARA BUSCAR");
        }else{
            tablamodel=ldao.buscarlider(buscando);
            vistaLiderIglesia.tablaiglesia.setModel(tablamodel);
        }
    }
    public void limpiartabla(JTable tabla){
        try {
            int filas=tabla.getRowCount();
            for(int i=0;filas>i;i++){
                tablamodel.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL LIMPIAR TABLA");
        }
    }

    
}
