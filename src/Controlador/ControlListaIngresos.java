/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import Modelo.*;
import java.awt.Component;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
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
public class ControlListaIngresos extends MouseAdapter implements ActionListener{
    VistaListaIngresos vistaListaIngresos = new VistaListaIngresos();
    IngresoDAO idao;
    Ingreso ingreso = new Ingreso();
    DefaultTableModel tablamodel=new DefaultTableModel();
    int id;
    List<Ingreso> lista;
    
    VistaListaEgresos vistaegresos = new VistaListaEgresos();
    EgresoDAO edao = new EgresoDAO();
    ControlListaEgreso egre;
    
    //ExportarEnExcel excel;
    ExportarIngresosEgresos excel;
    
    public ControlListaIngresos(VistaListaIngresos vli, IngresoDAO dAO){
        this.vistaListaIngresos = vli;
        this.idao = dAO;
        
        this.egre = new ControlListaEgreso(this.vistaegresos, this.edao);
        
        //mostrarLista();
        listarIngresosDelUltimoMes();
        ajustarAnchoColumnas(vistaListaIngresos.tablalistaingreso);
        //System.err.println("listar 100");
        cargarComboTipo();
        //System.err.println("listar 200");
        inicializarFechasActuales();
        //System.err.println("listar 300");
        this.vistaListaIngresos.botonreporte.addActionListener(this);
       // this.vistaListaIngresos.botonfiltrar.addActionListener(this);
        this.vistaListaIngresos.botonlistar.addActionListener(this);
        this.vistaListaIngresos.botonexportar.addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        //ACCION DE EVENTOS
        if(vistaListaIngresos.botonreporte==ae.getSource()){
            try {
                //generarReporte();
                generarReporteIngresos();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo agregar");
            }
        }else if(vistaListaIngresos.botonlistar==ae.getSource()){
            try {
                //mostrarLista();
                listarIngresosDelUltimoMes();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo habilitar");
            } 
        }else if(vistaListaIngresos.botonexportar==ae.getSource()){
            try {
                exportars();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo habilitar");
            } 
        }/*else if(vistaListaIngresos.botonfiltrar==ae.getSource()){
            try {
                //mostrarLista();
                //buscarIngresosPorCI();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo filtrar");
            } 
        }*/
    
    }
    
    /*public void mostrarLista() {
        lista=idao.listarIngresos();
        tablamodel=(DefaultTableModel) vistaListaIngresos.tablalistaingreso.getModel();
        tablamodel.setRowCount(0);
        
        Object obj[]=new Object[6];
        
        if (lista != null && !lista.isEmpty()){
            for(int i=0;i<lista.size();i++){
                obj[0]=lista.get(i).getFecha();
                obj[1]=lista.get(i).getTipo_ingreso();
                obj[2]=lista.get(i).getMonto();
                obj[3]=lista.get(i).getDescripcion();
                obj[4]=lista.get(i).getNombreMiembro();
                obj[5]=lista.get(i).getNombreLider();
                
                tablamodel.addRow(obj);
                
            }
        }else{
            System.out.println("No hay datos para mostrar.");
        }
            vistaListaIngresos.tablalistaingreso.setModel(tablamodel);
    }*/
    
    public void listarIngresosDelUltimoMes() {
        // Calcular las fechas (inicio = un mes antes de hoy, fin = hoy)
        LocalDate fechaFin = LocalDate.now();
        LocalDate fechaInicio = fechaFin.minusMonths(1);

        // Asignar las fechas a los JDateChooser
        vistaListaIngresos.datedesde.setDate(java.sql.Date.valueOf(fechaInicio));
        vistaListaIngresos.datehasta.setDate(java.sql.Date.valueOf(fechaFin));
        //System.err.println("listarIngreso");
        List<Ingreso> lista = idao.listarIngresosActual();
        DefaultTableModel modelo = (DefaultTableModel) vistaListaIngresos.tablalistaingreso.getModel();
        modelo.setRowCount(0); // Limpiar tabla antes de cargar nuevos datos
        //System.err.println("listarIngreso2");
        for (Ingreso i : lista) {
            Object[] fila = new Object[]{
                //i.getIdingreso(),
                i.getFecha(),
                i.getTipo_ingreso(),
                (i.getMonto() != null) ? i.getMonto() : 0.0,
                //i.getMonto(),
                i.getDescripcion(),
                i.getNombreMiembro(),
                i.getNombreLider()
            };
            modelo.addRow(fila);
        }
        //System.err.println("listarIngreso3");
        calcularTotalIngresos();
        //calcularSaldoActual();
    }
    
