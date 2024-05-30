package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.servicios.ServicioMapaMascoteroImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertNull;

public class ControladorMapaMascoteroTest {

    @InjectMocks
    private ControladorMapaMascotero controladorMapaMascotero;
    @Mock
    private ServicioMapaMascoteroImpl mockServicioMapaMascotero;
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void queFiltrarVeterinariasMuestreLasVeterinariasDisponibles() {
        // Preparacion
        List<Veterinaria> veterinarias = new ArrayList<>();
        veterinarias.add(new Veterinaria());
        veterinarias.add(new Veterinaria());
        when(mockServicioMapaMascotero.getVeterinarias()).thenReturn(veterinarias);
        // Ejecución
        ModelAndView vista = this.controladorMapaMascotero.filtrarPublicaciones("veterinarias");
        // Verificación
        assertThat(vista.getViewName(), equalToIgnoringCase("mapa-mascotero"));
        assertEquals(veterinarias, vista.getModel().get("veterinarias"));
    }

    @Test
    public void queFiltrarPeluqueriasMuestreLasPeluqueriasDisponibles() {
        // Preparacion
        List<Peluqueria> peluquerias = new ArrayList<>();
        peluquerias.add(new Peluqueria());
        peluquerias.add(new Peluqueria());
        when(mockServicioMapaMascotero.getPeluquerias()).thenReturn(peluquerias);
        // Ejecución
        ModelAndView vista = this.controladorMapaMascotero.filtrarPublicaciones("peluquerias");
        // Verificación
        assertThat(vista.getViewName(), equalToIgnoringCase("mapa-mascotero"));
        assertThat(vista.getModel().get("peluquerias").toString(), containsStringIgnoringCase("peluqueria"));
    }

    @Test
    public void queFiltrarMascotasMuestreLaMascotaDisponible() {
        // Preparacion
        List<Mascota> mascotas = new ArrayList<>();
        Mascota mascota = new Mascota(
                "Max",
                "http//imagen.com",
                "Le encanta jugar a la pelota",
                Zona.NORTE,
                MascotaTipo.PERRO,
                MascotaRaza.BOXER,
                MascotaColor.MARRON,
                MascotaEstado.CON_EL_DUENIO
        );
        mascotas.add(mascota);
        when(mockServicioMapaMascotero.getMascotas()).thenReturn(mascotas);
        // Ejecución
        ModelAndView vista = this.controladorMapaMascotero.filtrarPublicaciones("mascotas");
        // Verificación
        assertEquals("mapa-mascotero", vista.getViewName());
        assertEquals(mascotas, vista.getModel().get("mascotas"));
    }

    @Test
    public void queFiltrarMascotasDevuelvaLasTresMascotasDisponibles() {
        // Preparacion
        List<Mascota> mascotas = new ArrayList<>();
        Mascota mascota1 = new Mascota();
        Mascota mascota2 = new Mascota();
        Mascota mascota3 = new Mascota();
        mascotas.add(mascota1);
        mascotas.add(mascota2);
        mascotas.add(mascota3);
        when(mockServicioMapaMascotero.getMascotas()).thenReturn(mascotas);
        // Ejecución
        ModelAndView vista = this.controladorMapaMascotero.filtrarPublicaciones("mascotas");
        // Verificación
        assertEquals("mapa-mascotero", vista.getViewName());
        assertEquals(vista.getModel().get("mascotas"), mascotas);
        assert(mockServicioMapaMascotero.getMascotas().contains(mascota1));
        assert(mockServicioMapaMascotero.getMascotas().contains(mascota2));
        assert(mockServicioMapaMascotero.getMascotas().contains(mascota3));

    }

    @Test
    public void queAlFiltrarPublicacionesPorFiltroInvalidoDevuelvaNull() {
        // Preparacion
        when(mockServicioMapaMascotero.getVeterinarias()).thenReturn(new ArrayList<>());
        // Ejecución
        ModelAndView vista = this.controladorMapaMascotero.filtrarPublicaciones("filtroInvalido");
        // Verificación
        assertThat(vista.getViewName(), equalToIgnoringCase("mapa-mascotero"));
        assertNull(vista.getModel().get("veterinarias"));
        assertNull(vista.getModel().get("peluquerias"));
        assertNull(vista.getModel().get("mascotas"));
    }

    @Test
    public void queSePuedaFiltrarPorTipoDePublicacionVeterinariaAunqueNoHayaNinguna() {
        // Preparacion
        when(mockServicioMapaMascotero.getVeterinarias()).thenReturn(new ArrayList<>());
        // Ejecución
        ModelAndView vista = this.controladorMapaMascotero.filtrarPublicaciones("veterinarias");
        // Verificación
        assertThat(vista.getViewName(), equalToIgnoringCase("mapa-mascotero"));
        assertEquals(new ArrayList<>(), vista.getModel().get("veterinarias"));
        verify(mockServicioMapaMascotero).getVeterinarias();
    }

    @Test
    public void queSePuedaFiltrarPorTipoDePublicacionMascotaAunqueNoHayaNinguna() {
        // Preparacion
        when(mockServicioMapaMascotero.getMascotas()).thenReturn(new ArrayList<>());
        // Ejecución
        ModelAndView vista = this.controladorMapaMascotero.filtrarPublicaciones("mascotas");
        // Verificación
        assertThat(vista.getViewName(), equalToIgnoringCase("mapa-mascotero"));
        assertEquals(new ArrayList<>(), vista.getModel().get("mascotas"));
        verify(mockServicioMapaMascotero).getMascotas();
    }

    @Test
    public void queSePuedaFiltrarPorTipoDePublicacionPeluqueriasAunqueNoHayaNinguna() {
        // Preparacion
        when(mockServicioMapaMascotero.getPeluquerias()).thenReturn(new ArrayList<>());
        // Ejecución
        ModelAndView vista = this.controladorMapaMascotero.filtrarPublicaciones("peluquerias");
        // Verificación
        assertThat(vista.getViewName(), equalToIgnoringCase("mapa-mascotero"));
        assertEquals(new ArrayList<>(), vista.getModel().get("peluquerias"));
        verify(mockServicioMapaMascotero).getPeluquerias();
    }
    @Test
    public void queDevuelvaUnModelAndViewComoObjeto() {
        // Ejecucion
        ModelAndView vistaDonacion = controladorMapaMascotero.filtrarPublicaciones("veterinarias");
        // Verificacion
        assertThat(vistaDonacion.getClass().getSimpleName(), equalToIgnoringCase("ModelAndView"));
    }

}
