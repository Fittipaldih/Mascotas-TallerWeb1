package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.PublicacionTipo;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioPublicacion;
import com.tallerwebi.dominio.servicios.interfaces.ServicioRedSocial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioRedSocialImpl implements ServicioRedSocial {
    @Autowired
    private RepositorioPublicacion repositorioPublicacion;

    public ServicioRedSocialImpl(RepositorioPublicacion repositorioPublicacion) {
        this.repositorioPublicacion = repositorioPublicacion;
    }

    @Override
    public List<Publicacion> getTodasLasPublicaciones() {
        return this.repositorioPublicacion.getPublicaciones();
    }

    @Override
    public List<Publicacion> getPublicacionesTipoBuscadoPORDuenio() {
        return this.repositorioPublicacion.getPublicacionesPorTipoPublicacion(PublicacionTipo.BUSCADO_POR_DUENIO);
    }

    @Override
    public List<Publicacion> getPublicacionesTipoBuscandoALDuenio() {
        return this.repositorioPublicacion.getPublicacionesPorTipoPublicacion(PublicacionTipo.BUSCANDO_AL_DUENIO);
    }

    @Override
    public List<Publicacion> getPublicacionesTipoPerdido() {
        List<Publicacion> publicacionesBuscadoPorDuenio = getPublicacionesTipoBuscadoPORDuenio();
        List<Publicacion> publicacionesBuscandoAlDuenio = getPublicacionesTipoBuscandoALDuenio();
        publicacionesBuscadoPorDuenio.addAll(publicacionesBuscandoAlDuenio);
        return publicacionesBuscadoPorDuenio;
    }

    @Override
    public List<Publicacion> getPublicacionesTipoDonacion() {
        return this.repositorioPublicacion.getPublicacionesPorTipoPublicacion(PublicacionTipo.DONACION);
    }

    @Override
    public List<Publicacion> getPublicacionesTipoHistoria() {
        return this.repositorioPublicacion.getPublicacionesPorTipoPublicacion(PublicacionTipo.HISTORIA);
    }

    @Override
    public String getSeccionEditar(PublicacionTipo publicacionTipo) {
        String seccionEditar = "";
        switch (publicacionTipo) {
           case HISTORIA:
               seccionEditar = "historia";
               break;
           case DONACION:
               seccionEditar= "donacion";
               break;
           case BUSCADO_POR_DUENIO:
               seccionEditar= "perdido";
        }
        return seccionEditar;
    }

    @Override
    public Publicacion getPublicacionPorId(Long idPublicacion) {
      return  this.repositorioPublicacion.getPublicacionPorId(idPublicacion);
    }

}
