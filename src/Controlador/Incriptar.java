/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 *
 * @author LENOVO
 */
public class Incriptar {
    // Método para encriptar una cadena usando SHA-256
    public static String hashSHA256(String texto) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(texto.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (Exception ex) {
            throw new RuntimeException("Error al encriptar con SHA-256", ex);
        }
    }

    // Método para verificar si una cadena ya está en formato SHA-256
    public static boolean esSHA256(String texto) {
        return texto.matches("^[a-fA-F0-9]{64}$");
    }
}
