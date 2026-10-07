package org.example.modelo;

public class Usuario {
    private String username;
    private String password;
    private String nombre;
    private int saldo;

    public Usuario(String username, String password, String nombre, int saldo) {
        this.username = username;
        this.password = password;
        setNombre(nombre);
        this.saldo = Math.max(saldo, 0);

    }
    public Usuario(String username, String password, String nombre) {
        this(username, password, nombre, 1000);
    }
    public Usuario(){
        this("invitado, "1234, "Invitado", 500 );

    }

    public boolean validarCredenciales(String u, String p) {
        return this.username.equals(u) && this.password.equals(p);
    }

    public String GetUsername(){
        return username;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nuevoNombre){
        if (nuevoNombre == null || nuevoNombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nuevoNombre.trim();
    }

    public int getSaldo(){
        return saldo;
    }

    public void depositar(int monto){
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto que ingreso no es Valido tiene que ser mayor a 0");
        }
        this.saldo += monto;

    }
    public boolean descontarSaldo(int monto){
        if (monto > 0 && this.saldo >= monto) {
            this.saldo -= monto;
            return true;
        }
        return false;

    }
    public void agregarGanancia(int monto){
        if (monto > 0){
            this.saldo += monto;

        }
    }

}