package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.PerdidoExeption;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository("repositorioPublicaciones")

public class RepositorioPublicacionesImp implements RepositorioPublicaciones {

    private List<Publicacion> publicaciones;
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioPublicacionesImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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

    @Override
    public void guardarPerdido(Perdido perdido) throws PerdidoExeption {
        Boolean seGuardo = (Boolean) this.sessionFactory.getCurrentSession().save(perdido);
        if (seGuardo == false) {
            throw new PerdidoExeption();
        }
    }
}
