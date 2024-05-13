package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.MascotaNoEncontrada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioPerfilMascotaImpl implements ServicioPerfilMascota {
    @Autowired
    private RepositorioMascota repositorioMascota;

    @Override
    public Mascota buscarMascotaPorIdPublicacion(Long idPublicacion) throws MascotaNoEncontrada {
        Mascota mascota = repositorioMascota.buscarMascotaPorId(idPublicacion);
        if (mascota == null) {
            throw new MascotaNoEncontrada("No existe mascota asociada a la publicacion o fue eliminada: " + idPublicacion);
        }
        return mascota;
    }
/*
    @Override
    public Mascota buscarMascotaPorIdPublicacion(Long idPublicacion) throws MascotaNoEncontrada {
        Mascota mascota = repositorioMascota.buscarMascotaPorIdPublicacion(idPublicacion);
        if (mascota == null) {
            throw new MascotaNoEncontrada("Mascota no encontrada con publicación ID: " + idPublicacion);
        }
        return mascota;
    }
 */
}
