package com.tallerwebi.dominio.servicios;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.PublicacionDTO;
import com.tallerwebi.dominio.PublicacionPerdido;
import com.tallerwebi.dominio.PublicacionTipo;
import com.tallerwebi.dominio.excepcion.PerdidoException;
import com.tallerwebi.dominio.servicios.interfaces.ServicioPublicarPerdido;
import com.tallerwebi.infraestructura.RepositorioPublicacionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ServicioPublicarPerdidoImp implements ServicioPublicarPerdido {

    @Autowired
    private RepositorioPublicacionImpl repositorioPublicarPerdido;
    @Autowired
    private ServicioPublicacionConversion publicacionConversionService;

    @Override
    public void publicarPerdido(PublicacionPerdido perdido, MultipartFile imagen) throws PerdidoException {
        if(imagen != null && !imagen.isEmpty()){
            try {
                perdido.setImagen(imagen.getBytes());
            } catch (IOException e){
                throw new RuntimeException("Error al procesar la imagen", e);
            }
        }
        perdido.setTipoPublicacion(PublicacionTipo.BUSCANDO_AL_DUENIO);
        repositorioPublicarPerdido.guardarPerdido(perdido);
    }

    public List<PublicacionDTO> obtenerTodasLasDonaciones() {
        List<Publicacion> publicaciones = repositorioPublicarPerdido.getPublicacionesPorTipoPublicacion(PublicacionTipo.BUSCANDO_AL_DUENIO);
        return publicacionConversionService.convertirEntidadesADTOs(publicaciones);
    }
}
