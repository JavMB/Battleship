package com.javier.modelo;
/**
 * Clase que representa un barco dentro del tablero.
 * Cada barco tiene una longitud, una posición inicial y una orientación.
 *
 * Tareas:
 * - Atributos: tamaño, lista de coordenadas ocupadas.
 * - Métodos para saber si está hundido, registrar impactos, etc.
 * - Usada por Tablero para colocar los barcos.
 *
 * Autor: Josep
 */


public class Barco {
    private static final int MAX_DISTANCIA = 5;
    private final int[] posicionInicial=new int [2] ;
    private final int[] posicionFinal= new int[2];


    public Barco(boolean isVertical,int filaInicio, int columnaInicio, int filaFinal, int columnaFinal) {
       isValido(isVertical,filaInicio,columnaInicio,filaFinal,columnaFinal);
        posicionInicial[0]=filaInicio;
        posicionInicial[1]=columnaInicio;
        posicionFinal[0]=filaFinal;
        posicionFinal[1]=columnaFinal;
    }


    /**
     *
     * @param isVertical valor booleano para determinar si debe de tener la misma fila o la misma columna
     * @param filaInicio valor del inicio de la fila
     * @param columnaInicio valor de inicio de la columna
     * @param filaFinal valor de fin de la fila
     * @param columnaFinal valor de fin de la columna
     * @throws IllegalArgumentException En caso de que no estuviera bien los valores del barco
     */
    private void isValido(boolean isVertical,int filaInicio, int columnaInicio,
                          int filaFinal, int columnaFinal) throws IllegalArgumentException {
        if (filaInicio == filaFinal &&  columnaInicio== columnaFinal ) {
            throw new IllegalArgumentException("El barco no Puede ser de 1x1");
        }
        if (MAX_DISTANCIA < Math.abs(filaInicio-filaFinal)+1|| MAX_DISTANCIA < Math.abs(columnaInicio-columnaFinal)+1) {
            throw new IllegalArgumentException("El barco no puede ocupar más de 5 casillas");
        }

        if (isVertical) {
            if (!(filaInicio != filaFinal && columnaInicio == columnaFinal)) {

                throw new IllegalArgumentException("El barco no Puede estar en diagonal");
            }
        }else {
            if (columnaInicio == columnaFinal) {
                throw new IllegalArgumentException("El barco no Puede estar en diagonal");

            }
        }
    }





}
