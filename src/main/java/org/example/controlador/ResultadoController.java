package org.example.controlador;

import org.example.modelo.Resultado;
import org.example.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

public class ResultadoController {
    private final SessionController session;

    public ResultadoController(SessionController session) {
        this.session = session;
    }

    public List<Resultado> obtenerHistorialUsuario() {
        Usuario usuario = session.getUsuarioActual();
        if (usuario != null) {
            return usuario.getHistorial();
        }
        return new ArrayList<>();
    }
}