package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.PublicacionDonacion;
import com.tallerwebi.dominio.PublicacionTipo;
import com.tallerwebi.dominio.Zona;
import com.tallerwebi.dominio.servicios.ServicioPublicarDonacion;
import com.tallerwebi.dominio.servicios.ServicioPublicarDonacionImp;
import com.tallerwebi.dominio.servicios.ServicioPublicarHistoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

public class ControladorPublicarDonacionTest {

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
        Double montoACubrir = 4000.000;
        String nombreMascota = "Pancho";
        Zona zona = Zona.OESTE;
        String descripcion = "Esto es una descripcion";
        MockMultipartFile imagen = new MockMultipartFile(
                "imagen",
                "imagen.jpg",
                "image/jpeg",
                "Imagen de prueba".getBytes());

        //ejecucion
        ModelAndView vista = controladorPublicarDonacion.publicarDonacion(nombreMascota,montoACubrir,zona,descripcion, imagen);

        //verificacion
        assertThat(vista.getViewName(), equalToIgnoringCase("publicar"));
    }

}
