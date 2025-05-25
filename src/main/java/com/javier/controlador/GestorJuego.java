package com.javier.controlador;

import com.javier.audio.ReproductorEfectos;
import com.javier.audio.ReproductorMusica;
import com.javier.modelo.*;
import com.javier.vista.Config;
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

   private final static Barcos[] FLOTA_A_COLOCAR = {
            Barcos.PORTAAVIONES,
            Barcos.ACORAZADO,
            Barcos.CRUCERO,
            Barcos.CRUCERO,
            Barcos.SUBMARINO,
            Barcos.DESTRUCTOR,
            Barcos.DESTRUCTOR
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

        // Verificar límites del tablero
        if (esHorizontal) {
            if (col + longitud > Config.COLUMNAS_TABLERO) {
                vista.mostrarError("El " + tipoBarcoActual.name() + " se sale del tablero (horizontal).");
                return;
            }
        } else {
            if (fila + longitud > Config.FILAS_TABLERO) {
                vista.mostrarError("El " + tipoBarcoActual.name() + " se sale del tablero (vertical).");
                return;
            }
        }

        // Verificar solapamiento
        List<Coordenada> coordenadasPotenciales = new ArrayList<>();
        for (int i = 0; i < longitud; i++) {
            int cFila = esHorizontal ? fila : fila + i;
            int cCol = esHorizontal ? col + i : col;

            if (cFila < 0 || cFila >= Config.FILAS_TABLERO || cCol < 0 || cCol >= Config.COLUMNAS_TABLERO) {
                vista.mostrarError("Coordenada fuera de tablero al verificar solapamiento.");
                return;
            }

            if (jugador.getTableroPropio().getCelda(cFila, cCol) instanceof CeldaBarco) {
                vista.mostrarError("No puedes solapar el " + tipoBarcoActual.name() + " con otro barco.");
                return;
            }

            coordenadasPotenciales.add(new Coordenada(cCol, cFila));
        }

        // Colocar el barco
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

            // Verificar si ya fue tocada
            if (celdaObjetivoCPU.isTocada()) {
                vista.mostrarError("Ya has disparado en (" + col + "," + fila + ")");
                return;
            }

            boolean impacto = celdaObjetivoCPU.procesarDisparo();
            vista.actualizarVistaTableroEnemigo(cpu.getTableroPropio());

            if (!impacto) {
                // Es agua
                ReproductorEfectos.getInstance().agua();
                vista.mostrarMensaje("¡Agua! Disparaste en (" + col + "," + fila + ")");
            } else {
                // Es un barco
                CeldaBarco celdaBarco = (CeldaBarco) celdaObjetivoCPU;
                if (celdaBarco.getBarco().isHundido()) {
                    ReproductorEfectos.getInstance().hundir();
                    vista.mostrarMensaje("¡HUNDIDO! Has hundido un barco enemigo en (" + col + "," + fila + ")");
                } else {
                    ReproductorEfectos.getInstance().tocado();
                    vista.mostrarMensaje("¡Tocado! Impacto en (" + col + "," + fila + ")");
                }
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

            boolean impactoCpu = celdaObjetivoJugador.procesarDisparo();
            vista.actualizarVistaTableroJugador(jugador.getTableroPropio());

            String mensajeCpu = "CPU disparó en (" + coordDisparoCpu.x() + "," + coordDisparoCpu.y() + "): ";

            if (!impactoCpu) {
                mensajeCpu += "¡Agua!";
            } else {
                CeldaBarco celdaBarco = (CeldaBarco) celdaObjetivoJugador;
                if (celdaBarco.getBarco().isHundido()) {
                    mensajeCpu += "¡HUNDIDO! Tu barco ha sido hundido.";
                    cpu.setStrategy(estrategiaAleatoria);
                } else {
                    mensajeCpu += "¡Tocado!";
                    cpu.setStrategy(estrategiaCaza);
                }
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
        return false;
    }

    private void finalizarPartida(boolean jugadorGano) {
        modoActual = Modo.FIN;
        vista.bloquearTableros();
        ReproductorMusica.getInstancia().stop();

        if (jugadorGano) {
            ReproductorEfectos.getInstance().win();
            vista.mostrarVictoria("¡FELICIDADES! ¡Has hundido toda la flota enemiga!");
        } else {
            ReproductorEfectos.getInstance().lose();
            vista.mostrarDerrota("¡Has perdido! Toda tu flota ha sido hundida.");
        }
    }
}
