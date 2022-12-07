/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;

/**
 *
 * @author RAHUL
 */
public class Lideriglesia {
    private int idlider;
    private int idmembrecia;
    private String nombre;
    private String apaterno;
    private String amaterno;
    private String ci;
    private String cargo;
    private Date iniciogestion;
    private Date fingestion;
    
    public Lideriglesia(){}

    public Lideriglesia(int idlider, int idmembrecia, String nombre, String apaterno, String amaterno, String ci, String cargo, Date iniciogestion, Date fingestion) {
        this.idlider = idlider;
        this.idmembrecia = idmembrecia;
        this.nombre = nombre;
        this.apaterno = apaterno;
        this.amaterno = amaterno;
        this.ci = ci;
        this.cargo = cargo;
        this.iniciogestion = iniciogestion;
        this.fingestion = fingestion;
    }

    public int getIdlider() {
        return idlider;
    }

    public void setIdlider(int idlider) {
        this.idlider = idlider;
    }

    public int getIdmembrecia() {
        return idmembrecia;
    }

    public void setIdmembrecia(int idmembrecia) {
        this.idmembrecia = idmembrecia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApaterno() {
        return apaterno;
    }

    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }

    public String getAmaterno() {
        return amaterno;
    }

    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Date getIniciogestion() {
        return iniciogestion;
    }

    public void setIniciogestion(Date iniciogestion) {
        this.iniciogestion = iniciogestion;
    }

    public Date getFingestion() {
        return fingestion;
    }

    public void setFingestion(Date fingestion) {
        this.fingestion = fingestion;
    }

   
    
}
