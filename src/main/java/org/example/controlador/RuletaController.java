package org.example.controlador;

import org.example.modelo.Resultado;
import org.example.modelo.Ruleta;
import org.example.modelo.TipoApuesta;
import org.example.modelo.Usuario;

public class RuletaController {
    private final Ruleta ruleta;
    private final SessionController session;
    private final ResultadoController resultadoController;

    public RuletaController(SessionController session, ResultadoController resultadoController) {
        this.session = session;
        this.resultadoController = resultadoController;
        this.ruleta = new Ruleta(session.getSaldoActual());
    }

    public Resultado realizarApuesta(TipoApuesta tipo, int monto) {
        Usuario usuario = session.getUsuarioActual();
        if (usuario == null) throw new IllegalStateException("No hay sesión activa.");

        if (monto <= 0 || usuario.getSaldo() < monto) {
            throw new IllegalArgumentException("Saldo insuficiente o monto inválido.");
        }

        usuario.descontarSaldo(monto);
        int numero = ruleta.girarRuleta();
        boolean gano = ruleta.evaluarResultado(numero, tipo);

        if (gano) {
            int ganancia = monto * 2;
            usuario.agregarGanancia(ganancia);
        }

        ruleta.setSaldo(usuario.getSaldo());
        Resultado res = new Resultado(numero, tipo, monto, gano);
        resultadoController.agregarResultado(res);

        return res;
    }

    public int getSaldoActual() {
        return session.getSaldoActual();
    }
}