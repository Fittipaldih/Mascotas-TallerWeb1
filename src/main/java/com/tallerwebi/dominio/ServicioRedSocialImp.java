//package com.tallerwebi.dominio;
//
//import com.tallerwebi.infraestructura.RepositorioPublicacionesImp;
//import org.springframework.stereotype.Service;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public  class ServicioRedSocialImp implements ServicioRedSocial {
//    private List<Publicacion> todasPublicaciones;
//    private RepositorioPublicacionesImp repositorioPublicaciones;
//
//    public ServicioRedSocialImp() {
//        this.todasPublicaciones = new ArrayList<>();
//        this.repositorioPublicaciones = new RepositorioPublicacionesImp();
//    }
//
//
//    @Override
//    public List<Publicacion> filtrarPublicacion(TipoPublicacion tipoPublicacion, Zona zona) {
//        todasPublicaciones.addAll(todasPublicaciones());
//
//        return busquedaCompleta(tipoPublicacion, zona);
//
//    }
//
//    @Override
//    public List<Publicacion> todasPublicaciones() {
//        List<Publicacion> todasPublicaciones = new ArrayList<>();
//        todasPublicaciones.addAll(repositorioPublicaciones.getPublicaciones());
//
//        return todasPublicaciones;
//    }
//
//    @Override
//    public List<Publicacion> filtrarTipo(TipoPublicacion tipoPublicacion) {
//        this.todasPublicaciones.addAll(repositorioPublicaciones.getPublicaciones());
//        return busquedaSegunTipo(tipoPublicacion);
//    }
//
//    @Override
//    public List<Publicacion> filtrarZona(Zona zona) {
//        this.todasPublicaciones.addAll(repositorioPublicaciones.getPublicaciones());
//        return busquedaPorZona(zona);
//    }
//
//    @Override
//    public List<Publicacion> filtrarColor(ColorPelo pelo) {
//        this.todasPublicaciones.addAll(repositorioPublicaciones.getPublicaciones());
//        return busquedaPorPelo(pelo);
//    }
//
//
////---------------------------------------------------------------
//
//    // COMO HAGO LA BUSQUEDA COMPLETA SI NO COMPARTEN TODOS LOS ATRIBUTOS
//    private List<Publicacion> busquedaCompleta(TipoPublicacion tipoPublicacion, Zona zona) {
//        List<Publicacion> publicacionesFiltradas = new ArrayList<>();
//        ModelAndView mav = new ModelAndView();
//        ModelMap mp = new ModelMap();
//
//        for (Publicacion publicacion : todasPublicaciones) {
//            if (publicacion.getTipoPublicacion().equals(tipoPublicacion)
//                    && publicacion.getZona().equals(zona)) {
//
//                publicacionesFiltradas.add(publicacion);
//            }
//        }
//
//        return publicacionesFiltradas;
//    }
//
//    private List<Publicacion> busquedaSegunTipo(TipoPublicacion tipoPublicacion) {
//        List<Publicacion> publicacionesSegunTipo = new ArrayList<>();
//
//        for (Publicacion publicacion : todasPublicaciones) {
//            if (publicacion.getTipoPublicacion().equals(tipoPublicacion)) {
//                publicacionesSegunTipo.add(publicacion);
//            }
//        }
//        return publicacionesSegunTipo;
//    }
//
//    private List<Publicacion> busquedaPorZona(Zona zona) {
//        List<Publicacion> publicacionesSegunZona= new ArrayList<>();
//
//        for (Publicacion publicacion : todasPublicaciones) {
//            if (publicacion.getZona().equals(zona)) {
//                publicacionesSegunZona.add(publicacion);
//            }
//        }
//        return publicacionesSegunZona;
//    }
//
//    private List<Publicacion> busquedaPorPelo(ColorPelo pelo) {
//        List<Publicacion> publicacionesSegunPelo = new ArrayList<>();
//
//        for (Publicacion publicacion : todasPublicaciones) {
//            if (publicacion.getColorPelo().equals(pelo)) {
//                publicacionesSegunPelo.add(publicacion);
//            }
//        }
//        return publicacionesSegunPelo;
//    }

//
//    @Test
//    public void queAlPresionarFiltrarDevuelvaLaVistaRedSocialConLasPublicacionesFiltradas() {
//        // preparacion
//        TipoPublicacion tipoPublicacion= mock(TipoPublicacion.class).PERDIDOS;
//        Zona zonaMock = mock(Zona.class).OESTE;
//        ColorPelo  colorPeloMock = mock(ColorPelo.class).MARRON;
//        TiempoBusqueda tiempoBusquedaMock = mock(TiempoBusqueda.class).HORA;
//        List<Publicacion> publicaciones = new ArrayList<>();
//        ModelMap modelMap = new ModelMap();
//
//        //ejecucion
//        ModelAndView vista = this.controladorRedSocial.publicacionesFiltradas(TipoPublicacion.PERDIDOS, zonaMock, colorPeloMock, tiempoBusquedaMock);
//        publicaciones.add(new Publicacion(TipoPublicacion.PERDIDOS, Zona.OESTE,ColorPelo.MARRON, "Se perdio"));
//        modelMap.put("filtro",publicaciones);
//
//        //verificacion
//        assertThat(vista.getViewName(), equalToIgnoringCase("red-social"));
//        assertThat(modelMap.get("filtro"), equalTo(publicaciones));
//    }


//    @Test
//    public void queAlPresionarFiltrarDevuelvaLaVistaRedSocial() {
//        // preparacion
//        TipoPublicacion tipoPublicacion= mock(TipoPublicacion.class).PERDIDOS;
//        Zona zonaMock = mock(Zona.class).OESTE;
//        ColorPelo  colorPeloMock = mock(ColorPelo.class).MARRON;
//        TiempoBusqueda tiempoBusquedaMock = mock(TiempoBusqueda.class).HORA;
//
//        //ejecucion
//        ModelAndView vista = this.controladorRedSocial.publicacionesFiltradas(TipoPublicacion.PERDIDOS, zonaMock, colorPeloMock, tiempoBusquedaMock);
//
//        //verificacion
//        assertThat(vista.getViewName(), equalToIgnoringCase("red-social"));
//    }



//}

