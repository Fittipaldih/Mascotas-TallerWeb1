package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.PerdidoExeption;

import java.util.List;

public interface RepositorioPublicaciones {
    List<Publicacion> getPublicaciones();
    void guardarPerdido(Perdido perdido) throws PerdidoExeption;
}
