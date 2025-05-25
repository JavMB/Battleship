package com.javier.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Player implements Estrategia {
    protected final Tablero tableroPropio;
    protected final List<Barco> barcos; // SOLO PARA CICLO DEL JUEGO
    protected Set<Coordenada> disparadas;

    public Player() {
        this.disparadas = new HashSet<>();
        this.tableroPropio = new Tablero();
        this.barcos = new ArrayList<>();
    }

    /**
     * Verifica si todos los barcos del jugador están hundidos.
     *
     * @return true si todos los barcos están hundidos, false en caso contrario
     */
    public boolean todosLosBarcosHundidos() {
        for (Barco barco : barcos) {
            if (barco.isColocado() && !barco.isHundido()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Coordenada disparar(Tablero enemigo, Set<Coordenada> yaDisparadas) {
        return null;
    }

    public Tablero getTableroPropio() {
        return tableroPropio;
    }

    public List<Barco> getBarcos() {
        return barcos;
    }

    public Set<Coordenada> getDisparadas() {
        return disparadas;
    }
}

