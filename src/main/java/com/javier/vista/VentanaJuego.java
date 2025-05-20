package com.javier.vista;

import com.javier.controlador.GestorJuego;
import com.javier.modelo.Coordenada;
import com.javier.modelo.Estado;
import com.javier.modelo.Tablero;

import javax.swing.*;
import java.awt.*;

public class VentanaJuego extends JFrame {

    private static final int TAMANO_TABLERO = 10;
    private static final Color COLOR_AGUA = new Color(173, 216, 230);

    private JPanel panelJugador;
    private JPanel panelEnemigo;
    private JButton[][] botonesJugador;
    private JButton[][] botonesEnemigo;
    private GestorJuego controlador;

    public VentanaJuego() {
        super("BattleShip - Partida");
        configurarVentana();
        inicializarComponentes();
        this.setVisible(true);
    }

    public void setControlador(GestorJuego controlador) {
        this.controlador = controlador;
    }

    private void configurarVentana() {
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JPanel panelTableros = new JPanel(new GridLayout(1, 2, 20, 0));

        panelJugador = crearPanelTablero("Tu flota");
        panelEnemigo = crearPanelTablero("Enemigo");

        botonesJugador = crearBotonera(panelJugador, false, true);
        botonesEnemigo = crearBotonera(panelEnemigo, false, false);

        panelTableros.add(panelJugador);
        panelTableros.add(panelEnemigo);
        panelPrincipal.add(panelTableros, BorderLayout.CENTER);
        this.add(panelPrincipal);
    }

    private JPanel crearPanelTablero(String titulo) {
        JPanel panel = new JPanel(new GridLayout(TAMANO_TABLERO, TAMANO_TABLERO));
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
        return panel;
    }

    /**
     *
     * @param panel Panel donde se añaden los botones
     * @param habilitar Si los botones deben empezar habilitados
     * @param esJugador Si es el tablero del jugador (true) o del enemigo (false)
     * @return matriz de botones
     */
    private JButton[][] crearBotonera(JPanel panel, boolean habilitar, boolean esJugador) {
        JButton[][] botones = new JButton[TAMANO_TABLERO][TAMANO_TABLERO];
        for (int fila = 0; fila < TAMANO_TABLERO; fila++) {
            for (int col = 0; col < TAMANO_TABLERO; col++) {
                JButton boton = new JButton();
                boton.setBackground(COLOR_AGUA);
                boton.setPreferredSize(new Dimension(30, 30));
                boton.setEnabled(habilitar);

                final int f = fila;
                final int c = col;
                if (esJugador) {
                    boton.addActionListener(e -> {
                        if (controlador != null) {
                            controlador.manejarClickJugador(f, c);
                        }
                    });
                } else {
                    boton.addActionListener(e -> {
                        if (controlador != null) {
                            controlador.manejarClickEnemigo(f, c);
                        }
                    });
                }

                botones[fila][col] = boton;
                panel.add(boton);
            }
        }
        return botones;
    }

    /**
     * Actualiza la vista del tablero enemigo, coloreando los botones según el estado del modelo.
     */
    public void actualizarVistaTableroEnemigo(Tablero tableroEnemigo) {
        for (int fila = 0; fila < TAMANO_TABLERO; fila++) {
            for (int col = 0; col < TAMANO_TABLERO; col++) {
                Estado estado = tableroEnemigo.getCelda(fila, col).getEstado();
                botonesEnemigo[fila][col].setBackground(colorPorEstado(estado));
            }
        }
    }

    /**
     * Actualiza la vista del tablero del jugador.
     */
    public void actualizarVistaTableroJugador(Tablero tableroJugador) {
        for (int fila = 0; fila < TAMANO_TABLERO; fila++) {
            for (int col = 0; col < TAMANO_TABLERO; col++) {
                Estado estado = tableroJugador.getCelda(fila, col).getEstado();
                botonesJugador[fila][col].setBackground(colorPorEstado(estado));
            }
        }
    }

    /**
     * Cambia el modo de interacción del tablero del jugador.
     * Si modoColocacion=true: se habilita el tablero del jugador (para colocar barcos).
     * Si modoColocacion=false: se deshabilita el tablero del jugador.
     */
    public void setModoColocacion(boolean modoColocacion) {
        for (int fila = 0; fila < TAMANO_TABLERO; fila++) {
            for (int col = 0; col < TAMANO_TABLERO; col++) {
                botonesJugador[fila][col].setEnabled(modoColocacion);
            }
        }
    }

    /**
     * Cambia el modo de interacción del tablero enemigo.
     * Si modoDisparo=true: se habilita el tablero enemigo (para disparar).
     * Si modoDisparo=false: se deshabilita el tablero enemigo.
     */
    public void setModoDisparo(boolean modoDisparo) {
        for (int fila = 0; fila < TAMANO_TABLERO; fila++) {
            for (int col = 0; col < TAMANO_TABLERO; col++) {
                botonesEnemigo[fila][col].setEnabled(modoDisparo);
            }
        }
    }

    /**
     * Bloquea todos los tableros (fin del juego).
     */
    public void bloquearTableros() {
        setModoColocacion(false);
        setModoDisparo(false);
    }

    /**
     * Personaliza los colores según el estado de la celda.
     */
    private Color colorPorEstado(Estado estado) {
        return switch (estado) {
            case TOCADO -> Color.ORANGE;
            case HUNDIDO -> Color.RED;
            case BARCO -> Color.GRAY;
            default -> COLOR_AGUA;
        };
    }
}