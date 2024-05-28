package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.servicios.ServicioDetallePublicacionImpl;
import com.tallerwebi.dominio.servicios.ServicioPublicacionConversion;
import com.tallerwebi.dominio.servicios.ServicioRedSocialImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsMapContaining.hasKey;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.mockito.Mockito.*;

public class ControladorDetallePublicacionTest {

    @InjectMocks
    private ControladorDetallePublicacion controladorDetallePublicacion;

    @Mock
    private ServicioDetallePublicacionImpl mockServicioDetallePublicacion;
    @Mock
    ServicioRedSocialImpl servicioRedSocial;
    @Mock
    ServicioPublicacionConversion publicacionConversionService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void queMeDevuelvaLaPublicacionDePerdidoConId1CuandoHagaLaPeticion() throws Exception {
        // Prepar
        Long id = 1L;
        Publicacion perdido = new PublicacionPerdido();
        perdido.setIdPublicacion(id);
        when(mockServicioDetallePublicacion.getPublicacion(perdido.getIdPublicacion())).thenReturn(perdido);
        // Ejecucion
        ModelAndView vista = this.controladorDetallePublicacion.mostrarDetallePublicacion(perdido.getIdPublicacion());
        // Verificacion
        assertThat(vista.getViewName(), is(equalToIgnoringCase("detalle-publicacion")));
        assertThat(vista.getModel(), hasKey("publicacionData"));
    }

    @Test
    public void queMeDevuelvaLaPublicacionDeHistoriaConId2CuandoHagaLaPeticion() throws Exception {
        // Preparacion
        Publicacion perdido = new PublicacionPerdido();
        Publicacion historia = new PublicacionHistoria();
        perdido.setIdPublicacion(1L);
        historia.setIdPublicacion(2L);
        historia.setTipoPublicacion(PublicacionTipo.HISTORIA);
        when(mockServicioDetallePublicacion.getPublicacion(historia.getIdPublicacion())).thenReturn(historia);
        // Ejecucion
        ModelAndView vista = this.controladorDetallePublicacion.mostrarDetallePublicacion(historia.getIdPublicacion());
        // Verificacion
        assertThat(vista.getViewName(), is(equalToIgnoringCase("detalle-publicacion")));
        assertThat(vista.getModel(), hasKey("publicacionData"));
    }

    @Test
    public void queSiLaPublicacionNOseEncuentraVuelvaAlHomeConElMensajeDeExcepcionCorrecto() throws Exception {
        // Preparacion
        when(mockServicioDetallePublicacion.getPublicacion(10L)).thenThrow(new Exception("No existe publicacion con el id " + 10L + " o fue eliminada"));
        // Ejecucion
        ModelAndView vista = this.controladorDetallePublicacion.mostrarDetallePublicacion(10L);
        // Verificacion
        assertThat(vista.getViewName(), is(equalToIgnoringCase("home")));
        assertThat(vista.getModel().get("mensaje").toString(), equalToIgnoringCase("No existe publicacion con el id " + 10L + " o fue eliminada"));
    }

    @Test
    public void queHagaElComentarioYNoDevuelvaVista() throws Exception {
        // Preparacion
        Long idPublicacion = 1L;
        String textoComentario = "Este es un comentario de prueba.";
        doNothing().when(mockServicioDetallePublicacion).hacerComentario(textoComentario, idPublicacion);
        // Ejecucion
        controladorDetallePublicacion.realizarComentario(idPublicacion, textoComentario);
        // Verificacion
        verify(mockServicioDetallePublicacion, times(1)).hacerComentario(textoComentario, idPublicacion);
    }

    @Test
    public void queLanceUnaExcepcionSiHayErrorAlHacerComentario() throws Exception {
        // Preparacion
        Long idPublicacion = 1L;
        String textoComentario = "Este es un comentario de prueba.";
        // Ejecucion
        doThrow(new Exception("Error al guardar comentario")).when(mockServicioDetallePublicacion).hacerComentario(textoComentario, idPublicacion);
        // Verificacion
        controladorDetallePublicacion.realizarComentario(idPublicacion, textoComentario);
    }

    @Test
    public void queSeElimineLaPublicacionAlLlamarAlMetodoDelServicio() throws Exception {
        // Preparación
        Long idPublicacion = 1L;
        Publicacion publicacion = new PublicacionPerdido();
        when(mockServicioDetallePublicacion.getPublicacion(idPublicacion)).thenReturn(publicacion);
        doNothing().when(mockServicioDetallePublicacion).eliminarPublicacion(idPublicacion);
        // Ejecución
        mockServicioDetallePublicacion.eliminarPublicacion(idPublicacion);
        // Verificación
        verify(mockServicioDetallePublicacion, times(1)).eliminarPublicacion(idPublicacion);
    }
    @Test
    public void queDevuelvaUnModelAndViewComoObjeto() {
        // Ejecucion
        ModelAndView vistaDonacion = controladorDetallePublicacion.mostrarDetallePublicacion(1L);
        // Verificacion
        assertThat(vistaDonacion.getClass().getSimpleName(), equalToIgnoringCase("ModelAndView"));
    }
}
