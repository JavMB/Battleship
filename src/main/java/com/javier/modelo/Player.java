package com.javier.modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    protected final Tablero tableroPropio;
    protected final List<Barco> barcos;

    protected Player() {
        this.tableroPropio = new Tablero();
        this.barcos = new ArrayList<>();
        instanciarBarcos(); // llamada para crear la flota del jugador
    }

    private void instanciarBarcos() {
        for (Barcos tipoBarco : Barcos.values()) {
            for (int i = 0; i < tipoBarco.getCantidad(); i++) {
                barcos.add(new Barco(tipoBarco));
            }
        }
    }

    /**
     * Coloca los barcos de la lista 'barcos' en el 'tableroPropio'.
     * La lógica de cómo se colocan (aleatorio para CPU, manual para Humano)
     * se implementará en las subclases.
     */
    public abstract void generarBarcos(); // Este método ahora se enfoca en la COLOCACIÓN

    public abstract Coordenada disparar();
}