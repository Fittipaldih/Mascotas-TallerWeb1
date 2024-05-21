package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioPublicacion;
import com.tallerwebi.infraestructura.config.HibernateTestInfraestructuraConfig;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateTestInfraestructuraConfig.class})
public class RepositorioPublicacionTest {

    @Autowired
    private SessionFactory sessionFactory;

    private RepositorioPublicacion repositorioPublicacion;

    @BeforeEach
    public void init(){
        this.repositorioPublicacion = new RepositorioPublicacionImpl(this.sessionFactory);
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaGuardarUnaPublicacionPerdido(){
        // Preparación
        PublicacionPerdido perdido = new PublicacionPerdido();
        perdido.setDescripcion("Gato perdido");

        // Ejecución
        this.repositorioPublicacion.guardarPerdido(perdido);

        // Verificación
        PublicacionPerdido perdidoObtenido = (PublicacionPerdido) this.sessionFactory.getCurrentSession()
                .createQuery("FROM PublicacionPerdido where id = :id")
                .setParameter("id", perdido.getIdPublicacion())
                .getSingleResult();

        assertThat(perdidoObtenido, notNullValue());
        assertThat(perdidoObtenido.getDescripcion(), equalTo(perdido.getDescripcion()));
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaGuardarUnaPublicacionHistoria(){
        // Preparación
        PublicacionHistoria historia = new PublicacionHistoria();
        historia.setTitular("Historia épica");

        // Ejecución
        this.repositorioPublicacion.guardarHistoria(historia);

        // Verificación
        PublicacionHistoria historiaObtenida = (PublicacionHistoria) this.sessionFactory.getCurrentSession()
                .createQuery("FROM PublicacionHistoria where id = :id")
                .setParameter("id", historia.getIdPublicacion())
                .getSingleResult();

        assertThat(historiaObtenida, notNullValue());
        assertThat(historiaObtenida.getTitular(), equalTo(historia.getTitular()));
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedanObtenerTodasLasPublicaciones(){
        // Preparación
        dadoQueExistenPublicaciones();

        // Ejecución
        List<Publicacion> publicaciones = this.repositorioPublicacion.getPublicaciones();

        // Verificación
        assertThat(publicaciones, notNullValue());
        assertThat(publicaciones, hasSize(3));
    }

    private void dadoQueExistenPublicaciones() {
        PublicacionPerdido perdido = new PublicacionPerdido();
        perdido.setDescripcion("Gato perdido");

        PublicacionHistoria historia = new PublicacionHistoria();
        historia.setTitular("Historia épica");

        PublicacionHistoria historia2 = new PublicacionHistoria();
        historia2.setTitular("Otra historia");

        this.sessionFactory.getCurrentSession().save(perdido);
        this.sessionFactory.getCurrentSession().save(historia);
        this.sessionFactory.getCurrentSession().save(historia2);
    }
}
