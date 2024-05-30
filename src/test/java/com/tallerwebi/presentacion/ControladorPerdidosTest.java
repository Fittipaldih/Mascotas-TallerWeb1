package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.servicios.ServicioPublicacionPerdidoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

public class ControladorPerdidosTest {

    @InjectMocks
    private ControladorPerdidos controladorPerdidos;

    @Mock
    private ServicioPublicacionPerdidoImpl servicioPerdidosImp;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void quePuedaFiltrarPublicacionesDePerdidosCuandoSePresionaElBoton() {
        //Datos de prueba
        Zona zona = Zona.SUR;
        PublicacionTiempo tiempoPublicacion = PublicacionTiempo.DIA;
        String colorPelo = "Marrón";
        List<PublicacionPerdido> publicacionesFiltradas = new ArrayList<>();
        publicacionesFiltradas.add(new PublicacionPerdido());

        //Preparacion
        when(servicioPerdidosImp.filtrarPublicacionPerdidos(zona, tiempoPublicacion, colorPelo)).thenReturn(publicacionesFiltradas);

        //Ejecucion
        ModelAndView modelAndView = controladorPerdidos.filtrarPublicacion(zona, tiempoPublicacion, colorPelo);

        //Verificacion
        assertThat(modelAndView.getViewName(), is(equalTo("redirect:/perdidos")));
        assertThat(modelAndView.getModel(), hasKey("perdidosFiltrados"));

        List<PublicacionPerdido> resultList = (List<PublicacionPerdido>) modelAndView.getModel().get("perdidosFiltrados");
        assertThat(resultList, hasSize(1));
        verify(servicioPerdidosImp, times(1)).filtrarPublicacionPerdidos(zona, tiempoPublicacion, colorPelo);
    }

    @Test
    public void queTengaDosElementosYPuedaFiltrarPublicacionesDePerdidosCuandoSePresionaElBoton() {
        // Datos de prueba
        Zona zona = Zona.NORTE;
        PublicacionTiempo tiempoPublicacion = PublicacionTiempo.SEMANA;
        String colorPelo = "Gris";
        List<PublicacionPerdido> publicacionesFiltradas = new ArrayList<>();
        publicacionesFiltradas.add(new PublicacionPerdido());
        publicacionesFiltradas.add(new PublicacionPerdido());

        // Configuración del mock
        when(servicioPerdidosImp.filtrarPublicacionPerdidos(zona, tiempoPublicacion, colorPelo)).thenReturn(publicacionesFiltradas);

        // Ejecución
        ModelAndView modelAndView = controladorPerdidos.filtrarPublicacion(zona, tiempoPublicacion, colorPelo);

        // Verificación
        assertThat(modelAndView.getViewName(), is(equalTo("redirect:/perdidos")));
        List<PublicacionPerdido> resultList = (List<PublicacionPerdido>) modelAndView.getModel().get("perdidosFiltrados");
        assertThat(resultList, hasSize(2));
        verify(servicioPerdidosImp, times(1)).filtrarPublicacionPerdidos(zona, tiempoPublicacion, colorPelo);
    }

    @Test
    public void queNoPuedaFiltrarPublicaciones() {
        //Datos de prueba
        Zona zona = Zona.SUR;
        PublicacionTiempo tiempoPublicacion = PublicacionTiempo.SEMANA;
        String colorPelo = "Marrón";
        List<PublicacionPerdido> publicacionesFiltradas = new ArrayList<>();

        //Preparacion
        when(servicioPerdidosImp.filtrarPublicacionPerdidos(zona, tiempoPublicacion, colorPelo)).thenReturn(publicacionesFiltradas);

        //Ejecucion
        ModelAndView modelAndView = controladorPerdidos.filtrarPublicacion(zona, tiempoPublicacion, colorPelo);

        //Verificacion
        assertThat(modelAndView.getViewName(), is(equalTo("redirect:/perdidos")));
        assertThat(modelAndView.getModel(), hasKey("perdidosFiltrados"));
        assertThat(modelAndView.getModel().get("perdidosFiltrados"), instanceOf(List.class));
        List<PublicacionPerdido> resultList = (List<PublicacionPerdido>) modelAndView.getModel().get("perdidosFiltrados");
        assertThat(resultList, is(empty()));
        verify(servicioPerdidosImp, times(1)).filtrarPublicacionPerdidos(zona, tiempoPublicacion, colorPelo);
    }
}
