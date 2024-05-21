package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.MascotaNoEncontrada;
import com.tallerwebi.dominio.servicios.ServicioMapaMascoteroImpl;
import com.tallerwebi.dominio.servicios.ServicioPerfilMascotaImpl;
import com.tallerwebi.infraestructura.RepositorioMascotaImpl;
import com.tallerwebi.infraestructura.RepositorioPeluqueriaImpl;
import com.tallerwebi.infraestructura.RepositorioVeterinariaImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

public class ServicioPerfilMascotaImplTest {

    private RepositorioMascotaImpl repositorioMascotaMock;
    private ServicioPerfilMascotaImpl servicioPerfilMascotaImpl;

    @BeforeEach
    public void init() {
        repositorioMascotaMock = mock(RepositorioMascotaImpl.class);
        servicioPerfilMascotaImpl = new ServicioPerfilMascotaImpl(repositorioMascotaMock);
    }

    @Test
    public void queSeObtengaLaMascotaCorrectaPorId1CuandoTengoDosMascotas() throws MascotaNoEncontrada {
        // Preparación
        Mascota mascota = new Mascota("Nina", "htttp:foto", "Es una salchicha", Zona.OESTE, MascotaTipo.PERRO, MascotaRaza.SALCHICHA, MascotaColor.NEGRO, MascotaEstado.CON_EL_DUENIO);
        Mascota mascota2 = new Mascota("Nina2", "htttp:foto", "Es una salchicha", Zona.NORTE, MascotaTipo.PERRO, MascotaRaza.SALCHICHA, MascotaColor.NEGRO, MascotaEstado.CON_EL_DUENIO);

        when(repositorioMascotaMock.buscarMascotaPorId(1L)).thenReturn(mascota);
        // Ejecución
        Mascota mascotaObtenida = servicioPerfilMascotaImpl.buscarMascotaPorId(1L);
        // Validación
        assertThat(mascotaObtenida, equalTo(mascota));
        assertThat(mascotaObtenida.getId(), equalTo(mascota.getId()));
    }

    @Test
    public void queSeObtengaLaMascotaConId2CuandoBuscoPorID2() throws MascotaNoEncontrada {
        // Preparación
        Mascota mascota = new Mascota("Nina", "htttp:foto", "Es una salchicha", Zona.OESTE, MascotaTipo.PERRO, MascotaRaza.SALCHICHA, MascotaColor.NEGRO, MascotaEstado.CON_EL_DUENIO);
        Mascota mascota2 = new Mascota("Nina2", "htttp:foto", "Es una salchicha", Zona.NORTE, MascotaTipo.PERRO, MascotaRaza.SALCHICHA, MascotaColor.NEGRO, MascotaEstado.CON_EL_DUENIO);

        when(repositorioMascotaMock.buscarMascotaPorId(2L)).thenReturn(mascota2);
        // Ejecución
        Mascota mascotaObtenida = servicioPerfilMascotaImpl.buscarMascotaPorId(2L);
        // Validación
        assertThat(mascotaObtenida, equalTo(mascota2));
        assertThat(mascotaObtenida.getId(), equalTo(mascota2.getId()));
    }
}
