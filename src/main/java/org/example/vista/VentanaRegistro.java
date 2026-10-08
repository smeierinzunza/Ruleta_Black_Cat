package org.example.vista;

import org.example.controlador.SessionController;
import javax.swing.*;
import java.awt.*;

public class VentanaRegistro {
    private final SessionController session;
    private final JFrame frame = new JFrame("Registro - Casino Black Cat");
    private final JTextField txtUsuario = new JTextField();
    private final JPasswordField txtClave = new JPasswordField();
    private final JTextField txtNombre = new JTextField();

    public VentanaRegistro(SessionController session) {
        this.session = session;
        configurarUI();
    }

    private void configurarUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 220);
        frame.setLayout(new GridLayout(4, 2, 8, 8));

        frame.add(new JLabel("Usuario:"));
        frame.add(txtUsuario);
        frame.add(new JLabel("Contraseña:"));
        frame.add(txtClave);
        frame.add(new JLabel("Nombre Completo:"));
        frame.add(txtNombre);

        JButton btnGuardar = new JButton("Registrar");
        JButton btnVolver = new JButton("Volver");
        frame.add(btnGuardar);
        frame.add(btnVolver);

        btnGuardar.addActionListener(e -> registrar());
        btnVolver.addActionListener(e -> {
            frame.dispose();
            new VentanaLogin(session).mostrarVentana();
        });
    }

    private void registrar() {
        try {
            session.registrarUsuario(txtUsuario.getText(), new String(txtClave.getPassword()), txtNombre.getText());
            JOptionPane.showMessageDialog(frame, "¡Registro exitoso! Por favor inicie sesión.");
            frame.dispose();
            new VentanaLogin(session).mostrarVentana();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error de Registro", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}