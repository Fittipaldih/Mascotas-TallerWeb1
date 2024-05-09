package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Controller

public class ControladorRedSocialTest {

    private ControladorRedSocial controladorRedSocial;
    private ServicioRedSocialImp servicioRedSocialMock;

    @BeforeEach
    public void init() {
        servicioRedSocialMock = mock(ServicioRedSocialImp.class);
        this.controladorRedSocial = new ControladorRedSocial(servicioRedSocialMock);
    }

    @Test
    public void queAlPresionarFiltrarDevuelvaLaVistaRedSocial() {
        // preparacion
        TipoPublicacion tipoPublicacion= mock(TipoPublicacion.class).PERDIDOS;
        Zona zonaMock = mock(Zona.class).OESTE;
        ColorPelo  colorPeloMock = mock(ColorPelo.class).MARRON;
        TiempoBusqueda tiempoBusquedaMock = mock(TiempoBusqueda.class).HORA;

        //ejecucion
        ModelAndView vista = this.controladorRedSocial.publicacionesFiltradas(TipoPublicacion.PERDIDOS, zonaMock, colorPeloMock, tiempoBusquedaMock);

        //verificacion
        assertThat(vista.getViewName(), equalToIgnoringCase("red-social"));
    }

    @Test
    public void queAlPresionarFiltrarDevuelvaLaVistaRedSocialConLasPublicacionesFiltradas() {
        // preparacion
        TipoPublicacion tipoPublicacion= mock(TipoPublicacion.class).PERDIDOS;
        Zona zonaMock = mock(Zona.class).OESTE;
        ColorPelo  colorPeloMock = mock(ColorPelo.class).MARRON;
        TiempoBusqueda tiempoBusquedaMock = mock(TiempoBusqueda.class).HORA;
        List<Publicacion> publicaciones = new ArrayList<>();
        ModelMap modelMap = new ModelMap();

        //ejecucion
        ModelAndView vista = this.controladorRedSocial.publicacionesFiltradas(TipoPublicacion.PERDIDOS, zonaMock, colorPeloMock, tiempoBusquedaMock);
        publicaciones.add(new Publicacion(TipoPublicacion.PERDIDOS, Zona.OESTE, TiempoBusqueda.HORA,ColorPelo.MARRON, "Se perdio"));
        modelMap.put("filtro",publicaciones);

        //verificacion
        assertThat(vista.getViewName(), equalToIgnoringCase("red-social"));
        assertThat(modelMap.get("filtro"), equalTo(publicaciones));
    }

    @Test
    public void queAlPresionarPublicarDevuelvaLaPublicar() {
        //ejecucion
        ModelAndView vista = this.controladorRedSocial.publicar();

        //verificacion
        assertThat(vista.getViewName(), equalToIgnoringCase("publicar"));
    }
}
