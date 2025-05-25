package com.javier.modelo;

public enum Barcos {
    PORTAAVIONES(5),
    ACORAZADO(4),
    CRUCERO(3),
    SUBMARINO(3),
    DESTRUCTOR(2);

    private final int longitud;

    Barcos(int longitud) {
        this.longitud = longitud;
    }

    public int getLongitud() {
        return longitud;
    }
}
