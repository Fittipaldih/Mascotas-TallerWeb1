package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.PublicacionInexistenteExeption;
import com.tallerwebi.dominio.servicios.ServicioEditarDonacionImp;
import com.tallerwebi.dominio.servicios.ServicioEditarHistoriaImp;
import com.tallerwebi.infraestructura.RepositorioPublicacionImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

public class ServicioEditarDonacionImpTest {
    @Mock
    private RepositorioPublicacionImpl repositorioPublicacionMock;
    @InjectMocks
    private ServicioEditarDonacionImp servicioEditarDonacionImp;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void queLanceExcepcionCuandoSeIntenteEditarUnaPublicacionNoExistente() {
        Long idPublicacionInexistente = 99L;

        when(repositorioPublicacionMock.getPublicacionPorId(idPublicacionInexistente)).thenReturn(null);

        assertThrows(PublicacionInexistenteExeption.class, () -> {
            servicioEditarDonacionImp.editarDonacion(idPublicacionInexistente, "Capo",4000.00,
                    Zona.OESTE, "Esto es una descripcion de prueba", new byte[021]);
        });
    }

    @Test
    public void queLanceExcepcionCuandoSeIntenteEditarUnaPublicacionYNoSeaUnaPublicacionDonacion() throws PublicacionInexistenteExeption {
        Long idPublicacionIncorrecta = 1L;
        Publicacion publicacionIncorrecta = new PublicacionPerdido();

        when(repositorioPublicacionMock.getPublicacionPorId(idPublicacionIncorrecta)).thenReturn(publicacionIncorrecta);

        assertThrows(PublicacionInexistenteExeption.class, () -> {
            servicioEditarDonacionImp.editarDonacion(idPublicacionIncorrecta, "Capo",4000.00,
                    Zona.OESTE, "Esto es una descripcion de prueba", new byte[021]);
        });
    }

    @Test
    public void queLlameAlMetodoDelRepositorioCuandoPublicacionExiste() throws PublicacionInexistenteExeption {
        Long idPublicacionExistente = 1L;
        Publicacion publicacionExistente = new PublicacionDonacion();

        when(repositorioPublicacionMock.getPublicacionPorId(idPublicacionExistente)).thenReturn(publicacionExistente);

            servicioEditarDonacionImp.editarDonacion(idPublicacionExistente, "Capo",4000.00,
                    Zona.OESTE, "Esto es una descripcion de prueba", new byte[021]);

        verify(repositorioPublicacionMock, times(1)).editarDonacion(eq(idPublicacionExistente),
                eq("Capo"), eq(4000.00),
                eq( Zona.OESTE), eq("Esto es una descripcion de prueba"),
                any(byte[].class)); // caulquier valor de ese tipo
    }
}

//Explicación de los componentes
//verify(repositorioPublicacionMock, times(1)):

//verify es un método de Mockito que verifica si un método en un mock fue llamado con ciertos argumentos.
//repositorioPublicacionMock es el mock del repositorio que estás verificando.

//times(1) indica que el método debe haber sido llamado exactamente una vez.
//editarPerdido(...):  Este es el método del mock (repositorioPublicacionMock) que estás verificando.

//Los parámetros dentro del paréntesis son los argumentos con los que esperas que se haya llamado el método.

//eq(argumento): eq es un método de Mockito que crea un matcher para verificar que el argumento pasado al método sea igual al valor esperado. Se usa para comparar valores específicos.