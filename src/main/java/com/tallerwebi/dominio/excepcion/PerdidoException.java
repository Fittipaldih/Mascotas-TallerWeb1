package com.tallerwebi.dominio.excepcion;

public class PerdidoException extends Exception {
    public PerdidoException(String errorAlPublicar) {
        super("La mascota Perdidida NO pudo registrarse");
    }
}
