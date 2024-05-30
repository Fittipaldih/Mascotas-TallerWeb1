package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.PublicacionDTO;
import com.tallerwebi.dominio.PublicacionTipo;
import com.tallerwebi.dominio.Zona;
import com.tallerwebi.dominio.servicios.ServicioEditarDonacionImp;
import com.tallerwebi.dominio.servicios.ServicioPublicacionConversion;
import com.tallerwebi.dominio.servicios.ServicioRedSocialImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorEditarDonacionTest {

    @Mock
    private ServicioRedSocialImpl servicioRedSocial;
    @Mock
    private ServicioPublicacionConversion publicacionConversionService;
    @InjectMocks
    private ControladorEditarDonacion controladorEditarDonacion;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void queDevuelvaLaVistaRedSocialConUnMensajeDeExitoCuandoSePudoModificarLaPublicacion()  {
        // preparacion
        String nombreMascota = "Capo";
        Zona zona = Zona.OESTE;
        String descripcion = "Descripcion de la mascota original" ;
        MockMultipartFile imagen = new MockMultipartFile(
                "imagen",
                "imagen.jpg",
                "image/jpeg",
                "Imagen de prueba".getBytes());
        Long idPublicacionOriginal = 2L;
        Double montoACubrir = 40030.0;

        //Mock service
        List<Publicacion> publicaciones = new ArrayList<>();
        when(servicioRedSocial.getTodasLasPublicaciones()).thenReturn(publicaciones);
        List<PublicacionDTO> publicacionesDTO = new ArrayList<>();
        when(publicacionConversionService.convertirEntidadesADTOs(publicaciones)).thenReturn(publicacionesDTO);


        //ejecucion
        ModelAndView vista = this.controladorEditarDonacion.editarDonacion(nombreMascota,idPublicacionOriginal,montoACubrir,zona, descripcion, imagen);
        // verificacion
        assertThat(vista.getViewName(),equalToIgnoringCase("red-social"));
    }

    @Test
        public void queDevuelvaLaVistaRedSocialCuandoSePudoModificarLaPublicacion()  {
        // preparacion
        String nombreMascota = "Capo";
        Zona zona = Zona.OESTE;
        String descripcion = "Descripcion de la mascota original" ;
        MockMultipartFile imagen = new MockMultipartFile(
                "imagen",
                "imagen.jpg",
                "image/jpeg",
                "Imagen de prueba".getBytes());
        Long idPublicacionOriginal = 2L;
        Double montoACubrir = 40030.0;

        //Mock service
        List<Publicacion> publicaciones = new ArrayList<>();
        when(servicioRedSocial.getTodasLasPublicaciones()).thenReturn(publicaciones);
        List<PublicacionDTO> publicacionesDTO = new ArrayList<>();
        when(publicacionConversionService.convertirEntidadesADTOs(publicaciones)).thenReturn(publicacionesDTO);


        //ejecucion
        ModelAndView vista = this.controladorEditarDonacion.editarDonacion(nombreMascota,idPublicacionOriginal,montoACubrir,zona, descripcion, imagen);
        // verificacion
        assertThat(vista.getViewName(),equalToIgnoringCase("red-social"));
     }

     @Test
        public void queDevuelvaLaVistaRedSocialConUnMensajeDeErrorCuandoNoSePudoModificarLaPublicacion()  {
        // preparacion
         String nombreMascota = "Capo";
         Zona zona = Zona.OESTE;
         String descripcion = "Descripcion de la mascota original" ;
         MockMultipartFile imagen = new MockMultipartFile(
                 "imagen",
                 "imagen.jpg",
                 "image/jpeg",
                 "Imagen de prueba".getBytes());
         Long idPublicacionOriginal = 2L;
         Double montoACubrir = 40030.0;

         //Mock service
         List<Publicacion> publicaciones = new ArrayList<>();
         when(servicioRedSocial.getTodasLasPublicaciones()).thenReturn(publicaciones);
         List<PublicacionDTO> publicacionesDTO = new ArrayList<>();
         when(publicacionConversionService.convertirEntidadesADTOs(publicaciones)).thenReturn(publicacionesDTO);


         //ejecucion
         ModelAndView vista = this.controladorEditarDonacion.editarDonacion(nombreMascota,idPublicacionOriginal,montoACubrir,zona, descripcion, imagen);
         // verificacion
         assertThat(vista.getViewName(),equalToIgnoringCase("red-social"));
         assertThat(vista.getModel().get("error").toString(), equalToIgnoringCase("Error al editar la donación. Intentá nuevamente."));

    }
}
