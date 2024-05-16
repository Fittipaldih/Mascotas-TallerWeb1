package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.Mascota;
import com.tallerwebi.dominio.excepcion.MascotaNoEncontrada;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioMascota;
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
            throw new MascotaNoEncontrada("No existe mascota asociada a la publicacion o fue eliminada: " + idPublicacion);
        }
        return mascota;
    }
 */
}