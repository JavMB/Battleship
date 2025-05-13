package com.javier.modelo;

public enum Barcos {
    PORTAAVIONES(1, 5), BUQUE(2, 4), DESCTRUCTOR(3, 3), FRAGATA(2, 2), SUBMARINO(4, 1);
    private final int cantidad;
    private final int longitud;

    Barcos(int cantidad, int longitud) {
        this.cantidad = cantidad;
        this.longitud = longitud;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getLongitud() {
        return longitud;
    }
}
