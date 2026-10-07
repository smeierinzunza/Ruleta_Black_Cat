package org.example.vista;
import org.example.modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistro {
    private final JFrame frame = new JFrame("Casino Black Cat - Registro");
    private final JTextField txtUsuario = new JTextField(15);
    private final JPasswordField txtClave = new JPasswordField(15);
    private final JTextField txtNombre = new JTextField(15);
    private final JButton btnGuardar = new JButton("Registrar");
    private final JButton btnVolver = new JButton("Volver");

    public VentanaRegistro() {
        configurarComponentes();
    }

    private void configurarComponentes() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 220);
        frame.setLayout(new GridLayout(4, 2, 8, 8));

        frame.add(new JLabel("Usuario:"));
        frame.add(txtUsuario);
        frame.add(new JLabel("Contraseña:"));
        frame.add(txtClave);
        frame.add(new JLabel("Nombre Completo:"));
        frame.add(txtNombre);
        frame.add(btnGuardar);
        frame.add(btnVolver);

        btnGuardar.addActionListener(e -> registrarUsuario());
        btnVolver.addActionListener(e -> volverLogin());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void registrarUsuario() {
        String user = txtUsuario.getText().trim();
        String pass = new String(txtClave.getPassword()).trim();
        String nombre = txtNombre.getText().trim();
        if (user.isEmpty() || pass.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        VentanaLogin.USUARIOS.add(new Usuario(user, pass, nombre));
        JOptionPane.showMessageDialog(frame, "¡Usuario registrado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        volverLogin();
    }
    private void volverLogin() {
        frame.dispose();
        new VentanaLogin().mostrarVentana();
    }
}

