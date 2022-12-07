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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAHUL
 */
public class ControlDiezmo extends MouseAdapter implements ActionListener{
    VistaDiezmo vistaDiezmo=new VistaDiezmo();
    DiezmoDAO diezmoDAO;
    Diezmo diezmo=new Diezmo();
    DefaultTableModel tablaModel=new DefaultTableModel();
    int id;
    List<Diezmo> lista;
    
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    List<Lideriglesia> lislider=new ArrayList<>();
    
    public ControlDiezmo(VistaDiezmo vistaDiezmo, DiezmoDAO diezmoDAO){
        this.vistaDiezmo=vistaDiezmo;
        this.diezmoDAO=diezmoDAO;
        listar();
        mostrarlider();
        inhabilitar();
        
        this.vistaDiezmo.botonagregar.addActionListener(this);
        this.vistaDiezmo.botoncancelar.addActionListener(this);
        this.vistaDiezmo.botoneliminar.addActionListener(this);
        this.vistaDiezmo.botonnuevo.addActionListener(this);
        this.vistaDiezmo.botoneditar.addActionListener(this);
        
        this.vistaDiezmo.tabladiezmo.addMouseListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(vistaDiezmo.botonagregar==ae.getSource()){
            try {
                insertar();
                limpiartabla(vistaDiezmo.tabladiezmo);
                listar();
                limpiarfield();
               
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "NO SE AGREGO");
            }
            }else if(vistaDiezmo.botoneditar==ae.getSource()){
            try {
                editar();
                limpiartabla(vistaDiezmo.tabladiezmo);
                listar();
                limpiarfield();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "NO SE MODIFICO");
            }
            }else if(vistaDiezmo.botoneliminar==ae.getSource()){
                try {
                    eliminar();
                    limpiartabla(vistaDiezmo.tabladiezmo);
                    listar();
                    limpiarfield();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "NO SE ELIMINO");
                }
            }else if(vistaDiezmo.botoncancelar==ae.getSource()){
                try {
                    limpiarfield();
                    limpiartabla(vistaDiezmo.tabladiezmo);
                    listar();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "NO SE CANCELO");
                }
            }else if(vistaDiezmo.botonnuevo==ae.getSource()){
                try {
                    limpiarfield();
                    habilitar();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "NO FUE LIMPIADO");
                }
        }
       
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        int fila=vistaDiezmo.tabladiezmo.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA");
        }else{
            id=lista.get(fila).getIddiezmo();
            int idlid=lista.get(fila).getIdlider();
            
            String tesorero=vistaDiezmo.tabladiezmo.getValueAt(fila, 0).toString();
            String ci=vistaDiezmo.tabladiezmo.getValueAt(fila, 1).toString();
            String mes=vistaDiezmo.tabladiezmo.getValueAt(fila, 2).toString();
            String ent=vistaDiezmo.tabladiezmo.getValueAt(fila, 3).toString();
            String sal=vistaDiezmo.tabladiezmo.getValueAt(fila, 4).toString();
            String saldo=vistaDiezmo.tabladiezmo.getValueAt(fila, 5).toString();
            String sala=vistaDiezmo.tabladiezmo.getValueAt(fila, 6).toString();
            String des=vistaDiezmo.tabladiezmo.getValueAt(fila, 7).toString();
            String fe=vistaDiezmo.tabladiezmo.getValueAt(fila, 8).toString();
            
            try {
                vistaDiezmo.textesoreria.setText(String.valueOf(idlid));
                vistaDiezmo.txtcarnet.setText(ci);
                vistaDiezmo.boxmes.setSelectedItem(mes);
                vistaDiezmo.txtentrada.setText(ent);
                vistaDiezmo.txtsalida.setText(sal);
                vistaDiezmo.txtanterior.setText(saldo);
                vistaDiezmo.txtactual.setText("");
                vistaDiezmo.texdescripcion.setText(des);
                
                vistaDiezmo.dateregistro.setDate(sdf.parse(fe));
                
            } catch (Exception errrr) {
            }
        }
    }
    
    
    public void mostrarlider(){
        LiderDAO ldao=new LiderDAO();
        //List<Lideriglesia> lislider=new ArrayList<>();
        lislider=ldao.mostrar();
        vistaDiezmo.boxtesorero.addItem(" " + "-"+"Seleccione nombre del tesorero");
        for(int i=0;i<lislider.size();i++){
            vistaDiezmo.boxtesorero.addItem(lislider.get(i).getIdlider()+"-"+lislider.get(i).getNombre()+" "+lislider.get(i).getApaterno());
            
        }    
            
    }
    public void listar(){
        lista=diezmoDAO.listarDiezmo();
        tablaModel=(DefaultTableModel) vistaDiezmo.tabladiezmo.getModel();
        Object obj[]=new Object[9];
        System.out.println("diezmo");
        
        for(int i=0;i<lista.size();i++){
            
            obj[0]=lista.get(i).getTesorn()+ ' '+ lista.get(i).getTesorap();
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
        vistaDiezmo.tabladiezmo.setModel(tablaModel);
    }
    public void insertar(){
        if(vistaDiezmo.textesoreria.getText().trim().isEmpty()||
                vistaDiezmo.txtcarnet.getText().trim().isEmpty()||
                vistaDiezmo.boxmes.getSelectedItem().toString().trim().isEmpty()||
                vistaDiezmo.txtentrada.getText().trim().isEmpty()||
                vistaDiezmo.txtsalida.getText().trim().isEmpty()||
                vistaDiezmo.txtanterior.getText().trim().isEmpty()||
                vistaDiezmo.txtactual.getText().trim().isEmpty()||
                vistaDiezmo.texdescripcion.getText().trim().isEmpty()||
                vistaDiezmo.dateregistro.getCalendar().toString().trim().isEmpty()){
            
            JOptionPane.showMessageDialog(null,"DEBE LLENAR TODOS LOS CAMPOS");
        }else{
            int idlider=Integer.parseInt(vistaDiezmo.textesoreria.getText());
            Lideriglesia lider= new Lideriglesia();
            //lider=lislider.stream().filter(lider->lider.getIdlider().equals(idlider));
            System.out.println("lider "+ idlider);
            //System.out.println("lista"+ lislider
                    
            diezmo.setIdlider(Integer.parseInt(vistaDiezmo.textesoreria.getText()));
            
            diezmo.setCarnet(vistaDiezmo.txtcarnet.getText());
            
            int seleccionado=vistaDiezmo.boxmes.getSelectedIndex();
            diezmo.setMes((String) vistaDiezmo.boxmes.getItemAt(seleccionado));
            
            diezmo.setEntrada(Double.parseDouble(vistaDiezmo.txtentrada.getText()));
            diezmo.setSalida(Double.parseDouble(vistaDiezmo.txtsalida.getText()));
            diezmo.setSaldoanterior(Double.parseDouble(vistaDiezmo.txtanterior.getText()));
            diezmo.setSaldoactual(Double.parseDouble(vistaDiezmo.txtactual.getText()));
            diezmo.setDescripcion(vistaDiezmo.texdescripcion.getText());
            
            Calendar calenr;
            int diar,mesr,yearr;
            calenr=vistaDiezmo.dateregistro.getCalendar();
            diar=calenr.get(Calendar.DAY_OF_MONTH);
            mesr=calenr.get(Calendar.MONTH);
            yearr=calenr.get(Calendar.YEAR)-1900;
            diezmo.setFecharegistro(new Date(yearr, mesr, diar));
            
            diezmoDAO.agregar(diezmo);
        }
        
    }
    public void editar(){
        int fila=vistaDiezmo.tabladiezmo.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA");
        }else {
            if(vistaDiezmo.textesoreria.getText().trim().isEmpty()||
                vistaDiezmo.txtcarnet.getText().trim().isEmpty()||
                vistaDiezmo.boxmes.getSelectedItem().toString().trim().isEmpty()||
                vistaDiezmo.txtentrada.getText().trim().isEmpty()||
                vistaDiezmo.txtsalida.getText().trim().isEmpty()||
                vistaDiezmo.txtanterior.getText().trim().isEmpty()||
                vistaDiezmo.txtactual.getText().trim().isEmpty()||
                vistaDiezmo.texdescripcion.getText().trim().isEmpty()||
                vistaDiezmo.dateregistro.getCalendar().toString().trim().isEmpty()){
            
            JOptionPane.showMessageDialog(null,"DEBE LLENAR TODOS LOS CAMPOS");
        }else{
            id=lista.get(fila).getIddiezmo();
            int iddiez=Integer.parseInt(vistaDiezmo.textesoreria.getText());
            
            String ci=vistaDiezmo.txtcarnet.getText();
            String mes=(String)vistaDiezmo.boxmes.getSelectedItem();
            Double ent=Double.parseDouble(vistaDiezmo.txtentrada.getText());
            Double sal=Double.parseDouble(vistaDiezmo.txtsalida.getText());
            Double saldo=Double.parseDouble(vistaDiezmo.txtanterior.getText());
            Double sala=Double.parseDouble(vistaDiezmo.txtactual.getText());
            String des=vistaDiezmo.texdescripcion.getText();
                    
            Calendar calenn;
            int dian,mesn,yearn;
            calenn=vistaDiezmo.dateregistro.getCalendar();
            dian=calenn.get(Calendar.DAY_OF_MONTH);
            mesn=calenn.get(Calendar.MONTH);
            yearn=calenn.get(Calendar.YEAR)-1900;
            Date freg=(new Date(yearn, mesn, dian));
            
            diezmo.setIddiezmo(id);
            diezmo.setIdlider(iddiez);
            diezmo.setCarnet(ci);
            diezmo.setMes(mes);
            diezmo.setEntrada(ent);
            diezmo.setSalida(sal);
            diezmo.setSaldoanterior(saldo);
            diezmo.setSaldoactual(sala);
            diezmo.setDescripcion(des);
            
            diezmoDAO.modificar(diezmo);
            }
        }
    }

    public void eliminar(){
        int fila=vistaDiezmo.tabladiezmo.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA PARA ELIMINAR");
        }else{
            diezmoDAO.eliminardiezmo(id);
            System.out.println("eliminando");
        }
    }
    public void limpiartabla(JTable tabla){
        try {
            int filas=tabla.getRowCount();
            for(int i=0;filas>i;i++){
                tablaModel.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar tabla");
        }
    }
    public void limpiarfield(){
        vistaDiezmo.textesoreria.setText("");
        vistaDiezmo.txtcarnet.setText("");
        vistaDiezmo.boxmes.setSelectedItem("");
        vistaDiezmo.txtentrada.setText("");
        vistaDiezmo.txtsalida.setText("");
        vistaDiezmo.txtanterior.setText("");
        vistaDiezmo.txtactual.setText("");
        vistaDiezmo.texdescripcion.setText("");
        
        Date fechaactual=new Date(Calendar.getInstance().getTime().getTime());
        vistaDiezmo.dateregistro.setDate(fechaactual);
    }
    public void inhabilitar(){
        vistaDiezmo.botonagregar.setEnabled(false);
        vistaDiezmo.botoncancelar.setEnabled(false);
        vistaDiezmo.botoneliminar.setEnabled(false);
        vistaDiezmo.botoneditar.setEnabled(false);
        vistaDiezmo.botonreporte.setEnabled(false);
        
        vistaDiezmo.texdescripcion.setEnabled(false);
        vistaDiezmo.txtactual.setEnabled(false);
        vistaDiezmo.txtanterior.setEnabled(false);
        vistaDiezmo.txtcarnet.setEnabled(false);
        vistaDiezmo.txtentrada.setEnabled(false);
        vistaDiezmo.txtsalida.setEnabled(false);
        vistaDiezmo.dateregistro.setEnabled(false);
        vistaDiezmo.boxmes.setEnabled(false);
        vistaDiezmo.boxtesorero.setEnabled(false);
    }
    public void habilitar(){
        vistaDiezmo.botonagregar.setEnabled(true);
        vistaDiezmo.botoncancelar.setEnabled(true);
        vistaDiezmo.botoneliminar.setEnabled(true);
        vistaDiezmo.botoneditar.setEnabled(true);
        vistaDiezmo.botonreporte.setEnabled(true);
        
        vistaDiezmo.texdescripcion.setEnabled(true);
        vistaDiezmo.txtactual.setEnabled(true);
        vistaDiezmo.txtanterior.setEnabled(true);
        vistaDiezmo.txtcarnet.setEnabled(true);
        vistaDiezmo.txtentrada.setEnabled(true);
        vistaDiezmo.txtsalida.setEnabled(true);
        vistaDiezmo.dateregistro.setEnabled(true);
        vistaDiezmo.boxmes.setEnabled(true);
        vistaDiezmo.boxtesorero.setEnabled(true);
    }
    
    
    //////// calcular saldo actual
        
        
}
