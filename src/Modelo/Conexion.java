/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author RAHUL OMERO MAIRANAs
 */
public class Conexion {
    //public static void main(String[] args) {
    Connection conectar;
    public Connection conectando(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            conectar=DriverManager.getConnection("jdbc:mysql://localhost:3306/nuevajerusalen?useTimezone=true&serverTimezone=America/La_Paz", "root", "");
            
            System.out.println("Esta Conectado eeeee");
        } catch (Exception e) {
            System.out.println("no hay conexion a con BBDD");
        }
        return conectar;
    }
}
