package com.javier.modelo;

import java.util.ArrayList;
import java.util.List;

public class Barco {
    private Barcos tipo;
    private int impactosRecibidos;
    private boolean colocado;
    private List<Coordenada> coordenadas;

    public Barco(Barcos tipo) {
        this.tipo = tipo;
        this.impactosRecibidos = 0;
        this.colocado = false;
        this.coordenadas = new ArrayList<>();
    }

    public int getLongitud() {
        return this.tipo.getLongitud();
    }

    public Barcos getTipo() {
        return tipo;
    }

    public void registrarImpacto() {
        if (impactosRecibidos < getLongitud()) {
            impactosRecibidos++;
        }
    }

    /**
     *
     * @return Devuelve si se ha hundido el barco.
     */
    public boolean isHundido() {
        return impactosRecibidos >= getLongitud();
    }

    /**
     * Indica si el barco ya ha sido colocado en el tablero
     *
     * @return true si el barco ya está colocado, false en caso contrario
     */
    public boolean isColocado() {
        return colocado;
    }

    /**
     * Cambia el estado de colocación del barco
     *
     * @param colocado true para indicar que el barco está colocado
     */
    public void setColocado(boolean colocado) {
        this.colocado = colocado;
    }

    /**
     * Establece las coordenadas donde está colocado el barco
     *
     * @param coordenadas Lista de coordenadas donde está colocado el barco
     */
    public void setCoordenadas(List<Coordenada> coordenadas) {
        this.coordenadas = new ArrayList<>(coordenadas);
    }

    /**
     * Obtiene las coordenadas donde está colocado el barco
     *
     * @return Lista de coordenadas del barco
     */
    public List<Coordenada> getCoordenadas() {
        return new ArrayList<>(coordenadas);
    }
}
