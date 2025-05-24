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


    public CeldaVacia(Coordenada coordenada) {
        super(coordenada,Estado.NADA);
    }

    @Override
    public Estado procesarDisparo() {
        if (getEstado() == Estado.NADA) {
            setEstado(Estado.AGUA);
            return getEstado();

        }else{
            return Estado.YA_DISPARADO;
        }

    }
}
