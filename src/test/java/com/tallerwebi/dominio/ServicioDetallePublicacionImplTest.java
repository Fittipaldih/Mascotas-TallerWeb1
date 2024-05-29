package com.tallerwebi.dominio;

import com.tallerwebi.dominio.repositorioInterfaces.RepositorioComentario;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioPublicacion;
import com.tallerwebi.dominio.servicios.ServicioDetallePublicacionImpl;
import com.tallerwebi.infraestructura.RepositorioPublicacionImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.testng.Assert.fail;

public class ServicioDetallePublicacionImplTest {

    @Mock
    private RepositorioPublicacion repositorioPublicacion;
    @Mock
    private RepositorioComentario repositorioComentario;
    @Mock
    private RepositorioPublicacionImpl repositorioPublicacionImpl;
    @InjectMocks
    private ServicioDetallePublicacionImpl servicioDetallePublicacion;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void queDevuelvaLaPublicacionCorrectaPorId() throws Exception {
        // SOLO ANDA OK PERO SI CORRO TODO ANDA MAL?
        // preparacion
        Long id = 10L;
        Publicacion publicacion = new PublicacionPerdido();
        publicacion.setIdPublicacion(id);
        // Mockear el repo para simular busqueda por ID
        Mockito.when(this.repositorioPublicacionImpl.getPublicacionPorId(id)).thenReturn(publicacion);
        // ejecucion
        Publicacion publicacionObtenida = this.servicioDetallePublicacion.getPublicacion(id);
        // verificacion
        assertThat(publicacionObtenida, equalTo(publicacion));
    }

    @Test
    public void queDevuelvaLaPublicacionDonacionOkPorSuId() throws Exception {
        // SOLO ANDA OK PERO SI CORRO TODO ANDA MAL?
        // prep
        Long idDonacion = 6L;
        Publicacion donacion = new PublicacionDonacion();
        donacion.setIdPublicacion(idDonacion);
        when(repositorioPublicacionImpl.getPublicacionPorId(donacion.getIdPublicacion())).thenReturn(donacion);
        // ejec
        Publicacion publicacionDonacionObtenida = repositorioPublicacionImpl.getPublicacionPorId(idDonacion);
        // verif
        assertThat(publicacionDonacionObtenida, equalTo(donacion));
    }

    @Test
    public void queDevuelvaExcepcionSiLaPublicacionNoExiste() throws Exception {
        // Preparacion
        Long idPublicacionNoExistente = 99L; // ID de una publi que no existe
        // Mockear el repo para simular busqueda por ID
        Mockito.when(repositorioPublicacionImpl.getPublicacionPorId(idPublicacionNoExistente)).thenReturn(null);
        // ejecucion
        try {
            servicioDetallePublicacion.getPublicacion(idPublicacionNoExistente);
            fail("esperaba excepcion");
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
        // ejecucion
        servicioDetallePublicacion.hacerComentario(textoDelComentario, id);
        // verificacion
        doThrow(Exception.class);
    }

    @Test
    public void queDevuelvaLaPublicacionPerdidoOkPorSuId() throws Exception {
        // prep
        Long idPerdido = 1L;
        Publicacion perdido = new PublicacionPerdido();
        perdido.setIdPublicacion(idPerdido);
        when(repositorioPublicacionImpl.getPublicacionPorId(perdido.getIdPublicacion())).thenReturn(perdido);
        // ejec
        Publicacion publicacionPerdidoObtenida = repositorioPublicacionImpl.getPublicacionPorId(idPerdido);
        // verif
        assertThat(publicacionPerdidoObtenida, equalTo(perdido));
    }

    @Test
    public void queDevuelvaLaPublicacionHistoriaOkPorSuId() throws Exception {
        // prep
        Long idHistoria = 2L;
        Publicacion historia = new PublicacionHistoria();
        historia.setIdPublicacion(idHistoria);
        when(repositorioPublicacionImpl.getPublicacionPorId(historia.getIdPublicacion())).thenReturn(historia);
        // ejec
        Publicacion publicacionHistoriaObtenida = repositorioPublicacionImpl.getPublicacionPorId(idHistoria);
        // verif
        assertThat(publicacionHistoriaObtenida, equalTo(historia));
    }



}
