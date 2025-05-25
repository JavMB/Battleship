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

    public boolean isTocada() {
        return tocada;
    }

    public void setTocada(boolean tocada) {
        this.tocada = tocada;
    }

    public abstract boolean procesarDisparo();
}
