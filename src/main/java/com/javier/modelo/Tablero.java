package com.javier.modelo;

import java.util.Arrays;
import java.util.Objects;




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

    public void setCelda(int y, int x, CeldaBarco celdaDeEsteBarco) {
        celdas[x][y] = celdaDeEsteBarco;
    }

    public Celda getCelda(int fila, int col) {
        return celdas[fila][col];
    }
}
