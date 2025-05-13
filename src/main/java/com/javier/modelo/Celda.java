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
    Barcos tipo;
    Coordenada coordenada;


    /**
     * Procesa un disparo en esta celda.
     * Actualiza el estado de la celda y devuelve el resultado del disparo.
     *
     * @return El resultado del disparo (AGUA, TOCADO, HUNDIDO).
     */
    public abstract Estado procesarDisparo();


}
