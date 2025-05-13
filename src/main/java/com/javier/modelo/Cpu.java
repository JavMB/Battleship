package com.javier.modelo;

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


public class Cpu extends Player {
    private final Random rnd;
    private final Set<Coordenada> disparadas;

    public Cpu() {
        super();
        this.rnd = new Random();
        this.disparadas = new HashSet<>();
    }

    @Override
    public void generarBarcos() {
        for (Barco b : barcos) {
            for (int i = 0; i < b.getTipo().getCantidad(); i++) {
                for (int j = 0; j < b.getTipo().getLongitud(); j++) {
                    Coordenada barco = generarCeldaBarco(b.getLongitud());
                    tableroPropio.tablero()[barco.x()][barco.y()] = new CeldaBarco();
                    b.getCeldasBarco().add(barco);
                }

            }
        }

    }


    @Override
    public Coordenada disparar() {
        int x = rnd.nextInt(0, 10); // hacer Config para los valores
        int y = rnd.nextInt(0, 10);
        Coordenada disparo = new Coordenada(x, y);
        if (validarCoordenadaDisparo(disparo)) { // recursion porque la veia clara, aunq while es mejor pero no pasa nada
            disparadas.add(disparo);
            return disparo;
        } else {
            return disparar();
        }
    }

    private boolean validarCoordenadaDisparo(Coordenada disparo) {
        return !disparadas.contains(disparo);
    }


    private Coordenada generarCeldaBarco(int longitud) {


    }


}
