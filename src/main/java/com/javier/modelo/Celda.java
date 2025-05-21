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
    private boolean disparada;
    public Celda() {
        this.disparada = false;
    }

    protected boolean isDisparada() {
        return disparada;
    }

    public final boolean esImpactada() {
        return isDisparada() && tieneBarco();
    }


    public void disparar(int x, int y) {
        disparada = true;
    }
    public abstract boolean tieneBarco();


    @Override
    public String toString() {
        return "Celda{" + "disparada=" + disparada + '}';

    }
}
