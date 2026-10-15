package org.example.vista;
import org.example.controlador.ResultadoController;
import org.example.controlador.SessionController;
import org.example.modelo.Resultado;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;



public class VentanaHistorial {
    private final SessionController session;
    private final ResultadoController resultadoController;
    private final JFrame frame = new JFrame("RULETA - Historial de Jugadas");

    public VentanaHistorial(SessionController session) {
        this.session = session;
        this.resultadoController = new ResultadoController(session);
        configurarUI();
    }
    private void configurarUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 350);
        frame.setLayout(new BorderLayout());

        // Encabezados de la tabla
        String[] columnas = {"N° Jugada", "Número Obtenido", "Tipo Apuesta", "Monto", "Resultado"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);

        List<Resultado> historial = resultadoController.obtenerHistorialUsuario();

        int jugadaNum = 1;
        for (Resultado res : historial) {
            Object[] fila = {
                    jugadaNum++,
                    res.getNumero(),
                    res.getTipo(),
                    "$" + res.getMonto(),
                    res.isAcierto() ? "GANÓ" : "PERDIÓ"
            };
            model.addRow(fila);
        }

        JTable tabla = new JTable(model);
        tabla.setEnabled(false); // Vista de solo lectura
        JScrollPane scrollPane = new JScrollPane(tabla);

        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(e -> {
            frame.dispose();
            new VentanaMenu(session).mostrarVentana();
        });

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(btnVolver);

        frame.add(new JLabel("Historial personal de: " + session.getNombreUsuario(), SwingConstants.CENTER), BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panelBoton, BorderLayout.SOUTH);
    }
    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}