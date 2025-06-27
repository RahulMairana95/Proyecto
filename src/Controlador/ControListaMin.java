/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.Component;
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
import javax.swing.table.TableCellRenderer;
/**
 *
 * @author LENOVO
 */
public class ControListaMin extends MouseAdapter implements ActionListener{
    VistaListaLiderMin vistaLiderm=new VistaListaLiderMin();
    MinDAO mdao;
    Ministerio mi=new Ministerio();
    DefaultTableModel tablamodel=new DefaultTableModel();
    int id;
    List<Ministerio> lista;
    
    ExportarEnExcel excel;
    
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    
    public ControListaMin(VistaListaLiderMin lm, MinDAO dm){
        this.vistaLiderm=lm;
        this.mdao=dm;
        mostrarlista();
        boxministerio();
        ajustarAnchoColumnas(vistaLiderm.tablamin);
        
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
        }else if (vistaLiderm.botonbuscar == ae.getSource()) {
            String ministerioSeleccionado = vistaLiderm.boxministerio.getSelectedItem().toString();

            if (!ministerioSeleccionado.equalsIgnoreCase("Selecciona un ministerio")) {
                List<Ministerio> lista = mdao.buscarPorMinisterio(ministerioSeleccionado);
                llenarTablaMinisterio(lista);
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona un ministerio.");
        }}else if(vistaLiderm.botonlistar==ae.getSource()){
                try{
                    limpiartabla(vistaLiderm.tablamin);
                    mostrarlista();
                    vistaLiderm.boxministerio.setSelectedItem("");
                } catch (Exception e){
                    JOptionPane.showMessageDialog(null, "Error en la busqueda");
                }
            }
    }
    
    //Cargar boxcargoMinisterio
    public void boxministerio(){
        vistaLiderm.boxministerio.removeAllItems();
        
        vistaLiderm.boxministerio.addItem("Selecione un Ministerio para buscar");
        vistaLiderm.boxministerio.addItem("Ministerio Femenil");
        vistaLiderm.boxministerio.addItem("Ministerio Juvenil");
        vistaLiderm.boxministerio.addItem("Ministerio Prejuvenil");
        vistaLiderm.boxministerio.addItem("Ministerio de Alabanza");
        vistaLiderm.boxministerio.addItem("Escuela Dominical");
        vistaLiderm.boxministerio.addItem("Evangelismo y Misiones");
        vistaLiderm.boxministerio.addItem("Oansa");
        vistaLiderm.boxministerio.addItem("CDI");
        vistaLiderm.boxministerio.addItem("Otro");
    }
    public void mostrarlista(){
        lista=mdao.mostrarlidermin();
        tablamodel=(DefaultTableModel) vistaLiderm.tablamin.getModel();
        Object obj[]=new Object[8];
        //System.out.println("lista Lider");
        for(int i=0;i<lista.size();i++){
            
            obj[0]=lista.get(i).getNombre();
            obj[1]=lista.get(i).getApellidop();
            obj[2]=lista.get(i).getApellidom();
            obj[3]=lista.get(i).getNumdocumento();
            obj[4]=lista.get(i).getMinisterio();
            obj[5]=lista.get(i).getCargo();
            obj[6]=sdf.format(lista.get(i).getIniciogestion());
            obj[7]=sdf.format(lista.get(i).getFingestion());
            
            tablamodel.addRow(obj);
        }
        vistaLiderm.tablamin.setModel(tablamodel);
    }
    public void llenarTablaMinisterio(List<Ministerio> lista) {
        DefaultTableModel modelo = (DefaultTableModel) vistaLiderm.tablamin.getModel(); // ajusta el nombre si es necesario
        modelo.setRowCount(0);

        for (Ministerio m : lista) {
            modelo.addRow(new Object[]{
                //m.getIdmin(),
                m.getNombre(),
                m.getApellidop(),
                m.getApellidom(),
                m.getNumdocumento(),
                m.getMinisterio(),
                m.getCargo(),
                m.getIniciogestion(),
                m.getFingestion()
            });
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
    public void exportars(){
        try {
            excel= new ExportarEnExcel();
            excel.ExportarE(vistaLiderm.tablamin);
        } catch (IOException ex) {
            Logger.getLogger(VistaListaMembrecia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void ajustarAnchoColumnas(JTable tabla) {
    for (int columna = 0; columna < tabla.getColumnCount(); columna++) {
        int ancho = 50; // Ancho mínimo
        for (int fila = 0; fila < tabla.getRowCount(); fila++) {
            TableCellRenderer render = tabla.getCellRenderer(fila, columna);
            Component comp = tabla.prepareRenderer(render, fila, columna);
            ancho = Math.max(comp.getPreferredSize().width + 10, ancho);
        }
        tabla.getColumnModel().getColumn(columna).setPreferredWidth(ancho);
    }
}

}
