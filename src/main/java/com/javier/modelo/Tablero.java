package com.javier.modelo;

/**
 * Clase que representa el tablero de juego.
 * Contiene una matriz de celdas (jugador o CPU).
 * <p>
 * Tareas:
 * - Crear matriz 2D de `Celda`.
 * - Método para colocar barcos (automáticamente o manual).
 * - Método para recibir disparos en una coordenada.
 * - Método para mostrar el estado del tablero.
 * <p>
 * Autor: Josep
 */


public record Tablero(Celda[][] tablero) {


    private void generarTableroVacio() {
        // que se llene de Celdas vacias porfi
    }
}
