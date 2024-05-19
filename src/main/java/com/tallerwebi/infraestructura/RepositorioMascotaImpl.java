package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Zona;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioMascota;
import com.tallerwebi.dominio.Mascota;
import org.hibernate.SessionFactory;
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
    public Mascota buscarMascotaPorId(Long idMascota) {
        String sql = "Select m from Mascota m where m.id = :idMascota";
        Query query = sessionFactory.getCurrentSession().createQuery(sql);
        query.setParameter("idMascota", idMascota);
        return (Mascota)query.getSingleResult();
    }

    @Override
    public List<Mascota> buscarMascotasPorZona(Zona zona) {
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
/*
    @Override
    public Mascota buscarMascotaPorIdPublicacion(Long idPublicacion) {
        String hql = "FROM Mascota m WHERE m.idPublicacion = :idPublicacion";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("idPublicacion", idPublicacion);
        return (Mascota) query.getSingleResult();
    }*/

    @Override
    public Boolean verificarSiMascotaExiste(Mascota mascota) {
        List<Mascota> mascotasQueTengo = this.dameTodasLasMascotas();
        if (!mascotasQueTengo.contains(mascota)) {
            return false;
        } return true;
    }
}
