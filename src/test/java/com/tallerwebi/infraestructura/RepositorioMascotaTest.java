package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Mascota;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioMascota;
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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateTestInfraestructuraConfig.class})
public class RepositorioMascotaTest {

    @Autowired //se utiliza para hacer una inyeccion de dependencia automaticamente.
    private SessionFactory sessionFactory;

    private RepositorioMascota repositorioMascota;

    @BeforeEach
    public void init() {
        this.repositorioMascota = new RepositorioMascotaImpl(this.sessionFactory);
    }

    @Test
    @Transactional //Se utiliza para asegurar que se cumpla la operacion, en el caso que falle se hace un rollback.
    @Rollback //Para que se vuelva a generar la Query cuando se termine el test.
    public void queSePuedaGuardarUnaMascota() {
        Mascota mascota = new Mascota();
        mascota.setNombre("Cachito");
        mascota.setDescripcion("Perro Labrador");

        this.repositorioMascota.guardarMascota(mascota);

        Mascota mascotaObtenida = this.sessionFactory.getCurrentSession() //Para crear la Query
                .createQuery("FROM Mascota where id = 1", Mascota.class)
                .getSingleResult();

        assertThat(mascotaObtenida, equalTo(mascota));
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaBuscarUnaMascotaPorIdCuandoElIdEsCuatro() {
        Mascota mascotaUno = new Mascota();
        mascotaUno.setNombre("Nina");
        mascotaUno.setDescripcion("Falsa caniche");
        this.repositorioMascota.guardarMascota(mascotaUno);

        Mascota mascotaDos = new Mascota();
        mascotaDos.setNombre("Akane");
        mascotaDos.setDescripcion("Gigante color negro");
        this.repositorioMascota.guardarMascota(mascotaDos);

        assertThat(mascotaDos.getId(), equalTo(4L));

        Mascota mascotaObtenida = repositorioMascota.buscarMascotaPorId(4L);

        assertThat(mascotaObtenida.getId(), equalTo(mascotaDos.getId()));
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaActualizarElNombreDeLaMascota() {
        Mascota mascota = new Mascota();
        mascota.setNombre("Pancho");
        mascota.setDescripcion("Perrito salchicha");
        this.sessionFactory.getCurrentSession().save(mascota);

        String nuevoNombre = "Braulio";
        mascota.setNombre(nuevoNombre);
        this.repositorioMascota.modificarMascota(mascota);

        Mascota mascotaActualizada = (Mascota) this.sessionFactory.getCurrentSession()
                .createQuery("FROM Mascota WHERE id = :id")
                .setParameter("id", mascota.getId())
                .getSingleResult();

        assertThat(mascotaActualizada.getNombre(), equalTo(nuevoNombre));
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaActualizarLaDescripcionDeLaMascota() {
        Mascota mascota = new Mascota();
        mascota.setNombre("Jazmín");
        mascota.setDescripcion("Caniche");
        this.repositorioMascota.guardarMascota(mascota);

        String nuevaDescripcion = "Caniche de Susana";
        mascota.setDescripcion(nuevaDescripcion);
        this.repositorioMascota.modificarMascota(mascota);

        Mascota mascotaActualizada = (Mascota) this.sessionFactory.getCurrentSession()
                .createQuery("FROM Mascota WHERE id = :id")
                .setParameter("id", mascota.getId())
                .getSingleResult();

        assertThat(mascotaActualizada.getDescripcion(), equalTo(nuevaDescripcion));
    }
}