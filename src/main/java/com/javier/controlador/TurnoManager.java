package com.javier.controlador;

import java.util.Objects;


public final class TurnoManager {

    public enum Turno {
        CPU("Es el turno de la CPU"),
        JUGADOR("Es tu turno");

        private final String texto;

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

    /**
     * Cambia el turno entre la CPU y el jugador
     */
    public void siguienteTurno() {
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
