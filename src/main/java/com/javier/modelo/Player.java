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
    public boolean validarBarco(List<Coordenada> coordenadas) {





    }

    private void setBarco(List<Coordenada> barco) {
        // mireya esto lo llamo ya validado pero necesito que extraigas logica
        // al tablero algun metodo  que que pueda hacer tipo tableroPropio.asignar()
        // lo que me refiero es que no el jugador a mano coloque estas cordenadas como nuevas CeldaBarco
        // en el tablero si no que el tablero tenga metodos utiles, que me ahorren trabajo
        // pero piensa que al final es solo un tablero su proposito es contener informacion y celdas
        // saber darte la que le pides y saber colocar las que le pasas , con un minimo de validaciones de proteccion.

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