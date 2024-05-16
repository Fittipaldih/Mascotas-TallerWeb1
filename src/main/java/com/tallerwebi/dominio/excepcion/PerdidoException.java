package com.tallerwebi.dominio.excepcion;

public class PerdidoException extends Exception {
    public PerdidoException() {
        super("La mascota Perdidida NO pudo registrarse");
    }
}
