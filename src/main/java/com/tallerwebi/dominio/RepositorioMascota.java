package com.tallerwebi.dominio;

public interface RepositorioMascota {
    Mascota buscarMascotaPorId(Integer idMascota);

    void guardarMascota(Mascota mascota);

    void modificarMascota(Mascota mascota);

    void eliminarMascota(Mascota mascota);
}
