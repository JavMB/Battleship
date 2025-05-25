package com.javier.controlador;

import com.javier.modelo.*;
import com.javier.vista.VentanaJuego;

import java.util.ArrayList;
import java.util.List;

public class GestorJuego {
    private Player jugador;
    private Cpu cpu;
    private VentanaJuego vista;
    private Modo modoActual;
    private TurnoManager turnos;
    private final Estrategia estrategiaAleatoria;
    private final Estrategia estrategiaCaza;

    private enum Modo {COLOCACION, DISPARO, FIN}

    private static final Barcos[] FLOTA_A_COLOCAR = {
            Barcos.PORTAAVIONES,
            Barcos.BUQUE,
            Barcos.DESCTRUCTOR,
            Barcos.FRAGATA,
            Barcos.FRAGATA
    };
    private int indiceBarcoAPlantar = 0;

    public GestorJuego(VentanaJuego vista) {
        this.vista = vista;
        this.jugador = new Player();
        this.cpu = new Cpu();
        this.vista.setControlador(this);
        this.modoActual = Modo.COLOCACION;
        this.turnos = new TurnoManager();
        this.estrategiaAleatoria = new EstrategiaAleatoria();
        this.estrategiaCaza = new EstrategiaTocaryHundir();

        this.vista.actualizarVistaTableroJugador(jugador.getTableroPropio());
        this.vista.actualizarVistaTableroEnemigo(cpu.getTableroPropio());

        iniciarFaseColocacion();
    }

    private void iniciarFaseColocacion() {
        modoActual = Modo.COLOCACION;
        indiceBarcoAPlantar = 0;
        jugador.getBarcos().clear();
        jugador.getTableroPropio().generarTableroVacio();
        vista.actualizarVistaTableroJugador(jugador.getTableroPropio());

        vista.setModoColocacion(true);
        vista.setModoDisparo(false);

        if (indiceBarcoAPlantar < FLOTA_A_COLOCAR.length) {
            Barcos tipoPrimerBarco = FLOTA_A_COLOCAR[indiceBarcoAPlantar];
            vista.mostrarMensaje("Coloca tu " + tipoPrimerBarco.name() + " (" + tipoPrimerBarco.getLongitud() + " celdas)");
        } else {
            vista.mostrarError("Error: No hay barcos definidos para colocar.");
            try {
                throw new ErrorCriticoException("No hay barcos definidos para colocar.");
            } catch (ErrorCriticoException e) {
                modoActual = Modo.FIN;
                vista.bloquearTableros();
                vista.mostrarError(e.getMessage());
            }

        }
    }

    public void manejarClickJugador(int fila, int col) {
        if (modoActual != Modo.COLOCACION) {
            return;
        }

        if (indiceBarcoAPlantar >= FLOTA_A_COLOCAR.length) {
            vista.mostrarError("Ya has colocado todos tus barcos. Pasando a fase de disparo.");
            finalizarColocacionEIniciarDisparo();
            return;
        }

        Barcos tipoBarcoActual = FLOTA_A_COLOCAR[indiceBarcoAPlantar];
        Barco barcoIntentado = new Barco(tipoBarcoActual);
        boolean esHorizontal = vista.isOrientacionHorizontal();
        int longitud = barcoIntentado.getLongitud();

        if (esHorizontal) {
            if (col + longitud > Tablero.TAMANYO_TABLERO) {
                vista.mostrarError("El " + tipoBarcoActual.name() + " se sale del tablero (horizontal).");
                return;
            }
        } else {
            if (fila + longitud > Tablero.TAMANYO_TABLERO) {
                vista.mostrarError("El " + tipoBarcoActual.name() + " se sale del tablero (vertical).");
                return;
            }
        }

        List<Coordenada> coordenadasPotenciales = new ArrayList<>();
        for (int i = 0; i < longitud; i++) {
            int cFila = esHorizontal ? fila : fila + i;
            int cCol = esHorizontal ? col + i : col;

            if (cFila < 0 || cFila >= Tablero.TAMANYO_TABLERO || cCol < 0 || cCol >= Tablero.TAMANYO_TABLERO) {
                vista.mostrarError("Coordenada fuera de tablero al verificar solapamiento.");
                return;
            }

            if (jugador.getTableroPropio().getCelda(cFila, cCol) instanceof CeldaBarco) {
                vista.mostrarError("No puedes solapar el " + tipoBarcoActual.name() + " con otro barco.");
                return;
            }
            coordenadasPotenciales.add(new Coordenada(cCol, cFila));
        }

        for (Coordenada coord : coordenadasPotenciales) {
            CeldaBarco celdaDeEsteBarco = new CeldaBarco(coord, barcoIntentado);
            jugador.getTableroPropio().setCelda(coord.y(), coord.x(), celdaDeEsteBarco);
        }
        barcoIntentado.setColocado(true);
        barcoIntentado.setCoordenadas(coordenadasPotenciales);
        jugador.getBarcos().add(barcoIntentado);

        vista.actualizarVistaTableroJugador(jugador.getTableroPropio());

        indiceBarcoAPlantar++;
        if (indiceBarcoAPlantar < FLOTA_A_COLOCAR.length) {
            Barcos siguienteTipo = FLOTA_A_COLOCAR[indiceBarcoAPlantar];
            vista.mostrarMensaje("Coloca tu " + siguienteTipo.name() + " (" + siguienteTipo.getLongitud() + " celdas)");
        } else {
            finalizarColocacionEIniciarDisparo();
        }
    }

