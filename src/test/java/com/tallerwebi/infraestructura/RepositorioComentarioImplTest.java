package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Comentario;
import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.PublicacionDonacion;
import com.tallerwebi.dominio.PublicacionHistoria;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioComentario;
import com.tallerwebi.infraestructura.config.HibernateTestInfraestructuraConfig;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateTestInfraestructuraConfig.class})
public class RepositorioComentarioImplTest {

    @Autowired
    private SessionFactory sessionFactory;

    private RepositorioComentario repositorioComentario;

    @BeforeEach
    public void init() {
        this.repositorioComentario = new RepositorioComentarioImpl(this.sessionFactory);
    }

    @Test
    @Transactional
    @Rollback
    public void queSeGuardeElComentarioEnLaBDCuandoHayContenidoParaGuardar() throws Exception {
        //Preparacion
        Publicacion publicacion = new PublicacionHistoria();
        this.sessionFactory.getCurrentSession().save(publicacion);

        String contenido = "Este es un comentario de prueba";

        //Ejecucion
        repositorioComentario.guardarNuevoComentarioEnPublicacion(contenido, publicacion.getIdPublicacion());

        //Verificacion
        Query query = this.sessionFactory.getCurrentSession().createQuery("FROM Comentario WHERE publicacion.idPublicacion = :idPublicacion");
        query.setParameter("idPublicacion", publicacion.getIdPublicacion());
        List<Comentario> comentarios = query.getResultList();

        assertThat(comentarios, is(not(empty())));
        assertThat(comentarios, hasSize(1));
        assertThat(comentarios.get(0).getContenido(), is(equalTo(contenido)));
    }

    @Test
    @Transactional
    @Rollback
    public void queNoSeGuardeElComentarioEnLaBDCuandoNoHayContenidoParaGuardar() throws Exception {
        //Preparacion
        Publicacion publicacion = new PublicacionDonacion();
        this.sessionFactory.getCurrentSession().save(publicacion);

        String contenido = "";

        //Ejecucion
        repositorioComentario.guardarNuevoComentarioEnPublicacion(contenido, publicacion.getIdPublicacion());

        //Verificacion
        Query query = this.sessionFactory.getCurrentSession().createQuery("FROM Comentario WHERE publicacion.idPublicacion = :idPublicacion");
        query.setParameter("idPublicacion", publicacion.getIdPublicacion());
        List<Comentario> comentarios = query.getResultList();

        assertThat(comentarios, is(empty()));
    }

    @Test
    @Transactional
    @Rollback
    public void queRespondaCorrectamenteCuandoLaPublicacionEsNula() {
        //Preparacion
        String contenido = "Este es un comentario de prueba";
        Long idPublicacionInvalida = -1L;

        //Ejecucion - verificacion
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            repositorioComentario.guardarNuevoComentarioEnPublicacion(contenido, idPublicacionInvalida);
        });

        assertThat(exception.getMessage(), is(equalTo("Publicación con ID " + idPublicacionInvalida + " no encontrada")));
    }
}
