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
public class Diezmo {
    private int iddiezmo;
    private int idlider;
    private String carnet;
    private String mes;
    private Double entrada;
    private Double salida;
    private Double saldoanterior;
    private Double saldoactual;
    private String descripcion;
    private Date fecharegistro;
    private String tesorn;
    private String tesorap;
    
    public Diezmo(){}

    public Diezmo(int iddiezmo, int idlider, String carnet, String mes, Double entrada, Double salida, Double saldoanterior, Double saldoactual, String descripcion, Date fecharegistro, String tesorn, String tesorap) {
        this.iddiezmo = iddiezmo;
        this.idlider = idlider;
        this.carnet = carnet;
        this.mes = mes;
        this.entrada = entrada;
        this.salida = salida;
        this.saldoanterior = saldoanterior;
        this.saldoactual = saldoactual;
        this.descripcion = descripcion;
        this.fecharegistro = fecharegistro;
        this.tesorn = tesorn;
        this.tesorap = tesorap;
    }

    public int getIddiezmo() {
        return iddiezmo;
    }

    public void setIddiezmo(int iddiezmo) {
        this.iddiezmo = iddiezmo;
    }

    public int getIdlider() {
        return idlider;
    }

    public void setIdlider(int idlider) {
        this.idlider = idlider;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Double getEntrada() {
        return entrada;
    }

    public void setEntrada(Double entrada) {
        this.entrada = entrada;
    }

    public Double getSalida() {
        return salida;
    }

    public void setSalida(Double salida) {
        this.salida = salida;
    }

    public Double getSaldoanterior() {
        return saldoanterior;
    }

    public void setSaldoanterior(Double saldoanterior) {
        this.saldoanterior = saldoanterior;
    }

    public Double getSaldoactual() {
        return saldoactual;
    }

    public void setSaldoactual(Double saldoactual) {
        this.saldoactual = saldoactual;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public String getTesorn() {
        return tesorn;
    }

    public void setTesorn(String tesorn) {
        this.tesorn = tesorn;
    }

    public String getTesorap() {
        return tesorap;
    }

    public void setTesorap(String tesorap) {
        this.tesorap = tesorap;
    }

    
}
