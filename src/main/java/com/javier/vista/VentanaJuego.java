package com.javier.vista;

import com.javier.controlador.GestorJuego;
import com.javier.modelo.Celda;
import com.javier.modelo.CeldaBarco;
import com.javier.modelo.Tablero;

import javax.swing.*;
import java.awt.*;

public class VentanaJuego extends JFrame {
    private static final Color COLOR_AGUA = new Color(173, 216, 230);
    private static final Color COLOR_BARCO = new Color(60, 60, 60);
    private static final Color COLOR_TOCADO = Color.ORANGE;
    private static final Color COLOR_HUNDIDO = Color.RED;
    private static final Color COLOR_AGUA_TOCADA = new Color(100, 150, 200);

    private JPanel panelJugador;
    private JPanel panelEnemigo;
    private JButton[][] botonesJugador;
    private JButton[][] botonesEnemigo;
    private GestorJuego controlador;
    private JLabel labelEstado;
    private JPanel panelMensajes;
    private boolean orientacionHorizontal = true;
    private JButton btnOrientacion;

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

        // Panel de mensajes
        panelMensajes = new JPanel();
        labelEstado = new JLabel("Coloca tus barcos");
        labelEstado.setFont(new Font("Arial", Font.BOLD, 14));
        panelMensajes.add(labelEstado);

        // Botón para cambiar orientación
        btnOrientacion = new JButton("Orientación: Horizontal");
        btnOrientacion.addActionListener(e -> alternarOrientacion());
        panelMensajes.add(btnOrientacion);

        panelJugador = crearPanelTablero("Tu flota");
        panelEnemigo = crearPanelTablero("Enemigo");

        botonesJugador = crearBotonera(panelJugador, true, true);
        botonesEnemigo = crearBotonera(panelEnemigo, false, false);

        panelTableros.add(panelJugador);
        panelTableros.add(panelEnemigo);

        panelPrincipal.add(panelTableros, BorderLayout.CENTER);
        panelPrincipal.add(panelMensajes, BorderLayout.SOUTH);

        this.add(panelPrincipal);
    }

    private JPanel crearPanelTablero(String titulo) {
        JPanel panel = new JPanel(new GridLayout(Config.FILAS_TABLERO, Config.COLUMNAS_TABLERO));
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
        return panel;
    }

    private JButton[][] crearBotonera(JPanel panel, boolean habilitar, boolean esJugador) {
        JButton[][] botones = new JButton[Config.FILAS_TABLERO][Config.COLUMNAS_TABLERO];
        for (int fila = 0; fila < Config.FILAS_TABLERO; fila++) {
            for (int col = 0; col < Config.COLUMNAS_TABLERO; col++) {
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
     * Actualiza la vista del tablero enemigo, ocultando los barcos no tocados
     */
    public void actualizarVistaTableroEnemigo(Tablero tableroEnemigo) {
        for (int fila = 0; fila < Config.FILAS_TABLERO; fila++) {
            for (int col = 0; col < Config.COLUMNAS_TABLERO; col++) {
                Celda celda = tableroEnemigo.getCelda(fila, col);
                Color color = obtenerColorCeldaEnemigo(celda);
                botonesEnemigo[fila][col].setBackground(color);
            }
        }
    }

    /**
     * Actualiza la vista del tablero del jugador, mostrando todos los barcos
     */
    public void actualizarVistaTableroJugador(Tablero tableroJugador) {
        for (int fila = 0; fila < Config.FILAS_TABLERO; fila++) {
            for (int col = 0; col < Config.COLUMNAS_TABLERO; col++) {
                Celda celda = tableroJugador.getCelda(fila, col);
                Color color = obtenerColorCeldaJugador(celda);
                botonesJugador[fila][col].setBackground(color);

                // Borde especial para barcos
                if (celda instanceof CeldaBarco) {
                    botonesJugador[fila][col].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
                } else {
                    botonesJugador[fila][col].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
                }
            }
        }
        panelJugador.revalidate();
        panelJugador.repaint();
    }

    /**
     * Obtiene el color para una celda del tablero enemigo
     */
    private Color obtenerColorCeldaEnemigo(Celda celda) {
        if (!celda.isTocada()) {
            return COLOR_AGUA; // No mostrar barcos enemigos no tocados
        }

        if (celda instanceof CeldaBarco) {
            CeldaBarco celdaBarco = (CeldaBarco) celda;
            if (celdaBarco.getBarco().isHundido()) {
                return COLOR_HUNDIDO;
            } else {
                return COLOR_TOCADO;
            }
        } else {
            return COLOR_AGUA_TOCADA; // Agua donde ya se disparó
        }
    }

    /**
     * Obtiene el color para una celda del tablero del jugador
     */
    private Color obtenerColorCeldaJugador(Celda celda) {
        if (celda instanceof CeldaBarco celdaBarco) {
            if (celda.isTocada()) {
                if (celdaBarco.getBarco().isHundido()) {
                    return COLOR_HUNDIDO;
                } else {
                    return COLOR_TOCADO;
                }
            } else {
                return COLOR_BARCO; // Barco no tocado
            }
        } else {
            if (celda.isTocada()) {
                return COLOR_AGUA_TOCADA; // Agua donde ya se disparó
            } else {
                return COLOR_AGUA; // Agua normal
            }
        }
    }

    public void setModoColocacion(boolean modoColocacion) {
        for (int fila = 0; fila < Config.FILAS_TABLERO; fila++) {
            for (int col = 0; col < Config.COLUMNAS_TABLERO; col++) {
                botonesJugador[fila][col].setEnabled(modoColocacion);
            }
        }
    }

    public void setModoDisparo(boolean modoDisparo) {
        for (int fila = 0; fila < Config.FILAS_TABLERO; fila++) {
            for (int col = 0; col < Config.COLUMNAS_TABLERO; col++) {
                botonesEnemigo[fila][col].setEnabled(modoDisparo);
            }
        }
    }

    public void bloquearTableros() {
        setModoColocacion(false);
        setModoDisparo(false);
    }

    public void mostrarMensaje(String mensaje) {
        labelEstado.setText(mensaje);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarVictoria(String mensaje) {
        labelEstado.setText(mensaje);
        labelEstado.setForeground(Color.GREEN.darker());
        JOptionPane.showMessageDialog(this, mensaje, "¡Victoria!", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarDerrota(String mensaje) {
        labelEstado.setText(mensaje);
        labelEstado.setForeground(Color.RED);
        JOptionPane.showMessageDialog(this, mensaje, "Derrota", JOptionPane.WARNING_MESSAGE);
    }

    private void alternarOrientacion() {
        orientacionHorizontal = !orientacionHorizontal;
        btnOrientacion.setText("Orientación: " + (orientacionHorizontal ? "Horizontal" : "Vertical"));
        mostrarMensaje("Coloca tu barco en orientación " + (orientacionHorizontal ? "horizontal" : "vertical"));
    }

    public boolean isOrientacionHorizontal() {
        return orientacionHorizontal;
    }
}
