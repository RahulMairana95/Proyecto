package Controlador;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InactividadSistema {

    private final Timer timer;

    public interface ListenerCierre {
        void cerrar();
    }

    public InactividadSistema(int tiempoInactividadMs, ListenerCierre listener) {

        // Timer que ejecuta el cierre si no hay actividad
        timer = new Timer(tiempoInactividadMs, e -> listener.cerrar());
        timer.setRepeats(false);
        timer.start();

        // Listener GLOBAL para detectar movimiento del mouse o teclas
        AWTEventListener actividadGlobal = new AWTEventListener() {
            @Override
            public void eventDispatched(AWTEvent event) {

                // Cualquier tecla presionada o movimiento del mouse reinicia el tiempo
                if (event instanceof KeyEvent || event instanceof MouseEvent) {
                    reiniciar();
                }
            }
        };

        // Registra el listener global
        Toolkit.getDefaultToolkit().addAWTEventListener(
            actividadGlobal,
            AWTEvent.KEY_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK | AWTEvent.MOUSE_EVENT_MASK
        );
    }

    public void reiniciar() {
        timer.restart();
    }

    public void detener() {
        timer.stop();
    }
}
