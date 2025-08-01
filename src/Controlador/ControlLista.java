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
import java.util.ArrayList;
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
public class ControlLista extends MouseAdapter implements ActionListener{
    VistaListaMembrecia vistaListaMembrecia=new VistaListaMembrecia();
    MembreciaDAO mdao;
    Membrecia men=new Membrecia();
    DefaultTableModel tablaModel=new DefaultTableModel();
    int id;
    List<Membrecia> lista;
    
    ExportarEnExcel excel;
    Date fechaactual=new Date(Calendar.getInstance().getTime().getTime());
    public ControlLista(VistaListaMembrecia vlm, MembreciaDAO aO){
        this.vistaListaMembrecia=vlm;
        this.mdao=aO;
        listar();
        fechaACtual();
        ajustarAnchoColumnas(vistaListaMembrecia.tablalistar);
        
        boxestadocivil();
        boxtalentos();
        comboactivo();
        combodones();
        
        //agregarValidacioness();
        
        //EVENTO DE BOTON REPORTAR
        this.vistaListaMembrecia.btnexportar.addActionListener(this);
        this.vistaListaMembrecia.botonbuscar.addActionListener(this);
        this.vistaListaMembrecia.botonfiltrar.addActionListener(this);
        this.vistaListaMembrecia.botonlistar.addActionListener(this);
        this.vistaListaMembrecia.botonhappy.addActionListener(this);
        this.vistaListaMembrecia.btnnacimiento.addActionListener(this);
        this.vistaListaMembrecia.btnbautismo.addActionListener(this);
        
        // 👇 Placeholder en el campo de texto de búsqueda
        javax.swing.SwingUtilities.invokeLater(() -> {
            vistaListaMembrecia.txtbusqueda.setText("Buscar por nombres, apellidos o CI");
            vistaListaMembrecia.txtbusqueda.setForeground(Color.GRAY);
            
            vistaListaMembrecia.botonbuscar.requestFocusInWindow();
        });

        vistaListaMembrecia.txtbusqueda.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (vistaListaMembrecia.txtbusqueda.getText().equals("Buscar por nombres, apellidos o CI")) {
                    vistaListaMembrecia.txtbusqueda.setText("");
                    vistaListaMembrecia.txtbusqueda.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (vistaListaMembrecia.txtbusqueda.getText().trim().isEmpty()) {
                    vistaListaMembrecia.txtbusqueda.setText("Buscar por nombres, apellidos o CI");
                    vistaListaMembrecia.txtbusqueda.setForeground(Color.GRAY);
                }
            }
            
        });
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
            
            buscarmiembros();
            
        }else if(vistaListaMembrecia.botonfiltrar==ae.getSource()){
            
            filtrarMembrecia();
            
        }else if(vistaListaMembrecia.botonlistar==ae.getSource()){
            try{
                limpiartabla(vistaListaMembrecia.tablalistar);
                listar();
                limpiarFiltross();
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error al listar");
            }
        }else if(vistaListaMembrecia.botonhappy==ae.getSource()){
            try{
                buscarCumpleañeros();
                fechaACtual();
            } catch (Exception e){
                e.printStackTrace(); // muestra el error exacto en consola
                JOptionPane.showMessageDialog(null, "Error en la búsqueda: " + e.getMessage());
            }
        }else if(vistaListaMembrecia.btnnacimiento==ae.getSource()){
            try{
                buscarPorFechaNacimiento();
                fechaACtual();
            } catch (Exception e){
                e.printStackTrace(); // muestra el error exacto en consola
                JOptionPane.showMessageDialog(null, "Error en la búsqueda: " + e.getMessage());
            }
        }else if(vistaListaMembrecia.btnbautismo==ae.getSource()){
            try{
                buscarPorFechaBautizo();
                fechaACtual();
            } catch (Exception e){
                e.printStackTrace(); // muestra el error exacto en consola
                JOptionPane.showMessageDialog(null, "Error en la búsqueda: " + e.getMessage());
            }
        }
    }
    
    public void listar(){
        lista=mdao.listarMembrecia();
        tablaModel=(DefaultTableModel) vistaListaMembrecia.tablalistar.getModel();
        Object obj[]=new Object[15];
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
            obj[11]=lista.get(i).getDireccion();
            obj[12]=(lista.get(i).getTelefono()== 0) ? "--" : lista.get(i).getTelefono();
            obj[13]=lista.get(i).getNomreferencia();
            obj[14]=lista.get(i).getNumreferencia();
            
            tablaModel.addRow(obj);
        }
        vistaListaMembrecia.tablalistar.setModel(tablaModel);
    }
    
    public void exportars(){
        try {
            excel= new ExportarEnExcel();
            excel.ExportarE(vistaListaMembrecia.tablalistar);
        } catch (IOException ex) {
            Logger.getLogger(VistaListaMembrecia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void llenarTabla(List<Membrecia> lista) {
        DefaultTableModel modelo = (DefaultTableModel) vistaListaMembrecia.tablalistar.getModel();
        modelo.setRowCount(0); // Limpia la tabla

        for (Membrecia m : lista) {
            String telefonoMostrado = (m.getTelefono() == 0) ? "--" : String.valueOf(m.getTelefono());
            modelo.addRow(new Object[]{
                //m.getIdmembrecia(),
                m.getNombre(),
                m.getApellidop(),
                m.getApellidom(),
                m.getNumdocumento(),
                m.getFechanacimiento(),
                m.getEstadocivil(),
                m.getFechaconversion(),
                m.getFechabautizo(),
                m.getTalentos(),
                m.getDones(),
                m.getActivo(),
                m.getDireccion(),
                telefonoMostrado,
                m.getNomreferencia(),
                m.getNumreferencia()
            });
        }
    }
    public void boxestadocivil(){
        vistaListaMembrecia.boxestado.removeAllItems();
        vistaListaMembrecia.boxestado.addItem("Filtrar por Estado Civil");
        vistaListaMembrecia.boxestado.addItem("Casado");
        vistaListaMembrecia.boxestado.addItem("Soltero");
        vistaListaMembrecia.boxestado.addItem("Viudo");
        vistaListaMembrecia.boxestado.addItem("Viuda");
        vistaListaMembrecia.boxestado.addItem("Concubino");
    }
    public void boxtalentos(){
        vistaListaMembrecia.boxtalentos.removeAllItems();
        vistaListaMembrecia.boxtalentos.addItem("Filtrar por Talentos");
        vistaListaMembrecia.boxtalentos.addItem("Música");
        vistaListaMembrecia.boxtalentos.addItem("Deportivo");
        vistaListaMembrecia.boxtalentos.addItem("Creativo");
        vistaListaMembrecia.boxtalentos.addItem("Gestión de Personal");
        vistaListaMembrecia.boxtalentos.addItem("Organizacional");
        vistaListaMembrecia.boxtalentos.addItem("Gastronomía");
        vistaListaMembrecia.boxtalentos.addItem("Área de Ventas");
        vistaListaMembrecia.boxtalentos.addItem("Manejo de Inversiones");
        vistaListaMembrecia.boxtalentos.addItem("Idiomas");
        vistaListaMembrecia.boxtalentos.addItem("Comunicación");
        vistaListaMembrecia.boxtalentos.addItem("Cientifico");
        vistaListaMembrecia.boxtalentos.addItem("Actuación");
    }
    public void combodones(){
        vistaListaMembrecia.boxdones.removeAllItems();
        
        vistaListaMembrecia.boxdones.addItem("Filtrar por Dones");
        vistaListaMembrecia.boxdones.addItem("Palabra de Sabiduría");
        vistaListaMembrecia.boxdones.addItem("Palabra de Conocimiento");
        vistaListaMembrecia.boxdones.addItem("Fe");
        vistaListaMembrecia.boxdones.addItem("Dones de Sanidad");
        vistaListaMembrecia.boxdones.addItem("Poder de Milagros");
        vistaListaMembrecia.boxdones.addItem("Profecía");
        vistaListaMembrecia.boxdones.addItem("Discernimiento de Espiritus");
        vistaListaMembrecia.boxdones.addItem("Diversas clases de Lenguas");
        vistaListaMembrecia.boxdones.addItem("Interpretación de Lenguas");
        vistaListaMembrecia.boxdones.addItem("Servicio");
        vistaListaMembrecia.boxdones.addItem("Enseñanza");
        vistaListaMembrecia.boxdones.addItem("Exhortación");
        vistaListaMembrecia.boxdones.addItem("Dar");
        vistaListaMembrecia.boxdones.addItem("Liderazgo");
        vistaListaMembrecia.boxdones.addItem("Misericordia");
    }
    
    public void comboactivo(){
        vistaListaMembrecia.boxactivo.removeAllItems();
        
        vistaListaMembrecia.boxactivo.addItem("Filtrar Activos");
        vistaListaMembrecia.boxactivo.addItem("Activo");
        vistaListaMembrecia.boxactivo.addItem("Inactivo");
    }
    public void buscarCumpleañeros() {
        java.util.Date utilDesde = vistaListaMembrecia.datehappyini.getDate();
        java.util.Date utilHasta = vistaListaMembrecia.datehappyfin.getDate();

        if (utilDesde == null || utilHasta == null) {
            JOptionPane.showMessageDialog(vistaListaMembrecia, "Seleccione ambas fechas.");
            return;
        }
        // Llamamos al DAO
        MembreciaDAO dao = new MembreciaDAO();
        List<Membrecia> lista = dao.buscarCumpleanierosPorRango(
                new java.sql.Date(utilDesde.getTime()), 
                new java.sql.Date(utilHasta.getTime()));
         llenarTabla(lista);

        
    }
    public void fechaACtual(){
        Date fechaactual=new Date(Calendar.getInstance().getTime().getTime());
                vistaListaMembrecia.datehappyini.setDate(fechaactual);
                vistaListaMembrecia.datehappyfin.setDate(fechaactual);
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
   
    public void buscarmiembros(){
        try {
            String texto = vistaListaMembrecia.txtbusqueda.getText().trim();
            
            if (texto.equals("Buscar por nombres, apellidos o CI") || texto.isEmpty()){
                JOptionPane.showMessageDialog(null, "Por favor, ingrese Nombres y Apellidos o Número de C.I. para que la Búsqueda sea precisa.");
                return;
            }
                List<Membrecia> resultado = mdao.buscarMembrecia(texto);
                llenarTabla(resultado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la búsqueda.");
            e.printStackTrace();
        }
    }
    public void filtrarMembrecia() {
        String estadoCivil = vistaListaMembrecia.boxestado.getSelectedItem().toString().trim();
        String dones = vistaListaMembrecia.boxdones.getSelectedItem().toString().trim();
        String talentos = vistaListaMembrecia.boxtalentos.getSelectedItem().toString().trim();
        String activo = vistaListaMembrecia.boxactivo.getSelectedItem().toString().trim();
        
        // Validar si no se ha seleccionado ningún filtro
        boolean sinFiltro = estadoCivil.equalsIgnoreCase("Filtrar por Estado Civil") &&
                            dones.equalsIgnoreCase("Filtrar por Dones") &&
                            talentos.equalsIgnoreCase("Filtrar por Talentos") &&
                            activo.equalsIgnoreCase("Filtrar Activos");

        if (sinFiltro) {
            JOptionPane.showMessageDialog(null, "Seleccione al menos una opción para filtrar.");
            return; // Sale del método sin hacer la búsqueda
        }
        // Validar si se seleccionaron filtros reales
            if (estadoCivil.equalsIgnoreCase("Filtrar por Estado Civil")) estadoCivil = "";
            if (dones.equalsIgnoreCase("Filtrar por Dones")) dones = "";
            if (talentos.equalsIgnoreCase("Filtrar por Talentos")) talentos = "";
            if (activo.equalsIgnoreCase("Filtrar Activos")) activo = "";

        List<Membrecia> lista = mdao.filtrarMembreciaAvanzado(estadoCivil, dones, talentos, activo);
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se encontraron resultados con los filtros seleccionados.");
                
            }
        llenarTabla(lista);
    }

    private void limpiarFiltross() {
        vistaListaMembrecia.txtbusqueda.setText("Buscar por nombres, apellidos o CI");
        vistaListaMembrecia.txtbusqueda.setForeground(Color.GRAY);
        vistaListaMembrecia.txtbusqueda.setEnabled(true);

        vistaListaMembrecia.boxestado.setSelectedIndex(0);
        vistaListaMembrecia.boxdones.setSelectedIndex(0);
        vistaListaMembrecia.boxtalentos.setSelectedIndex(0);
        vistaListaMembrecia.boxactivo.setSelectedIndex(0);

    }

    private void buscarPorFechaNacimiento() {
        java.util.Date desde = vistaListaMembrecia.datehappyini.getDate();
        java.util.Date hasta = vistaListaMembrecia.datehappyfin.getDate();

        if (desde == null || hasta == null) {
            JOptionPane.showMessageDialog(null, "Seleccione ambas fechas para buscar.");
            return;
        }
        java.sql.Date fechaDesde = new java.sql.Date(desde.getTime());
        java.sql.Date fechaHasta = new java.sql.Date(hasta.getTime());
        if (desde.after(hasta)) {
            JOptionPane.showMessageDialog(null, "La fecha 'Desde' no puede ser posterior a la fecha 'Hasta'.");
            return;
        }

        List<Membrecia> lista = mdao.buscarPorFechaNacimiento(fechaDesde, fechaHasta);
        llenarTabla(lista);

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron registros en ese rango de fechas.");
        }
    }
    private void buscarPorFechaBautizo() {
        java.util.Date desde = vistaListaMembrecia.datehappyini.getDate();
        java.util.Date hasta = vistaListaMembrecia.datehappyfin.getDate();

        if (desde == null || hasta == null) {
            JOptionPane.showMessageDialog(null, "Seleccione ambas fechas para buscar.");
            return;
        }
        java.sql.Date fechaDesde = new java.sql.Date(desde.getTime());
        java.sql.Date fechaHasta = new java.sql.Date(hasta.getTime());

        if (desde.after(hasta)) {
            JOptionPane.showMessageDialog(null, "La fecha 'Desde' no puede ser posterior a la fecha 'Hasta'.");
            return;
        }

        List<Membrecia> lista = mdao.buscarPorFechaBautizo(fechaDesde, fechaHasta);
        llenarTabla(lista);

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron registros en ese rango de fechas de bautizo.");
        }
    }


}
