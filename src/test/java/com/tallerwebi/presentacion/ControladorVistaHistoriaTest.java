package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.PublicacionHistoria;
import com.tallerwebi.dominio.Zona;
import com.tallerwebi.dominio.servicios.ServicioPublicacionHistoriaImpl;
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

public class ControladorVistaHistoriaTest {

    @InjectMocks
    private ControladorVistaHistoria controladorVistaHistoria;
    @Mock
    private ServicioPublicacionHistoriaImpl servicioHistorias;
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void quePuedaFiltrarHistoriasPorZona() {
        //Datos de prueba
        Zona zona = Zona.CABA;
        List<PublicacionHistoria> historiasFiltradas = new ArrayList<>();
        historiasFiltradas.add(new PublicacionHistoria());
        historiasFiltradas.add(new PublicacionHistoria());
        //Preparacion
        when(servicioHistorias.filtrarHistoriasPorZona(zona)).thenReturn(historiasFiltradas);
        //Ejecucion
        ModelAndView modelAndView = controladorVistaHistoria.filtrarHistorias(zona);
        //Verificacion
        assertThat(modelAndView.getViewName(), is(equalTo("historias")));
        assertThat(modelAndView.getModel(), hasKey("historias"));
        List<PublicacionHistoria> resultList = (List<PublicacionHistoria>) modelAndView.getModel().get("historias");
        assertThat(resultList, hasSize(2));
        verify(servicioHistorias, times(1)).filtrarHistoriasPorZona(zona);
    }

    @Test
    public void queDevuelvaListaVaciaSiNoHayHistoriasParaLaZona() {
        //Datos de prueba
        Zona zona = Zona.NORTE;
        List<PublicacionHistoria> historiasFiltradas = new ArrayList<>();
        //Preparacion
        when(servicioHistorias.filtrarHistoriasPorZona(zona)).thenReturn(historiasFiltradas);
        //Ejecucion
        ModelAndView modelAndView = controladorVistaHistoria.filtrarHistorias(zona);
        //Verificacion
        assertThat(modelAndView.getViewName(), is(equalTo("historias")));
        assertThat(modelAndView.getModel(), hasKey("historias"));
        List<PublicacionHistoria> resultList = (List<PublicacionHistoria>) modelAndView.getModel().get("historias");
        assertThat(resultList, is(empty()));
        // Verificar que el método se llamó con los parámetros correctos
        verify(servicioHistorias, times(1)).filtrarHistoriasPorZona(zona);
    }

    @Test
    public void queManejeElErrorCorrectamenteCuandoZonaEsNull() {
        //Datos de prueba
        Zona zona = null;
        List<PublicacionHistoria> historiasFiltradas = new ArrayList<>();
        //Preparacion
        when(servicioHistorias.filtrarHistoriasPorZona(zona)).thenReturn(historiasFiltradas);
        //Ejecucion
        ModelAndView modelAndView = controladorVistaHistoria.filtrarHistorias(zona);
        //Verificacion
        assertThat(modelAndView.getViewName(), is(equalTo("historias")));
        assertThat(modelAndView.getModel(), hasKey("historias"));
        List<PublicacionHistoria> resultList = (List<PublicacionHistoria>) modelAndView.getModel().get("historias");
        assertThat(resultList, is(empty()));
        verify(servicioHistorias, times(1)).filtrarHistoriasPorZona(zona);
    }
}
