package com.javier.modelo;



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
