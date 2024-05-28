package com.tallerwebi.dominio.excepcion;

public class PublicacionInexistenteExeption extends Exception {
    public PublicacionInexistenteExeption() {
        super("El publicacion no existe");
    }
}
