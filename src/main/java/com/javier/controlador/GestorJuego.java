package com.javier.controlador;

import com.javier.modelo.*;
import com.javier.vista.VentanaJuego;


public class GestorJuego {
    private Player jugador;
    private Cpu cpu;
    private VentanaJuego vista;
    private Modo modoActual = Modo.COLOCACION;
    private TurnoManager turnos = new TurnoManager();
    private enum Modo { COLOCACION, DISPARO, FIN }




    public GestorJuego(VentanaJuego vista) {
        this.vista = vista;
        this.jugador = new Player();
        this.cpu = new Cpu();
        this.vista.setControlador(this);

        this.vista.actualizarVistaTableroJugador(jugador.getTableroPropio());
        this.vista.actualizarVistaTableroEnemigo(cpu.getTableroPropio());

        vista.setModoColocacion(true);
        vista.setModoDisparo(false);
    }

    //  cuando el usuario hace click en el tablero del jugador para colocar barcos
    public void manejarClickJugador(int fila, int col) {
        //TODO en modo colocar hare

    }

    // cuando el usuario hace click en el tablero enemigo para disparar
    public void manejarClickEnemigo(int fila, int col) {

    }

    private void realizarTurnoCpu() {
        // Ddsparo de la CPU
        turnos.siguienteTurno();
    }


    public void manejarInputTexto(Coordenada cord){}
    //Josep esto habra un input text y un boton con un listener asociado que al darla activa este evento, que basicamente
    //es lo mismo que manejarclickjugador , depende del estado si es inicio o disparo pues esa cordenada intentara poner un barco, o disparar a un barco enemigo
}