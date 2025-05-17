package com.javier.controlador;

import com.javier.modelo.*;
import com.javier.vista.VentanaJuego;

import java.util.List;

public class GestorJuego {
    private Player jugador;
    private Cpu cpu;
    private VentanaJuego vista;

    public GestorJuego(VentanaJuego vista) {
        this.vista = vista;
        this.jugador = new Player();
        generarBarcosJugador();
        this.cpu = new Cpu();


        // Mostrar tableros
        this.vista.actualizarVistaTableroJugador(jugador.getTableroPropio());
        this.vista.actualizarVistaTableroEnemigo(cpu.getTableroPropio());
    }

    private void generarBarcosJugador() {
        for (Barco barco : jugador.getBarcos()) {
            jugador.nuevoBarco(barcoInteractivo());
        }
    }

    private List<Coordenada> barcoInteractivo() {






    }


}
