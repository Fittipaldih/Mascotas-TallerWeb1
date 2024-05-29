package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.PublicacionInexistenteExeption;
import com.tallerwebi.dominio.servicios.ServicioEditarHistoriaImp;
import com.tallerwebi.dominio.servicios.ServicioEditarPerdidoImp;
import com.tallerwebi.infraestructura.RepositorioPublicacionImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ServicioEditarHistoriaImpTest {

    private RepositorioPublicacionImpl repositorioPublicacionMock;
    private ServicioEditarHistoriaImp servicioEditarHistoriaImp;

    @BeforeEach
    public void init() {
        this.repositorioPublicacionMock = mock(RepositorioPublicacionImpl.class);
        this.servicioEditarHistoriaImp = new ServicioEditarHistoriaImp(repositorioPublicacionMock);
    }

    @Test
    public void queLanceExcepcionCuandoSeIntenteEditarUnaPublicacionNoExistente() {
        Long idPublicacionInexistente = 99L;

        when(repositorioPublicacionMock.getPublicacionPorId(idPublicacionInexistente)).thenReturn(null);

        assertThrows(PublicacionInexistenteExeption.class, () -> {
            servicioEditarHistoriaImp.editarHistoria(idPublicacionInexistente, "Esta es la Historia de capo", "Capo",
                    Zona.OESTE, "Esto es una descripcion de prueba", new byte[021]);

        });
    }

    @Test
    public void queLanceExcepcionCuandoSeIntenteEditarUnaPublicacionYNoSeaUnaPublicacionHisoria()  {
        Long idPublicacionIncorrecta = 1L;
        Publicacion publicacionIncorrecta = new PublicacionPerdido();

        when(repositorioPublicacionMock.getPublicacionPorId(idPublicacionIncorrecta)).thenReturn(publicacionIncorrecta);

        assertThrows(PublicacionInexistenteExeption.class, () -> {
            servicioEditarHistoriaImp.editarHistoria(idPublicacionIncorrecta, "Esta es la Historia de capo", "Capo",
                    Zona.OESTE, "Esto es una descripcion de prueba", new byte[021]);});
    }

    @Test
    public void queLlameAlMetodoDelRepositorioCuandoPublicacionExiste() throws PublicacionInexistenteExeption {
        Long idPublicacionExistente = 1L;
        Publicacion publicacionExistente = new PublicacionHistoria();

        when(repositorioPublicacionMock.getPublicacionPorId(idPublicacionExistente)).thenReturn(publicacionExistente);

        servicioEditarHistoriaImp.editarHistoria(idPublicacionExistente, "Esta es la Historia de capo", "Capo",
                Zona.OESTE, "Esto es una descripcion de prueba", new byte[021]);

        verify(repositorioPublicacionMock, times(1)).editarHistoria(eq(idPublicacionExistente),
                eq("Esta es la Historia de capo"), eq("Capo"),
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