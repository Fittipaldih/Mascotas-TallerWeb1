package com.tallerwebi.dominio;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioPublicacion;
import com.tallerwebi.dominio.servicios.ServicioDetallePublicacionImpl;
import com.tallerwebi.infraestructura.RepositorioComentarioImpl;
import com.tallerwebi.infraestructura.RepositorioPublicacionImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

public class ServicioDetallePublicacionImplTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private RepositorioComentarioImpl repositorioComentario;

    @Mock
    private RepositorioPublicacionImpl repositorioPublicacionImpl;

    @InjectMocks
    private ServicioDetallePublicacionImpl servicioDetallePublicacion;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
    }

    @Test
    public void queDevuelvaLaPublicacionCorrectaPorId() throws Exception {
        // Preparación
        Long id = 10L;
        Publicacion publicacion = new PublicacionPerdido();
        publicacion.setIdPublicacion(id);
        when(repositorioPublicacionImpl.getPublicacionPorId(id)).thenReturn(publicacion);
        // Ejecución
        Publicacion publicacionObtenida = servicioDetallePublicacion.getPublicacion(id);
        // Verificación
        assertThat(publicacionObtenida, equalTo(publicacion));
    }

    @Test
    public void queDevuelvaLaPublicacionDonacionOkPorSuId() throws Exception {
        // Preparación
        Long idDonacion = 6L;
        Publicacion donacion = new PublicacionDonacion();
        donacion.setIdPublicacion(idDonacion);
        when(repositorioPublicacionImpl.getPublicacionPorId(idDonacion)).thenReturn(donacion);
        // Ejecución
        Publicacion publicacionDonacionObtenida = servicioDetallePublicacion.getPublicacion(idDonacion);
        // Verificación
        assertThat(publicacionDonacionObtenida, equalTo(donacion));
    }

    @Test
    public void queDevuelvaExcepcionSiLaPublicacionNoExiste() throws Exception {
        // Preparación
        Long idPublicacionNoExistente = 99L;
        when(repositorioPublicacionImpl.getPublicacionPorId(idPublicacionNoExistente)).thenReturn(null);
        // Ejecución y verificación
        try {
            servicioDetallePublicacion.getPublicacion(idPublicacionNoExistente);
            fail("Esperaba excepción");
        } catch (Exception e) {
            assertThat(e.getMessage(), equalToIgnoringCase("No existe publicacion con el id " + idPublicacionNoExistente + " o fue eliminada"));
        }
    }

    @Test
    public void queDevuelvaExcepcionSiQuieroComentarAlgoPeroElTextoEstaVacio() throws Exception {
        // preparacion
        Publicacion publicacion = new PublicacionPerdido();
        Long id = 5L;
        publicacion.setIdPublicacion(id);
        String textoDelComentario = "";

        // Simulación
        when(repositorioPublicacionImpl.getPublicacionPorId(id)).thenReturn(publicacion);
        doThrow(new Exception("El comentario esta vacio")).when(repositorioComentario).guardarNuevoComentarioEnPublicacion(textoDelComentario, id);

        // ejecucion y verificacion
        assertThrows(Exception.class, () -> servicioDetallePublicacion.hacerComentario(textoDelComentario, id));
    }

    @Test
    public void queDevuelvaLaPublicacionPerdidoOkPorSuId() throws Exception {
        // Preparación
        Long idPerdido = 1L;
        Publicacion perdido = new PublicacionPerdido();
        perdido.setIdPublicacion(idPerdido);
        when(repositorioPublicacionImpl.getPublicacionPorId(idPerdido)).thenReturn(perdido);
        // Ejecución
        Publicacion publicacionPerdidoObtenida = servicioDetallePublicacion.getPublicacion(idPerdido);
        // Verificación
        assertThat(publicacionPerdidoObtenida, equalTo(perdido));
    }

    @Test
    public void queDevuelvaLaPublicacionHistoriaOkPorSuId() throws Exception {
        // Preparación
        Long idHistoria = 2L;
        Publicacion historia = new PublicacionHistoria();
        historia.setIdPublicacion(idHistoria);
        when(repositorioPublicacionImpl.getPublicacionPorId(idHistoria)).thenReturn(historia);
        // Ejecución
        Publicacion publicacionHistoriaObtenida = servicioDetallePublicacion.getPublicacion(idHistoria);
        // Verificación
        assertThat(publicacionHistoriaObtenida, equalTo(historia));
    }
}
