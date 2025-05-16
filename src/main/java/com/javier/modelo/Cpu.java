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
    private enum Direccion {
        N, S, E, O
    }


    private final Random rnd;
    private Estrategia strategy;
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
                        this.tableroPropio.setCelda(coord.y(), coord.x(), celdaDeEsteBarco);

                    }
                    colocado = true;
                }
                intentos++;
            }
            if (!colocado) {
                throw new RuntimeException();
            }
        }
    }

    /**
     * Intenta encontrar una posición válida (coordenadas) para un barco de una longitud dada.
     */
    private List<Coordenada> encontrarPosicionParaBarco(int longitud) {
        boolean encontrada = false;
        List<Coordenada> celdasBarco;
        Direccion direccion = Direccion.values()[rnd.nextInt(Direccion.values().length)];
        do {
            int columna = rnd.nextInt(tableroPropio.tablero()[0].length);
            int fila = rnd.nextInt(tableroPropio.tablero().length);
            Coordenada cord = new Coordenada(columna, fila);
            celdasBarco = comprobarEjes(cord, direccion, longitud);

            if (celdasBarco != null) {
                encontrada = true;
            }

        } while (!encontrada);


        return celdasBarco;
    }

    private List<Coordenada> comprobarEjes(Coordenada cord, Direccion direccion, int longitud) {
        List<Coordenada> coordenadas = new ArrayList<>();
        int x = cord.x();
        int y = cord.y();

        int dx = 0;
        int dy = 0;

        switch (direccion) {
            case N -> dy = -1;
            case S -> dy = 1;
            case E -> dx = 1;
            case O -> dx = -1;
        }
        coordenadas.add(new Coordenada(x, y)); // la primera

        for (int i = 1; i < longitud; i++) {
            x += dx;
            y += dy;


            if (x >= 0 && y >= 0 && x < 10 && y < 10) {
                if (tableroPropio.tablero()[y][x] instanceof CeldaVacia) {
                    coordenadas.add(new Coordenada(x, y));
                } else return null;
            } else return null;

        }

        return coordenadas;
    }


    public Coordenada disparar(Tablero enemigo) { // patron Strategy podria tenerlo , mas dificultades
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

    public Random getRnd() {
        return rnd;
    }

    public Estrategia getStrategy() {
        return strategy;
    }

    public void setStrategy(Estrategia strategy) {
        this.strategy = strategy;
    }

    public Set<Coordenada> getDisparadas() {
        return disparadas;
    }


}