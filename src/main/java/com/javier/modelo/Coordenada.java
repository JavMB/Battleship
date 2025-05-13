package com.javier.modelo;

/**
 * Clase que representa una coordenada en el tablero.
 * Se usa para marcar posiciones de barcos, disparos, etc.
 * <p>
 * Tareas:
 * - Crear dos atributos de tipo `int` llamados `x` y `y` para guardar las coordenadas.
 * - Crear un **constructor** para poder asignar los valores de `x` e `y` cuando se cree una nueva coordenada.
 * - Crear los métodos `getX()` y `getY()` para obtener los valores de `x` y `y`.
 * - Crear el método `toString()` para que la coordenada se pueda mostrar en forma de texto, como por ejemplo "(3, 5)".
 * <p>
 * <p>
 * - Los atributos `x` y `y` deben ser privados.
 * - El constructor debe tomar dos parámetros (uno para `x` y otro para `y`).
 * - Usa `toString()` para mostrar la coordenada como un texto bonito "(" + x + ", " + y + ")".
 * <p>
 * Autor: Jose Luis
 */

public record Coordenada(int x, int y) {
}
