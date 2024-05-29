package com.tallerwebi.dominio;

import com.tallerwebi.dominio.servicios.ServicioPublicacionConversion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Base64;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ServicioPublicacionConversionTest {

    private ServicioPublicacionConversion servicioPublicacionConversion;

    @BeforeEach
    void setUp() {
        servicioPublicacionConversion = new ServicioPublicacionConversion();
    }

    @Test
    void queConviertaEntidadADTOConImagen() {
        //Preparacion
        Publicacion publicacion = new PublicacionHistoria();
        publicacion.setIdPublicacion(1L);
        publicacion.setTipoPublicacion(PublicacionTipo.HISTORIA);
        publicacion.setDescripcion("Descripción de prueba");
        publicacion.setNombreMascota("Fido");
        publicacion.setZona(Zona.NORTE);
        publicacion.setMascotaColor(MascotaColor.DORADO);
        publicacion.setMascotaRaza(MascotaRaza.SALCHICHA);
        publicacion.setImagen("imagen".getBytes());

        //Ejecucion
        PublicacionDTO dto = servicioPublicacionConversion.convertirEntidadADTO(publicacion);

        //Verificacion
        assertNotNull(dto);
        assertEquals(publicacion.getIdPublicacion(), dto.getIdPublicacion());
        assertEquals(publicacion.getTipoPublicacion().name(), dto.getTipoPublicacion());
        assertEquals(publicacion.getFechaPublicacion(), dto.getFechaPublicacion());
        assertEquals(publicacion.getDescripcion(), dto.getDescripcion());
        assertEquals(publicacion.getNombreMascota(), dto.getNombreMascota());
        assertEquals(publicacion.getZona().name(), dto.getZona());
        assertEquals(publicacion.getMascotaColor().name(), dto.getMascotaColor());
        assertEquals(publicacion.getMascotaRaza().name(), dto.getMascotaRaza());
        assertEquals(Base64.getEncoder().encodeToString(publicacion.getImagen()), dto.getImagen());
    }

    @Test
    void queConviertaEntidadADTONoContengaImagen() {
        //Preparacion
        Publicacion publicacion = new PublicacionPerdido();
        publicacion.setIdPublicacion(1L);
        publicacion.setTipoPublicacion(PublicacionTipo.BUSCANDO_AL_DUENIO);
        publicacion.setDescripcion("Descripción de prueba");
        publicacion.setNombreMascota("Fido");
        publicacion.setZona(Zona.SUR);
        publicacion.setMascotaColor(MascotaColor.BLANCO);
        publicacion.setMascotaRaza(MascotaRaza.LABRADOR);

        //Ejecucion
        PublicacionDTO dto = servicioPublicacionConversion.convertirEntidadADTO(publicacion);

        //Verificacion
        assertNotNull(dto);
        assertEquals(publicacion.getIdPublicacion(), dto.getIdPublicacion());
        assertEquals(publicacion.getTipoPublicacion().name(), dto.getTipoPublicacion());
        assertEquals(publicacion.getDescripcion(), dto.getDescripcion());
        assertEquals(publicacion.getNombreMascota(), dto.getNombreMascota());
        assertEquals(publicacion.getZona().name(), dto.getZona());
        assertEquals(publicacion.getMascotaColor().name(), dto.getMascotaColor());
        assertEquals(publicacion.getMascotaRaza().name(), dto.getMascotaRaza());
        assertNull(dto.getImagen());
    }

    @Test
    void queConviertaListaDeEntidadesADTOs() {
        //Preparacion
        Publicacion publicacion1 = new PublicacionDonacion();
        publicacion1.setIdPublicacion(1L);
        publicacion1.setTipoPublicacion(PublicacionTipo.DONACION);
        publicacion1.setDescripcion("Descripción de prueba 1");
        publicacion1.setNombreMascota("Fido");
        publicacion1.setZona(Zona.OESTE);
        publicacion1.setMascotaColor(MascotaColor.GRIS);
        publicacion1.setMascotaRaza(MascotaRaza.PERSA);

        Publicacion publicacion2 = new PublicacionDonacion();
        publicacion2.setIdPublicacion(2L);
        publicacion2.setTipoPublicacion(PublicacionTipo.DONACION);
        publicacion2.setDescripcion("Descripción de prueba 2");
        publicacion2.setNombreMascota("Max");
        publicacion2.setZona(Zona.SUR);
        publicacion2.setMascotaColor(MascotaColor.NEGRO);
        publicacion2.setMascotaRaza(MascotaRaza.PASTOR_ALEMAN);

        List<Publicacion> publicaciones = List.of(publicacion1, publicacion2);

        //Ejecucion
        List<PublicacionDTO> dtos = servicioPublicacionConversion.convertirEntidadesADTOs(publicaciones);

        //Verificacion
        assertNotNull(dtos);
        assertEquals(2, dtos.size());

        //Verificacion de la primera conversion
        PublicacionDTO dto1 = dtos.get(0);
        assertEquals(publicacion1.getIdPublicacion(), dto1.getIdPublicacion());
        assertEquals(publicacion1.getTipoPublicacion().name(), dto1.getTipoPublicacion());
        assertEquals(publicacion1.getFechaPublicacion(), dto1.getFechaPublicacion());
        assertEquals(publicacion1.getDescripcion(), dto1.getDescripcion());
        assertEquals(publicacion1.getNombreMascota(), dto1.getNombreMascota());
        assertEquals(publicacion1.getZona().name(), dto1.getZona());
        assertEquals(publicacion1.getMascotaColor().name(), dto1.getMascotaColor());
        assertEquals(publicacion1.getMascotaRaza().name(), dto1.getMascotaRaza());

        //Verificacion de la segunda conversion
        PublicacionDTO dto2 = dtos.get(1);
        assertEquals(publicacion2.getIdPublicacion(), dto2.getIdPublicacion());
        assertEquals(publicacion2.getTipoPublicacion().name(), dto2.getTipoPublicacion());
        assertEquals(publicacion2.getFechaPublicacion(), dto2.getFechaPublicacion());
        assertEquals(publicacion2.getDescripcion(), dto2.getDescripcion());
        assertEquals(publicacion2.getNombreMascota(), dto2.getNombreMascota());
        assertEquals(publicacion2.getZona().name(), dto2.getZona());
        assertEquals(publicacion2.getMascotaColor().name(), dto2.getMascotaColor());
        assertEquals(publicacion2.getMascotaRaza().name(), dto2.getMascotaRaza());
    }
}
