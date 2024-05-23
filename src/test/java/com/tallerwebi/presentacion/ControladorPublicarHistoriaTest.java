package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.PublicacionHistoria;
import com.tallerwebi.dominio.PublicacionTipo;
import com.tallerwebi.dominio.Zona;
import com.tallerwebi.dominio.excepcion.HistoriaException;
import com.tallerwebi.dominio.servicios.ServicioPublicarHistoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.mockito.Mockito.*;

public class ControladorPublicarHistoriaTest{

    @InjectMocks
    private ControladorPublicarHistoria controladorPublicarHistoria;
    @Mock
    private ServicioPublicarHistoria servicioPublicarHistoriaImp;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void queDevuelvaElMensajeDeExitoCuandoPublicaCorrectamenteLaHistoria() throws Exception {
        //preparacion
        String titular = "Esto es un titular";
        String nombreMascota = "Pancho";
        Zona zona = Zona.OESTE;
        String descripcion = "Esto es una descripcion";
        MockMultipartFile imagen = new MockMultipartFile(
                "imagen",
                "imagen.jpg",
                "image/jpeg",
                "Imagen de prueba".getBytes());
        //ejecucion
        ModelAndView vista = controladorPublicarHistoria.publicarHistoria(titular, nombreMascota, zona, descripcion, imagen);
        //verificacion
        assertThat(vista.getViewName(), equalToIgnoringCase("publicar"));
    }

    @Test
    public void queDevuelvaElMensajeDeErrorCuandoOcurreUnProblemaAlPublicarLaHistoria() throws Exception {
        // Datos de prueba
        String titular = "Titular de prueba";
        String nombreMascota = "Fido";
        Zona zona = Zona.NORTE;
        String descripcion = "Descripción de prueba";
        MockMultipartFile imagen = new MockMultipartFile("imagen", "imagen.jpg", "image/jpeg", "Imagen de prueba".getBytes());
        // Configurar el mock para lanzar una excepción
        doThrow(new HistoriaException("Error al publicar")).when(servicioPublicarHistoriaImp).publicarHistoria(new PublicacionHistoria(titular, nombreMascota, zona, descripcion, PublicacionTipo.HISTORIA, imagen.getBytes()), imagen);
        // Ejecución
        ModelAndView modelAndView = controladorPublicarHistoria.publicarHistoria(titular, nombreMascota, zona, descripcion, imagen);
        // Verificación
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("publicar"));
        assertThat(modelAndView.getModel().get("error").toString(), equalToIgnoringCase("Error al publicar la historia. Intentá nuevamente."));
    }
}
