package org.example.modelo;

public class Resultado {
    private final int numero;
    private final TipoApuesta tipo;
    private final int monto;
    private final boolean acierto;

    public Resultado(int numero, TipoApuesta tipo, int monto, boolean acierto) {
        this.numero = numero;
        this.tipo = tipo;
        this.monto = monto;
        this.acierto = acierto;
    }
    public int getNumero() {
        return numero; }
    public TipoApuesta getTipo(){
        return tipo;}
    public int getMonto(){
        return monto;}
    public boolean isAcierto(){
        return acierto;}

}
