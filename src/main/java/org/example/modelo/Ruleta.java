package org.example.modelo;
import java.util.Random;
import java.util.Scanner;


public class Ruleta {
    private int saldo;
    private final Random rng = new Random();
    private final int[] numerosRojos = {
            1, 3, 5, 7, 9, 12, 14, 16, 18,
            19, 21, 23, 25, 27, 30, 32, 34, 36
    };

    public Ruleta() {
        this(0);

    }

    public Ruleta(int saldoInicial){
        this.saldo = Math.max(saldoIncial, 0);
    }
    public int girarRuleta() {
        return rng.nextInt(37);
    }
    public boolean evaluarResultado(int numero, TipoApuesta tipo) {
        if (numero == 0) return false;
        return switch (tipo) {
            case ROJO -> esRojo(numero);
            case NEGRO -> !esRojo(numero);
            case PAR -> numero % 2 == 0;
            case IMPAR -> numero % 2 != 0;
        };
    }

    private boolean esRojo(int n) {
        for (int rojo : numerosRojos) {
            if (rojo == n) return true;
        }
        return false;
    }

    public int getSaldo() { return saldo; }

    public void depositar(int monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("Monto no válido.");
        }
        this.saldo += monto;
    }

    public void setSaldo(int saldo) {
        this.saldo = Math.max(saldo, 0);
    }
}




