package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.servicios.ServicioEditarDonacionImp;
import com.tallerwebi.dominio.servicios.ServicioEditarHistoriaImp;
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

public class ControladorEditarHistoriaTest {

    @Mock
    private ServicioRedSocialImpl servicioRedSocial;
    @Mock
    private ServicioPublicacionConversion publicacionConversionService;
    @InjectMocks
    private ControladorEditarHistoria controladorEditarHistoria;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.publicacionConversionService = mock(ServicioPublicacionConversion.class);
        this.servicioRedSocial = mock(ServicioRedSocialImpl.class);
    }

    @Test
    public void queDevuelvaLaVistaRedSocialConUnMensajeDeExitoCuandoSePudoModificarLaPublicacion()  {
        // preparacion
        String nombreMascota = "Capo";
        String titular = "Esta es la Historia de Capo";
        PublicacionTipo tipoPublicacion = PublicacionTipo.BUSCANDO_AL_DUENIO;
        Zona zona = Zona.OESTE;
        String descripcion = "Descripcion de la mascota original" ;
        MockMultipartFile imagen = new MockMultipartFile(
                "imagen",
                "imagen.jpg",
                "image/jpeg",
                "Imagen de prueba".getBytes());
        Long idPublicacionOriginal = 2L;
        //Mock service
        List<Publicacion> publicaciones = new ArrayList<>();
        when(servicioRedSocial.getTodasLasPublicaciones()).thenReturn(publicaciones);
        List<PublicacionDTO> publicacionesDTO = new ArrayList<>();
        when(publicacionConversionService.convertirEntidadesADTOs(publicaciones)).thenReturn(publicacionesDTO);
        //ejecucion
        ModelAndView vista = this.controladorEditarHistoria.editarHistoria(titular,nombreMascota,idPublicacionOriginal,zona, descripcion, imagen);
        // verificacion
        assertThat(vista.getViewName(),equalToIgnoringCase("red-social"));
    }

    @Test
        public void queDevuelvaLaVistaRedSocialCuandoSePudoModificarLaPublicacion()  {
        // preparacion
        String nombreMascota = "Capo";
        String titular = "Esta es la Historia de Capo";
        PublicacionTipo tipoPublicacion = PublicacionTipo.BUSCANDO_AL_DUENIO;
        Zona zona = Zona.OESTE;
        String descripcion = "Descripcion de la mascota original" ;
        MockMultipartFile imagen = new MockMultipartFile(
                "imagen",
                "imagen.jpg",
                "image/jpeg",
                "Imagen de prueba".getBytes());
        Long idPublicacionOriginal = 2L;
        //Mock service
        List<Publicacion> publicaciones = new ArrayList<>();
        when(servicioRedSocial.getTodasLasPublicaciones()).thenReturn(publicaciones);
//        when(servicioEditarPerdidoImpoMock.editarPerdido(idPublicacionOriginal,nombreMascota,telefonoContacto,nombreContacto,mascotaColor,mascotaRaza,tipoPublicacion,zona,descripcion,direccion,imagen);
        List<PublicacionDTO> publicacionesDTO = new ArrayList<>();
        when(publicacionConversionService.convertirEntidadesADTOs(publicaciones)).thenReturn(publicacionesDTO);
        //ejecucion
        ModelAndView vista = this.controladorEditarHistoria.editarHistoria(titular,nombreMascota,idPublicacionOriginal,zona, descripcion, imagen);
        // verificacion
        assertThat(vista.getViewName(),equalToIgnoringCase("red-social"));
     }

     @Test
        public void queDevuelvaLaVistaRedSocialConUnMensajeDeErrorCuandoNoSePudoModificarLaPublicacion()  {
        // preparacion
         String nombreMascota = "Capo";
         String titular = "Esta es la Historia de Capo";
         PublicacionTipo tipoPublicacion = PublicacionTipo.BUSCANDO_AL_DUENIO;
         Zona zona = Zona.OESTE;
         String descripcion = "Descripcion de la mascota original" ;
         MockMultipartFile imagen = new MockMultipartFile(
                 "imagen",
                 "imagen.jpg",
                 "image/jpeg",
                 "Imagen de prueba".getBytes());
         Long idPublicacionOriginal = 2L;
         //Mock service
         List<Publicacion> publicaciones = new ArrayList<>();
         when(servicioRedSocial.getTodasLasPublicaciones()).thenReturn(publicaciones);
//        when(servicioEditarPerdidoImpoMock.editarPerdido(idPublicacionOriginal,nombreMascota,telefonoContacto,nombreContacto,mascotaColor,mascotaRaza,tipoPublicacion,zona,descripcion,direccion,imagen);
         List<PublicacionDTO> publicacionesDTO = new ArrayList<>();
         when(publicacionConversionService.convertirEntidadesADTOs(publicaciones)).thenReturn(publicacionesDTO);
         //ejecucion
         ModelAndView vista = this.controladorEditarHistoria.editarHistoria(titular,nombreMascota,idPublicacionOriginal,zona, descripcion, imagen);
         // verificacion
         assertThat(vista.getViewName(),equalToIgnoringCase("red-social"));
         assertThat(vista.getModel().get("error").toString(), equalToIgnoringCase("Error al editar la historia. Intentá nuevamente."));

    }
}
