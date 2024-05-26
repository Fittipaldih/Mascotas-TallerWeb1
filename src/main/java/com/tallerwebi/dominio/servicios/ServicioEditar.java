package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.PublicacionTipo;
import com.tallerwebi.dominio.Zona;

public interface ServicioEditar {

    public void editarHistoria(Long idPublicacion, String titular, String nombreMascota, Zona zona, String descripcion,byte[] imagenBytes);
}
