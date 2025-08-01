/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class RespaldoBD implements ActionListener{
    VistaRespaldo respaldo = new VistaRespaldo();
    // Cambia estos valores si es necesario
    private final String usuario = "root";
    private final String contraseña = ""; // dejar vacío si no tiene
    private final String nombreBD = "membrecia"; // nombre de la base de datos
    private final String rutaMySQL = "C:\\xampp\\mysql\\bin\\";
    
    public RespaldoBD(VistaRespaldo vr){
        this.respaldo = vr;
        
        ///EVENTO DE BOTONES
        this.respaldo.botondescargar.addActionListener(this);
        this.respaldo.botoncargar.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == respaldo.botondescargar){
            try {
                respaldarBaseDeDatos();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al Descargar");
            }
            
        }else if(respaldo.botoncargar == ae.getSource()){
            try {
                cargarBaseDeDatos();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al cargar");
            }
            
        }
    }

    
    public void verificarOCrearBaseDeDatos() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", usuario, contraseña);
             Statement stmt = con.createStatement()) {

            String sql = "CREATE DATABASE IF NOT EXISTS " + nombreBD;
            stmt.executeUpdate(sql);
            System.out.println("Base de datos verificada o creada.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al verificar/crear la base de datos:\n" + e.getMessage());
        }
    }
    
    public void respaldarBaseDeDatos() {
        String nombreArchivo = "respaldo_" + nombreBD + "_" + System.currentTimeMillis() + ".sql";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File(nombreArchivo));

        int seleccion = fileChooser.showSaveDialog(null);
        if (seleccion != JFileChooser.APPROVE_OPTION) return;

        String rutaRespaldo = fileChooser.getSelectedFile().getAbsolutePath();

        // Comando para mysqldump
        String comando = rutaMySQL + "mysqldump.exe";
        ProcessBuilder pb = new ProcessBuilder(
            comando,
            "-u", usuario,
            "--password=" + contraseña,
            "--add-drop-database",
            "-B", nombreBD
        );

        pb.redirectOutput(new File(rutaRespaldo));
        pb.redirectErrorStream(true); // redirige errores también a la salida

        // Inicia la barra de progreso en modo indeterminado
        respaldo.bardescargar.setIndeterminate(true);
        respaldo.bardescargar.setString("Respaldando base de datos...");
        respaldo.bardescargar.setStringPainted(true); // muestra texto

        // Hilo para evitar que se congele la interfaz
        new Thread(() -> {
            try {
                Process proceso = pb.start();
                boolean completado = proceso.waitFor(60, TimeUnit.SECONDS);

                // Detener barra de progreso
                respaldo.bardescargar.setIndeterminate(false);

                if (completado && proceso.exitValue() == 0) {
                    respaldo.bardescargar.setValue(100);
                    respaldo.bardescargar.setString("Respaldo completado");
                    JOptionPane.showMessageDialog(null, "Respaldo creado:\n" + rutaRespaldo);
                } else {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                    StringBuilder salida = new StringBuilder();
                    String linea;
                    while ((linea = reader.readLine()) != null) {
                        salida.append(linea).append("\n");
                    }

                    respaldo.bardescargar.setValue(0);
                    respaldo.bardescargar.setString("Error al respaldar");
                    JOptionPane.showMessageDialog(null, "Error al crear respaldo:\n" + salida.toString());
                }

            } catch (IOException | InterruptedException e) {
                respaldo.bardescargar.setIndeterminate(false);
                respaldo.bardescargar.setValue(0);
                respaldo.bardescargar.setString("Error");
                JOptionPane.showMessageDialog(null, "Error al ejecutar mysqldump:\n" + e.getMessage());
            }
        }).start();
    }
    public void cargarBaseDeDatos() {
        verificarOCrearBaseDeDatos();

        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showOpenDialog(null);
        if (seleccion != JFileChooser.APPROVE_OPTION) return;

        File archivoSQL = fileChooser.getSelectedFile();
        String comando = rutaMySQL + "mysql.exe";

        ProcessBuilder pb = new ProcessBuilder(
            comando,
            "-u", usuario,
            "--password=" + contraseña,
            nombreBD
        );
        pb.redirectErrorStream(true); // combina salida estándar y de error

        // Configurar barra de progreso
        respaldo.barcargar.setIndeterminate(true);
        respaldo.barcargar.setString("Restaurando base de datos...");
        respaldo.barcargar.setStringPainted(true);

        // Hilo para ejecutar restauración sin bloquear la interfaz
        new Thread(() -> {
            try {
                Process proceso = pb.start();

                // Escribir archivo .sql a la entrada del proceso
                OutputStream os = proceso.getOutputStream();
                Files.copy(archivoSQL.toPath(), os);
                os.flush();
                os.close();

                boolean completado = proceso.waitFor(60, TimeUnit.SECONDS);

                respaldo.barcargar.setIndeterminate(false);

                if (completado && proceso.exitValue() == 0) {
                    respaldo.barcargar.setValue(100);
                    respaldo.barcargar.setString("Restauración completada");
                    JOptionPane.showMessageDialog(null, "Base de datos restaurada correctamente.");
                } else {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                    StringBuilder salida = new StringBuilder();
                    String linea;
                    while ((linea = reader.readLine()) != null) {
                        salida.append(linea).append("\n");
                    }

                    respaldo.barcargar.setValue(0);
                    respaldo.barcargar.setString("Error al restaurar");
                    JOptionPane.showMessageDialog(null, "Error al restaurar base de datos:\n" + salida.toString());
                }

            } catch (IOException | InterruptedException e) {
                respaldo.barcargar.setIndeterminate(false);
                respaldo.barcargar.setValue(0);
                respaldo.barcargar.setString("Error");
                JOptionPane.showMessageDialog(null, "Error al cargar la base:\n" + e.getMessage());
            }
        }).start();
    }

}

