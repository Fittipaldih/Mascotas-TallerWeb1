package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository("repositorioPublicaciones")

public class RepositorioPublicacionesImp implements RepositorioPublicaciones {

    private List<Publicacion> publicaciones;

    public RepositorioPublicacionesImp() {
        this.publicaciones = new ArrayList<Publicacion>();
        publicaciones.add(new Publicacion(TipoPublicacion.PERDIDOS, Zona.OESTE,  "BLANCO", "Se perdio"));
        publicaciones.add(new Publicacion(TipoPublicacion.DONACIONES, Zona.NORTE,  "negro", "Estamos recaudando"));
        publicaciones.add(new Publicacion(TipoPublicacion.HISTORIAS, Zona.OESTE, "Marron", "Esta es la historia de Juan"));
        publicaciones.add(new Publicacion(TipoPublicacion.PERDIDOS, Zona.OESTE, "blanco", "Se perdio Manolito en Av.Martin Fierro"));
    }

    @Override
    public List<Publicacion> getPublicaciones() {
        return this.publicaciones;
    }
}
