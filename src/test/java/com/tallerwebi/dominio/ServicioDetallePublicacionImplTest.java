package com.tallerwebi.dominio;

import com.tallerwebi.dominio.repositorioInterfaces.RepositorioComentario;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioPublicacion;
import com.tallerwebi.dominio.servicios.ServicioDetallePublicacionImpl;
import com.tallerwebi.infraestructura.RepositorioComentarioImpl;
import com.tallerwebi.infraestructura.RepositorioPublicacionImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class ServicioDetallePublicacionImplTest {

    @Mock
    private RepositorioPublicacion repositorioPublicacion;
    @Mock
    private RepositorioComentarioImpl repositorioComentario;
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
        // Preparación
        Long id = 10L;
        Publicacion publicacion = new PublicacionPerdido();
        publicacion.setIdPublicacion(id);
        when(this.repositorioPublicacionImpl.getPublicacionPorId(id)).thenReturn(publicacion);

        // Ejecución
        Publicacion publicacionObtenida = this.servicioDetallePublicacion.getPublicacion(id);

        // Verificación
        assertThat(publicacionObtenida, equalTo(publicacion));
    }

    @Test
    public void queDevuelvaLaPublicacionDonacionOkPorSuId() throws Exception {
        // Preparación
        Long idDonacion = 6L;
        Publicacion donacion = new PublicacionDonacion();
        donacion.setIdPublicacion(idDonacion);
        when(repositorioPublicacionImpl.getPublicacionPorId(donacion.getIdPublicacion())).thenReturn(donacion);

        // Ejecución
        Publicacion publicacionDonacionObtenida = repositorioPublicacionImpl.getPublicacionPorId(idDonacion);

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

        // ejecucion y verificacion
        assertThrows(Exception.class, () -> servicioDetallePublicacion.hacerComentario(textoDelComentario, id));
    }



    @Test
    public void queDevuelvaLaPublicacionPerdidoOkPorSuId() throws Exception {
        // Preparación
        Long idPerdido = 1L;
        Publicacion perdido = new PublicacionPerdido();
        perdido.setIdPublicacion(idPerdido);
        when(repositorioPublicacionImpl.getPublicacionPorId(perdido.getIdPublicacion())).thenReturn(perdido);

        // Ejecución
        Publicacion publicacionPerdidoObtenida = repositorioPublicacionImpl.getPublicacionPorId(idPerdido);

        // Verificación
        assertThat(publicacionPerdidoObtenida, equalTo(perdido));
    }

    @Test
    public void queDevuelvaLaPublicacionHistoriaOkPorSuId() throws Exception {
        // Preparación
        Long idHistoria = 2L;
        Publicacion historia = new PublicacionHistoria();
        historia.setIdPublicacion(idHistoria);
        when(repositorioPublicacionImpl.getPublicacionPorId(historia.getIdPublicacion())).thenReturn(historia);

        // Ejecución
        Publicacion publicacionHistoriaObtenida = repositorioPublicacionImpl.getPublicacionPorId(idHistoria);

        // Verificación
        assertThat(publicacionHistoriaObtenida, equalTo(historia));
    }
}
