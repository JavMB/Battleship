package com.javier.modelo;

public class CeldaVacia extends Celda {
    public CeldaVacia(Coordenada coordenada) {
        super(coordenada);
    }

    @Override
    public boolean procesarDisparo() {
        if (!tocada) {
            tocada = true;
            return false; // false = agua/fallo
        }
        return false; // ya disparado anteriormente
    }
}
