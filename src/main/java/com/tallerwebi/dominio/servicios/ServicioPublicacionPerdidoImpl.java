package com.tallerwebi.dominio.servicios;


import com.tallerwebi.dominio.*;
import com.tallerwebi.infraestructura.RepositorioMascotaImpl;
import com.tallerwebi.infraestructura.RepositorioPublicacionImpl;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioPublicacionPerdidoImpl implements ServicioPublicacionPerdido {
    private RepositorioPublicacionImpl repositorioPublicacionesImp;
    private RepositorioMascotaImpl repositorioMascotaImp;
    private SessionFactory sessionFactory;

    public ServicioPublicacionPerdidoImpl() {
        this.repositorioPublicacionesImp = new RepositorioPublicacionImpl(sessionFactory, repositorioMascotaImp);
    }

    @Override
    public List<PublicacionPerdido> filtrarPublicacionPerdidos(Zona zona, PublicacionTiempo tiempoPublicacion, String colorPelo) {
        List<PublicacionPerdido> perdidos = new ArrayList<>();
        perdidos.addAll(filtrarPerdidos(this.repositorioPublicacionesImp.getPublicaciones(),zona,tiempoPublicacion,colorPelo));
        return perdidos;
    }

    private List<PublicacionPerdido> filtrarPerdidos(List<Publicacion> publicaciones, Zona zona, PublicacionTiempo tiempoPublicacion, String colorPelo) {
        List<PublicacionPerdido>  perdidosFiltrados = new ArrayList<>();
        perdidosFiltrados.addAll(obtenerSoloPerdidos(publicaciones));

        for (PublicacionPerdido perdido : perdidosFiltrados) {
            if (perdido.getZona().equals(zona) && perdido.getTiempoBusqueda().equals(tiempoPublicacion) && perdido.getColorPelo().equals(colorPelo)) {
                perdidosFiltrados.add(perdido);
            }
        }
        return perdidosFiltrados;
    }

    private static List<PublicacionPerdido> obtenerSoloPerdidos(List<Publicacion> publicaciones) {
        List<PublicacionPerdido> perdidos = new ArrayList<>();

        for (Publicacion publicacion : publicaciones) {
            if (publicacion instanceof PublicacionPerdido && publicacion.getTipoPublicacion().equals(PublicacionTipo.PERDI_MI_PERRO)) {
                perdidos.add((PublicacionPerdido) publicacion);
            }
        }
        return perdidos;
    }
}
