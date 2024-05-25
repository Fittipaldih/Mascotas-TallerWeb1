package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.Publicacion;

public interface ServicioDetallePublicacion {
    Publicacion getPublicacion(Long id) throws Exception;
}
