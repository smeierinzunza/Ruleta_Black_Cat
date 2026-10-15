package org.example.vista;

import org.example.controlador.SessionController;
import javax.swing.*;
import java.awt.*;

public class VentanaMenu {
    private final SessionController session;
    private final JFrame frame = new JFrame("RULETA - Casino Black Cat");
    private final JLabel lblSaldo = new JLabel();

    public VentanaMenu(SessionController session) {
        this.session = session;
        configurarUI();
    }

    private void configurarUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(520, 320);
        frame.setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel(new GridLayout(4, 1, 8, 8));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JButton btnJugar = new JButton("Jugar");
        JButton btnPerfil = new JButton("Perfil");
        JButton btnHistorial = new JButton("Historial");
        JButton btnSalir = new JButton("Salir");

        panelBotones.add(btnJugar);
        panelBotones.add(btnPerfil);
        panelBotones.add(btnHistorial);
        panelBotones.add(btnSalir);

        JTextArea txtInfo = new JTextArea();
        txtInfo.setEditable(false);
        txtInfo.setText("Bienvenido/a " + session.getNombreUsuario() + "\n\n" +
                "A la izquierda tienes:\n" +
                "- Jugar: abre la ventana de juego.\n" +
                "- Historial: abre la ventana con tu historial de jugadas.\n" +
                "- Perfil: modifica tus datos y recarga saldo.\n" +
                "- Salir: cierra sesión y vuelve al login.");

        actualizarSaldoLabel();

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelInferior.add(lblSaldo);

        frame.add(panelBotones, BorderLayout.WEST);
        frame.add(txtInfo, BorderLayout.CENTER);
        frame.add(panelInferior, BorderLayout.SOUTH);

        btnJugar.addActionListener(e -> {
            frame.dispose();
            new VentanaRuleta(session).mostrarVentana();
        });

        btnHistorial.addActionListener(e -> {
            frame.dispose();
            new VentanaHistorial(session).mostrarVentana();
        });

        btnPerfil.addActionListener(e -> mostrarPerfil());

        btnSalir.addActionListener(e -> {
            session.cerrarSesion();
            frame.dispose();
            new VentanaLogin(session).mostrarVentana();
        });
    }

    private void actualizarSaldoLabel() {
        lblSaldo.setText("Saldo Actual: $" + session.getSaldoActual());
    }

    private void mostrarPerfil() {
        String nuevoNombre = JOptionPane.showInputDialog(frame, "Nombre actual: " + session.getNombreUsuario() + "\nNuevo nombre:", session.getNombreUsuario());
        if (nuevoNombre != null && !nuevoNombre.isBlank()) {
            try {
                session.actualizarNombre(nuevoNombre);
                JOptionPane.showMessageDialog(frame, "Nombre actualizado correctamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        String montoStr = JOptionPane.showInputDialog(frame, "Saldo actual: $" + session.getSaldoActual() + "\nMonto a depositar:");
        if (montoStr != null && !montoStr.isBlank()) {
            try {
                int monto = Integer.parseInt(montoStr.trim());
                session.depositarSaldo(monto);
                actualizarSaldoLabel();
                JOptionPane.showMessageDialog(frame, "Depósito realizado.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Monto inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}