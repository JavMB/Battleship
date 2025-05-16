package com.javier.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Player implements Estrategia {
    protected final Tablero tableroPropio;
    protected final List<Barco> barcos;
    protected Set<Coordenada> disparadas;

    protected Player() {
        this.disparadas = new HashSet<>();
        this.tableroPropio = new Tablero(); //creado ya de vacias constructor de Tablero
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


    public void generarBarcos() {
    // manual barco por barco comprobando tablero
    }


    @Override
    public Coordenada disparar(Tablero enemigo, Set<Coordenada> yaDisparadas) {
        return null;
    }
}