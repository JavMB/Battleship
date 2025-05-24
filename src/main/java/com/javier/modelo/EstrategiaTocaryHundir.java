package com.javier.modelo;

import java.util.*;

public class EstrategiaTocaryHundir implements Estrategia{
    private final Random random = new Random();

    @Override
    public Coordenada disparar(Tablero enemigo, Set<Coordenada> yaDisparadas) {
        Celda[][] celdas = enemigo.getCeldas();
        int filas = celdas.length;
        int columnas = celdas[0].length;

        List<Coordenada> objetivos = new ArrayList<>();

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Coordenada cord = new Coordenada(i, j);
                Celda celda = celdas[i][j];
                if (celda.getEstado().equals(Estado.TOCADO) && celda instanceof CeldaBarco){
                    objetivos.add(cord);
                }
            }
        }

        if (!objetivos.isEmpty()){
            for (Coordenada value : objetivos){
                List<Coordenada> adyacentes = buscarExtensionesEnLinea(value, celdas);
                for (Coordenada vecina : adyacentes){
                    Celda celdaVecina = celdas[vecina.x()][vecina.y()];
                    if (celdaVecina.getEstado() == Estado.NADA){
                        return vecina;
                    }
                }
            }
        }

        Coordenada disparo;
        do {
            int x = random.nextInt(filas);
            int y = random.nextInt(columnas);
            disparo = new Coordenada(x, y);
        }while (yaDisparadas.contains(disparo));

        return disparo;
    }

    private List<Coordenada> buscarExtensionesEnLinea(Coordenada inicio, Celda[][] celdas){
        int filas = celdas.length;
        int columnas = celdas[0].length;
        List<Coordenada> objetivos = new ArrayList<>();
        List<Coordenada> horizontal = new ArrayList<>();
        horizontal.addAll(buscarEnDireccion(inicio, -1, 0, celdas));
        horizontal.addAll(buscarEnDireccion(inicio, 1, 0, celdas));
        if (!horizontal.isEmpty()){
            return horizontal;
        }

        List<Coordenada> vertical = new ArrayList<>();
        vertical.addAll(buscarEnDireccion(inicio, 0, -1, celdas));
        vertical.addAll(buscarEnDireccion(inicio, 0, 1, celdas));
        return vertical;
    }

    private List<Coordenada> buscarEnDireccion(Coordenada inicio, int dx, int dy, Celda[][] celdas){
        List<Coordenada> resultado = new ArrayList<>();
        int x = inicio.x() + dx;
        int y = inicio.y() + dy;

        while (x >= 0 && x < celdas.length && y >= 0 && y < celdas[0].length){
            Estado estado = celdas[x][y].getEstado();
            if (estado == Estado.TOCADO || estado == Estado.HUNDIDO){
                x += dx;
                y += dy;
                continue;
            } else if (estado == Estado.NADA) {
                resultado.add(new Coordenada(x, y));
            }
            break;
        }
        return resultado;
    }
}
