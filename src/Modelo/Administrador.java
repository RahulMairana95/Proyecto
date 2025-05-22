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
    private String nombre;
    private String apellidos;
    private String numdocumento;
    private int telefono;
    private String email;
    private String usuario;
    private String nombreusuario;
    private String contraseña;
    
    
    public Administrador(){
    
    }

    public Administrador(int idadmin, int idlider, String nombre, String apellidos, String numdocumento, int telefono, String email, String usuario, String nombreusuario, String contraseña) {
        this.idadmin = idadmin;
        this.idlider = idlider;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.numdocumento = numdocumento;
        this.telefono = telefono;
        this.email = email;
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
