package com.javier.modelo;





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

    public Barco getBarco() {
        return barco;
    }
}
