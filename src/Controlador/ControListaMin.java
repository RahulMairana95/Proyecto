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
 * @author LENOVO
 */
public class ControListaMin extends MouseAdapter implements ActionListener{
    VistaListaLiderMin vistaLiderm=new VistaListaLiderMin();
    MinDAO mdao;
    Minis mi=new Minis();
    DefaultTableModel tablamodel=new DefaultTableModel();
    int id;
    List<Minis> lista;
    
    ExcelExpo exp;
    
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    
    public ControListaMin(VistaListaLiderMin lm, MinDAO dm){
        this.vistaLiderm=lm;
        this.mdao=dm;
        mostrarlista();
        
        this.vistaLiderm.botonbuscar.addActionListener(this);
        this.vistaLiderm.botonlistar.addActionListener(this);
        this.vistaLiderm.botonreporte.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(vistaLiderm.botonreporte==ae.getSource()){
            try {
                exportars();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR EN REPORTAR");
            }
        }else if(vistaLiderm.botonbuscar==ae.getSource()){
            try{
                buscar(vistaLiderm.boxministerio.getSelectedItem().toString().trim());
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error en la busqueda");
            }
        }else if(vistaLiderm.botonlistar==ae.getSource()){
            try{
                limpiartabla(vistaLiderm.tablamin);
                mostrarlista();
                vistaLiderm.boxministerio.setSelectedItem("");
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error en la busqueda");
            }
        }
    }
    
    public void mostrarlista(){
        lista=mdao.mostrar();
        tablamodel=(DefaultTableModel) vistaLiderm.tablamin.getModel();
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
        vistaLiderm.tablamin.setModel(tablamodel);
    }
    public void buscar(String buscando){
        //if(vistaLiderm.boxministerio.getSelectedItem()==0){
            //JOptionPane.showMessageDialog(null, "INGRESE UN DATO PARA BUSCAR");
        //}else{
            tablamodel=mdao.buscarlider(buscando);
            vistaLiderm.tablamin.setModel(tablamodel);
        //}
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
    public void exportars(){
        try {
            exp= new ExcelExpo();
            exp.Exportar(vistaLiderm.tablamin);
        } catch (IOException ex) {
            Logger.getLogger(VistaListaMembrecia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
