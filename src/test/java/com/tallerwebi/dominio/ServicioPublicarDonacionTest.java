package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.DonacionException;
import com.tallerwebi.dominio.servicios.ServicioPublicarDonacionImp;
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

class ServicioPublicarDonacionTest {

    @Mock
    private RepositorioPublicacionImpl repositorioPublicacion;

    @InjectMocks
    private ServicioPublicarDonacionImp servicioPublicarDonacion;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void quePubliqueUnaDonacionConImagen() throws IOException, DonacionException {
        //preparacion
        PublicacionDonacion donacion = new PublicacionDonacion();
        MultipartFile imagenMock = mock(MultipartFile.class);
        byte[] imagenBytes = "imagen".getBytes();

        when(imagenMock.isEmpty()).thenReturn(false);
        when(imagenMock.getBytes()).thenReturn(imagenBytes);

        //ejecucion
        servicioPublicarDonacion.publicarDonacion(donacion, imagenMock);

        //verificacion
        assertArrayEquals(imagenBytes, donacion.getImagen());
        verify(repositorioPublicacion, times(1)).guardarDonacion(donacion);
    }

    @Test
    void quePubliqueUnaDonacionSinImagen() throws DonacionException {
        //preparacion
        PublicacionDonacion donacion = new PublicacionDonacion();
        MultipartFile imagenMock = mock(MultipartFile.class);

        when(imagenMock.isEmpty()).thenReturn(true);

        //ejecucion
        servicioPublicarDonacion.publicarDonacion(donacion, imagenMock);

        //verificacion
        assertNull(donacion.getImagen());
        verify(repositorioPublicacion, times(1)).guardarDonacion(donacion);
    }

    @Test
    void queDevuelvaMensajeDeErrorCuandoHayErrorEnLaImagen() throws IOException {
        //preparacion
        PublicacionDonacion donacion = new PublicacionDonacion();
        MultipartFile imagenMock = mock(MultipartFile.class);

        when(imagenMock.isEmpty()).thenReturn(false);
        when(imagenMock.getBytes()).thenThrow(new IOException("Error de IO"));

        //ejecucion
        Exception exception = assertThrows(RuntimeException.class, () -> {
            servicioPublicarDonacion.publicarDonacion(donacion, imagenMock);
        });

        //verificacion
        assertThat(exception.getMessage(), equalToIgnoringCase("Error al procesar la imagen"));
        verify(repositorioPublicacion, never()).guardarDonacion(donacion);
    }
}