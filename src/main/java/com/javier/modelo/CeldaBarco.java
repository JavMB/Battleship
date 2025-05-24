package com.javier.modelo;


/**
 * Subclase de Celda que representa una celda donde hay un barco.
 *
 * Tareas:
 * - Heredar de Celda.
 * - Guardar si ya fue disparada o no.
 * - Mostrar símbolo o imagen de tocado o no tocado.
 *
 * Autor: Josep
 */


public class CeldaBarco extends Celda {
    private final Barco barco;

    public CeldaBarco(Coordenada coordenada,Barco barco) {
        super(coordenada, Estado.BARCO);
        this.barco = barco;

    }

    @Override
    public Estado procesarDisparo() {
        if (getEstado().equals(Estado.BARCO)) {
            barco.registrarImpacto();
            if (!(barco.isHundido())){
                setEstado(Estado.TOCADO);
                return getEstado();
            }else {
                setEstado(Estado.HUNDIDO);
                return getEstado();
            }

        }
        return Estado.YA_DISPARADO;
    }
}
