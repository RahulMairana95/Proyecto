/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;

/**
 *
 * @author LENOVO
 */
public class MiembrosActivos {
   private int idactivos;
   private int idmembrecia;
   private int ndiezmo;
   private String numrecibo;
   private String nombre;
   private String apellidos;
   private double cantidad;
   private Date fecha;
   
   public MiembrosActivos(){}

    public MiembrosActivos(int idactivos, int idmembrecia, int ndiezmo, String numrecibo, String nombre, String apellidos, double cantidad, Date fecha) {
        this.idactivos = idactivos;
        this.idmembrecia = idmembrecia;
        this.ndiezmo = ndiezmo;
        this.numrecibo = numrecibo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public int getIdactivos() {
        return idactivos;
    }

    public void setIdactivos(int idactivos) {
        this.idactivos = idactivos;
    }

    public int getIdmembrecia() {
        return idmembrecia;
    }

    public void setIdmembrecia(int idmembrecia) {
        this.idmembrecia = idmembrecia;
    }

    public int getNdiezmo() {
        return ndiezmo;
    }

    public void setNdiezmo(int ndiezmo) {
        this.ndiezmo = ndiezmo;
    }

    public String getNumrecibo() {
        return numrecibo;
    }

    public void setNumrecibo(String numrecibo) {
        this.numrecibo = numrecibo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
   
}
