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
    VistaRegistro vistaRegistro;
    AdminDAO adminDAO;
    Administrador adminis;
    DefaultTableModel tablamodel=new DefaultTableModel();
    
    List<Administrador> lista;
    int id;
    
    public ControlAdmin(VistaRegistro vr, AdminDAO adao){
        System.out.println("listando admin");
        this.vistaRegistro=vr;
        this.adminDAO=adao;
        mostrar();
        inhabilitar();
        
        //  --------------EVENTOS
        
        this.vistaRegistro.btnagregar.addActionListener(this);
        this.vistaRegistro.btneliminar.addActionListener(this);
        this.vistaRegistro.btncancelar.addActionListener(this);
        this.vistaRegistro.btnnuevo.addActionListener(this);

        
        this.vistaRegistro.tablausuario.addMouseListener(this);
    }
    
    /////////METODOS
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(vistaRegistro.btnagregar==ae.getSource()){
            try {
                insertar();
                limpiartabla(vistaRegistro.tablausuario);
                mostrar();
                limpiarfield();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo agregar");
            }
        }else if(vistaRegistro.btneliminar==ae.getSource()){
            try {
                eliminar();
                limpiartabla(vistaRegistro.tablausuario);
                mostrar();
                limpiarfield();   
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar");
            }
        }else if(vistaRegistro.btncancelar==ae.getSource()){
            try {
                limpiarfield();
                limpiartabla(vistaRegistro.tablausuario);
                mostrar();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo cancelar");
            }
        }else if(vistaRegistro.btnnuevo==ae.getSource()){
            try {
                limpiarfield();
                habilitar();
            } catch (Exception e) {
            }
        }
    }
     @Override
    public void mouseClicked(MouseEvent e){
        int fila=vistaRegistro.tablausuario.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA");
        }else{
            id=lista.get(fila).getIdadmin();
            
            String nom=vistaRegistro.tablausuario.getValueAt(fila, 0).toString();
            String pat=vistaRegistro.tablausuario.getValueAt(fila, 1).toString();
            String mat=vistaRegistro.tablausuario.getValueAt(fila, 2).toString();
            String num=vistaRegistro.tablausuario.getValueAt(fila, 3).toString();
            String tel=vistaRegistro.tablausuario.getValueAt(fila, 4).toString();
            String emal=vistaRegistro.tablausuario.getValueAt(fila, 5).toString();
            String car=vistaRegistro.tablausuario.getValueAt(fila, 6).toString();
            String usu=vistaRegistro.tablausuario.getValueAt(fila, 7).toString();
            String nomu=vistaRegistro.tablausuario.getValueAt(fila, 8).toString();
            String cont=vistaRegistro.tablausuario.getValueAt(fila, 9).toString();
            
            
            vistaRegistro.txtnombre.setText(nom); //System.out.println("errooooo"+ err);
            vistaRegistro.txtapellidopa.setText(pat);
            vistaRegistro.txtapellidoma.setText(mat);
            vistaRegistro.txtnumdocumento.setText(num);
            //System.out.println("parseado aqui");
            vistaRegistro.txttelefono.setText(tel);
            vistaRegistro.txtemail.setText(emal);
            //System.out.println("parseado");
            vistaRegistro.boxcargo.setSelectedItem(car);
            vistaRegistro.boxusuario.setSelectedItem(usu);
            vistaRegistro.txtnombreusuario.setText(cont);
            vistaRegistro.txtcontraseña.setText(cont);
        }
    }
    public void mostrar(){
        lista=adminDAO.listaradmin();
        tablamodel=(DefaultTableModel) vistaRegistro.tablausuario.getModel();
        Object obj[]=new Object[10];
        
        for(int i=0;i<lista.size();i++){
            obj[0]=lista.get(i).getNombre();
            obj[1]=lista.get(i).getApaterno();
            obj[2]=lista.get(i).getAmaterno();
            obj[3]=lista.get(i).getNumdocumento();
            obj[4]=lista.get(i).getTelefono();
            obj[5]=lista.get(i).getEmail();
            obj[6]=lista.get(i).getCargo();
            obj[7]=lista.get(i).getUsuario();
            obj[8]=lista.get(i).getNombreusuario();
            obj[9]=lista.get(i).getContraseña();
            
            tablamodel.addRow(obj);
        }
        vistaRegistro.tablausuario.setModel(tablamodel);
    }

    
    public void insertar(){
        if(vistaRegistro.txtnombre.getText().trim().isEmpty()||
                vistaRegistro.txtapellidopa.getText().trim().isEmpty()||
                vistaRegistro.txtapellidoma.getText().trim().isEmpty()||
                vistaRegistro.txtnumdocumento.getText().trim().isEmpty()||
                vistaRegistro.txttelefono.getText().trim().isEmpty()||
                vistaRegistro.txtemail.getText().trim().isEmpty()||
                vistaRegistro.boxcargo.getSelectedItem().toString().trim().isEmpty()||
                vistaRegistro.boxusuario.getSelectedItem().toString().trim().isEmpty()||
                vistaRegistro.txtnombreusuario.getText().trim().isEmpty()||
                vistaRegistro.txtcontraseña.getText().trim().isEmpty()){
            
            JOptionPane.showMessageDialog(null,"DEBE LLENAR TODOS LOS CAMPOS");
        }else{
            if(validarEmail(vistaRegistro.txtemail.getText())){
            adminis.setNombre(vistaRegistro.txtnombre.getText());
            adminis.setApaterno(vistaRegistro.txtapellidopa.getText());
            adminis.setAmaterno(vistaRegistro.txtapellidoma.getText());
            adminis.setNumdocumento(vistaRegistro.txtnumdocumento.getText());
            adminis.setTelefono(Integer.parseInt(vistaRegistro.txttelefono.getText()));
            adminis.setEmail(vistaRegistro.txtemail.getText());
            
            adminis.setCargo((String) vistaRegistro.boxcargo.getSelectedItem());
//            int select=vistaRegistro.boxcargo.getSelectedIndex();
//            adminis.setCargo(vistaRegistro.boxcargo.getItemAt(select));
//            
//            select=vistaRegistro.boxusuario.getSelectedIndex();
//            adminis.setUsuario(vistaRegistro.boxusuario.getItemAt(select));
            adminis.setUsuario((String) vistaRegistro.boxusuario.getSelectedItem());
            
            adminis.setNombreusuario(vistaRegistro.txtnombreusuario.getText());
            adminis.setContraseña(vistaRegistro.txtcontraseña.getText());
            
            adminDAO.agregar(adminis);
            
            System.out.println("fsssaasssssssssssssss");
        }else{
                JOptionPane.showMessageDialog(null, "Ingrese Correo Valido Ej:correo@gmail.com");
 
            }
        }
    }
        
    public void eliminar(){
        int fila=vistaRegistro.tablausuario.getSelectedRow();
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
            vistaRegistro.txtnombre.setText(""); //System.out.println("errooooo"+ err);
            vistaRegistro.txtapellidopa.setText("");
            vistaRegistro.txtapellidoma.setText("");
            vistaRegistro.txtnumdocumento.setText("");
            //System.out.println("parseado aqui");
            vistaRegistro.txttelefono.setText("");
            vistaRegistro.txtemail.setText("");
            //System.out.println("parseado");
            vistaRegistro.boxcargo.setSelectedItem("");
            vistaRegistro.boxusuario.setSelectedItem("");
            vistaRegistro.txtnombreusuario.setText("");
            vistaRegistro.txtcontraseña.setText("");
        
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
        vistaRegistro.btnagregar.setEnabled(false);
        vistaRegistro.btncancelar.setEnabled(false);
        vistaRegistro.btneliminar.setEnabled(false);
        vistaRegistro.btnmodificar.setEnabled(false);
        
        vistaRegistro.txtnombre.setEnabled(false);
        vistaRegistro.txtapellidopa.setEnabled(false);
        vistaRegistro.txtapellidoma.setEnabled(false);
        vistaRegistro.txtnumdocumento.setEnabled(false);
        vistaRegistro.txtnombreusuario.setEnabled(false);
        vistaRegistro.txtcontraseña.setEnabled(false);
        vistaRegistro.txtemail.setEnabled(false);
        vistaRegistro.txttelefono.setEnabled(false);
        vistaRegistro.boxcargo.setEnabled(false);
        vistaRegistro.boxusuario.setEnabled(false);
    }
    public void habilitar(){
        vistaRegistro.btnagregar.setEnabled(true);
        vistaRegistro.btncancelar.setEnabled(true);
        vistaRegistro.btneliminar.setEnabled(true);
        vistaRegistro.btnmodificar.setEnabled(true);
        
        vistaRegistro.txtnombre.setEnabled(true);
        vistaRegistro.txtapellidopa.setEnabled(true);
        vistaRegistro.txtapellidoma.setEnabled(true);
        vistaRegistro.txtnumdocumento.setEnabled(true);
        vistaRegistro.txtnombreusuario.setEnabled(true);
        vistaRegistro.txtcontraseña.setEnabled(true);
        vistaRegistro.txtemail.setEnabled(true);
        vistaRegistro.txttelefono.setEnabled(true);
        vistaRegistro.boxcargo.setEnabled(true);
        vistaRegistro.boxusuario.setEnabled(true);
    }
}
