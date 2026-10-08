package org.example.vista;

import org.example.modelo.Ruleta;

import javax.swing.*;
import java.awt.*;

public class VentanaRuleta {
    private final JFrame frame = new JFrame("RULETA - Juego");
    private final Ruleta motorRuleta;
    private final String nombreUsuario;

    private final JComboBox<String> cmbTipoApuesta = new JComboBox<>(new String[]{"Color", "Paridad"});
    private final JComboBox<String> cmbOpciones = new JComboBox<>(new String[]{"Rojo (R)", "Negro (N)"});
    private final JTextField txtMonto = new JTextField(10);
    private final JLabel lblResultado = new JLabel("Esperando apuesta...");

    public VentanaRuleta(String nombreUsuario, Ruleta motorRuleta) {
        this.nombreUsuario = nombreUsuario;
        this.motorRuleta = motorRuleta; // Recibe el motor lógico
        configurarComponentes();
    }

    private void configurarComponentes() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        panelFormulario.add(new JLabel("Tipo de apuesta:"));
        panelFormulario.add(cmbTipoApuesta);
        panelFormulario.add(new JLabel("Seleccione opción:"));
        panelFormulario.add(cmbOpciones);
        panelFormulario.add(new JLabel("Monto:"));

        JPanel panelMonto = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelMonto.add(txtMonto);
        panelFormulario.add(panelMonto);

        JButton btnGirar = new JButton("Girar");
        JButton btnVolver = new JButton("Volver al Menú");
        panelFormulario.add(btnGirar);
        panelFormulario.add(btnVolver);

        lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
        lblResultado.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        frame.add(panelFormulario, BorderLayout.CENTER);
        frame.add(lblResultado, BorderLayout.SOUTH);

        // Dinamismo del ComboBox
        cmbTipoApuesta.addActionListener(e -> actualizarOpciones());

        // Eventos de botones
        btnVolver.addActionListener(e -> {
            frame.dispose();
            new VentanaMenu(nombreUsuario).mostrarVentana();
        });

        btnGirar.addActionListener(e -> procesarApuesta());
    }

    private void actualizarOpciones() {
        cmbOpciones.removeAllItems();
        if (cmbTipoApuesta.getSelectedIndex() == 0) {
            cmbOpciones.addItem("Rojo (R)");
            cmbOpciones.addItem("Negro (N)");
        } else {
            cmbOpciones.addItem("Par (P)");
            cmbOpciones.addItem("Impar (I)");
        }
    }

    private void procesarApuesta() {
        try {
            int monto = Integer.parseInt(txtMonto.getText().trim());
            if (monto <= 0) throw new NumberFormatException();
            String seleccion = (String) cmbOpciones.getSelectedItem();
            char tipoApuesta = seleccion.charAt(seleccion.length() - 2);
            int numeroObtenido = motorRuleta.girarRuleta();
            boolean acierto = motorRuleta.evaluarResultado(numeroObtenido, tipoApuesta);
            motorRuleta.registrarResultado(numeroObtenido, monto, acierto);
            String estado = acierto ? "GANASTE" : "PERDISTE";
            lblResultado.setText(String.format("Número: %d | Apuesta: %s | %s $%d",
                    numeroObtenido, tipoApuesta, estado, monto));
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Ingrese un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}