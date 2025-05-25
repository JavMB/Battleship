package com.javier.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class EstrategiaTocaryHundir implements Estrategia {
    private final Random random = new Random();

    @Override
    public Coordenada disparar(Tablero enemigo, Set<Coordenada> yaDisparadas) {
        Celda[][] celdas = enemigo.getCeldas();
        int filas = celdas.length;
        int columnas = celdas[0].length;
        List<Coordenada> objetivos = new ArrayList<>();


        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Coordenada cord = new Coordenada(j, i); // x=j, y=i
                Celda celda = celdas[i][j];


                if (celda instanceof CeldaBarco && celda.isTocada()) {
                    CeldaBarco celdaBarco = (CeldaBarco) celda;
                    if (!celdaBarco.getBarco().isHundido()) {
                        objetivos.add(cord);
                    }
                }
            }
        }


        if (!objetivos.isEmpty()) {
            for (Coordenada objetivo : objetivos) {
                List<Coordenada> adyacentes = buscarExtensionesEnLinea(objetivo, celdas);
                for (Coordenada vecina : adyacentes) {

                    if (!yaDisparadas.contains(vecina)) {
                        Celda celdaVecina = celdas[vecina.y()][vecina.x()];

                        if (!celdaVecina.isTocada()) {
                            return vecina;
                        }
                    }
                }
            }
        }


        Coordenada disparo;
        do {
            int x = random.nextInt(columnas);
            int y = random.nextInt(filas);
            disparo = new Coordenada(x, y);
        } while (yaDisparadas.contains(disparo));

        return disparo;
    }

    /**
     * Método que hace que la CPU busque lugares cerca de donde ha tocado un barco
     * @param inicio La coordenada que ha sido tocada
     * @param celdas La matriz donde se van a buscar barcos
     * @return Un ArrayList con las coordenadas adyacentes posibles como buen disparo
     */
    private List<Coordenada> buscarExtensionesEnLinea(Coordenada inicio, Celda[][] celdas) {

        List<Coordenada> horizontal = new ArrayList<>();
        horizontal.addAll(buscarEnDireccion(inicio, -1, 0, celdas)); // izquierda
        horizontal.addAll(buscarEnDireccion(inicio, 1, 0, celdas));  // derecha

        if (!horizontal.isEmpty()) {
            return horizontal;
        }


        List<Coordenada> vertical = new ArrayList<>();
        vertical.addAll(buscarEnDireccion(inicio, 0, -1, celdas)); // arriba
        vertical.addAll(buscarEnDireccion(inicio, 0, 1, celdas));  // abajo

        return vertical;
    }

    /**
     * Método que busca en diferentes direcciones a una coordenada dada que ya ha tocado
     * @param inicio La coordenada tocada
     * @param dx La dirección en la que se va a buscar en el eje x
     * @param dy La dirección en la que se va a buscar en el eje y
     * @param celdas La matriz donde se van a buscar los barcos
     * @return Un ArrayList con las posibles coordenadas a disparar cerca de la celda ya disparada y tocada
     */
    private List<Coordenada> buscarEnDireccion(Coordenada inicio, int dx, int dy, Celda[][] celdas) {
        List<Coordenada> resultado = new ArrayList<>();
        int x = inicio.x() + dx;
        int y = inicio.y() + dy;

        while (x >= 0 && x < celdas[0].length && y >= 0 && y < celdas.length) {
            Celda celda = celdas[y][x];


            if (celda instanceof CeldaBarco && celda.isTocada()) {
                x += dx;
                y += dy;
                continue;
            }

            else if (!celda.isTocada()) {
                resultado.add(new Coordenada(x, y));
            }
            break;
        }

        return resultado;
    }
}
