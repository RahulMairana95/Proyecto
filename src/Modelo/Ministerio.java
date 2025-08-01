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
public class Ministerio {
    private int idmin;
    private int idmembrecia;
    private String nombre;
    private String apellidop;
    private String apellidom;
    private String numdocumento;
    private int telefono;
    private String ministerio;
    private String cargo;
    private Date iniciogestion;
    private Date fingestion;
    
    public Ministerio(){}

    public Ministerio(int idmin, int idmembrecia, String nombre, String apellidop, 
            String apellidom, String numdocumento, int telefono, String ministerio, 
            String cargo, Date iniciogestion, Date fingestion) {
        
        this.idmin = idmin;
        this.idmembrecia = idmembrecia;
        this.nombre = nombre;
        this.apellidop = apellidop;
        this.apellidom = apellidom;
        this.numdocumento = numdocumento;
        this.telefono = telefono;
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

    public String getApellidop() {
        return apellidop;
    }

    public void setApellidop(String apellidop) {
        this.apellidop = apellidop;
    }

    public String getApellidom() {
        return apellidom;
    }

    public void setApellidom(String apellidom) {
        this.apellidom = apellidom;
    }

    public String getNumdocumento() {
        return numdocumento;
    }

    public void setNumdocumento(String numdocumento) {
        this.numdocumento = numdocumento;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
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
