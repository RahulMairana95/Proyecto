/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author RAHUL
 */
public class ControlAdministrador extends MouseAdapter implements ActionListener{
    VistaRegistro vistaAdministrador=new VistaRegistro();
    AdministradorDAO adminDAO;
    Administrador adminis=new Administrador();
    DefaultTableModel tablamodel=new DefaultTableModel();
    
    LiderDAO ldao;
    
    List<Administrador> lista;
    int id;
    
    private int idLiderSeleccionado = 0;

    List<Lideriglesia> lisusua=new ArrayList<>();
    
    public ControlAdministrador(VistaRegistro vr, AdministradorDAO adao){
        System.out.println("listando admin");
        this.vistaAdministrador=vr;
        this.adminDAO=adao;
        this.ldao=new LiderDAO();
        mostrar();
        cargarComboTipo();
        //cargarComboLider();
        inhabilitar();
        ajustarAnchoColumnas(vistaAdministrador.tablausuario);
        
        soloNumeros(vistaAdministrador.txtnumci);
        //mostrarUsuarios();
        
        //  --------------EVENTOS
        
        this.vistaAdministrador.btnagregar.addActionListener(this);
        this.vistaAdministrador.btnmodificar.addActionListener(this);
        this.vistaAdministrador.btneliminar.addActionListener(this);
        this.vistaAdministrador.btncancelar.addActionListener(this);
        this.vistaAdministrador.btnnuevo.addActionListener(this);
        this.vistaAdministrador.botonlider.addActionListener(this);
        
        this.vistaAdministrador.botonrestablecer.addActionListener(this);

        
        this.vistaAdministrador.tablausuario.addMouseListener(this);
    }
    
