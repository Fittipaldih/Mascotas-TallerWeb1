package com.tallerwebi.dominio;

public interface RepositorioMascota {
    Mascota buscarMascotaPorId(Long idMascota);
    void guardarMascota(Mascota mascota);
    void modificarMascota(Mascota mascota);
    void eliminarMascota(Mascota mascota);
}
