package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.PublicacionTipo;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface ServicioRedSocial {
    List<Publicacion> getTodasLasPublicaciones();
    List<Publicacion> getPublicacionesTipoBuscadoPORDuenio();
    List<Publicacion> getPublicacionesTipoBuscandoALDuenio();
    List<Publicacion> getPublicacionesTipoDonacion();
    List<Publicacion> getPublicacionesTipoHistoria();
    String getSeccionEditar(PublicacionTipo publicacionTipo);
    Publicacion getPublicacionPorId(Long idPublicacion);
}
