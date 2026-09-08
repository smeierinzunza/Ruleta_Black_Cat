package org.example;
import javax.swing.*;

public class VentanaSaludo {
    private final String nombreUsuario;
    public VentanaSaludo(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public void mostrarVentana(){
        JOptionPane.showMessageDialog(null,
                "Sesión iniciada como: " + nombreUsuario + "\nPresione Aceptar para continuar a la ruleta.",
                "Casino Black Cat",
                JOptionPane.INFORMATION_MESSAGE);
        Ruleta.menu();
    }
}
