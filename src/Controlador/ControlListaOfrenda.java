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
public class ControlListaOfrenda {
    VistaListaOfrenda vistaListaofrenda=new VistaListaOfrenda();
    OfrendaDAO dAO;
    Ofrenda die=new Ofrenda();
    DefaultTableModel tablaModel=new DefaultTableModel();
    int id;
    List<Ofrenda> lista;
    public ControlListaOfrenda(VistaListaOfrenda vlm, OfrendaDAO aO){
        this.vistaListaofrenda=vlm;
        this.dAO=aO;
        listar();
    }
    public void listar(){
        lista=dAO.listarOfrenda();
        tablaModel=(DefaultTableModel) vistaListaofrenda.tablaofrenda.getModel();
        Object obj[]=new Object[9];
        for(int i=0;i<lista.size();i++){
            
            obj[0]=lista.get(i).getTesorn()+ ' '+ lista.get(i).getTesorap();
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
        vistaListaofrenda.tablaofrenda.setModel(tablaModel);
    }
}
