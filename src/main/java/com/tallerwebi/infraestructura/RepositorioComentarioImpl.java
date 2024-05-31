package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Comentario;
import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioComentario;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RepositorioComentarioImpl implements RepositorioComentario {

    @Autowired
    private final SessionFactory sessionFactory;

    @Autowired
    public RepositorioComentarioImpl(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void guardarNuevoComentarioEnPublicacion(String contenido, Long idPublicacion) throws Exception {
        if (contenido == null || contenido.trim().isEmpty()) {
            throw new Exception("El comentario esta vacio");
        }
        Publicacion publicacion = this.sessionFactory.getCurrentSession().get(Publicacion.class, idPublicacion);

        if (publicacion != null) {
            Comentario comentario = new Comentario(contenido);
            comentario.setPublicacion(publicacion);
            this.sessionFactory.getCurrentSession().save(comentario);
        } else {
            throw new IllegalArgumentException("Publicación con ID " + idPublicacion + " no encontrada");
        }
    }
}
