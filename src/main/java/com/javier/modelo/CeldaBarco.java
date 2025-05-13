package com.javier.modelo;
/**
 * Subclase de Celda que representa una celda donde hay un barco.
 *
 * Tareas:
 * - Heredar de Celda.
 * - Guardar si ya fue disparada o no.
 * - Mostrar símbolo o imagen de tocado o no tocado.
 *
 * Autor: Josep
 */


public class CeldaBarco extends Celda {
    private final Barco barco;

    public CeldaBarco(Barco barco) {

        this.barco = barco;
    }

    @Override
    public void disparar(int x, int y){
        super.disparar(x,y);
        barco.registrarImpacto(new Coordenada(x,y));
    }

    @Override
    public boolean tieneBarco() {
        return true;
    }


    public boolean estaUndido(){
        return this.barco.isHundido();
    }


    @Override
    public String toString() {
        return super.isDisparada() ? "1": "2";
    }


}
