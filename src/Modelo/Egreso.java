/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;
/**
 *
 * @author LENOVO
 */
public class Egreso {
    private int idegreso;
    private int idlider;
    private Date fecha;
    private String tipo_egreso;
    private Double monto;
    private String descripcion;
    private String motivo;
    private String metodo_de_pago;
    private String nombreLider;
    
    public Egreso(){}

    public Egreso(int idegreso, int idlider, Date fecha, String tipo_egreso, 
            Double monto, String descripcion, String motivo, String metodo_de_pago, 
            String nombreLider) {
        this.idegreso = idegreso;
        this.idlider = idlider;
        this.fecha = fecha;
        this.tipo_egreso = tipo_egreso;
        this.monto = monto;
        this.descripcion = descripcion;
        this.motivo = motivo;
        this.metodo_de_pago = metodo_de_pago;
        this.nombreLider = nombreLider;
    }

    public int getIdegreso() {
        return idegreso;
    }

    public void setIdegreso(int idegreso) {
        this.idegreso = idegreso;
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

    public String getTipo_egreso() {
        return tipo_egreso;
    }

    public void setTipo_egreso(String tipo_egreso) {
        this.tipo_egreso = tipo_egreso;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getMetodo_de_pago() {
        return metodo_de_pago;
    }

    public void setMetodo_de_pago(String metodo_de_pago) {
        this.metodo_de_pago = metodo_de_pago;
    }

    public String getNombreLider() {
        return nombreLider;
    }

    public void setNombreLider(String nombreLider) {
        this.nombreLider = nombreLider;
    }
    
    
}
