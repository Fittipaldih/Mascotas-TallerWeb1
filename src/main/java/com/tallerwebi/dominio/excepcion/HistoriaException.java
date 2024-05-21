package com.tallerwebi.dominio.excepcion;

public class HistoriaException extends Exception {

    public HistoriaException(){
        super("La historia no pudo ser publicada");
    }

    public HistoriaException(String message) {
        super(message);
    }
}
