package org.example.launcher;
import org.example.controlador.SessionController;
import org.example.vista.VentanaLogin;
import javax.swing.SwingUtilities;

public class Launcher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SessionController session = new SessionController();
            new VentanaLogin(session).mostrarVentana();
        });
    }
}