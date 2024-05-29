package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.Mascota;
import com.tallerwebi.dominio.excepcion.MascotaNoEncontrada;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioMascota;
import com.tallerwebi.dominio.servicios.interfaces.ServicioPerfilMascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioPerfilMascotaImpl implements ServicioPerfilMascota {
    @Autowired
    private RepositorioMascota repositorioMascota;

    public ServicioPerfilMascotaImpl(RepositorioMascota repositorioMascota) {
        this.repositorioMascota = repositorioMascota;
    }

    @Override
    public Mascota buscarMascotaPorId(Long id) throws MascotaNoEncontrada {
        Mascota mascota = repositorioMascota.buscarMascotaPorId(id);
        if (mascota == null) {
            throw new MascotaNoEncontrada("No existe mascota asociada a la publicacion o fue eliminada: " + id);
        }
        return mascota;
    }
}
