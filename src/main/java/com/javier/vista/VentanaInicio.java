package com.javier.vista;

import javax.swing.*;
import java.awt.*;

public class VentanaInicio extends JFrame {

    public VentanaInicio() {
        super("BattleShip");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        initComponents();
        this.setVisible(true);
    }

    private void initComponents() {

        JPanel panelFondo = new ImagenFondo();
        panelFondo.setLayout(new BorderLayout());


        JPanel panelTitulo = new JPanel();
        panelTitulo.setOpaque(false);


        JLabel lblTitulo = new JLabel("BATTLESHIP");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 48));
        lblTitulo.setForeground(Color.WHITE);
        panelTitulo.add(lblTitulo);


        panelFondo.add(panelTitulo, BorderLayout.NORTH);


        this.add(panelFondo);
    }


    class ImagenFondo extends JPanel {
        private Image imagen;

        public ImagenFondo() {
            try {
                imagen = new ImageIcon("/home/javi/DAM/PROG/Battleship/src/main/resources/battleship.jpg").getImage();
            } catch (Exception e) {
                System.out.println("Error al cargar la imagen");
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (imagen != null) {
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            } else {
                setBackground(Color.DARK_GRAY);
            }
        }
    }


}