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
    private Estado estado;

    public CeldaBarco(Coordenada coordenada,Barco barco) {
        super(coordenada);
        this.barco = barco;
        this.estado = Estado.BARCO;
    }

    @Override
    public Estado procesarDisparo() {
        if (estado.equals(Estado.BARCO)) {
            barco.registrarImpacto();
            if (!(barco.isHundido())){
                estado = Estado.TOCADO;
                return estado;
            }else {
                estado = Estado.HUNDIDO;
                return estado;
            }

        }
        return Estado.YA_DISPARADO;
    }
}
