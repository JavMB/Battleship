//package com.javier.modelo;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import java.util.Set;
//
//public class EstrategiaTocaryHundir implements Estrategia{
//    private final Random random = new Random();
//
//    @Override
//    public Coordenada disparar(Tablero enemigo, Set<Coordenada> yaDisparadas) {
//        Celda[][] celdas = enemigo.getCeldas();
//        int filas = celdas.length;
//        int columnas = celdas[0].length;
//
//        List<Coordenada> objetivos = new ArrayList<>();
//
//        for (int i = 0; i < filas; i++) {
//            for (int j = 0; j < columnas; j++) {
//                Coordenada cord = new Coordenada(i, j);
//                Celda celda = celdas[i][j];
//                if (celda.getEstado() == (Estado.TOCADO) && celda instanceof CeldaBarco){
//                    objetivos.add(cord);
//                }
//            }
//        }
//
//        if (!objetivos.isEmpty()){
//            for (Coordenada value : objetivos){
//                List<Coordenada> adyacentes = buscarExtensionesEnLinea(value, celdas);
//                for (Coordenada vecina : adyacentes){
//                    Celda celdaVecina = celdas[vecina.x()][vecina.y()];
//                    if (celdaVecina.getEstado() == Estado.NADA){
//                        return vecina;
//                    }
//                }
//            }
//        }
//
//        Coordenada disparo;
//        do {
//            int x = random.nextInt(filas);
//            int y = random.nextInt(columnas);
//            disparo = new Coordenada(x, y);
//        }while (yaDisparadas.contains(disparo));
//
//        return disparo;
//    }
//
//    /**
//     * Metodo que hace la CPU busque lugares cerca de donde ha tocado un barco para que no sea totalmente aleatorio
//     * @param inicio La coordenada que ha sido tocada
//     * @param celdas La matriz donde se van a buscar barcos
//     * @return Un ArrayList con las coordenadas adyacentes posibles como buen disparo
//     */
//    private List<Coordenada> buscarExtensionesEnLinea(Coordenada inicio, Celda[][] celdas){
//        List<Coordenada> horizontal = new ArrayList<>();
//        horizontal.addAll(buscarEnDireccion(inicio, -1, 0, celdas));
//        horizontal.addAll(buscarEnDireccion(inicio, 1, 0, celdas));
//        if (!horizontal.isEmpty()){
//            return horizontal;
//        }
//
//        List<Coordenada> vertical = new ArrayList<>();
//        vertical.addAll(buscarEnDireccion(inicio, 0, -1, celdas));
//        vertical.addAll(buscarEnDireccion(inicio, 0, 1, celdas));
//        return vertical;
//    }
//
//    /**
//     * Metodo que busca en diferentes direcciones a una coordenada dada que ya ha tocado
//     * @param inicio La coordenada tocada
//     * @param dx La dirección en la que se va a buscar en el eje x
//     * @param dy La dirección en la que se va a buscar en el eje y
//     * @param celdas La matriz donde se van a buscar los barcos
//     * @return Un ArrayList con las posibles coordenadas a disparar cerca de la celda ya disparada y tocada
//     */
//    private List<Coordenada> buscarEnDireccion(Coordenada inicio, int dx, int dy, Celda[][] celdas){
//        List<Coordenada> resultado = new ArrayList<>();
//        int x = inicio.x() + dx;
//        int y = inicio.y() + dy;
//
//        while (x >= 0 && x < celdas.length && y >= 0 && y < celdas[0].length){
//            Estado estado = celdas[x][y].getEstado();
//            if (estado == Estado.TOCADO || estado == Estado.HUNDIDO){
//                x += dx;
//                y += dy;
//                continue;
//            } else if (estado == Estado.NADA) {
//                resultado.add(new Coordenada(x, y));
//            }
//            break;
//        }
//        return resultado;
//    }
//}
