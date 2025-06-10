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
        ventanaPrincipal.itemegresos.addActionListener(this);
        ventanaPrincipal.itemlideriglesia.addActionListener(this);
        ventanaPrincipal.itemRegistro.addActionListener(this);
        ventanaPrincipal.itemLista.addActionListener(this);
        ventanaPrincipal.itemIglesia.addActionListener(this);
        ventanaPrincipal.itemreporteingresos.addActionListener(this);
        ventanaPrincipal.itemreporteegresos.addActionListener(this);
        ventanaPrincipal.itemingresos.addActionListener(this);
        ventanaPrincipal.itemministerio.addActionListener(this);
        ventanaPrincipal.iteMinis.addActionListener(this);
        ventanaPrincipal.itemInfo.addActionListener(this);
        //ventanaPrincipal.itemSalir.addActionListener(this);
        //BOTONES DE INFORMACION DE LA VENTANA PRINCIPAL
        ventanaPrincipal.botonUce.addActionListener(this);
        ventanaPrincipal.botonJuve.addActionListener(this);
        ventanaPrincipal.botonOansa.addActionListener(this);
        ventanaPrincipal.botonmisiones.addActionListener(this);
        ventanaPrincipal.botonfemenil.addActionListener(this);
        ventanaPrincipal.botonshiret.addActionListener(this);
        ventanaPrincipal.botoncdi.addActionListener(this);
        ventanaPrincipal.botondominical.addActionListener(this);
        
        
        ventanaPrincipal.itemInfo.addActionListener(this);
        
        ventanaPrincipal.idusuarioaminnis.setText(admin.getIdadmin()+ "");
        ventanaPrincipal.idusuarioaminnis.setVisible(false);
        
        ventanaPrincipal.lblusuario.setVisible(false);
        
        if (admin.getUsuario().equalsIgnoreCase("Pastor")||admin.getUsuario().equalsIgnoreCase("superadmin")) 
            ventanaPrincipal.itemRegistro.setEnabled(true);
        else
          ventanaPrincipal.itemRegistro.setEnabled(false);
        
        if(admin.getUsuario().equalsIgnoreCase("Tesorero")){
            ventanaPrincipal.itemreporteingresos.setEnabled(true);
            ventanaPrincipal.itemreporteegresos.setEnabled(true);
            ventanaPrincipal.itemingresos.setEnabled(true);
            ventanaPrincipal.itemegresos.setEnabled(true);
            ventanaPrincipal.itemLista.setEnabled(true);
            ventanaPrincipal.itemlideriglesia.setEnabled(false);
            ventanaPrincipal.itemministerio.setEnabled(false);
            ventanaPrincipal.itemIglesia.setEnabled(true);
            ventanaPrincipal.iteMinis.setEnabled(true);
            ventanaPrincipal.itemmiembros.setEnabled(false);
            
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ventanaPrincipal.itemLista==ae.getSource()){
            listarMiembros();
        }else if(ventanaPrincipal.itemlideriglesia==ae.getSource()){
            agregarLider();
        }else if(ventanaPrincipal.itemRegistro==ae.getSource()){
            agregarAdmin();
        }else if(ventanaPrincipal.itemmiembros==ae.getSource()){
            agregarMembrecia();
        }else if(ventanaPrincipal.itemIglesia==ae.getSource()){
            listarLideresGlesia();
        }else if(ventanaPrincipal.itemministerio==ae.getSource()){
            ministerios();
        }else if(ventanaPrincipal.iteMinis==ae.getSource()){
            listamin();///////BOTONES DE INFORMACION
        }else if(ventanaPrincipal.botonUce==ae.getSource()){
            uce();
        }else if(ventanaPrincipal.botonJuve==ae.getSource()){
            minJuve();
        }else if(ventanaPrincipal.botonOansa==ae.getSource()){
            OANSA();
        }else if(ventanaPrincipal.botonfemenil==ae.getSource()){
            femenil();
        }else if(ventanaPrincipal.botonshiret==ae.getSource()){
            prejuve();
        }else if(ventanaPrincipal.botonmisiones==ae.getSource()){
            misiones();
        }else if(ventanaPrincipal.botondominical==ae.getSource()){
            dominical();
        }else if(ventanaPrincipal.botoncdi==ae.getSource()){
            CDI();
        }else if(ventanaPrincipal.itemInfo==ae.getSource()){
            infoIglesia();
        }else if(ventanaPrincipal.itemingresos==ae.getSource()){
            agregarIngreso();
        }else if(ventanaPrincipal.itemegresos==ae.getSource()){
            agregarEgreso();
        }else if(ventanaPrincipal.itemreporteingresos==ae.getSource()){
            listaIngresos();
        }else if(ventanaPrincipal.itemreporteegresos==ae.getSource()){
            listaEgresos();
        }
        
    }
    
    public void agregarMembrecia(){
        VistaMembrecia vistaMembrecia=new VistaMembrecia();
        MembreciaDAO membreciaDAO=new MembreciaDAO(vistaMembrecia);
        ControlMembrecia controlMembrecia=new ControlMembrecia(vistaMembrecia, membreciaDAO);
        vistaMembrecia.setTitle("REGISTRO DE MIEMBROS");
        
        centarFrameInterno(vistaMembrecia);
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
        AdministradorDAO adminDAO=new AdministradorDAO(vistaRegistro);
        ControlAdmin controlAdmin=new ControlAdmin(vistaRegistro, adminDAO);
        vistaRegistro.setTitle("REGISTRO DE USUARIOS");
        
        centarFrameInterno(vistaRegistro);
    }
    
    public void agregarIngreso(){
        VistaIngreso vistaIngreso= new VistaIngreso();
        IngresoDAO ingresoDAO= new IngresoDAO(vistaIngreso);
        ControlIngreso ingreso= new ControlIngreso(vistaIngreso, ingresoDAO);
        vistaIngreso.setTitle("REGISTRO DE INGRESOS");
        
        centarFrameInterno(vistaIngreso);
    }
    public void agregarEgreso(){
        VistaEgreso vistaegreso= new VistaEgreso();
        EgresoDAO egresoDAO= new EgresoDAO(vistaegreso);
        ControlEgreso egreso= new ControlEgreso(vistaegreso, egresoDAO);
        vistaegreso.setTitle("REGISTRO DE EGRESOS");
        
        centarFrameInterno(vistaegreso);
    }
    public void listaIngresos(){
        VistaListaIngresos vistaListaIngresos = new VistaListaIngresos();
        IngresoDAO ingresoDAO= new IngresoDAO();
        ControlListaIngresos ingreso= new ControlListaIngresos(vistaListaIngresos, ingresoDAO);
        vistaListaIngresos.setTitle("REPORTE DE INGRESOS");
        
        centarFrameInterno(vistaListaIngresos);
       
    }
    public void listaEgresos(){
        VistaListaEgresos vistaListaEgresos = new VistaListaEgresos();
        EgresoDAO egresoDAO= new EgresoDAO();
        ControlListaEgreso egreso = new ControlListaEgreso(vistaListaEgresos, egresoDAO);
        vistaListaEgresos.setTitle("REPORTE DE EGRESOS");
        
        centarFrameInterno(vistaListaEgresos);
       
    }
    public void listarMiembros(){
        VistaListaMembrecia vistaListaMembrecia=new VistaListaMembrecia();
        MembreciaDAO membreciaDAO=new MembreciaDAO();
        ControlLista controlLista=new ControlLista(vistaListaMembrecia, membreciaDAO);
        vistaListaMembrecia.setTitle("LISTA DE MIEMBROS BAUTIZADOS");
        
        centarFrameInterno(vistaListaMembrecia);
       
    }
    public void listarLideresGlesia(){
        VistaListaLider vistaLiderIglesia=new VistaListaLider();
        LiderDAO liderDAO=new LiderDAO();
        ControlIglesia controlIglesia=new ControlIglesia(vistaLiderIglesia, liderDAO);
        vistaLiderIglesia.setTitle("LISTA DE LIDERES DE LA IGLESIA");
        
        centarFrameInterno(vistaLiderIglesia);
       
    }
    
    
    public void ministerios(){
        VistaLiderMin vlm=new VistaLiderMin();
        MinDAO aO=new MinDAO(vlm);
        ControlMin cm=new ControlMin(vlm,aO);
        vlm.setTitle("REGISTRO DE MINISTERIOS");
        
        centarFrameInterno(vlm);
    }
    public void listamin(){
        VistaListaLiderMin vistaLiderm=new VistaListaLiderMin();
        MinDAO lider=new MinDAO();
        ControListaMin control=new ControListaMin(vistaLiderm, lider);
        vistaLiderm.setTitle("LISTA DE LIDERES DE MINISTERIOS");
        
        centarFrameInterno(vistaLiderm);
       
    }
    public void uce(){
        //VistaListaLiderMin vistaLiderm=new VistaListaLiderMin();
        VistaUCE vistaUCE=new VistaUCE();
        //MinDAO lider=new MinDAO();
        //ControListaMin control=new ControListaMin(vistaLiderm, lider);
        vistaUCE.setTitle("UNIÓN CRISTIANA EVANGÉLICA");
        
        centarFrameInterno(vistaUCE);
    }
   public void minJuve(){
       VistaMinJuve vistaJuve=new VistaMinJuve();
       vistaJuve.setTitle("MINISTERIO JUVENIL");
       centarFrameInterno(vistaJuve);
   }
   public void OANSA(){
       VistaOANSA vistaOANSA=new VistaOANSA();
       vistaOANSA.setTitle("OANSA");
       centarFrameInterno(vistaOANSA);
   }
   public void femenil(){
       VistaMinFem fem= new VistaMinFem();
       fem.setTitle("MINISTERIO FEMENIL");
       centarFrameInterno(fem);
   }
   public void prejuve(){
       VistaMinPre minPre=new VistaMinPre();
       minPre.setTitle("MINISTERIO PREJUVENIL");
       centarFrameInterno(minPre);
   }
   public void juvenil(){
       VistaMinJuve juve=new VistaMinJuve();
       juve.setTitle("OANSA");
       centarFrameInterno(juve);
   }
   public void misiones(){
       VistaMisiones misiones= new VistaMisiones();
       misiones.setTitle("EVANGELISMO Y MISIONES");
       centarFrameInterno(misiones);
   }
   public void dominical(){
       VistaDominical dominical=new VistaDominical();
       dominical.setTitle("ESCUELA DOMINICAL");
       centarFrameInterno(dominical);
   }
   public void CDI(){
       VistaCDI cDI=new VistaCDI();
       cDI.setTitle("CDI");
       centarFrameInterno(cDI);
   }
   public void infoIglesia(){
       VistaNJM jM=new VistaNJM();
       jM.setTitle("SERVICIOS DE REUNIONES");
       centarFrameInterno(jM);
   }
    public void centarFrameInterno(JInternalFrame frameInterno){
        panel.add(frameInterno);
        Dimension dimpanel=panel.getSize();
        Dimension dimframe=frameInterno.getSize();
        frameInterno.setLocation((dimpanel.width-dimframe.width)/2,(dimpanel.height-dimframe.height)/2);
        frameInterno.show();
    }
}
