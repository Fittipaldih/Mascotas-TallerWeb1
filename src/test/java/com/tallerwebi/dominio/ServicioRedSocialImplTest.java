package com.tallerwebi.dominio;

import com.tallerwebi.dominio.repositorioInterfaces.RepositorioPublicacion;
import com.tallerwebi.dominio.servicios.ServicioRedSocialImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
public class ServicioRedSocialImplTest {


    private RepositorioPublicacion repositorioPublicacionMock;
    private ServicioRedSocialImpl servicioRedSocialImpl;

    @BeforeEach
    public void init() {
        this.repositorioPublicacionMock = mock(RepositorioPublicacion.class);
        this.servicioRedSocialImpl = new ServicioRedSocialImpl(repositorioPublicacionMock);
    }

    @Test
    public void queSePuedanObtenerTodasLasPublicaciones() {
        // Preparacion
        List<Publicacion> publicacionesMock = mock(List.class);
        when(publicacionesMock.size()).thenReturn(2);
        when(repositorioPublicacionMock.getPublicaciones()).thenReturn(publicacionesMock);
        // Ejecucion
        List<Publicacion> publicacionesRecibidas = servicioRedSocialImpl.getTodasLasPublicaciones();
        // Verificacion
        assertEquals(2, publicacionesRecibidas.size());
    }

    @Test
    public void queSePuedanObtenerTodasLasPublicacionesTipoBuscadoPorDuenio(){
        // Preparacion
        List<Publicacion> publicaciones = new ArrayList<>();
        PublicacionPerdido publicacion1 = new PublicacionPerdido();
        publicacion1.setTipoPublicacion(PublicacionTipo.BUSCADO_POR_DUENIO);
        publicaciones.add(publicacion1);
        when(repositorioPublicacionMock.getPublicacionesPorTipoPublicacion(PublicacionTipo.BUSCADO_POR_DUENIO)).thenReturn(publicaciones);
        // Ejecucion
        List<Publicacion> publicacionesRecibidas = servicioRedSocialImpl.getPublicacionesTipoBuscadoPORDuenio();
        // Verificacion
        assertEquals(publicacionesRecibidas, repositorioPublicacionMock.getPublicacionesPorTipoPublicacion(PublicacionTipo.BUSCADO_POR_DUENIO));
    }

    @Test
    public void queSePuedanObtenerTodasLasPublicacionesTipoBuscandoAlDuenio(){
        // Preparacion
        List<Publicacion> publicacionesBuscandoAlDueño = new ArrayList<>();
        PublicacionPerdido publicacion = new PublicacionPerdido();
        publicacion.setTipoPublicacion(PublicacionTipo.BUSCANDO_AL_DUENIO);
        publicacionesBuscandoAlDueño.add(publicacion);
        when(repositorioPublicacionMock.getPublicacionesPorTipoPublicacion(PublicacionTipo.BUSCANDO_AL_DUENIO)).thenReturn(publicacionesBuscandoAlDueño);
        // Ejecucion
        List<Publicacion> publicacionesRecibidas = servicioRedSocialImpl.getPublicacionesTipoBuscandoALDuenio();
        // Verificacion
        assertEquals(publicacionesRecibidas, repositorioPublicacionMock.getPublicacionesPorTipoPublicacion(PublicacionTipo.BUSCANDO_AL_DUENIO));
    }

    @Test
    public void queSePuedanObtenerTodasLasPublicacionesTipoDonacion(){
        // Preparacion
        List<Publicacion> publicaciones = new ArrayList<>();
        PublicacionPerdido publicacion1 = new PublicacionPerdido();
        publicacion1.setTipoPublicacion(PublicacionTipo.DONACION);
        publicaciones.add(publicacion1);
        when(repositorioPublicacionMock.getPublicacionesPorTipoPublicacion(PublicacionTipo.DONACION)).thenReturn(publicaciones);
        // Ejecucion
        List<Publicacion> publicacionesRecibidas = servicioRedSocialImpl.getPublicacionesTipoDonacion();
        // Verificacion
        assertEquals(publicacionesRecibidas, repositorioPublicacionMock.getPublicacionesPorTipoPublicacion(PublicacionTipo.DONACION));
    }

    @Test
    public void queSePuedanObtenerTodasLasPublicacionesTipoHistoria(){
        // Preparacion
        List<Publicacion> publicaciones = new ArrayList<>();
        PublicacionPerdido publicacion1 = new PublicacionPerdido();
        publicacion1.setTipoPublicacion(PublicacionTipo.HISTORIA);
        publicaciones.add(publicacion1);
        when(repositorioPublicacionMock.getPublicacionesPorTipoPublicacion(PublicacionTipo.HISTORIA)).thenReturn(publicaciones);
        // Ejecucion
        List<Publicacion> publicacionesRecibidas = servicioRedSocialImpl.getPublicacionesTipoHistoria();
        // Verificacion
        assertEquals(publicacionesRecibidas, repositorioPublicacionMock.getPublicacionesPorTipoPublicacion(PublicacionTipo.HISTORIA));
    }
}