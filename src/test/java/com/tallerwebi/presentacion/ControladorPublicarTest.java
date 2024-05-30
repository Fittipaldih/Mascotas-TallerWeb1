package com.tallerwebi.presentacion;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

public class ControladorPublicarTest {

    private final ControladorPublicar controladorPublicar;

    public ControladorPublicarTest() {
        this.controladorPublicar = new ControladorPublicar();
    }

    @Test
    public void queAlTocarElBotonDeMascotaPerdidaDevuelvaLaVistaPublicarPerdido() {
        //ejecucion
        ModelAndView vistaPerdido = this.controladorPublicar.irPublicarPerdido();
        //verificacion
        assertThat(vistaPerdido.getViewName(), equalToIgnoringCase("publicarPerdido"));
    }

    @Test
    public void queAlTocarElBotonDeHistoriaDeMascotaDevuelvaLaVistaPublicarHistoria() {
        //ejecucion
        ModelAndView vistaHistoria = this.controladorPublicar.irPublicarHistoria();
        //verificacion
        assertThat(vistaHistoria.getViewName(), equalToIgnoringCase("publicarHistoria"));
    }

    @Test
    public void queAlTocarElBotonDeDonacionAMascotaDevuelvaLaVistaPublicarDonacion() {
        //ejecucion
        ModelAndView vistaHistoria = this.controladorPublicar.irPublicarHistoria();
        //verificacion
        assertThat(vistaHistoria.getViewName(), equalToIgnoringCase("publicarHistoria"));
    }
    @Test
    public void queDevuelvaUnModelAndViewComoObjeto() {
        // Ejecucion
        ModelAndView vistaDonacion = controladorPublicar.irPublicarDonacion();
        // Verificacion
        assertThat(vistaDonacion.getClass().getSimpleName(), equalToIgnoringCase("ModelAndView"));
    }

}
