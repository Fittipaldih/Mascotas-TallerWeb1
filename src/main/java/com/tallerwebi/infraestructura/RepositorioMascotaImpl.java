package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioMascota;
import com.tallerwebi.dominio.Mascota;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository("repositorioMascota")
public class RepositorioMascotaImpl implements RepositorioMascota {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioMascotaImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Mascota> dameTodasLasMascotas() {
        String hql = "FROM Mascota";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        return query.getResultList();
    }

    @Override
    public Mascota buscarMascotaPorId(Integer idMascota) {
        final Session session = sessionFactory.getCurrentSession();
        return (Mascota) session.createCriteria(Mascota.class)
                .add(Restrictions.eq("idMascota", idMascota))
                .uniqueResult();
    }
    @Override
    public List<Mascota> buscarMascotasPorZona(String zona) {
        String sql = "Select m from Mascota m where m.zona = :zona";
        Query query = sessionFactory.getCurrentSession().createQuery(sql);
        query.setParameter("zona", zona);
        return query.getResultList();
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
        sessionFactory.getCurrentSession().delete(mascota);
    }

}
