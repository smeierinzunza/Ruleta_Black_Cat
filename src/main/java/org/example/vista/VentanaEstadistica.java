package org.example.vista;
import org.example.controlador.EstadisticasController;
import org.example.controlador.SessionController;
import org.example.modelo.Estadistica;
import javax.swing.*;
import java.awt.*;

public class VentanaEstadistica {
    private final SessionController session;
    private final EstadisticasController estadisticasController;
    private JFrame frame  =  new JFrame("Ruleta - Estadistica");

    public VentanaEstadistica(SessionController session){
        this.session = session;
        this.estadisticasController = new EstadisticasController(session);
        configurarUI();
    }

    private void configurarUI(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 320);
        frame.setLayout(new BorderLayout());

        Estadistica stats = estadisticasController.obtenerEstadisticaUsuario();
        JPanel panelDatos = new JPanel(new GridLayout(5, 2, 10, 10));
        panelDatos.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        panelDatos.add(new JLabel("Total de Jugadas:"));
        panelDatos.add(new JLabel(String.valueOf(stats.getTotalJugadas())));

        panelDatos.add(new JLabel("Victorias Totales:"));
        panelDatos.add(new JLabel(String.valueOf(stats.getVictorias())));

        panelDatos.add(new JLabel("Porcentaje de Victorias:"));
        panelDatos.add(new JLabel(String.format("%.2f%%", stats.getPorcentajeVictorias())));

        panelDatos.add(new JLabel("Racha Máxima:"));
        panelDatos.add(new JLabel(stats.getRachaMaxima() + " victoria(s)"));

        panelDatos.add(new JLabel("Tipo Apuesta Más Jugada:"));
        panelDatos.add(new JLabel(stats.getTipoMasJugado() != null ? stats.getTipoMasJugado().toString() : "Sin jugadas"));

        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(e -> {
            frame.dispose();
            new VentanaMenu(session).mostrarVentana();
        });

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.add(btnVolver);

        JLabel lblTitulo = new JLabel("Estadísticas de: " + session.getNombreUsuario(), SwingConstants.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));

        frame.add(lblTitulo, BorderLayout.NORTH);
        frame.add(panelDatos, BorderLayout.CENTER);
        frame.add(panelBoton, BorderLayout.SOUTH);
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

