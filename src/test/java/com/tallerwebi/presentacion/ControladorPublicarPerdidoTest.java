package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.PerdidoException;
import com.tallerwebi.dominio.servicios.ServicioPublicarPerdido;
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

public class ControladorPublicarPerdidoTest {

    @InjectMocks
    private ControladorPublicarPerdido controladorPublicarPerdido;

    @Mock
    private ServicioPublicarPerdido servicioPublicarPerdidoImp;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void queDevuelvaElMensajeDeExitoCuandoPublicaCorrectamenteElPerdido() throws Exception {
        //preparacion
        String direccion = "Varela 123";
        String nombreMascota = "Coquita";
        Zona zona = Zona.SUR;
        MascotaColor mascotaColor = MascotaColor.MARRON;
        String descripcion = "Perdido en el parque";
        String nombreContacto = "Juan";
        Long telefonoContacto = 123456789L;
        PublicacionTipo tipoPublicacionPerdido = PublicacionTipo.BUSCADO_POR_DUENIO;
        MascotaRaza mascotaRaza = MascotaRaza.SALCHICHA;
        MockMultipartFile imagen = new MockMultipartFile(
                "imagen",
                "imagen.jpg",
                "image/jpeg",
                "Imagen de prueba".getBytes());
        //ejecución
        ModelAndView vista = controladorPublicarPerdido.publicarPerdido(direccion, nombreMascota, zona, mascotaColor, descripcion, nombreContacto, telefonoContacto, tipoPublicacionPerdido, mascotaRaza, imagen);
        //verificación
        assertThat(vista.getViewName(), equalToIgnoringCase("publicar"));
        //assertThat(vista.getModel().get("mensaje").toString(), equalToIgnoringCase("¡La publicación ha sido creada exitosamente!"));
    }

    @Test
    public void queDevuelvaElMensajeDeErrorCuandoOcurreUnProblemaAlPublicarElPerdido() throws Exception {
        //preparacion
        String direccion = "Av de Mayo 123";
        String nombreMascota = "Copito";
        Zona zona = Zona.SUR;
        MascotaColor mascotaColor = MascotaColor.MARRON;
        String descripcion = "Perdido cerca de la escuela";
        String nombreContacto = "Juan";
        Long telefonoContacto = 123456789L;
        PublicacionTipo tipoPublicacionPerdido = PublicacionTipo.BUSCANDO_AL_DUENIO;
        MascotaRaza mascotaRaza = MascotaRaza.BOXER;
        MockMultipartFile imagen = new MockMultipartFile("imagen", "imagen.jpg", "image/jpeg", "Imagen de prueba".getBytes());
        doThrow(new PerdidoException("Error al publicar")).when(servicioPublicarPerdidoImp).publicarPerdido(any(PublicacionPerdido.class), eq(imagen));
        //ejecuciom
        ModelAndView modelAndView = controladorPublicarPerdido.publicarPerdido(direccion, nombreMascota, zona, mascotaColor, descripcion, nombreContacto, telefonoContacto, tipoPublicacionPerdido, mascotaRaza, imagen);
        //verificacion
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("publicar"));
        assertThat(modelAndView.getModel().get("error").toString(), equalToIgnoringCase("Error al publicar la mascota perdida. Intentá nuevamente."));
    }
}
