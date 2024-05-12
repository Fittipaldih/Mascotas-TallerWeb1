package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.PerdidoExeption;

import java.util.List;

public interface RepositorioPublicacion {
    List<Publicacion> getPublicaciones();
    void guardarPerdido(Perdido perdido) throws PerdidoExeption;
    void guardarPublicacion(Publicacion publicacion);
    void eliminarPublicacion(Publicacion publicacion);
    Publicacion buscarPublicacion(Publicacion publicacion);
    List <Publicacion> buscarPublicacionesPorUsuario(Usuario usuario);
    List <Publicacion> buscarPublicacionesPorZona(Zona zona);
    List <Publicacion> buscarPublicacionesPorColorPelo(Color color);
    void modificarTelefonoPublicacion(Publicacion Publicacion);
}
