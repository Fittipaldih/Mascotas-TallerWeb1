package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.PerdidoExeption;

import java.util.List;

public interface RepositorioPublicacion {
    void nuevaPublicacion(String nombre, String foto, String descripcion, Zona zona, TipoMascota tipoMascota, RazaMascota raza, ColorMascota color, Usuario usuario, EstadoMascota estado, TipoPublicacion tipoPublicacion);

    List<Publicacion> getPublicaciones();

    void guardarPerdido(Perdido perdido) throws PerdidoExeption;

    void eliminarPublicacion(Publicacion publicacion);

    Publicacion buscarPublicacion(Publicacion publicacion);

    List<Publicacion> buscarPublicacionesPorUsuario(Usuario usuario);

    List<Publicacion> buscarPublicacionesPorZona(Zona zona);

    List<Publicacion> buscarPublicacionesPorColorPelo(ColorMascota color);

    void modificarTelefonoPublicacion(Publicacion Publicacion);

    void crearPublicacionParaMascotaExistente(Mascota mascota, TipoPublicacion tipoPublicacion, String descripcion);

    void crearPublicacionParaMascotaNueva(Mascota mascota, TipoPublicacion tipoPublicacion,  String descripcion);
}
