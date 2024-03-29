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
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAHUL
 */
public class ControlOfrenda extends MouseAdapter implements ActionListener{
    VistaOfrenda vistaOfrenda=new VistaOfrenda();
    OfrendaDAO ofrendaDAO;
    Ofrenda ofrenda=new Ofrenda();
    DefaultTableModel tablaModel=new DefaultTableModel();
    int id;
    List<Ofrenda> lista;
    
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    
    ExcelExpo exp;
    
    public ControlOfrenda(VistaOfrenda vistaDiezmo, OfrendaDAO diezmoDAO){
        this.vistaOfrenda=vistaDiezmo;
        this.ofrendaDAO=diezmoDAO;
        listar();
        mostrarlider();
        inhabilitar();
        
        this.vistaOfrenda.botonagregar.addActionListener(this);
        this.vistaOfrenda.botoncancelar.addActionListener(this);
        this.vistaOfrenda.botoneliminar.addActionListener(this);
        this.vistaOfrenda.botonnuevo.addActionListener(this);
        this.vistaOfrenda.botoneditar.addActionListener(this);
        this.vistaOfrenda.botonbuscar.addActionListener(this);
        this.vistaOfrenda.botonlistar.addActionListener(this);
        
        this.vistaOfrenda.botonreporte.addActionListener(this);
        
        this.vistaOfrenda.tablaofrenda.addMouseListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(vistaOfrenda.botonagregar==ae.getSource()){
            try {
                insertar();
                limpiartabla(vistaOfrenda.tablaofrenda);
                listar();
                limpiarfield();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR AL AGREGAR");
            }
            }else if(vistaOfrenda.botoneditar==ae.getSource()){
            try {
                editar();
                limpiartabla(vistaOfrenda.tablaofrenda);
                listar();
                limpiarfield();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR AL MODIFICAR");
            }
            }else if(vistaOfrenda.botoneliminar==ae.getSource()){
                try {
                    eliminar();
                    limpiartabla(vistaOfrenda.tablaofrenda);
                    listar();
                    limpiarfield();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR");
                }
            }else if(vistaOfrenda.botoncancelar==ae.getSource()){
                try {
                    limpiarfield();
                    limpiartabla(vistaOfrenda.tablaofrenda);
                    listar();
                    inhabilitar();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "ERROR AL CANCELAR");
                }
            }else if(vistaOfrenda.botonnuevo==ae.getSource()){
                try {
                    limpiarfield();
                    habilitar();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "ERROR AL LIMPIAR");
                }
            }else if(vistaOfrenda.botonbuscar==ae.getSource()){
                try {
                    buscar(vistaOfrenda.txtbuscar.getText());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "INGRESE DATOS PARA BUSCAR");
                }
            }else if(vistaOfrenda.botonlistar==ae.getSource()){
                try {
                    vistaOfrenda.txtbuscar.setText("");
                    limpiartabla(vistaOfrenda.tablaofrenda);
                    listar();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "ERROR AL MOSTRAR LISTA");
                }
            }else if(vistaOfrenda.botonreporte==ae.getSource()){
                try {
                    excel();
            } catch (Exception e) {
            }
            }
       
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        int fila=vistaOfrenda.tablaofrenda.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA");
        }else{
            id=lista.get(fila).getIdofrenda();
            //int idlid=lista.get(fila).getIdlider();
            
            String tesorero=vistaOfrenda.tablaofrenda.getValueAt(fila, 0).toString();
            String ci=vistaOfrenda.tablaofrenda.getValueAt(fila, 1).toString();
            String mes=vistaOfrenda.tablaofrenda.getValueAt(fila, 2).toString();
            String ent=vistaOfrenda.tablaofrenda.getValueAt(fila, 3).toString();
            String sal=vistaOfrenda.tablaofrenda.getValueAt(fila, 4).toString();
            String saldo=vistaOfrenda.tablaofrenda.getValueAt(fila, 5).toString();
            String sala=vistaOfrenda.tablaofrenda.getValueAt(fila, 6).toString();
            String des=vistaOfrenda.tablaofrenda.getValueAt(fila, 7).toString();
            String fe=vistaOfrenda.tablaofrenda.getValueAt(fila, 8).toString();
            
            try {
                vistaOfrenda.txttesorero.setText(tesorero);
                vistaOfrenda.txtcarnet.setText(ci);
                vistaOfrenda.boxmes.setSelectedItem(mes);
                vistaOfrenda.txtentrada.setText(ent);
                vistaOfrenda.ttxtsalida.setText(sal);
                vistaOfrenda.txtanterior.setText(saldo);
                vistaOfrenda.txtactual.setText(sala);
                vistaOfrenda.texdescripcion.setText(des);
                
                vistaOfrenda.dateregistro.setDate(sdf.parse(fe));
                System.out.println(fe+" fechita");
            } catch (Exception errrr) {
            }
        }
    }
    
    
    public void mostrarlider(){
        LiderDAO ldao=new LiderDAO();
        List<Lideriglesia> lislider=new ArrayList<>();
        lislider=ldao.mostrar();
        vistaOfrenda.boxtesorero.addItem(" "+" "+"Seleccione nombre del tesorero");
        for(int i=0;i<lislider.size();i++){
            vistaOfrenda.boxtesorero.addItem(lislider.get(i).getIdlider()+" "+lislider.get(i).getNombre()+" "+lislider.get(i).getApellidos());
            
        }    
            
    }
    public void listar(){
        lista=ofrendaDAO.listarOfrenda();
        tablaModel=(DefaultTableModel) vistaOfrenda.tablaofrenda.getModel();
        Object obj[]=new Object[9];
        System.out.println("ofrenda");
        
        for(int i=0;i<lista.size();i++){
            
            obj[0]=lista.get(i).getTesorero();
            obj[1]=lista.get(i).getCarnet();
            obj[2]=lista.get(i).getMes();
            obj[3]=lista.get(i).getEntrada();
            obj[4]=lista.get(i).getSalida();
            obj[5]=lista.get(i).getSaldoanterior();
            obj[6]=lista.get(i).getSaldoactual();
            obj[7]=lista.get(i).getDescripcion();
            obj[8]=sdf.format(lista.get(i).getFecharegistro());
            
            tablaModel.addRow(obj);
            
        }
        vistaOfrenda.tablaofrenda.setModel(tablaModel);
    }
    public void insertar(){
        if(vistaOfrenda.txttesorero.getText().trim().isEmpty()||
                vistaOfrenda.txtcarnet.getText().trim().isEmpty()||
                vistaOfrenda.boxmes.getSelectedItem().toString().trim().isEmpty()||
                vistaOfrenda.txtentrada.getText().trim().isEmpty()||
                vistaOfrenda.ttxtsalida.getText().trim().isEmpty()||
                vistaOfrenda.txtanterior.getText().trim().isEmpty()||
                vistaOfrenda.txtactual.getText().trim().isEmpty()||
                vistaOfrenda.texdescripcion.getText().trim().isEmpty()||
                vistaOfrenda.dateregistro.getCalendar().toString().trim().isEmpty()){
            
            JOptionPane.showMessageDialog(null,"DEBE LLENAR TODOS LOS CAMPOS");
        }else{
            String tmp=(String) vistaOfrenda.boxtesorero.getSelectedItem();
            String [] aux=tmp.split(" ");
            String idlid=aux[0];
            /////DATOS SACADOS DE LIDER
            ofrenda.setIdlider(Integer.parseInt(idlid));
            
            ofrenda.setTesorero(vistaOfrenda.txttesorero.getText());
            ofrenda.setCarnet(vistaOfrenda.txtcarnet.getText());
            
            int seleccionado=vistaOfrenda.boxmes.getSelectedIndex();
            ofrenda.setMes((String) vistaOfrenda.boxmes.getItemAt(seleccionado));
            
            ofrenda.setEntrada(Double.parseDouble(vistaOfrenda.ttxtsalida.getText()));
            ofrenda.setSalida(Double.parseDouble(vistaOfrenda.txtentrada.getText()));
            ofrenda.setSaldoanterior(Double.parseDouble(vistaOfrenda.txtanterior.getText()));
            ofrenda.setSaldoactual(Double.parseDouble(vistaOfrenda.txtactual.getText()));
            ofrenda.setDescripcion(vistaOfrenda.texdescripcion.getText());
            
            Calendar calenr;
            int diar,mesr,yearr;
            calenr=vistaOfrenda.dateregistro.getCalendar();
            diar=calenr.get(Calendar.DAY_OF_MONTH);
            mesr=calenr.get(Calendar.MONTH);
            yearr=calenr.get(Calendar.YEAR)-1900;
            ofrenda.setFecharegistro(new Date(yearr, mesr, diar));
            
            ofrendaDAO.añadir(ofrenda);
        }
        
    }
    public void editar(){
        int fila=vistaOfrenda.tablaofrenda.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA");
        }else {
            if(vistaOfrenda.txttesorero.getText().trim().isEmpty()||
                vistaOfrenda.txtcarnet.getText().trim().isEmpty()||
                vistaOfrenda.boxmes.getSelectedItem().toString().trim().isEmpty()||
                vistaOfrenda.txtentrada.getText().trim().isEmpty()||
                vistaOfrenda.ttxtsalida.getText().trim().isEmpty()||
                vistaOfrenda.txtanterior.getText().trim().isEmpty()||
                vistaOfrenda.txtactual.getText().trim().isEmpty()||
                vistaOfrenda.texdescripcion.getText().trim().isEmpty()||
                vistaOfrenda.dateregistro.getCalendar().toString().trim().isEmpty()){
            
            JOptionPane.showMessageDialog(null,"DEBE LLENAR TODOS LOS CAMPOS");
        }else{
            id=lista.get(fila).getIdofrenda();
            //int iddiez=Integer.parseInt(vistaOfrenda.txttesorero.getText());
            
            String tes=vistaOfrenda.txttesorero.getText();
            String ci=vistaOfrenda.txtcarnet.getText();
            String mes=(String)vistaOfrenda.boxmes.getSelectedItem();
            Double ent=Double.parseDouble(vistaOfrenda.txtentrada.getText());
            Double sal=Double.parseDouble(vistaOfrenda.ttxtsalida.getText());
            Double saldo=Double.parseDouble(vistaOfrenda.txtanterior.getText());
            Double sala=Double.parseDouble(vistaOfrenda.txtactual.getText());
            String des=vistaOfrenda.texdescripcion.getText();
                    
            Calendar calenn;
            int dian,mesn,yearn;
            calenn=vistaOfrenda.dateregistro.getCalendar();
            dian=calenn.get(Calendar.DAY_OF_MONTH);
            mesn=calenn.get(Calendar.MONTH);
            yearn=calenn.get(Calendar.YEAR)-1900;
            Date freg=(new Date(yearn, mesn, dian));
            
            ofrenda.setIdofrenda(id);
            ofrenda.setTesorero(tes);
            ofrenda.setCarnet(ci);
            ofrenda.setMes(mes);
            ofrenda.setEntrada(ent);
            ofrenda.setSalida(sal);
            ofrenda.setSaldoanterior(saldo);
            ofrenda.setSaldoactual(sala);
            ofrenda.setDescripcion(des);
            ofrenda.setFecharegistro(freg);
            
            ofrendaDAO.modificar(ofrenda);
            }
        }
    }

    public void eliminar(){
        int fila=vistaOfrenda.tablaofrenda.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA PARA ELIMINAR");
        }else{
            ofrendaDAO.eliminarOfrenda(id);
            System.out.println("eliminando");
        }
    }
    public void buscar(String buscando){
        if(vistaOfrenda.txtbuscar.getText().length()==0){
            JOptionPane.showMessageDialog(null, "INGRESE UN DATO PARA BUSCAR");
        }else{
            tablaModel=ofrendaDAO.buscarOfrenda(buscando);
            vistaOfrenda.tablaofrenda.setModel(tablaModel);
        }
    }
    public void excel(){
        try {
            exp= new ExcelExpo();
            exp.Exportar(vistaOfrenda.tablaofrenda);
        } catch (IOException ex) {
            Logger.getLogger(VistaListaMembrecia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void limpiartabla(JTable tabla){
        try {
            int filas=tabla.getRowCount();
            for(int i=0;filas>i;i++){
                tablaModel.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL LIMPIAR LA TABLA");
        }
    }
    public void limpiarfield(){
        vistaOfrenda.txttesorero.setText("");
        vistaOfrenda.txtcarnet.setText("");
        vistaOfrenda.boxmes.setSelectedItem("");
        vistaOfrenda.ttxtsalida.setText("");
        vistaOfrenda.txtentrada.setText("");
        vistaOfrenda.txtanterior.setText("");
        vistaOfrenda.txtactual.setText("");
        vistaOfrenda.texdescripcion.setText("");
        
        Date fechaactual=new Date(Calendar.getInstance().getTime().getTime());
        vistaOfrenda.dateregistro.setDate(fechaactual);
    }
    public void inhabilitar(){
        vistaOfrenda.botonagregar.setEnabled(false);
        vistaOfrenda.botoncancelar.setEnabled(false);
        vistaOfrenda.botoneliminar.setEnabled(false);
        vistaOfrenda.botoneditar.setEnabled(false);
        vistaOfrenda.botonreporte.setEnabled(false);
        vistaOfrenda.botonSuma.setEnabled(false);
        
        vistaOfrenda.texdescripcion.setEnabled(false);
        vistaOfrenda.txtactual.setEnabled(false);
        vistaOfrenda.txtanterior.setEnabled(false);
        vistaOfrenda.txtcarnet.setEnabled(false);
        vistaOfrenda.ttxtsalida.setEnabled(false);
        vistaOfrenda.txtentrada.setEnabled(false);
        vistaOfrenda.dateregistro.setEnabled(false);
        vistaOfrenda.boxmes.setEnabled(false);
        vistaOfrenda.boxtesorero.setEnabled(false);
    }
    public void habilitar(){
        vistaOfrenda.botonagregar.setEnabled(true);
        vistaOfrenda.botoncancelar.setEnabled(true);
        vistaOfrenda.botoneliminar.setEnabled(true);
        vistaOfrenda.botoneditar.setEnabled(true);
        vistaOfrenda.botonreporte.setEnabled(true);
        vistaOfrenda.botonSuma.setEnabled(true);
        
        vistaOfrenda.texdescripcion.setEnabled(true);
        vistaOfrenda.txtactual.setEnabled(true);
        vistaOfrenda.txtanterior.setEnabled(true);
        vistaOfrenda.txtcarnet.setEnabled(true);
        vistaOfrenda.ttxtsalida.setEnabled(true);
        vistaOfrenda.txtentrada.setEnabled(true);
        vistaOfrenda.dateregistro.setEnabled(true);
        vistaOfrenda.boxmes.setEnabled(true);
        vistaOfrenda.boxtesorero.setEnabled(true);
    }
}
