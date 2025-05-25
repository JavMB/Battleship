package com.javier.modelo;

public class CeldaBarco extends Celda {
    private final Barco barco;

    public CeldaBarco(Coordenada coordenada, Barco barco) {
        super(coordenada);
        this.barco = barco;
    }

    /**
     * @return true si el disparo ha impactado en el barco.
     *          False si ya estaba tocada.
     */
    @Override
    public boolean procesarDisparo() {
        if (!tocada) {
            tocada = true;
            barco.registrarImpacto();
            return true;
        }
        return false;
    }

    public Barco getBarco() {
        return barco;
    }
}
