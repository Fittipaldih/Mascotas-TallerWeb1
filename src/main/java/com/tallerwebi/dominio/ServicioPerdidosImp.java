package com.tallerwebi.dominio;


import com.tallerwebi.infraestructura.RepositorioMascotaImpl;
import com.tallerwebi.infraestructura.RepositorioPublicacionImpl;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioPerdidosImp implements ServicioPerdidos{
    private RepositorioPublicacionImpl repositorioPublicacionesImp;
    private RepositorioMascotaImpl repositorioMascotaImp;
    private SessionFactory sessionFactory;

    public ServicioPerdidosImp() {
        this.repositorioPublicacionesImp = new RepositorioPublicacionImpl(sessionFactory, repositorioMascotaImp);
    }

    @Override
    public List<Perdido> filtrarPublicacionPerdidos(Zona zona, TiempoPublicacion tiempoPublicacion, String colorPelo) {
        List<Perdido> perdidos = new ArrayList<>();
        perdidos.addAll(filtrarPerdidos(this.repositorioPublicacionesImp.getPublicaciones(),zona,tiempoPublicacion,colorPelo));
        return perdidos;
    }

    private List<Perdido> filtrarPerdidos(List<Publicacion> publicaciones, Zona zona, TiempoPublicacion tiempoPublicacion, String colorPelo) {
        List<Perdido>  perdidosFiltrados = new ArrayList<>();
        perdidosFiltrados.addAll(obtenerSoloPerdidos(publicaciones));

        for (Perdido perdido : perdidosFiltrados) {
            if (perdido.getZona().equals(zona) && perdido.getTiempoBusqueda().equals(tiempoPublicacion) && perdido.getColorPelo().equals(colorPelo)) {
                perdidosFiltrados.add(perdido);
            }
        }
        return perdidosFiltrados;
    }

    private static List<Perdido> obtenerSoloPerdidos(List<Publicacion> publicaciones) {
        List<Perdido> perdidos = new ArrayList<>();

        for (Publicacion publicacion : publicaciones) {
            if (publicacion instanceof Perdido && publicacion.getTipoPublicacion().equals(TipoPublicacion.PERDI_MI_PERRO)) {
                perdidos.add((Perdido) publicacion);
            }
        }
        return perdidos;
    }
}
