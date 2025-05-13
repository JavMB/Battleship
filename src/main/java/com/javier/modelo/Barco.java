package com.javier.modelo;

import java.util.*;

/**
 * Clase que representa un barco dentro del tablero.
 * Cada barco tiene una longitud, una posición inicial y una orientación.
 *
 * Tareas:
 * - Atributos: tamaño, lista de coordenadas ocupadas.
 * - Métodos para saber si está hundido, registrar impactos, etc.
 * - Usada por Tablero para colocar los barcos.
 *
 * Autor: Josep
 */


public class Barco {
    private final List<Coordenada> cordenadas;
    private final Map<Coordenada, Boolean> disparos;

    private Barco(Coordenada... cordenadas) {
        this.cordenadas = new ArrayList<>();
        this.cordenadas.addAll(Arrays.asList(cordenadas));

        disparos = new HashMap<>();
        for (Coordenada coordenada : cordenadas) {
            disparos.put(coordenada, false);
        }
    }


    public int numeroImpactos() {
        int impactos = 0;
        for (Coordenada coordenada : cordenadas) {
            if (Boolean.TRUE.equals(disparos.get(coordenada))) {
                impactos++;
            }
        }

        return impactos;
    }

    public int getLongitud() {
        return cordenadas.size();
    }

    public void registrarImpacto(Coordenada coordenada) {
        if (!disparos.containsKey(coordenada)) {
            throw new IllegalArgumentException("La coordenada no pertenece al barco");
        }
        if (Boolean.TRUE.equals(disparos.get(coordenada))) {
            throw new IllegalArgumentException("La coordenada ya ha sido impactada");
        }
        disparos.put(coordenada, true);
    }


    public Coordenada[] getCoordenadas() {
        return cordenadas.toArray(new Coordenada[cordenadas.size()]);
    }

    public boolean isHundido(){
            return  numeroImpactos()>= getLongitud();
    }

}
