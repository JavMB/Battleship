package com.javier.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un barco dentro del tablero.
 * Cada barco tiene una longitud, una posición inicial y una orientación.
 * <p>
 * Tareas:
 * - Atributos: tamaño, lista de coordenadas ocupadas.
 * - Métodos para saber si está hundido, registrar impactos, etc.
 * - Usada por Tablero para colocar los barcos.
 * <p>
 * Autor: Josep
 */


public class Barco {
    private Barcos tipo;
    private int impactosRecibidos;


    public Barco(Barcos tipo) {
        this.tipo = tipo;
    }


    /**
     * @param isVertical    valor booleano para determinar si debe de tener la misma fila o la misma columna
     * @param filaInicio    valor del inicio de la fila
     * @param columnaInicio valor de inicio de la columna
     * @param filaFinal     valor de fin de la fila
     * @param columnaFinal  valor de fin de la columna
     * @throws IllegalArgumentException En caso de que no estuviera bien los valores del barco
     */
    private void isValido(boolean isVertical, int filaInicio, int columnaInicio,
                          int filaFinal, int columnaFinal) throws IllegalArgumentException {
        if (filaInicio == filaFinal && columnaInicio == columnaFinal) {
            throw new IllegalArgumentException("El barco no Puede ser de 1x1");
        }
        if (MAX_DISTANCIA < Math.abs(filaInicio - filaFinal) + 1 || MAX_DISTANCIA < Math.abs(columnaInicio - columnaFinal) + 1) {
            throw new IllegalArgumentException("El barco no puede ocupar más de 5 casillas");
        }

        if (isVertical) {
            if (!(filaInicio != filaFinal && columnaInicio == columnaFinal)) {

                throw new IllegalArgumentException("El barco no Puede estar en diagonal");
            }
        } else {
            if (columnaInicio == columnaFinal) {
                throw new IllegalArgumentException("El barco no Puede estar en diagonal");

            }
        }
    }

    public int getLongitud() {
        if (posicionInicial[0] == posicionFinal[0]) {
            return 1 + Math.abs(posicionInicial[1] - posicionFinal[1]);
        } else {
            return 1 + Math.abs(posicionInicial[0] - posicionFinal[0]);
        }
    }

    public Barcos getTipo() {
        return tipo;
    }

    public void registrarImpacto() {
        countDisparos++;
    }

    public boolean isHundido() {
        return countDisparos >= getLongitud();
    }

}
