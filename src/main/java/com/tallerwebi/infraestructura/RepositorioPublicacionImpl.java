package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.PublicacionInexistenteExeption;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioPublicacion;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class RepositorioPublicacionImpl implements RepositorioPublicacion {

    @Autowired
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

    @Override
    public void eliminarPublicacion(Publicacion publicacion) {
        Publicacion publicacionAEliminar = this.sessionFactory.getCurrentSession().get(Publicacion.class, publicacion.getIdPublicacion());
        if (publicacionAEliminar != null) {
            this.sessionFactory.getCurrentSession().delete(publicacionAEliminar);
        }
    }

    @Override
    public void eliminarPublicacionPorId(Long id) {
        String hql = "DELETE FROM Publicacion p WHERE p.id = :id";

        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void eliminarComentarioPorId(Long id) {
        String hql = "DELETE FROM Comentario c WHERE c.id = :id";

        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Publicacion getPublicacionPorId(Long id) {
        String hql = "FROM Publicacion P WHERE P.id = :id";

        org.hibernate.query.Query<Publicacion> query = this.sessionFactory.getCurrentSession().createQuery(hql, Publicacion.class);
        query.setParameter("id", id);

        return query.uniqueResult();
    }



    @Override
    public void editarHistoria(Long idPublicacion, String titular, String nombreMascota, Zona zona, String descripcion, byte[] imagenBytes) {
            String hql = "UPDATE PublicacionHistoria p SET p.descripcion = :descripcion, " +
                    "p.zona = :zona, " +
                    "p.imagen = :imagenBytes, " +
                    "p.nombreMascota = :nombreMascota, " +
                    "p.titular = :titular " +
                    "WHERE p.idPublicacion = :idPublicacion";

            Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
            query.setParameter("descripcion", descripcion);
            query.setParameter("zona", zona);
            query.setParameter("imagenBytes", imagenBytes);
            query.setParameter("nombreMascota", nombreMascota);
            query.setParameter("titular", titular);
            query.setParameter("idPublicacion", idPublicacion);
            query.executeUpdate();
    }

    @Override
    public void editarPerdido(Long idPublicacion, String nombreMascota, Long telefonoContacto,
                              String nombreContacto, MascotaColor mascotaColor, MascotaRaza mascotaRaza,
                              PublicacionTipo tipoPublicacion, Zona zona, String descripcion,
                              String direccion, byte[] imagenBytes)  {
            String hql = "UPDATE PublicacionPerdido p SET p.descripcion = :descripcion, " +
                    "p.zona = :zona, " +
                    "p.imagen = :imagenBytes, " +
                    "p.nombreMascota = :nombreMascota, " +
                    "p.nombreContacto = :nombreContacto, " +
                    "p.telefonoContacto = :telefonoContacto, " +
                    "p.mascotaColor = :mascotaColor, " +
                    "p.mascotaRaza = :mascotaRaza, " +
                    "p.tipoPublicacion = :tipoPublicacion, " +
                    "p.direccion = :direccion " +
                    "WHERE p.idPublicacion = :idPublicacion";

            Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
            query.setParameter("descripcion", descripcion);
            query.setParameter("zona", zona);
            query.setParameter("imagenBytes", imagenBytes);
            query.setParameter("nombreMascota", nombreMascota);
            query.setParameter("nombreContacto", nombreContacto);
            query.setParameter("telefonoContacto", telefonoContacto);
            query.setParameter("mascotaColor", mascotaColor);
            query.setParameter("mascotaRaza", mascotaRaza);
            query.setParameter("tipoPublicacion", tipoPublicacion);
            query.setParameter("direccion", direccion);
            query.setParameter("idPublicacion", idPublicacion);
            query.executeUpdate();
    }


    @Override
    public void editarDonacion(Long idPublicacion, String nombreMascota, Double montoACubrir, Zona zona, String descripcion, byte[] imagenBytes) {
            String hql = "UPDATE PublicacionDonacion p SET p.descripcion = :descripcion, " +
                    "p.zona = :zona, " +
                    "p.imagen = :imagenBytes, " +
                    "p.nombreMascota = :nombreMascota, " +
                    "p.montoACubrir = :montoACubrir, " +
                    "p.nombreMascota = :nombreMascota " +
                    "WHERE p.idPublicacion = :idPublicacion";

            Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
            query.setParameter("descripcion", descripcion);
            query.setParameter("zona", zona);
            query.setParameter("imagenBytes", imagenBytes);
            query.setParameter("nombreMascota", nombreMascota);
            query.setParameter("montoACubrir", montoACubrir);
            query.setParameter("idPublicacion", idPublicacion);
            query.executeUpdate();
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
