/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import Vista.*;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAHUL
 */
public class ControlIglesia {
    VistaLiderIglesia vistaLiderIglesia=new VistaLiderIglesia();
    LiderDAO ldao;
    Lideriglesia lideriglesia=new Lideriglesia();
    DefaultTableModel tablamodel=new DefaultTableModel();
    int id;
    List<Lideriglesia> lista;
    
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    public ControlIglesia(VistaLiderIglesia vlm, LiderDAO aO){
        this.vistaLiderIglesia=vlm;
        this.ldao=aO;
        mostrarlista();
    }
    public void mostrarlista(){
        lista=ldao.mostrar();
        tablamodel=(DefaultTableModel) vistaLiderIglesia.tablaiglesia.getModel();
        Object obj[]=new Object[7];
        System.out.println("lista Lider");
        for(int i=0;i<lista.size();i++){
            
            obj[0]=lista.get(i).getNombre();
            obj[1]=lista.get(i).getApaterno();
            obj[2]=lista.get(i).getAmaterno();
            obj[3]=lista.get(i).getCi();
            obj[4]=lista.get(i).getCargo();
            obj[5]=sdf.format(lista.get(i).getIniciogestion());
            obj[6]=sdf.format(lista.get(i).getFingestion());
            
            tablamodel.addRow(obj);
        }
        vistaLiderIglesia.tablaiglesia.setModel(tablamodel);
    }
}
