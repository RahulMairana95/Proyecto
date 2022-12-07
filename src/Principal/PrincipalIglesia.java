/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;


import Vista.*;
import Controlador.*;
import Modelo.*;
import java.sql.Connection;
import javax.swing.UIManager;


/**
 *
 * @author RAHUL
 */
public class PrincipalIglesia {
    
    public static void main(String[] args) {
        
        
        try{
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
            //System.out.println("probando");
        }catch(Exception e){
            e.printStackTrace();
        }
        
      //sin contraseña
        //VentanaPrincipal ventana=new VentanaPrincipal();
        //VentanaPrincipalIglesia iglesia=new VentanaPrincipalIglesia(ventana);
        
        ////////////PRINCIPAL
        
        Usuario iniciar=new Usuario();
        ValidarAdmin admin=new ValidarAdmin(iniciar);
        ControlCuenta controlCuenta=new ControlCuenta(iniciar, admin);
        
       
        
//Usuario usuario=new Usuario();
        
        //VistaLider lider=new VistaLider();
        //con=conexion.conectando();
        
        System.out.println("conec");
    }
}
