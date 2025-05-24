package com.javier.modelo;

import java.util.Random;
import java.util.Set;

public class EstrategiaAleatoria implements Estrategia {
    private final Random random = new Random();

    @Override
    public Coordenada disparar(Tablero enemigo, Set<Coordenada> yaDisparadas) {
        Celda[][] celdas = enemigo.getCeldas();
        int filas = celdas.length;
        int columnas = celdas[0].length;

        if (yaDisparadas.size() >= filas * columnas){
            return null;
        }

        Coordenada disparo;
        do{
            int x = random.nextInt(filas);
            int y = random.nextInt(columnas);
            disparo = new Coordenada(x, y);
        }while (yaDisparadas.contains(disparo));

        return disparo;
    }
}
