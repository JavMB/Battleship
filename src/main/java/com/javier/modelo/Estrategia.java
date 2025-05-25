package com.javier.modelo;

import java.util.Set;

public interface Estrategia {

    /**
     * Método con el que la CPU dispara
     * @param enemigo El tablero del jugador
     * @param yaDisparadas Las celdas que ya han sido disparadas
     * @return La coordenada que se dispara
     */
    Coordenada disparar(Tablero enemigo, Set<Coordenada> yaDisparadas);

}
