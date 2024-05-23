package com.tallerwebi.dominio.repositorioInterfaces;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.PerdidoException;

import java.util.List;

public interface RepositorioPublicacion {
    void guardarHistoria(PublicacionHistoria historia);

    void guardarPerdido(PublicacionPerdido perdido);

    void guardarDonacion(PublicacionDonacion donacion);

    List<Publicacion> getPublicaciones();

    List<Publicacion> getPublicacionesPorTipoPublicacion(PublicacionTipo tipo);

    List<Publicacion> buscarPublicacionesPorZona(Zona zona);

}
