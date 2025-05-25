package com.javier.modelo;

public class CeldaVacia extends Celda {
    public CeldaVacia(Coordenada coordenada) {
        super(coordenada);
    }

    /**
     * Procesa un disparo en una celda vacía.
     * Siempre devuelve, false ya que no hay barco en esta celda.
     *
     * @return false, indicando que el disparo ha fallado (agua).
     */
    @Override
    public boolean procesarDisparo() {
        if (!tocada) {
            tocada = true;
            return false; // false = agua/fallo
        }
        return false; // ya disparado anteriormente
    }
}
