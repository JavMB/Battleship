package com.javier.audio;


public class ReproductorMusica extends Reproductor {

    private static ReproductorMusica instancia;

    private ReproductorMusica() {
        super(new Audio("musica/Menu.wav"), new Audio("musica/Battle.wav"));
    }

    /**
     * Patrón Singleton para obtener una única instancia del reproductor de música.
     *
     * @return instancia única de ReproductorMusica
     */
    public static ReproductorMusica getInstancia() {
        if (instancia == null) {
            instancia = new ReproductorMusica();
        }
        return instancia;
    }


    /**
     * Reproduce la música del juego.
     * <p>
     * Recomendación: Llamar a este método al iniciar el juego.
     */
    @Override
    public void play() {
        super.play(true);
    }

    /**
     * reproduce la musica del menu
     */
    public void musicaMenu() {
        super.play(0);
    }

    /**
     * Reproduce la música para el juego
     */
    public void musicaBattle() {
        super.play(1);
    }
}
