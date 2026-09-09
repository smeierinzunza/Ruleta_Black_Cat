package org.example;

public class Usuario {
    private String username;
    private String password;
    private String nombre;

    public Usuario(String username, String password, String nombre) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
    }

    public boolean valiarCredenciales(String u, String p){

        return this.username.equals(u) && this.password.equals(p);
    }
    public String getNombre() {

        return nombre;
    }
}
