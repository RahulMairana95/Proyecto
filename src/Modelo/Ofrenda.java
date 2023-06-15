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
public class Ofrenda {
    private int idofrenda;
    private int idlider;
    private String tesorero;
    private String carnet;
    private String mes;
    private Double entrada;
    private Double salida;
    private Double saldoanterior;
    private Double saldoactual;
    private String descripcion;
    private Date fecharegistro;
    
    public Ofrenda(){}

    public Ofrenda(int idofrenda, int idlider, String tesorero, String carnet, String mes, Double entrada, Double salida, Double saldoanterior, Double saldoactual, String descripcion, Date fecharegistro) {
        this.idofrenda = idofrenda;
        this.idlider = idlider;
        this.tesorero = tesorero;
        this.carnet = carnet;
        this.mes = mes;
        this.entrada = entrada;
        this.salida = salida;
        this.saldoanterior = saldoanterior;
        this.saldoactual = saldoactual;
        this.descripcion = descripcion;
        this.fecharegistro = fecharegistro;
    }

    public int getIdofrenda() {
        return idofrenda;
    }

    public void setIdofrenda(int idofrenda) {
        this.idofrenda = idofrenda;
    }

    public int getIdlider() {
        return idlider;
    }

    public void setIdlider(int idlider) {
        this.idlider = idlider;
    }

    public String getTesorero() {
        return tesorero;
    }

    public void setTesorero(String tesorero) {
        this.tesorero = tesorero;
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

}
