package com.javier.modelo;

/**
 * Excepción personalizada para errores críticos del juego que impiden su continuación.
 */
public class ErrorCriticoException extends Exception {

    /**
     * Constructor para crear una excepción de error crítico con un mensaje detallado.
     *
     * @param mensaje El mensaje que describe el error crítico.
     */
    public ErrorCriticoException(String mensaje) {
        super(mensaje);
    }

    /**
     * Constructor para crear una excepción de error crítico con un mensaje y causa subyacente.
     *
     * @param mensaje El mensaje que describe el error crítico.
     * @param causa La causa subyacente de este error.
     */
    public ErrorCriticoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
