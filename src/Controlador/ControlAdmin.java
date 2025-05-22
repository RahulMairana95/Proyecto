/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAHUL
 */
public class ControlAdmin extends MouseAdapter implements ActionListener{
    VistaRegistro vistaAdministrador=new VistaRegistro();
    AdministradorDAO adminDAO;
    Administrador adminis=new Administrador();
    DefaultTableModel tablamodel=new DefaultTableModel();
    
    List<Administrador> lista;
    int id;
    
    List<Lideriglesia> lisusua=new ArrayList<>();
    
    public ControlAdmin(VistaRegistro vr, AdministradorDAO adao){
        System.out.println("listando admin");
        this.vistaAdministrador=vr;
        this.adminDAO=adao;
        mostrar();
        inhabilitar();
        mostrarUsuarios();
        
        //  --------------EVENTOS
        
        this.vistaAdministrador.btnagregar.addActionListener(this);
        this.vistaAdministrador.btnmodificar.addActionListener(this);
        this.vistaAdministrador.btneliminar.addActionListener(this);
        this.vistaAdministrador.btncancelar.addActionListener(this);
        this.vistaAdministrador.btnnuevo.addActionListener(this);

        
        this.vistaAdministrador.tablausuario.addMouseListener(this);
    }
    
    /////////LLAMANDO METODOS
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(vistaAdministrador.btnagregar==ae.getSource()){
            try {
                insertar();
                limpiartabla(vistaAdministrador.tablausuario);
                mostrar();
                limpiarfield();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo agregar");
            }
        }else if(vistaAdministrador.btneliminar==ae.getSource()){
            try {
                eliminar();
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
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo cancelar");
            }
        }else if(vistaAdministrador.btnmodificar==ae.getSource()){
            try {
                editar();
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
        }
    }
     @Override
    public void mouseClicked(MouseEvent e){
        int fila=vistaAdministrador.tablausuario.getSelectedRow();
        if(fila== -1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA");
            
        }else{
            id=lista.get(fila).getIdadmin();
            
            String nombre=vistaAdministrador.tablausuario.getValueAt(fila, 0).toString();
            String apellidos=vistaAdministrador.tablausuario.getValueAt(fila, 1).toString();
            String numci=vistaAdministrador.tablausuario.getValueAt(fila, 2).toString();
            String telefono=vistaAdministrador.tablausuario.getValueAt(fila, 3).toString();
            String correo=vistaAdministrador.tablausuario.getValueAt(fila, 4).toString();
            String roles=vistaAdministrador.tablausuario.getValueAt(fila, 5).toString();
            String nomusuario=vistaAdministrador.tablausuario.getValueAt(fila, 6).toString();
            String claveusuario=vistaAdministrador.tablausuario.getValueAt(fila, 7).toString();
            
            try {
                vistaAdministrador.txtnombre.setText(nombre);
                vistaAdministrador.txtapellidos.setText(apellidos);
                vistaAdministrador.txtnumdocumento.setText(numci);
                vistaAdministrador.txttelefono.setText(telefono);
                vistaAdministrador.txtemail.setText(correo);
                
                vistaAdministrador.boxroles.setSelectedItem(roles);
                
                vistaAdministrador.txtnombreusuario.setText(nomusuario);
                vistaAdministrador.txtcontraseña.setText(claveusuario);
            } catch (Exception error) {
            }
        }
        
    }
    public void mostrar(){
        lista=adminDAO.listaradmin();
        tablamodel=(DefaultTableModel) vistaAdministrador.tablausuario.getModel();
        Object obj[]=new Object[8];
        
        for(int i=0;i<lista.size();i++){
            obj[0]=lista.get(i).getNombre();
            obj[1]=lista.get(i).getApellidos();
            obj[2]=lista.get(i).getNumdocumento();
            obj[3]=lista.get(i).getTelefono();
            obj[4]=lista.get(i).getEmail();
            obj[5]=lista.get(i).getUsuario();
            obj[6]=lista.get(i).getNombreusuario();
            obj[7]=lista.get(i).getContraseña();
            
            tablamodel.addRow(obj);
        }
        vistaAdministrador.tablausuario.setModel(tablamodel);
    }

    
    public void insertar(){
        if(vistaAdministrador.txtnombre.getText().trim().isEmpty()||
                vistaAdministrador.txtapellidos.getText().trim().isEmpty()||
                vistaAdministrador.txtnumdocumento.getText().trim().isEmpty()||
                vistaAdministrador.txttelefono.getText().trim().isEmpty()||
                vistaAdministrador.txtemail.getText().trim().isEmpty()||
                vistaAdministrador.boxroles.getSelectedItem().toString().trim().isEmpty()||
                vistaAdministrador.txtnombreusuario.getText().trim().isEmpty()||
                vistaAdministrador.txtcontraseña.getText().trim().isEmpty()){
            
            JOptionPane.showMessageDialog(null,"DEBE LLENAR TODOS LOS CAMPOS");
        }else{
            String tmp=(String) vistaAdministrador.boxusuarios.getSelectedItem();
            String [] aux=tmp.split(" ");
            String idlid=aux[0];
                
            if(validarEmail(vistaAdministrador.txtemail.getText())){
            //System.out.println("idlid"+ Integer.parseInt(idlid));
            adminis.setIdlider(Integer.parseInt(idlid));
            //System.out.println("1------------");
            adminis.setNombre(vistaAdministrador.txtnombre.getText());
            //System.out.println("2 -------------");
            adminis.setApellidos(vistaAdministrador.txtapellidos.getText());
            //System.out.println("2 -------------");
            //adminis.setAmaterno(vistaAdministrador.txtapellidos.getText());
            adminis.setNumdocumento(vistaAdministrador.txtnumdocumento.getText());
            //System.out.println("entrando a la condicions");
            adminis.setTelefono(Integer.parseInt(vistaAdministrador.txttelefono.getText()));
            adminis.setEmail(vistaAdministrador.txtemail.getText());
            
            adminis.setUsuario((String) vistaAdministrador.boxroles.getSelectedItem());
            int select=vistaAdministrador.boxroles.getSelectedIndex();
            adminis.setUsuario(vistaAdministrador.boxroles.getItemAt(select));

            //adminis.setUsuario((String) vistaAdministrador.boxusuario.getSelectedItem());  
            //int select=vistaAdministrador.boxusuario.getSelectedIndex();
            //adminis.setUsuario(vistaAdministrador.boxusuario.getItemAt(select));
            
            
            
            adminis.setNombreusuario(vistaAdministrador.txtnombreusuario.getText());
            adminis.setContraseña(vistaAdministrador.txtcontraseña.getText());
            
            adminDAO.agregar(adminis);
            
            System.out.println("fsssaasssssssssssssss");
        }else{
                JOptionPane.showMessageDialog(null, "Ingrese Correo Valido Ej:correo@gmail.com");
 
            }
        }
    }
    public void editar(){
        int fila=vistaAdministrador.tablausuario.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA");
        }else{
            if(vistaAdministrador.txtnombre.getText().trim().isEmpty()||
               vistaAdministrador.txtapellidos.getText().trim().isEmpty()||
               vistaAdministrador.txtnumdocumento.getText().trim().isEmpty()||
               vistaAdministrador.txttelefono.getText().trim().isEmpty()||
               vistaAdministrador.txtemail.getText().trim().isEmpty()||
               vistaAdministrador.boxroles.getSelectedItem().toString().trim().isEmpty()||
               vistaAdministrador.txtnombreusuario.getText().trim().isEmpty()||
               vistaAdministrador.txtcontraseña.getText().trim().isEmpty()){
                
               JOptionPane.showMessageDialog(null,"DEBE LLENAR TODOS LOS CAMPOS");
            }else{
                id=lista.get(fila).getIdadmin();
                
                String nombre=vistaAdministrador.txtnombre.getText();
                String apellidos=vistaAdministrador.txtapellidos.getText();
                String carnet=vistaAdministrador.txtnumdocumento.getText();
                String teel=vistaAdministrador.txttelefono.getText();
                String correo=vistaAdministrador.txtemail.getText();
                String roles=(String)vistaAdministrador.boxroles.getSelectedItem();
                String nomusu=vistaAdministrador.txtnombreusuario.getText();
                String contra=vistaAdministrador.txtcontraseña.getText();
                
                adminis.setIdadmin(id);
                adminis.setNombre(nombre);
                adminis.setApellidos(apellidos);
                adminis.setNumdocumento(carnet);
                adminis.setTelefono(Integer.parseInt(teel));
                adminis.setEmail(correo);
                adminis.setUsuario(roles);
                adminis.setNombreusuario(nomusu);
                adminis.setContraseña(contra);
                
                adminDAO.editar(adminis);
            }
        }
    }
        
    public void eliminar(){
        int fila=vistaAdministrador.tablausuario.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA PARA ELIMINAR");
        }else{
            adminDAO.eliminarcuenta(id);
            System.out.println("eliminando");
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
            vistaAdministrador.txtnombre.setText(""); //System.out.println("errooooo"+ err);
            //vistaRegistro.txtnombre.setText("");
            vistaAdministrador.txtapellidos.setText("");
            vistaAdministrador.txtnumdocumento.setText("");
            //System.out.println("parseado aqui");
            vistaAdministrador.txttelefono.setText("");
            vistaAdministrador.txtemail.setText("");
            //System.out.println("parseado");
            //vistaRegistro.boxcargo.setSelectedItem("");
            vistaAdministrador.boxroles.setSelectedItem("");
            vistaAdministrador.txtnombreusuario.setText("");
            vistaAdministrador.txtcontraseña.setText("");
        
    }
    public void mostrarUsuarios(){
        LiderDAO ldao= new LiderDAO();
        //List<Lideriglesia> listausuario=new ArrayList<>();
        lisusua=ldao.mostrarlider();
        vistaAdministrador.boxusuarios.addItem(" "+" "+"Selecione un nombre");
        for(int i=0;i<lisusua.size();i++){
            vistaAdministrador.boxusuarios.addItem(lisusua.get(i).getIdlider()+" "+lisusua.get(i).getNombre()+" "+lisusua.get(i).getApellidop());
        }
    }
    
    public boolean validarEmail(String email){
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
    }
    public void inhabilitar(){
        vistaAdministrador.btnagregar.setEnabled(false);
        vistaAdministrador.btncancelar.setEnabled(false);
        vistaAdministrador.btneliminar.setEnabled(false);
        vistaAdministrador.btnmodificar.setEnabled(false);
        
        vistaAdministrador.txtnombre.setEnabled(false);
        //vistaRegistro.txtnombre.setEnabled(false);
        vistaAdministrador.txtapellidos.setEnabled(false);
        vistaAdministrador.txtnumdocumento.setEnabled(false);
        vistaAdministrador.txtnombreusuario.setEnabled(false);
        vistaAdministrador.txtcontraseña.setEnabled(false);
        vistaAdministrador.txtemail.setEnabled(false);
        vistaAdministrador.txttelefono.setEnabled(false);
        //vistaRegistro.boxcargo.setEnabled(false);
        vistaAdministrador.boxroles.setEnabled(false);
        //vistaAdministrador.tablausuario.setEnabled(false);
    }
    public void habilitar(){
        vistaAdministrador.btnagregar.setEnabled(true);
        vistaAdministrador.btncancelar.setEnabled(true);
        vistaAdministrador.btneliminar.setEnabled(true);
        vistaAdministrador.btnmodificar.setEnabled(true);
        
        vistaAdministrador.txtnombre.setEnabled(true);
        //vistaRegistro.txtnombre.setEnabled(true);
        vistaAdministrador.txtapellidos.setEnabled(true);
        vistaAdministrador.txtnumdocumento.setEnabled(true);
        vistaAdministrador.txtnombreusuario.setEnabled(true);
        vistaAdministrador.txtcontraseña.setEnabled(true);
        vistaAdministrador.txtemail.setEnabled(true);
        vistaAdministrador.txttelefono.setEnabled(true);
        //vistaRegistro.boxcargo.setEnabled(true);
        vistaAdministrador.boxroles.setEnabled(true);
    }
}
