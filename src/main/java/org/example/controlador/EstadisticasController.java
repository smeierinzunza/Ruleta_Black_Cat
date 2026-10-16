package org.example.controlador;

import org.example.modelo.Estadistica;
import org.example.modelo.Resultado;
import org.example.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;


public class EstadisticasController {
    private final SessionController session;

    public EstadisticasController(SessionController session){
        this.session = session;
    }

    public Estadistica obtenerEstadisticaUsuario(){
        Usuario usuario = session.getUsuarioActual();
        List<Resultado> historial= (usuario != null) ? usuario.getHistorial() : new ArrayList<>();
        return new Estadistica(historial);
    }
}
