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
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
    public void queSePuedaEliminarUnaPublicacion(){
        // Preparación
        PublicacionPerdido perdido = new PublicacionPerdido();
        perdido.setDescripcion("Gato perdido");
        perdido.setIdPublicacion(1L);
        Long idPublicacionEliminada = 1L;

        // Ejecución
        this.repositorioPublicacion.guardarPerdido(perdido);
        this.repositorioPublicacion.eliminarPublicacion(perdido);

        // Verificación
        PublicacionPerdido PublicacionNula = (PublicacionPerdido) this.repositorioPublicacion.getPublicacionPorId(1L);

        assertThat(PublicacionNula, nullValue());
    }
    @Test
    @Transactional
    @Rollback
    public void queSePuedaEliminarUnaPublicacionPoId(){
        // Preparación
        PublicacionPerdido perdido = new PublicacionPerdido();
        perdido.setDescripcion("Gato perdido");
        perdido.setIdPublicacion(1L);
        Long idPublicacionEliminada = 1L;

        // Ejecución
        this.repositorioPublicacion.guardarPerdido(perdido);
        this.repositorioPublicacion.eliminarPublicacionPorId(1L);

        // Verificación
        PublicacionPerdido PublicacionNula = (PublicacionPerdido) this.repositorioPublicacion.getPublicacionPorId(idPublicacionEliminada);

        assertThat(PublicacionNula, nullValue());
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaGuardarUnaPublicacionDonacion(){
        // Preparación
        PublicacionDonacion donacion = new PublicacionDonacion();
        donacion.setDescripcion("Gato con un tumor increible hermano AYUDA");

        // Ejecución
        this.repositorioPublicacion.guardarDonacion(donacion);

        // Verificación
        PublicacionDonacion donacionObtenido = (PublicacionDonacion) this.sessionFactory.getCurrentSession()
                .createQuery("FROM PublicacionDonacion where id = :id")
                .setParameter("id", donacion.getIdPublicacion())
                .getSingleResult();

        assertThat(donacionObtenido, notNullValue());
        assertThat(donacionObtenido.getDescripcion(), equalTo(donacion.getDescripcion()));
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaGuardarUnaPublicacionHistoria(){
        // Preparación
        PublicacionHistoria historia = new PublicacionHistoria();
        historia.setTitular("Historia épica");
        historia.setZona(Zona.OESTE);
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

    @Test
    @Transactional
    @Rollback
    public void queSePuedanObtenerLasPublicacionesPorZona(){
        // Preparación
        dadoQueExistenPublicaciones();
        Zona zona = Zona.OESTE;

        PublicacionHistoria historia = new PublicacionHistoria();
        historia.setTitular("Historia épica");
        historia.setZona(Zona.OESTE);
        // Ejecución
        this.repositorioPublicacion.guardarHistoria(historia);
        List<Publicacion> publicaciones = this.repositorioPublicacion.getPublicacionesPorZona(zona);

        // Verificación
        assertThat(publicaciones, notNullValue());
        assertThat(publicaciones, hasSize(1));
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedanObtenerLasPublicacionesPorTipoDePublicacion(){
        // Preparación
        dadoQueExistenPublicaciones();
        PublicacionTipo publicacionTipo = PublicacionTipo.HISTORIA;

        PublicacionHistoria historia = new PublicacionHistoria();
        historia.setTitular("Historia épica");
        historia.setTipoPublicacion(PublicacionTipo.HISTORIA);
        // Ejecución
        this.repositorioPublicacion.guardarHistoria(historia);
        List<Publicacion> publicaciones = this.repositorioPublicacion.getPublicacionesPorTipoPublicacion(publicacionTipo);

        // Verificación
        assertThat(publicaciones, notNullValue());
        assertThat(publicaciones, hasSize(1));
        assertThat(publicaciones.get(0).getTipoPublicacion(),equalTo(PublicacionTipo.HISTORIA));
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedanObtenerLasPublicacionesPorNombreMascota(){
        // Preparación
        dadoQueExistenPublicaciones();
        String nombreMascota = "CAPO";

        PublicacionHistoria historia = new PublicacionHistoria();
        historia.setTitular("Historia épica");
        historia.setNombreMascota("CAPO");
        // Ejecución
        this.repositorioPublicacion.guardarHistoria(historia);
        List<Publicacion> publicaciones = this.repositorioPublicacion.getPublicacionesPorNombreMascota(nombreMascota);

        // Verificación
        assertThat(publicaciones, notNullValue());
        assertThat(publicaciones, hasSize(1));
        assertThat(publicaciones.get(0).getNombreMascota(),equalToIgnoringCase("CAPO"));
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedanObtenerLasPublicacionesPorNombreMascotaYZona(){
        // Preparación
        dadoQueExistenPublicaciones();
        String nombreMascota = "CAPO";
        Zona zona = Zona.OESTE;

        PublicacionHistoria historia = new PublicacionHistoria();
        historia.setTitular("Historia épica");
        historia.setNombreMascota("CAPO");
        historia.setZona(Zona.OESTE);
        // Ejecución
        this.repositorioPublicacion.guardarHistoria(historia);
        List<Publicacion> publicaciones = this.repositorioPublicacion.getPublicacionesPorZonaYNombreMascota(zona,nombreMascota);

        // Verificación
        assertThat(publicaciones, notNullValue());
        assertThat(publicaciones, hasSize(1));
        assertThat(publicaciones.get(0).getNombreMascota(),equalToIgnoringCase("CAPO"));
        assertThat(publicaciones.get(0).getZona(),equalTo(Zona.OESTE));
    }

//    @Test
//    @Transactional
//    @Rollback
//    public void queSePuedaEditarUnaPublicacionDonacion(){
//        // Preparación
//        PublicacionDonacion donacion = new PublicacionDonacion();
//        donacion.setDescripcion("Gato con un tumor increible hermano AYUDA");
//        donacion.setIdPublicacion(1L);
//        donacion.setZona(Zona.OESTE);
//        donacion.setNombreMascota("CAPO");
//        donacion.setmontoACubrir(400.0);
//        //datos para editar
//        Long id = 1L;
//        String descripcionEdit = "Esto esta editado";
//        Zona zona = Zona.OESTE;
//        String nombreMascota = "RAFA";
//        Double  montoACubrir = 100.0;
//        byte[] imagenBytes = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
//
//        // Ejecución
//        this.repositorioPublicacion.guardarDonacion(donacion);
//        this.repositorioPublicacion.editarDonacion(id,nombreMascota,montoACubrir,zona,descripcionEdit,imagenBytes);
//       PublicacionDonacion publicacionObtenida = (PublicacionDonacion) this.repositorioPublicacion.getPublicacionPorId(1l);
//        // Verificación
//
//
//        assertThat(publicacionObtenida, notNullValue());
//        assertThat(publicacionObtenida.getDescripcion(), equalTo(descripcionEdit));
//    }

//    @Test
//    @Transactional
//    @Rollback
//    public void queSePuedanObtenerUnaPublicacionPorId(){
//        // Preparación
//        Zona zona = Zona.OESTE;
//
//        PublicacionHistoria historia = new PublicacionHistoria();
//        historia.setTitular("Historia épica");
//        historia.setIdPublicacion(99L);
//        // Ejecución
//        this.repositorioPublicacion.guardarHistoria(historia);
//        PublicacionHistoria publicacionObtenida = (PublicacionHistoria) this.repositorioPublicacion.getPublicacionPorId(1L);
//
//        // Verificación
//        assertThat(publicacionObtenida, notNullValue());
//        assertThat(publicacionObtenida.getIdPublicacion(), equalTo(99L));
//        assertThat(publicacionObtenida.getTitular(), equalTo("Historia épica"));
//    }















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
