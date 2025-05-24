package com.javier.modelo;





public class Barco {
    private Barcos tipo;
    private int impactosRecibidos;


    public Barco(Barcos tipo) {
        this.tipo = tipo;
        this.impactosRecibidos = 0;
    }



    public int getLongitud() {
        return this.tipo.getLongitud();
    }

    public Barcos getTipo() {
        return tipo;
    }

    public void registrarImpacto() {
        impactosRecibidos++;
    }

    public boolean isHundido() {
        return impactosRecibidos >= getLongitud();
    }

}
