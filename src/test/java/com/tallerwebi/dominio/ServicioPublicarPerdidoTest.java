package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.PerdidoException;
import com.tallerwebi.dominio.servicios.ServicioPublicarPerdidoImp;
import com.tallerwebi.infraestructura.RepositorioPublicacionImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServicioPublicarPerdidoTest {

    @Mock
    private RepositorioPublicacionImpl repositorioPublicacion;

    @InjectMocks
    private ServicioPublicarPerdidoImp servicioPublicarPerdidoImp;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void quePubliqueUnPerdidoConImagen() throws IOException, PerdidoException {
        //preparacion
        PublicacionPerdido perdido = new PublicacionPerdido();
        MultipartFile imagenMock = mock(MultipartFile.class);
        byte[] imagenBytes = "imagen".getBytes();

        when(imagenMock.isEmpty()).thenReturn(false);
        when(imagenMock.getBytes()).thenReturn(imagenBytes);

        //ejecucion
        servicioPublicarPerdidoImp.publicarPerdido(perdido, imagenMock);

        //verificacion
        assertArrayEquals(imagenBytes, perdido.getImagen());
        verify(repositorioPublicacion, times(1)).guardarPerdido(perdido);
    }

    @Test
    void quePubliqueUnPerdidoSinImagen() throws PerdidoException {
        //preparacion
        PublicacionPerdido perdido = new PublicacionPerdido();
        MultipartFile imagenMock = mock(MultipartFile.class);

        when(imagenMock.isEmpty()).thenReturn(true);

        //ejecucion
        servicioPublicarPerdidoImp.publicarPerdido(perdido, imagenMock);

        //verificacion
        assertNull(perdido.getImagen());
        verify(repositorioPublicacion, times(1)).guardarPerdido(perdido);
    }

    @Test
    void queDevuelvaMensajeDeErrorCuandoHayErrorEnLaImagen() throws IOException {
        //preparacion
        PublicacionPerdido perdido = new PublicacionPerdido();
        MultipartFile imagenMock = mock(MultipartFile.class);

        when(imagenMock.isEmpty()).thenReturn(false);
        when(imagenMock.getBytes()).thenThrow(new IOException("Error de IO"));

        //ejecucion
        Exception exception = assertThrows(RuntimeException.class, () -> {
            servicioPublicarPerdidoImp.publicarPerdido(perdido, imagenMock);
        });

        //verificacion
        assertThat(exception.getMessage(), equalToIgnoringCase("Error al procesar la imagen"));
        verify(repositorioPublicacion, never()).guardarPerdido(perdido);
    }
}
