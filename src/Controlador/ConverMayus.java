/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Vista.*;
/**
 *
 * @author LENOVO
 */
public class ConverMayus {
    //istaMembrecia vsm;
    
    /*public static void main(String[] args){
        String nombre="raul";
        
        System.out.println(convertirMayus(nombre));
    }*/
    public static String convertirMayus(String parametro){
        String atg="";
        char l1 =' ';
        char c=' ';
        
        for(int i=0;i<parametro.length();i++){
            l1=parametro.charAt(0);
            c=parametro.charAt(i);
            
            if(l1==' '){
                atg="Digite sin espacio";
            }else if(!Character.isLetter(l1)){
                atg="Empezar con letra"+ l1;
            }
            if(i==0){
                c=Character.toUpperCase(c);
            }
            atg+=c;
        }
        
        return atg;
    }
}
