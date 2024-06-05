package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.Comentario;
import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioPublicacion;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioComentario;
import com.tallerwebi.dominio.servicios.interfaces.ServicioDetallePublicacion;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioDetallePublicacionImpl implements ServicioDetallePublicacion {

    private final RepositorioPublicacion repositorioPublicacion;
    private final RepositorioComentario repositorioComentario;

    @Autowired
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

    @Override
    public void eliminarPublicacion(Long idPublicacion) {
        eliminarComentariosAsociados(idPublicacion);
        this.repositorioPublicacion.eliminarPublicacionPorId(idPublicacion);
    }

    private void eliminarComentariosAsociados(Long idPublicacion) {
        Publicacion publicacion = this.repositorioPublicacion.getPublicacionPorId(idPublicacion);
        List<Comentario> comentarios = publicacion.getComentarios();
        for (Comentario comentario : comentarios) {
            this.repositorioPublicacion.eliminarComentarioPorId(comentario.getId());
        }
    }

    @Override
    public void hacerComentario(String textoDelComentario, Long idPublicacion) throws Exception {
        this.repositorioComentario.guardarNuevoComentarioEnPublicacion(textoDelComentario, idPublicacion);
    }
}