    /////////LLAMANDO METODOS
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(vistaAdministrador.btnagregar==ae.getSource()){
            try {
                registrarAdministrador();
                limpiartabla(vistaAdministrador.tablausuario);
                mostrar();
                limpiarfield();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo agregar");
            }
        }else if(vistaAdministrador.btneliminar==ae.getSource()){
            try {
                eliminarAdministrador();
                limpiartabla(vistaAdministrador.tablausuario);
                mostrar();
                limpiarfield();   
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar");
            }
        }else if(vistaAdministrador.btncancelar==ae.getSource()){
            try {
                limpiarfield();
                limpiartabla(vistaAdministrador.tablausuario);
                mostrar();
                inhabilitar();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo cancelar");
            }
        }else if(vistaAdministrador.btnmodificar==ae.getSource()){
            try {
                modificarAdministrador();
                limpiartabla(vistaAdministrador.tablausuario);
                mostrar();
                limpiarfield();
            } catch (Exception e) {
            }
        }else if(vistaAdministrador.btnnuevo==ae.getSource()){
            try {
                limpiarfield();
                habilitar();
            } catch (Exception e) {
            }
        }else if(vistaAdministrador.botonrestablecer == ae.getSource()){
            restablecerContraseña();
        }else if(vistaAdministrador.botonlider==ae.getSource()){
            try {
                buscarLiderPorCI();
            } catch (Exception e) {
            }
        }
    }
     @Override
    public void mouseClicked(MouseEvent e){
        int fila=vistaAdministrador.tablausuario.getSelectedRow();
        if(fila== -1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA");
            
        }else{
            clic();
            
            id=lista.get(fila).getIdadmin();
            
            String roles=vistaAdministrador.tablausuario.getValueAt(fila, 1).toString();
            String nomusuario=vistaAdministrador.tablausuario.getValueAt(fila, 2).toString();
            String claveusuario=vistaAdministrador.tablausuario.getValueAt(fila, 3).toString();
            String nombreCompleto = vistaAdministrador.tablausuario.getValueAt(fila, 0).toString();
            
            idLiderSeleccionado= lista.get(fila).getIdlider();
            
            try {
                vistaAdministrador.boxusuarios.setSelectedItem(roles);
                
                vistaAdministrador.txtnombreusuario.setText(nomusuario);
                vistaAdministrador.txtcontraseña.setText(claveusuario);
                
                vistaAdministrador.txtnomlider.setText(nombreCompleto);
                
                /*cargarComboLider();
                seleccionarLider(vistaAdministrador.boxlider, idLider);*/
            } catch (Exception error) {
                error.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al cargar los datos: " + error.getMessage());
            }
        }
        
    }
    /////////////////MOSTRAR
    public void mostrar(){
        lista=adminDAO.listarAdministradores();
        tablamodel=(DefaultTableModel) vistaAdministrador.tablausuario.getModel();
        tablamodel.setRowCount(0);
        
        Object obj[]=new Object[7];
        
        if (lista != null && !lista.isEmpty()){
            for(int i=0;i<lista.size();i++){
                obj[0]=lista.get(i).getNombrelider();
                obj[1]=lista.get(i).getUsuario();
                obj[2]=lista.get(i).getNombreusuario();
                obj[3] = "••••••";
                //obj[3]=lista.get(i).getContraseña();
                
                tablamodel.addRow(obj);
                
            }
        }else{
            System.out.println("No hay datos para mostrar.");
        }
            vistaAdministrador.tablausuario.setModel(tablamodel);
    }
    ///////SELECCIONAR LIUDER
    /*private void seleccionarLider(JComboBox<Lideriglesia> combo, int idLider) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            Lideriglesia l = combo.getItemAt(i);
            if (l.getIdlider() == idLider) {
                combo.setSelectedIndex(i);
                return;
            }
        }
    }*/
    /////////CARGAR COMO LIDER
    /*public void cargarComboLider() {
        LiderDAO dao = new LiderDAO();
        List<Lideriglesia> lista = dao.mostrarlider();
        System.out.println("Líderes encontrados: " + lista.size());


        vistaAdministrador.boxlider.removeAllItems(); // Limpia el combo

        // Agrega un líder por defecto para forzar la selección
        Lideriglesia liderPorDefecto = new Lideriglesia();
        liderPorDefecto.setIdlider(0);
        liderPorDefecto.setNombre("Selecciona un nombre");
        liderPorDefecto.setApellidop("");
        liderPorDefecto.setApellidom("");
        vistaAdministrador.boxlider.addItem(liderPorDefecto);

        for (Lideriglesia li : lista) {
            vistaAdministrador.boxlider.addItem(li);
        }
    }*/
    ////////CARGAR COMBO TIPO
    public void cargarComboTipo() {
        vistaAdministrador.boxusuarios.removeAllItems(); // Limpia el combo por si ya tenía algo

        // Agrega el item por defecto (para validar que se seleccione uno)
        vistaAdministrador.boxusuarios.addItem("Pastor"); // index 0
        vistaAdministrador.boxusuarios.addItem("Secretario");              // index 1
        vistaAdministrador.boxusuarios.addItem("Tesorero");  
    }

    public void registrarAdministrador() {
        if (idLiderSeleccionado <= 0) {
            JOptionPane.showMessageDialog(null, "Debe buscar y seleccionar un líder válido primero.");
            return;
        }
        Administrador admin = new Administrador();

        // Obtener líder
        /*Lideriglesia lider = (Lideriglesia) vistaAdministrador.boxlider.getSelectedItem();
        admin.setIdlider(lider.getIdlider());*/
       
        admin.setIdlider(idLiderSeleccionado);

        // Obtener tipo de usuario como texto
        String tipoUsuario = (String) vistaAdministrador.boxusuarios.getSelectedItem();
        admin.setUsuario(tipoUsuario); // ahora es String

        // Obtener los campos de texto
        admin.setNombreusuario(vistaAdministrador.txtnombreusuario.getText());
        admin.setContraseña(vistaAdministrador.txtcontraseña.getText());

        // Registrar
        AdministradorDAO dao = new AdministradorDAO();
        if (dao.registrarAdministrador(admin)) {
            JOptionPane.showMessageDialog(null, "Administrador registrado con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar administrador.");
        }
    }
    
    public void modificarAdministrador() {
        if (id == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila para modificar");
            return;
        }
        if (idLiderSeleccionado <= 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un líder válido");
            return;
        }

        try {
            // 1. Obtener datos de la vista
            String rol = vistaAdministrador.boxusuarios.getSelectedItem().toString();
            String nombreUsuario = vistaAdministrador.txtnombreusuario.getText().trim();
            String contraseña = vistaAdministrador.txtcontraseña.getText().trim();
            String ci = vistaAdministrador.txtnomlider.getText().trim(); 
            //Lideriglesia lider = (Lideriglesia) vistaAdministrador.boxlider.getSelectedItem(); // suponiendo que tienes un JComboBox con líderes

            // Validación básica
            if (nombreUsuario.isEmpty() || contraseña.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe completar todos los campos");
                return;
            }

            /*if (lider == null) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un líder");
                return;
            }*/

            // 2. Crear objeto Administrador con los datos
            Administrador admin = new Administrador();
            admin.setIdadmin(id); // este ID debe haberse guardado en mouseClicked
            admin.setIdlider(idLiderSeleccionado);
            admin.setUsuario(rol);
            admin.setNombreusuario(nombreUsuario);
            admin.setContraseña(contraseña);

            // 3. Ejecutar la modificación vía DAO
            AdministradorDAO dao = new AdministradorDAO();
            boolean actualizado = dao.editarAdministrador(admin);

            // 4. Mostrar resultado
            if (actualizado) {
                JOptionPane.showMessageDialog(null, "Administrador actualizado con éxito");
                mostrar();     // vuelve a cargar la tabla
                limpiarfield();    // limpia los campos del formulario
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo actualizar el administrador");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + ex.getMessage());
        }
    }
    public void eliminarAdministrador() {
    if (id == 0) {
        JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar este administrador?", "Confirmar", JOptionPane.YES_NO_OPTION);
    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }

    AdministradorDAO dao = new AdministradorDAO();
    boolean eliminado = dao.eliminarAdministrador(id);

    if (eliminado) {
        JOptionPane.showMessageDialog(null, "Administrador eliminado correctamente");
        mostrar();    // recarga la tabla
        limpiarfield();   // limpia los campos del formulario
        id = 0;             // resetea el ID seleccionado
    } else {
        JOptionPane.showMessageDialog(null, "No se pudo eliminar el administrador");
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
            /*vistaAdministrador.txtnombre.setText(""); //System.out.println("errooooo"+ err);
            //vistaRegistro.txtnombre.setText("");
            vistaAdministrador.txtapellidop.setText("");
            vistaAdministrador.txtapellidom.setText("");*/
            vistaAdministrador.boxusuarios.setSelectedItem("");
            vistaAdministrador.txtnombreusuario.setText("");
            vistaAdministrador.txtcontraseña.setText("");
            vistaAdministrador.txtnomlider.setText("");
            vistaAdministrador.txtnumci.setText("");
        
    }
    /*public void mostrarUsuarios(){
        LiderDAO ldao= new LiderDAO();
        //List<Lideriglesia> listausuario=new ArrayList<>();
        lisusua=ldao.mostrarlider();
        vistaAdministrador.boxlider.addItem(" "+" "+"Selecione un nombre");
        for(int i=0;i<lisusua.size();i++){
            vistaAdministrador.boxlider.addItem(lisusua.get(i).getIdlider()+" "+lisusua.get(i).getNombre()+" "+lisusua.get(i).getApellidop());
        }
    }*/
    
    /*public boolean validarEmail(String email){
    // Patrón para validar el email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        // El email a validar
        //String email = "correo@prueba.com";

        Matcher mather = pattern.matcher(email);

        if (mather.find() == true) {
            return true;
        } else {
            return false;
        }
    }*/
    public void inhabilitar(){
        vistaAdministrador.btnagregar.setEnabled(false);
        vistaAdministrador.btncancelar.setEnabled(false);
        vistaAdministrador.btneliminar.setEnabled(false);
        vistaAdministrador.btnmodificar.setEnabled(false);
        vistaAdministrador.txtnombreusuario.setEnabled(false);
        vistaAdministrador.txtcontraseña.setEnabled(false);
        vistaAdministrador.txtnomlider.setEnabled(false);
        vistaAdministrador.boxusuarios.setEnabled(false);
        vistaAdministrador.botonrestablecer.setEnabled(false);
        vistaAdministrador.txtnumci.setEnabled(false);
        vistaAdministrador.botonlider.setEnabled(false);
    }
    public void habilitar(){
        vistaAdministrador.btnagregar.setEnabled(true);
        vistaAdministrador.btncancelar.setEnabled(true);
        vistaAdministrador.botonlider.setEnabled(true);
        //vistaAdministrador.btnmodificar.setEnabled(true);
        
        /*vistaAdministrador.txtnombre.setEnabled(true);
        //vistaRegistro.txtnombre.setEnabled(true);
        vistaAdministrador.txtapellidop.setEnabled(true);*/
        vistaAdministrador.txtnumci.setEnabled(true);
        vistaAdministrador.txtnombreusuario.setEnabled(true);
        vistaAdministrador.txtcontraseña.setEnabled(true);
        vistaAdministrador.txtnomlider.setEnabled(true);
        vistaAdministrador.boxusuarios.setEnabled(true);
  
    }
    public void clic(){
        //vistaAdministrador.btnagregar.setEnabled(true);
        vistaAdministrador.btncancelar.setEnabled(true);
        vistaAdministrador.btneliminar.setEnabled(true);
        vistaAdministrador.btnmodificar.setEnabled(true);
        vistaAdministrador.botonrestablecer.setEnabled(true);
        vistaAdministrador.btnagregar.setEnabled(false);
        vistaAdministrador.botonlider.setEnabled(false);
        
        /*vistaAdministrador.txtnombre.setEnabled(true);
        //vistaRegistro.txtnombre.setEnabled(true);
        vistaAdministrador.txtapellidop.setEnabled(true);
        vistaAdministrador.txtapellidom.setEnabled(true);*/
        vistaAdministrador.txtnombreusuario.setEnabled(true);
        vistaAdministrador.txtcontraseña.setEnabled(false);
        vistaAdministrador.txtnomlider.setEnabled(true);
        vistaAdministrador.boxusuarios.setEnabled(true);
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
    public void restablecerContraseña() {
        int fila = vistaAdministrador.tablausuario.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un administrador");
            return;
        }

        String nuevaPass = JOptionPane.showInputDialog("Ingrese nueva contraseña:");
        if (nuevaPass == null || nuevaPass.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una contraseña.");
            return;
        }

        int idAdmin = lista.get(fila).getIdadmin();
        String nuevaHash = Incriptar.hashSHA256(nuevaPass);

        boolean actualizado = adminDAO.actualizarContraseña(idAdmin, nuevaHash);
        if (actualizado) {
            JOptionPane.showMessageDialog(null, "Contraseña actualizada correctamente.");
            mostrar(); // vuelve a cargar la tabla
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar la contraseña.");
        }
    }
    public void buscarLiderPorCI() {
        String ci = vistaAdministrador.txtnumci.getText().trim();

        if (ci.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un número de carnet");
            return;
        }

        Lideriglesia lider = ldao.obtenerLiderPorCI(ci);
        if (lider != null) {
            idLiderSeleccionado = lider.getIdlider(); // 🔹 guardar ID interno
            vistaAdministrador.txtnomlider.setText(
                lider.getNombre() + " " + lider.getApellidop() + " " + lider.getApellidom()
            ); // 🔹 mostrar nombre completo
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró ningún líder con ese CI");
            idLiderSeleccionado = 0;
            vistaAdministrador.txtnumci.setText("");
        }
    }
    public void soloNumeros(JTextField textField) {
        textField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    evt.consume(); // No permite letras ni símbolos
                    JOptionPane.showMessageDialog(null, 
                    "Solo se permiten números en este campo", 
                    "Entrada no válida", 
                    JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    
}
