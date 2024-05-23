package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.PublicacionDonacion;
import com.tallerwebi.dominio.PublicacionTipo;
import com.tallerwebi.dominio.Zona;
import com.tallerwebi.dominio.excepcion.DonacionException;
import com.tallerwebi.dominio.servicios.ServicioPublicarDonacion;
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
        MockitoAnnotations.initMocks(this);
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
        // Configurar el mock para lanzar una excepción
        doThrow(new DonacionException("Error al publicar")).when(servicioPublicarDonacion).publicarDonacion(new PublicacionDonacion(monto, PublicacionTipo.DONACION, nombreMascota, zona, descripcion, imagen.getBytes()), imagen);
        // Ejecución
        ModelAndView modelAndView = controladorPublicarDonacion.publicarDonacion(nombreMascota, monto, zona, descripcion, imagen);
        // Verificación
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("publicar"));
    }

}
