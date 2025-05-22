/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author LENOVO
 */
public class ComboItem {
    private int id;
    private String nombre;

    public ComboItem(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return nombre; // Esto es lo que se muestra en el JComboBox
    }
    public String getNombre(){
        return nombre;
    }
}

