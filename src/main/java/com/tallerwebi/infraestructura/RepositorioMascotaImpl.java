package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioMascota;
import com.tallerwebi.dominio.Mascota;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioMascota")
public class RepositorioMascotaImpl implements RepositorioMascota {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioMascotaImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Mascota buscarMascotaPorId(Integer idMascota) {
        final Session session = sessionFactory.getCurrentSession();
        return (Mascota) session.createCriteria(Mascota.class)
                .add(Restrictions.eq("idMascota", idMascota))
                .uniqueResult();
    }

    @Override
    public void guardarMascota(Mascota mascota) {
        sessionFactory.getCurrentSession().save(mascota);
    }

    @Override
    public void modificarMascota(Mascota mascota) {
        sessionFactory.getCurrentSession().update(mascota);
    }

    @Override
    public void eliminarMascota(Mascota mascota) {

    }

}
