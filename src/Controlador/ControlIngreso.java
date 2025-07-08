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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
/**
 *
 * @author LENOVO
 */
public class ControlIngreso extends MouseAdapter implements ActionListener{
    
    VistaIngreso vistaIngreso=new VistaIngreso();
    IngresoDAO ingresoDAO;
    ComboItem comboItem;
    MembreciaDAO membreciaDAO;
    int id;
    List<Ingreso> lista;
    List<Membrecia> lislider=new ArrayList<>();
    DefaultTableModel tablamodel=new DefaultTableModel();
    
    ExportarEnExcel excel;
    //SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    
    public ControlIngreso(VistaIngreso vi, IngresoDAO idao){
        this.vistaIngreso=vi;
        this.ingresoDAO=idao;
        mostrarLista();
        cargarComboMembresia();
        //agregarListenerComboMiembro();
        cargarComboLider();
        cargarComboTipoIngreso();
        inhabilitar();
        ajustarAnchoColumnas(vistaIngreso.tablaingreso);
        
        inicializarFechasActuales();
        
        /*vistaIngreso.boxmiembro.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Membrecia seleccionado = (Membrecia) vistaIngreso.boxmiembro.getSelectedItem();
            if (seleccionado != null) {
                System.out.println("Seleccionaste: " + seleccionado.getNombre() + " " + seleccionado.getApellidop());
                // Aquí puedes hacer más cosas si lo necesitas, como llenar otros campos.
            }
        }
        });*/
        //EVENTO DE BOTONES
        this.vistaIngreso.botonregistrar.addActionListener(this);
        this.vistaIngreso.botonnuevo.addActionListener(this);
        this.vistaIngreso.botoncancelar.addActionListener(this);
        this.vistaIngreso.botonmodificar.addActionListener(this);
        this.vistaIngreso.botoneliminar.addActionListener(this);
        this.vistaIngreso.botonexportar.addActionListener(this);
        this.vistaIngreso.botonbuscar.addActionListener(this);
        this.vistaIngreso.botonlistar.addActionListener(this);
        
        
        //EVENTO DE MOUSE EN LA TABLA
        this.vistaIngreso.tablaingreso.addMouseListener(this);
        
        // 👇 Placeholder en el campo de texto de búsqueda
        javax.swing.SwingUtilities.invokeLater(() -> {
            vistaIngreso.txtbuscar.setText("Buscar por nombres o apellidos");
            vistaIngreso.txtbuscar.setForeground(Color.GRAY);
            
            vistaIngreso.botonbuscar.requestFocusInWindow();
        });

        vistaIngreso.txtbuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (vistaIngreso.txtbuscar.getText().equals("Buscar por nombres o apellidos")) {
                    vistaIngreso.txtbuscar.setText("");
                    vistaIngreso.txtbuscar.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (vistaIngreso.txtbuscar.getText().trim().isEmpty()) {
                    vistaIngreso.txtbuscar.setText("Buscar por nombres o apellidos");
                    vistaIngreso.txtbuscar.setForeground(Color.GRAY);
                }
            }
        });
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
            


        //ACCION DE EVENTOS
        if(vistaIngreso.botonregistrar==ae.getSource()){
            try {
                agregarIngreso();
                limpiartabla(vistaIngreso.tablaingreso);
                mostrarLista();
                limpiarCampos();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo agregar");
            }
        }else if(vistaIngreso.botonnuevo==ae.getSource()){
            try {
                limpiarCampos();
                habilitar();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo habilitar");
            }
            
        }else if(vistaIngreso.botoncancelar==ae.getSource()){
            try {
                limpiarCampos();
                limpiartabla(vistaIngreso.tablaingreso);
                mostrarLista();
                inhabilitar();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo cancelar");
            }
        }else if(vistaIngreso.botonmodificar==ae.getSource()){
            try {
                modificarIngreso();
                limpiartabla(vistaIngreso.tablaingreso);
                
                mostrarLista();
                inhabilitar();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo modificar");
            }
        }else if(vistaIngreso.botoneliminar==ae.getSource()){
            try {
                eliminarIngreso();
                limpiartabla(vistaIngreso.tablaingreso);
                
                mostrarLista();
                inhabilitar();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo eliminar");
            }
        }else if(vistaIngreso.botonbuscar==ae.getSource()){
            try {
                String texto = vistaIngreso.txtbuscar.getText().trim();
                // Validación para evitar buscar con el hint
            if (texto.equals("Buscar por nombres o apellidos") || texto.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese Nombres y Apellidos del Tesorero o Miembro para que la Búsqueda sea precisa.");
                return;
            }
                List<Ingreso> listabuscada = ingresoDAO.buscarIngresos(texto);
                llenarTabla(listabuscada);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error al realizar la búsqueda");
                e.printStackTrace();
            }
        }else if(vistaIngreso.botonlistar==ae.getSource()){
            try {
                mostrarLista();
                vistaIngreso.txtbuscar.setText("Buscar por nombres o apellidos");
                vistaIngreso.txtbuscar.setForeground(Color.GRAY);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error al recargar");
            }
        }else if(vistaIngreso.botonexportar==ae.getSource()){
            try {
                exportars();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error al exportar en Excel");
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int fila = vistaIngreso.tablaingreso.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA");
        } else {
            vistaIngreso.botonmodificar.setEnabled(true);
            vistaIngreso.botoneliminar.setEnabled(true);
            vistaIngreso.botonexportar.setEnabled(true);
            
            id = lista.get(fila).getIdingreso();

            String fecha = vistaIngreso.tablaingreso.getValueAt(fila, 0).toString();
            String tipo = vistaIngreso.tablaingreso.getValueAt(fila, 1).toString();
            String monto = vistaIngreso.tablaingreso.getValueAt(fila, 2).toString();
            String des = vistaIngreso.tablaingreso.getValueAt(fila, 3).toString();

            int idMiembro = lista.get(fila).getIdmembrecia();
            int idLider = lista.get(fila).getIdlider();

            try {
                // 1. Fecha
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                vistaIngreso.dateingreso.setDate(sdf.parse(fecha));

                // 2. Tipo, monto y descripción
                vistaIngreso.boxingreso.setSelectedItem(tipo);
                vistaIngreso.txtmonto.setText(monto);
                vistaIngreso.txtdescripcion.setText(des);

                // 3. Recargar combos por si acaso
                cargarComboMembresia(); // 👈 asegúrate que el combo está lleno
                System.out.println("Items en boxmiembro: " + vistaIngreso.boxmiembro.getItemCount());
                // 4. Seleccionar miembro por ID
                seleccionar(vistaIngreso.boxmiembro, idMiembro);
                
                cargarComboLider();
                System.out.println("Items en boxlider: " + vistaIngreso.boxlider.getItemCount());
                // 5. (Opcional) seleccionar líder si tienes comboLider cargado por ID
                seleccionarLider(vistaIngreso.boxlider, idLider); // solo si es necesario

            } catch (Exception er) {
                er.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al cargar los datos: " + er.getMessage());
            }
        }
    }

    private void seleccionar(JComboBox<Membrecia> combo, int idMembrecia) {
        boolean encontrado = false;  // Para verificar si encontramos el miembro
        for (int i = 0; i < combo.getItemCount(); i++) {
            Membrecia m = combo.getItemAt(i);
            System.out.println("Comparando: " + m.getIdmembrecia() + " == " + idMembrecia);  // Verifica los valores
            if (m.getIdmembrecia() == idMembrecia) {
                combo.setSelectedIndex(i);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró el miembro con ID " + idMembrecia);
        }
    }


    private void seleccionarLider(JComboBox<Lideriglesia> combo, int idLider) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            Lideriglesia l = combo.getItemAt(i);
            if (l.getIdlider() == idLider) {
                combo.setSelectedIndex(i);
                return;
            }
        }
    }



    
    public void mostrarLista() {
        lista=ingresoDAO.listarIngresos();
        tablamodel=(DefaultTableModel) vistaIngreso.tablaingreso.getModel();
        tablamodel.setRowCount(0);
        
        Object obj[]=new Object[6];
        
        if (lista != null && !lista.isEmpty()){
            for(int i=0;i<lista.size();i++){
                obj[0]=lista.get(i).getFecha();
                obj[1]=lista.get(i).getTipo_ingreso();
                obj[2]=lista.get(i).getMonto();
                obj[3]=lista.get(i).getDescripcion();
                obj[4]=(lista.get(i).getNombreMiembro() == null || lista.get(i).getNombreMiembro().trim().isEmpty())
                        ? "--"
                        : lista.get(i).getNombreMiembro();
                obj[5]=lista.get(i).getNombreLider();
                
                tablamodel.addRow(obj);
                
            }
        }else{
            System.out.println("No hay datos para mostrar.");
        }
            vistaIngreso.tablaingreso.setModel(tablamodel);
    }
    ///////CARGAR COMBO MEMBRECIA
    public void cargarComboMembresia() {
        MembreciaDAO lldao = new MembreciaDAO();
        List<Membrecia> lista = lldao.listarMembrecia();
        
        System.out.println("Membresías encontradas: " + lista.size());

        vistaIngreso.boxmiembro.removeAllItems(); // Limpia el combo

        // 👉 Agrega opción "Sin nombre"
        Membrecia sinNombre = new Membrecia();
        sinNombre.setIdmembrecia(0);
        sinNombre.setNombre("Sin nombre");
        sinNombre.setApellidop("");
        sinNombre.setApellidom("");
        vistaIngreso.boxmiembro.addItem(sinNombre); // 👈 agregar el objeto

        for (Membrecia m : lista) {
            System.out.println("Agregando miembro: " + m.getNombre());
            vistaIngreso.boxmiembro.addItem(m); // 👈 agregar el objeto
        }
    }
    /////////CARGAR COMO LIDER
    public void cargarComboLider() {
        LiderDAO dao = new LiderDAO();
        List<Lideriglesia> lista = dao.mostrarlider();
        System.out.println("Líderes encontrados: " + lista.size());


        vistaIngreso.boxlider.removeAllItems(); // Limpia el combo

        // Agrega un líder por defecto para forzar la selección
        Lideriglesia liderPorDefecto = new Lideriglesia();
        liderPorDefecto.setIdlider(0);
        liderPorDefecto.setNombre("Selecciona un nombre");
        liderPorDefecto.setApellidop("");
        liderPorDefecto.setApellidom("");
        vistaIngreso.boxlider.addItem(liderPorDefecto);

        for (Lideriglesia l : lista) {
            vistaIngreso.boxlider.addItem(l);
        }
    }
    ////////CARGAR COMBO TIPO
    public void cargarComboTipoIngreso() {
        vistaIngreso.boxingreso.removeAllItems(); // Limpia el combo por si ya tenía algo

        // Agrega el item por defecto (para validar que se seleccione uno)
        vistaIngreso.boxingreso.addItem("Seleccione un tipo"); // index 0
        vistaIngreso.boxingreso.addItem("Diezmo");              // index 1
        vistaIngreso.boxingreso.addItem("Ofrenda");             // index 2
        vistaIngreso.boxingreso.addItem("Donación");
        vistaIngreso.boxingreso.addItem("Otro");
    }

    ///REGISTRAR NUEVO INGRESO
    public void agregarIngreso() {
        // Validar campos obligatorios
        String tipoIngreso = vistaIngreso.boxingreso.getSelectedItem().toString();

        // Validación de campos
        if (vistaIngreso.dateingreso.getDate() == null ||
            vistaIngreso.txtdescripcion.getText().trim().isEmpty() ||
            vistaIngreso.txtmonto.getText().trim().isEmpty() ||
            vistaIngreso.boxingreso.getSelectedIndex() == 0 ||
            vistaIngreso.boxlider.getSelectedIndex() == 0 ||
            (tipoIngreso.equalsIgnoreCase("Diezmo") && vistaIngreso.boxmiembro.getSelectedIndex() == 0)) {

            JOptionPane.showMessageDialog(null, "⚠️ Complete todos los campos obligatorios.");
            return; // Detiene el proceso si hay campos vacíos
        }
        
        // Si pasa la validación, creamos el objeto Ingreso
        Ingreso nuevoIngreso = new Ingreso();
        //System.out.println("BUSCANDO ERROR 100");
        // Asignar los valores a nuevoIngreso
        //nuevoIngreso.setFecha((Date) vistaIngreso.dateingreso.getDate());
        java.util.Date utilDate = vistaIngreso.dateingreso.getDate(); // Obtener la fecha como java.util.Date
        if (utilDate != null) {
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // Convertir a java.sql.Date
            nuevoIngreso.setFecha(sqlDate); // Asignar la fecha correctamente
        } else {
            // Si no hay fecha seleccionada, manejar el caso (por ejemplo, mostrar un mensaje de error)
            JOptionPane.showMessageDialog(null, "⚠️ Seleccione una fecha.");
            return;
        }
        //java.sql.Date fechaDesde = new java.sql.Date(utilDate.getTime());

        // Verifica que esto no sea null
        nuevoIngreso.setDescripcion(vistaIngreso.txtdescripcion.getText());
        
        nuevoIngreso.setMonto(Double.parseDouble(vistaIngreso.txtmonto.getText()));
        
        nuevoIngreso.setTipo_ingreso(tipoIngreso);
        
        // Verifica que el ID de Miembro y Líder estén correctos
        Membrecia miembroSeleccionado = (Membrecia) vistaIngreso.boxmiembro.getSelectedItem();
        //System.out.println("BUSCANDO ERROR 600 " + miembroSeleccionado);
        Lideriglesia liderSeleccionado = (Lideriglesia) vistaIngreso.boxlider.getSelectedItem();
        //System.out.println("BUSCANDO ERROR 700");
        // Asegúrate de que los objetos no sean nulos antes de asignar
        if (miembroSeleccionado != null && liderSeleccionado != null) {
            nuevoIngreso.setIdmembrecia(miembroSeleccionado.getIdmembrecia()); // Miembro
            nuevoIngreso.setIdlider(liderSeleccionado.getIdlider()); // Líder
            
            if(miembroSeleccionado.getNombre().equalsIgnoreCase("Sin nombre")){
                nuevoIngreso.setNombreMiembro("--");
                
            } else{
                nuevoIngreso.setNombreMiembro(
                miembroSeleccionado.getNombre() + " " +
                        miembroSeleccionado.getApellidop() + " " +
                        miembroSeleccionado.getApellidom());
            }
            // Asignar nombre del líder
            nuevoIngreso.setNombreLider(
                liderSeleccionado.getNombre() + " " +
                liderSeleccionado.getApellidop() + " " +
                liderSeleccionado.getApellidom()
            );
        } else {
            JOptionPane.showMessageDialog(null, "⚠️ Miembro o Líder no seleccionado.");
            return;
        }

        // Debugging: imprimir los valores antes de intentar insertar
       /* System.out.println("Fecha: " + nuevoIngreso.getFecha());
        System.out.println("Descripción: " + nuevoIngreso.getDescripcion());
        System.out.println("Monto: " + nuevoIngreso.getMonto());
        System.out.println("Tipo de ingreso: " + nuevoIngreso.getTipo_ingreso());
        System.out.println("ID Miembro: " + nuevoIngreso.getIdmembrecia());
        System.out.println("ID Líder: " + nuevoIngreso.getIdlider());*/

        // Paso 2: Registrar el ingreso en la base de datos
        IngresoDAO ingresoDAO = new IngresoDAO();
        boolean resultado = ingresoDAO.registrarIngreso(nuevoIngreso);

        if (resultado) {
            JOptionPane.showMessageDialog(null, "Ingreso registrado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "⚠️ Error al registrar el ingreso.");
        }
    }
    
    /////////MODIFICAR REGISTRO
    public void modificarIngreso() {
        if (id == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila para modificar");
            return;
        }

        try {
            // 1. Obtener datos de la vista
            Date fecha = new Date(vistaIngreso.dateingreso.getDate().getTime());
            String tipo = vistaIngreso.boxingreso.getSelectedItem().toString();
            double monto = Double.parseDouble(vistaIngreso.txtmonto.getText());
            String descripcion = vistaIngreso.txtdescripcion.getText();

            Membrecia miembro = (Membrecia) vistaIngreso.boxmiembro.getSelectedItem();
            Lideriglesia lider = (Lideriglesia) vistaIngreso.boxlider.getSelectedItem();

            if (miembro == null || lider == null) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un miembro y un líder");
                return;
            }

            // 2. Crear objeto Ingreso con datos actualizados
            Ingreso ingreso = new Ingreso();
            ingreso.setIdingreso(id);
            ingreso.setFecha(fecha);
            ingreso.setTipo_ingreso(tipo);
            ingreso.setMonto(monto);
            ingreso.setDescripcion(descripcion);
            ingreso.setIdmembrecia(miembro.getIdmembrecia());
            ingreso.setIdlider(lider.getIdlider());

            // 3. Llamar al DAO para modificar
            IngresoDAO dao = new IngresoDAO();
            boolean modificado = dao.modificarlista(ingreso);

            // 4. Mostrar resultado
            if (modificado) {
                JOptionPane.showMessageDialog(null, "Ingreso modificado con éxito");
                mostrarLista(); // recarga la tabla del método
                limpiarCampos();  // limpia los campos del formulario
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo modificar el ingreso");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al modificar: " + ex.getMessage());
        }
    }
    
    ///////ELIMINAR REGISTROS
    public void eliminarIngreso() {
        if (id == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar este ingreso?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        IngresoDAO dao = new IngresoDAO();
        boolean eliminado = dao.eliminarIngreso(id);

        if (eliminado) {
            JOptionPane.showMessageDialog(null, "Ingreso eliminado correctamente");
            mostrarLista();  // refresca tabla
            limpiarCampos();   // limpia campos
            id = 0;             // resetea ID seleccionado
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el ingreso");
        }
    }

    public void mostrarTabla(List<Ingreso> lista) {
        DefaultTableModel modelo = (DefaultTableModel) vistaIngreso.tablaingreso.getModel();
        modelo.setRowCount(0); // Limpia la tabla

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (Ingreso i : lista) {
            Object[] fila = new Object[6];
            fila[0] = sdf.format(i.getFecha());           // Fecha
            fila[1] = i.getTipo_ingreso();                // Tipo ingreso
            fila[2] = i.getMonto();                       // Monto
            fila[3] = i.getDescripcion();                 // Descripción
            fila[4] = i.getNombreMiembro();               // Miembro
            fila[5] = i.getNombreLider();                 // Líder

            modelo.addRow(fila); // Añade la fila al modelo
        }

        vistaIngreso.tablaingreso.setModel(modelo); // Asigna el modelo actualizado
    }
    
    public void llenarTabla(List<Ingreso> lista) {
        DefaultTableModel modelo = (DefaultTableModel) vistaIngreso.tablaingreso.getModel();
        modelo.setRowCount(0); // Limpiar tabla

        for (Ingreso i : lista) {
            modelo.addRow(new Object[]{
                i.getFecha(),
                i.getTipo_ingreso(),
                i.getMonto(),
                i.getDescripcion(),
                i.getNombreMiembro(),
                i.getNombreLider()
            });
        }
    }


    private void inicializarFechasActuales() {
        java.util.Date hoy = new java.util.Date();
        vistaIngreso.dateingreso.setDate(hoy);
    }
    

    
    public void limpiarCampos() {
        //vistaIngreso.dateingreso.setDate(null);
        vistaIngreso.txtdescripcion.setText("");
        vistaIngreso.txtmonto.setText("");
        vistaIngreso.boxingreso.setSelectedIndex(0);
        vistaIngreso.boxmiembro.setSelectedIndex(0);
        vistaIngreso.boxlider.setSelectedIndex(0);
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
            //exp= new ExcelExpo();
            //exp.Exportar(vistaLider.tablalider);
            excel= new ExportarEnExcel();
            excel.ExportarE(vistaIngreso.tablaingreso);
        } catch (IOException ex) {
            Logger.getLogger(VistaListaMembrecia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    

    public void inhabilitar(){/// inhabilita los campos
        vistaIngreso.botonregistrar.setEnabled(false);
        vistaIngreso.botoncancelar.setEnabled(false);
        vistaIngreso.botoneliminar.setEnabled(false);
        vistaIngreso.botonexportar.setEnabled(false);
        //vistaIngreso.botonlistar.setEnabled(false);
        vistaIngreso.botonmodificar.setEnabled(false);
        //vistaIngreso.botonbuscar.setEnabled(false);
        
        vistaIngreso.dateingreso.setEnabled(false);
        
        //vistaIngreso.txtbuscar.setEnabled(false);
        vistaIngreso.txtdescripcion.setEnabled(false);
        vistaIngreso.txtmonto.setEnabled(false);
        
        vistaIngreso.boxingreso.setEnabled(false);
        vistaIngreso.boxlider.setEnabled(false);
        vistaIngreso.boxmiembro.setEnabled(false);
    }
    public void habilitar(){/// inhabilita los campos
        vistaIngreso.botonregistrar.setEnabled(true);
        vistaIngreso.botoncancelar.setEnabled(true);
        //vistaIngreso.botoneliminar.setEnabled(true);
        //vistaIngreso.botonexportar.setEnabled(true);
        //vistaIngreso.botonlistar.setEnabled(true);
        //vistaIngreso.botonmodificar.setEnabled(true);
        //vistaIngreso.botonbuscar.setEnabled(true);
        
        vistaIngreso.dateingreso.setEnabled(true);
        
        //vistaIngreso.txtbuscar.setEnabled(true);
        vistaIngreso.txtdescripcion.setEnabled(true);
        vistaIngreso.txtmonto.setEnabled(true);
        
        vistaIngreso.boxingreso.setEnabled(true);
        vistaIngreso.boxlider.setEnabled(true);
        vistaIngreso.boxmiembro.setEnabled(true);
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
