package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Veterinaria;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioVeterinaria;
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
public class RepositorioVeterinariaTest {

    @Autowired
    private SessionFactory sessionFactory;

    private RepositorioVeterinaria repositorioVeterinaria;

    @BeforeEach
    public void init() {
        this.repositorioVeterinaria = new RepositorioVeterinariaImpl(this.sessionFactory);
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaGuardarUnaVeterinaria() {
        //preparacion
        Veterinaria veterinaria = new Veterinaria();
        veterinaria.setDireccion("Primera Junta 180, Villa Madero");
        veterinaria.setNombre("Veterinaria Test");
        veterinaria.setId((long)1);
        veterinaria.setOpen24(true);
        veterinaria.setTelefono(1144444444);
        //ejecucion
        this.repositorioVeterinaria.guardarVeterinaria(veterinaria);

        Veterinaria veterinariaObtenida = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Veterinaria where nombre = 'Veterinaria Test'", Veterinaria.class)
                .getSingleResult();
        //comprobacion
        assertThat(veterinariaObtenida, equalTo(veterinaria));
        assertThat(veterinariaObtenida.getTelefono(), equalTo(veterinaria.getTelefono()));
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaEditarTelefonoDeUnaVeterinaria() {
        //preparacion
        Veterinaria veterinaria = new Veterinaria();
        veterinaria.setDireccion("Primera Junta 180, Villa Madero");
        veterinaria.setNombre("Veterinaria");
        veterinaria.setId((long)1);
        veterinaria.setOpen24(true);
        veterinaria.setTelefono(1144444444);
        this.repositorioVeterinaria.guardarVeterinaria(veterinaria);
        Veterinaria veterinariaObtenida = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Veterinaria where idVeterinaria = 1", Veterinaria.class)
                .getSingleResult();

        assertThat(veterinariaObtenida.getTelefono(), equalTo(1144444444));
        // ejecucion
        veterinaria.setTelefono(12345678);
        this.repositorioVeterinaria.modificarTelefonoVeterinaria(veterinaria);
        // comprobacion
        assertThat(veterinariaObtenida.getTelefono(), equalTo(12345678));
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaEliminarVeterinariaConId1(){
        //preparacion
        Veterinaria veterinaria = new Veterinaria();
        veterinaria.setDireccion("Primera Junta 180, Villa Madero");
        veterinaria.setNombre("Veterinaria");
        veterinaria.setId((long)1);
        veterinaria.setOpen24(true);
        veterinaria.setTelefono(1144444444);
        this.repositorioVeterinaria.guardarVeterinaria(veterinaria);
        // ejecucion
        this.repositorioVeterinaria.eliminarVeterinaria(veterinaria);
        // comprobacion
        assertThrows(NoResultException.class, () ->
                this.sessionFactory.getCurrentSession()
                        .createQuery("FROM Veterinaria where idVeterinaria = :id", Veterinaria.class)
                        .setParameter("id", veterinaria.getIdVeterinaria())
                        .getSingleResult());
    }

}