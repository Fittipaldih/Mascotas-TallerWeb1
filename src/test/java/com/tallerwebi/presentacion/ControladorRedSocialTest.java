package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    private ServicioRedSocial servicioRedSocialMock;

    @BeforeEach
    public void init() {
     this.controladorRedSocial = new ControladorRedSocial();
        servicioRedSocialMock = mock(ServicioRedSocial.class);
    }

    @Test
    public void queDevuelvaLaMismaVistaConPublicacionesFiltradas(){
        // preparacion
        ModelMap model = new ModelMap();
        TipoPublicacion tipo = TipoPublicacion.PERDIDOS;
        List<Publicacion> publicaciones = new ArrayList<>();

        // ejecucion
        publicaciones.add(new Publicacion(TipoPublicacion.PERDIDOS, Zona.OESTE, TiempoBusqueda.HORA,ColorPelo.NEGRO, "Se perdio Manolito en Av.Martin Fierro"));
        publicaciones.add(new Publicacion(TipoPublicacion.PERDIDOS, Zona.OESTE, TiempoBusqueda.HORA,ColorPelo.MARRON, "Se perdio"));
        model.put("publicacionesFiltroTipo", publicaciones);
        ModelAndView mav = new ModelAndView("vista", model);

        when(this.servicioRedSocialMock.filtrarTipo(tipo)).thenReturn(publicaciones);

        //verificacion
        assertThat(mav.getViewName(), equals("vista"));
    }



}
