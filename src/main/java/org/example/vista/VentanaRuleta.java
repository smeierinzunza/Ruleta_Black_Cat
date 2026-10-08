package org.example.vista;

import org.example.controlador.ResultadoController;
import org.example.controlador.RuletaController;
import org.example.controlador.SessionController;
import org.example.modelo.Resultado;
import org.example.modelo.TipoApuesta;

import javax.swing.*;
import java.awt.*;

public class VentanaRuleta {
    private final SessionController session;
    private final RuletaController ruletaController;
    private final JFrame frame = new JFrame("RULETA - Juego");

    private final JComboBox<TipoApuesta> cmbTipoApuesta = new JComboBox<>(TipoApuesta.values());
    private final JTextField txtMonto = new JTextField(10);
    private final JLabel lblResultado = new JLabel("Esperando apuesta...");
    private final JLabel lblSaldo = new JLabel();

    public VentanaRuleta(SessionController session, ResultadoController resultadoController) {
        this.session = session;
        this.ruletaController = new RuletaController(session, resultadoController);
        configurarUI();
    }

    private void configurarUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 320);
        frame.setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        panelFormulario.add(new JLabel("Tipo de Apuesta:"));
        panelFormulario.add(cmbTipoApuesta);

        panelFormulario.add(new JLabel("Monto a Apostar:"));
        panelFormulario.add(txtMonto);

        JButton btnGirar = new JButton("Girar");
        JButton btnVolver = new JButton("Volver al Menú");

        panelFormulario.add(btnGirar);
        panelFormulario.add(btnVolver);

        actualizarSaldo();

        JPanel panelInferior = new JPanel(new GridLayout(2, 1));
        lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
        lblSaldo.setHorizontalAlignment(SwingConstants.CENTER);

        panelInferior.add(lblResultado);
        panelInferior.add(lblSaldo);

        frame.add(panelFormulario, BorderLayout.CENTER);
        frame.add(panelInferior, BorderLayout.SOUTH);

        btnGirar.addActionListener(e -> procesarApuesta());
        btnVolver.addActionListener(e -> {
            frame.dispose();
            new VentanaMenu(session).mostrarVentana();
        });
    }

    private void procesarApuesta() {
        try {
            int monto = Integer.parseInt(txtMonto.getText().trim());
            TipoApuesta tipo = (TipoApuesta) cmbTipoApuesta.getSelectedItem();

            Resultado res = ruletaController.realizarApuesta(tipo, monto);

            String estado = res.isAcierto() ? "GANASTE" : "PERDISTE";
            lblResultado.setText(String.format("Número: %d | Apuesta: %s | %s $%d",
                    res.getNumero(), res.getTipo(), estado, res.getMonto()));

            actualizarSaldo();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Ingrese un monto numérico válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException | IllegalStateException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void actualizarSaldo() {
        lblSaldo.setText("Saldo Disponible: $" + ruletaController.getSaldoActual());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}