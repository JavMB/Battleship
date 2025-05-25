package com.javier.audio;

public class ReproductorEfectos extends Reproductor {
    private static ReproductorEfectos instance;
    private ReproductorEfectos() {
        super(new Audio("efectos/tocar_agua.wav"), new Audio("efectos/tocado.wav"), new Audio("efectos/undir.wav"));
    }

    public static ReproductorEfectos getInstance() {
        if(instance == null) {
            instance = new ReproductorEfectos();
        }
        return instance;
    }

    @Override
    public void play() {
        super.play(false);
    }

    /**
     * Reproduce el sonido de agua
     */
    public void agua() {
        super.play(0);
    }

    /**
     * Reproduce el sonido de tocado
     */
    public void tocado() {
        super.play(1);
    }

    /**
     * Reproduce el sonido de hundir
     *
     * Recomendacion:Reproducir luego de tocado si el barco se ha hundido
     */
    public void hundir() {
        super.play(2);
    }
}
