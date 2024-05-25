package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.excepcion.MascotaNoEncontrada;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioPublicacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioDetallePublicacionImpl implements ServicioDetallePublicacion {

    @Autowired
    private RepositorioPublicacion repositorioPublicacion;

    public ServicioDetallePublicacionImpl(RepositorioPublicacion repositorioPublicacion) {
        this.repositorioPublicacion = repositorioPublicacion;
    }

    @Override
    public Publicacion getPublicacion(Long id) throws Exception {
        Publicacion publicacion = this.repositorioPublicacion.getPublicacionPorId(id);
        if (publicacion == null) {
            throw new Exception("No existe publicacion con el id " + id + " o fue eliminada");
        }
        return publicacion;
    }
}
