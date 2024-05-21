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

    private SessionFactory sessionFactory;

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
/*
    @Override
    public void modificarTelefonoPublicacion(Publicacion publicacion) {
        String hql = "UPDATE Publicacion set telefonoContacto = :telefono WHERE idPublicacion = :idPublicacion";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("telefono", publicacion.getNumContacto());
        query.setParameter("idPublicacion", publicacion.getIdPublicacion());
        query.executeUpdate();
    }

    @Override
    public void crearPublicacionParaMascotaExistente(Mascota mascota, PublicacionTipo tipoPublicacion, String descripcion) {
        Publicacion publicacion = new Publicacion(tipoPublicacion, mascota.getZona(), descripcion, mascota.getUsuario().getTelefono());
        this.sessionFactory.getCurrentSession().save(publicacion);
    }

    @Override
    public void crearPublicacionParaMascotaNueva(Mascota mascota, PublicacionTipo tipoPublicacion, String descripcion) {
        this.repositorioMascota.guardarMascota(mascota);
        Long idMascota = mascota.getId();
        Publicacion publicacion = new Publicacion(tipoPublicacion, mascota.getZona(), descripcion, mascota.getUsuario().getTelefono());
        this.sessionFactory.getCurrentSession().save(publicacion);
    }


    @Override
    public void nuevaPublicacion(String nombre, String foto, String descripcion, Zona zona, MascotaTipo tipoMascota, MascotaRaza raza, MascotaColor color, Usuario usuario, MascotaEstado estado, PublicacionTipo tipoPublicacion) {
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
    public List<Publicacion> buscarPublicacionesPorColorPelo(MascotaColor color) {
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
*/
       /* String nombreMascota= perdido.getNombreMascota();
        String direccion = perdido.getDireccion();
        String nombreContacto = perdido.getNombreContacto();
        Zona zona = perdido.getZona();
        MascotaColor mascotaColor= perdido.getMascotaColor();
        String descripcion = perdido.getDescripcion();
        Integer telefonoContacto= perdido.getTelefonoContacto();

        String hql = "INSERT INTO Publicacion  (nombreMascota, direccion,nombreContacto,zona,mascotaColor, descripcion, telefonoContacto) " +
                "  values (nombreMascota, direccion,nombreContacto,zona,mascotaColor, descripcion, telefonoContacto)";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
    }*/
}
