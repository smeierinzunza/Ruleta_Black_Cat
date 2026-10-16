package org.example.modelo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Estadistica {
    private int totalJugadas;
    private int victorias;
    private double porcentajeVictorias;
    private int rachaMaxima;
    private TipoApuesta tipoMasJugado;

    public Estadistica (List<Resultado> historial) {
        calcularEstadistica(historial);

    }

    public void calcularEstadistica(List<Resultado> historial){
        if (historial == null || historial.isEmpty()) {
            this.totalJugadas = 0;
            this.rachaMaxima = 0;
            this.porcentajeVictorias = 0.0;
            this.victorias = 0;
            this.tipoMasJugado = null;
            return;
        }

        this.totalJugadas = historial.size();
        this.victorias = 0;
        int rachaActual = 0;
        this.rachaMaxima = 0;

        Map<TipoApuesta, Integer> conteoTipo = new HashMap<>();

        for (Resultado r : historial){
            TipoApuesta tipo = r.getTipo();
            conteoTipo.put(tipo, conteoTipo.getOrDefault(tipo, 0)+1);

            if (r.isAcierto()) {
                this.victorias++;
                rachaActual++;
                if (rachaActual > this.rachaMaxima) {
                    this.rachaMaxima = rachaActual;
                }
            } else {
                rachaActual = 0;
            }
        }
        this.porcentajeVictorias = ((double) this.victorias / this.totalJugadas) * 100.0;

        TipoApuesta masJugado = null;
        int maxConteo = -1;
        for (Map.Entry<TipoApuesta, Integer> entry : conteoTipo.entrySet()) {
            if (entry.getValue() > maxConteo) {
                maxConteo = entry.getValue();
                masJugado = entry.getKey();
            }
        }
        this.tipoMasJugado = masJugado;
    }

    public int getTotalJugadas() { return totalJugadas; }
    public int getVictorias() { return victorias; }
    public double getPorcentajeVictorias() { return porcentajeVictorias; }
    public int getRachaMaxima() { return rachaMaxima; }
    public TipoApuesta getTipoMasJugado() { return tipoMasJugado; }
}
