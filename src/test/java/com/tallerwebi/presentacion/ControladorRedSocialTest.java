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

public class ControladorRedSocialTest {

    private ControladorRedSocial controladorRedSocial;

    @BeforeEach
    public void init() {
        this.controladorRedSocial = new ControladorRedSocial();
    }

    @Test
    public void queAlPresionarPublicarDevuelvaLaPublicar() {
        //ejecucion
        ModelAndView vista = this.controladorRedSocial.publicar();
        //verificacion
        assertThat(vista.getViewName(), equalToIgnoringCase("publicar"));
    }

    @Test
    public void queAlPresionarMascotasPerdidasDevuelvaLaVistaPerdidos() {
        //ejecucion
        ModelAndView vista = this.controladorRedSocial.mostrarPublicacionesPerdidos();
        //verificacion
        assertThat(vista.getViewName(), equalToIgnoringCase("perdidos"));
    }

    @Test
    public void queAlPresionarHistoriasDeMascotasDevuelvaLaHistorias() {
        //ejecucion
        ModelAndView vista = this.controladorRedSocial.mostrarPublicacionesHistorias();
        //verificacion
        assertThat(vista.getViewName(), equalToIgnoringCase("historias"));
    }

    @Test
    public void queAlPresionarDonacionesAMascotasDevuelvaLaDonaciones() {
        //ejecucion
        ModelAndView vista = this.controladorRedSocial.mostrarPublicacionesDonaciones();
        //verificacion
        assertThat(vista.getViewName(), equalToIgnoringCase("donaciones"));
    }

    @Test
    public void queDevuelvaUnModelAndViewComoObjeto() {
        // Ejecucion
        ModelAndView vistaDonacion = controladorRedSocial.mostrarPublicacionesDonaciones();
        // Verificacion
        assertThat(vistaDonacion.getClass().getSimpleName(), equalToIgnoringCase("ModelAndView"));
    }
}
