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
    private static final int TAMANYO_TABLERO = 10;

    private Celda[][] celdas;

    public Tablero(){
        celdas = new Celda[TAMANYO_TABLERO][TAMANYO_TABLERO];
        generarTableroVacio();
    }

    private void generarTableroVacio() {
        for (int i = 0; i < TAMANYO_TABLERO; i++) {
            for (int j = 0; j < TAMANYO_TABLERO; j++) {
                celdas[i][j] = new CeldaVacia();
            }
        }
    }

}
