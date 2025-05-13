package com.javier.controlador;

import com.javier.modelo.Coordenada;
import com.javier.modelo.Tablero;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Clase que gestiona los movimientos de la CPU.
 * <p>
 * Tareas:
 * - Elegir coordenadas aleatorias para disparar.
 * - Evitar repetir disparos ya realizados.
 * - Si hay tiempo: mejorar la IA para que intente acertar mejor.
 * <p>
 * Autor: Javi
 */


public class CPU extends Player {
    private final Random rnd;
    private final Set<Coordenada> disparadas;

    public CPU() {
        this.rnd = new Random();
        this.disparadas = new HashSet<>();
    }

    public Coordenada disparo() {
        int x = rnd.nextInt(0, 10); // hacer Config para los valores
        int y = rnd.nextInt(0, 10);
        Coordenada disparo = new Coordenada(x, y);
        if (validarDisparo(disparo)) { // recursion porque la veia clara, aunq while es mejor pero no pasa nada
            disparadas.add(disparo);
            return disparo;
        } else {
            return disparo();
        }
    }

    private boolean validarDisparo(Coordenada disparo) {
        return !disparadas.contains(disparo);
    }

    public Tablero generartableroCpu(){



    }



}
