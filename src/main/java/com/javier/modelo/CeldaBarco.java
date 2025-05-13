package com.javier.modelo;
/**
 * Subclase de Celda que representa una celda donde hay un barco.
 *
 * Tareas:
 * - Heredar de Celda.
 * - Guardar si ya fue disparada o no.
 * - Mostrar símbolo o imagen de tocado o no tocado.
 *
 * Autor: Josep
 */


public class CeldaBarco extends Celda {
    private final Barco barco;
    @Override
    public Estado procesarDisparo() {
        return null;
    }
}
