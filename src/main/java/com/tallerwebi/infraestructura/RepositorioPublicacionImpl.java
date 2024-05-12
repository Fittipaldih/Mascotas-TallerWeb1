package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.PerdidoExeption;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;
import java.util.List;

@Repository("repositorioPublicacion")
public class RepositorioPublicacionImpl implements RepositorioPublicacion {

    private SessionFactory sessionFactory;

    public RepositorioPublicacionImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Publicacion> getPublicaciones() {
        String hql = "FROM Publicacion";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        return query.getResultList();
    }

    @Override
    public void guardarPublicacion(Publicacion publicacion) {
        this.sessionFactory.getCurrentSession().save(publicacion);
    }

    @Override
    public void modificarTelefonoPublicacion(Publicacion publicacion) {
        String hql = "UPDATE Publicacion set telefonoContacto = :telefono WHERE idPublicacion = :idPublicacion";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("telefono", publicacion.getTelefonoContacto());
        query.setParameter("idPublicacion", publicacion.getIdPublicacion());
        query.executeUpdate();
    }

    @Override
    public void eliminarPublicacion(Publicacion publicacion) {
        Publicacion publicacionAEliminar = this.sessionFactory.getCurrentSession().get(Publicacion.class, publicacion.getIdPublicacion());
        if (publicacionAEliminar != null) {
            this.sessionFactory.getCurrentSession().delete(publicacionAEliminar);
        }
    }

    @Override
    public Publicacion buscarPublicacion(Publicacion publicacion) {
        Publicacion publicacionEncontrada = this.sessionFactory.getCurrentSession().get(Publicacion.class, publicacion.getIdPublicacion());
        if (publicacionEncontrada != null) {
            return publicacionEncontrada;
        }
        return null;
    }

    @Override
    public List<Publicacion> buscarPublicacionesPorUsuario(Usuario usuario) {
        String hql = "FROM Publicacion p WHERE p.usuario = :usuario";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("usuario", usuario);
        return query.getResultList();
    }

    @Override
    public List<Publicacion> buscarPublicacionesPorColorPelo(Color color) {
        String hql = "FROM Publicacion p WHERE p.color = :color";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("color", color);
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
    public void guardarPerdido(Perdido perdido) throws PerdidoExeption {
        Boolean seGuardo = (Boolean) this.sessionFactory.getCurrentSession().save(perdido);
        if (seGuardo == false) {
            throw new PerdidoExeption();
        }
    }
}
