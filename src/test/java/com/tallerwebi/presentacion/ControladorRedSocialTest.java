package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.PublicacionDTO;
import com.tallerwebi.dominio.Zona;
import com.tallerwebi.dominio.servicios.ServicioPublicacionConversion;
import com.tallerwebi.dominio.servicios.interfaces.ServicioRedSocial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.mockito.Mockito.when;

public class ControladorRedSocialTest {

    @InjectMocks
    private ControladorRedSocial controladorRedSocial;
    @Mock
    private ServicioRedSocial servicioRedSocial;
    @Mock
    private ServicioPublicacionConversion publicacionConversionService;
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void queAlPresionarPublicarDevuelvaLaPublicar() {
        // Mockear la dependencia
        when(servicioRedSocial.obtenerUltimasPublicaciones(3)).thenReturn(Collections.emptyList());
        // Ejecución
        ModelAndView vista = controladorRedSocial.publicar();
        // Verificación
        assertThat(vista.getViewName(), equalToIgnoringCase("publicar"));
    }

    @Test
    public void queAlPresionarMascotasPerdidasDevuelvaLaVistaPerdidos() {
        // Mockear las dependencias
        when(servicioRedSocial.getPublicacionesTipoPerdido()).thenReturn(Collections.emptyList());
        when(publicacionConversionService.convertirEntidadesADTOs(Collections.emptyList())).thenReturn(Collections.emptyList());
        // Ejecución
        ModelAndView vista = controladorRedSocial.mostrarPublicacionesPerdidos();
        // Verificación
        assertThat(vista.getViewName(), equalToIgnoringCase("perdidos"));
    }

    @Test
    public void queAlPresionarHistoriasDeMascotasDevuelvaLaHistorias() {
        // Mockear las dependencias
        when(servicioRedSocial.getPublicacionesTipoHistoria()).thenReturn(Collections.emptyList());
        when(publicacionConversionService.convertirEntidadesADTOs(Collections.emptyList())).thenReturn(Collections.emptyList());
        // Ejecución
        ModelAndView vista = controladorRedSocial.mostrarPublicacionesHistorias();
        // Verificación
        assertThat(vista.getViewName(), equalToIgnoringCase("historias"));
    }

    @Test
    public void queAlPresionarDonacionesAMascotasDevuelvaLaDonaciones() {
        // Mockear las dependencias
        when(servicioRedSocial.getPublicacionesTipoDonacion()).thenReturn(Collections.emptyList());
        when(publicacionConversionService.convertirEntidadesADTOs(Collections.emptyList())).thenReturn(Collections.emptyList());
        // Ejecución
        ModelAndView vista = controladorRedSocial.mostrarPublicacionesDonaciones();
        // Verificación
        assertThat(vista.getViewName(), equalToIgnoringCase("donaciones"));
    }

    @Test
    public void queDevuelvaUnModelAndViewComoObjeto() {
        // Mockear las dependencias
        when(servicioRedSocial.getPublicacionesTipoDonacion()).thenReturn(Collections.emptyList());
        when(publicacionConversionService.convertirEntidadesADTOs(Collections.emptyList())).thenReturn(Collections.emptyList());
        // Ejecución
        ModelAndView vistaDonacion = controladorRedSocial.mostrarPublicacionesDonaciones();
        // Verificación
        assertThat(vistaDonacion.getClass().getSimpleName(), equalToIgnoringCase("ModelAndView"));
    }
}
