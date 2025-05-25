package com.javier.audio;

public class ReproductorEfectos extends Reproductor {
    private static ReproductorEfectos instance;

    private ReproductorEfectos() {
        super(new Audio("efectos/tocar_agua.wav"), new Audio("efectos/tocado.wav"), new Audio("efectos/hundir.wav"), new Audio("efectos/wine.wav"), new Audio("efectos/lose.wav"));
    }

    /**
     * Patrón Singleton para asegurar que solo haya una instancia de ReproductorEfectos
     *
     * @return instancia única de ReproductorEfectos
     */
    public static ReproductorEfectos getInstance() {
        if (instance == null) {
            instance = new ReproductorEfectos();
        }
        return instance;
    }

    /**
     * Reproduce el sonido de efectos sin bucle.
     * <p>
     * Este método se utiliza para reproducir sonidos de efectos que no necesitan repetirse.
     */
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
     * <p>
     * Recomendacion:Reproducir luego de tocado si el barco se ha hundido
     */
    public void hundir() {
        super.play(2);
    }

    /**
     * Reproduce el sonido de victoria
     */
    public void win() {
        super.play(3);
    }


    /**
     * Reproduce el sonido de derrota
     */
    public void lose() {
        super.play(4);

    }
}
