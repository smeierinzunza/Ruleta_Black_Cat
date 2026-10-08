package org.example.controlador;

import org.example.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

public class SessionController {
    private final List<Usuario> usuarios = new ArrayList<>();
    private Usuario usuarioActual;

    public SessionController() {
        // Usuarios semilla
        usuarios.add(new Usuario("admin", "1234", "Don Donnie", 5000));
        usuarios.add(new Usuario("jugador1", "paso123", "Gato Afortunado", 1000));
    }

    public void registrarUsuario(String usuario, String clave, String nombre) {
        if (usuario == null || usuario.isBlank() || clave == null || clave.isBlank() || nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Todos los datos son requeridos.");
        }
        for (Usuario u : usuarios) {
            if (u.getUsername().equalsIgnoreCase(usuario.trim())) {
                throw new IllegalArgumentException("El nombre de usuario ya existe.");
            }
        }
        usuarios.add(new Usuario(usuario.trim(), clave.trim(), nombre.trim()));
    }

    public boolean iniciarSesion(String usuario, String clave) {
        for (Usuario u : usuarios) {
            if (u.validarCredenciales(usuario, clave)) {
                this.usuarioActual = u;
                return true;
            }
        }
        return false;
    }

    public boolean hayUsuario() {
        return usuarioActual != null;
    }

    public String getNombreUsuario() {
        return hayUsuario() ? usuarioActual.getNombre() : "";
    }

    public int getSaldoActual() {
        return hayUsuario() ? usuarioActual.getSaldo() : 0;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void actualizarNombre(String nuevoNombre) {
        if (hayUsuario()) {
            usuarioActual.setNombre(nuevoNombre);
        }
    }

    public void depositarSaldo(int monto) {
        if (hayUsuario()) {
            usuarioActual.depositar(monto);
        }
    }

    public void cerrarSesion() {
        usuarioActual = null;
    }
}