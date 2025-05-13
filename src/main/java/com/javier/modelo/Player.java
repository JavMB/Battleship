package com.javier.modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    protected static final int CANTIDAD_PORTAAVIONES = 1;

    protected final Tablero tableroPropio;
    protected final List<Barco> barcos;

    protected Player() {
        this.tableroPropio = new Tablero();
        this.barcos = new ArrayList<>();
    }

    private void instanciarBarcos() {
        for (Barcos b : Barcos.values()) {
            for (int i = 0; i < b.getCantidad(); i++) {
                barcos.add(new Barco(b));
            }
        }
    }


    public abstract void generarBarcos();

    public abstract Coordenada disparar();


}
