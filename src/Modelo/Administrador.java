/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author RAHUL
 */
public class Administrador {
    private int idadmin;
    private int idlider;
    private String nombrelider;
    private String usuario;
    private String nombreusuario;
    private String contraseña;
    
    
    public Administrador(){
    
    }

    public Administrador(int idadmin, int idlider, String nombrelider, String usuario, String nombreusuario, String contraseña) {
        this.idadmin = idadmin;
        this.idlider = idlider;
        this.nombrelider = nombrelider;
        this.usuario = usuario;
        this.nombreusuario = nombreusuario;
        this.contraseña = contraseña;
    }

    public int getIdadmin() {
        return idadmin;
    }

    public void setIdadmin(int idadmin) {
        this.idadmin = idadmin;
    }

    public int getIdlider() {
        return idlider;
    }

    public void setIdlider(int idlider) {
        this.idlider = idlider;
    }

    public String getNombrelider() {
        return nombrelider;
    }

    public void setNombrelider(String nombrelider) {
        this.nombrelider = nombrelider;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    
}
