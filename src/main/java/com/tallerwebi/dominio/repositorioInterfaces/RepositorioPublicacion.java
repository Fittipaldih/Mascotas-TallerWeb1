package com.tallerwebi.dominio.repositorioInterfaces;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.PerdidoException;
import com.tallerwebi.dominio.excepcion.PublicacionInexistenteExeption;

import java.util.List;

public interface RepositorioPublicacion {
    void guardarHistoria(PublicacionHistoria historia);

    void guardarPerdido(PublicacionPerdido perdido);

    void guardarDonacion(PublicacionDonacion donacion);

    List<Publicacion> getPublicaciones();

    List<Publicacion> getPublicacionesPorNombreMascota(String nombre);

    List<Publicacion> getPublicacionesPorTipoPublicacion(PublicacionTipo tipo);

    List<Publicacion> getPublicacionesPorZona(Zona zona);

    List<PublicacionHistoria> getPublicacionesHistoriaPorZona(Zona zona);

    List<Publicacion> getPublicacionesPorZonaYNombreMascota(Zona zona, String nombre);

    void eliminarPublicacion(Publicacion publicacion);

    void eliminarPublicacionPorId(Long id);

    void eliminarComentarioPorId(Long id);

    Publicacion getPublicacionPorId(Long id);

    void editarHistoria(Long idPublicacion, String titular, String nombreMascota, Zona zona,
                        String descripcion, byte[] imagenBytes) ;

    void editarPerdido(Long idPublicacion, String nombreMascota, Long telefonoContacto, String nombreContacto,
                       MascotaColor mascotaColor, MascotaRaza mascotaRaza, PublicacionTipo tipoPublicacion,
                       Zona zona, String descripcion, String direccion, byte[] imagen) ;

    void editarDonacion(Long idPublicacion, String nombreMascota, Double montoACubrir, Zona zona,
                        String descripcion, byte[] imagenBytes) ;
/*
   void nuevaPublicacion(String nombre, String foto, String descripcion, Zona zona, MascotaTipo tipoMascota, MascotaRaza raza, MascotaColor color, Usuario usuario, MascotaEstado estado, PublicacionTipo tipoPublicacion);

    Publicacion buscarPublicacion(Publicacion publicacion);

    List<Publicacion> buscarPublicacionesPorUsuario(Usuario usuario);

    List<Publicacion> buscarPublicacionesPorColorPelo(MascotaColor color);

    void modificarTelefonoPublicacion(Publicacion Publicacion);

    void crearPublicacionParaMascotaExistente(Mascota mascota, PublicacionTipo tipoPublicacion, String descripcion);

    void crearPublicacionParaMascotaNueva(Mascota mascota, PublicacionTipo tipoPublicacion, String descripcion);

 */
}
