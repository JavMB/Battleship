package com.javier.audio;

public class Reproductor {
    private final Audio[] listaReproduccion;
    private int posicion;
    public Reproductor(Audio... audios) {
        if (audios == null || audios.length == 0) {
            throw new IllegalArgumentException("Audio array is null or empty");
        }
        listaReproduccion = audios;

        posicion = 0;
    }

    public void play() {
        listaReproduccion[posicion].play();
    }

    public void stop(){
        listaReproduccion[posicion].stop();
    }
    public void reStart(){
        listaReproduccion[posicion].reset();
        posicion = 0;
        play();
    }
    public void nextSoud(){
        listaReproduccion[posicion].reset();
        posicion = (posicion + 1) % listaReproduccion.length;
        play();
    }
    public void reset(){
        listaReproduccion[posicion].reset();
    }
    public void reStartSoud(){
        listaReproduccion[posicion].reStart();
    }
}
