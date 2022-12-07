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
            JOptionPane.showMessageDialog(iniciar, "INGRESE LOS DATOS");
        }else{
            admin=validarAdmin.ValidarAdmin(nombre, contra);
            System.out.println("valida nombre y contra");
            
            if(admin.getNombreusuario()!=null && admin.getContraseña()!=null){
             VentanaPrincipal ventanaPrincipal=new VentanaPrincipal();
             VentanaPrincipalIglesia ventanaPrincipalIglesia=new VentanaPrincipalIglesia(ventanaPrincipal,admin);
             iniciar.dispose();
            }else{
                JOptionPane.showMessageDialog(iniciar, "Datos No validos");
            }
        }
    }     
}
