package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioRedSocial {

    public List<Publicacion> filtrarPublicacion(TipoPublicacion tipoPublicacion, Zona zona, ColorPelo pelo, TiempoBusqueda tiempoBusqueda);
}
