package com.javier.modelo;

import com.javier.vista.Config;

import java.util.Arrays;
import java.util.Objects;


public class Tablero {
    private final Celda[][] celdas;

    public Tablero() {
        celdas = new Celda[Config.FILAS_TABLERO][Config.COLUMNAS_TABLERO];
        generarTableroVacio();
    }

    public Celda[][] getCeldas() {
        return celdas;
    }

    /**
     * Metodo que se llama al crear el tablero para llenar las celdas con celdaVacia directamente
     */
    public void generarTableroVacio() {
        for (int i = 0; i < Config.FILAS_TABLERO; i++) {
            for (int j = 0; j < Config.COLUMNAS_TABLERO; j++) {
                celdas[i][j] = new CeldaVacia(new Coordenada(i, j));
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

    /**
     * Metodo para asignar barcos a celdas
     * @param fila La fila en la que se encuentra el barco
     * @param col La columna en la que se encuentra el barco
     * @param celdaDeEsteBarco La CeldaBarco que va en la celda
     */
    public void setCelda(int fila, int col, CeldaBarco celdaDeEsteBarco) {
        celdas[fila][col] = celdaDeEsteBarco;
    }

    /**
     * Metodo para devolver uan celda específica
     * @param fila La fila en la que se encuentra la celda
     * @param col La columna en la que se encuentra la celda
     * @return La celda que hay en esa fila y columna
     */
    public Celda getCelda(int fila, int col) {
        return celdas[fila][col];
    }
}
