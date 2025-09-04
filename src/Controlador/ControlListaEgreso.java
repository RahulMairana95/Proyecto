/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.*;
import Modelo.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class ControlListaEgreso extends MouseAdapter implements ActionListener{
    VistaListaEgresos vistaListaEgresos = new VistaListaEgresos();
    EgresoDAO edao;
    Egreso egreso = new Egreso();
    DefaultTableModel tablamodel=new DefaultTableModel();
    int id;
    List<Egreso> lista;
    
    
    //ExportarEnExcel excel;
    ExportarIngresosEgresos excel;
    
    
    public ControlListaEgreso(VistaListaEgresos vle, EgresoDAO dAO){
        this.vistaListaEgresos = vle;
        this.edao = dAO;
        
        //mostrarLista();
        listarEgresosDelUltimoMes();
        cargarComboTipo();
        ajustarAnchoColumnas(vistaListaEgresos.tablalistaegreso);
        //inicializarTabla();
        
        inicializarFechasActuales();
        
        this.vistaListaEgresos.botonreporte.addActionListener(this);
        this.vistaListaEgresos.botonlistar.addActionListener(this);
        this.vistaListaEgresos.botonexportar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        //ACCION DE EVENTOS
        if(vistaListaEgresos.botonreporte==ae.getSource()){
            try {
                generarReporte();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo agregar");
            }
        }else if(vistaListaEgresos.botonlistar==ae.getSource()){
            try {
                //mostrarLista();
                listarEgresosDelUltimoMes();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo habilitar");
            } 
        }else if(vistaListaEgresos.botonexportar==ae.getSource()){
            try {
                exportars();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo habilitar");
            } 
        }
    }
    //////// MOSTRAR LISTA DE EGRESOS EN LA TABLA
    /*public void mostrarLista() {
        lista=edao.listarEgresos();
        tablamodel=(DefaultTableModel) vistaListaEgresos.tablalistaegreso.getModel();
        tablamodel.setRowCount(0);
        
        Object obj[]=new Object[7];
        
        if (lista != null && !lista.isEmpty()){
            for(int i=0;i<lista.size();i++){
                obj[0]=lista.get(i).getFecha();
                obj[1]=lista.get(i).getTipo_egreso();
                obj[2]=lista.get(i).getMonto();
                obj[3]=lista.get(i).getDescripcion();
                obj[4]=lista.get(i).getMotivo();
                obj[5]=lista.get(i).getMetodo_de_pago();
                obj[6]=lista.get(i).getNombreLider();
                
                tablamodel.addRow(obj);
                
            }
        }else{
            System.out.println("No hay datos para mostrar.");
        }
            vistaListaEgresos.tablalistaegreso.setModel(tablamodel);
    }*/
    public void listarEgresosDelUltimoMes() {
        // Calcular las fechas (inicio = un mes antes de hoy, fin = hoy)
        LocalDate fechaFin = LocalDate.now();
        LocalDate fechaInicio = fechaFin.minusMonths(1);

        // Asignar las fechas a los JDateChooser
        vistaListaEgresos.datedesde.setDate(java.sql.Date.valueOf(fechaInicio));
        vistaListaEgresos.datehasta.setDate(java.sql.Date.valueOf(fechaFin));
        
        List<Egreso> lista = edao.listarEgresoActual();
        DefaultTableModel modelo = (DefaultTableModel) vistaListaEgresos.tablalistaegreso.getModel();
        modelo.setRowCount(0); // Limpiar tabla

        for (Egreso e : lista) {
            Object[] fila = new Object[]{
                //e.getIdegreso(),
                e.getFecha(),
                e.getTipo_egreso(),
                e.getMonto(),
                e.getDescripcion(),
                e.getMotivo(),
                e.getMetodo_de_pago(),
                e.getNombreLider()
            };
            modelo.addRow(fila);
        }
        calcularTotalEgresos();
    }
    public void cargarComboTipo() {
        vistaListaEgresos.boxtiposelect.removeAllItems(); // Limpia el combo por si ya tenía algo

        // Agrega el item por defecto (para validar que se seleccione uno)
        //vistaEgreso.boxtipo.addItem("Seleccione un tipo"); // index 0
        vistaListaEgresos.boxtiposelect.addItem("Diezmo");              // index 1
        vistaListaEgresos.boxtiposelect.addItem("Ofrenda");             // index 2
        vistaListaEgresos.boxtiposelect.addItem("Donación");
        //vistaListaIngresos.boxtiposelect.addItem("Otro");
    }
    
    private void generarReporte() {
        // Obtener fechas desde la vista (java.util.Date)
        java.util.Date utilFechaDesde = vistaListaEgresos.datedesde.getDate();
        java.util.Date utilFechaHasta = vistaListaEgresos.datehasta.getDate();

        // Validar que no sean nulas
        if (utilFechaDesde == null || utilFechaHasta == null) {
            JOptionPane.showMessageDialog(vistaListaEgresos, "Debe seleccionar ambas fechas.");
            return;
        }

        // Convertir a java.sql.Date
        java.sql.Date fechaDesde = new java.sql.Date(utilFechaDesde.getTime());
        java.sql.Date fechaHasta = new java.sql.Date(utilFechaHasta.getTime());

        // Obtener tipo desde el combo
        Object tipoSeleccionado = vistaListaEgresos.boxtiposelect.getSelectedItem();
        if (tipoSeleccionado == null) {
            JOptionPane.showMessageDialog(vistaListaEgresos, "Debe seleccionar un tipo de egreso.");
            return;
        }

        String tipo = tipoSeleccionado.toString();

        // Consultar y llenar tabla
        List<Egreso> lista = edao.buscarEgresosPorFechaYTipo(fechaDesde, fechaHasta, tipo);
        llenarTablaReporte(lista);
    }

    
    private void llenarTablaReporte(List<Egreso> lista) {
        DefaultTableModel modelo = (DefaultTableModel) vistaListaEgresos.tablalistaegreso.getModel();
        modelo.setRowCount(0); // Limpiar la tabla antes de llenar

        for (Egreso e : lista) {
            modelo.addRow(new Object[]{
                e.getFecha(),
                e.getTipo_egreso(),
                e.getMonto(),
                e.getDescripcion(),
                e.getMotivo(),
                e.getMetodo_de_pago(),
                e.getNombreLider()
            });
        }
        calcularTotalEgresos();
    }
    
    private void inicializarTabla() {
        DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"Fecha", "Tipo", "Monto", "Descripción", "Motivo", "Pago", "Líder"}, 0
        );
        vistaListaEgresos.tablalistaegreso.setModel(modelo);
    }
    
    public void exportars(){
        try {
            //exp= new ExcelExpo();
            //exp.Exportar(vistaLider.tablalider);
            excel= new ExportarIngresosEgresos();
            excel.exportarConTotal(vistaListaEgresos.tablalistaegreso);
        } catch (IOException ex) {
            Logger.getLogger(VistaListaMembrecia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void inicializarFechasActuales() {
        java.util.Date hoy = new java.util.Date();
        //vistaListaEgresos.datedesde.setDate(hoy);
        vistaListaEgresos.datehasta.setDate(hoy);
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
    
    public double calcularTotalEgresos() {
        double total = 0.0;
        DefaultTableModel modelo = (DefaultTableModel) vistaListaEgresos.tablalistaegreso.getModel();

        for (int i = 0; i < modelo.getRowCount(); i++) {
            Object valor = modelo.getValueAt(i, 2); // columna 2 es "Monto"
            if(valor != null){
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

        vistaListaEgresos.txtcalcular.setText("Total: Bs. " + String.format("%.2f", total));
        return total;
    }

}
