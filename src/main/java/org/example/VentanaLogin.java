package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaLogin {
    public static final List<Usuario> USUARIOS = new ArrayList<>();
    private final JFrame frame = new JFrame("Casino Black Cat - Login");
    private final JTextField txtUsuario = new JTextField(20);
    private final JPasswordField txtClave = new JPasswordField(20);

    public VentanaLogin() {
        if (USUARIOS.isEmpty()) {
            USUARIOS.add(new Usuario("admin", "1234", "Don Donnie"));
            USUARIOS.add(new Usuario("jugador1", "paso123", "Gato Afortunado"));
        }
        configurarComponentes();
    }

    private void configurarComponentes() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 200);
        frame.setLayout(new GridLayout(4, 2, 8, 8));

        frame.add(new JLabel("Usuario:"));
        frame.add(txtUsuario);
        frame.add(new JLabel("Clave:"));
        frame.add(txtClave);

        JButton btnIngresar = new JButton("Ingresar");
        JButton btnRegistrar = new JButton("Registrarse");

        frame.add(btnIngresar);
        frame.add(btnRegistrar);

        btnIngresar.addActionListener(e -> login());
        btnRegistrar.addActionListener(e -> {
            frame.dispose();
            new VentanaRegistro().mostrarVentana();
        });
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void login() {
        String user = txtUsuario.getText().trim();
        String pass = new String(txtClave.getPassword()).trim();
        String nombreUsuario = validarCredenciales(user, pass);

        if (!nombreUsuario.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "¡Bienvenido, " + nombreUsuario + "!");
            frame.dispose();
            new VentanaMenu(nombreUsuario).mostrarVentana(); // Redirige al nuevo menú
        } else {
            JOptionPane.showMessageDialog(frame, "Credenciales incorrectas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String validarCredenciales(String u, String p) {
        for (Usuario usr : USUARIOS) {
            if (usr.validarCredenciales(u, p)) return usr.getNombre();
        }
        return "";
    }
}