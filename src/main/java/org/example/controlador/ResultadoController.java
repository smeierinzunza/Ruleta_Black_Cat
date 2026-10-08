package org.example.controlador;
import org.example.modelo.Resultado;
import org.w3c.dom.html.HTMLIsIndexElement;

import java.util.List;
import java.util.ArrayList;

public class ResultadoController {
    private final List<Resultado> historial = new ArrayList<>();
    public void agregarResultado(Resultado res) {
        historial.add(res);
    }

    public List<Resultado> getHistorial() {
        return new ArrayList<>(historial);

    }

}
