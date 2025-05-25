package com.javier.modelo;

import com.javier.vista.Config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Clase que representa una CPU como jugador en el juego de Batalla Naval.
 * Gestiona la colocación aleatoria de barcos y los disparos siguiendo una estrategia.
 * Utiliza el patrón Strategy para seleccionar la forma de disparar.
 * Hereda de la clase Player.
 */
public class Cpu extends Player {

    /**
     * Enumeración para las direcciones posibles de colocación de barcos.
     */
    private enum Direccion {
        N, S, E, O
    }

    /**
     * Generador de números aleatorios para la CPU
     */
    private final Random rnd;

    /**
     * Estrategia actual utilizada para disparar
     */
    private Estrategia strategy;

    /**
     * Constructor por defecto.
     * Inicializa el generador de números aleatorios, el conjunto de disparos realizados
     * y genera los barcos en el tablero propio.
     */
    public Cpu() {
        this.rnd = new Random();
        this.disparadas = new HashSet<>();
        Barcos[] flota = {
                Barcos.PORTAAVIONES,
                Barcos.ACORAZADO,
                Barcos.CRUCERO,
                Barcos.CRUCERO,
                Barcos.SUBMARINO,
                Barcos.DESTRUCTOR,
                Barcos.DESTRUCTOR
        };
        for (Barcos tipo : flota) {
            this.barcos.add(new Barco(tipo));
        }
        generarBarcos();
    }

    /**
     * Genera y coloca todos los barcos en el tablero propio de manera aleatoria.
     * Si no puede colocar un barco tras varios intentos, lanza una excepción.
     *
     * @throws RuntimeException si no se puede colocar un barco después de MAX_INTENTOS_COLOCACION intentos.
     */
    public void generarBarcos() {
        tableroPropio.generarTableroVacio();

        for (Barco barcoActual : this.barcos) {
            boolean colocado = false;
            int intentos = 0;

            while (!colocado && intentos < Config.MAX_INTENTOS_COLOCACION) {
                List<Coordenada> coordenadasBarco = encontrarPosicionParaBarco(barcoActual.getLongitud());

                if (coordenadasBarco != null && coordenadasBarco.size() == barcoActual.getLongitud()) {
                    for (Coordenada coord : coordenadasBarco) {
                        CeldaBarco celdaDeEsteBarco = new CeldaBarco(coord, barcoActual);
                        this.tableroPropio.setCelda(coord.y(), coord.x(), celdaDeEsteBarco);
                    }
                    barcoActual.setColocado(true);
                    barcoActual.setCoordenadas(coordenadasBarco);
                    colocado = true;

                    System.out.println(" Barco " + barcoActual.getTipo() + " colocado en: " + coordenadasBarco);
                }

                intentos++;
            }

            if (!colocado) {
                System.err.println("No se pudo colocar: " + barcoActual.getTipo());
                throw new RuntimeException("No se pudo colocar el barco: " + barcoActual.getTipo());
            }
        }
    }


    /**
     * Intenta encontrar una posición válida (lista de coordenadas) para un barco de una longitud dada.
     *
     * @param longitud Longitud del barco a colocar.
     * @return Lista de coordenadas donde se puede colocar el barco, o null si no encuentra espacio.
     */
    private List<Coordenada> encontrarPosicionParaBarco(int longitud) {

        int intentos = 0;

        while (intentos < Config.MAX_INTENTOS_COLOCACION) {

            int columna = rnd.nextInt(tableroPropio.getCeldas()[0].length);
            int fila = rnd.nextInt(tableroPropio.getCeldas().length);
            Coordenada cord = new Coordenada(columna, fila);


            Direccion[] direcciones = Direccion.values();
            for (Direccion dir : direcciones) {
                List<Coordenada> celdasBarco = comprobarEjes(cord, dir, longitud);
                if (celdasBarco != null && celdasBarco.size() == longitud) {
                    return celdasBarco;
                }
            }

            intentos++;
        }

        return null;
    }


    /**
     * Comprueba si desde una coordenada inicial y en una dirección dada, cabe un barco de la longitud indicada.
     *
     * @param cord      Coordenada inicial.
     * @param direccion Dirección en la que se quiere colocar el barco.
     * @param longitud  Longitud del barco.
     * @return Lista de coordenadas válidas para el barco, o null si no cabe.
     */
    private List<Coordenada> comprobarEjes(Coordenada cord, Direccion direccion, int longitud) {
        List<Coordenada> coordenadas = new ArrayList<>();
        int x = cord.x();
        int y = cord.y();


        if (x < 0 || y < 0 || x >= 10 || y >= 10) {
            return null;
        }


        if (!(tableroPropio.getCeldas()[y][x] instanceof CeldaVacia)) {
            return null;
        }

        int dx = 0;
        int dy = 0;
        switch (direccion) {
            case N -> dy = -1;
            case S -> dy = 1;
            case E -> dx = 1;
            case O -> dx = -1;
        }


        for (int i = 0; i < longitud; i++) {
            int currentX = x + (dx * i);
            int currentY = y + (dy * i);


            if (currentX < 0 || currentY < 0 || currentX >= 10 || currentY >= 10) {
                return null;
            }


            if (!(tableroPropio.getCeldas()[currentY][currentX] instanceof CeldaVacia)) {
                return null;
            }

            coordenadas.add(new Coordenada(currentX, currentY));
        }

        return coordenadas;
    }


    /**
     * Realiza un disparo al tablero enemigo utilizando la estrategia seleccionada.
     *
     * @param enemigo    Tablero enemigo donde disparar.
     * @param estrategia Estrategia a seguir para disparar.
     * @return Coordenada disparada.
     */
    public Coordenada disparar(Tablero enemigo, Estrategia estrategia) {
        setStrategy(estrategia);
        return strategy.disparar(enemigo, disparadas);
    }

    /**
     * Devuelve el generador de números aleatorios de la CPU.
     *
     * @return Objeto Random utilizado.
     */
    public Random getRnd() {
        return rnd;
    }

    /**
     * Devuelve la estrategia actual de disparo de la CPU.
     *
     * @return Estrategia utilizada.
     */
    public Estrategia getStrategy() {
        return strategy;
    }

    /**
     * Establece la estrategia de disparo de la CPU.
     *
     * @param strategy Nueva estrategia.
     */
    public void setStrategy(Estrategia strategy) {
        this.strategy = strategy;
    }

    /**
     * Devuelve el conjunto de coordenadas ya disparadas por la CPU.
     *
     * @return Conjunto de coordenadas disparadas.
     */
    public Set<Coordenada> getDisparadas() {
        return disparadas;
    }
}
