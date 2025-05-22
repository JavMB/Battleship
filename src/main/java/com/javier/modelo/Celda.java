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

    protected Celda(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public abstract Estado procesarDisparo();

    public Estado getEstado() {
        // josep el estado es igual para los dos tipos de celdas , no puedo usar el polimofismo bien en VentanaJuego
    }
}

