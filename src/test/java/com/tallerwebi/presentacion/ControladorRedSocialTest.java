package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.mockito.Mockito.mock;

@Controller

public class ControladorRedSocialTest {

    private ControladorRedSocial controladorRedSocial;
    private ServicioRedSocial servicioRedSocialMock;

    @BeforeEach
    public void init() {
     this.controladorRedSocial = new ControladorRedSocial();
        servicioRedSocialMock = mock(ServicioRedSocial.class);
    }

    @Test
    public void queAlFiltrarElControladorNosMuestreLaVistaDeFiltrados() {
        // preparacion
        TipoPublicacion tipoPublicacion= mock(TipoPublicacion.class).PERDIDOS;
        Zona zonaMock = mock(Zona.class).OESTE;
        ColorPelo  colorPeloMock = mock(ColorPelo.class).MARRON;
        TiempoBusqueda tiempoBusquedaMock = mock(TiempoBusqueda.class).HORA;


        //ejecucion
        ModelAndView vista = this.controladorRedSocial.publicacionesFiltradas(tipoPublicacion,zonaMock, colorPeloMock, tiempoBusquedaMock);

        // verificacion
        assertThat(vista.getViewName(), equalToIgnoringCase("redirect:/red-social"));

    }
}