    public void cargarComboTipo() {
        vistaListaIngresos.boxtiposelect.removeAllItems(); // Limpia el combo por si ya tenía algo

        // Agrega el item por defecto (para validar que se seleccione uno)
        //vistaEgreso.boxtipo.addItem("Seleccione un tipo"); // index 0
        vistaListaIngresos.boxtiposelect.addItem("Diezmo");              // index 1
        vistaListaIngresos.boxtiposelect.addItem("Ofrenda");             // index 2
        vistaListaIngresos.boxtiposelect.addItem("Donación");
        //vistaListaIngresos.boxtiposelect.addItem("Otro");
    }
    
    private void generarReporte() {
        java.util.Date utilFechaDesde = vistaListaIngresos.datedesde.getDate();
        java.util.Date utilFechaHasta = vistaListaIngresos.datehasta.getDate();

        if (utilFechaDesde == null || utilFechaHasta == null) {
            JOptionPane.showMessageDialog(vistaListaIngresos, "Debe seleccionar ambas fechas.");
            return;
        }

        java.sql.Date fechaDesde = new java.sql.Date(utilFechaDesde.getTime());
        java.sql.Date fechaHasta = new java.sql.Date(utilFechaHasta.getTime());

        Object tipoSeleccionado = vistaListaIngresos.boxtiposelect.getSelectedItem();
        if (tipoSeleccionado == null) {
            JOptionPane.showMessageDialog(vistaListaIngresos, "Debe seleccionar un tipo de ingreso.");
            return;
        }

        String tipo = tipoSeleccionado.toString();

        List<Ingreso> lista = idao.buscarIngresosPorFechaYTipo(fechaDesde, fechaHasta, tipo);
        llenarTablaReporte(lista);
    }
    
