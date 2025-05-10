package com.javier.vista;

import javax.swing.*;
import java.awt.*;

public class VentanaJuego extends JFrame {

    private JPanel panelJugador;
    private JPanel panelEnemigo;

    public VentanaJuego() {
        super("BattleShip - Partida");
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        initComponents();
        this.setVisible(true);
    }

    private void initComponents() {

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());


        JPanel panelTableros = new JPanel();
        panelTableros.setLayout(new GridLayout(1, 2, 20, 0));


        panelJugador = new JPanel();
        panelJugador.setBorder(BorderFactory.createTitledBorder("Tu flota"));
        panelJugador.setLayout(new GridLayout(10, 10));


        panelEnemigo = new JPanel();
        panelEnemigo.setBorder(BorderFactory.createTitledBorder("Enemigo"));
        panelEnemigo.setLayout(new GridLayout(10, 10));


        panelTableros.add(panelJugador);
        panelTableros.add(panelEnemigo);


        panelPrincipal.add(panelTableros, BorderLayout.CENTER);


        this.add(panelPrincipal);
    }


}