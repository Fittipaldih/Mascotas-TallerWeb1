package com.tallerwebi.dominio.repositorioInterfaces;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.PerdidoException;

import java.util.List;

public interface RepositorioPublicacion {
    void guardarHistoria(PublicacionHistoria historia);

    void guardarPerdido(PublicacionPerdido perdido);

    void guardarDonacion(PublicacionDonacion donacion);

    List<Publicacion> getPublicaciones();

    List<Publicacion> getPublicacionesPorTipoPublicacion(PublicacionTipo tipo);

    List<Publicacion> buscarPublicacionesPorZona(Zona zona);

/*
   void nuevaPublicacion(String nombre, String foto, String descripcion, Zona zona, MascotaTipo tipoMascota, MascotaRaza raza, MascotaColor color, Usuario usuario, MascotaEstado estado, PublicacionTipo tipoPublicacion);

    void eliminarPublicacion(Publicacion publicacion);

    Publicacion buscarPublicacion(Publicacion publicacion);

    List<Publicacion> buscarPublicacionesPorUsuario(Usuario usuario);

    List<Publicacion> buscarPublicacionesPorColorPelo(MascotaColor color);

    void modificarTelefonoPublicacion(Publicacion Publicacion);

    void crearPublicacionParaMascotaExistente(Mascota mascota, PublicacionTipo tipoPublicacion, String descripcion);

    void crearPublicacionParaMascotaNueva(Mascota mascota, PublicacionTipo tipoPublicacion, String descripcion);

 */
}
