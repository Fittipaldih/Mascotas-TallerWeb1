package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Peluqueria;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioPeluqueria;
import com.tallerwebi.infraestructura.config.HibernateTestInfraestructuraConfig;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateTestInfraestructuraConfig.class})
public class RepositorioPeluqueriaTest {

    @Autowired
    private SessionFactory sessionFactory;

    private RepositorioPeluqueria repositorioPeluqueria;

    @BeforeEach
    public void init() {
        this.repositorioPeluqueria = new RepositorioPeluqueriaImpl(this.sessionFactory);
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaGuardarUnaPeluqueria() {
        //preparacion
        Peluqueria Peluqueria = new Peluqueria();
        Peluqueria.setDireccion("Finlandia 3212, Tablada");
        Peluqueria.setNombre("Mi Peluqueria");
        Peluqueria.setId((long)1);
        Peluqueria.setTelefono(11111111);
        //ejecucion
        this.repositorioPeluqueria.guardarPeluqueria(Peluqueria);

        Peluqueria PeluqueriaObtenida = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Peluqueria where nombre = 'Mi Peluqueria'", Peluqueria.class)
                .getSingleResult();
        //comprobacion
        assertThat(PeluqueriaObtenida, equalTo(Peluqueria));
        assertThat(PeluqueriaObtenida.getTelefono(), equalTo(Peluqueria.getTelefono()));
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaEditarTelefonoDeUnaPeluqueria() {
        //preparacion
        Peluqueria Peluqueria = new Peluqueria();
        Peluqueria.setDireccion("Finlandia 3212, Tablada");
        Peluqueria.setNombre("Peluqueria");
        Peluqueria.setId((long)1);
        Peluqueria.setTelefono(11111111);
        this.repositorioPeluqueria.guardarPeluqueria(Peluqueria);
        Peluqueria PeluqueriaObtenida = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Peluqueria where idPeluqueria = 1", Peluqueria.class)
                .getSingleResult();

        assertThat(PeluqueriaObtenida.getTelefono(), equalTo(11111111));
        // ejecucion
        Peluqueria.setTelefono(12345678);
        this.repositorioPeluqueria.modificarTelefonoPeluqueria(Peluqueria);
        // comprobacion
        assertThat(PeluqueriaObtenida.getTelefono(), equalTo(12345678));
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaEliminarPeluqueriaConId1(){
        //preparacion
        Peluqueria Peluqueria = new Peluqueria();
        Peluqueria.setDireccion("Finlandia 3212, Tablada");
        Peluqueria.setNombre("Peluqueria");
        Peluqueria.setId((long)1);
        Peluqueria.setTelefono(11111111);
        this.repositorioPeluqueria.guardarPeluqueria(Peluqueria);
        // ejecucion
        this.repositorioPeluqueria.eliminarPeluqueria(Peluqueria);
        // comprobacion
        assertThrows(NoResultException.class, () ->
                this.sessionFactory.getCurrentSession()
                        .createQuery("FROM Peluqueria where idPeluqueria = :id", Peluqueria.class)
                        .setParameter("id", Peluqueria.getIdPeluqueria())
                        .getSingleResult());
    }

}