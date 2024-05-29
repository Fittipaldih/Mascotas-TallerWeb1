package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.PublicacionHistoria;
import com.tallerwebi.dominio.PublicacionTipo;
import com.tallerwebi.dominio.Zona;
import com.tallerwebi.dominio.excepcion.HistoriaException;
import com.tallerwebi.dominio.servicios.interfaces.ServicioPublicarHistoria;
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
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void queDevuelvaElMensajeDeExitoCuandoPublicaCorrectamenteLaHistoria() throws Exception {
        //preparacion
        String titular = "Titulo de panchito";
        String nombreMascota = "Pancho";
        Zona zona = Zona.OESTE;
        String descripcion = "una descripcion";
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
        //preparacion
        String titular = "Titulo de cirin";
        String nombreMascota = "Ciro";
        Zona zona = Zona.NORTE;
        String descripcion = "descripcion prueba";
        MockMultipartFile imagen = new MockMultipartFile("imagen", "imagen.jpg", "image/jpeg", "Imagen de prueba".getBytes());
        // Configurar el mock para lanzar una excepcion
        doThrow(new HistoriaException("Error al publicar")).when(servicioPublicarHistoriaImp).publicarHistoria(new PublicacionHistoria(titular, nombreMascota, zona, descripcion, PublicacionTipo.HISTORIA, imagen.getBytes()), imagen);
        // ejecucion
        ModelAndView modelAndView = controladorPublicarHistoria.publicarHistoria(titular, nombreMascota, zona, descripcion, imagen);
        // verificar
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("publicar"));
        assertThat(modelAndView.getModel().get("error").toString(), equalToIgnoringCase("Error al publicar la historia. Intentá nuevamente."));
    }


    @Test
    public void queValideElNombreMascotaVacioYDevuelvaMensajeDeError() throws Exception {
        //preparacion
        String titular = "Esto es un tituloo";
        String nombreMascota = "";
        Zona zona = Zona.OESTE;
        String descripcion = "Esto es una descripcion";
        MockMultipartFile imagen = new MockMultipartFile(
                "imagen",
                "imagen.jpg",
                "image/jpeg",
                "Imagen de prueba".getBytes());
        //ejecucion
        ModelAndView vista = controladorPublicarHistoria.publicarHistoria(titular, nombreMascota, zona, descripcion, imagen);
        // Verificacion
        assertThat(vista.getViewName(), equalToIgnoringCase("publicar"));
        assertThat(vista.getModel().get("error").toString(), equalToIgnoringCase("Error al publicar la historia. Intentá nuevamente."));
    }
    @Test
    public void queValideElTitularVacioYDevuelvaMensajeDeError() throws Exception {
        // preparacion
        String titular = "";
        String nombreMascota = "Tobi";
        Zona zona = Zona.NORTE;
        String descripcion = "Descripcion";
        MockMultipartFile imagen = new MockMultipartFile(
                "imagen",
                "imagen.jpg",
                "image/jpeg",
                "Imagen de prueba".getBytes());
        // eject
        ModelAndView vista = controladorPublicarHistoria.publicarHistoria(titular, nombreMascota, zona, descripcion, imagen);
        // validac
        assertThat(vista.getViewName(), equalToIgnoringCase("publicar"));
        assertThat(vista.getModel().get("error").toString(), equalToIgnoringCase("Error al publicar la historia. Intentá nuevamente."));
    }
    @Test
    public void queValideLaZonaNoSeleccionadaYDevuelvaMensajeDeError() throws Exception {
        // Preparacion
        String titular = "Encontré a mi perro perdido";
        String nombreMascota = "Pelusa";
        Zona zona = null; // Zona no seleccionada
        String descripcion = "Descripción de la historia";
        MockMultipartFile imagen = new MockMultipartFile(
                "imagen",
                "imagen.jpg",
                "image/jpeg",
                "Imagen de prueba".getBytes());
        // Ejecucion
        ModelAndView vista = controladorPublicarHistoria.publicarHistoria(titular, nombreMascota, zona, descripcion, imagen);
        // Verificacionn
        assertThat(vista.getViewName(), equalToIgnoringCase("publicar"));
        assertThat(vista.getModel().get("error").toString(), equalToIgnoringCase("Error al publicar la historia. Intentá nuevamente."));
    }
    @Test
    public void queValideLaDescripcionVaciaYDevuelvaMensajeDeError() throws Exception {
        // Preparacion
        String titular = "Encontré a mi perro perdido";
        String nombreMascota = "Pelusa";
        Zona zona = Zona.NORTE;
        String descripcion = "";
        MockMultipartFile imagen = new MockMultipartFile(
                "imagen",
                "imagen.jpg",
                "image/jpeg",
                "Imagen de prueba".getBytes());
        // Ejecucion
        ModelAndView vista = controladorPublicarHistoria.publicarHistoria(titular, nombreMascota, zona, descripcion, imagen);
        // Verificacion
        assertThat(vista.getViewName(), equalToIgnoringCase("publicar"));
        assertThat(vista.getModel().get("error").toString(), equalToIgnoringCase("Error al publicar la historia. Intentá nuevamente."));
    }

}
