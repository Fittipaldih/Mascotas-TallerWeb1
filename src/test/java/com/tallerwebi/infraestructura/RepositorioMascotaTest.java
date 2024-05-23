package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Mascota;
import com.tallerwebi.dominio.Usuario;
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

    @Autowired
    private SessionFactory sessionFactory;
    private RepositorioMascota repositorioMascota;

    @BeforeEach
    public void init() {
        this.repositorioMascota = new RepositorioMascotaImpl(this.sessionFactory);
    }

    private Usuario guardarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Mati");
        this.sessionFactory.getCurrentSession().save(usuario);
        return usuario;
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaGuardarUnaMascota() {
        //preparacion
        Usuario usuario = this.guardarUsuario();
        Mascota mascota = new Mascota();
        mascota.setNombre("Cachito");
        mascota.setDescripcion("Perro Labrador");
        mascota.setUsuario(usuario);
        //ejecucion
        this.repositorioMascota.guardarMascota(mascota);
        //comprobacion
        Mascota mascotaObtenida = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Mascota where id = 1", Mascota.class)
                .getSingleResult();

        assertThat(mascotaObtenida, equalTo(mascota));
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaBuscarUnaMascotaPorIdCuandoElIdEsCuatro() {
        // preparacion
        Usuario usuario = this.guardarUsuario();
        Mascota mascotaUno = new Mascota();
        mascotaUno.setNombre("Nina");
        mascotaUno.setDescripcion("Falsa caniche");
        mascotaUno.setUsuario(usuario);
        this.repositorioMascota.guardarMascota(mascotaUno);
        // aca sucede que toma las mascotas de los demas test , por eso solo se crean dos y ya tiene id 4
        Mascota mascotaDos = new Mascota();
        mascotaDos.setNombre("Akane");
        mascotaDos.setDescripcion("Gigante color negro");
        mascotaDos.setUsuario(usuario);
        this.repositorioMascota.guardarMascota(mascotaDos);
        // ejecucion
        assertThat(mascotaDos.getId(), equalTo(4L));
        //comprobacion
        Mascota mascotaObtenida = repositorioMascota.buscarMascotaPorId(4L);
        assertThat(mascotaObtenida.getId(), equalTo(mascotaDos.getId()));
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaActualizarElNombreDeLaMascota() {
        // preparacion
        Usuario usuario = this.guardarUsuario();
        Mascota mascota = new Mascota();
        mascota.setNombre("Pancho");
        mascota.setDescripcion("Perrito salchicha");
        mascota.setUsuario(usuario);
        this.sessionFactory.getCurrentSession().save(mascota);
        // ejecucion
        String nuevoNombre = "Braulio";
        mascota.setNombre(nuevoNombre);
        this.repositorioMascota.modificarMascota(mascota);
        //comprobacion
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
        // preparacion
        Usuario usuario = this.guardarUsuario();
        Mascota mascota = new Mascota();
        mascota.setNombre("Jazmín");
        mascota.setDescripcion("Caniche");
        mascota.setUsuario(usuario);
        this.repositorioMascota.guardarMascota(mascota);
        //ejecucion
        String nuevaDescripcion = "Caniche de Susana";
        mascota.setDescripcion(nuevaDescripcion);
        this.repositorioMascota.modificarMascota(mascota);
        //comprobacion
        Mascota mascotaActualizada = (Mascota) this.sessionFactory.getCurrentSession()
                .createQuery("FROM Mascota WHERE id = :id")
                .setParameter("id", mascota.getId())
                .getSingleResult();

        assertThat(mascotaActualizada.getDescripcion(), equalTo(nuevaDescripcion));
    }
}