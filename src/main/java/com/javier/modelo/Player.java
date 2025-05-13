package com.javier.modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private Tablero tableroPropio;

    private final List<Barco> barcos;

    protected Player() {
        this.barcos = new ArrayList<>();
    }

    public abstract Tablero generarTablero();

    public abstract Coordenada disparar();


}
