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
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author RAHUL
 */
public class ControlCuenta implements ActionListener{
    Usuario iniciar;
    ValidarAdmin validarAdmin;
    Administrador admin;
    
    public ControlCuenta(Usuario iniciar, ValidarAdmin validaradmin){
        this.iniciar=iniciar;
        this.validarAdmin=validaradmin;
        
        iniciar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        iniciar.setLocationRelativeTo(null);
        iniciar.setResizable(false);
        
        iniciar.setVisible(true);
        iniciar.botonenter.addActionListener(this);
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        validar();
    }
    public void validar(){
        String nombre=iniciar.txtcuenta.getText();
        String contra=iniciar.txtcontraseña.getText();
        
        if(nombre.equals("")||contra.equals("")){
            if (nombre.equals("")){
                //iniciar.txtcuenta.setText("los campos están vacíos");
                JOptionPane.showMessageDialog(iniciar, "Igrese el nombre de usuario");
            }
            if (contra.equals("")){
                //iniciar.txtcontraseña.setText("los campos están vacíos");
                JOptionPane.showMessageDialog(iniciar, "Igrese la contraseña");
            }
            
        }else{
            boolean usuarioExiste= validarAdmin.existeUsuario(nombre);
            if (!usuarioExiste){
                JOptionPane.showMessageDialog(iniciar, "Nombre de usuario incorrecto");
            }
            admin=validarAdmin.validarAdmin(nombre, contra);
            //System.out.println("valida nombre y contra");
            
            //if(admin != null && admin.getNombreusuario()!=null && admin.getContraseña()!=null){
            if (admin != null && nombre.equals(admin.getNombreusuario()) && contra.equals(admin.getContraseña())) {
             VentanaPrincipal ventanaPrincipal=new VentanaPrincipal();
             VentanaPrincipalIglesia ventanaPrincipalIglesia=new VentanaPrincipalIglesia(ventanaPrincipal,admin);
             iniciar.dispose();
            }else{
                JOptionPane.showMessageDialog(iniciar, "Contraseña incorrecta.");
                iniciar.txtcontraseña.setText("");
            }
        }
    }     
}
