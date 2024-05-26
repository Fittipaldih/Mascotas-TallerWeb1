package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.excepcion.MascotaNoEncontrada;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioPublicacion;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioComentario;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioDetallePublicacionImpl implements ServicioDetallePublicacion {

    @Autowired
    private RepositorioPublicacion repositorioPublicacion;
    @Autowired
    private RepositorioComentario repositorioComentario;

    public ServicioDetallePublicacionImpl(RepositorioPublicacion repositorioPublicacion, RepositorioComentario repositorioComentario) {
        this.repositorioPublicacion = repositorioPublicacion;
        this.repositorioComentario = repositorioComentario;
    }

    @Override
    public Publicacion getPublicacion(Long id) throws Exception {
        Publicacion publicacion = this.repositorioPublicacion.getPublicacionPorId(id);
        if (publicacion == null) {
            throw new Exception("No existe publicacion con el id " + id + " o fue eliminada");
        }
        Hibernate.initialize(publicacion.getComentarios());
        return publicacion;
    }

    public void hacerComentario(String textoDelComentario, Long idPublicacion) throws Exception {
        this.repositorioComentario.guardarNuevoComentarioEnPublicacion(textoDelComentario, idPublicacion);
    }
}
