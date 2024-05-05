package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioRedSocial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

@Controller

public class ControladorRedSocialTest {

    private ControladorRedSocial controladorRedSocial;
    private ServicioRedSocial servicioRedSocial;

    @BeforeEach
    public void init() {
        // preparacion
     this.controladorRedSocial = new ControladorRedSocial();
     // debe recibir todos los parametros del filtro
//     this.servicioRedSocial = new ServicioRedSocial();
    }

    @Test
    public void queAlFiltrarElControladorNosMuestreLaVistaDeFiltrados() {
        //ejecucion
        ModelAndView vista = this.controladorRedSocial.publicacionesFiltradas();
    }
}
