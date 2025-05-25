package com.javier.vista;

import com.javier.controlador.GestorJuego;
import com.javier.modelo.Coordenada;
import com.javier.modelo.Estado;
import com.javier.modelo.Tablero;

import javax.swing.*;
import java.awt.*;

public class VentanaJuego extends JFrame {

    private static final Color COLOR_AGUA = new Color(173, 216, 230);

    private JPanel panelJugador;
    private JPanel panelEnemigo;
    private JButton[][] botonesJugador;
    private JButton[][] botonesEnemigo;
    private GestorJuego controlador;
    private JLabel labelEstado;
    private JPanel panelMensajes;
    private boolean orientacionHorizontal = true; // true=horizontal, false=vertical
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

    /**
     *
     * @param panel Panel donde se añaden los botones
     * @param habilitar Si los botones deben empezar habilitados
     * @param esJugador Si es el tablero del jugador (true) o del enemigo (false)
     * @return matriz de botones
     */
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
     * Actualiza la vista del tablero enemigo, coloreando los botones según el estado del modelo.
     */
    public void actualizarVistaTableroEnemigo(Tablero tableroEnemigo) {
        for (int fila = 0; fila < Config.FILAS_TABLERO; fila++) {
            for (int col = 0; col < Config.COLUMNAS_TABLERO; col++) {
                Estado estado = tableroEnemigo.getCelda(fila, col).getEstado();
                // para ocultar los barcos enemigos, solo se muestra el estado de agua, tocado o hundido
                Color color = (estado == Estado.BARCO ? COLOR_AGUA : colorPorEstado(estado));
                botonesEnemigo[fila][col].setBackground(color);
            }
        }
    }

    /**
     * Actualiza la vista del tablero del jugador.
     */
    public void actualizarVistaTableroJugador(Tablero tableroJugador) {
        for (int fila = 0; fila < Config.FILAS_TABLERO; fila++) {
            for (int col = 0; col < Config.COLUMNAS_TABLERO; col++) {
                Estado estado = tableroJugador.getCelda(fila, col).getEstado();
                botonesJugador[fila][col].setBackground(colorPorEstado(estado));
                if (estado == Estado.BARCO) {
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
     * Cambia el modo de interacción del tablero del jugador.
     * Si modoColocacion=true: se habilita el tablero del jugador (para colocar barcos).
     * Si modoColocacion=false: se deshabilita el tablero del jugador.
     */
    public void setModoColocacion(boolean modoColocacion) {
        for (int fila = 0; fila < Config.FILAS_TABLERO; fila++) {
            for (int col = 0; col < Config.COLUMNAS_TABLERO; col++) {
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
        for (int fila = 0; fila < Config.FILAS_TABLERO; fila++) {
            for (int col = 0; col < Config.COLUMNAS_TABLERO; col++) {
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
            case BARCO -> new Color(60, 60, 60);
            default -> COLOR_AGUA;
        };
    }

    /**
     * Muestra un mensaje en la interfaz.
     */
    public void mostrarMensaje(String mensaje) {
        labelEstado.setText(mensaje);
    }

    /**
     * Muestra un mensaje de error.
     */
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Muestra un mensaje de victoria al finalizar la partida.
     */
    public void mostrarVictoria(String mensaje) {
        labelEstado.setText(mensaje);
        labelEstado.setForeground(Color.GREEN.darker());
        JOptionPane.showMessageDialog(this, mensaje, "¡Victoria!", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra un mensaje de derrota al finalizar la partida.
     */
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
