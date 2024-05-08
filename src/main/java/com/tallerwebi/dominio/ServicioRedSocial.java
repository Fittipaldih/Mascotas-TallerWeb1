package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioRedSocial {

    public List<Publicacion> filtrarPublicacion(TipoPublicacion tipoPublicacion, Zona zona, ColorPelo pelo, TiempoBusqueda tiempoBusqueda);
    public List<Publicacion> todasPublicaciones();
    public List<Publicacion> filtrarTipo(TipoPublicacion tipoPublicacion);
    public List<Publicacion> filtrarZona(Zona zona);
    public List<Publicacion> filtrarColor(ColorPelo pelo);
    public List<Publicacion> filtrarTiempoBusqueda(TiempoBusqueda tiempoBusqueda);
}
