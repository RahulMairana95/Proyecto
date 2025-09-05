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

public class ControlCuenta implements ActionListener {
    Usuario iniciar;
    ValidarAdmin validarAdmin;
    Administrador admin;

    // Constructor original (con ambos parámetros)
    public ControlCuenta(Usuario iniciar, ValidarAdmin validaradmin) {
        this.iniciar = iniciar;
        this.validarAdmin = validaradmin;
        
        //validaradmin.actualizarTodasLasContraseñas();

        iniciar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        iniciar.setLocationRelativeTo(null);
        iniciar.setResizable(false);
        iniciar.setVisible(true);
        iniciar.botonenter.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        validar();
        System.out.println("Resultado de validarAdmin: " + (admin != null ? admin.getNombreusuario() : "null"));
    }

    public void validar() {
        String nombre = iniciar.txtcuenta.getText().trim();
        String contra = iniciar.txtcontraseña.getText().trim();

        if (nombre.equals("") || contra.equals("")) {
            if (nombre.equals("")) {
                JOptionPane.showMessageDialog(iniciar, "Ingrese el nombre de usuario");
            }
            if (contra.equals("")) {
                JOptionPane.showMessageDialog(iniciar, "Ingrese la contraseña");
            }
        } else {
            boolean usuarioExiste = validarAdmin.existeUsuario(nombre);
            if (!usuarioExiste && !nombre.equals("superadmin")) {
                JOptionPane.showMessageDialog(iniciar, "Nombre de usuario incorrecto");
                return;
            }
            
            admin = validarAdmin.validarAdmin(nombre, contra);

            if (admin != null /*&& nombre.equals(admin.getNombreusuario()) && contra.equals(admin.getContraseña())*/) {
                
                Sesion.administradorActual = admin;
                
                VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
                VentanaPrincipalIglesia ventanaPrincipalIglesia = new VentanaPrincipalIglesia(ventanaPrincipal, admin);
                iniciar.dispose();
            } else {
                JOptionPane.showMessageDialog(iniciar, "Contraseña incorrecta.");
                iniciar.txtcontraseña.setText("");
            }
        }
    }
}

