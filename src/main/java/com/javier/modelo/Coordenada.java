package com.javier.modelo;

import java.util.Objects;

/**
 * Clase que representa una coordenada en el tablero.
 * Se usa para marcar posiciones de barcos, disparos, etc.
 *
 * Tareas:
 * - Crear dos atributos de tipo `int` llamados `x` y `y` para guardar las coordenadas.
 * - Crear un **constructor** para poder asignar los valores de `x` e `y` cuando se cree una nueva coordenada.
 * - Crear los métodos `getX()` y `getY()` para obtener los valores de `x` y `y`.
 * - Crear el método `toString()` para que la coordenada se pueda mostrar en forma de texto, como por ejemplo "(3, 5)".
 *
 *
 * - Los atributos `x` y `y` deben ser privados.
 * - El constructor debe tomar dos parámetros (uno para `x` y otro para `y`).
 * - Usa `toString()` para mostrar la coordenada como un texto bonito "(" + x + ", " + y + ")".
 *
 * Autor: Josep Vicent
 */

public class Coordenada {
    // Aquí se van a definir los atributos x y y, y los métodos getX(), getY() y toString().

    private final int x;
    private final int y;

    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coordenada that = (Coordenada) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordenada{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
