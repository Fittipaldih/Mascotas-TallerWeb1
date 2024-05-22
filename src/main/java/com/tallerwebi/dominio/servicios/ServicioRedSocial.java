package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.Publicacion;

import java.util.List;

public interface ServicioRedSocial {
    List<Publicacion> getTodasLasPublicaciones();
    List<Publicacion> getPublicacionesTipoBuscadoPORDuenio();
    List<Publicacion> getPublicacionesTipoBuscandoALDuenio();
    List<Publicacion> getPublicacionesTipoDonacion();
    List<Publicacion> getPublicacionesTipoHistoria();
}
