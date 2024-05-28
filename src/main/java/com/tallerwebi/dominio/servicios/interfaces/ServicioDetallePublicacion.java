package com.tallerwebi.dominio.servicios.interfaces;

import com.tallerwebi.dominio.Publicacion;

public interface ServicioDetallePublicacion {
    Publicacion getPublicacion(Long id) throws Exception;

    void eliminarPublicacion(Long idPublicacion);
}
