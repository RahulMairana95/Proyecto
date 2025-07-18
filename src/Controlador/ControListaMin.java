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
        boxcargo();
        ajustarAnchoColumnas(vistaLiderm.tablamin);
        fechaACtual();
        agregarValidacionesLider();
        
        this.vistaLiderm.botonbuscar.addActionListener(this);
        this.vistaLiderm.botonlistar.addActionListener(this);
        this.vistaLiderm.botonreporte.addActionListener(this);
        this.vistaLiderm.botondesde.addActionListener(this);
        this.vistaLiderm.botonhasta.addActionListener(this);
        
        // 👇 Placeholder en el campo de texto de búsqueda
        javax.swing.SwingUtilities.invokeLater(() -> {
        vistaLiderm.txtbuscar.setText("Ingrese Nombres, Apellidos o CI");
        vistaLiderm.txtbuscar.setForeground(Color.GRAY);
        
        vistaLiderm.botonbuscar.requestFocusInWindow();
        });

        vistaLiderm.txtbuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (vistaLiderm.txtbuscar.getText().equals("Ingrese Nombres, Apellidos o CI")) {
                    vistaLiderm.txtbuscar.setText("");
                    vistaLiderm.txtbuscar.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (vistaLiderm.txtbuscar.getText().trim().isEmpty()) {
                    vistaLiderm.txtbuscar.setText("Ingrese Nombres, Apellidos o CI");
                    vistaLiderm.txtbuscar.setForeground(Color.GRAY);
                }
            }
        });
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
            ejecutarBusquedaLideres();
        }else if(vistaLiderm.botonlistar==ae.getSource()){
            try{
                    limpiartabla(vistaLiderm.tablamin);
                    mostrarlista();
                    limpiarFiltros();
                    fechaACtual();
            } catch (Exception e){
                    JOptionPane.showMessageDialog(null, "Error en la busqueda");
            }
        }else if (vistaLiderm.botonhasta == ae.getSource()) {
            buscarFin();
        }else if (vistaLiderm.botondesde == ae.getSource()) {
            buscarInicio();
        }
    }
    
    //Cargar boxcargoMinisterio
    public void boxministerio(){
        vistaLiderm.boxministerio.removeAllItems();
        
        vistaLiderm.boxministerio.addItem("Buscar por Ministerio");
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
    //Cargar box cargo
    public void boxcargo(){
        vistaLiderm.boxcargo.removeAll();
        
        vistaLiderm.boxcargo.addItem("Buscar por Cargo");
        vistaLiderm.boxcargo.addItem("Presidente");
        vistaLiderm.boxcargo.addItem("Vicepresidente");
        vistaLiderm.boxcargo.addItem("Secretario");
        vistaLiderm.boxcargo.addItem("Tesorero");
        vistaLiderm.boxcargo.addItem("Ministerial");
        vistaLiderm.boxcargo.addItem("Social");
        vistaLiderm.boxcargo.addItem("Evangelismo y Misiones");
        vistaLiderm.boxcargo.addItem("Discipulado");
    }
    public void mostrarlista(){
        lista=mdao.mostrarlidermin();
        tablamodel=(DefaultTableModel) vistaLiderm.tablamin.getModel();
        Object obj[]=new Object[9];
        //System.out.println("lista Lider");
        for(int i=0;i<lista.size();i++){
            
            obj[0]=lista.get(i).getNombre();
            obj[1]=lista.get(i).getApellidop();
            obj[2]=lista.get(i).getApellidom();
            obj[3]=lista.get(i).getNumdocumento();
            obj[4]=(lista.get(i).getTelefono() == 0) ? "--" : lista.get(i).getTelefono();
            obj[5]=lista.get(i).getMinisterio();
            obj[6]=lista.get(i).getCargo();
            obj[7]=sdf.format(lista.get(i).getIniciogestion());
            obj[8]=sdf.format(lista.get(i).getFingestion());
            
            tablamodel.addRow(obj);
        }
        vistaLiderm.tablamin.setModel(tablamodel);
    }
    public void llenarTablaMinisterio(List<Ministerio> lista) {
        DefaultTableModel modelo = (DefaultTableModel) vistaLiderm.tablamin.getModel(); // ajusta el nombre si es necesario
        modelo.setRowCount(0);

        for (Ministerio m : lista) {
            String telefonoMostrado = (m.getTelefono() == 0) ? "--" : String.valueOf(m.getTelefono());
            modelo.addRow(new Object[]{
                //m.getIdmin(),
                m.getNombre(),
                m.getApellidop(),
                m.getApellidom(),
                m.getNumdocumento(),
                m.getMinisterio(),
                telefonoMostrado,
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
    public void ejecutarBusquedaLideres() {
        try {
            String texto = vistaLiderm.txtbuscar.getText().trim();
            String ministerio = vistaLiderm.boxministerio.getSelectedItem().toString();
            String cargo = vistaLiderm.boxcargo.getSelectedItem().toString();

            int filtrosUsados = 0;

            if (!texto.isEmpty() && !texto.equalsIgnoreCase("Ingrese Nombres, Apellidos o CI")) {
                filtrosUsados++;
            }

            if (!ministerio.equalsIgnoreCase("Buscar por Ministerio")) {
                filtrosUsados++;
            }
            if (!cargo.equalsIgnoreCase("Buscar por Cargo")) {
                filtrosUsados++;
            }

            if (filtrosUsados == 0) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese nombres o CI, o seleccione un cargo o ministerio para buscar.");
                return;
            }

            if (filtrosUsados > 1) {
                JOptionPane.showMessageDialog(null, "Por favor, use solo un filtro a la vez.");
                limpiarFiltros(); // si tienes un método para limpiar texto y combo
                return;
            }

            List<Ministerio> lista = null;

            if (!cargo.equalsIgnoreCase("Buscar por Cargo")) {
                lista = mdao.buscarPorCargo(cargo);
            } else if (!texto.isEmpty() && !texto.equalsIgnoreCase("Ingrese Nombres, Apellidos o CI")) {
                lista = mdao.buscarPorNombreOApellido(texto);
            } else if (!cargo.equalsIgnoreCase("Buscar por Ministerio")) {
                lista = mdao.buscarPorMinisterio(ministerio);
            }
            llenarTablaMinisterio(lista);

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
        vistaLiderm.txtbuscar.getDocument().addDocumentListener(new DocumentListener() {
            private void validarTexto() {
                String texto = vistaLiderm.txtbuscar.getText().trim();
                boolean activo = !texto.isEmpty() && !texto.equalsIgnoreCase("Ingrese Nombres, Apellidos o CI");

                // Desactivar boxcargo si se está escribiendo texto
                vistaLiderm.boxcargo.setEnabled(!activo);
                vistaLiderm.boxministerio.setEnabled(!activo);
            }

            public void insertUpdate(DocumentEvent e) { validarTexto(); }
            public void removeUpdate(DocumentEvent e) { validarTexto(); }
            public void changedUpdate(DocumentEvent e) { validarTexto(); }
        });

        // Validar cuando se selecciona un cargo en el combo
        vistaLiderm.boxcargo.addActionListener(e -> {
            String cargoSeleccionado = vistaLiderm.boxcargo.getSelectedItem().toString();
            boolean activo = !cargoSeleccionado.equals("Buscar por Cargo");

            // Desactiva el campo de texto si hay un cargo seleccionado
            vistaLiderm.txtbuscar.setEnabled(!activo);
            vistaLiderm.boxministerio.setEnabled(!activo);
        });
        vistaLiderm.boxministerio.addActionListener(e -> {
            String minSeleccionado = vistaLiderm.boxministerio.getSelectedItem().toString();
            boolean activo = !minSeleccionado.equals("Buscar por Ministerio");

            // Desactiva el campo de texto si hay un cargo seleccionado
            vistaLiderm.txtbuscar.setEnabled(!activo);
            vistaLiderm.boxcargo.setEnabled(!activo);
        });
    }
    public void limpiarFiltros() {
        vistaLiderm.txtbuscar.setText("Ingrese Nombres, Apellidos o CI");
        vistaLiderm.txtbuscar.setForeground(Color.GRAY);
        vistaLiderm.txtbuscar.setEnabled(true);
        
        vistaLiderm.boxcargo.setSelectedIndex(0);
        vistaLiderm.boxministerio.setSelectedIndex(0);
        
        vistaLiderm.boxcargo.setEnabled(true);
        vistaLiderm.boxministerio.setEnabled(true);
        
    }
    public void fechaACtual(){
        Date fechaactual=new Date(Calendar.getInstance().getTime().getTime());
                vistaLiderm.datedesde.setDate(fechaactual);
                vistaLiderm.datehasta.setDate(fechaactual);
    }
    
    public void buscarInicio(){
        java.util.Date desde = vistaLiderm.datedesde.getDate();
        java.util.Date hasta = vistaLiderm.datehasta.getDate();

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
            List<Ministerio> resultado = mdao.buscarPorInicioGestion(fechaDesde, fechaHasta);
            llenarTablaMinisterio(resultado);

            if (resultado.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se encontraron líderes en ese rango de fechas.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar por fecha de inicio de gestión.");
            e.printStackTrace();
        }
    }
    public void buscarFin(){
        java.util.Date desde = vistaLiderm.datedesde.getDate();
        java.util.Date hasta = vistaLiderm.datehasta.getDate();

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
            List<Ministerio> resultado = mdao.buscarPorFinGestion(fechaDesde, fechaHasta);
            llenarTablaMinisterio(resultado);

            if (resultado.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se encontraron líderes en ese rango de fechas.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar por fecha de fin de gestión.");
            e.printStackTrace();
        }
    }
}
