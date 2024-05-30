package com.tallerwebi.dominio.servicios.interfaces;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.PublicacionTipo;
import com.tallerwebi.dominio.Zona;

import java.util.List;

public interface ServicioRedSocial {
    Publicacion getPublicacionPorId(Long idPublicacion);

    List<Publicacion> getTodasLasPublicaciones();

    List<Publicacion> getPublicacionesTipoBuscadoPORDuenio();

    List<Publicacion> getPublicacionesTipoBuscandoALDuenio();

    List<Publicacion> getPublicacionesTipoDonacion();

    List<Publicacion> getPublicacionesTipoHistoria();

    List<Publicacion> getPublicacionesSegunFiltros(Zona zona, String nombre);

    String getSeccionEditar(PublicacionTipo publicacionTipo);
}
