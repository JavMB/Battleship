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

    /**
     * Procesa el disparo en la celda.
     * @return El estado de la celda después de procesar el disparo.
     */
    public abstract Estado procesarDisparo();

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;

    }
}

