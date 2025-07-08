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
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
public class ControlMin extends MouseAdapter implements ActionListener{
    VistaLiderMin vistaLiderMin=new VistaLiderMin();
    MinDAO mdao;
    MembreciaDAO memDAO;
    Ministerio minis=new Ministerio();
    DefaultTableModel tablamodel=new DefaultTableModel();
    int id;
    List<Ministerio> lista;
    Membrecia mem=new Membrecia();
    
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    List<Membrecia> lislider=new ArrayList<>();
    
    ExportarEnExcel excel;
    
    public ControlMin(VistaLiderMin vl, MinDAO md){
        this.vistaLiderMin=vl;
        this.mdao=md;
        mostrarlista();
        boxministerio();
        boxcargo();
        inhabilitar();
        mostrarNombre();
        ajustarAnchoColumnas(vistaLiderMin.tablamin);
        
        this.vistaLiderMin.botonagregar.addActionListener(this);
        this.vistaLiderMin.botonnuevo.addActionListener(this);
        this.vistaLiderMin.botoneditar.addActionListener(this);
        this.vistaLiderMin.botoncancelar.addActionListener(this);
        this.vistaLiderMin.botoneliminar.addActionListener(this);
        
        this.vistaLiderMin.botonreporte.addActionListener(this);
        //BOTONES BUSCAR Y LISTAR
        this.vistaLiderMin.botonbuscar.addActionListener(this);
        this.vistaLiderMin.botonlistar.addActionListener(this);
        
        this.vistaLiderMin.tablamin.addMouseListener(this);
        
        
        // 👇 Placeholder en el campo de texto de búsqueda
        javax.swing.SwingUtilities.invokeLater(() -> {
            vistaLiderMin.txtbuscar.setText("Buscar por nombres, apellidos o CI");
            vistaLiderMin.txtbuscar.setForeground(Color.GRAY);
            vistaLiderMin.botonbuscar.requestFocusInWindow();
        });
        vistaLiderMin.txtbuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (vistaLiderMin.txtbuscar.getText().equals("Buscar por nombres, apellidos o CI")) {
                    vistaLiderMin.txtbuscar.setText("");
                    vistaLiderMin.txtbuscar.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (vistaLiderMin.txtbuscar.getText().trim().isEmpty()) {
                    vistaLiderMin.txtbuscar.setText("Buscar por nombres, apellidos o CI");
                    vistaLiderMin.txtbuscar.setForeground(Color.GRAY);
                }
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(vistaLiderMin.botonagregar==ae.getSource()){
            try {
                agregarNuevo();
                limpiartabla(vistaLiderMin.tablamin);
                mostrarlista();
                limpiarfield();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo agregar");
            }
        }else if(vistaLiderMin.botoneditar==ae.getSource()){
            try {
                modificar();
                limpiartabla(vistaLiderMin.tablamin);
                mostrarlista();
                limpiarfield();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo modificar");
            }
        }else if(vistaLiderMin.botoneliminar==ae.getSource()){
            try {
                eliminarlider();
                limpiartabla(vistaLiderMin.tablamin);
                mostrarlista();
                limpiarfield();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar");
            }
        }else if(vistaLiderMin.botoncancelar==ae.getSource()){
            try {
                limpiarfield();
                limpiartabla(vistaLiderMin.tablamin);
                mostrarlista();
                inhabilitar();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo cancelar");
            }
        }else if(vistaLiderMin.botonnuevo==ae.getSource()){
            try {
                limpiarfield();
                habilitar();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo limpiar");
            }
        }else if(vistaLiderMin.botonbuscar==ae.getSource()){
            try {
                 String texto = vistaLiderMin.txtbuscar.getText().trim();
                  // Validación para evitar buscar con el hint
            if (texto.equals("Buscar por nombres, apellidos o CI") || texto.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese Nombres y Apellidos o Número de C.I. para que la Búsqueda sea precisa.");
                return;
            }
                List<Ministerio> resultados = mdao.buscarMinisterio(texto);
                mostrarTablaMinisterios(resultados);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR AL BUSCAR LIDER");
            }
        }else if(vistaLiderMin.botonlistar==ae.getSource()){
            try{
                vistaLiderMin.txtbuscar.setText("Buscar por nombres, apellidos o CI");
                vistaLiderMin.txtbuscar.setForeground(Color.GRAY);
                limpiartabla(vistaLiderMin.tablamin);
                mostrarlista();
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "ERROR AL LISTAR");
            }
        }else if(vistaLiderMin.botonreporte==ae.getSource()){
            try{
                exportars();
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "ERROR AL EXPORTAR");
            }
        }
    }
    @Override
    public void mouseClicked(MouseEvent e){
        int fila=vistaLiderMin.tablamin.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA");
        }else{
            vistaLiderMin.botoneditar.setEnabled(true);
            vistaLiderMin.botoneliminar.setEnabled(true);
            vistaLiderMin.botonreporte.setEnabled(true);
            
            id=lista.get(fila).getIdmin();
            
            String nom=vistaLiderMin.tablamin.getValueAt(fila, 0).toString();
            String apep=vistaLiderMin.tablamin.getValueAt(fila, 1).toString();
            String apem=vistaLiderMin.tablamin.getValueAt(fila, 2).toString();
            String ci=vistaLiderMin.tablamin.getValueAt(fila, 3).toString();
            String min=vistaLiderMin.tablamin.getValueAt(fila, 4).toString();
            String cargo=vistaLiderMin.tablamin.getValueAt(fila, 5).toString();
            String ini=vistaLiderMin.tablamin.getValueAt(fila, 6).toString();
            String fin=vistaLiderMin.tablamin.getValueAt(fila, 7).toString();
            
            try {
                vistaLiderMin.txtnombre.setText(nom);
                vistaLiderMin.txtpaterno.setText(apep);
                vistaLiderMin.txtmaterno.setText(apem);
                vistaLiderMin.txtdocumento.setText(ci);
                
                vistaLiderMin.boxministerio.setSelectedItem(min);
                vistaLiderMin.boxcargo.setSelectedItem(cargo);
                
                vistaLiderMin.fechainicio.setDate(sdf.parse(ini));
                vistaLiderMin.fechafin.setDate(sdf.parse(fin));
            } catch (Exception errr) {
            }
        }
    }
    public void mostrarNombre(){
        MembreciaDAO lldao=new MembreciaDAO();
        //List<Lideriglesia> lislider=new ArrayList<>();
        lislider=lldao.listarMembrecia();
        vistaLiderMin.boxnombre.addItem(" "+" "+"Seleccione un nombre");
        for(int i=0;i<lislider.size();i++){
            vistaLiderMin.boxnombre.addItem(lislider.get(i).getIdmembrecia()+" "+lislider.get(i).getNombre()+" "+lislider.get(i).getApellidop()+" "+lislider.get(i).getApellidom());
            
        }    
          
    }
    //Cargar boxcargoMinisterio
    public void boxministerio(){
        vistaLiderMin.boxministerio.removeAllItems();
        
        vistaLiderMin.boxministerio.addItem("Selecione un Ministerio");
        vistaLiderMin.boxministerio.addItem("Ministerio Femenil");
        vistaLiderMin.boxministerio.addItem("Ministerio Juvenil");
        vistaLiderMin.boxministerio.addItem("Ministerio Prejuvenil");
        vistaLiderMin.boxministerio.addItem("Ministerio de Alabanza");
        vistaLiderMin.boxministerio.addItem("Escuela Dominical");
        vistaLiderMin.boxministerio.addItem("Evangelismo y Misiones");
        vistaLiderMin.boxministerio.addItem("Oansa");
        vistaLiderMin.boxministerio.addItem("CDI");
        vistaLiderMin.boxministerio.addItem("Otro");
    }
    //Cargar box cargo
    public void boxcargo(){
        vistaLiderMin.boxcargo.removeAll();
        
        vistaLiderMin.boxcargo.addItem("Seleccione un Cargo");
        vistaLiderMin.boxcargo.addItem("Presidente");
        vistaLiderMin.boxcargo.addItem("Vicepresidente");
        vistaLiderMin.boxcargo.addItem("Secretario");
        vistaLiderMin.boxcargo.addItem("Tesorero");
        vistaLiderMin.boxcargo.addItem("Ministerial");
        vistaLiderMin.boxcargo.addItem("Social");
        vistaLiderMin.boxcargo.addItem("Evangelismo y Misiones");
        vistaLiderMin.boxcargo.addItem("Discipulado");
    }
    public void mostrarlista(){
        lista=mdao.mostrarlidermin();
        tablamodel=(DefaultTableModel) vistaLiderMin.tablamin.getModel();
        tablamodel.setRowCount(0);
        
        Object obj[]=new Object[8];
        //System.out.println("lista Lider");
        
        // Verificar si la lista contiene datos
        if (lista != null && !lista.isEmpty()){
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
            }else {
                System.out.println("No hay datos para mostrar.");
            }
                vistaLiderMin.tablamin.setModel(tablamodel);
    }
    public void agregarNuevo(){
        
        if(vistaLiderMin.boxministerio.getSelectedItem().toString().trim().isEmpty()||
           vistaLiderMin.boxcargo.getSelectedItem().toString().trim().isEmpty()||
           vistaLiderMin.fechainicio.getDate() == null||
           vistaLiderMin.fechafin.getDate() == null){
            
          JOptionPane.showMessageDialog(null,"DEBE LLENAR TODOS LOS CAMPOS");  
        }else{
            
            String tmp=(String) vistaLiderMin.boxnombre.getSelectedItem();
            String [] aux=tmp.split(" ");
            String idmen=aux[0];
            
            minis.setIdmembrecia(Integer.parseInt(idmen));
            //System.err.println(idmen+"miembro num");
            ///////
            minis.setMinisterio((String)vistaLiderMin.boxministerio.getSelectedItem());
            minis.setCargo((String)vistaLiderMin.boxcargo.getSelectedItem());
            
            // Obtener la fecha de inicio
            Calendar calenn = vistaLiderMin.fechainicio.getCalendar();
            int diai = calenn.get(Calendar.DAY_OF_MONTH);
            int mesi = calenn.get(Calendar.MONTH); // Enero es 0, Diciembre es 11
            int yeari = calenn.get(Calendar.YEAR);

            minis.setIniciogestion(new java.sql.Date(calenn.getTimeInMillis()));  // Utilizando getTimeInMillis()
            
            // Obtener la fecha de fin
            Calendar calenc = vistaLiderMin.fechafin.getCalendar();
            int diaf = calenc.get(Calendar.DAY_OF_MONTH);
            int mesf = calenc.get(Calendar.MONTH); // Enero es 0, Diciembre es 11
            int yearf = calenc.get(Calendar.YEAR);

            minis.setFingestion(new java.sql.Date(calenc.getTimeInMillis())); // Utilizando getTimeInMillis()

            // Agregar al DAO
            mdao.agregar(minis);
            
        }
    }
    public void modificar(){
        int fila=vistaLiderMin.tablamin.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA");
            return;
        }
        
        if(vistaLiderMin.boxministerio.getSelectedItem() == null||
           vistaLiderMin.boxministerio.getSelectedItem().toString().trim().isEmpty()||
           vistaLiderMin.boxcargo.getSelectedItem() == null||
           vistaLiderMin.boxcargo.getSelectedItem().toString().trim().isEmpty()||
           vistaLiderMin.fechainicio.getCalendar() == null||
           vistaLiderMin.fechafin.getCalendar() == null){
            
          JOptionPane.showMessageDialog(null,"DEBE LLENAR TODOS LOS CAMPOS");  
          return;
        }
        try {
            // Obtener ID del líder seleccionado
            int ide = lista.get(fila).getIdmin();
            System.out.println("📌 ID seleccionado: " + ide); // Para depuración
            
            

            String ministe = vistaLiderMin.boxministerio.getSelectedItem().toString().trim();
            String cargo = vistaLiderMin.boxcargo.getSelectedItem().toString().trim();

            // Convertir fechas correctamente
            java.sql.Date fini = obtenerFechaSQL(vistaLiderMin.fechainicio);
            java.sql.Date fin = obtenerFechaSQL(vistaLiderMin.fechafin);

            if (fini == null || fin == null) {
                JOptionPane.showMessageDialog(null, "⚠️ Debe seleccionar ambas fechas.");
                return;
            }

            // Verificar si la fecha de inicio es posterior a la fecha de fin
            if (fini.after(fin)) {
                JOptionPane.showMessageDialog(null, "⚠️ La fecha de inicio no puede ser posterior a la fecha de fin.");
                return;
            }

                // Asignar valores al objeto
                Ministerio lider = new Ministerio();
                lider.setIdmin(ide);
                lider.setMinisterio(ministe);
                lider.setCargo(cargo);
                lider.setIniciogestion(fini);
                lider.setFingestion(fin);

                // Llamar a la función modificar y verificar si funcionó
                boolean actualizado = mdao.modificar(lider);

            if (actualizado) {
                JOptionPane.showMessageDialog(null, "✅ ¡Modificación exitosa!");
            } else {
                JOptionPane.showMessageDialog(null, "❌ Error: No se pudo modificar.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "❌ Error al procesar los datos. Verifique la información.");
            e.printStackTrace(); // Para depuración
        }
    }
    
    private java.sql.Date obtenerFechaSQL(com.toedter.calendar.JDateChooser dateChooser) {
        if (dateChooser.getDate() != null) {
            return new java.sql.Date(dateChooser.getDate().getTime()); // Convierte java.util.Date a java.sql.Date
        } else {
            return null; // Si no hay fecha seleccionada, retorna null
        }
    }
    public void eliminar(){
        int fila=vistaLiderMin.tablamin.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA PARA ELIMINAR");
        }else{
            mdao.eliminarlider(id);
            System.out.println("eliminando");
        }
    }
    public void eliminarlider() {
        // Obtener la fila seleccionada en la tabla
        int fila = vistaLiderMin.tablamin.getSelectedRow();

        // Verificar si se seleccionó una fila
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "⚠️ SELECCIONE UNA FILA PARA ELIMINAR");
            return; // Salir si no se ha seleccionado ninguna fila
        }

        // Obtener el ID del líder seleccionado (asumiendo que la lista ya está cargada correctamente)
        int ide = lista.get(fila).getIdmin();
        System.out.println("🆔 ID del líder a eliminar: " + ide);

        // Mostrar un mensaje de confirmación al usuario
        int confirmacion = JOptionPane.showConfirmDialog(
            null, 
            "¿ESTÁ SEGURO DE ELIMINAR?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE  // El tipo de mensaje (opcional, para dar más contexto)
        );

        // Si el usuario confirma, proceder con la eliminación
        if (confirmacion == JOptionPane.YES_OPTION) {
            // Llamar al DAO para eliminar el líder
            boolean eliminado = mdao.eliminarlider(ide);

            // Si la eliminación fue exitosa
            if (eliminado) {
                JOptionPane.showMessageDialog(null, "✅ ¡EL LÍDER SELECCIONADO HA SIDO ELIMINADO!");

                // Actualizar la lista de líderes después de la eliminación
                lista = mdao.mostrarlidermin(); // Actualizar la lista de líderes
                mostrarlista();         // Recargar la tabla con los datos actualizados
            } else {
                // Si hubo un error al eliminar
                JOptionPane.showMessageDialog(null, "❌ Error: No se pudo eliminar el líder.");
            }
        } else {
            // Si el usuario elige no eliminar
            JOptionPane.showMessageDialog(null, "❌ El líder no ha sido eliminado.");
        }
    }
    public void mostrarTablaMinisterios(List<Ministerio> lista) {
        DefaultTableModel modelo = (DefaultTableModel) vistaLiderMin.tablamin.getModel();
        modelo.setRowCount(0);

        for (Ministerio min : lista) {
            modelo.addRow(new Object[]{
                min.getNombre(),
                min.getApellidop(),
                min.getApellidom(),
                min.getNumdocumento(),
                min.getMinisterio(),
                min.getCargo(),
                min.getIniciogestion(),
                min.getFingestion()
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
            JOptionPane.showMessageDialog(null, "Error al limpiar tabla");
        }
    }
    public void exportars(){
        try {
            excel= new ExportarEnExcel();
            excel.ExportarE(vistaLiderMin.tablamin);
        } catch (IOException ex) {
            Logger.getLogger(VistaListaMembrecia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void limpiarfield(){
        vistaLiderMin.txtnombre.setText("");
        vistaLiderMin.txtpaterno.setText("");
        vistaLiderMin.txtmaterno.setText("");
        vistaLiderMin.txtdocumento.setText("");
        vistaLiderMin.boxministerio.setSelectedItem("");
        vistaLiderMin.boxcargo.setSelectedItem("");
        
        Date fechaactual=new Date(Calendar.getInstance().getTime().getTime());
        vistaLiderMin.fechainicio.setDate(fechaactual);
        vistaLiderMin.fechafin.setDate(fechaactual);
    }
    public void inhabilitar(){
        vistaLiderMin.botonagregar.setEnabled(false);
        vistaLiderMin.botoncancelar.setEnabled(false);
        vistaLiderMin.botoneliminar.setEnabled(false);
        vistaLiderMin.botoneditar.setEnabled(false);
        vistaLiderMin.botonreporte.setEnabled(false);
        //vistaLiderMin.tablamin.setEnabled(false);
        
        vistaLiderMin.boxnombre.setEnabled(false);
        vistaLiderMin.txtnombre.setEnabled(false);
        vistaLiderMin.txtpaterno.setEnabled(false);
        vistaLiderMin.txtmaterno.setEnabled(false);
        vistaLiderMin.txtdocumento.setEnabled(false);
        vistaLiderMin.boxministerio.setEnabled(false);
        vistaLiderMin.boxcargo.setEnabled(false);
        vistaLiderMin.fechafin.setEnabled(false);
        vistaLiderMin.fechainicio.setEnabled(false);
    }
    public void habilitar(){
        vistaLiderMin.botonagregar.setEnabled(true);
        vistaLiderMin.botoncancelar.setEnabled(true);
        //vistaLiderMin.botoneliminar.setEnabled(true);
        //vistaLiderMin.botoneditar.setEnabled(true);
        //vistaLiderMin.botonreporte.setEnabled(true);
        //vistaLiderMin.tablamin.setEnabled(true);
        
        vistaLiderMin.boxnombre.setEnabled(true);
        vistaLiderMin.txtnombre.setEnabled(true);
        vistaLiderMin.txtpaterno.setEnabled(true);
        vistaLiderMin.txtmaterno.setEnabled(true);
        vistaLiderMin.txtdocumento.setEnabled(true);
        vistaLiderMin.boxministerio.setEnabled(true);
        vistaLiderMin.boxcargo.setEnabled(true);
        vistaLiderMin.fechafin.setEnabled(true);
        vistaLiderMin.fechainicio.setEnabled(true);
    }
    
    public void actualizarTablaConResultados(List<Ministerio> lista) {
        DefaultTableModel modelo = (DefaultTableModel) vistaLiderMin.tablamin.getModel();
        modelo.setRowCount(0); // Limpiar la tabla

        for (Ministerio m : lista) {
            modelo.addRow(new Object[]{
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
