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
public class Membrecia {
    private int idmembrecia;
    private String nombre;
    private String apellidop;
    private String apellidom;
    private String numdocumento;
    private Date fechanacimiento;
    private String estadocivil;
    private Date fechaconversion;
    private Date fechabautizo;
    private String talentos;
    private String dones;
    private String activo;
    private String direccion;
    private String nomreferencia;
    private int numreferencia;
    
    public Membrecia(){};
    
    @Override
    public String toString() {
        return nombre + " " + apellidop + " " + apellidom;
    }


    public Membrecia(int idmembrecia, String nombre, String apellidop, String apellidom, String numdocumento, Date fechanacimiento, String estadocivil, Date fechaconversion, Date fechabautizo, String talentos, String dones, String activo, String direccion, String nomreferencia, int numreferencia) {
        this.idmembrecia = idmembrecia;
        this.nombre = nombre;
        this.apellidop = apellidop;
        this.apellidom = apellidom;
        this.numdocumento = numdocumento;
        this.fechanacimiento = fechanacimiento;
        this.estadocivil = estadocivil;
        this.fechaconversion = fechaconversion;
        this.fechabautizo = fechabautizo;
        this.talentos = talentos;
        this.dones = dones;
        this.activo = activo;
        this.direccion = direccion;
        this.nomreferencia = nomreferencia;
        this.numreferencia = numreferencia;
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

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(String estadocivil) {
        this.estadocivil = estadocivil;
    }

    public Date getFechaconversion() {
        return fechaconversion;
    }

    public void setFechaconversion(Date fechaconversion) {
        this.fechaconversion = fechaconversion;
    }

    public Date getFechabautizo() {
        return fechabautizo;
    }

    public void setFechabautizo(Date fechabautizo) {
        this.fechabautizo = fechabautizo;
    }

    public String getTalentos() {
        return talentos;
    }

    public void setTalentos(String talentos) {
        this.talentos = talentos;
    }

    public String getDones() {
        return dones;
    }

    public void setDones(String dones) {
        this.dones = dones;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNomreferencia() {
        return nomreferencia;
    }

    public void setNomreferencia(String nomreferencia) {
        this.nomreferencia = nomreferencia;
    }

    public int getNumreferencia() {
        return numreferencia;
    }

    public void setNumreferencia(int numreferencia) {
        this.numreferencia = numreferencia;
    }

    
    
    
    
    
}
