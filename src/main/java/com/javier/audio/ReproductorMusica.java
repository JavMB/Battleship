package com.javier.audio;


public class ReproductorMusica extends Reproductor {

    private static ReproductorMusica instancia;
    private ReproductorMusica() {
        super(new Audio("musica/Menu.wav"),new Audio("musica/Battle.wav"));
    }

    public static ReproductorMusica getInstancia() {
        if (instancia == null) {
            instancia = new ReproductorMusica();
        }
        return instancia;
    }


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
