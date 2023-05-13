/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import Vista.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAHUL
 */
public class ControlListaDiezmo {
    VistaListaDiezmo vistaListaDiezmo=new VistaListaDiezmo();
    DiezmoDAO dAO;
    Diezmo die=new Diezmo();
    DefaultTableModel tablaModel=new DefaultTableModel();
    int id;
    List<Diezmo> lista;
    public ControlListaDiezmo(VistaListaDiezmo vlm, DiezmoDAO aO){
        this.vistaListaDiezmo=vlm;
        this.dAO=aO;
        listar();
    }
    public void listar(){
        lista=dAO.listarDiezmo();
        tablaModel=(DefaultTableModel) vistaListaDiezmo.tabladiezmo.getModel();
        Object obj[]=new Object[11];
        for(int i=0;i<lista.size();i++){
            
            //obj[0]=lista.get(i).getTesorn()+ ' '+ lista.get(i).getTesorap();
            obj[1]=lista.get(i).getCarnet();
            obj[2]=lista.get(i).getMes();
            obj[3]=lista.get(i).getEntrada();
            obj[4]=lista.get(i).getSalida();
            obj[5]=lista.get(i).getSaldoanterior();
            obj[6]=lista.get(i).getSaldoactual();
            obj[7]=lista.get(i).getDescripcion();
            obj[8]=lista.get(i).getFecharegistro();
            
            tablaModel.addRow(obj);
        }
        vistaListaDiezmo.tabladiezmo.setModel(tablaModel);
    }
    public void editable(){
        //vistaListaDiezmo.set
    }
}
