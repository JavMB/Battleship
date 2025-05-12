package com.javier.modelo;
/**
 * Clase base para representar una celda del tablero.
 * Puede contener un barco, estar vacía, o haber sido disparada.
 *
 * Tareas:
 * - Crear clase abstracta (o normal) con métodos comunes como:
 *   `esTocado()`, `mostrar()`, `tieneBarco()`, etc.
 * - Será la base para las subclases `CeldaBarco`, `CeldaVacia`, etc.
 *
 * Autor: Josep
 */


public abstract class Celda {
    protected boolean disparada;
    public Celda() {
        this.disparada = false;
    }

    public boolean isDisparada() {
        return disparada;
    }

    public void disparar() {
        disparada = true;
    }
    public abstract boolean tieneBarco();


}
