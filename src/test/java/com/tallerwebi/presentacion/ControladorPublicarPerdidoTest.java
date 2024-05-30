package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.PerdidoException;
import com.tallerwebi.dominio.servicios.interfaces.ServicioPublicarPerdido;
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
        MockitoAnnotations.openMocks(this);
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
        //ejecucion
        ModelAndView vista = controladorPublicarPerdido.publicarPerdido(direccion, nombreMascota, zona, mascotaColor, descripcion, nombreContacto, telefonoContacto, tipoPublicacionPerdido, mascotaRaza, imagen);
        //verificacion
        assertThat(vista.getViewName(), equalToIgnoringCase("publicar"));
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

    @Test
    public void queValideLaDireccionVaciaYDevuelvaMensajeDeError() throws Exception {
        // Preparacion
        String direccion = ""; // VACIO
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
        // Ejecucion
        ModelAndView vista = controladorPublicarPerdido.publicarPerdido(direccion, nombreMascota, zona, mascotaColor, descripcion, nombreContacto, telefonoContacto, tipoPublicacionPerdido, mascotaRaza, imagen);
        // Verificacion
        assertThat(vista.getViewName(), equalToIgnoringCase("publicar"));
        assertThat(vista.getModel().get("error").toString(), equalToIgnoringCase("Error al publicar la mascota perdida. Intentá nuevamente."));
    }

    @Test
    public void queValideElNombreVacioYDevuelvaMensajeDeError() throws Exception {
        // Preparacion
        String direccion = "Av de Mayo 1212";
        String nombreMascota = "";// VACIO
        Zona zona = Zona.NORTE;
        MascotaColor mascotaColor = MascotaColor.NEGRO;
        String descripcion = "Perdido en el parque";
        String nombreContacto = "Juan";
        Long telefonoContacto = 123456789L;
        PublicacionTipo tipoPublicacionPerdido = PublicacionTipo.BUSCANDO_AL_DUENIO;
        MascotaRaza mascotaRaza = MascotaRaza.ABISINIO;
        MockMultipartFile imagen = new MockMultipartFile(
                "imagen",
                "imagen.jpg",
                "image/jpeg",
                "Imagen de prueba".getBytes());
        // Ejecucion
        ModelAndView vista = controladorPublicarPerdido.publicarPerdido(direccion, nombreMascota, zona, mascotaColor, descripcion, nombreContacto, telefonoContacto, tipoPublicacionPerdido, mascotaRaza, imagen);
        // Verificacion
        assertThat(vista.getViewName(), equalToIgnoringCase("publicar"));
        assertThat(vista.getModel().get("error").toString(), equalToIgnoringCase("Error al publicar la mascota perdida. Intentá nuevamente."));
    }

    @Test
    public void queValideZonaVaciaYDevuelvaMensajeDeError() throws Exception {
        // Preparacion
        String direccion = "Av de Mayo 1212";
        String nombreMascota = "CocaCola";
        Zona zona = null;
        MascotaColor mascotaColor = MascotaColor.NEGRO;
        String descripcion = "Perdido en el parque";
        String nombreContacto = "Juan";
        Long telefonoContacto = 123456789L;
        PublicacionTipo tipoPublicacionPerdido = PublicacionTipo.BUSCANDO_AL_DUENIO;
        MascotaRaza mascotaRaza = MascotaRaza.BEAGLE;
        MockMultipartFile imagen = new MockMultipartFile(
                "imagen",
                "imagen.jpg",
                "image/jpeg",
                "Imagen de prueba".getBytes());
        // Ejecucion
        ModelAndView vista = controladorPublicarPerdido.publicarPerdido(direccion, nombreMascota, zona, mascotaColor, descripcion, nombreContacto, telefonoContacto, tipoPublicacionPerdido, mascotaRaza, imagen);
        // Verificacion
        assertThat(vista.getViewName(), equalToIgnoringCase("publicar"));
        assertThat(vista.getModel().get("error").toString(), equalToIgnoringCase("Error al publicar la mascota perdida. Intentá nuevamente."));
    }

    @Test
    public void queValideColorVacioYDevuelvaMensajeDeError() throws Exception {
        // Preparacion
        String direccion = "Av de Mayo 1212";
        String nombreMascota = "CocaCola";
        Zona zona = Zona.CABA;
        MascotaColor mascotaColor = null;
        String descripcion = "Perdido en el parque";
        String nombreContacto = "Juan";
        Long telefonoContacto = 123456789L;
        PublicacionTipo tipoPublicacionPerdido = PublicacionTipo.BUSCANDO_AL_DUENIO;
        MascotaRaza mascotaRaza = MascotaRaza.BEAGLE;
        MockMultipartFile imagen = new MockMultipartFile(
                "imagen",
                "imagen.jpg",
                "image/jpeg",
                "Imagen de prueba".getBytes());
        // Ejecucion
        ModelAndView vista = controladorPublicarPerdido.publicarPerdido(direccion, nombreMascota, zona, mascotaColor, descripcion, nombreContacto, telefonoContacto, tipoPublicacionPerdido, mascotaRaza, imagen);
        // Verificacion
        assertThat(vista.getViewName(), equalToIgnoringCase("publicar"));
        assertThat(vista.getModel().get("error").toString(), equalToIgnoringCase("Error al publicar la mascota perdida. Intentá nuevamente."));
    }

    @Test
    public void queValideDescripcionVaciaYDevuelvaMensajeDeError() throws Exception {
        // Preparacion
        String direccion = "Av de Mayo 1212";
        String nombreMascota = "CocaCola";
        Zona zona = Zona.CABA;
        MascotaColor mascotaColor = MascotaColor.DORADO;
        String descripcion = "";
        String nombreContacto = "Juan";
        Long telefonoContacto = 123456789L;
        PublicacionTipo tipoPublicacionPerdido = PublicacionTipo.BUSCANDO_AL_DUENIO;
        MascotaRaza mascotaRaza = MascotaRaza.BEAGLE;
        MockMultipartFile imagen = new MockMultipartFile(
                "imagen",
                "imagen.jpg",
                "image/jpeg",
                "Imagen de prueba".getBytes());
        // Ejecucion
        ModelAndView vista = controladorPublicarPerdido.publicarPerdido(direccion, nombreMascota, zona, mascotaColor, descripcion, nombreContacto, telefonoContacto, tipoPublicacionPerdido, mascotaRaza, imagen);
        // Verificacion
        assertThat(vista.getViewName(), equalToIgnoringCase("publicar"));
        assertThat(vista.getModel().get("error").toString(), equalToIgnoringCase("Error al publicar la mascota perdida. Intentá nuevamente."));
    }

    @Test
    public void queValideNombreContactoVacioYDevuelvaMensajeDeError() throws Exception {
        // Preparacion
        String direccion = "Av de Mayo 1212";
        String nombreMascota = "CocaCola";
        Zona zona = Zona.CABA;
        MascotaColor mascotaColor = MascotaColor.DORADO;
        String descripcion = "Perdido en el parque";
        String nombreContacto = "";
        Long telefonoContacto = 123456789L;
        PublicacionTipo tipoPublicacionPerdido = PublicacionTipo.BUSCANDO_AL_DUENIO;
        MascotaRaza mascotaRaza = MascotaRaza.BEAGLE;
        MockMultipartFile imagen = new MockMultipartFile(
                "imagen",
                "imagen.jpg",
                "image/jpeg",
                "Imagen de prueba".getBytes());
        // Ejecucion
        ModelAndView vista = controladorPublicarPerdido.publicarPerdido(direccion, nombreMascota, zona, mascotaColor, descripcion, nombreContacto, telefonoContacto, tipoPublicacionPerdido, mascotaRaza, imagen);
        // Verificacion
        assertThat(vista.getViewName(), equalToIgnoringCase("publicar"));
        assertThat(vista.getModel().get("error").toString(), equalToIgnoringCase("Error al publicar la mascota perdida. Intentá nuevamente."));
    }

    @Test
    public void queValideTelefonoContactoVacioYDevuelvaMensajeDeError() throws Exception {
        // Preparacion
        String direccion = "Av de Mayo 1212";
        String nombreMascota = "Pepsi";
        Zona zona = Zona.CABA;
        MascotaColor mascotaColor = null;
        String descripcion = "Perdido en el parque";
        String nombreContacto = "Pepe Argento";
        Long telefonoContacto = null;
        PublicacionTipo tipoPublicacionPerdido = PublicacionTipo.BUSCANDO_AL_DUENIO;
        MascotaRaza mascotaRaza = MascotaRaza.BEAGLE;
        MockMultipartFile imagen = new MockMultipartFile(
                "imagen",
                "imagen.jpg",
                "image/jpeg",
                "Imagen de prueba".getBytes());
        // Ejecucion
        ModelAndView vista = controladorPublicarPerdido.publicarPerdido(direccion, nombreMascota, zona, mascotaColor, descripcion, nombreContacto, telefonoContacto, tipoPublicacionPerdido, mascotaRaza, imagen);
        // Verificacion
        assertThat(vista.getViewName(), equalToIgnoringCase("publicar"));
        assertThat(vista.getModel().get("error").toString(), equalToIgnoringCase("Error al publicar la mascota perdida. Intentá nuevamente."));
    }

    @Test
    public void queValideTipoPublicacionNullYDevuelvaMensajeDeError() throws Exception {
        // Preparacion
        String direccion = "Av de Mayo 1212";
        String nombreMascota = "Pepsi";
        Zona zona = Zona.CABA;
        MascotaColor mascotaColor = null;
        String descripcion = "Perdido en el parque";
        String nombreContacto = "Pepe Argento";
        Long telefonoContacto = 12341234L;
        PublicacionTipo tipoPublicacionPerdido = null;
        MascotaRaza mascotaRaza = MascotaRaza.BEAGLE;
        MockMultipartFile imagen = new MockMultipartFile(
                "imagen",
                "imagen.jpg",
                "image/jpeg",
                "Imagen de prueba".getBytes());
        // Ejecucion
        ModelAndView vista = controladorPublicarPerdido.publicarPerdido(direccion, nombreMascota, zona, mascotaColor, descripcion, nombreContacto, telefonoContacto, tipoPublicacionPerdido, mascotaRaza, imagen);
        // Verificacion
        assertThat(vista.getViewName(), equalToIgnoringCase("publicar"));
        assertThat(vista.getModel().get("error").toString(), equalToIgnoringCase("Error al publicar la mascota perdida. Intentá nuevamente."));
    }

    @Test
    public void queValideMascotaRazaNullYDevuelvaMensajeDeError() throws Exception {
        // Preparacion
        String direccion = "Av de Mayo 1212";
        String nombreMascota = "Pepsi";
        Zona zona = Zona.CABA;
        MascotaColor mascotaColor = null;
        String descripcion = "Perdido en el parque";
        String nombreContacto = "Pepe Argento";
        Long telefonoContacto = 12341234L;
        PublicacionTipo tipoPublicacionPerdido = PublicacionTipo.BUSCADO_POR_DUENIO;
        MascotaRaza mascotaRaza = null;
        MockMultipartFile imagen = new MockMultipartFile(
                "imagen",
                "imagen.jpg",
                "image/jpeg",
                "Imagen de prueba".getBytes());
        // Ejecucion
        ModelAndView vista = controladorPublicarPerdido.publicarPerdido(direccion, nombreMascota, zona, mascotaColor, descripcion, nombreContacto, telefonoContacto, tipoPublicacionPerdido, mascotaRaza, imagen);
        // Verificacion
        assertThat(vista.getViewName(), equalToIgnoringCase("publicar"));
        assertThat(vista.getModel().get("error").toString(), equalToIgnoringCase("Error al publicar la mascota perdida. Intentá nuevamente."));
    }


}
