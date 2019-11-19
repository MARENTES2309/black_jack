package com.example.black_jack;

public class Persona {
    private String nombre;
    private int numero;

    public Persona(String nombre, int numero) {
        this.nombre = nombre;
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntos() {
        return numero;
    }

    public void setPuntos(int puntos) {
        this.numero = puntos;
    }
}
