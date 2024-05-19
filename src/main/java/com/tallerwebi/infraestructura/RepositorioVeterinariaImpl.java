package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Zona;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioVeterinaria;
import com.tallerwebi.dominio.Veterinaria;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository("repositorioVeterinaria")
public class RepositorioVeterinariaImpl implements RepositorioVeterinaria {

    private SessionFactory sessionFactory;

    public RepositorioVeterinariaImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Veterinaria> dameTodasLasVeterinarias() {
        String hql = "FROM Veterinaria";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        return query.getResultList();
    }

    @Override
    public void guardarVeterinaria(Veterinaria veterinaria) {
        this.sessionFactory.getCurrentSession().save(veterinaria);
    }

    @Override
    public void modificarTelefonoVeterinaria(Veterinaria veterinaria) {
        String hql = "UPDATE Veterinaria set telefono = :telefono WHERE idVeterinaria = :idVeterinaria";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("telefono", veterinaria.getTelefono());
        query.setParameter("idVeterinaria", veterinaria.getIdVeterinaria());
        query.executeUpdate();
    }

    @Override
    public void eliminarVeterinaria(Veterinaria veterinaria) {
        Veterinaria veterinariaAEliminar = this.sessionFactory.getCurrentSession().get(Veterinaria.class, veterinaria.getIdVeterinaria());
        if (veterinariaAEliminar != null) {
            this.sessionFactory.getCurrentSession().delete(veterinariaAEliminar);
        }
    }

    @Override
    public List<Veterinaria> buscarVeterinariasPorZona(Zona zona) {
        String hql = "FROM Veterinaria v WHERE v.direccion LIKE :zona";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("zona", "%" + zona + "%");
        return query.getResultList();
    }
    @Override
    public Veterinaria buscarVeterinariaPorNombre(String nombre) {
        String hql = "FROM Veterinaria v WHERE v.nombre LIKE :nombre";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("nombre", nombre);
        return (Veterinaria) query.getSingleResult();
    }
}
