package org.example.vista;

import org.example.controlador.SessionController;
import javax.swing.*;
import java.awt.*;

public class VentanaLogin {
    private final SessionController session;
    private final JFrame frame = new JFrame("Login - Casino Black Cat");
    private final JTextField txtUsuario = new JTextField(15);
    private final JPasswordField txtClave = new JPasswordField(15);

    public VentanaLogin(SessionController session) {
        this.session = session;
        configurarUI();
    }

    private void configurarUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 200);
        frame.setLayout(new GridLayout(4, 2, 8, 8));

        frame.add(new JLabel("Usuario:"));
        frame.add(txtUsuario);
        frame.add(new JLabel("Clave:"));
        frame.add(txtClave);

        JButton btnIngresar = new JButton("Ingresar");
        JButton btnRegistrar = new JButton("Registrar");

        frame.add(btnIngresar);
        frame.add(btnRegistrar);

        btnIngresar.addActionListener(e -> login());
        btnRegistrar.addActionListener(e -> {
            frame.dispose();
            new VentanaRegistro(session).mostrarVentana();
        });
    }

    private void login() {
        String user = txtUsuario.getText().trim();
        String pass = new String(txtClave.getPassword()).trim();

        if (session.iniciarSesion(user, pass)) {
            JOptionPane.showMessageDialog(frame, "¡Bienvenido, " + session.getNombreUsuario() + "!");
            frame.dispose();
            new VentanaMenu(session).mostrarVentana();
        } else {
            JOptionPane.showMessageDialog(frame, "Credenciales incorrectas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}