    public void buscarIngresosPorCI() {
        // Obtener C.I. del miembro
        String ci = vistaListaIngresos.txtbuscarci.getText().trim();

        if (ci.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el número de C.I. del miembro.");
            return;
        }

        // Obtener fechas desde y hasta de los DateChooser
        java.sql.Date fechaDesde = null;
        java.sql.Date fechaHasta = null;

        if (vistaListaIngresos.datedesde.getDate() != null) {
            fechaDesde = new java.sql.Date(vistaListaIngresos.datedesde.getDate().getTime());
        }

        if (vistaListaIngresos.datehasta.getDate() != null) {
            fechaHasta = new java.sql.Date(vistaListaIngresos.datehasta.getDate().getTime());
        }

        // Llamar al método DAO para buscar ingresos de tipo "diezmo"
        List<Ingreso> listaIngresos = idao.buscarIngresosPorCIDiezmo(ci, fechaDesde, fechaHasta);

        // Llenar la tabla con los resultados
        llenarTablaReporte(listaIngresos);

        if (listaIngresos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron ingresos de diezmo para este C.I.");
        }
    }
    public void generarReporteIngresos() {
        // Obtener C.I. del miembro
        String ci = vistaListaIngresos.txtbuscarci.getText().trim();

        // Obtener tipo de ingreso seleccionado
        String tipo = vistaListaIngresos.boxtiposelect.getSelectedItem().toString().trim();

        // Obtener fechas desde y hasta
        java.sql.Date fechaDesde = null;
        java.sql.Date fechaHasta = null;

        if (vistaListaIngresos.datedesde.getDate() != null) {
            fechaDesde = new java.sql.Date(vistaListaIngresos.datedesde.getDate().getTime());
        }

        if (vistaListaIngresos.datehasta.getDate() != null) {
            fechaHasta = new java.sql.Date(vistaListaIngresos.datehasta.getDate().getTime());
        }

        List<Ingreso> listaIngresos;

        if (!ci.isEmpty()) {
            // Validar que solo se pueda buscar por C.I. si es tipo "Diezmo"
            if (!tipo.equalsIgnoreCase("Diezmo")) {
                JOptionPane.showMessageDialog(null, "Solo se puede buscar por C.I. si el tipo de ingreso es Diezmo.");
                // Limpiar campo de C.I.
                vistaListaIngresos.txtbuscarci.setText("");
                return;
                
            }
            
            // Buscar ingresos tipo diezmo del miembro
            listaIngresos = idao.buscarIngresosPorCIDiezmo(ci, fechaDesde, fechaHasta);
        } else {
            // Buscar por tipo y rango de fechas (todos los miembros)
            listaIngresos = idao.buscarIngresosPorFechaYTipo(fechaDesde, fechaHasta, tipo);
        }

        // Limpiar y llenar la tabla
        llenarTablaReporte(listaIngresos);

        if (listaIngresos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron ingresos para los criterios seleccionados.");
        }

        // Limpiar campo de C.I.
        vistaListaIngresos.txtbuscarci.setText("");
    }





    
    private void llenarTablaReporte(List<Ingreso> lista) {
        //System.err.println("listar Tabla");
        DefaultTableModel modelo = (DefaultTableModel) vistaListaIngresos.tablalistaingreso.getModel();
        modelo.setRowCount(0);
        //System.err.println("listar Tabla2");
        for (Ingreso i : lista) {
            modelo.addRow(new Object[]{
                i.getFecha(),
                i.getTipo_ingreso(),
                i.getMonto(),
                //(i.getMonto() != null) ? i.getMonto() : 0.0,
                i.getDescripcion(),
                i.getNombreMiembro(),
                i.getNombreLider()
            });
        }
        calcularTotalIngresos();
        //calcularSaldoActual();
        //System.err.println("listar Tabla3");
    }
    
    public void exportars(){
        try {
            //exp= new ExcelExpo();
            //exp.Exportar(vistaLider.tablalider);
            excel= new ExportarIngresosEgresos();
            excel.exportarConTotal(vistaListaIngresos.tablalistaingreso);
        } catch (IOException ex) {
            Logger.getLogger(VistaListaMembrecia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void inicializarFechasActuales() {
        java.util.Date hoy = new java.util.Date();
        //vistaListaIngresos.datedesde.setDate(hoy);
        vistaListaIngresos.datehasta.setDate(hoy);
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
    
    private double calcularTotalIngresos() {
        double total = 0.0;
        DefaultTableModel modelo = (DefaultTableModel) vistaListaIngresos.tablalistaingreso.getModel();

        for (int i = 0; i < modelo.getRowCount(); i++) {
            Object valor = modelo.getValueAt(i, 2); // columna 2 es "Monto"
            if (valor != null){
                if (valor instanceof Number) {
                    total += ((Number) valor).doubleValue();
                } else {
                    try {
                        total += Double.parseDouble(valor.toString().trim());
                    } catch (NumberFormatException e) {
                        // Ignorar valores inválidos
                    }
                }
            }
        }
        vistaListaIngresos.txtcalcular.setText("Total: Bs. " + String.format("%.2f", total));
        /*DecimalFormat df = new DecimalFormat("#,##0.00");
        vistaListaIngresos.txtcalcular.setText("Total: Bs. " + df.format(total));*/
        return total;
        
    }
    /*private void calcularSaldoActual(){
        double totalIngreso = calcularTotalIngresos();
        
        double totalegreso = this.egre.calcularTotalEgresos();
        double saldo = totalIngreso - totalegreso;
        
        vistaListaIngresos.txtsaldo.setText("Saldo: Bs. " + String.format("%.2f", saldo));
    }*/
    
}
