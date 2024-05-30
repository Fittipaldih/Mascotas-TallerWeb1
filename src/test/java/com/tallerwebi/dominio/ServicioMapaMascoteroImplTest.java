package com.tallerwebi.dominio;
import com.tallerwebi.dominio.servicios.ServicioMapaMascoteroImpl;
import com.tallerwebi.infraestructura.RepositorioMascotaImpl;
import com.tallerwebi.infraestructura.RepositorioPeluqueriaImpl;
import com.tallerwebi.infraestructura.RepositorioVeterinariaImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

public class ServicioMapaMascoteroImplTest {
    @InjectMocks
    private ServicioMapaMascoteroImpl servicioMapaMascotero;
    @Mock
    private RepositorioMascotaImpl repositorioMascotaMock;
    @Mock
    private RepositorioVeterinariaImpl repositorioVeterinariaMock;
    @Mock
    private RepositorioPeluqueriaImpl repositorioPeluqueriaMock;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void queSePuedanObtenerTodasLasMascotasCuandoLaMascotaEs1Sola() {
        // Preparación
        List<Mascota> mascotasEsperadas = new ArrayList<>();
        Mascota mascota = new Mascota("Nina", "htttp:foto", "Es una salchicha", Zona.OESTE, MascotaTipo.PERRO, MascotaRaza.SALCHICHA, MascotaColor.NEGRO, MascotaEstado.CON_EL_DUENIO);
        mascotasEsperadas.add(mascota);
        when(repositorioMascotaMock.dameTodasLasMascotas()).thenReturn(mascotasEsperadas);
        // Ejecución
        List<Mascota> mascotasObtenidas = servicioMapaMascotero.getMascotas();
        // Validación
        assertThat(mascotasObtenidas.size(), equalTo(mascotasEsperadas.size()));
        assertThat(mascotasObtenidas, equalTo(mascotasEsperadas));
    }

    @Test
    public void queNoSeObtienenMascotasCuandoRepositorioNoContieneMascotas() {
        // Preparación
        List<Mascota> mascotas = new ArrayList<>();
        when(repositorioMascotaMock.dameTodasLasMascotas()).thenReturn(mascotas);
        // Ejecución
        List<Mascota> mascotasObtenidas = servicioMapaMascotero.getMascotas();
        // Validación
        assertThat(String.valueOf(mascotasObtenidas.isEmpty()), true);
        assertThat(repositorioMascotaMock.dameTodasLasMascotas().size(), equalTo(0));
    }


    @Test
    public void queSePuedanObtenerTodasLasMascotasCuandoLasMascotasSon2() {
        // Preparación
        List<Mascota> mascotasEsperadas = new ArrayList<>();
        Mascota mascota = new Mascota("Nina", "htttp:foto", "Es una salchicha", Zona.OESTE, MascotaTipo.PERRO, MascotaRaza.SALCHICHA, MascotaColor.NEGRO, MascotaEstado.CON_EL_DUENIO);
        Mascota mascota2 = new Mascota("Nina2", "htttp:foto2", "Es una salchicha2", Zona.NORTE, MascotaTipo.PERRO, MascotaRaza.SALCHICHA, MascotaColor.BLANCO, MascotaEstado.CON_EL_DUENIO);
        mascotasEsperadas.add(mascota);
        mascotasEsperadas.add(mascota2);
        when(repositorioMascotaMock.dameTodasLasMascotas()).thenReturn(mascotasEsperadas);
        // Ejecución
        List<Mascota> mascotasObtenidas = servicioMapaMascotero.getMascotas();
        // Validación
        assertThat(mascotasObtenidas.size(), equalTo(mascotasEsperadas.size()));
        assertThat(mascotasObtenidas, equalTo(mascotasEsperadas));
    }

    @Test
    public void queSePuedanObtenerTodasLasVeterinarias(){
        // Preparación
        List<Veterinaria> veterinariasEsperadas = new ArrayList<>();
        Veterinaria veterinaria = new Veterinaria(12341234L, "Veterinaria", "Direccion", Zona.OESTE, true);
        veterinariasEsperadas.add(veterinaria);
        when(repositorioVeterinariaMock.dameTodasLasVeterinarias()).thenReturn(veterinariasEsperadas);
        // Ejecución
        List<Veterinaria> veterinariasObtenidas = servicioMapaMascotero.getVeterinarias();
        // Validación
        assertThat(veterinariasObtenidas.size(), equalTo(veterinariasEsperadas.size()));
        assertThat(veterinariasObtenidas, equalTo(veterinariasEsperadas));
    }

    @Test
    public void queSePuedanObtenerTodasLasPeluqerias() {
        // Preparación
        List<Peluqueria> esperado = new ArrayList<>();
        Peluqueria peluqueria = new Peluqueria(12341234, "peluqueria", "Direccion", Zona.OESTE);
        esperado.add(peluqueria);
        when(repositorioPeluqueriaMock.dameTodasLasPeluquerias()).thenReturn(esperado);
        // Ejecución
        List<Peluqueria> obtenido = servicioMapaMascotero.getPeluquerias();
        // Validación
        assertThat(obtenido.size(), equalTo(esperado.size()));
        assertThat(obtenido, equalTo(esperado));
    }

}