    private void finalizarColocacionEIniciarDisparo() {
        vista.mostrarMensaje("Todos tus barcos colocados. La CPU está colocando los suyos...");
        cpu.generarBarcos();
        cpu.setStrategy(estrategiaAleatoria);

        modoActual = Modo.DISPARO;
        vista.setModoColocacion(false);
        vista.setModoDisparo(true);

        vista.mostrarMensaje("¡Comienza la batalla! Dispara contra el enemigo.");
    }


    public void manejarClickEnemigo(int fila, int col) {
        if (modoActual != Modo.DISPARO) {
            return;
        }

        try {
            Coordenada coordDisparo = new Coordenada(col, fila);
            if (jugador.getDisparadas().contains(coordDisparo)) {
                vista.mostrarError("Ya has disparado en (" + col + "," + fila + ")");
                return;
            }
            jugador.getDisparadas().add(coordDisparo);

            Celda celdaObjetivoCPU = cpu.getTableroPropio().getCelda(fila, col);
            Estado resultado = celdaObjetivoCPU.procesarDisparo();

            vista.actualizarVistaTableroEnemigo(cpu.getTableroPropio());

            if (resultado == Estado.AGUA) {
                vista.mostrarMensaje("¡Agua! Disparaste en (" + col + "," + fila + ")");
            } else if (resultado == Estado.TOCADO) {
                vista.mostrarMensaje("¡Tocado! Impacto en (" + col + "," + fila + ")");
            } else if (resultado == Estado.HUNDIDO) {
                vista.mostrarMensaje("¡HUNDIDO! Has hundido un barco enemigo en (" + col + "," + fila + ")");
                CeldaBarco celdaBarco = (CeldaBarco) celdaObjetivoCPU;
                for (Coordenada c : celdaBarco.getBarco().getCoordenadas()) {
                    cpu.getTableroPropio().getCelda(c.y(), c.x()).setEstado(Estado.HUNDIDO);
                }
                vista.actualizarVistaTableroEnemigo(cpu.getTableroPropio());
            }

            if (comprobarFinDeJuego()) {
                return;
            }

            realizarTurnoCpu();

        } catch (Exception e) {
            vista.mostrarError("Error al procesar disparo: " + e.getMessage());
        }
    }

    private void realizarTurnoCpu() {
        if (modoActual != Modo.DISPARO) return;

        vista.mostrarMensaje("Turno de la CPU...");

        try {
            Coordenada coordDisparoCpu = cpu.disparar(jugador.getTableroPropio(), cpu.getStrategy());

            Celda celdaObjetivoJugador = jugador.getTableroPropio().getCelda(coordDisparoCpu.y(), coordDisparoCpu.x());
            Estado resultadoCpu = celdaObjetivoJugador.procesarDisparo();

            vista.actualizarVistaTableroJugador(jugador.getTableroPropio());

            String mensajeCpu = "CPU disparó en (" + coordDisparoCpu.x() + "," + coordDisparoCpu.y() + "): ";
            if (resultadoCpu == Estado.AGUA) {
                mensajeCpu += "¡Agua!";
            } else if (resultadoCpu == Estado.TOCADO) {
                mensajeCpu += "¡Tocado!";
                cpu.setStrategy(estrategiaCaza);
            } else if (resultadoCpu == Estado.HUNDIDO) {
                mensajeCpu += "¡HUNDIDO! Tu barco ha sido hundido.";
                cpu.setStrategy(estrategiaAleatoria);
            }
            vista.mostrarMensaje(mensajeCpu);

            comprobarFinDeJuego();

        } catch (Exception e) {
            vista.mostrarError("Error en el turno de la CPU: " + e.getMessage());
        }
    }


    private boolean comprobarFinDeJuego() {
        if (jugador.todosLosBarcosHundidos()) {
            finalizarPartida(false);
            return true;
        } else if (cpu.todosLosBarcosHundidos()) {
            finalizarPartida(true);
            return true;
        }
        return false; // nadie ha ganado todavía
    }


    private void finalizarPartida(boolean jugadorGano) {
        modoActual = Modo.FIN;
        vista.bloquearTableros();
        if (jugadorGano) {
            vista.mostrarVictoria("¡FELICIDADES! ¡Has hundido toda la flota enemiga!");
        } else {
            vista.mostrarDerrota("¡Has perdido! Toda tu flota ha sido hundida.");
        }

    }
}
