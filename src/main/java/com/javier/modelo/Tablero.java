package com.javier.modelo;

import java.util.Arrays;
import java.util.Objects;

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
 * Autor: Mireya
 */


public class Tablero{
    private static final int TAMANYO_TABLERO = 10;

    private Celda[][] celdas;

    public Tablero(){
        celdas = new Celda[TAMANYO_TABLERO][TAMANYO_TABLERO];
        generarTableroVacio();
    }

    public Celda[][] getCeldas() {
        return celdas;
    }

    public void setCeldas(Celda[][] celdas) {
        this.celdas = celdas;
    }

    private void generarTableroVacio() {
        for (int i = 0; i < TAMANYO_TABLERO; i++) {
            for (int j = 0; j < TAMANYO_TABLERO; j++) {
                celdas[i][j] = new CeldaVacia();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tablero tablero = (Tablero) o;
        return Objects.deepEquals(celdas, tablero.celdas);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(celdas);
    }

    @Override
    public String toString() {
        return "Tablero{" +
                "celdas=" + Arrays.toString(celdas) +
                '}';
    }
}
