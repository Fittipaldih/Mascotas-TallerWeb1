package com.tallerwebi.dominio.excepcion;

public class DonacionException extends Exception {

    public DonacionException(){
        super("La donacion no pudo ser publicada");
    }

    public DonacionException(String message) {
        super(message);
    }
}
