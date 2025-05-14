package com.javier.controlador;

import java.util.Objects;

/**
 * Clase que controla de quién es el turno (jugador o CPU).
 *
 * Tareas:
 * - Usar una **variable** para guardar si es el turno del jugador o de la CPU.
 *   Puedes usar algo simple, como un `enum` con `JUGADOR` y `CPU` (por ejemplo).
 * - Crear un método llamado `siguienteTurno()`. Este método debe cambiar el turno. Si es del jugador, que pase a la CPU, y si es de la CPU, que pase al jugador.
 * - Crear un método `getTurnoActual()` para saber si es el turno del jugador o de la CPU. Este método debe devolver un valor que nos indique el turno actual.
 *
 *
 * - Usar un `enum` (enumerado) con 2 valores: `JUGADOR` y `CPU`.
 * - Asegúrate de cambiar el turno cada vez que se llame a `siguienteTurno()`.
 *
 * Autor: Mireya
 */

public class TurnoManager {

    enum Turno {
        CPU ("Es el turno de la CPU"),
        JUGADOR ("Es tu turno");

        private String texto;

        Turno(String texto) {
            this.texto = texto;
        }

        @Override
        public String toString() {
            return texto;
        }
    }

    private Turno turnoActual;

    public TurnoManager() {
        this.turnoActual = Turno.JUGADOR;
    }

    public Turno getTurnoActual() {
        return turnoActual;
    }

    public void siguienteTurno(){
        Turno[] turnos = Turno.values();
        int siguiente = (turnoActual.ordinal() + 1) % turnos.length;
        turnoActual = turnos[siguiente];
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TurnoManager that = (TurnoManager) o;
        return turnoActual == that.turnoActual;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(turnoActual);
    }

    @Override
    public String toString() {
        return "TurnoManager{" +
                "turnoActual=" + turnoActual +
                '}';
    }
}
