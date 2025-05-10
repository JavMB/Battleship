package com.javier.vista;

import javax.swing.*;
import java.awt.*;

public class VentanaJuego extends JFrame {

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


        this.add(panelPrincipal);
    }


}