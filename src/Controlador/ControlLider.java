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
import java.time.LocalDate;
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
 * @author RAHUL
 */
public class ControlLider extends MouseAdapter implements ActionListener{
    //VistaLider vistaLider=new VistaLider();
    VistaLider vistaLider;
    LiderDAO ldao;
    MembreciaDAO menAO;
    Lideriglesia lideriglesia=new Lideriglesia();
    DefaultTableModel tablamodel=new DefaultTableModel();
    int id;
    List<Lideriglesia> lista;
    Membrecia mem=new Membrecia();
    
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    List<Membrecia> lislider=new ArrayList<>();
    
    ExcelExpo exp;
    ExportarEnExcel excel;
    
    private int idMembreciaSeleccionado = 0;
    
    public ControlLider(VistaLider vl, LiderDAO ld){
        this.vistaLider=vl;
        this.ldao=ld;
        this.menAO= new MembreciaDAO();
        //System.out.println("liderando");
        mostrarlista();
        boxcargo();
        inhabilitar();
        //mostrarNombre();
        ajustarAnchoColumnas(vistaLider.tablalider);
        //listarnombres();
        
        this.vistaLider.botonagregar.addActionListener(this);
        this.vistaLider.botoneditar.addActionListener(this);
        this.vistaLider.botoncancelar.addActionListener(this);
        this.vistaLider.botoneliminar.addActionListener(this);
        this.vistaLider.botonnuevo.addActionListener(this);
        this.vistaLider.botonbuscarmiembros.addActionListener(this);
        
        this.vistaLider.botonreporte.addActionListener(this);
        //BOTONES BUSCAR Y LISTAR
        this.vistaLider.botonbuscar.addActionListener(this);
        this.vistaLider.botonlistar.addActionListener(this);
        
        this.vistaLider.tablalider.addMouseListener(this);
        
        
        // 👇 Placeholder en el campo de texto de búsqueda
        javax.swing.SwingUtilities.invokeLater(() -> {
            vistaLider.txtbuscar.setText("Buscar por nombres, apellidos o CI");
            vistaLider.txtbuscar.setForeground(Color.GRAY);
            vistaLider.botonbuscar.requestFocusInWindow();
        });
        vistaLider.txtbuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (vistaLider.txtbuscar.getText().equals("Buscar por nombres, apellidos o CI")) {
                    vistaLider.txtbuscar.setText("");
                    vistaLider.txtbuscar.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (vistaLider.txtbuscar.getText().trim().isEmpty()) {
                    vistaLider.txtbuscar.setText("Buscar por nombres, apellidos o CI");
                    vistaLider.txtbuscar.setForeground(Color.GRAY);
                }
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(vistaLider.botonagregar==ae.getSource()){
            try {
                agregarNuevos();
                limpiartabla(vistaLider.tablalider);
                mostrarlista();
                limpiarfield();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo agregar");
            }
        }else if(vistaLider.botoneditar==ae.getSource()){
            try {
                modificar();
                limpiartabla(vistaLider.tablalider);
                mostrarlista();
                limpiarfield();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "NO SE MODIFICÓ");
            }
        }else if(vistaLider.botoneliminar==ae.getSource()){
            try {
                eliminarlider();
                limpiartabla(vistaLider.tablalider);
                mostrarlista();
                limpiarfield();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar");
            }
        }else if(vistaLider.botoncancelar==ae.getSource()){
            try {
                limpiarfield();
                limpiarCamposMiembro();
                limpiartabla(vistaLider.tablalider);
                mostrarlista();
                inhabilitar();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo cancelar");
            }
        }else if(vistaLider.botonnuevo==ae.getSource()){
            try {
                limpiarfield();
                habilitar();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "NO SE PUDO LIMPIAR");
            }
        }else if(vistaLider.botonbuscar==ae.getSource()){
            try {
                String texto = vistaLider.txtbuscar.getText().trim();
                // Validación para evitar buscar con el hint
            if (texto.equals("Buscar por nombres, apellidos o CI") || texto.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese Nombres y Apellidos o Número de C.I. para que la Búsqueda sea precisa.");
                return;
            }
                List<Lideriglesia> lista = ldao.buscarLider(texto);
                mostrarTablaLideres(lista); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR AL BUSCAR LIDER");
            }
        }else if(vistaLider.botonlistar==ae.getSource()){
            try{
                vistaLider.txtbuscar.setText("Buscar por nombres, apellidos o CI");
                vistaLider.txtbuscar.setForeground(Color.GRAY);
                limpiartabla(vistaLider.tablalider);
                mostrarlista();
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "ERROR AL RECARGAR");
            }
        }else if(vistaLider.botonreporte==ae.getSource()){
            try{
                exportars();
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "ERROR AL EXPORTAR");
            }
        }else if(vistaLider.botonbuscarmiembros==ae.getSource()){
            try{
                buscarMiembroPorCI();
                vistaLider.textmiembro.setText("");
            }catch (Exception e){
                //JOptionPane.showMessageDialog(null, "ERROR AL EXPORTAR");
            }
        }
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e){
        int fila=vistaLider.tablalider.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA");
        }else{
            clic();
            
            id=lista.get(fila).getIdlider();
            
            String nom=vistaLider.tablalider.getValueAt(fila, 0).toString();
            String apep=vistaLider.tablalider.getValueAt(fila, 1).toString();
            String apem=vistaLider.tablalider.getValueAt(fila, 2).toString();
            String ci=vistaLider.tablalider.getValueAt(fila, 3).toString();
            String tel=vistaLider.tablalider.getValueAt(fila, 4).toString();
            String cargo=vistaLider.tablalider.getValueAt(fila, 5).toString();
            String ini=vistaLider.tablalider.getValueAt(fila, 6).toString();
            String fin=vistaLider.tablalider.getValueAt(fila, 7).toString();
            
            try {
                vistaLider.txtnombre.setText(nom);
                vistaLider.txtpaterno.setText(apep);
                vistaLider.txtmaterno.setText(apem);
                vistaLider.txtdocumento.setText(ci);
                vistaLider.txttelefono.setText(tel);
                
                vistaLider.boxcargo.setSelectedItem(cargo);
                
                vistaLider.fechainicio.setDate(sdf.parse(ini));
                vistaLider.fechafin.setDate(sdf.parse(fin));
            } catch (Exception errr) {
            }
        }
    }
    /*public void mostrarNombre(){
        MembreciaDAO lldao=new MembreciaDAO();
        //List<Lideriglesia> lislider=new ArrayList<>();
        lislider=lldao.listarMembrecia();
        vistaLider.boxnombre.addItem(" "+" "+"Seleccione un nombre");
        for(int i=0;i<lislider.size();i++){
            vistaLider.boxnombre.addItem(lislider.get(i).getIdmembrecia()+" "+lislider.get(i).getNombre()+" "+lislider.get(i).getApellidop()+" "+lislider.get(i).getApellidom());
            
        }    
          
    }*/
    ///Cargar boxcargo
    public void boxcargo(){
        vistaLider.boxcargo.removeAllItems();
        
        vistaLider.boxcargo.addItem("Seleccione un cargo");
        vistaLider.boxcargo.addItem("Pastor");
        vistaLider.boxcargo.addItem("Anciano");
        vistaLider.boxcargo.addItem("Diácono");
        vistaLider.boxcargo.addItem("Diaconisa");
        vistaLider.boxcargo.addItem("Tesorero de Diezmos");
        vistaLider.boxcargo.addItem("Tesorero de Ofrendas");
        vistaLider.boxcargo.addItem("Secretario");
        vistaLider.boxcargo.addItem("Superintendente");
        vistaLider.boxcargo.addItem("Misiones");
        vistaLider.boxcargo.addItem("Evangelismo");
        
    }
    
    public void mostrarlista(){
        lista=ldao.mostrarlider();
        System.out.println(lista.size() + "nooooooooooooooo");
        
        tablamodel=(DefaultTableModel) vistaLider.tablalider.getModel();
        tablamodel.setRowCount(0);
        
        Object obj[]=new Object[8];
        //System.out.println("lista Lider");
        // Verificar si la lista contiene datos
        if (lista != null && !lista.isEmpty()) {
            for (int i = 0; i < lista.size(); i++) {
                obj[0] = lista.get(i).getNombre();
                obj[1] = lista.get(i).getApellidop();
                obj[2] = lista.get(i).getApellidom();
                obj[3] = lista.get(i).getNumdocumento();
                obj[4] = (lista.get(i).getTelefono() == 0) ? "--": lista.get(i).getTelefono();
                obj[5] = lista.get(i).getCargo();
                obj[6] = sdf.format(lista.get(i).getIniciogestion());
                obj[7] = sdf.format(lista.get(i).getFingestion());

                // Agregar la fila a la tabla
                tablamodel.addRow(obj);
            }
        } else {
            System.out.println("No hay datos para mostrar.");
        }

        // Asignar el modelo actualizado a la tabla (aunque ya se asignó, esto asegura que se actualice la vista)
        vistaLider.tablalider.setModel(tablamodel);
    }
    
    
    //////////AGREGAR NUEVO REGISTRO
    public void agregarNuevos() {
        if (vistaLider.boxcargo.getSelectedItem().toString().trim().isEmpty() ||
            vistaLider.fechainicio.getDate() == null ||  // Verificar si la fecha está seleccionada
            vistaLider.fechafin.getDate() == null ||
            idMembreciaSeleccionado == 0) {    // Verificar si la fecha está seleccionada

            JOptionPane.showMessageDialog(null, "DEBE LLENAR TODOS LOS CAMPOS");
        } else {
            /*String tmp = (String) vistaLider.boxnombre.getSelectedItem();
            String[] aux = tmp.split(" ");
            String idmen = aux[0];*/

            lideriglesia.setIdmembrecia(idMembreciaSeleccionado);

            // Datos de la Membresía (puedes descomentar si es necesario)
            // lideriglesia.setNombre(vistaLider.txtnombre.getText());
            // lideriglesia.setApellidos(vistaLider.txtapellidos.getText());
            // lideriglesia.setCi(vistaLider.txtdocumento.getText());

            lideriglesia.setCargo((String) vistaLider.boxcargo.getSelectedItem());

            // Obtener la fecha de inicio
            Calendar calenn = vistaLider.fechainicio.getCalendar();
            int diai = calenn.get(Calendar.DAY_OF_MONTH);
            int mesi = calenn.get(Calendar.MONTH); // Enero es 0, Diciembre es 11
            int yeari = calenn.get(Calendar.YEAR);

            lideriglesia.setIniciogestion(new java.sql.Date(calenn.getTimeInMillis()));  // Utilizando getTimeInMillis()

            // Obtener la fecha de fin
            Calendar calenc = vistaLider.fechafin.getCalendar();
            int diaf = calenc.get(Calendar.DAY_OF_MONTH);
            int mesf = calenc.get(Calendar.MONTH); // Enero es 0, Diciembre es 11
            int yearf = calenc.get(Calendar.YEAR);

            lideriglesia.setFingestion(new java.sql.Date(calenc.getTimeInMillis())); // Utilizando getTimeInMillis()

            // Agregar al DAO
            ldao.agregarl(lideriglesia);
        }
    }
    
    public void modificar() {
        int fila = vistaLider.tablalider.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA");
            return;
        }

        if (vistaLider.boxcargo.getSelectedItem() == null || 
            vistaLider.boxcargo.getSelectedItem().toString().trim().isEmpty() ||
            vistaLider.fechainicio.getCalendar() == null ||
            vistaLider.fechafin.getCalendar() == null) {

            JOptionPane.showMessageDialog(null, "DEBE LLENAR TODOS LOS CAMPOS");
            return;
        }

        try {
            // Obtener ID del líder seleccionado
            int ide = lista.get(fila).getIdlider();
            System.out.println("ID seleccionado: " + ide); // Para depuración
            
            


            String cargo = vistaLider.boxcargo.getSelectedItem().toString().trim();

            // Convertir fechas correctamente
            java.sql.Date fini = obtenerFechaSQL(vistaLider.fechainicio);
            java.sql.Date fin = obtenerFechaSQL(vistaLider.fechafin);

            if (fini == null || fin == null) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar ambas fechas.");
                return;
            }

            // Verificar si la fecha de inicio es posterior a la fecha de fin
            if (fini.after(fin)) {
                JOptionPane.showMessageDialog(null, "La fecha de inicio no puede ser posterior a la fecha de fin.");
                return;
            }

                // Asignar valores al objeto
                Lideriglesia lider = new Lideriglesia();
                lider.setIdlider(ide);
                lider.setCargo(cargo);
                lider.setIniciogestion(fini);
                lider.setFingestion(fin);

                // Llamar a la función modificar y verificar si funcionó
                boolean actualizado = ldao.modificar(lider);

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
        int fila=vistaLider.tablalider.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA PARA ELIMINAR");
        }else{
            ldao.eliminar(id);
            System.out.println("eliminando");
            JOptionPane.showMessageDialog(null, "ELIMINADO EXITOSAMENTE");
        }
    }
    public void eliminarlider() {
        // Obtener la fila seleccionada en la tabla
        int fila = vistaLider.tablalider.getSelectedRow();

        // Verificar si se seleccionó una fila
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "⚠️ SELECCIONE UNA FILA PARA ELIMINAR");
            return; // Salir si no se ha seleccionado ninguna fila
        }

        // Obtener el ID del líder seleccionado (asumiendo que la lista ya está cargada correctamente)
        int ide = lista.get(fila).getIdlider();
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
            boolean eliminado = ldao.eliminar(ide);

            // Si la eliminación fue exitosa
            if (eliminado) {
                JOptionPane.showMessageDialog(null, "¡EL LÍDER SELECCIONADO HA SIDO ELIMINADO!");

                // Actualizar la lista de líderes después de la eliminación
                lista = ldao.mostrarlider(); // Actualizar la lista de líderes
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

    public void mostrarTablaLideres(List<Lideriglesia> lista) {
        DefaultTableModel modelo = (DefaultTableModel) vistaLider.tablalider.getModel();
        modelo.setRowCount(0); // limpiar tabla

        for (Lideriglesia li : lista) {
            String telefonoMostrado = (li.getTelefono() == 0) ? "--" : String.valueOf(li.getTelefono());
            modelo.addRow(new Object[]{
                li.getNombre(),
                li.getApellidop(),
                li.getApellidom(),
                li.getNumdocumento(),
                telefonoMostrado,
                li.getCargo(),
                li.getIniciogestion(),
                li.getFingestion()
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
    public void limpiarfield(){
        vistaLider.txtnombre.setText("");
        vistaLider.txtpaterno.setText("");
        vistaLider.txtmaterno.setText("");
        vistaLider.txtdocumento.setText("");
        vistaLider.txttelefono.setText("");
        vistaLider.boxcargo.setSelectedItem("");
        
        Date fechaactual=new Date(Calendar.getInstance().getTime().getTime());
        vistaLider.fechainicio.setDate(fechaactual);
        vistaLider.fechafin.setDate(fechaactual);
    }
    private void limpiarCamposMiembro() {
        idMembreciaSeleccionado = 0;
        vistaLider.textmiembro.setText("");
        vistaLider.txtnombre.setText("");
        vistaLider.txtpaterno.setText("");
        vistaLider.txtmaterno.setText("");
        vistaLider.txtdocumento.setText("");
        vistaLider.txttelefono.setText("");
    }
    public void exportars(){
        try {
            //exp= new ExcelExpo();
            //exp.Exportar(vistaLider.tablalider);
            excel= new ExportarEnExcel();
            excel.ExportarE(vistaLider.tablalider);
        } catch (IOException ex) {
            Logger.getLogger(VistaListaMembrecia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void inhabilitar(){
        vistaLider.botonagregar.setEnabled(false);
        vistaLider.botoncancelar.setEnabled(false);
        vistaLider.botoneliminar.setEnabled(false);
        vistaLider.botoneditar.setEnabled(false);
        vistaLider.botonreporte.setEnabled(false);
        vistaLider.botonbuscarmiembros.setEnabled(false);
        
        vistaLider.textmiembro.setEnabled(false);
        vistaLider.txtnombre.setEnabled(false);
        vistaLider.txtmaterno.setEnabled(false);
        vistaLider.txtpaterno.setEnabled(false);
        vistaLider.txtdocumento.setEnabled(false);
        vistaLider.txttelefono.setEnabled(false);
        vistaLider.boxcargo.setEnabled(false);
        vistaLider.fechafin.setEnabled(false);
        vistaLider.fechainicio.setEnabled(false);
    }
    public void habilitar(){
        vistaLider.botonagregar.setEnabled(true);
        vistaLider.botoncancelar.setEnabled(true);
        vistaLider.botonbuscarmiembros.setEnabled(true);
        //vistaLider.botoneditar.setEnabled(true);
        //vistaLider.botonreporte.setEnabled(true);
        vistaLider.tablalider.setEnabled(true);
        
        vistaLider.textmiembro.setEnabled(true);
        vistaLider.txtnombre.setEnabled(true);
        vistaLider.txtpaterno.setEnabled(true);
        vistaLider.txtmaterno.setEnabled(true);
        vistaLider.txtdocumento.setEnabled(true);
        vistaLider.txttelefono.setEnabled(true);
        vistaLider.boxcargo.setEnabled(true);
        vistaLider.fechafin.setEnabled(true);
        vistaLider.fechainicio.setEnabled(true);
    }
    public void clic(){
        //vistaLider.botonagregar.setEnabled(true);
        vistaLider.botoncancelar.setEnabled(true);
        vistaLider.botoneliminar.setEnabled(true);
        vistaLider.botoneditar.setEnabled(true);
        vistaLider.botonreporte.setEnabled(true);
        //vistaLider.tablalider.setEnabled(true);
        
        //vistaLider.textmiembro.setEnabled(true);
        vistaLider.txtnombre.setEnabled(true);
        vistaLider.txtpaterno.setEnabled(true);
        vistaLider.txtmaterno.setEnabled(true);
        vistaLider.txtdocumento.setEnabled(true);
        vistaLider.txttelefono.setEnabled(true);
        vistaLider.boxcargo.setEnabled(true);
        vistaLider.fechafin.setEnabled(true);
        vistaLider.fechainicio.setEnabled(true);
    }

    public void actualizarTablaConResultados(List<Lideriglesia> resultados) {
    // Obtener el modelo de la tabla
    DefaultTableModel modelo = (DefaultTableModel) vistaLider.tablalider.getModel();

    // Limpiar la tabla antes de agregar los nuevos resultados
    modelo.setRowCount(0);

    // Recorrer la lista de resultados y agregar cada líder a la tabla
    for (Lideriglesia lider : resultados) {
        // Crear un arreglo con los datos del líder para agregar a la tabla
        Object[] fila = {
            //lider.getIdlider(),            // ID del líder
            lider.getNombre(),             // Nombre del líder
            lider.getApellidop(),          // Apellido paterno
            lider.getApellidom(),          // Apellido materno
            lider.getNumdocumento(),       // Número de documento
            lider.getCargo(),              // Cargo
            lider.getIniciogestion(),      // Fecha de inicio de gestión
            lider.getFingestion()          // Fecha de fin de gestión
        };

        // Agregar la fila a la tabla
        modelo.addRow(fila);
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
    
    public void buscarMiembroPorCI() {
        String ci = vistaLider.textmiembro.getText().trim();
        System.out.println("👉 CI capturado desde la vista: [" + ci + "]");
        System.out.println("👉 menAO en buscarMiembroPorCI: " + menAO);
        if (!ci.isEmpty()) {
            Membrecia mem = menAO.buscarMembreciaPorCI(ci);
            if (mem != null) {
                idMembreciaSeleccionado = mem.getIdmembrecia();

                // Llenar los campos en la vista
                vistaLider.txtnombre.setText(mem.getNombre());
                System.out.println("👉 Nombre seteado: " + vistaLider.txtnombre.getText());
                vistaLider.txtpaterno.setText(mem.getApellidop());
                vistaLider.txtmaterno.setText(mem.getApellidom());
                vistaLider.txtdocumento.setText(mem.getNumdocumento());
                vistaLider.txttelefono.setText(String.valueOf(mem.getTelefono()));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ningún miembro con ese CI");
                limpiarCamposMiembro();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe ingresar un número de carnet");
        }
    }
}
