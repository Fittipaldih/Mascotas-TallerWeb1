package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.PublicacionDonacion;
import com.tallerwebi.dominio.PublicacionTipo;
import com.tallerwebi.dominio.Zona;
import com.tallerwebi.dominio.excepcion.DonacionException;
import com.tallerwebi.dominio.servicios.ServicioPublicarDonacionImp;
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

public class ControladorPublicarDonacionTest{

    @InjectMocks
    private ControladorPublicarDonacion controladorPublicarDonacion;
    @Mock
    private ServicioPublicarDonacionImp servicioPublicarDonacion;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void queDevuelvaElMensajeDeExitoCuandoPublicaCorrectamenteLaDonacion() throws Exception {
        //preparacion
        String nombreMascota = "Corbata";
        Double monto = 55000.0;
        Zona zona = Zona.SUR;
        String descripcion = "Esto es una descripcion";
        MockMultipartFile imagen = new MockMultipartFile(
                "imagen",
                "imagen.jpg",
                "image/jpeg",
                "Imagen de prueba".getBytes());
        //ejecucion
        ModelAndView vista = controladorPublicarDonacion.publicarDonacion(nombreMascota, monto, zona, descripcion, imagen);
        //verificacion
        assertThat(vista.getViewName(), equalToIgnoringCase("publicar"));
    }

    @Test
    public void queDevuelvaElMensajeDeErrorCuandoOcurreUnProblemaAlPublicarLaDonacion() throws Exception {
        // Datos de prueba
        String nombreMascota = "Corbata";
        Double monto = 55000.0;
        Zona zona = Zona.SUR;
        String descripcion = "Esto es una descripcion";
        MockMultipartFile imagen = new MockMultipartFile(
                "imagen",
                "imagen.jpg",
                "image/jpeg",
                "Imagen de prueba".getBytes());
        // Configurar el mock para excepcion
        doThrow(new DonacionException("Error al publicar")).when(servicioPublicarDonacion).publicarDonacion(new PublicacionDonacion(monto, PublicacionTipo.DONACION, nombreMascota, zona, descripcion, imagen.getBytes()), imagen);
        // Ejecucion
        ModelAndView modelAndView = controladorPublicarDonacion.publicarDonacion(nombreMascota, monto, zona, descripcion, imagen);
        // Verificacion
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("publicar"));
    }

    @Test
    public void queValideElNombreMascotaVacioYDevuelvaMensajeDeError() throws Exception {
        // Preparacion
        String nombreMascota = ""; // Nombre vacio
        Double monto = 5000.0;
        Zona zona = Zona.NORTE;
        String descripcion = "Descripcion";
        MockMultipartFile imagen = new MockMultipartFile(
                "imagen",
                "imagen.jpg",
                "image/jpeg",
                "Imagen de prueba".getBytes());
        // Ejecucion
        ModelAndView vista = controladorPublicarDonacion.publicarDonacion(nombreMascota, monto, zona, descripcion, imagen);
        // Verificacion
        assertThat(vista.getViewName(), equalToIgnoringCase("publicar"));
        assertThat(vista.getModel().get("error").toString(), equalToIgnoringCase("Error al publicar la donación. Intentá nuevamente."));
    }

    @Test
    public void queValideElMontoDeDonacionNoValidoPorSerNegativoYDevuelvaMensajeDeError() throws Exception {
        // Preparacion
        String nombreMascota = "Cleo";
        Double monto = -1000.0; // Monto negativo
        Zona zona = Zona.SUR;
        String descripcion = "Descripcionn";
        MockMultipartFile imagen = new MockMultipartFile(
                "imagen",
                "imagen.jpg",
                "image/jpeg",
                "Imagen de prueba".getBytes());
        // Ejecucion
        ModelAndView vista = controladorPublicarDonacion.publicarDonacion(nombreMascota, monto, zona, descripcion, imagen);
        // Verificacion
        assertThat(vista.getViewName(), equalToIgnoringCase("publicar"));
        assertThat(vista.getModel().get("error").toString(), equalToIgnoringCase("Error al publicar la donación. Intentá nuevamente."));
    }

    @Test
    public void queValideElMontoDeDonacionNoValidoPorSerMuyGrandeYDevuelvaMensajeDeError() throws Exception {
        // Preparacion
        String nombreMascota = "Cleo";
        Double monto = 143143143143124000.0; // Monto gigante
        Zona zona = Zona.SUR;
        String descripcion = "Descripcionn";
        MockMultipartFile imagen = new MockMultipartFile(
                "imagen",
                "imagen.jpg",
                "image/jpeg",
                "Imagen de prueba".getBytes());
        // Ejecucion
        ModelAndView vista = controladorPublicarDonacion.publicarDonacion(nombreMascota, monto, zona, descripcion, imagen);
        // Verificacion
        assertThat(vista.getViewName(), equalToIgnoringCase("publicar"));
        assertThat(vista.getModel().get("error").toString(), equalToIgnoringCase("Error al publicar la donación. Intentá nuevamente."));
    }

    @Test
    public void queValideLaZonaNoSeleccionadaYDevuelvaMensajeDeError() throws Exception {
        // Preparacion
        String nombreMascota = "Terry";
        Double monto = 5000.0;
        Zona zona = null; // Zona no seleccionada
        String descripcion = "Descripcionn";
        MockMultipartFile imagen = new MockMultipartFile(
                "imagen",
                "imagen.jpg",
                "image/jpeg",
                "Imagen de prueba".getBytes());
        // Ejecucion
        ModelAndView vista = controladorPublicarDonacion.publicarDonacion(nombreMascota, monto, zona, descripcion, imagen);
        // Verificacion
        assertThat(vista.getViewName(), equalToIgnoringCase("publicar"));
        assertThat(vista.getModel().get("error").toString(), equalToIgnoringCase("Error al publicar la donación. Intentá nuevamente."));
    }

    @Test
    public void queValideLaDescripcionVaciaYDevuelvaMensajeDeError() throws Exception {
        // Preparacion
        String nombreMascota = "Lolo";
        Double monto = 5000.0;
        Zona zona = Zona.NORTE;
        String descripcion = ""; // Descripcion vacía
        MockMultipartFile imagen = new MockMultipartFile(
                "imagen",
                "imagen.jpg",
                "image/jpeg",
                "Imagen de prueba".getBytes());
        // Ejecucion
        ModelAndView vista = controladorPublicarDonacion.publicarDonacion(nombreMascota, monto, zona, descripcion, imagen);
        // Verificacion
        assertThat(vista.getViewName(), equalToIgnoringCase("publicar"));
        assertThat(vista.getModel().get("error").toString(), equalToIgnoringCase("Error al publicar la donación. Intentá nuevamente."));
    }


}
