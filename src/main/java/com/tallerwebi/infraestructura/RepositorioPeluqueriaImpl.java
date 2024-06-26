package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Zona;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioPeluqueria;
import com.tallerwebi.dominio.Peluqueria;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;
import java.util.List;

@Repository("repositorioPeluqueria")
public class RepositorioPeluqueriaImpl implements RepositorioPeluqueria {

    private final SessionFactory sessionFactory;

    public RepositorioPeluqueriaImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Peluqueria> dameTodasLasPeluquerias() {
        String hql = "FROM Peluqueria";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        return query.getResultList();
    }

    @Override
    public void guardarPeluqueria(Peluqueria peluqueria) {
        this.sessionFactory.getCurrentSession().save(peluqueria);
    }

    @Override
    public void modificarTelefonoPeluqueria(Peluqueria peluqueria) {
        String hql = "UPDATE Peluqueria set telefono = :telefono WHERE idPeluqueria = :idPeluqueria";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("telefono", peluqueria.getTelefono());
        query.setParameter("idPeluqueria", peluqueria.getIdPeluqueria());
        query.executeUpdate();
    }

    @Override
    public void eliminarPeluqueria(Peluqueria peluqueria) {
        Peluqueria peluqueriaAEliminar = this.sessionFactory.getCurrentSession().get(Peluqueria.class, peluqueria.getIdPeluqueria());
        if (peluqueriaAEliminar != null) {
            this.sessionFactory.getCurrentSession().delete(peluqueriaAEliminar);
        }
    }

    @Override
    public List<Peluqueria> buscarPeluqueriasPorZona(Zona zona) {
        String hql = "FROM Peluqueria v WHERE v.direccion LIKE :zona";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("zona", "%" + zona + "%");
        return query.getResultList();
    }
}
