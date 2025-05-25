package com.javier.modelo;




public abstract class Celda {
    protected Coordenada coordenada;
    private Estado estado;
    protected Celda(Coordenada coordenada, Estado estado) {
        this.coordenada = coordenada;
        this.estado = estado;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public abstract Estado procesarDisparo();

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;

    }
}

