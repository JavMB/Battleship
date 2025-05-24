package com.javier.modelo;

/**
 * Clase base para representar una celda del tablero.
 * Puede contener un barco, estar vacía, o haber sido disparada.
 * <p>
 * Tareas:
 * - Crear clase abstracta (o normal) con métodos comunes como:
 * `esTocado()`, `mostrar()`, `tieneBarco()`, etc.
 * - Será la base para las subclases `CeldaBarco`, `CeldaVacia`, etc.
 * <p>
 * Autor: Josep
 */


public abstract class Celda {
    protected Coordenada coordenada;
    private Estado estado;
    protected Celda(Coordenada coordenada, Estado estado) {
        this.coordenada = coordenada;
        this.estado = estado;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public abstract Estado procesarDisparo();

    public Estado getEstado() {
        return estado;
    }

    protected void setEstado(Estado estado) {
        this.estado = estado;

    }
}

