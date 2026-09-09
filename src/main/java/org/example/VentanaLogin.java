package org.example;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class VentanaLogin {
    public static final List <Usuario> USUARIOS = new ArrayList<>();

    private final JFrame frame = new JFrame("Login - Casino Black Cat");
    private final JLabel lblUsuario = new JLabel("Usuario:");
    private final JTextField txtUsuario = new JTextField(15);
    private final JLabel lblClave = new JLabel("Clave:");
    private final JPasswordField txtClave = new JPasswordField(15);
    private final JButton btnIngresar = new JButton("Ingresar");
    private final JButton btnRegistrar = new JButton("Regitrarse");

    public VentanaLogin() {
        if (USUARIOS.isEmpty()) {
            USUARIOS.add(new Usuario("admin", "1234", "Don Donnie"));
            USUARIOS.add(new Usuario("jugador1", "paso123", "Gato Afortunado"));
        }
        configurarComponentes();
    }
    private void configurarComponentes() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350,200);
        frame.setLayout(new GridLayout(4, 2, 8, 8));
        frame.add(lblUsuario);
        frame.add(txtUsuario);
        frame.add(lblClave);
        frame.add(txtClave);
        frame.add(btnIngresar);
        frame.add(btnRegistrar);
        btnIngresar.addActionListener(e -> login());
        btnRegistrar.addActionListener(e -> abrirRegistro());

    }
    public void mostrarVentana(){
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void login(){
        String user = txtUsuario.getText().trim();
        String pass = new String(txtClave.getPassword()).trim();
        String nombreUsuario = validarCredenciales(user, pass);
        if (!nombreUsuario.isEmpty()){
            JOptionPane.showMessageDialog(frame, "Bienvenido" + nombreUsuario + "!", "Acceso Concedido", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
            new VentanaSaludo(nombreUsuario).mostrarVentana();
        } else {
            JOptionPane.showMessageDialog(frame, "Credenciales Erroneas. Intente nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private String validarCredenciales(String u, String p) {
        for (Usuario usuario : USUARIOS){

            if (usuario.valiarCredenciales(u, p)){
                return usuario.getNombre();
            }
        }
        return "";

    }
    private void abrirRegistro(){
        frame.dispose();

        new VentanaLogin().mostrarVentana();
    }
}
