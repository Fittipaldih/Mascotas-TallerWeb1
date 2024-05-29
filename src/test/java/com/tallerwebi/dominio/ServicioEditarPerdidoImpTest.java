package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.PublicacionInexistenteExeption;
import com.tallerwebi.dominio.repositorioInterfaces.RepositorioPublicacion;
import com.tallerwebi.dominio.servicios.ServicioEditarPerdidoImp;
import com.tallerwebi.dominio.servicios.ServicioRedSocialImpl;
import com.tallerwebi.infraestructura.RepositorioPublicacionImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ServicioEditarPerdidoImpTest {

    private RepositorioPublicacionImpl repositorioPublicacionMock;
    private ServicioEditarPerdidoImp servicioEditarPerdidoImp;

    @BeforeEach
    public void init() {
        this.repositorioPublicacionMock = mock(RepositorioPublicacionImpl.class);
        this.servicioEditarPerdidoImp = new ServicioEditarPerdidoImp(repositorioPublicacionMock);
    }
// casos a verficar
//
//    Que el método lanza una excepción cuando la publicación no existe.
//    Que el método del repositorio se llama cuando la publicación existe.

    @Test
    public void queLanceExcepcionCuandoSeIntenteEditarUnaPublicacionNoExistente()  {
        Long idPublicacionInexistente = 99L;

        when(repositorioPublicacionMock.getPublicacionPorId(idPublicacionInexistente)).thenReturn(null);

        assertThrows(PublicacionInexistenteExeption.class, () -> {
            servicioEditarPerdidoImp.editarPerdido(idPublicacionInexistente, "Capo", 1138721497L,
                    "Agustin", MascotaColor.NEGRO, MascotaRaza.MAINE_COON, PublicacionTipo.BUSCANDO_AL_DUENIO,
                    Zona.OESTE, "Esto es una descripcion de prueba", "Esto es una direccion de prueba", new byte[021]);
        });
    }

    @Test
    public void queLlameAlMetodoDelRepositorioCuandoPublicacionExiste() throws PublicacionInexistenteExeption {
        Long idPublicacionExistente = 1L;
        Publicacion publicacionExistente = new PublicacionPerdido();

        when(repositorioPublicacionMock.getPublicacionPorId(idPublicacionExistente)).thenReturn(publicacionExistente);

        servicioEditarPerdidoImp.editarPerdido(idPublicacionExistente, "Capo", 1138721497L,
                "Agustin", MascotaColor.NEGRO, MascotaRaza.MAINE_COON, PublicacionTipo.BUSCANDO_AL_DUENIO,
                Zona.OESTE, "Esto es una descripcion de prueba", "Esto es una direccion de prueba", new byte[021]);

        verify(repositorioPublicacionMock, times(1)).editarPerdido(eq(idPublicacionExistente), eq("Capo"), eq(1138721497L),
                eq("Agustin"), eq(MascotaColor.NEGRO), eq(MascotaRaza.MAINE_COON), eq(PublicacionTipo.BUSCANDO_AL_DUENIO),
                eq(Zona.OESTE), eq("Esto es una descripcion de prueba"), eq("Esto es una direccion de prueba"), any(byte[].class));
    }

    @Test
    public void queLanceExcepcionCuandoSeIntenteEditarUnaPublicacionYNoSeaUnaPublicacionPerdido()  {
        Long idPublicacionIncorrecta = 1L;
        Publicacion publicacionIncorrecta = new PublicacionHistoria();

        when(repositorioPublicacionMock.getPublicacionPorId(idPublicacionIncorrecta)).thenReturn(publicacionIncorrecta);

        assertThrows(PublicacionInexistenteExeption.class, () -> {
            servicioEditarPerdidoImp.editarPerdido(idPublicacionIncorrecta, "Capo", 1138721497L,
                    "Agustin", MascotaColor.NEGRO, MascotaRaza.MAINE_COON, PublicacionTipo.BUSCANDO_AL_DUENIO,
                    Zona.OESTE, "Esto es una descripcion de prueba", "Esto es una direccion de prueba", new byte[021]);
        });
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