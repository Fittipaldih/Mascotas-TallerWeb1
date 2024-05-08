package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.*;

import java.util.ArrayList;
import java.util.List;

public class RepositorioPublicacionesImp implements RepositorioPublicaciones {

    private List<Publicacion> publicaciones;
    public RepositorioPublicacionesImp() {
        this.publicaciones = new ArrayList<Publicacion>();
        publicaciones.add(new Publicacion(TipoPublicacion.PERDIDOS, Zona.OESTE, TiempoBusqueda.HORA,ColorPelo.MARRON, "Se perdio"));
        publicaciones.add(new Publicacion(TipoPublicacion.DONACIONES, Zona.NORTE, TiempoBusqueda.HORA,ColorPelo.BLANCO, "Estamos recaudando"));
        publicaciones.add(new Publicacion(TipoPublicacion.HISTORIAS, Zona.OESTE, TiempoBusqueda.MASMES,ColorPelo.NEGRO, "Esta es la historia de Juan"));
        publicaciones.add(new Publicacion(TipoPublicacion.PERDIDOS, Zona.OESTE, TiempoBusqueda.HORA,ColorPelo.NEGRO, "Se perdio Manolito en Av.Martin Fierro"));
    }

    @Override
    public List<Publicacion> getPublicaciones() {
        return this.publicaciones;
    }
}
