package com.javier.modelo;

public class CeldaBarco extends Celda {
    private final Barco barco;

    public CeldaBarco(Coordenada coordenada, Barco barco) {
        super(coordenada);
        this.barco = barco;
    }

    @Override
    public boolean procesarDisparo() {
        if (!tocada) {
            tocada = true;
            barco.registrarImpacto();
            return true; // true = impacto
        }
        return false; // ya disparado anteriormente
    }

    public Barco getBarco() {
        return barco;
    }
}
