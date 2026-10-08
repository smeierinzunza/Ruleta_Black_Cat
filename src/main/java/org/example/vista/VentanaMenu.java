package org.example.vista;

import org.example.modelo.Ruleta;

import javax.swing.*;
import java.awt.*;

public class VentanaMenu {
    private final JFrame frame = new JFrame("RULETA - Casino Black Cat");
    private final String nombreUsuario;
    private final Ruleta motorRuleta = new Ruleta();

    public VentanaMenu(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        configurarComponentes();
    }

    private void configurarComponentes() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new BorderLayout());

        // Panel lateral izquierdo para los botones
        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnJugar = new JButton("Jugar");
        JButton btnHistorial = new JButton("Historial");
        JButton btnSalir = new JButton("Salir");

        panelBotones.add(btnJugar);
        panelBotones.add(btnHistorial);
        panelBotones.add(btnSalir);

        // Panel central de texto
        JTextArea txtInfo = new JTextArea();
        txtInfo.setEditable(false);
        txtInfo.setText("Bienvenido/a al menú principal: " + nombreUsuario + "\n\n" +
                "A la izquierda tienes:\n" +
                "- Jugar: abre la ventana de juego.\n" +
                "- Historial: abre la ventana de estadísticas.\n" +
                "- Salir: cierra sesión y vuelve al login.");
        txtInfo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        frame.add(panelBotones, BorderLayout.WEST);
        frame.add(txtInfo, BorderLayout.CENTER);

        // Eventos
        btnJugar.addActionListener(e -> {
            frame.dispose();
            new VentanaRuleta(nombreUsuario, motorRuleta).mostrarVentana();
        });

        btnHistorial.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Historial se implementará aparte."));

        btnSalir.addActionListener(e -> {
            frame.dispose();
            new VentanaLogin().mostrarVentana();
        });
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
