package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.PerdidoExeption;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class RepositorioPublicacionImpl implements RepositorioPublicacion {

    private SessionFactory sessionFactory;
    private RepositorioMascotaImpl repositorioMascota;

    public RepositorioPublicacionImpl(SessionFactory sessionFactory, RepositorioMascotaImpl repositorioMascota) {
        this.sessionFactory = sessionFactory;
        this.repositorioMascota = repositorioMascota;
    }

    @Override
    public List<Publicacion> getPublicaciones() {
        String hql = "FROM Publicacion";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        return query.getResultList();
    }

    @Override
    public void modificarTelefonoPublicacion(Publicacion publicacion) {
        String hql = "UPDATE Publicacion set telefonoContacto = :telefono WHERE idPublicacion = :idPublicacion";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("telefono", publicacion.getNumContacto());
        query.setParameter("idPublicacion", publicacion.getIdPublicacion());
        query.executeUpdate();
    }

    @Override
    public void crearPublicacionParaMascotaExistente(Mascota mascota, TipoPublicacion tipoPublicacion, String descripcion) {
        Publicacion publicacion = new Publicacion(tipoPublicacion, mascota.getZona(), descripcion, mascota.getUsuario().getTelefono());
        this.sessionFactory.getCurrentSession().save(publicacion);
    }

    @Override
    public void crearPublicacionParaMascotaNueva(Mascota mascota, TipoPublicacion tipoPublicacion, String descripcion) {
        this.repositorioMascota.guardarMascota(mascota);
        Long idMascota = mascota.getId();
        Publicacion publicacion = new Publicacion(tipoPublicacion, mascota.getZona(), descripcion, mascota.getUsuario().getTelefono());
        this.sessionFactory.getCurrentSession().save(publicacion);
    }


    @Override
    public void nuevaPublicacion(String nombre, String foto, String descripcion, Zona zona, TipoMascota tipoMascota, RazaMascota raza, ColorMascota color, Usuario usuario, EstadoMascota estado, TipoPublicacion tipoPublicacion) {
        Mascota mascota = new Mascota(nombre, foto, descripcion, zona, tipoMascota, raza, color, usuario, estado);
        if (!this.repositorioMascota.dameTodasLasMascotas().contains(mascota)) {
            this.crearPublicacionParaMascotaNueva(mascota, tipoPublicacion, descripcion);
        } else {
            this.crearPublicacionParaMascotaExistente(mascota, tipoPublicacion, descripcion);
        }
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
        String hql = "FROM Publicacion p JOIN Usuario u WHERE p.idUsuario = u.id";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("usuario", usuario);
        return query.getResultList();
    }

    @Override
    public List<Publicacion> buscarPublicacionesPorColorPelo(ColorMascota color) {
        String hql = "FROM Publicacion p JOIN Mascota m WHERE p.idMascota = m.id AND m.color = :color";
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
