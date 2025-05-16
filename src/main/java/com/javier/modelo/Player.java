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

    // solo valida cada barco
    public boolean validarBarco(List<Coordenada> coordenadas) {// el siguiente no colocado

    }

    private void setBarco(List<Coordenada> barco) {
        // MIREA esto lo llamo ya validado pero necesito que extraigas LOGICA
        // al tablero algun metodo  que que pueda hacer tipo tableroPropio.asignar()
        // y yap porque si no no paro de picar codigo y en la cpu algo tambien tendre asi.
    }


    @Override
    public Coordenada disparar(Tablero enemigo, Set<Coordenada> yaDisparadas) {
        return null;
    }

    public Tablero getTableroPropio() {
        return tableroPropio;
    }

}