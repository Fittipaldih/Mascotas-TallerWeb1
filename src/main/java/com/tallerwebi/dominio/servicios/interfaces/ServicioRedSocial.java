package com.tallerwebi.dominio.servicios.interfaces;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.PublicacionTipo;

import java.util.List;

public interface ServicioRedSocial {
    List<Publicacion> getTodasLasPublicaciones();
    List<Publicacion> getPublicacionesTipoBuscadoPORDuenio();
    List<Publicacion> getPublicacionesTipoBuscandoALDuenio();

    List<Publicacion> getPublicacionesTipoPerdido();

    List<Publicacion> getPublicacionesTipoDonacion();
    List<Publicacion> getPublicacionesTipoHistoria();
    String getSeccionEditar(PublicacionTipo publicacionTipo);
    Publicacion getPublicacionPorId(Long idPublicacion);

    List<Publicacion> obtenerUltimasPublicaciones(int cantidad);
}
