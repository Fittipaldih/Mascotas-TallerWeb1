package com.tallerwebi.dominio.servicios;


import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioPublicacion;
import com.tallerwebi.dominio.servicios.interfaces.ServicioPublicacionPerdido;
import com.tallerwebi.infraestructura.RepositorioPublicacionImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioPublicacionPerdidoImpl implements ServicioPublicacionPerdido {

    private final RepositorioPublicacion repositorioPublicaciones;

    @Autowired
    public ServicioPublicacionPerdidoImpl(RepositorioPublicacion repositorioPublicacion) {
        this.repositorioPublicaciones = repositorioPublicacion;
    }

    @Override
    public List<PublicacionPerdido> filtrarPublicacionPerdidos(Zona zona, PublicacionTiempo tiempoPublicacion, String colorPelo) {
        return new ArrayList<>(filtrarPerdidos(this.repositorioPublicaciones.getPublicaciones(), zona, tiempoPublicacion, colorPelo));
    }

    private List<PublicacionPerdido> filtrarPerdidos(List<Publicacion> publicaciones, Zona zona, PublicacionTiempo tiempoPublicacion, String colorPelo) {
        return new ArrayList<>(obtenerSoloPerdidos(publicaciones));
    }

    private static List<PublicacionPerdido> obtenerSoloPerdidos(List<Publicacion> publicaciones) {
        List<PublicacionPerdido> perdidos = new ArrayList<>();

        for (Publicacion publicacion : publicaciones) {
            if (publicacion instanceof PublicacionPerdido && publicacion.getTipoPublicacion().equals(PublicacionTipo.BUSCADO_POR_DUENIO)) {
                perdidos.add((PublicacionPerdido) publicacion);
            }
        }
        return perdidos;
    }
}
