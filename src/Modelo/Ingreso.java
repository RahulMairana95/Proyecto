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
public class Ingreso {
    private int idingreso;
    private int idmembrecia;
    private int idlider;
    private Date fecha;
    private String descripcion;
    private Double monto;
    private String tipo_ingreso;
    private String nombreMiembro;
    private String nombreLider;
// getters y setters

    public Ingreso(){}
    
    public Ingreso(int idingreso, int idmembrecia, int idlider, Date fecha, 
            String descripcion, Double monto, String tipo_ingreso, 
            String nombreMiembro, String nombreLider) {
        
        this.idingreso = idingreso;
        this.idmembrecia = idmembrecia;
        this.idlider = idlider;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.monto = monto;
        this.tipo_ingreso = tipo_ingreso;
        this.nombreMiembro = nombreMiembro;
        this.nombreLider = nombreLider;
    }

    public int getIdingreso() {
        return idingreso;
    }

    public void setIdingreso(int idingreso) {
        this.idingreso = idingreso;
    }

    public int getIdmembrecia() {
        return idmembrecia;
    }

    public void setIdmembrecia(int idmembrecia) {
        this.idmembrecia = idmembrecia;
    }

    public int getIdlider() {
        return idlider;
    }

    public void setIdlider(int idlider) {
        this.idlider = idlider;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getTipo_ingreso() {
        return tipo_ingreso;
    }

    public void setTipo_ingreso(String tipo_ingreso) {
        this.tipo_ingreso = tipo_ingreso;
    }

    public String getNombreMiembro() {
        return nombreMiembro;
    }

    public void setNombreMiembro(String nombreMiembro) {
        this.nombreMiembro = nombreMiembro;
    }

    public String getNombreLider() {
        return nombreLider;
    }

    public void setNombreLider(String nombreLider) {
        this.nombreLider = nombreLider;
    }

    
    
}
