package com.javier.modelo;


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
        this.impactosRecibidos = 0;
    }



    public int getLongitud() {
        return this.tipo.getLongitud();
    }

    public Barcos getTipo() {
        return tipo;
    }

    public void registrarImpacto() {
        impactosRecibidos++;
    }

    public boolean isHundido() {
        return impactosRecibidos >= getLongitud();
    }

}
