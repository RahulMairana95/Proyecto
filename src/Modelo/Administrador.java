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
    private String nombre;
    private String apaterno;
    private String apmaterno;
    private String numdocumento;
    private int telefono;
    private String email;
    private String cargo;
    private String usuario;
    private String nombreusuario;
    private String contraseña;
    
    
    public Administrador(){
    
    }

    public Administrador(int idadmin, String nombre, String apaterno, String apmaterno, String numdocumento, int telefono, String email, String cargo, String usuario, String nombreusuario, String contraseña) {
        this.idadmin = idadmin;
        this.nombre = nombre;
        this.apaterno = apaterno;
        this.apmaterno = apmaterno;
        this.numdocumento = numdocumento;
        this.telefono = telefono;
        this.email = email;
        this.cargo = cargo;
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
        return apmaterno;
    }

    public void setAmaterno(String amaterno) {
        this.apmaterno = amaterno;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
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
