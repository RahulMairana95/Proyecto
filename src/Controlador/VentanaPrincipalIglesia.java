/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Vista.*;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
/**
 *
 * @author RAHUL
 */
public class VentanaPrincipalIglesia implements ActionListener{
    VentanaPrincipal ventanaPrincipal;
    JDesktopPane panel;
    ImageIcon imagen;
    
    public VentanaPrincipalIglesia(){}
    
    public VentanaPrincipalIglesia(VentanaPrincipal ventanaPrincipal,Administrador admin){
        this.ventanaPrincipal=ventanaPrincipal;
        panel=ventanaPrincipal.panelPrincipal;
        
        imagen=new ImageIcon(getClass().getResource("igle.png"));
        ventanaPrincipal.setIconImage(imagen.getImage());
        
        
        ventanaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ventanaPrincipal.setTitle("Sistema de Membrecía Iglesia Nueva Jerusalen Motecato");
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setVisible(true);
        
        //EVENTOS
        ventanaPrincipal.itemmiembros.addActionListener(this);
        //ventanaPrincipal.itemMembrecia.addActionListener(this);
        ventanaPrincipal.itemoregistrarfrenda.addActionListener(this);
        ventanaPrincipal.itemregistrardiezmo.addActionListener(this);
        ventanaPrincipal.itemlideriglesia.addActionListener(this);
        ventanaPrincipal.itemRegistro.addActionListener(this);
        ventanaPrincipal.itemLista.addActionListener(this);
        ventanaPrincipal.itemIglesia.addActionListener(this);
        ventanaPrincipal.itemDiezmo.addActionListener(this);
        ventanaPrincipal.itemOfrenda.addActionListener(this);
        //ventanaPrincipal.itemSalir.addActionListener(this);
        
        ventanaPrincipal.idusuarioaminnis.setText(admin.getIdadmin()+ "");
        ventanaPrincipal.idusuarioaminnis.setVisible(false);
        
        ventanaPrincipal.lblusuario.setVisible(false);
        
        if (admin.getUsuario().equalsIgnoreCase("Pastor")||admin.getUsuario().equalsIgnoreCase("superadmin")) 
            ventanaPrincipal.itemRegistro.setEnabled(true);
        else
          ventanaPrincipal.itemRegistro.setEnabled(false);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ventanaPrincipal.itemLista==ae.getSource()){
            listarMiembros();
        }else if(ventanaPrincipal.itemregistrardiezmo==ae.getSource()){
            agregarDiezmo();
        }else if(ventanaPrincipal.itemoregistrarfrenda==ae.getSource()){
            agregarOfrenda();
        }else if(ventanaPrincipal.itemlideriglesia==ae.getSource()){
            agregarLider();
        }else if(ventanaPrincipal.itemRegistro==ae.getSource()){
            agregarAdmin();
        }else if(ventanaPrincipal.itemmiembros==ae.getSource()){
            agregarMembrecia();
        }else if(ventanaPrincipal.itemIglesia==ae.getSource()){
            listarLideresGlesia();
        }else if(ventanaPrincipal.itemDiezmo==ae.getSource()){
            listarDiezmo();
        }else if(ventanaPrincipal.itemOfrenda==ae.getSource()){
            listarOfrenda();
        }
    }
    
    public void agregarMembrecia(){
        VistaMembrecia vistaMembrecia=new VistaMembrecia();
        MembreciaDAO membreciaDAO=new MembreciaDAO(vistaMembrecia);
        ControlMembrecia controlMembrecia=new ControlMembrecia(vistaMembrecia, membreciaDAO);
        vistaMembrecia.setTitle("REGISTRO DE MIEMBROS");
        
        centarFrameInterno(vistaMembrecia);
    }
    public void agregarDiezmo(){
        VistaDiezmo vistaDiezmo=new VistaDiezmo();
        DiezmoDAO diezmoDAO=new DiezmoDAO(vistaDiezmo);
        ControlDiezmo controlDiezmo=new ControlDiezmo(vistaDiezmo, diezmoDAO);
        vistaDiezmo.setTitle("REGISTRO DE DIEZMOS");
        
        centarFrameInterno(vistaDiezmo);
    }
    public void listarDiezmo(){
        VistaListaDiezmo vistalistaDiezmo=new VistaListaDiezmo();
        DiezmoDAO diezmoDAO=new DiezmoDAO();
        ControlListaDiezmo controlDiezmo=new ControlListaDiezmo(vistalistaDiezmo, diezmoDAO);
        vistalistaDiezmo.setTitle("LISTA DE CONTROL DE DIEZMOS");
        
        centarFrameInterno(vistalistaDiezmo);
    }
    public void agregarOfrenda(){
        VistaOfrenda vistaOfrenda=new VistaOfrenda();
        OfrendaDAO ofrendaDAO=new OfrendaDAO(vistaOfrenda);
        ControlOfrenda controlOfrenda=new ControlOfrenda(vistaOfrenda, ofrendaDAO);
        vistaOfrenda.setTitle("REGISTRO DE OFRENDAS");
        
        centarFrameInterno(vistaOfrenda);
    }
    public void listarOfrenda(){
        VistaListaOfrenda vistalistaOfrenda=new VistaListaOfrenda();
        OfrendaDAO ofrendaDAO=new OfrendaDAO();
        ControlListaOfrenda controlOfrenda=new ControlListaOfrenda(vistalistaOfrenda, ofrendaDAO);
        vistalistaOfrenda.setTitle("LISTA DE CONROL DE OFRENDAS");
        
        centarFrameInterno(vistalistaOfrenda);
    }
    public void agregarLider(){
        VistaLider vistaLider=new VistaLider();
        LiderDAO liderDAO=new LiderDAO(vistaLider);
        ControlLider controlLider=new ControlLider(vistaLider, liderDAO);
        vistaLider.setTitle("REGISTRO DE LIDERES");
        
        centarFrameInterno(vistaLider);
       
    }
    public void agregarAdmin(){
        VistaRegistro vistaRegistro=new VistaRegistro();
        AdminDAO adminDAO=new AdminDAO(vistaRegistro);
        ControlAdmin controlAdmin=new ControlAdmin(vistaRegistro, adminDAO);
        vistaRegistro.setTitle("REGISTRO DE USUARIOS");
        
        centarFrameInterno(vistaRegistro);
    }
    public void listarMiembros(){
        VistaListaMembrecia vistaListaMembrecia=new VistaListaMembrecia();
        MembreciaDAO membreciaDAO=new MembreciaDAO();
        ControlLista controlLista=new ControlLista(vistaListaMembrecia, membreciaDAO);
        vistaListaMembrecia.setTitle("LISTA DE MIEMBROS BAUTIZADOS");
        
        centarFrameInterno(vistaListaMembrecia);
       
    }
    public void listarLideresGlesia(){
        VistaLiderIglesia vistaLiderIglesia=new VistaLiderIglesia();
        LiderDAO liderDAO=new LiderDAO();
        ControlIglesia controlIglesia=new ControlIglesia(vistaLiderIglesia, liderDAO);
        vistaLiderIglesia.setTitle("LISTA DE LIDERES DE LA IGLESIA");
        
        centarFrameInterno(vistaLiderIglesia);
       
    }
    
    
    public void centarFrameInterno(JInternalFrame frameInterno){
        panel.add(frameInterno);
        Dimension dimpanel=panel.getSize();
        Dimension dimframe=frameInterno.getSize();
        frameInterno.setLocation((dimpanel.width-dimframe.width)/2,(dimpanel.height-dimframe.height)/2);
        frameInterno.show();
    }
}
