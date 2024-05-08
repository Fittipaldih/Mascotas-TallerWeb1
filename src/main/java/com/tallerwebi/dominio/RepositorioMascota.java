package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioMascota {
    Mascota buscarMascotaPorId(Integer idMascota);

    List<Mascota> buscarMascotasPorZona(String zona);

    void guardarMascota(Mascota mascota);

    void modificarMascota(Mascota mascota);

    void eliminarMascota(Mascota mascota);
}
