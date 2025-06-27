/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
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
 * @author RAHUL
 */
public class ControlIglesia extends MouseAdapter implements ActionListener{
    VistaListaLider vistaLiderIglesia=new VistaListaLider();
    LiderDAO ldao;
    Lideriglesia lideriglesia=new Lideriglesia();
    DefaultTableModel tablamodel=new DefaultTableModel();
    int id;
    List<Lideriglesia> lista;
    
    ExportarEnExcel excel;
    
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    public ControlIglesia(VistaListaLider vlm, LiderDAO aO){
        this.vistaLiderIglesia=vlm;
        this.ldao=aO;
        mostrarlista();
        ajustarAnchoColumnas(vistaLiderIglesia.tablaiglesia);
        
        this.vistaLiderIglesia.botonbuscar.addActionListener(this);
        this.vistaLiderIglesia.botonlistar.addActionListener(this);
        this.vistaLiderIglesia.botonreporte.addActionListener(this);
        
        // 👇 Placeholder en el campo de texto de búsqueda
        vistaLiderIglesia.txtbuscar.setText("Buscar por nombres, apellidos y CI");
        vistaLiderIglesia.txtbuscar.setForeground(Color.GRAY);

        vistaLiderIglesia.txtbuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (vistaLiderIglesia.txtbuscar.getText().equals("Buscar por nombres, apellidos y CI")) {
                    vistaLiderIglesia.txtbuscar.setText("");
                    vistaLiderIglesia.txtbuscar.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (vistaLiderIglesia.txtbuscar.getText().trim().isEmpty()) {
                    vistaLiderIglesia.txtbuscar.setText("Buscar por nombres, apellidos y CI");
                    vistaLiderIglesia.txtbuscar.setForeground(Color.GRAY);
                }
            }
        });
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(vistaLiderIglesia.botonreporte==ae.getSource()){
            try {
                exportars();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR EN REPORTAR");
            }
        }else if(vistaLiderIglesia.botonbuscar==ae.getSource()){
             String texto = vistaLiderIglesia.txtbuscar.getText().trim();
             
             // Validación para evitar buscar con el hint
            if (texto.equals("Buscar por nombres, apellidos y CI") || texto.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese Nombres y Apellidos o Número de C.I. para que la Búsqueda sea precisa.");
                return;
            }
            try{
               List<Lideriglesia> resultado = ldao.buscarLideres(texto);
                llenarTablaLider(resultado);

                if (resultado.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No se encontraron líderes con esos datos.");
                }
                
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error al buscar líderes.");
            }
        }else if(vistaLiderIglesia.botonlistar==ae.getSource()){
            try{
                limpiartabla(vistaLiderIglesia.tablaiglesia);
                mostrarlista();
                // Limpia el campo de texto y vuelve a mostrar el hint
                vistaLiderIglesia.txtbuscar.setText("Buscar por nombres, apellidos y CI");
                vistaLiderIglesia.txtbuscar.setForeground(Color.GRAY);
                //vistaLiderIglesia.txtbuscar.setText("");
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error en la busqueda");
            }
        }
    }
    public void mostrarlista(){
        lista=ldao.mostrarlider();
        tablamodel=(DefaultTableModel) vistaLiderIglesia.tablaiglesia.getModel();
        Object obj[]=new Object[7];
        System.out.println("lista Lider");
        for(int i=0;i<lista.size();i++){
            
            obj[0]=lista.get(i).getNombre();
            obj[1]=lista.get(i).getApellidop();
            obj[2]=lista.get(i).getApellidom();
            obj[3]=lista.get(i).getNumdocumento();
            obj[4]=lista.get(i).getCargo();
            obj[5]=sdf.format(lista.get(i).getIniciogestion());
            obj[6]=sdf.format(lista.get(i).getFingestion());
            
            tablamodel.addRow(obj);
        }
        vistaLiderIglesia.tablaiglesia.setModel(tablamodel);
    }
    public void exportars(){
        try {
            excel= new ExportarEnExcel();
            excel.ExportarE(vistaLiderIglesia.tablaiglesia);
        } catch (IOException ex) {
            Logger.getLogger(VistaListaMembrecia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*public void buscar(String buscando){
        if(vistaLiderIglesia.txtbuscar.getText().length()==0){
            JOptionPane.showMessageDialog(null, "INGRESE UN DATO PARA BUSCAR");
        }else{
            tablamodel=ldao.buscar(buscando);
            vistaLiderIglesia.tablaiglesia.setModel(tablamodel);
        }
    }*/
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
    public void llenarTablaLider(List<Lideriglesia> lista) {
        DefaultTableModel modelo = (DefaultTableModel) vistaLiderIglesia.tablaiglesia.getModel(); // ajusta el nombre de la tabla
        modelo.setRowCount(0); // Limpiar la tabla

        for (Lideriglesia l : lista) {
            modelo.addRow(new Object[]{
                //l.getIdlider(),
                l.getNombre(),
                l.getApellidop(),
                l.getApellidom(),
                l.getNumdocumento(),
                l.getCargo(),
                l.getIniciogestion(),
                l.getFingestion()
            });
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
