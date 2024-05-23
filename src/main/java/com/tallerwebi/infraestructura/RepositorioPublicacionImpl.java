package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioPublicacion;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class RepositorioPublicacionImpl implements RepositorioPublicacion {

     SessionFactory sessionFactory;

    @Autowired
    public RepositorioPublicacionImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void guardarPerdido(PublicacionPerdido perdido) {
        this.sessionFactory.getCurrentSession().save(perdido);
    }

    @Override
    public void guardarDonacion(PublicacionDonacion donacion)  { this.sessionFactory.getCurrentSession().save(donacion);}

    @Override
    public void guardarHistoria(PublicacionHistoria historia) {this.sessionFactory.getCurrentSession().save(historia);}

    @Override
    public List<Publicacion> getPublicaciones() {
        String hql = "FROM Publicacion";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        return query.getResultList();
    }

    @Override
    public List<Publicacion> buscarPublicacionesPorZona(Zona zona) {
        String hql = "FROM Publicacion p WHERE p.zona = :zona";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("zona", zona);
        return query.getResultList();
    }

    @Override
    public List<Publicacion> getPublicacionesPorTipoPublicacion(PublicacionTipo tipo){
        String hql = "FROM Publicacion p WHERE p.tipoPublicacion = :tipo";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("tipo", tipo);
        return query.getResultList();
    }

    public List<PublicacionHistoria> getPublicacionesHistoriaPorZona(Zona zona){
        String hql = "FROM Publicacion p WHERE p.zona = :zona AND p.tipoPublicacion = 'HISTORIA'";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("zona", zona);
        return query.getResultList();
    }

}
