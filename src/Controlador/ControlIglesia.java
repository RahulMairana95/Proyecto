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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
        boxcargo();
        
        //agregarValidacionesLider();
        fechaACtual();
        
        this.vistaLiderIglesia.botonbuscar.addActionListener(this);
        this.vistaLiderIglesia.botonfiltrar.addActionListener(this);
        this.vistaLiderIglesia.botonlistar.addActionListener(this);
        this.vistaLiderIglesia.botonreporte.addActionListener(this);
        this.vistaLiderIglesia.botondesde.addActionListener(this);
        this.vistaLiderIglesia.botonhasta.addActionListener(this);
        
        // 👇 Placeholder en el campo de texto de búsqueda
        javax.swing.SwingUtilities.invokeLater(() -> {
            vistaLiderIglesia.txtbuscar.setText("Buscar líderes por nombres, apellidos o CI");
            vistaLiderIglesia.txtbuscar.setForeground(Color.GRAY);
            
            vistaLiderIglesia.botonbuscar.requestFocusInWindow();
        });

        vistaLiderIglesia.txtbuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (vistaLiderIglesia.txtbuscar.getText().equals("Buscar líderes por nombres, apellidos o CI")) {
                    vistaLiderIglesia.txtbuscar.setText("");
                    vistaLiderIglesia.txtbuscar.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (vistaLiderIglesia.txtbuscar.getText().trim().isEmpty()) {
                    vistaLiderIglesia.txtbuscar.setText("Buscar líderes por nombres, apellidos o CI");
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
            buscarlideres();
        }else if(vistaLiderIglesia.botonlistar==ae.getSource()){
            try{
                limpiartabla(vistaLiderIglesia.tablaiglesia);
                mostrarlista();
                limpiarFiltros();
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error en la busqueda");
            }
        }else if(vistaLiderIglesia.botondesde==ae.getSource()){
            buscarInicio();
            fechaACtual();
        }else if(vistaLiderIglesia.botonhasta==ae.getSource()){
            buscarFin();
            fechaACtual();
        }else if(vistaLiderIglesia.botonfiltrar==ae.getSource()){
            filltrarcargos();
        }
    }
    public void mostrarlista(){
        lista=ldao.mostrarlider();
        tablamodel=(DefaultTableModel) vistaLiderIglesia.tablaiglesia.getModel();
        Object obj[]=new Object[8];
        System.out.println("lista Lider");
        for(int i=0;i<lista.size();i++){
            
            obj[0]=lista.get(i).getNombre();
            obj[1]=lista.get(i).getApellidop();
            obj[2]=lista.get(i).getApellidom();
            obj[3]=lista.get(i).getNumdocumento();
            obj[4]=(lista.get(i).getTelefono() == 0) ? "--" : lista.get(i).getTelefono();
            obj[5]=lista.get(i).getCargo();
            obj[6]=sdf.format(lista.get(i).getIniciogestion());
            obj[7]=sdf.format(lista.get(i).getFingestion());
            
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
            String telefonoMostrado = (l.getTelefono() == 0) ? "--" : String.valueOf(l.getTelefono());
            modelo.addRow(new Object[]{
                //l.getIdlider(),
                l.getNombre(),
                l.getApellidop(),
                l.getApellidom(),
                l.getNumdocumento(),
                telefonoMostrado,
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
    ///Cargar boxcargo
    public void boxcargo(){
        vistaLiderIglesia.boxcargos.removeAllItems();
        
        vistaLiderIglesia.boxcargos.addItem("Filtrar por Cargo");
        vistaLiderIglesia.boxcargos.addItem("Pastor");
        vistaLiderIglesia.boxcargos.addItem("Anciano");
        vistaLiderIglesia.boxcargos.addItem("Diácono");
        vistaLiderIglesia.boxcargos.addItem("Diaconisa");
        vistaLiderIglesia.boxcargos.addItem("Tesorero de Diezmos");
        vistaLiderIglesia.boxcargos.addItem("Tesorero de Ofrendas");
        vistaLiderIglesia.boxcargos.addItem("Secretario");
        vistaLiderIglesia.boxcargos.addItem("Superintendente");
        vistaLiderIglesia.boxcargos.addItem("Misiones");
        vistaLiderIglesia.boxcargos.addItem("Evangelismo");
        
    }
    public void buscarlideres(){
        try {
            String texto = vistaLiderIglesia.txtbuscar.getText().trim();
            if (texto.equals("Buscar líderes por nombres, apellidos o CI") || texto.isEmpty()){
                JOptionPane.showMessageDialog(null, "Por favor, ingrese Nombres, Apellidos o Número de C.I. para que la Búsqueda sea precisa.");
                return;
            }
                List<Lideriglesia> resultado = ldao.buscarLideres(texto);
                llenarTablaLider(resultado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la búsqueda.");
            e.printStackTrace();
        }
    }
    public void filltrarcargos(){
        try {
            String cargo = vistaLiderIglesia.boxcargos.getSelectedItem().toString();
            int filtrosUsados = 0;
            if (!cargo.equalsIgnoreCase("Filtrar por Cargo")) {
                filtrosUsados++;
            }
            if (filtrosUsados == 0) {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione un cargo para filtrar.");
                return;
            }
            List<Lideriglesia> lista = null;

            if (!cargo.equalsIgnoreCase("Filtrar por Cargo")) {
                lista = ldao.buscarPorCargo(cargo);
            }
            llenarTablaLider(lista);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al filtrar cargos");
            e.printStackTrace();
        }
    }
    public void ejecutarBusquedaLideres() {
        try {
            String texto = vistaLiderIglesia.txtbuscar.getText().trim();
            String cargo = vistaLiderIglesia.boxcargos.getSelectedItem().toString();

            int filtrosUsados = 0;

            if (!texto.isEmpty() && !texto.equalsIgnoreCase("Ingrese Nombres, Apellidos o CI")) {
                filtrosUsados++;
            }

            if (!cargo.equalsIgnoreCase("Filtrar por Cargo")) {
                filtrosUsados++;
            }

            if (filtrosUsados == 0) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese nombres o CI, o seleccione un cargo para buscar.");
                return;
            }

            if (filtrosUsados > 1) {
                JOptionPane.showMessageDialog(null, "Por favor, use solo un filtro a la vez.");
                limpiarFiltros(); // si tienes un método para limpiar texto y combo
                return;
            }

            List<Lideriglesia> lista = null;

            if (!cargo.equalsIgnoreCase("Filtrar por Cargo")) {
                lista = ldao.buscarPorCargo(cargo);
            } else if (!texto.isEmpty() && !texto.equalsIgnoreCase("Ingrese Nombres, Apellidos o CI")) {
                lista = ldao.buscarLideres(texto);
            }

            llenarTablaLider(lista);

            if (lista == null || lista.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se encontraron líderes con esos datos.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la búsqueda.");
            e.printStackTrace();
        }
    }
    private void agregarValidacionesLider() {
        // Validar cuando se escribe en el campo de texto
        vistaLiderIglesia.txtbuscar.getDocument().addDocumentListener(new DocumentListener() {
            private void validarTexto() {
                String texto = vistaLiderIglesia.txtbuscar.getText().trim();
                boolean activo = !texto.isEmpty() && !texto.equalsIgnoreCase("Ingrese Nombres, Apellidos o CI");

                // Desactivar boxcargo si se está escribiendo texto
                vistaLiderIglesia.boxcargos.setEnabled(!activo);
            }

            public void insertUpdate(DocumentEvent e) { validarTexto(); }
            public void removeUpdate(DocumentEvent e) { validarTexto(); }
            public void changedUpdate(DocumentEvent e) { validarTexto(); }
        });

        // Validar cuando se selecciona un cargo en el combo
        vistaLiderIglesia.boxcargos.addActionListener(e -> {
            String cargoSeleccionado = vistaLiderIglesia.boxcargos.getSelectedItem().toString();
            boolean activo = !cargoSeleccionado.equals("Filtrar por Cargo");

            // Desactiva el campo de texto si hay un cargo seleccionado
            vistaLiderIglesia.txtbuscar.setEnabled(!activo);
        });
    }

    public void limpiarFiltros() {
        vistaLiderIglesia.txtbuscar.setText("Buscar líderes por nombres, apellidos o CI");
        vistaLiderIglesia.txtbuscar.setForeground(Color.GRAY);
        vistaLiderIglesia.boxcargos.setSelectedIndex(0);
    }
    public void fechaACtual(){
        Date fechaactual=new Date(Calendar.getInstance().getTime().getTime());
                vistaLiderIglesia.datedesde.setDate(fechaactual);
                vistaLiderIglesia.datehasta.setDate(fechaactual);
    }
    public void buscarInicio(){
        java.util.Date desde = vistaLiderIglesia.datedesde.getDate();
        java.util.Date hasta = vistaLiderIglesia.datehasta.getDate();

        if (desde == null || hasta == null) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione ambas fechas.");
            return;
        }
        java.sql.Date fechaDesde = new java.sql.Date(desde.getTime());
        java.sql.Date fechaHasta = new java.sql.Date(hasta.getTime());
        if (desde.after(hasta)) {
            JOptionPane.showMessageDialog(null, "La fecha 'Desde' no puede ser mayor que la fecha 'Hasta'.");
            return;
        }

        try {
            List<Lideriglesia> resultado = ldao.buscarPorInicioGestion(fechaDesde, fechaHasta);
            llenarTablaLider(resultado);

            if (resultado.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se encontraron líderes en ese rango de fechas.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar por fecha de inicio de gestión.");
            e.printStackTrace();
        }
    }
    public void buscarFin(){
        java.util.Date desde = vistaLiderIglesia.datedesde.getDate();
        java.util.Date hasta = vistaLiderIglesia.datehasta.getDate();

        if (desde == null || hasta == null) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione ambas fechas.");
            return;
        }
        java.sql.Date fechaDesde = new java.sql.Date(desde.getTime());
        java.sql.Date fechaHasta = new java.sql.Date(hasta.getTime());
        if (desde.after(hasta)) {
            JOptionPane.showMessageDialog(null, "La fecha 'Desde' no puede ser mayor que la fecha 'Hasta'.");
            return;
        }

        try {
            List<Lideriglesia> resultado = ldao.buscarPorFinGestion(fechaDesde, fechaHasta);
            llenarTablaLider(resultado);

            if (resultado.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se encontraron líderes en ese rango de fechas.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar por fecha de fin de gestión.");
            e.printStackTrace();
        }
    }
    
}
