package com.javier.modelo;
/**
 * Subclase de Celda que representa una celda sin barco.
 *
 * Tareas:
 * - Heredar de Celda.
 * - Marcar si fue disparada o no.
 * - Mostrar símbolo de agua, fallo, etc.
 *
 * Autor: Josep
 */


public class CeldaVacia extends Celda {

    private Estado estado;
    public CeldaVacia(Coordenada coordenada) {
        super(coordenada);
        this.estado = Estado.NADA;
    }

    @Override
    public Estado procesarDisparo() {
        if (estado == Estado.NADA) {
            estado = Estado.AGUA;
            return estado;

        }else{
            return Estado.YA_DISPARADO;
        }

    }
}
