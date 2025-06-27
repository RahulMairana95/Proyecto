/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.*;
import Modelo.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author LENOVO
 */
public class ControlEgreso extends MouseAdapter implements ActionListener{
    
    VistaEgreso vistaEgreso=new VistaEgreso();
    EgresoDAO egresoDAO;
    
    int id;
    
    List<Egreso> lista;
    DefaultTableModel tablamodel = new DefaultTableModel();
    
    ExportarEnExcel excel;
    
    public ControlEgreso(VistaEgreso ve, EgresoDAO edao){
        this.vistaEgreso=ve;
        this.egresoDAO=edao;
        
        /////////////LLAMANDO ACCIONES
        mostrarLista();
        cargarComboLider();
        cargarComboTipo();
        cargarComboMotivo();
        cargarComboMetodo();
        inhabilitar();
        ajustarAnchoColumnas(vistaEgreso.tablaegreso);
        
        inicializarFechasActuales();
        
        ///////EVENTO DE BOTONES
        this.vistaEgreso.botonagregar.addActionListener(this);
        this.vistaEgreso.botonnuevo.addActionListener(this);
        this.vistaEgreso.botoneditar.addActionListener(this);
        this.vistaEgreso.botonelminar.addActionListener(this);
        this.vistaEgreso.botoncancelar.addActionListener(this);
        this.vistaEgreso.botonbuscar.addActionListener(this);
        this.vistaEgreso.botonlistar.addActionListener(this);
        this.vistaEgreso.botonexportar.addActionListener(this);
        
        //////EVENTO MOUSECLICKED
        this.vistaEgreso.tablaegreso.addMouseListener(this);
        
        // 👇 Placeholder en el campo de texto de búsqueda
        vistaEgreso.txtbuscar.setText("Buscar por nombres y apellidos");
        vistaEgreso.txtbuscar.setForeground(Color.GRAY);

        vistaEgreso.txtbuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (vistaEgreso.txtbuscar.getText().equals("Buscar por nombres y apellidos")) {
                    vistaEgreso.txtbuscar.setText("");
                    vistaEgreso.txtbuscar.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (vistaEgreso.txtbuscar.getText().trim().isEmpty()) {
                    vistaEgreso.txtbuscar.setText("Buscar por nombres y apellidos");
                    vistaEgreso.txtbuscar.setForeground(Color.GRAY);
                }
            }
        });
        
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        //ACCION DE EVENTOS
        if(vistaEgreso.botonagregar==ae.getSource()){
            try {
                agregarEgreso();
                limpiartabla(vistaEgreso.tablaegreso);
                mostrarLista();
                limpiarCampos();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo agregar");
            }
        }else if(vistaEgreso.botonnuevo==ae.getSource()){
            try {
                limpiarCampos();
                habilitar();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo habilitar");
            }
            
        }else if(vistaEgreso.botoncancelar==ae.getSource()){
            try {
                limpiarCampos();
                limpiartabla(vistaEgreso.tablaegreso);
                mostrarLista();
                inhabilitar();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo cancelar");
            }
        }else if(vistaEgreso.botoneditar==ae.getSource()){
            try {
                modificaregreso();
                limpiartabla(vistaEgreso.tablaegreso);
                
                mostrarLista();
                inhabilitar();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo modificar");
            }
        }else if(vistaEgreso.botonelminar==ae.getSource()){
            try {
                eliminarEgreso();
                limpiartabla(vistaEgreso.tablaegreso);
                
                mostrarLista();
                inhabilitar();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo eliminar");
            }
        }else if(vistaEgreso.botonbuscar==ae.getSource()){
            try {
            String texto = vistaEgreso.txtbuscar.getText().trim();
            // Validación para evitar buscar con el hint
            if (texto.equals("Buscar por nombres y apellidos") || texto.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese Nombres y Apellidos del quien autorizó para que la Búsqueda sea precisa.");
                return;
            }
                List<Egreso> listaFiltrada = egresoDAO.buscarEgresos(texto);
                mostrarEnTabla(listaFiltrada); ////// tu método que llena la tabla
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al buscar egresos");
            }
        }else if(vistaEgreso.botonlistar==ae.getSource()){
            try{
                vistaEgreso.txtbuscar.setText("Buscar por nombres y apellidos");
                vistaEgreso.txtbuscar.setForeground(Color.GRAY);
                
                limpiartabla(vistaEgreso.tablaegreso);
                mostrarLista();  
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "ERROR AL MOSTRAR LISTA");
            }
        }else if(vistaEgreso.botonexportar==ae.getSource()){
            try{
                exportars();
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "ERROR AL MOSTRAR LISTA");
            }
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent em){
        int fila = vistaEgreso.tablaegreso.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA");
        } else {
            id = lista.get(fila).getIdegreso();

            String fecha = vistaEgreso.tablaegreso.getValueAt(fila, 0).toString();
            String tipo = vistaEgreso.tablaegreso.getValueAt(fila, 1).toString();
            String monto = vistaEgreso.tablaegreso.getValueAt(fila, 2).toString();
            String des = vistaEgreso.tablaegreso.getValueAt(fila, 3).toString();
            String mot = vistaEgreso.tablaegreso.getValueAt(fila, 4).toString();
            String met = vistaEgreso.tablaegreso.getValueAt(fila, 5).toString();

            int idLider = lista.get(fila).getIdlider();

            try {
                // 1. Fecha
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                vistaEgreso.datefecha.setDate(sdf.parse(fecha));

                // 2. Tipo, monto y descripción
                vistaEgreso.boxtipo.setSelectedItem(tipo);
                vistaEgreso.txtmonto.setText(monto);
                vistaEgreso.txtdescripcion.setText(des);
                vistaEgreso.boxmotivo.setSelectedItem(mot);
                vistaEgreso.boxpago.setSelectedItem(met);

                
                cargarComboLider();
                System.out.println("Items en boxlider: " + vistaEgreso.boxautorizar.getItemCount());
                // 5. (Opcional) seleccionar líder si tienes comboLider cargado por ID
                seleccionarLider(vistaEgreso.boxautorizar, idLider); // solo si es necesario

            } catch (Exception er) {
                er.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al cargar los datos: " + er.getMessage());
            }
        }
    }
    
    ///////SELECCIONAR LIUDER
    private void seleccionarLider(JComboBox<Lideriglesia> combo, int idLider) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            Lideriglesia l = combo.getItemAt(i);
            if (l.getIdlider() == idLider) {
                combo.setSelectedIndex(i);
                return;
            }
        }
    }
    
    //////// MOSTRAR LISTA DE EGRESOS EN LA TABLA
    public void mostrarLista() {
        lista=egresoDAO.listarEgresos();
        tablamodel=(DefaultTableModel) vistaEgreso.tablaegreso.getModel();
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
            vistaEgreso.tablaegreso.setModel(tablamodel);
    }
    
    /////////CARGAR COMO LIDER
    public void cargarComboLider() {
        LiderDAO dao = new LiderDAO();
        List<Lideriglesia> lista = dao.mostrarlider();
        System.out.println("Líderes encontrados: " + lista.size());


        vistaEgreso.boxautorizar.removeAllItems(); // Limpia el combo

        // Agrega un líder por defecto para forzar la selección
        Lideriglesia liderPorDefecto = new Lideriglesia();
        liderPorDefecto.setIdlider(0);
        liderPorDefecto.setNombre("Selecciona un nombre");
        liderPorDefecto.setApellidop("");
        liderPorDefecto.setApellidom("");
        vistaEgreso.boxautorizar.addItem(liderPorDefecto);

        for (Lideriglesia li : lista) {
            vistaEgreso.boxautorizar.addItem(li);
        }
    }
    ////////CARGAR COMBO TIPO
    public void cargarComboTipo() {
        vistaEgreso.boxtipo.removeAllItems(); // Limpia el combo por si ya tenía algo

        // Agrega el item por defecto (para validar que se seleccione uno)
        vistaEgreso.boxtipo.addItem("Seleccione un tipo"); // index 0
        vistaEgreso.boxtipo.addItem("Diezmo");              // index 1
        vistaEgreso.boxtipo.addItem("Ofrenda");             // index 2
        vistaEgreso.boxtipo.addItem("Donación");
        vistaEgreso.boxtipo.addItem("Otro");
    }
    ////////CARGAR COMBO TIPO
    public void cargarComboMotivo() {
        vistaEgreso.boxmotivo.removeAllItems(); // Limpia el combo por si ya tenía algo

        // Agrega el item por defecto (para validar que se seleccione uno)
        vistaEgreso.boxmotivo.addItem("Seleccione un motivo"); // index 0
        vistaEgreso.boxmotivo.addItem("Compra");              // index 1
        vistaEgreso.boxmotivo.addItem("Pago");             // index 2
        vistaEgreso.boxmotivo.addItem("Servicios");
        vistaEgreso.boxmotivo.addItem("Otro");
    }
    ////////CARGAR COMBO TIPO
    public void cargarComboMetodo() {
        vistaEgreso.boxpago.removeAllItems(); // Limpia el combo por si ya tenía algo

        // Agrega el item por defecto (para validar que se seleccione uno)
        vistaEgreso.boxpago.addItem("Seleccione un método"); // index 0
        vistaEgreso.boxpago.addItem("Efectivo");              // index 1
        vistaEgreso.boxpago.addItem("Transferencia");             // index 2
        vistaEgreso.boxpago.addItem("Cheque");
        vistaEgreso.boxpago.addItem("Otro");
    }
    
    ///REGISTRAR NUEVO EGRESO
    public void agregarEgreso() {
        // Validar campos obligatorios
        String tipoEgreso = vistaEgreso.boxtipo.getSelectedItem().toString();

        if (vistaEgreso.datefecha.getDate() == null ||
            vistaEgreso.txtdescripcion.getText().trim().isEmpty() ||
            vistaEgreso.txtmonto.getText().trim().isEmpty() ||
            vistaEgreso.boxtipo.getSelectedIndex() == 0 ||
            vistaEgreso.boxmotivo.getSelectedIndex() == 0 ||
            vistaEgreso.boxpago.getSelectedIndex() == 0 ||
            vistaEgreso.boxautorizar.getSelectedIndex() == 0) {

            JOptionPane.showMessageDialog(null, "⚠️ Complete todos los campos obligatorios.");
            return;
        }

        // Crear objeto Egreso
        Egreso nuevoEgreso = new Egreso();

        // Asignar fecha
        java.util.Date utilDate = vistaEgreso.datefecha.getDate();
        if (utilDate != null) {
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            nuevoEgreso.setFecha(sqlDate);
        } else {
            JOptionPane.showMessageDialog(null, "⚠️ Seleccione una fecha.");
            return;
        }

        // Validar y asignar monto
        double monto;
        try {
            monto = Double.parseDouble(vistaEgreso.txtmonto.getText());
            if (monto <= 0) {
                JOptionPane.showMessageDialog(null, "⚠️ El monto debe ser mayor a cero.");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "⚠️ Ingrese un monto válido.");
            return;
        }
        nuevoEgreso.setMonto(monto);

        // Asignar otros campos
        nuevoEgreso.setDescripcion(vistaEgreso.txtdescripcion.getText());
        nuevoEgreso.setTipo_egreso(tipoEgreso);
        nuevoEgreso.setMetodo_de_pago(vistaEgreso.boxpago.getSelectedItem().toString());
        nuevoEgreso.setMotivo(vistaEgreso.boxmotivo.getSelectedItem().toString());

        // Asignar líder autorizado
        Lideriglesia liderSeleccionado = (Lideriglesia) vistaEgreso.boxautorizar.getSelectedItem();
        if (liderSeleccionado != null) {
            nuevoEgreso.setIdlider(liderSeleccionado.getIdlider());
        } else {
            JOptionPane.showMessageDialog(null, "⚠️ Líder no seleccionado.");
            return;
        }

        // Registrar en la base de datos
        EgresoDAO egresoDAO = new EgresoDAO();
        boolean resultado = egresoDAO.registrarEgreso(nuevoEgreso);

        if (resultado) {
            JOptionPane.showMessageDialog(null, "Egreso registrado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "⚠️ Error al registrar el egreso.");
        }
    }
    //////MODIFICAR
    public void modificaregreso(){
        if (id == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila para modificar");
            return;
        }

        try {
            // 1. Obtener datos de la vista
            Date fecha = new Date(vistaEgreso.datefecha.getDate().getTime());
            String tipo = vistaEgreso.boxtipo.getSelectedItem().toString();
            double monto = Double.parseDouble(vistaEgreso.txtmonto.getText());
            String descripcion = vistaEgreso.txtdescripcion.getText();
            String mot = vistaEgreso.boxmotivo.getSelectedItem().toString();
            String met = vistaEgreso.boxpago.getSelectedItem().toString();

            Lideriglesia lider = (Lideriglesia) vistaEgreso.boxautorizar.getSelectedItem();

            if (lider == null) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un líder para autorizar");
                return;
            }

            // 2. Crear objeto Ingreso con datos actualizados
            Egreso egreso = new Egreso();
            egreso.setIdegreso(id);
            egreso.setFecha(fecha);
            egreso.setTipo_egreso(tipo);
            egreso.setMonto(monto);
            egreso.setDescripcion(descripcion);
            egreso.setMotivo(mot);
            egreso.setMetodo_de_pago(met);
            egreso.setIdlider(lider.getIdlider());

            // 3. Llamar al DAO para modificar
            EgresoDAO dao = new EgresoDAO();
            boolean modificado = dao.modificarlista(egreso);

            // 4. Mostrar resultado
            if (modificado) {
                JOptionPane.showMessageDialog(null, "Egreso modificado con éxito");
                mostrarLista(); // recarga la tabla si tienes ese método
                limpiarCampos();  // limpia los campos del formulario (opcional)
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo modificar el egreso");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al modificar: " + ex.getMessage());
        }
    }
    ///////ELIMINAR REGISTROS
    public void eliminarEgreso() {
        if (id == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar este egreso?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        EgresoDAO dao = new EgresoDAO();
        boolean eliminado = dao.eliminarEgreso(id);

        if (eliminado) {
            JOptionPane.showMessageDialog(null, "Egreso eliminado correctamente");
            mostrarLista();  // refresca tabla
            limpiarCampos();   // limpia campos
            id = 0;             // resetea ID seleccionado
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el egreso");
        }
    }

    public void actualizarTablaConResultados(List<Egreso> resultados) {
        // Obtener el modelo de la tabla
        DefaultTableModel modelo = (DefaultTableModel) vistaEgreso.tablaegreso.getModel();
        //List<Egreso> resultados = egresoDAO.buscarEgresos(texto);
        
        // Limpiar la tabla antes de agregar los nuevos resultados
        modelo.setRowCount(0);
        
        DecimalFormat df = new DecimalFormat("#,##0.00"); // Para formatear el monto como texto

        for (Egreso e : resultados) {
            String montoFormateado = (e.getMonto() != null) ? df.format(e.getMonto()) : "0.00";
            // Crear un arreglo con los datos de la tabla egreso para agregar a la tabla
            Object[] fila = {
                e.getIdegreso(),
                e.getFecha(),
                e.getTipo_egreso(),
                //df.format(e.getMonto()),
                montoFormateado,
                e.getDescripcion(),
                e.getMotivo(),
                e.getMetodo_de_pago(),
                e.getNombreLider()
            };
            modelo.addRow(fila);
        }
    }
    
    
    
    private void inicializarFechasActuales() {
        java.util.Date hoy = new java.util.Date();
        vistaEgreso.datefecha.setDate(hoy);
    }

    public void mostrarEnTabla(List<Egreso> lista) {
        DefaultTableModel modelo = (DefaultTableModel) vistaEgreso.tablaegreso.getModel();
        modelo.setRowCount(0); // Limpiar la tabla antes de agregar

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
    }
    public void exportars(){
        try {
            //exp= new ExcelExpo();
            //exp.Exportar(vistaLider.tablalider);
            excel= new ExportarEnExcel();
            excel.ExportarE(vistaEgreso.tablaegreso);
        } catch (IOException ex) {
            Logger.getLogger(VistaListaMembrecia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void limpiarCampos() {
        //vistaEgreso.datefecha.setDate(null);
        vistaEgreso.txtdescripcion.setText("");
        vistaEgreso.txtmonto.setText("");
        vistaEgreso.boxtipo.setSelectedIndex(0);
        vistaEgreso.boxmotivo.setSelectedIndex(0);
        vistaEgreso.boxpago.setSelectedIndex(0);
        vistaEgreso.boxautorizar.setSelectedIndex(0);
    }
    
    public void limpiartabla(JTable tabla){
        try {
            int filas=tabla.getRowCount();
            for(int i=0;filas>i;i++){
                tablamodel.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar tabla");
        }
    }
    
    public void inhabilitar(){/// inhabilita los campos
        vistaEgreso.botonagregar.setEnabled(false);
        vistaEgreso.botoncancelar.setEnabled(false);
        vistaEgreso.botonelminar.setEnabled(false);
        vistaEgreso.botonexportar.setEnabled(false);
        vistaEgreso.botonlistar.setEnabled(false);
        vistaEgreso.botoneditar.setEnabled(false);
        vistaEgreso.botonbuscar.setEnabled(false);
        
        vistaEgreso.datefecha.setEnabled(false);
        
        vistaEgreso.txtbuscar.setEnabled(false);
        vistaEgreso.txtdescripcion.setEnabled(false);
        vistaEgreso.txtmonto.setEnabled(false);
        
        vistaEgreso.boxtipo.setEnabled(false);
        vistaEgreso.boxautorizar.setEnabled(false);
        vistaEgreso.boxmotivo.setEnabled(false);
        vistaEgreso.boxpago.setEnabled(false);
    }
    
    public void habilitar(){/// habilita los campos
        vistaEgreso.botonagregar.setEnabled(true);
        vistaEgreso.botoncancelar.setEnabled(true);
        vistaEgreso.botonelminar.setEnabled(true);
        vistaEgreso.botonexportar.setEnabled(true);
        vistaEgreso.botonlistar.setEnabled(true);
        vistaEgreso.botoneditar.setEnabled(true);
        vistaEgreso.botonbuscar.setEnabled(true);
        
        vistaEgreso.datefecha.setEnabled(true);
        
        vistaEgreso.txtbuscar.setEnabled(true);
        vistaEgreso.txtdescripcion.setEnabled(true);
        vistaEgreso.txtmonto.setEnabled(true);
        
        vistaEgreso.boxtipo.setEnabled(true);
        vistaEgreso.boxautorizar.setEnabled(true);
        vistaEgreso.boxmotivo.setEnabled(true);
        vistaEgreso.boxpago.setEnabled(true);
    }
    public void ajustarAnchoColumnas(JTable tabla) {
        for (int columna = 0; columna < tabla.getColumnCount(); columna++) {
            int ancho = 60; // Ancho mínimo
            for (int fila = 0; fila < tabla.getRowCount(); fila++) {
                TableCellRenderer render = tabla.getCellRenderer(fila, columna);
                Component comp = tabla.prepareRenderer(render, fila, columna);
                ancho = Math.max(comp.getPreferredSize().width + 10, ancho);
            }
            tabla.getColumnModel().getColumn(columna).setPreferredWidth(ancho);
        }
    }
}
