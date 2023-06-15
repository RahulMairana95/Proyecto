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
public class Minis {
    private int idmin;
    private int idmembrecia;
    private String nombre;
    private String apellidos;
    private String ci;
    private String ministerio;
    private String cargo;
    private Date iniciogestion;
    private Date fingestion;
    
    public Minis(){}

    public Minis(int idmin, int idmembrecia, String nombre, String apellidos, String ci, String ministerio, String cargo, Date iniciogestion, Date fingestion) {
        this.idmin = idmin;
        this.idmembrecia = idmembrecia;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.ci = ci;
        this.ministerio = ministerio;
        this.cargo = cargo;
        this.iniciogestion = iniciogestion;
        this.fingestion = fingestion;
    }

    public int getIdmin() {
        return idmin;
    }

    public void setIdmin(int idmin) {
        this.idmin = idmin;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getMinisterio() {
        return ministerio;
    }

    public void setMinisterio(String ministerio) {
        this.ministerio = ministerio;
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
