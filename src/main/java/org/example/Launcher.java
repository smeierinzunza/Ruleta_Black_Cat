package org.example;
import javax.swing.SwingUtilities;

public class Launcher {
     public static void main(String[] args){
         SwingUtilities.invokeLater(() -> {
             VentanaLogin login = new VentanaLogin();
             login.mostrarVentana();
         });
     }
}
