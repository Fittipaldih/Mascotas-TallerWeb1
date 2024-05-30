package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.HistoriaException;
import com.tallerwebi.dominio.servicios.ServicioPublicarHistoriaImp;
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

class ServicioPublicarHistoriaTest {

    @Mock
    private RepositorioPublicacionImpl repositorioPublicacion;

    @InjectMocks
    private ServicioPublicarHistoriaImp servicioPublicarHistoria;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void quePubliqueUnaHistoriaConImagen() throws IOException, HistoriaException {
        //preparacion
        PublicacionHistoria historia = new PublicacionHistoria();
        MultipartFile imagenMock = mock(MultipartFile.class);
        byte[] imagenBytes = "imagen".getBytes();
        when(imagenMock.isEmpty()).thenReturn(false);
        when(imagenMock.getBytes()).thenReturn(imagenBytes);
        //ejecucion
        servicioPublicarHistoria.publicarHistoria(historia, imagenMock);
        //verificacion
        assertArrayEquals(imagenBytes, historia.getImagen());
        verify(repositorioPublicacion, times(1)).guardarHistoria(historia);
    }

    @Test
    void quePubliqueUnaHistoriaSinImagen() throws HistoriaException {
        //preparacion
        PublicacionHistoria historia = new PublicacionHistoria();
        MultipartFile imagenMock = mock(MultipartFile.class);
        when(imagenMock.isEmpty()).thenReturn(true);
        //ejecucion
        servicioPublicarHistoria.publicarHistoria(historia, imagenMock);
        //verificacion
        assertNull(historia.getImagen());
        verify(repositorioPublicacion, times(1)).guardarHistoria(historia);
    }

    @Test
    void queDevuelvaMensajeDeErrorCuandoHayErrorEnLaImagen() throws IOException {
        //preparacion
        PublicacionHistoria historia = new PublicacionHistoria();
        MultipartFile imagenMock = mock(MultipartFile.class);
        when(imagenMock.isEmpty()).thenReturn(false);
        when(imagenMock.getBytes()).thenThrow(new IOException("Error de IO"));
        //ejecucion
        Exception exception = assertThrows(RuntimeException.class, () -> {
            servicioPublicarHistoria.publicarHistoria(historia, imagenMock);
        });
        //verificacion
        assertThat(exception.getMessage(), equalToIgnoringCase("Error al procesar la imagen"));
        verify(repositorioPublicacion, never()).guardarHistoria(historia);
    }
}