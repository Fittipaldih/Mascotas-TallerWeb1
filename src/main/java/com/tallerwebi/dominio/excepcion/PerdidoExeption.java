package com.tallerwebi.dominio.excepcion;

public class PerdidoExeption extends Exception {
    public PerdidoExeption() {
        super("La mascota Perdidida NO pudo registrarse");
    }
}
