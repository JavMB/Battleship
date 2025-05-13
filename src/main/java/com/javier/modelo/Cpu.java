package com.javier.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Clase que gestiona los movimientos de la CPU.
 */
public class Cpu extends Player {
    private final Random rnd;
    private final Set<Coordenada> disparadas;
    private static final int MAX_INTENTOS_COLOCACION = 100;

    public Cpu() {
        super();
        this.rnd = new Random();
        this.disparadas = new HashSet<>();
    }

    @Override
    public void generarBarcos() {

        for (Barco barcoActual : this.barcos) {
            boolean colocado = false;
            int intentos = 0;
            while (!colocado && intentos < MAX_INTENTOS_COLOCACION) {
                List<Coordenada> coordenadasBarco = encontrarPosicionParaBarco(barcoActual.getLongitud());
                if (coordenadasBarco != null && !coordenadasBarco.isEmpty()) {

                    for (Coordenada coord : coordenadasBarco) {

                        CeldaBarco celdaDeEsteBarco = new CeldaBarco(barcoActual, coord);
                        this.tableroPropio.setCelda(coord.x(), coord.y(), celdaDeEsteBarco);
                    }
                    colocado = true;
                }
                intentos++;
            }
            if (!colocado) {

               //Excepcion o algo nose
            }
        }
    }

    /**
     * Intenta encontrar una posición válida (coordenadas) para un barco de una longitud dada.
     */
    private List<Coordenada> encontrarPosicionParaBarco(int longitud) {

    }


    @Override
    public Coordenada disparar() {
        int x, y;
        Coordenada disparo;
        int maxFilas = tableroPropio.getFilas();
        int maxColumnas = tableroPropio.getColumnas();

        do {
            x = rnd.nextInt(maxColumnas);
            y = rnd.nextInt(maxFilas);
            disparo = new Coordenada(x, y);
        } while (disparadas.contains(disparo));

        disparadas.add(disparo);
        return disparo;
    }


}