package com.javier.audio;


public class Reproductor {
    private final Audio[] listaReproduccion;
    private int posicion;

    /**
     * Crea un reproductor con una lista de audios.
     *
     * @param audios Lista de objetos {@link Audio} que conforman la lista de reproducción.
     * @throws IllegalArgumentException si el array es null o está vacío.
     */
    public Reproductor(Audio... audios) {
        if (audios == null || audios.length == 0) {
            throw new IllegalArgumentException("Audios array is null or empty");
        }
        listaReproduccion = audios;
        posicion = 0;
    }

    /**
     * Reproduce el sonido actual en la posición activa de la lista.
     */
    public void play() {
        listaReproduccion[posicion].play();
    }

    /**
     * Detiene la reproducción del sonido actual.
     */
    public void stop() {
        listaReproduccion[posicion].stop();
    }

    /**
     * Reinicia la lista de reproducción desde el primer sonido y lo reproduce.
     */
    public void reStart() {
        listaReproduccion[posicion].reset();
        posicion = 0;
        play();
    }

    /**
     * Reinicia el sonido actual desde el principio y lo reproduce.
     */
    public void reStartSound() {
        listaReproduccion[posicion].reStart();
    }

    /**
     * Avanza al siguiente sonido en la lista de forma circular y lo reproduce.
     */
    public void next() {
        listaReproduccion[posicion].reset();
        posicion = (posicion + 1) % listaReproduccion.length;
        play();
    }

    /**
     * Reinicia el estado de la lista y vuelve a la primera posición sin reproducir.
     */
    public void resetPlayList() {
        listaReproduccion[posicion].reset();
        posicion = 0;
    }

    /**
     * Reinicia el sonido actual sin cambiar de posición.
     */
    public void resetSound() {
        listaReproduccion[posicion].reset();
    }

    /**
     * Reproduce el sonido en la posición indicada.
     *
     * @param posicion Índice del sonido a reproducir.
     * @throws IllegalArgumentException si la posición es inválida.
     */
    public void play(int posicion) throws IllegalArgumentException {
        if (posicion < 0 || posicion >= listaReproduccion.length) {
            throw new IllegalArgumentException("Posicion no valida");
        }
        resetSound();
        this.posicion = posicion;
        play();
    }

    /**
     * Devuelve el índice del sonido actualmente activo en la lista.
     *
     * @return posición actual.
     */
    public int getCurrentIndex() {
        return posicion;
    }
}
