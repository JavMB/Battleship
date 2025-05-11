package com.javier.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaInicio extends JFrame {

    public VentanaInicio() {
        super("BattleShip");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        initComponents();
        // no llamamos a setVisible aquí, lo haremos desde el Main
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


        JPanel panelBotones = new JPanel();
        panelBotones.setOpaque(false);
        panelBotones.setLayout(new GridLayout(2, 1, 0, 10));


        JButton btnJugar = new JButton("INICIAR PARTIDA");
        btnJugar.setFont(new Font("Arial", Font.BOLD, 18));
        btnJugar.setBackground(new Color(0, 100, 200));
        btnJugar.setForeground(Color.WHITE);
        btnJugar.addActionListener(e -> iniciarJuego());


        JButton btnSalir = new JButton("SALIR");
        btnSalir.setFont(new Font("Arial", Font.BOLD, 18));
        btnSalir.setBackground(new Color(200, 50, 50));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.addActionListener(e -> System.exit(0));


        panelBotones.add(btnJugar);
        panelBotones.add(btnSalir);


        JPanel contenedorBotones = new JPanel();
        contenedorBotones.setOpaque(false);
        contenedorBotones.add(panelBotones);


        panelFondo.add(panelTitulo, BorderLayout.NORTH);
        panelFondo.add(contenedorBotones, BorderLayout.CENTER);


        this.add(panelFondo);
    }

    private void iniciarJuego() {
        this.setVisible(false);
        VentanaJuego ventanaJuego = new VentanaJuego();
        ventanaJuego.setVisible(true);

        this.dispose();
    }


    static class ImagenFondo extends JPanel {
        private Image imagen;

        public ImagenFondo() {
            try {
                imagen = new ImageIcon(getClass().getResource("/assets/battleship.jpg")).getImage();
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