package com.javier.vista;

import javax.swing.*;
import java.awt.*;

public class VentanaJuego extends JFrame {

    private JPanel panelJugador;
    private JPanel panelEnemigo;
    private JButton[][] botonesJugador;
    private JButton[][] botonesEnemigo;

    public VentanaJuego() {
        super("BattleShip - Partida");
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        initComponents();
        this.setVisible(true);
    }

    public static void update() {

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


        botonesJugador = new JButton[10][10];
        botonesEnemigo = new JButton[10][10];


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                botonesJugador[i][j] = new JButton();
                botonesJugador[i][j].setBackground(new Color(173, 216, 230));
                botonesJugador[i][j].setPreferredSize(new Dimension(30, 30));
                panelJugador.add(botonesJugador[i][j]);
            }
        }


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                botonesEnemigo[i][j] = new JButton();
                botonesEnemigo[i][j].setBackground(new Color(173, 216, 230));
                botonesEnemigo[i][j].setPreferredSize(new Dimension(30, 30));
                panelEnemigo.add(botonesEnemigo[i][j]);
            }
        }


        panelTableros.add(panelJugador);
        panelTableros.add(panelEnemigo);


        panelPrincipal.add(panelTableros, BorderLayout.CENTER);


        this.add(panelPrincipal);
    }


    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);

        for (Component c : panelJugador.getComponents()) {
            c.paint(g);
        }


    }


}