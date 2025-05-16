package com.javier.modelo;

import java.util.Set;

public interface Estrategia {
    Coordenada disparar(Tablero enemigo, Set<Coordenada> yaDisparadas);

}
