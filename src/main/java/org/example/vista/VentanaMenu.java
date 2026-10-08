package org.example.vista;

import org.example.controlador.ResultadoController;
import org.example.controlador.SessionController;
import javax.swing.*;
import java.awt.*;

public class VentanaMenu {
    private final SessionController session;
    private final ResultadoController resultadoController = new ResultadoController();
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
                "Opciones:\n" +
                "- Jugar: abre la mesa de apuestas.\n" +
                "- Perfil: consulta/actualiza tus datos y recarga saldo.\n" +
                "- Historial: consulta rondas anteriores.\n" +
                "- Salir: cierra sesión.");

        actualizarSaldoLabel();

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelInferior.add(lblSaldo);

        frame.add(panelBotones, BorderLayout.WEST);
        frame.add(txtInfo, BorderLayout.CENTER);
        frame.add(panelInferior, BorderLayout.SOUTH);

        btnJugar.addActionListener(e -> {
            frame.dispose();
            new VentanaRuleta(session, resultadoController).mostrarVentana();
        });

        btnPerfil.addActionListener(e -> mostrarPerfil());

        btnHistorial.addActionListener(e -> {
            int totalRondas = resultadoController.getHistorial().size();
            JOptionPane.showMessageDialog(frame, "Rondas jugadas en esta sesión: " + totalRondas);
        });

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
                JOptionPane.showMessageDialog(frame, "Depósito exitoso.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Monto numérico no válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}