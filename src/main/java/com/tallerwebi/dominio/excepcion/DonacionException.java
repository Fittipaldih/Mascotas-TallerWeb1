package com.tallerwebi.dominio.excepcion;

public class DonacionException extends Exception {
    public DonacionException() {
        super("La Donacion no pudo ser publicada");
    }
}
