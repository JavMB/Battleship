package com.javier.modelo;

public abstract class Celda {
    protected Coordenada coordenada;
    protected boolean tocada;

    protected Celda(Coordenada coordenada) {
        this.coordenada = coordenada;
        this.tocada = false;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    /**
     * Indica si la celda ha sido tocada por un disparo.
     * @return true si la celda ha sido tocada, false en caso contrario.
     */
    public boolean isTocada() {
        return tocada;
    }

    public void setTocada(boolean tocada) {
        this.tocada = tocada;
    }

    /**
     * Procesa un disparo en la celda.
     * Debe ser implementado por las subclases para definir el comportamiento específico
     * al recibir un disparo.
     *
     * @return true si el disparo ha impactado, false si ha fallado.
     */
    public abstract boolean procesarDisparo();
}
