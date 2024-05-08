package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.ControladorRedSocial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioReSocialTest {

    private ServicioRedSocial servicioRedSocialImp;

    @BeforeEach
    public void init() {
        this.servicioRedSocialImp = new ServicioRedSocialImp();
    }

    @Test
    public void queSeFiltrePorTodosLosCampos() {
        // preparacion
        TipoPublicacion tipoPublicacion= mock(TipoPublicacion.class).PERDIDOS;
        Zona zonaMock = mock(Zona.class).OESTE;
        ColorPelo  colorPeloMock = mock(ColorPelo.class).MARRON;
        TiempoBusqueda tiempoBusquedaMock = mock(TiempoBusqueda.class).HORA;
        String descripcion = "Se perdio";
        List<Publicacion> filtrados = new ArrayList<>();
        int cantidad = 1;


        //ejecucion
        filtrados.addAll(this.servicioRedSocialImp.filtrarPublicacion(tipoPublicacion,zonaMock, colorPeloMock, tiempoBusquedaMock));

        // verificacion
        assertThat(filtrados.size(),equalTo(cantidad));

    }

    @Test
    public void queSeFiltreUnicamentePorTipoPublicacion() {
        // preparacion
        TipoPublicacion tipoPublicacion= mock(TipoPublicacion.class).PERDIDOS;
        List<Publicacion> filtrados = new ArrayList<>();
        int cantidad = 2;

        //ejecucion
        filtrados.addAll(this.servicioRedSocialImp.filtrarTipo(tipoPublicacion));

        // verificacion
        assertThat(filtrados.size(),equalTo(cantidad));

    }

    @Test
    public void queSeFiltreUnicamentePorZona() {
        // preparacion
        Zona zona = mock(Zona.class).OESTE;
        List<Publicacion> filtrados = new ArrayList<>();
        int cantidad = 3;

        //ejecucion
        filtrados.addAll(this.servicioRedSocialImp.filtrarZona(zona));

        // verificacion
        assertThat(filtrados.size(),equalTo(cantidad));

    }

    @Test
    public void queSeFiltreUnicamentePorColorPelo() {
        // preparacion
        ColorPelo colorPelo = ColorPelo.BLANCO;
        List<Publicacion> filtrados = new ArrayList<>();
        int cantidad = 1;

        //ejecucion
        filtrados.addAll(this.servicioRedSocialImp.filtrarColor(colorPelo));

        // verificacion
        assertThat(filtrados.size(),equalTo(cantidad));

    }

    @Test
    public void queSeFiltreUnicamentePorTiempoBusqueda() {
        // preparacion
        TiempoBusqueda tiempoBusqueda = TiempoBusqueda.HORA;
        List<Publicacion> filtrados = new ArrayList<>();
        int cantidad = 3;

        //ejecucion
        filtrados.addAll(this.servicioRedSocialImp.filtrarTiempoBusqueda(tiempoBusqueda));

        // verificacion
        assertThat(filtrados.size(),equalTo(cantidad));

    }
}
