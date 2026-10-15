package org.example.controlador;

import org.example.modelo.Resultado;
import org.example.modelo.Ruleta;
import org.example.modelo.TipoApuesta;
import org.example.modelo.Usuario;

public class RuletaController {
    private final Ruleta ruleta;
    private final SessionController session;

    public RuletaController(Ruleta ruleta, SessionController session) {
        this.ruleta = ruleta;
        this.session = session;
    }

    public RuletaController(SessionController session) {
        this(new Ruleta(session.getSaldoActual()), session);
    }

    public Resultado realizarApuesta(TipoApuesta tipo, int monto) {
        Usuario usuario = session.getUsuarioActual();
        if (usuario == null) throw new IllegalStateException("No existe una sesión activa.");

        if (monto <= 0 || usuario.getSaldo() < monto) {
            throw new IllegalArgumentException("Saldo insuficiente o monto inválido.");
        }

        usuario.descontarSaldo(monto);
        int numero = ruleta.girarRuleta();
        boolean gano = ruleta.evaluarResultado(numero, tipo);

        if (gano) {
            usuario.agregarGanancia(monto * 2);
        }

        ruleta.setSaldo(usuario.getSaldo());

        Resultado res = new Resultado(numero, tipo, monto, gano);
        // Registro en la asociación del usuario y de la ruleta
        usuario.agregarResultado(res);
        ruleta.registrarResultado(res);

        return res;
    }

    public int getSaldoActual() {
        return session.getSaldoActual();
    }
}