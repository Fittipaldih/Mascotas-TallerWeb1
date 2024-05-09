package com.tallerwebi.dominio;

import com.tallerwebi.infraestructura.RepositorioPublicacionesImp;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Service
public  class ServicioRedSocialImp implements ServicioRedSocial {
    private List<Publicacion> todasPublicaciones;
    private RepositorioPublicacionesImp repositorioPublicaciones;

    public ServicioRedSocialImp() {
        this.todasPublicaciones = new ArrayList<>();
        this.repositorioPublicaciones = new RepositorioPublicacionesImp();
    }


    @Override
    public List<Publicacion> filtrarPublicacion(TipoPublicacion tipoPublicacion, Zona zona, ColorPelo pelo, TiempoBusqueda tiempoBusqueda) {
        todasPublicaciones.addAll(todasPublicaciones());

        return busquedaCompleta(tipoPublicacion, zona, pelo, tiempoBusqueda);

    }

    @Override
    public List<Publicacion> todasPublicaciones() {
        List<Publicacion> todasPublicaciones = new ArrayList<>();
        todasPublicaciones.addAll(repositorioPublicaciones.getPublicaciones());

        return todasPublicaciones;
    }

    @Override
    public List<Publicacion> filtrarTipo(TipoPublicacion tipoPublicacion) {
        this.todasPublicaciones.addAll(repositorioPublicaciones.getPublicaciones());
        return busquedaSegunTipo(tipoPublicacion);
    }

    @Override
    public List<Publicacion> filtrarZona(Zona zona) {
        this.todasPublicaciones.addAll(repositorioPublicaciones.getPublicaciones());
        return busquedaPorZona(zona);
    }

    @Override
    public List<Publicacion> filtrarColor(ColorPelo pelo) {
        this.todasPublicaciones.addAll(repositorioPublicaciones.getPublicaciones());
        return busquedaPorPelo(pelo);
    }


    @Override
    public List<Publicacion> filtrarTiempoBusqueda(TiempoBusqueda tiempoBusqueda) {
        this.todasPublicaciones.addAll(repositorioPublicaciones.getPublicaciones());
        return busquedaPorTiempo(tiempoBusqueda);
    }

//---------------------------------------------------------------

    private List<Publicacion> busquedaCompleta(TipoPublicacion tipoPublicacion, Zona zona, ColorPelo pelo, TiempoBusqueda tiempoBusqueda) {
        List<Publicacion> publicacionesFiltradas = new ArrayList<>();
        ModelAndView mav = new ModelAndView();
        ModelMap mp = new ModelMap();

        for (Publicacion publicacion : todasPublicaciones) {
            if (publicacion.getTipoPublicacion().equals(tipoPublicacion)
                    && publicacion.getZona().equals(zona)
                    && publicacion.getColorPelo().equals(pelo)
                    && publicacion.getTiempoBusqueda().equals(tiempoBusqueda)) {

                publicacionesFiltradas.add(publicacion);
            }
        }

        return publicacionesFiltradas;
    }

    private List<Publicacion> busquedaSegunTipo(TipoPublicacion tipoPublicacion) {
        List<Publicacion> publicacionesSegunTipo = new ArrayList<>();

        for (Publicacion publicacion : todasPublicaciones) {
            if (publicacion.getTipoPublicacion().equals(tipoPublicacion)) {
                publicacionesSegunTipo.add(publicacion);
            }
        }
        return publicacionesSegunTipo;
    }

    private List<Publicacion> busquedaPorZona(Zona zona) {
        List<Publicacion> publicacionesSegunZona= new ArrayList<>();

        for (Publicacion publicacion : todasPublicaciones) {
            if (publicacion.getZona().equals(zona)) {
                publicacionesSegunZona.add(publicacion);
            }
        }
        return publicacionesSegunZona;
    }

    private List<Publicacion> busquedaPorPelo(ColorPelo pelo) {
        List<Publicacion> publicacionesSegunPelo = new ArrayList<>();

        for (Publicacion publicacion : todasPublicaciones) {
            if (publicacion.getColorPelo().equals(pelo)) {
                publicacionesSegunPelo.add(publicacion);
            }
        }
        return publicacionesSegunPelo;
    }

    private List<Publicacion> busquedaPorTiempo(TiempoBusqueda tiempoBusqueda) {
        List<Publicacion> publicacionesSegunTiempo= new ArrayList<>();

        for (Publicacion publicacion : todasPublicaciones) {
            if (publicacion.getTiempoBusqueda().equals(tiempoBusqueda)) {
                publicacionesSegunTiempo.add(publicacion);
            }
        }
        return publicacionesSegunTiempo;
    }
}

